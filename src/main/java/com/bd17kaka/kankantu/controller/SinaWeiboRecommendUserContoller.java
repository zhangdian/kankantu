package com.bd17kaka.kankantu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
		LinkedList<JSONObject> result = sinaWeiboRecommendUserService.getAllRecommendUser(userId, tagName);
		System.out.println(result.size());
		if (null == result) {
			request.setAttribute("list", null);
		} else {
			List<List<SinaWeiboRecommendUser>> list = new ArrayList<List<SinaWeiboRecommendUser>>();
			for (int i = 0; i < 6; ++i) { // 6列
				List<JSONObject> subArray = new ArrayList<JSONObject>();
				for (int j = 0; j < 3; ++j) {
					subArray.add(result.pop());
				}
//				List<JSONObject> subArray = result.subList(3 * i, 3 * (i + 1));
				List<SinaWeiboRecommendUser> listCol = sinaWeiboRecommendUserService.listRecommendUser(userId, subArray);
				list.add(listCol);
			}
			request.setAttribute("list", list);
//			request.getSession().setAttribute("all_recommend_user", result.subList(6 * 3, result.size()));
			request.getSession().setAttribute("all_recommend_user", result);
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

		//  获取最新的6个推荐用户信息
		List<JSONObject> subArray = new ArrayList<JSONObject>();
		List<SinaWeiboRecommendUser> list = null;
		for (int i = 0; i < ((result.size() < 2) ? result.size() : 2); i++) {
			subArray.add((JSONObject)((LinkedList<JSONObject>) request.getSession().getAttribute("all_recommend_user")).pop());
		}
		
		list = sinaWeiboRecommendUserService.listRecommendUser(userId, subArray);
		// 拼装成html
		String msg = "";
		for (SinaWeiboRecommendUser s : list) {
			msg += "<div class='thumbnail'>" +
						"<a href='#'>" +
							"<img src='" + s.getProfileImageURL() + "' alt=''>" +
						"</a>" +
						"<div class='caption'>" +
							"<h5>" + s.getUserName() + "</h5>" +
							"<p>" + s.getFollowCount() + "粉丝</p>" +
							(!s.isFollow() ? 
									"<p><a onclick=\"addFollow(" + s.getUserId() + ", '" + curTag + "');\" class='btn btn-primary' id='follow_" + s.getUserId() + "'>加关注</a></p>"
									:
									"<p><a onclick=\"deleteFollow(" + s.getUserId() + ", '" + curTag + "');\" class='btn' id='follow_" + s.getUserId() + "'>取消关注</a></p>" 
							) +
						"</div>" + 
					"</div>";
		}
		System.out.println(msg);
		writeHtml(request, response, msg);
	}
}
