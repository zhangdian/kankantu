package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;


/**
 * 新浪微博token
 * @author bd17kaka
 */
public interface SinaWeiboTokenDao {
	
	void insert(Token t) throws JSONException;
	
	Token get(String userId);
}
