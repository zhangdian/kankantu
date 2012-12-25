package com.bd17kaka.kankantu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 身份验证filter
 * 
 * @author bd17kaka
 * 
 */
public class IdentityFilter implements Filter {

	private static final Logger Log = LoggerFactory
			.getLogger(IdentityFilter.class);

	public void destroy() {
		Log.info(" IdentityFilter destroy");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Log.debug("start do Filter.....");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 获取保存在session中的用户名
		String user = (String) req.getSession().getAttribute("user");

		// 获取请求的URL
		String srcUrl = req.getRequestURL().toString();
		String[] tokens = srcUrl.split("/");
		String url = tokens[tokens.length - 1];
		if (url.equals("index.jsp")) {
			chain.doFilter(req, resp);

		}else if(url.equals("login.do")){
			chain.doFilter(req, resp);
		}
		else if (user == null || user != "sendcloud") {
			// 用户没有登陆
			resp.sendRedirect("index.jsp");
		} else {
			// 登陆了的用户
			chain.doFilter(req, resp);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		Log.info(" IdentityFilter init");
	}

}
