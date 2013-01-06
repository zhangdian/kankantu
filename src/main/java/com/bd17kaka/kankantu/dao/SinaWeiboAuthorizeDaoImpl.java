package com.bd17kaka.kankantu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;

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
			returnConnection(jedis);
			return id;
		}
		// 将授权信息的各个部分保存起来
		jedis.set(keyPrefix + id + ":timestamp", info.getDate());
		jedis.set(keyPrefix + id + ":weibouserid", String.valueOf(info.getUserId()));
		jedis.set(keyPrefix + id + ":weibousername", info.getUserName());
		jedis.set(keyPrefix + id + ":token", info.getToken());
		// 将授权id保存到授权id列表中
		jedis.lpush(keyPrefix + "authorizeids", id.toString());
		returnConnection(jedis);
		return id;
	}

	@Override
	public List<SinaWeiboAuthorizeInfo> list(String userId) {
		// sinaweibo:authorize:userid:authorizeids
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		// 获取所有授权记录的id列表
		List<String> ids = jedis.lrange(keyPrefix + "authorizeids", 0, -1);
		if (null == ids) {
			returnConnection(jedis);
			return null;
		}
		// 瓶装所有记录
		List<SinaWeiboAuthorizeInfo> list = new ArrayList<SinaWeiboAuthorizeInfo>();
		for (String id : ids) {
			keyPrefix = prefix + userId + ":" + id + ":";
			SinaWeiboAuthorizeInfo info = new SinaWeiboAuthorizeInfo();
			info.setDate(jedis.get(keyPrefix + "timestamp"));
			info.setToken(jedis.get(keyPrefix + "token"));
			info.setUserId(Integer.parseInt(jedis.get(keyPrefix + "weibouserid")));
			info.setUserName(jedis.get(keyPrefix + "weibousername"));
			list.add(info);
		}
		returnConnection(jedis);
		return list;
	}
}
