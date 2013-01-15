package com.bd17kaka.kankantu.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboFollowDao;
import com.bd17kaka.kankantu.dao.SinaWeiboRecommendUserDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTagDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
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

@Service(value = "sinaWeiboRecommendUserService")
public class SinaWeiboRecommendUserServiceImpl implements SinaWeiboRecommendUserService {

	@Resource(name = "sinaWeiboTagDao")
	private SinaWeiboTagDao sinaWeiboTagDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;
	
	@Resource(name = "sinaWeiboFollowDao")
	private SinaWeiboFollowDao sinaWeiboFollowDao;

	@Override
	public List<SinaWeiboRecommendUser> listRecommendUser(String userId, String tagName) throws WeiboException {
		// 获取tag对象
		TagInfo tagInfo = sinaWeiboTagDao.get(tagName);
		if (null == tagInfo) {
			return null;
		}
		
		// 获取用户的token
		Token token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			// 这里可以抛出一个异常，让用户去授权
			return null;
		}
		
		// 设置token
		Search search = new Search();
		search.setToken(token.getToken());
		
		// 执行搜索
		String word = tagInfo.getTagName();
		JSONArray result = search.searchSuggestionsUsers(word, 10);
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
				s.setFollow(sinaWeiboFollowDao.exist(token.getUserId(), o.getString("uid")));
				
				// 获取用户的头像地址
				Users users = new Users();
				users.setToken(token.getToken());
				try {
					User user = users.showUserById(s.getUserId());
					if (null == user) {
						continue;
					}
					s.setProfileImageURL(user.getAvatarLarge());
				} catch (WeiboException e) {
					continue;
				}
				// 加入到list中
				listSinaWeiboRecommendUser.add(s);
			} catch (JSONException e) {
				continue;
			}
		}
		return listSinaWeiboRecommendUser;
	}

	@Override
	public SinaWeiboRecommendUser getByUid(String userId, String uid) throws WeiboException {
		// 获取用户的token
		Token token = sinaWeiboTokenDao.get(userId);
		if (null == token) {
			// 这里可以抛出一个异常，让用户去授权
			return null;
		}
		
		// 设置token
		Users users = new Users();
		users.setToken(token.getToken());
		
		User user = users.showUserById(uid);
		if (null == user) {
			return null;
		}
		return new SinaWeiboRecommendUser(
				uid, 
				user.getScreenName(), 
				String.valueOf(user.getFollowersCount()), 
				user.getAvatarLarge(), 
				sinaWeiboFollowDao.exist(token.getUserId(), uid)
				);
	}
}
