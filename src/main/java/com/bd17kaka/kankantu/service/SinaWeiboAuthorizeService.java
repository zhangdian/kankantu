package com.bd17kaka.kankantu.service;

import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboAuthorizeService {
	
	/**
	 * 获取访问api的token
	 * @param oauth
	 * @param code
	 * @throws WeiboException
	 */
	void storeToken(String code, String userid) throws WeiboException;
	
	/**
	 * 根据用户id获取token
	 * @param userId
	 * @return
	 */
	Token getTokenByUserId(String userId);
}
