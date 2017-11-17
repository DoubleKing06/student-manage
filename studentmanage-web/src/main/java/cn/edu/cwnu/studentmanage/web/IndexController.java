/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/", method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController{
	
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request,Model view){
	    view.addAttribute("contextPath", request.getContextPath());
		return "mainFrame/frame";
	}
}
