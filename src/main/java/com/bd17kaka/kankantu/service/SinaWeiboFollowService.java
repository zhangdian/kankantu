package com.bd17kaka.kankantu.service;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboFollowService {
	
	/**
	 * 保存统推荐的，指定tag的用户
	 * @param userId
	 * @param user
	 * @param tagName
	 * @throws WeiboException 
	 */
	void addFollow(String userId, String uid, String tagName) throws WeiboException;
}
