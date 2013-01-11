package com.bd17kaka.kankantu.dao;

import java.util.List;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * 推荐用户信息Dao
 * @author bd17kaka
 */
public interface SinaWeiboRecommendUserDao {
	
	///////////////////////////////////////////////////////////////////////////
	// 获取用户在sina微博中的推荐用户信息 
	/**
	 * 根据指定关键词，在sina微博中获取推荐用户信息
	 * @param tagInfo
	 * @return
	 * @throws WeiboException
	 */
	List<SinaWeiboRecommendUser> listRecommendUser(Token token, TagInfo tagInfo) throws WeiboException;
	
	///////////////////////////////////////////////////////////////////////////
	// redis中推荐用户信息操作
}
