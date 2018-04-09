package com.drunck.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drunck.domain.SysAdmin;
import com.drunck.domain.SysRole;
import com.drunck.domain.SysUserRole;
import com.drunck.service.SysAdminService;
import com.drunck.service.SysRoleService;
import com.drunck.service.SysUserRoleService;
import com.drunck.utils.CommonUtil;
import com.drunck.utils.PageBean;
import com.drunck.utils.PageInfo;
import com.drunck.utils.ResultInfo;

@Controller
@RequestMapping("user")
public class AdminController {
	@Resource
	private SysAdminService sysAdminService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	
	@RequestMapping("list")
	public ModelAndView list(String userName, PageInfo pageInfo) {
		ModelAndView view = new ModelAndView();

		SysAdmin sysAdmin = new SysAdmin();
		if(CommonUtil.isNotEmpty(userName)) {
			sysAdmin.setUserName(userName);
		}
		PageBean pager = sysAdminService.queryByPager(sysAdmin, pageInfo.getPageNo(), pageInfo.getPageSize());
		view.addObject("pager", pager);
		view.addObject("sysAdmin", sysAdmin);
		view.setViewName("admin/list");
		return view;
	}
	
	@RequestMapping("toAdd")
	public String toAdd(ModelMap modelMap) {
		List<SysRole> roles = sysRoleService.SelectAll();
		modelMap.addAttribute("roles", roles);
		return "admin/adduser";
	}
	
	@RequestMapping("createUser")
	@ResponseBody
	public ResultInfo createUser(SysAdmin sysAdmin, String roleId) {
		sysAdminService.save(sysAdmin, roleId);
		return ResultInfo.instance();
	}
	
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap modelMap) {
		List<SysRole> roles = sysRoleService.SelectAll();
		modelMap.addAttribute("roles", roles);
		SysAdmin sysAdmin = sysAdminService.queryById(id);
		modelMap.addAttribute("sysAdmin", sysAdmin);
		SysUserRole param = new SysUserRole();
		param.setUserId(id);
		SysUserRole sysUserRole = sysUserRoleService.selectOne(param);
		modelMap.addAttribute("sysUserRole", sysUserRole);
		return "admin/edituser";
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	public ResultInfo updateUser(SysAdmin sysAdmin, String roleId) {
		Date date = new Date();
		sysAdmin.setUpdateTime(date);
		sysAdminService.update(sysAdmin, roleId);
		return ResultInfo.instance();
	}
	
	@RequestMapping("deleteUser")
	@ResponseBody
	public ResultInfo deleteUser(String id) {
		sysAdminService.deleteById(id);
		return ResultInfo.instance();
	}
}
