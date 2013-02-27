package com.bd17kaka.kankantu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd17kaka.kankantu.constant.ConstatVar;
import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.SinaWeiboRecommendUser;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.service.SinaWeiboRecommendUserService;
import com.bd17kaka.kankantu.service.SinaWeiboTagService;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;
import com.bd17kaka.kankantu.weibo4j.org.json.JSONException;
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
	public String listRecommendUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
		LinkedList<JSONObject> result = null;
		try {
			result = sinaWeiboRecommendUserService.getAllRecommendUser(userId, tagName);
		} catch (KankantuException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		} catch (UserNotAuthorizeException e) {
			writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
		}
		if (null == result) {
			request.setAttribute("list", null);
		} else {
			System.out.println(result.size());
			List<List<SinaWeiboRecommendUser>> list = new ArrayList<List<SinaWeiboRecommendUser>>();
			boolean isEmpty = false;
			for (int i = 0; i < ConstatVar.COL_NUM; i++) { // 6列
				// 获取推荐用户信息，每列只有三个用户，但是拼装成一个List
				List<SinaWeiboRecommendUser> listCol = new ArrayList<SinaWeiboRecommendUser>();

				for (int j = 0; j < 3;) {
					JSONObject jo = null;
					try {
						jo = result.pop();
					} catch (NoSuchElementException e){
						isEmpty = true;
						break;
					}
					
					String uid;
					try {
						uid = jo.getString("uid");
					} catch (JSONException e1) {
						continue;
					}
					try {
						SinaWeiboRecommendUser sinaWeiboRecommendUser = sinaWeiboRecommendUserService.getByUid(userId, uid);
						if (null != sinaWeiboRecommendUser) {
							listCol.add(sinaWeiboRecommendUser);
							j++;
						}
					} catch (WeiboException e) {
					} catch (KankantuException e) {
					} catch (UserNotAuthorizeException e) {
						writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
						return "";
					}
				}
				list.add(listCol);
				if (isEmpty) {
					break;
				}
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
	public void listMoreRecommendUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		String curTag = request.getSession().getAttribute("cur_tag").toString();
		
		@SuppressWarnings("unchecked")
		LinkedList<JSONObject> result = (LinkedList<JSONObject>) request.getSession().getAttribute("all_recommend_user");
		if (null == result) {
			writeHtml(request, response, "没有更多");
			return;
		}

		//  获取最新的6个推荐用户信息
		List<SinaWeiboRecommendUser> list = new ArrayList<SinaWeiboRecommendUser>();
		for (int i = 0; i < 6;) {
			JSONObject jo = null;
			try {
				jo = result.pop();
			} catch (NoSuchElementException e){
				break;
			}
			System.out.println(result.size());
			
			// 获取推荐用户信息，每列只有一个用户，但是拼装成一个List
			String uid;
			try {
				uid = jo.getString("uid");
			} catch (JSONException e1) {
				continue;
			}
			
			try {
				SinaWeiboRecommendUser sinaWeiboRecommendUser = sinaWeiboRecommendUserService.getByUid(userId, uid);
				if (null != sinaWeiboRecommendUser) {
					list.add(sinaWeiboRecommendUser);
					i++;
				}
			} catch (WeiboException e) {
			} catch (KankantuException e) {
			} catch (UserNotAuthorizeException e) {
				writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
			}
		}
		
		// 拼装成html
		String msg = "";
		msg += "<div class=\"row-fluid\">";
		for (SinaWeiboRecommendUser s : list) {
			msg += "<div class=\"span2\">";
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
			msg += "</div>";
		}
		msg += "</div>";
		writeHtml(request, response, msg);
	}
}
