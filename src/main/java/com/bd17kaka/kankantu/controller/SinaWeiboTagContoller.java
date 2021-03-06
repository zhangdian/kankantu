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

import com.bd17kaka.kankantu.exception.KankantuException;
import com.bd17kaka.kankantu.exception.UserNotAuthorizeException;
import com.bd17kaka.kankantu.po.TagInfo;
import com.bd17kaka.kankantu.service.SinaWeiboTagService;
import com.bd17kaka.kankantu.service.WeiboService;
import com.bd17kaka.kankantu.utils.RedisUtils;
import com.bd17kaka.kankantu.weibo4j.model.WeiboException;

/**
 * @author bd17kaka
 */
@Controller(value = "sinaWeiboTagContoller")
public class SinaWeiboTagContoller extends BaseController {

	@Resource(name="sinaWeiboTagService")
	private SinaWeiboTagService sinaWeiboTagService;
	
	/**
	 * 同步tag
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("syncSinaWeiboTag.do")
	public void syncSinaWeiboTag(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		try {
			sinaWeiboTagService.syncSinaWeiboTags(userId);
		} catch (WeiboException e) {
			writeHtml(request, response, "连接Sina微博好像出了点问题~~~");
		} catch (UserNotAuthorizeException e) {
			writeHtml(request, response, "I'm very very very sorry, 我们内部好像出了点问题~~~");
		} catch (KankantuException e) {
			writeHtml(request, response, "好像没有授权哦，或者授权过期啦~~~，快去授权吧！");
		}
		response.sendRedirect("listSinaWeiboTag.do");
		return;
	}
	
	/**
	 * 获取所有tag列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws WeiboException
	 */
	@RequestMapping("listSinaWeiboTag.do")
	public String listSinaWeiboTag(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, WeiboException  {
		String userId = request.getSession().getAttribute("kankantu_userid").toString();
		List<TagInfo> list = sinaWeiboTagService.list(userId);
		request.setAttribute("list_tag", list);
		request.setAttribute("cur_tag", "all");
		return "sinaweibo_tag";
	}
}
