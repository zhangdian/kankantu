package com.bd17kaka.kankantu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.StoreTokenException;
import com.bd17kaka.kankantu.po.SinaWeiboAuthorizeInfo;
import com.bd17kaka.kankantu.po.Token;
import com.bd17kaka.kankantu.service.SinaWeiboAuthorizeService;
import com.bd17kaka.kankantu.weibo4j.Oauth;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * @author bd17kaka
 */
@Controller(value = "weiboOauthContoller")
public class SinaWeiboOauthContoller extends BaseController {

	private static String key = "3790992882";
	private static String password = "fa1b2986b8fcb881802c7ac811ef2d33";

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
	public void openOathPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// 不要使用weibo sdk自带的跳转函数，就直接跳转到授权页面
		Oauth oauth = new Oauth();
		try {
			response.sendRedirect(oauth.authorize("code", key, password));
		} catch (WeiboException e) {
			writeHtml(request, response, "连接Sina微博好像出了点问题~~~");
		}
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
	public void getToken(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String code = StringUtils.trimToEmpty(request.getParameter("code"));
		if (StringUtils.isEmpty(code)) {
			response.sendRedirect("sinaWeiboAuthorizeStatus.do");
			return; 
		}
		try {
			// 保存token
			sinaWeiboAuthorizeService.storeToken(code, request.getSession().getAttribute("kankantu_userid").toString());
			
			// 同步tag
			
			
			// 同步关注者的分组
			
			
			response.sendRedirect("sinaWeiboAuthorizeStatus.do");
		} catch (WeiboException e) {
			writeHtml(request, response, "连接Sina微博好像出了点问题~~~");
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
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
	public String sinaWeiboAuthorizeStatus(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		Token token = null;;
		try {
			token = sinaWeiboAuthorizeService.getTokenByUserId(userId);
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		}
		if (null != token) {
			request.setAttribute("token", token);
			int hour = Integer.parseInt(token.getExpire()) / 3600;
			request.setAttribute("hour", hour);
			int minute = Integer.parseInt(token.getExpire()) % 3600 / 60;
			request.setAttribute("minute", minute);
			int second = Integer.parseInt(token.getExpire()) % 3600 % 60;
			request.setAttribute("second", second);
		}
		return "sinaweibo_authorize_status";
	}
	
	/**
	 * 获取当前用户的验证状态
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("/listSinaWeiboAuthorize.do")
	public String listSinaWeiboAuthorize(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Object userId = request.getSession().getAttribute("kankantu_userid");
		if (null == userId) {
			return "index";
		}
		List<SinaWeiboAuthorizeInfo> list = sinaWeiboAuthorizeService.list(userId.toString());
		System.out.println(list.size());
		request.setAttribute("list", list);
		return "sinaweibo_authorize_history";
	}
}
