package com.bd17kaka.kankantu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboRecommendUserDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTagDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboRecommendUserService")
public class SinaWeiboRecommendUserServiceImpl implements SinaWeiboRecommendUserService {

	@Resource(name = "sinaWeiboTagDao")
	private SinaWeiboTagDao sinaWeiboTagDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;
	
	@Resource(name = "sinaWeiboRecommendUserDao")
	private SinaWeiboRecommendUserDao sinaWeiboRecommendUserDao;

	@Override
	public List<SinaWeiboRecommendUser> listRecommendUser(String userId, String tagName) throws WeiboException {
		// 获取tag对象
		TagInfo tagInfo = sinaWeiboTagDao.get(tagName);
		if (null == tagInfo) {
			return null;
		}
		
		// 获取用户的token
		Token token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			// 这里可以抛出一个异常，让用户去授权
			return null;
		}
		
		// 获取推荐用户列表
		List<SinaWeiboRecommendUser> list = sinaWeiboRecommendUserDao.listRecommendUser(token, tagInfo);
		return list;
	}

	@Override
	public SinaWeiboRecommendUser getByUid(String userId, String uid) throws WeiboException {
		// 获取用户的token
		Token token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			// 这里可以抛出一个异常，让用户去授权
			return null;
		}
		
		// 获取用户信息
		return sinaWeiboRecommendUserDao.getByUid(token, uid);
	}
}
