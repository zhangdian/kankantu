package com.bd17kaka.kankantu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.SinaweiboUserNotFoundException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.service.SinaWeiboFollowService;
import com.bd17kaka.kankantu.service.SinaWeiboTagService;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * @author bd17kaka
 */
@Controller(value = "sinaWeiboFollowContoller")
public class SinaWeiboFollowContoller extends BaseController {

	@Resource(name="sinaWeiboFollowService")
	private SinaWeiboFollowService sinaWeiboFollowService;
	
	@Resource(name="sinaWeiboTagService")
	private SinaWeiboTagService sinaWeiboTagService;
	
	/**
	 * 关注新浪微博用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("addFollow.do")
	public void addFollow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
		} catch (WeiboException e) {
			writeHtml(request, response, "连接Sina微博好像出了点问题~~~");
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		} catch (UserNotAuthorizeException e) {
			writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
		}
	}
	
	/**
	 * 取消对新浪微博用户的关注
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("deleteFollow.do")
	public void deleteFollow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		// 参数检查
		String tagName = StringUtils.trimToEmpty(request.getParameter("tag_name"));
		String uid = StringUtils.trimToEmpty(request.getParameter("uid"));
		if (StringUtils.isEmpty(tagName) || StringUtils.isEmpty(uid)) {
			response.sendRedirect("listSinaWeiboTag.do");
			return;
		}
		
		// 删除关注的用户
		try {
			sinaWeiboFollowService.deleteFollow(userId, uid, tagName);
		} catch (WeiboException e) {
			writeHtml(request, response, "连接Sina微博好像出了点问题~~~");
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		} catch (UserNotAuthorizeException e) {
			writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
		}
	}
	
	/**
	 * 显示当前tag分组的用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("showFollows.do")
	public String showFollows(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		// 参数检查
		String tagName = StringUtils.trimToEmpty(request.getParameter("tag_name"));
		if (StringUtils.isEmpty(tagName)) {
			response.sendRedirect("listSinaWeiboTag.do");
			return "";
		}
		
		// 获取当前tag_name分组，关注的用户
		List<SinaWeiboRecommendUser> list = null;
		try {
			list = sinaWeiboFollowService.listFollowByTag(userId, tagName);
			System.out.println("GGGGGGGGGGG:" + list.size());
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		} catch (UserNotAuthorizeException e) {
			writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
		}
		List<List<SinaWeiboRecommendUser>> listSinaWeiboRecommendUser = new ArrayList<List<SinaWeiboRecommendUser>>();
		listSinaWeiboRecommendUser.add(list);
		request.setAttribute("list", listSinaWeiboRecommendUser);
		
		// 获取所有tag，设置cur_tag
		List<TagInfo> list2 = sinaWeiboTagService.list(userId);
		request.setAttribute("list_tag", list2);
		request.setAttribute("cur_tag", tagName);
		
		return "sinaweibo_tag";
	}
}
