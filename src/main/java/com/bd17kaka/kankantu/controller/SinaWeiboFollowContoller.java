package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.exception.SinaweiboUserNotFoundException;
import com.bd17kaka.kankantu.service.SinaWeiboFollowService;
import com.bd17kaka.kankantu.service.SinaWeiboRecommendUserService;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * @author bd17kaka
 */
@Controller(value = "sinaWeiboFollowContoller")
public class SinaWeiboFollowContoller extends BaseController {

	@Resource(name="sinaWeiboFollowService")
	private SinaWeiboFollowService sinaWeiboFollowService;
	
	/**
	 * 获取推荐用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("addFollow.do")
	public void addFollow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		// 参数检查
		String tagName = StringUtils.trimToEmpty(request.getParameter("tag_name"));
		String uid = StringUtils.trimToEmpty(request.getParameter("uid"));
		if (StringUtils.isEmpty(tagName) || StringUtils.isEmpty(uid)) {
			response.sendRedirect("listSinaWeiboTag.do");
			return;
		}
		
		// 保存关注的用户
		try {
			sinaWeiboFollowService.addFollow(userId, uid, tagName);
		} catch (SinaweiboUserNotFoundException e) {
			writeHtml(request, response, "用户不存在");
		} catch (WeiboException e) {
			// return error page;
		}
	}
}
