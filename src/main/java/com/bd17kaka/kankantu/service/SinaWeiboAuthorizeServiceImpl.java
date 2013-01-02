package com.bd17kaka.kankantu.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bd17kaka.kankantu.dao.SinaWeiboAuthorizeDao;
import com.bd17kaka.kankantu.dao.SinaWeiboTokenDao;
import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
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
		// 保存授权信息
		SinaWeiboAuthorizeInfo info = new SinaWeiboAuthorizeInfo(Integer.parseInt(userId), "", token.getAccessToken(), time);
		if (sinaWeiboAuthorizeDao.insert(info, userId) <= 0) {
			throw new StoreTokenException(time);
		}
		// 保存token到最新的token
		sinaWeiboTokenDao.insert(token.getAccessToken(), userId);
	}

	@Override
	public Token getTokenByUserId(String userId) {
		return sinaWeiboTokenDao.get(userId);
	}
}
