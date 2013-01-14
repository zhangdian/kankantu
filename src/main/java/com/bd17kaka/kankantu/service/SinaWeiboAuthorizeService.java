package com.bd17kaka.kankantu.service;

import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

public interface SinaWeiboAuthorizeService {
	
	/**
	 *  获取访问api的token
	 * @param code
	 * @param userid
	 * @throws WeiboException
	 */
	void storeToken(String code, String userid) throws WeiboException;
	
	/**
	 * 根据用户id获取token
	 * @param userId
	 * @return
	 */
	Token getTokenByUserId(String userId);
	
	/**
	 * 获取指定用户的所有授权验证信息列表
	 * @param userId
	 * @return
	 */
	List<SinaWeiboAuthorizeInfo> list(String userId);
}
