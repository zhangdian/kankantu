package com.bd17kaka.kankantu.dao;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.User;

/**
 * 队列信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboAuthorizeDao")
public class SinaWeiboAuthorizeDaoImpl extends RedisUtils implements SinaWeiboAuthorizeDao {

	private static String prefix = "sinaweibo:authorize:";

	@Override
	public Long insert(SinaWeiboAuthorizeInfo info, String userId) {
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		// 获取一个authorizeid
		Long id = jedis.incr(keyPrefix + "maxid");
		if (id <= 0L) {
			return id;
		}
		// 将授权信息的各个部分保存起来
		jedis.set(keyPrefix + id + ":timestamp", info.getDate());
		jedis.set(keyPrefix + id + ":weibouserid", String.valueOf(info.getUserId()));
		jedis.set(keyPrefix + id + ":weibousername", info.getUserName());
		jedis.set(keyPrefix + id + ":token", info.getToken());
		// 将授权id保存到授权id集合中
		jedis.sadd(keyPrefix + "authorizeids", id.toString());
		return id;
	}
}
