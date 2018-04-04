package com.drunck.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysPrivilage;
import com.drunck.service.SysAdminService;
import com.drunck.service.SysPrivilageService;

@Controller
@RequestMapping("sys")
public class IndexController extends BaseController {
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysPrivilageService sysPrivilageService;
	
	@RequestMapping("loginfrom")
	public String loginfrom(String id) {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(String id, ModelMap map) {
		SysAdmin sysAdmin = (SysAdmin) this.getSession().getAttribute("currentUser");
		List<SysPrivilage> menus = sysPrivilageService.queryMenu(sysAdmin.getId());
		map.put("menus", menus);
		return "index";
	}
	
	
	
}
