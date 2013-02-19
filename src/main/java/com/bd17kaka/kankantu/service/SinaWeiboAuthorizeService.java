package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboAuthorizeService {
	
	/**
	 *  获取访问api的token
	 * @param code
	 * @throws WeiboException
	 */
	void storeToken(String code, String userId) 
			throws WeiboException, StoreTokenException, KankantuException;
	
	/**
	 * 根据用户id获取token
	 * @param userId
	 * @return
	 */
	Token getTokenByUserId(String userId)
			throws KankantuException;
	
	/**
	 * 获取指定用户的所有授权验证信息列表
	 * @param userId
	 * @return
	 */
	List<SinaWeiboAuthorizeInfo> list(String userId);
}
