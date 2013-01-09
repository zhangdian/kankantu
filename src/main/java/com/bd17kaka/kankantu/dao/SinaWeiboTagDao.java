package com.bd17kaka.kankantu.dao;

import java.util.List;

import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * Tag信息Dao
 * @author bd17kaka
 */
public interface SinaWeiboTagDao {
	
	///////////////////////////////////////////////////////////////////////////
	// 获取用户在sina微博中的tag信息
	/**
	 * 获取所有tag信息
	 * @param token
	 * @return
	 * @throws WeiboException 
	 */
	List<TagInfo> listSinaWeiboTag(Token token) throws WeiboException;
	
	///////////////////////////////////////////////////////////////////////////
	// redis中tag信息操作
	void insert(List<TagInfo> listTagInfo, String userId);
	
	// 获取用户当前的所有tag信息
	List<TagInfo> list(String userId);
}
