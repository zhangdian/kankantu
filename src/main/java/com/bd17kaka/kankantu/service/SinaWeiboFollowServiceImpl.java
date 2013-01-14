package com.bd17kaka.kankantu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboFollowDao;
import com.bd17kaka.kankantu.dao.SinaWeiboRecommendUserDao;
import com.bd17kaka.kankantu.exception.SinaweiboUserNotFoundException;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboFollowService")
public class SinaWeiboFollowServiceImpl implements SinaWeiboFollowService {

	@Resource(name = "sinaWeiboFollowDao")
	private SinaWeiboFollowDao sinaWeiboFollowDao;
	
	@Resource(name = "sinaWeiboRecommendUserService")
	private SinaWeiboRecommendUserService sinaWeiboRecommendUserService;
	
	@Override
	public void addFollow(String userId, String uid, String tagName) throws WeiboException {
		
		// 获取要关注的用户的信息
		SinaWeiboRecommendUser user = sinaWeiboRecommendUserService.getByUid(userId, uid);
		if (null == user) {
			throw new SinaweiboUserNotFoundException(uid);
		}
		// 保存关注的用户
		sinaWeiboFollowDao.insert(userId, user, tagName);
	}
}
