package com.drunck.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drunck.domain.SysPrivilage;
import com.drunck.domain.SysRole;
import com.drunck.domain.SysRolePrivilage;
import com.drunck.service.SysPrivilageService;
import com.drunck.service.SysRolePrivilageService;
import com.drunck.service.SysRoleService;
import com.drunck.utils.CommonUtil;
import com.drunck.utils.PageBean;
import com.drunck.utils.PageInfo;
import com.drunck.utils.ResultInfo;

@Controller
@RequestMapping("role")
public class RoleController {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysPrivilageService sysPrivilageService;
	@Resource
	private SysRolePrivilageService sysRolePrivilageService;
	
	@RequestMapping("list")
	public ModelAndView list(String name, PageInfo pageInfo) {
		ModelAndView view = new ModelAndView();

		SysRole sysRole = new SysRole();
		if(CommonUtil.isNotEmpty(name)) {
			sysRole.setName(name);
		}
		PageBean pager = sysRoleService.queryByPager(sysRole, pageInfo.getPageNo(), pageInfo.getPageSize());
		view.addObject("pager", pager);
		view.addObject("sysRole", sysRole);
		view.setViewName("role/list");
		return view;
	}
	
	@RequestMapping("toAdd")
	public String toAdd(ModelMap modelMap) {
		List<SysPrivilage> privilages = sysPrivilageService.queryPrivilage();
		modelMap.addAttribute("privilages", privilages);
		
		return "role/addrole";
	}
	
	@RequestMapping("createRole")
	@ResponseBody
	public ResultInfo createJob(SysRole sysRole, String[] pids) {
		Date date = new Date();
		sysRole.setCreateTime(date);
		sysRole.setUpdateTime(date);
		sysRoleService.save(sysRole, pids);
		return ResultInfo.instance();
	}
	
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap modelMap) {
		SysRole sysRole = sysRoleService.queryById(id);
		modelMap.addAttribute("sysRole", sysRole);
		
		List<SysPrivilage> privilages = sysPrivilageService.queryPrivilage();
		modelMap.addAttribute("privilages", privilages);
		
		SysRolePrivilage query = new SysRolePrivilage();
		query.setRoleId(id);
		List<SysRolePrivilage> rolePrivilages = sysRolePrivilageService.select(query);
		modelMap.addAttribute("rolePrivilages", rolePrivilages);
		
		return "role/editrole";
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public ResultInfo updateJob(SysRole sysRole, String[] pids) {
		Date date = new Date();
		sysRole.setUpdateTime(date);
		sysRoleService.update(sysRole, pids);
		return ResultInfo.instance();
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public ResultInfo deleteJob(String id) {
		sysRoleService.deleteById(id);
		return ResultInfo.instance();
	}
}
