package com.bd17kaka.kankantu.service;

import java.util.LinkedList;
import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONArray;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

public interface SinaWeiboRecommendUserService {
	
	/**
	 * 根据sina微博返回的推荐用户信息，拼装成我们的推荐用户信息
	 * @param userId
	 * @param tagName
	 * @return
	 * @throws WeiboException 
	 */
	List<SinaWeiboRecommendUser> listRecommendUser(String userId, List<JSONObject> result) throws WeiboException;
	
	/**
	 * 根据sina微博的uid，获取该用户信息
	 * @param userId
	 * @param uid
	 * @return
	 * @throws WeiboException
	 */
	SinaWeiboRecommendUser getByUid(String userId, String uid) throws WeiboException;
	
	/**
	 * 根据指定的tagName，获取所有推荐用户列表，默认返回1000个
	 * @param userId
	 * @param tagName
	 * @return linked list
	 */
	LinkedList<JSONObject> getAllRecommendUser(String userId, String tagName);
}
