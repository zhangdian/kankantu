package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;


/**
 * 新浪微博token
 * @author bd17kaka
 */
public interface SinaWeiboTokenDao {
	
	void insert(Token t) throws KankantuException;
	
	Token get(String userId) throws KankantuException;
}
