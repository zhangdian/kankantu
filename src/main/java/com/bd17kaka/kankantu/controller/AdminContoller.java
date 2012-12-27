package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bd17kaka
 */
@Controller(value = "adminContoller")
public class AdminContoller extends BaseController {

/*	@Resource(name = "appService")
	private AppService appService;*/
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String userName = StringUtils.trimToEmpty(request.getParameter("user_name"));
		String password = StringUtils.trimToEmpty(request.getParameter("password"));
		System.out.println(userName);
		System.out.println(password);
		if (StringUtils.isEmpty(userName) ||
				StringUtils.isEmpty(password)) {
			return "index";
		}
		if (!"kankantu".equals(userName)
				|| !"kankantu".equals(password)) {
			return "index";
		}
		return "main";
	}
}
