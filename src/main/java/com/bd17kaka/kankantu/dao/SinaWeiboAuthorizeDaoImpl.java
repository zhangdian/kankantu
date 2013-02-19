package com.bd17kaka.kankantu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

/**
 * 用户授权信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboAuthorizeDao")
public class SinaWeiboAuthorizeDaoImpl extends RedisUtils implements SinaWeiboAuthorizeDao {

	private static String prefix = "sinaweibo:authorize:";

	@Override
	public Long insert(SinaWeiboAuthorizeInfo info, String userId) throws KankantuException {
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		// 获取一个authorizeid
		Long id = jedis.incr(keyPrefix + "maxid");
		if (id <= 0L) {
			returnConnection(jedis);
			return id;
		}
		
		// 将授权信息对象拼装成JSONObject对象
		JSONObject jo = new JSONObject();
		try {
			jo.put("user_name", info.getUserName());
			jo.put("user_id", info.getUserId());
			jo.put("token", info.getToken());
			jo.put("date", info.getDate());
		} catch (JSONException e) {
			throw new KankantuException("将SinaWeiboAuthorizeInfo对象转换为JSONObject对象的时候失败");
		}
		
		String key = null;
		
		// 保存JSONObject格式的字符串到Redis
		key = keyPrefix + "id";
		jedis.set(key, jo.toString());
		
		// 将授权id保存到授权id列表中
		key = keyPrefix + "authorizeids";
		jedis.lpush(key, id.toString());
		
		returnConnection(jedis);
		return id;
	}

	@Override
	public List<SinaWeiboAuthorizeInfo> list(String userId) {
		// sinaweibo:authorize:userid:authorizeids
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		String key = null;
		
		// 获取所有授权记录的id列表
		key = keyPrefix + "authorizeids";
		List<String> ids = jedis.lrange(key, 0, -1);
		if (null == ids) {
			returnConnection(jedis);
			return null;
		}
		
		// 拼装所有记录
		List<SinaWeiboAuthorizeInfo> list = new ArrayList<SinaWeiboAuthorizeInfo>();
		for (String id : ids) {
			// 根据id获取授权信息
			key = prefix + userId + ":" + id;
			String value = jedis.get(key);
			if (null == value) {
				continue;
			}
			
			// 将JSON格式的字符串转换为JSON对象
			JSONObject jo = new JSONObject();
			jo = (JSONObject) JSONObject.stringToValue(value);
			if (null == jo) {
				return null;
			}
			
			// 将JSON对象转换为SinaWeiboAuthorizeInfo对象
			SinaWeiboAuthorizeInfo info = new SinaWeiboAuthorizeInfo();
			try {
				info.setDate(jo.getString("date"));
				info.setToken(jo.getString("token"));
				info.setUserId(jo.getString("user_id"));
				info.setUserName(jo.getString("user_name"));
			} catch (JSONException e) {
				// 某一个出现问题
				// 理论上不会到这个地方
			}
			
			list.add(info);
		}
		returnConnection(jedis);
		return list;
	}
}
