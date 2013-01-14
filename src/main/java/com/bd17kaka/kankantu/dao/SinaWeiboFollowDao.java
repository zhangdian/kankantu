package com.bd17kaka.kankantu.dao;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;

/**
 * 关注者Dao
 * @author bd17kaka
 */
public interface SinaWeiboFollowDao {

	/**
	 * 保存推荐用户到redis
	 * @param userId 我们系统中的用户ID
	 * @param user
	 */
	void insert(String userId, SinaWeiboRecommendUser user, String tagName);
	
	/**
	 * 获取某人是否关注了另外一个sina微博的用户
	 * @param userId
	 * @param uid
	 * @return
	 */
	boolean exist(String userId, String uid);
}
