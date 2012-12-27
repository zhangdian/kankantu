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

import com.bd17kaka.kankantu.service.WeiboService;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * @author bd17kaka
 */
@Controller(value = "weiboContoller")
public class WeiboContoller extends BaseController {

	@Resource(name="weiboService")
	private WeiboService weiboService;
	
	/**
	 * 转发微博
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("/repostWeibo.do")
	public String repostWeibo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		int num = 5;
		String token = request.getSession().getAttribute("token").toString();
		String userId = StringUtils.trimToEmpty(request.getParameter("user_id"));
		System.out.println("aaaaaaaaaaaaaaaaaaa");
		System.out.println(userId);
		if (StringUtils.isEmpty(userId) || !StringUtils.isNumeric(userId)) {
			return "main";
		}
		System.out.println("bbbbbbbbbbbbbbbbb");
		weiboService.repostWeibo(userId, num, token);
		request.setAttribute("status", "转发微博成功");
		return "main";
	}
}
