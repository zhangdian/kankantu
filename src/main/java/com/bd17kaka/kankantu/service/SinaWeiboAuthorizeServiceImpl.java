package com.bd17kaka.kankantu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboAuthorizeDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.Users;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.User;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

@Service(value = "sinaWeiboAuthorizeService")
public class SinaWeiboAuthorizeServiceImpl implements SinaWeiboAuthorizeService {

	@Resource(name = "sinaWeiboAuthorizeDao")
	private SinaWeiboAuthorizeDao sinaWeiboAuthorizeDao;
	
	@Resource(name = "sinaWeiboTokenDao")
	private SinaWeiboTokenDao sinaWeiboTokenDao;

	@Override
	public void storeToken(String code, String userId) throws WeiboException {
		Oauth oauth = new Oauth();
		// weibo userid和token
		AccessToken token = oauth.getAccessTokenByCode(code);
		if (null == token) {
			return;
		}
		// 获取时间戳
		Date date = new Date();
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formater.format(date);
		// 获取用户微博的信息
		String uid = token.getUid();
		Users users = new Users();
		users.setToken(token.getAccessToken());
		User user = users.showUserById(uid);
		// 保存授权信息
		SinaWeiboAuthorizeInfo info = new SinaWeiboAuthorizeInfo(uid, user.getScreenName(), token.getAccessToken(), time);
		if (sinaWeiboAuthorizeDao.insert(info, userId) <= 0) {
			throw new StoreTokenException(time);
		}
		// 保存token到最新的token
		Token t = new Token(uid, user.getScreenName(), token.getAccessToken(), "0");
		sinaWeiboTokenDao.insert(t, userId);
	}

	@Override
	public Token getTokenByUserId(String userId) {
		return sinaWeiboTokenDao.get(userId);
	}

	@Override
	public List<SinaWeiboAuthorizeInfo> list(String userId) {
		return sinaWeiboAuthorizeDao.list(userId);
	}
}
