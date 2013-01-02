package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.po.User;

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
		key = prefix + userId + ":expire";
		jedis.set(key, String.valueOf(3600 * 6));
		jedis.expire(key, 3600 * 6);
	}

	@Override
	public Token get(String userId) {
		ShardedJedis jedis =  getConnection();
		Token token = new Token();
		token.setUserId(userId);
		String key = prefix + userId + ":token";
		token.setToken(jedis.get(key));
		key = prefix + userId + ":expire";
		token.setExpire(jedis.ttl(key).toString());
		return token;
	}
}
