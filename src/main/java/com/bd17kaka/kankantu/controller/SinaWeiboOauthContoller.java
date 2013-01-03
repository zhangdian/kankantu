package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.service.SinaWeiboAuthorizeService;
import com.bd17kaka.kankantu.service.WeiboService;
import com.bd17kaka.kankantu.utils.RedisUtils;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.util.BareBonesBrowserLaunch;

/**
 * @author bd17kaka
 */
@Controller(value = "weiboOauthContoller")
public class SinaWeiboOauthContoller extends BaseController {

	private static String key = "3790992882";
	private static String password = "fa1b2986b8fcb881802c7ac811ef2d33";

/*	@Resource(name="weiboService")
	private WeiboService weiboService;*/
	
	@Resource(name="sinaWeiboAuthorizeService")
	private SinaWeiboAuthorizeService sinaWeiboAuthorizeService;
	
	/**
	 * 打开验证页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException 
	 */
	@RequestMapping("/openOathPage.do")
	public void openOathPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		// 不要使用weibo sdk自带的跳转函数，就直接跳转到授权页面
		Oauth oauth = new Oauth();
		response.sendRedirect(oauth.authorize("code", key, password));
	}
	
	/**
	 * 获取token
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("/getToken.do")
	public void getToken(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String code = StringUtils.trimToEmpty(request.getParameter("code"));
		if (StringUtils.isEmpty(code)) {
			response.sendRedirect("sinaWeiboAuthorizeStatus.do");
			return; 
		}
		try {
			sinaWeiboAuthorizeService.storeToken(code, request.getSession().getAttribute("kankantu_userid").toString());
			response.sendRedirect("sinaWeiboAuthorizeStatus.do");
		} catch (StoreTokenException e) {
			e.toString();
			response.sendRedirect("sinaWeiboAuthorizeStatus.do");
		}
		return;
	}
	
	/**
	 * 获取当前用户的验证状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("/sinaWeiboAuthorizeStatus.do")
	public String sinaWeiboAuthorizeStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		Token token = sinaWeiboAuthorizeService.getTokenByUserId(userId);
		if (null != token) {
			request.setAttribute("token", token);
		}
		return "sinaweibo_authorize_status";
	}
}
