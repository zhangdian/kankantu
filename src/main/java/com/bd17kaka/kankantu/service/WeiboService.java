package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface WeiboService {
	
	/**
	 * 获取访问api的token
	 * @param oauth
	 * @param code
	 * @return
	 * @throws WeiboException
	 */
	AccessToken getToken(Oauth oauth, String code) throws WeiboException;
	
	/**
	 * 获取某用户的最近<code>num</code>条微博的ID记录
	 * @param userId
	 * @param num
	 * @return
	 * @throws WeiboException 
	 */
	List<String> listByUserId(String userId, int num, String token) throws WeiboException;
	
	/**
	 * 转发指定id的微博
	 * @param id
	 * @throws WeiboException 
	 */
	void repostWeibo(String id, String token) throws WeiboException;
	
	/**
	 * 转发用户<code>userId</code>指定数量<code>num</code>的微博
	 * @param userId
	 * @param num
	 * @param token
	 * @throws WeiboException 
	 */
	void repostWeibo(String userId, int num, String token) throws WeiboException;
}
