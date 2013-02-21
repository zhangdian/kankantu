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
	///////////////////////////////////////////////////////////////////////////
	// redis中tag信息操作
	/**
	 * 将某用户在sina微博中的tag列表插入到我们的数据库
	 * @param listTagInfo
	 * @param userId
	 */
	void insert(List<TagInfo> listTagInfo, String userId);
	
	/**
	 * 列出用户所有的tag
	 * @param userId
	 * @return
	 */
	List<TagInfo> list(String userId);
	
	/**
	 * 根据指定的tag名称获取TagInfo对象
	 * @param tagName
	 * @return
	 */
	TagInfo get(String tagName);
}
