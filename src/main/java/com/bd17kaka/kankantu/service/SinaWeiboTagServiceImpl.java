package com.bd17kaka.kankantu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboTagDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboTagService")
public class SinaWeiboTagServiceImpl implements SinaWeiboTagService {

	@Resource(name = "sinaWeiboTagDao")
	private SinaWeiboTagDao sinaWeiboTagDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;

	@Override
	public void syncSinaWeiboTags(String userId) throws WeiboException {
		// 获取用户的token
		Token token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			// 这里可以抛出一个异常，让用户去授权
			return;
		}
		
		// 获取用户在sina微博里的tag信息
		List<TagInfo> listTagInfo = sinaWeiboTagDao.listSinaWeiboTag(token);
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
}
