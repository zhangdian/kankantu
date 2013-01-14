package com.bd17kaka.kankantu.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Search;
import com.bd17kaka.kankantu.weibo4j.Users;
import com.bd17kaka.kankantu.weibo4j.model.User;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONArray;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

/**
 * 新浪微博推荐用户信息DAO实现
 * @author bd17kaka
 */
@Repository(value="sinaWeiboRecommendUserDao")
public class SinaWeiboRecommendUserDaoImpl extends RedisUtils implements SinaWeiboRecommendUserDao {

	private String prefix = "sinaweibo:follow:";
	
	@Override
	public List<SinaWeiboRecommendUser> listRecommendUser(Token token, TagInfo tagInfo) throws WeiboException {
		// 设置token
		Search search = new Search();
		search.setToken(token.getToken());
		
		// 执行搜索
		String word = tagInfo.getTagName();
		JSONArray result = search.searchSuggestionsUsers(word, 5);
		if (null == result || result.length() == 0) {
			return null;
		}
		
		// 瓶装成TagInfo对象
		List<SinaWeiboRecommendUser> listSinaWeiboRecommendUser = new ArrayList<SinaWeiboRecommendUser>();
		for (int i = 0; i < result.length(); ++i) {
			JSONObject o = null;
			try {
				o = (JSONObject) result.get(i);
			} catch (JSONException e) {
				continue;
			}
			
			if (null == o) {
				continue;
			}
			
			SinaWeiboRecommendUser s = new SinaWeiboRecommendUser();
			try {
				// 设置用户信息
				s.setFollowCount(o.getString("followers_count"));
				s.setUserId(o.getString("uid"));
				s.setUserName(o.getString("screen_name"));
				
				// 获取用户的头像地址
				Users users = new Users();
				users.setToken(token.getToken());
				User user = users.showUserById(s.getUserId());
				if (null == user) {
					continue;
				}
				s.setProfileImageURL(user.getAvatarLarge());
				
				// 加入到list中
				listSinaWeiboRecommendUser.add(s);
			} catch (JSONException e) {
				continue;
			}
		}
		return listSinaWeiboRecommendUser;
	}

	@Override
	public void insert(String userId, SinaWeiboRecommendUser user, String tagName) {
		ShardedJedis jedis =  getConnection(); 
		String keyPrefix = prefix + userId + ":";
		String key = null;
		// 保存数据到 sinaweibo:follow:userid:follows
		key = keyPrefix + "follows";
		jedis.sadd(key, user.getUserId());
		
		// 保存数据到 sinaweibo:follow:userid:followid:tags
		key = keyPrefix + user.getUserId() + ":tags";
		jedis.sadd(key, tagName);
		
		// 保存数据到 sinaweibo:tag:userid:tagname:follows
		key = "sinaweibo:tag:" + userId + ":" + tagName + ":follows";
		jedis.sadd(key, user.getUserId());
		
		returnConnection(jedis);
	}
}
