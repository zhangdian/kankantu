package com.bd17kaka.kankantu.service;

import java.util.List;

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
	
	/**
	 * 从tagName分组中，删除关注的uid用户
	 * @param userId
	 * @param uid
	 * @param tagName
	 * @throws WeiboException
	 */
	void deleteFollow(String userId, String uid, String tagName) throws WeiboException;
	
	/**
	 * 列出某人在某分组下的关注的人列表
	 * @param userId
	 * @param tagName
	 * @return
	 */
	List<SinaWeiboRecommendUser> list(String userId, String tagName);
}
