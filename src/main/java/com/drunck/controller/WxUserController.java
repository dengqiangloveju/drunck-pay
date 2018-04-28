package com.drunck.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.drunck.utils.PageInfo;

@RestController
@RequestMapping("wxUser")
public class WxUserController {
	
	@RequestMapping("analyse")
	public ModelAndView list(String name, PageInfo pageInfo) {
		ModelAndView view = new ModelAndView();
		view.setViewName("welcome");
		return view;
	}
	
}
