package com.bd17kaka.kankantu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bd17kaka
 */
@Controller(value = "adminContoller")
public class AdminContoller extends BaseController {

/*	@Resource(name = "appService")
	private AppService appService;*/
	
	@RequestMapping("/getAppList.do")
	public String getAppList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		return "";
	}
}
