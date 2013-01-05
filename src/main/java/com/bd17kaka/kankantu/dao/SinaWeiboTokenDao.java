package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.po.Token;


/**
 * 新浪微博token
 * @author bd17kaka
 */
public interface SinaWeiboTokenDao {
	void insert(String token, String userId);
	
	Token get(String userId);
}