package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.exception.SignupException;
import com.bd17kaka.kankantu.exception.UserNameExistException;
import com.bd17kaka.kankantu.po.User;
import com.bd17kaka.kankantu.service.UserService;

/**
 * @author bd17kaka
 */
@Controller(value = "adminContoller")
public class AdminContoller extends BaseController {

	@Resource(name = "userService")
	private UserService userService;
	
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
		User user = userService.verifyUser(new User(0, userName, password, ""));
		if (null == user) {
			return "index";
		}
		request.getSession().setAttribute("kankantu_user", userName);
		request.getSession().setAttribute("kankantu_userid", user.getUserId());
		return "main";
	}
	
	@RequestMapping("/signup.do")
	public String signup(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String userName = StringUtils.trimToEmpty(request.getParameter("user_name"));
		String email = StringUtils.trimToEmpty(request.getParameter("email"));
		String password = StringUtils.trimToEmpty(request.getParameter("password"));
		String repassword = StringUtils.trimToEmpty(request.getParameter("re_password"));
		if (StringUtils.isEmpty(userName) ||
				StringUtils.isEmpty(password) ||
				StringUtils.isEmpty(email) ||
				StringUtils.isEmpty(repassword)) {
			request.setAttribute("msg", "信息部完整");
			return "signup";
		}
		if (!repassword.equals(password)) {
			request.setAttribute("msg", "两次输入的密码不相匹配");
			return "signup";
		}
		
		// 处理注册逻辑
		try {
		userService.signup(new User(0, userName, password, email));
		} catch (UserNameExistException e) {
			request.setAttribute("msg", "用户名已存在");
			return "signup";
		} catch (SignupException e) {
			request.setAttribute("msg", "注册失败，请稍后再试");
			return "signup";
		}
		request.getSession().setAttribute("kankantu_user", userName);
		return "main";
	}
	
	@RequestMapping("/signout.do")
	public String signout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		request.getSession().setAttribute("kankantu_user", null);
		return "index";
	}
}
