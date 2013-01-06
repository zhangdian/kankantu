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
	public void insert(Token t, String userId) {
		// 只需要设置token的生命周期即可，到时获取的时候，只需判断token的生命周期
		ShardedJedis jedis =  getConnection(); 
		String key = prefix + userId + ":token";
		jedis.set(key, t.getToken());
		jedis.expire(key, 3600 * 6);
		key = prefix + userId + ":weibousername";
		jedis.set(key, t.getUserName());
		key = prefix + userId + ":weibouserid";
		jedis.set(key, t.getUserId());
		returnConnection(jedis);
	}

	@Override
	public Token get(String userId) {
		// 因为token的声明周期是逐渐变短的
		// 所以不管是先获取键值还是先获取键值剩下的时间，都得判断
		ShardedJedis jedis =  getConnection();
		Token t = new Token();
		String key;
		// 获取weibousername和weibouserid
		key = prefix + userId + ":weibousername";
		String userName = jedis.get(key);
		t.setUserName(userName);
		key = prefix + userId + ":weibouserid";
		String uid = jedis.get(key);
		t.setUserId(uid);
		
		// 获取键值
		key = prefix + userId + ":token";
		String token = jedis.get(key);
		if (null == token) {
			returnConnection(jedis);
			return null;
		}
		t.setToken(token);
		// 获取生命周期
		Long expire = jedis.ttl(key);
		if (0L > expire) {
			returnConnection(jedis);
			return null;
		}
		t.setExpire(expire.toString());
		returnConnection(jedis);
		return t;
	}
}
