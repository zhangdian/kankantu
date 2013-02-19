package com.bd17kaka.kankantu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboAuthorizeDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.utils.TimeUtils;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.Users;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.User;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;

@Service(value = "sinaWeiboAuthorizeService")
public class SinaWeiboAuthorizeServiceImpl implements SinaWeiboAuthorizeService {

	@Resource(name = "sinaWeiboAuthorizeDao")
	private SinaWeiboAuthorizeDao sinaWeiboAuthorizeDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;

	@Override
	public void storeToken(String code, String userId) 
			throws WeiboException, KankantuException {
		Oauth oauth = new Oauth();
		// weibo userid和token
		AccessToken token = oauth.getAccessTokenByCode(code);
		if (null == token) {
			return;
		}
		
		// 获取用户微博的信息
		String uid = token.getUid();
		Users users = new Users();
		users.setToken(token.getAccessToken());
		User user = users.showUserById(uid);
		// 保存授权信息
		SinaWeiboAuthorizeInfo info = new SinaWeiboAuthorizeInfo(uid, user.getScreenName(), token.getAccessToken(), TimeUtils.getCurTime());
		if (sinaWeiboAuthorizeDao.insert(info, userId) <= 0) {
			throw new KankantuException("保存授权信息失败");
		}
		// 保存token到最新的token
		Token t = new Token(userId, uid, user.getScreenName(), token.getAccessToken(), "0");
		try {
			sinaWeiboTokenDao.insert(t);
		} catch (KankantuException e) {
			throw new KankantuException("保存token信息失败");
		}
	}

	@Override
	public Token getTokenByUserId(String userId) throws KankantuException  {
		try {
			return sinaWeiboTokenDao.get(userId);
		} catch (KankantuException e) {
			throw new KankantuException("获取用户token信息失败");
		}
	}

	@Override
	public List<SinaWeiboAuthorizeInfo> list(String userId) {
		return sinaWeiboAuthorizeDao.list(userId);
	}
}
