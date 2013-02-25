package com.bd17kaka.kankantu.dao;

import org.codehaus.jackson.JsonParser;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.constant.ConstatVar;
import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

/**
 * 用户认证的Token信息
 * @author bd17kaka
 */
@Repository(value="sinaWeiboTokenDao")
public class SinaWeiboTokenDaoImpl extends RedisUtils implements SinaWeiboTokenDao {

	private static String prefix = "sinaweibo:token:";

	@Override
	public void insert(Token t) throws KankantuException {
		ShardedJedis jedis =  getConnection(); 
		String userId = t.getUserId();
		
		// 拼装Token对象为JSON
		JSONObject jo = new JSONObject();
		try {
			jo.put("user_id", t.getUserId());
			jo.put("uid", t.getUid());
			jo.put("user_name", t.getUserName());
			jo.put("token", t.getToken());
			jo.put("expire", ConstatVar.TOKEN_EXPIRE);
		} catch (JSONException e) {
			throw new KankantuException("将Token对象转换为JSONObject的时候出现错误");
		}
		
		// 将token转换成JSON格式的字符串
		// 将字符串保存到redis中
		// 设置生命周期
		String key = prefix + userId + ":token";
		jedis.set(key, jo.toString());
		jedis.expire(key, ConstatVar.TOKEN_EXPIRE);
		
		// 收回到redis的连接
		returnConnection(jedis);
	}

	@Override
	public Token get(String userId) throws KankantuException {
		ShardedJedis jedis =  getConnection();
		
		// 获取token的JSON格式的字符串
		String key = prefix + userId + ":token";
		String value = jedis.get(key);
		if (null == value) {
			return null;
		}
		
		// 获取token对象的ttl
		Long expire = jedis.ttl(key);
		
		// 将JSON格式的字符串转换为JSON对象
		JSONObject jo = null;
		try {
			jo = new JSONObject(value);
		} catch (JSONException e1) {
			return null;
		}
//		jo = (JSONObject) JSONObject.stringToValue(value);
		if (null == jo) {
			return null;
		}
		
		// 将JSON对象转换为Token对象
		Token token = new Token();
		try {
			token.setExpire(jo.getString("expire"));
			token.setToken(jo.getString("token"));
			token.setUid(jo.getString("uid"));
			token.setUserId(jo.getString("user_id"));
			token.setUserName(jo.getString("user_name"));
		} catch (JSONException e) {
			throw new KankantuException("将JSONObject转换为Token对象的时候出现错误");
		}
		
		//将token的TTL设置为当前的真实值
		token.setExpire(String.valueOf(expire));
		
		returnConnection(jedis);
		return token;
	}
}
