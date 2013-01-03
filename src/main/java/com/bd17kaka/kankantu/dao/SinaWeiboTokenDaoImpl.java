package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.Token;

/**
 * 队列信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboTokenDao")
public class SinaWeiboTokenDaoImpl extends RedisUtils implements SinaWeiboTokenDao {

	private static String prefix = "sinaweibo:token:";

	@Override
	public void insert(String token, String userId) {
		ShardedJedis jedis =  getConnection(); 
		String key = prefix + userId + ":token";
		jedis.set(key, token);
		jedis.expire(key, 3600 * 6);
	}

	@Override
	public Token get(String userId) {
		// 因为token的声明周期是逐渐变短的
		// 所以不管是先获取键值还是先获取键值剩下的时间，都得判断
		ShardedJedis jedis =  getConnection();
		Token t = new Token();
		t.setUserId(userId);
		// 获取键值
		String key = prefix + userId + ":token";
		String token = jedis.get(key);
		if (null == token) {
			return null;
		}
		t.setToken(token);
		// 获取生命周期
		Long expire = jedis.ttl(key);
		if (0L > expire) {
			return null;
		}
		t.setExpire(expire.toString());
		return t;
	}
}
