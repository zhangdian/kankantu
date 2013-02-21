package com.bd17kaka.kankantu.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboTagDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Tags;
import com.bd17kaka.kankantu.weibo4j.model.Tag;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboTagService")
public class SinaWeiboTagServiceImpl implements SinaWeiboTagService {

	@Resource(name = "sinaWeiboTagDao")
	private SinaWeiboTagDao sinaWeiboTagDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;
	
	@Resource(name = "sinaWeiboTagService")
	private SinaWeiboTagService sinaWeiboTagService;

	@Override
	public void syncSinaWeiboTags(String userId) throws WeiboException, UserNotAuthorizeException, KankantuException {
		// 获取用户在sina微博里的tag信息
		List<TagInfo> listTagInfo = sinaWeiboTagService.listSinaWeiboTag(userId);
		if (null == listTagInfo) {
			return;
		}
		
		// 同步到我们的系统中
		sinaWeiboTagDao.insert(listTagInfo, userId);
	}

	@Override
	public List<TagInfo> list(String userId) {
		return sinaWeiboTagDao.list(userId);
	}

	@Override
	public List<TagInfo> listSinaWeiboTag(String userId) throws UserNotAuthorizeException, WeiboException, KankantuException {
		// 获取用户的token
		Token token = null;
		token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			throw new UserNotAuthorizeException("用户没有授权，或者授权过期");
		}
		
		// 设置访问接口
		Tags t = new Tags();
		t.setToken(token.getToken());
		
		// 获取所有tag
		List<Tag> list = t.getTags(token.getUid());
		if (null == list) {
			return null;
		}
		
		// 拼装成TagInfo
		List<TagInfo> listTagInfo = new ArrayList<TagInfo>();
		for (Tag tag : list) {
			TagInfo tagInfo = new TagInfo();
			tagInfo.setTagName(tag.getValue());
			listTagInfo.add(tagInfo);
		}
		
		return listTagInfo;
	}
}
