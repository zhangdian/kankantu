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
import com.bd17kaka.kankantu.service.WeiboService;
import com.bd17kaka.kankantu.utils.RedisUtils;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

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
		List<SinaWeiboRecommendUser> list = sinaWeiboRecommendUserService.listRecommendUser(userId, tagName);
		request.setAttribute("list_recommend_user", list);
		for (SinaWeiboRecommendUser t : list) {
			System.out.println(t.toString());
		}

		// 获取所有tag，设置cur_tag
		List<TagInfo> list2 = sinaWeiboTagService.list(userId);
		request.setAttribute("list_tag", list2);
		request.setAttribute("cur_tag", tagName);
		return "sinaweibo_tag";
	}
}
