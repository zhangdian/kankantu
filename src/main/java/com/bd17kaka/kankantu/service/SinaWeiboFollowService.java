package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboFollowService {
	
	/**
	 * 保存统推荐的，指定tag的用户
	 * @param userId
	 * @param user
	 * @param tagName
	 * @throws WeiboException 
	 * @throws UserNotAuthorizeException 
	 * @throws KankantuException 
	 */
	void addFollow(String userId, String uid, String tagName) throws WeiboException, KankantuException, UserNotAuthorizeException;
	
	/**
	 * 从tagName分组中，删除关注的uid用户
	 * @param userId
	 * @param uid
	 * @param tagName
	 * @throws WeiboException
	 * @throws UserNotAuthorizeException 
	 * @throws KankantuException 
	 */
	void deleteFollow(String userId, String uid, String tagName) throws WeiboException, KankantuException, UserNotAuthorizeException;
	
	/**
	 * 列出某人在某分组下的关注的人列表
	 * @param userId
	 * @param tagName
	 * @return
	 * @throws UserNotAuthorizeException 
	 * @throws KankantuException 
	 */
	List<SinaWeiboRecommendUser> listFollowByTag(String userId, String tagName) throws KankantuException, UserNotAuthorizeException;
	
	/**
	 * 同步用户在sina微博里关注的用户到kankantu
	 * @param userId
	 * @throws KankantuException 
	 * @throws UserNotAuthorizeException 
	 * @throws WeiboException 
	 */
	void syncSinaWeiboFollows(String userId) throws KankantuException, UserNotAuthorizeException, WeiboException;
}
