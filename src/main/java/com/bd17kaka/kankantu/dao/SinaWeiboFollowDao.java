package com.bd17kaka.kankantu.dao;

import java.util.List;
import java.util.Set;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;

/**
 * 关注者Dao
 * @author bd17kaka
 */
public interface SinaWeiboFollowDao {

	/**
	 * 将新浪微博关注的用户保存到redis
	 * @param userId 我们系统中的用户ID
	 * @param followUids sinaweibo关注者的uid
	 * @param user
	 */
	void insertSinaWeiboFollow(String userId, String[] followUids);
	
	/**
	 * 保存关注的用户到redis
	 * @param userId 我们系统中的用户ID
	 * @param user
	 */
	void insert(String userId, SinaWeiboRecommendUser user, String tagName);
	
	/**
	 * 删除关注的用户
	 * @param userId
	 * @param user
	 * @param tagName
	 */
	void delete(String userId, SinaWeiboRecommendUser user, String tagName);
	
	/**
	 * 列出用户在某tag分组中关注的新浪微博用户
	 * @param userId
	 * @param tagName
	 * @return
	 */
	Set<String> list(String userId, String tagName);
	
	/**
	 * 获取某人是否关注了另外一个sina微博的用户
	 * @param userId
	 * @param uid
	 * @return
	 */
	boolean exist(String userId, String uid);
}
