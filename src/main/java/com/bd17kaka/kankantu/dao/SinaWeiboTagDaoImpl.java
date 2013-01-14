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

/**
 * 用户taginfo信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboTagDao")
public class SinaWeiboTagDaoImpl extends RedisUtils implements SinaWeiboTagDao {

	private static String prefix = "sinaweibo:tag:";

	@Override
	public List<TagInfo> listSinaWeiboTag(Token token) throws WeiboException {
		// 设置访问接口
		Tags t = new Tags();
		t.setToken(token.getToken());
		
		// 获取所有tag
		List<Tag> list = t.getTags(token.getUid());
		if (null == list) {
			return null;
		}
		
		// 拼装成TagInfo
		List<TagInfo> listTagInfo = new ArrayList<TagInfo>();
		for (Tag tag : list) {
			TagInfo tagInfo = new TagInfo();
			tagInfo.setTagName(tag.getValue());
			listTagInfo.add(tagInfo);
		}
		
		return listTagInfo;
	}

	@Override
	public void insert(List<TagInfo> listTagInfo, String userId) {
		ShardedJedis jedis =  getConnection(); 
		
		if (null == listTagInfo) {
			return ;
		}
		
		String key = prefix + userId + ":tags";
		for (TagInfo tagInfo : listTagInfo) {
			jedis.sadd(key, tagInfo.getTagName());
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
			TagInfo tagInfo = new TagInfo();
			tagInfo.setTagName(s);
			list.add(tagInfo);
		}
		returnConnection(jedis);
		return list;
	}

	@Override
	public TagInfo get(String tagName) {
		// 暂时只有一个tagName字段
		return new TagInfo(tagName);
	}
}
