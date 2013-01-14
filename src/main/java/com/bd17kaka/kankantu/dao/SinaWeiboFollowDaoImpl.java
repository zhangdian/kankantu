package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;

/**
 * 关注者Dao实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboFollowDao")
public class SinaWeiboFollowDaoImpl extends RedisUtils implements SinaWeiboFollowDao {

	private String prefix = "sinaweibo:follow:";
	
	@Override
	public void insert(String userId, SinaWeiboRecommendUser user, String tagName) {
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		String key = null;
		// 保存数据到 sinaweibo:follow:userid:follows
		key = keyPrefix + "follows";
		jedis.sadd(key, user.getUserId());
		
		// 保存数据到 sinaweibo:follow:userid:followid:tags
		key = keyPrefix + user.getUserId() + ":tags";
		jedis.sadd(key, tagName);
		
		// 保存数据到 sinaweibo:tag:userid:tagname:follows
		key = "sinaweibo:tag:" + userId + ":" + tagName + ":follows";
		jedis.sadd(key, user.getUserId());
		
		returnConnection(jedis);
	}

	@Override
	public boolean exist(String userId, String uid) {
		ShardedJedis jedis =  getConnection(); 
		String key = prefix + userId + ":follows";
		boolean b = jedis.sismember(key, uid);
		returnConnection(jedis);
		return b;
	}
}
