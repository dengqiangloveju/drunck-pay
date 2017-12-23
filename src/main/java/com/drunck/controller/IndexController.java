package com.drunck.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drunck.service.SysAdminService;

@Controller
@RequestMapping("sys")
public class IndexController {
	@Resource
	private SysAdminService sysAdminService;
	
	@RequestMapping("loginfrom")
	public String loginfrom(String id) {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(String id) {
		return "index";
	}
	
}
