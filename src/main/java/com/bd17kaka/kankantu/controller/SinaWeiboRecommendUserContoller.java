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

import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.service.SinaWeiboRecommendUserService;
import com.bd17kaka.kankantu.service.SinaWeiboTagService;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONObject;

/**
 * @author bd17kaka
 */
@Controller(value = "sinaWeiboRecommendUserContoller")
public class SinaWeiboRecommendUserContoller extends BaseController {

	@Resource(name="sinaWeiboRecommendUserService")
	private SinaWeiboRecommendUserService sinaWeiboRecommendUserService;
	
	@Resource(name="sinaWeiboTagService")
	private SinaWeiboTagService sinaWeiboTagService;
	
	/**
	 * 获取推荐用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("listRecommendUser.do")
	public String listRecommendUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		
		// 参数判断
		System.out.println(request.getParameter("tag_name"));
		String tagName = StringUtils.trimToEmpty(request.getParameter("tag_name"));
		if (StringUtils.isEmpty(tagName)) {
			response.sendRedirect("listSinaWeiboTag.do");
			return "";
		}
		
		// 获取推荐用户
		// 首选获取所有的推荐用户信息
		List<JSONObject> result = sinaWeiboRecommendUserService.getAllRecommendUser(userId, tagName);
		if (null == result) {
			request.setAttribute("list_user", null);
		} else {
			List<JSONObject> subArray = result.subList(0, 10);
			List<SinaWeiboRecommendUser> list = sinaWeiboRecommendUserService.listRecommendUser(userId, subArray);
			request.setAttribute("list_user", list);
			request.getSession().setAttribute("all_recommend_user", result.subList(10, result.size()));
		}

		// 获取所有tag，设置cur_tag
		List<TagInfo> list2 = sinaWeiboTagService.list(userId);
		request.setAttribute("list_tag", list2);
		request.getSession().setAttribute("cur_tag", tagName);
		return "sinaweibo_tag";
	}
	
	/**
	 * 获取推荐用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */              
	@RequestMapping("listMoreRecommendUser.do")
	public void listMoreRecommendUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		String curTag = request.getSession().getAttribute("cur_tag").toString();
		
		@SuppressWarnings("unchecked")
		List<JSONObject> result = (List<JSONObject>) request.getSession().getAttribute("all_recommend_user");
		if (null == result) {
			writeHtml(request, response, "没有更多");
			return;
		}

		//  获取最新的10个推荐用户信息
		List<JSONObject> subArray = null;
		List<SinaWeiboRecommendUser> list = null;
		if (result.size() < 10) {
			subArray = result.subList(0, result.size());
			request.getSession().setAttribute("all_recommend_user", null);
		} else {
			subArray = result.subList(0, 10);
			request.getSession().setAttribute("all_recommend_user", result.subList(10, result.size()));
		}
		list = sinaWeiboRecommendUserService.listRecommendUser(userId, subArray);
		
		// 拼装成html
		String msg = "";
		for (SinaWeiboRecommendUser s : list) {
			msg += 
				"<li class='span2'>" +
				"	<div class='thumbnail'>" +
				"		<a href='#'>" +
				" 			<img src='"+ s.getProfileImageURL() +"' alt=''>" +
				"		</a>" +
				"	<div class='caption'>" +
				"	<h5>" + s.getUserName() + "</h5>" +
				"	<p>"+ s.getFollowCount() +"粉丝</p>" +
				(
						!s.isFollow() ? 
						"		<p><a onclick=\"addFollow("+ s.getUserId() +",'" + curTag + "');\" class='btn btn-primary' id='follow_"+ s.getUserId() +"'>加关注</a></p>" +
						"	</c:if>" 
						:
						"		<p><a onclick=\"deleteFollow("+ s.getUserId() +",'" + curTag + "');\" class='btn' id='follow_"+ s.getUserId() +"'>取消关注</a></p>" +
						"	</c:if>") 
				+
				"	</div>" +
				"	</div>" +
				"</li>";
			System.out.println(msg);
		}
				
		writeHtml(request, response, msg);
	}
}
