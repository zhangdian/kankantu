package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.service.WeiboService;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.http.AccessToken;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.util.BareBonesBrowserLaunch;

/**
 * @author bd17kaka
 */
@Controller(value = "weiboOauthContoller")
public class WeiboOauthContoller extends BaseController {

	private static String key = "3790992882";
	private static String password = "3790992882";

	@Resource(name="weiboService")
	private WeiboService weiboService;
	
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
	public String openOathPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code", key, password));
		request.getSession().setAttribute("oauth", oauth);
		return "main";
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
	public String getToken(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String code = StringUtils.trimToEmpty(request.getParameter("code"));
		if (StringUtils.isEmpty(code)) {
			return "main";
		}
		Oauth oauth = (Oauth) request.getSession().getAttribute("oauth");
		if (null == oauth) {
			return "main";
		}
		AccessToken accessToken = weiboService.getToken(oauth, code);
		if (null == accessToken) {
			return "main";
		}
		String token = accessToken.getAccessToken();
		request.getSession().setAttribute("token", token);
		return "main";
	}
}
