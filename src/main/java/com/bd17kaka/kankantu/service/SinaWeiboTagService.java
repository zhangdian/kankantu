package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboTagService {
	
	/**
	 *  同步某用户新浪微博的tags到我们的系统
	 * @param userId
	 * @throws WeiboException 
	 * @throws KankantuException 
	 * @throws UserNotAuthorizeException 
	 */
	void syncSinaWeiboTags(String userId) throws WeiboException, UserNotAuthorizeException, KankantuException;
	
	/**
	 * 获取某用户的所有tag信息
	 * @param userId
	 * @return
	 */
	List<TagInfo> list(String userId);
	
	/**
	 * 获取某用户在新浪微博中的tag
	 * @param userId
	 * @return
	 * @throws UserNotAuthorizeException 
	 * @throws WeiboException 
	 * @throws KankantuException 
	 */
	List<TagInfo> listSinaWeiboTag(String userId) throws UserNotAuthorizeException, WeiboException, KankantuException;
}
