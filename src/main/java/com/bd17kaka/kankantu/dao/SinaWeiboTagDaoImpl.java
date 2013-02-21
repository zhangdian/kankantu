package com.bd17kaka.kankantu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Tags;
import com.bd17kaka.kankantu.weibo4j.model.Tag;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

/**
 * 用户taginfo信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboTagDao")
public class SinaWeiboTagDaoImpl extends RedisUtils implements SinaWeiboTagDao {

	private static String prefix = "sinaweibo:tag:";

	@Override
	public void insert(List<TagInfo> listTagInfo, String userId) {
		ShardedJedis jedis =  getConnection(); 
		
		if (null == listTagInfo) {
			return;
		}
		
		String key = prefix + userId + ":tags";
		
		for (TagInfo tagInfo : listTagInfo) {
			// 将TagInfo对象转换为JSONObject对象
			JSONObject jo = new JSONObject();
			try {
				jo.put("tag_name", tagInfo.getTagName());
			} catch (JSONException e) {
				// 丢一个就丢一个，有啥关系
				continue;
			}
			
			// 将JSONObject格式的TagInfo对象保存到Redis
			jedis.sadd(key, jo.toString());
		}
		returnConnection(jedis);
	}

	@Override
	public List<TagInfo> list(String userId) {
		ShardedJedis jedis =  getConnection(); 
		// 获取所有tag
		String key = prefix + userId + ":tags";
		Set<String> set = jedis.smembers(key);
		if (null == set) {
			return null;
		}
		
		// 拼装成TagInfo对象
		List<TagInfo> list = new ArrayList<TagInfo>();
		for (String s : set) {
			// 将字符串格式的TagInfo对象转换成JSON格式
			JSONObject jo = new JSONObject();
			jo = (JSONObject) JSONObject.stringToValue(s);
			
			// 拼装成TagInfo对象
			TagInfo tagInfo = new TagInfo();
			try {
				tagInfo.setTagName(jo.getString("tag_name"));
			} catch (JSONException e) {
				// 丢一个就丢一个，有啥关系
				continue;
			}
			list.add(tagInfo);
		}
		returnConnection(jedis);
		return list;
	}

	@Override
	public TagInfo get(String tagName) {
		// 暂时只有一个tagName字段
		// 不需要去查找咯
		return new TagInfo(tagName);
	}
}
