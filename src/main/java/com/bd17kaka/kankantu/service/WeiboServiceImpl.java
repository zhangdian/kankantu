package com.bd17kaka.kankantu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.bd17kaka.kankantu.log.Log;
import com.bd17kaka.kankantu.utils.RedisUtils;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.Timeline;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.Paging;
import com.bd17kaka.kankantu.weibo4j.model.Status;
import com.bd17kaka.kankantu.weibo4j.model.StatusWapper;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value="weiboService")
public class WeiboServiceImpl implements WeiboService {

	@Override
	public AccessToken getToken(String code) throws WeiboException {
		return new Oauth().getAccessTokenByCode(code);
	}

	@Override
	public List<String> listByUserId(String userId, int num, String token) throws WeiboException {
		Timeline timeLine = new Timeline();
		timeLine.setToken(token);
		
		// 设置返回的页数和每一页的数量
		Paging page = new Paging();
		page.setPage(1);
		page.setCount(num);
		
		// 获取微博
		StatusWapper wapper = timeLine.getUserTimelineByUid(userId, page, 0, 0);
		if (null == wapper || 0 == wapper.getTotalNumber()) {
			return null;
		}
		ArrayList<String> ids = new ArrayList<String>();
		for (Status status : wapper.getStatuses()) {
			ids.add(status.getId());
			System.out.println(status.getId());
		}
		return ids;
	}

	@Override
	public void repostWeibo(String id) {
		// 不将微博直接转发出去，而是存储到redis中
		String key = "weibo:default:default";
		RedisUtils.getRedisConn().rpush(key, id);
	}

	@Override
	public void repostWeibo(String userId, int num, String token) throws WeiboException {
		List<String> list = listByUserId(userId, num, token);
		for (String id : list) {
			repostWeibo(id);
		}
	}
}

