package com.bd17kaka.kankantu.dao;

import java.util.Set;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.johm.JOhm;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.weibo4j.http.BASE64Encoder;

/**
 * 关注者Dao实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboFollowDao")
public class SinaWeiboFollowDaoImpl extends RedisUtils implements SinaWeiboFollowDao {

	private String followPrefix = "sinaweibo:follow:";
	private String tagPrefix = "sinaweibo:tag:";
	
	@Override
	public void insert(String userId, SinaWeiboRecommendUser user, String tagName) {
		ShardedJedis jedis =  getConnection(); 
		String key = null;
		// 保存数据到 sinaweibo:follow:userid:follows
		key = followPrefix + userId + ":follows";
		jedis.sadd(key, user.getUserId());
		
		// 保存数据到 sinaweibo:follow:userid:followid:tags
		key = followPrefix + userId + ":" + user.getUserId() + ":tags";
		jedis.sadd(key, tagName);
		
		// 保存数据到 sinaweibo:tag:userid:tagname:follows
		key = tagPrefix + userId + ":" + BASE64Encoder.encode(tagName.getBytes()) + ":follows";
		jedis.sadd(key, user.getUserId());
		
		returnConnection(jedis);
	}

	@Override
	public boolean exist(String userId, String uid) {
		ShardedJedis jedis =  getConnection(); 
		String key = followPrefix + userId + ":follows";
		boolean b = jedis.sismember(key, uid);
		returnConnection(jedis);
		return b;
	}

	@Override
	public void delete(String userId, SinaWeiboRecommendUser user,
			String tagName) {
		ShardedJedis jedis =  getConnection(); 
		String key = null;
		// 删除数据 sinaweibo:tag:userid:tagname:follows
		key = tagPrefix + userId + ":" + tagName + ":follows";
		jedis.srem(key, user.getUserId());

		// 删除数据 sinaweibo:follow:userid:followid:tags
		key = followPrefix + userId + ":" + user.getUserId() + ":tags";
		jedis.srem(key, tagName);
		
		// 如果用户关注的这个用户的tags数为0
		// 那么删除这个关注的用户，sinaweibo:follow:userid:follows
		if (0L == jedis.scard(key)) {
			key = followPrefix + userId + ":follows";
			jedis.srem(key, user.getUserId());
		}
		
		returnConnection(jedis);		
	}

	@Override
	public Set<String> list(String userId, String tagName) {
		ShardedJedis jedis =  getConnection(); 
		
		String key = tagPrefix + userId + ":" + BASE64Encoder.encode(tagName.getBytes()) + ":follows";
		System.out.println(key);
		Set<String> set = jedis.smembers(key);
		System.out.println(set.size());
		
		returnConnection(jedis);
		return set;
	}
	public static void main(String[] args) {
		String s = "哈哈";
		System.out.println(BASE64Encoder.encode(s.getBytes()));
		System.out.println(BASE64Encoder.encode(s.getBytes()));
		System.out.println(BASE64Encoder.encode(s.getBytes()));
	}
}
