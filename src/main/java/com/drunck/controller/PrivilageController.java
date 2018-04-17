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
import com.drunck.service.SysPrivilageService;
import com.drunck.utils.CommonUtil;
import com.drunck.utils.PageBean;
import com.drunck.utils.PageInfo;
import com.drunck.utils.ResultInfo;

@Controller
@RequestMapping("privilage")
public class PrivilageController {
	@Resource
	private SysPrivilageService sysPrivilageService;
	
	@RequestMapping("list")
	public ModelAndView list(String name, String resKey, PageInfo pageInfo) {
		ModelAndView view = new ModelAndView();

		SysPrivilage sysPrivilage = new SysPrivilage();
		if(CommonUtil.isNotEmpty(name)) {
			sysPrivilage.setName(name);
		}
		if(CommonUtil.isNotEmpty(resKey)) {
			sysPrivilage.setResKey(resKey);
		}
		
		PageBean pager = sysPrivilageService.queryByPager(sysPrivilage, pageInfo.getPageNo(), pageInfo.getPageSize());
		view.addObject("pager", pager);
		view.addObject("sysPrivilage", sysPrivilage);
		view.setViewName("privilage/list");
		return view;
	}
	
	@RequestMapping("toAdd")
	public String toAdd(ModelMap modelMap) {
		List<SysPrivilage> privilages = sysPrivilageService.queryTree();
		modelMap.addAttribute("privilages", privilages);
		return "privilage/addPrivilage";
	}
	
	@RequestMapping("createPrivilage")
	@ResponseBody
	public ResultInfo createPrivilage(SysPrivilage sysPrivilage) {
		Date date = new Date();
		if(CommonUtil.isEmpty(sysPrivilage.getParentId())) {
			sysPrivilage.setParentId("0");
		}
		sysPrivilage.setCreateTime(date);
		sysPrivilage.setUpdateTime(date);
		sysPrivilageService.save(sysPrivilage);
		return ResultInfo.instance();
	}
	
	@RequestMapping("toEdit")
	public String toEdit(String id, ModelMap modelMap) {
		List<SysPrivilage> privilages = sysPrivilageService.queryTree();
		modelMap.addAttribute("privilages", privilages);
		SysPrivilage sysPrivilage = sysPrivilageService.queryById(id);
		modelMap.addAttribute("sysPrivilage", sysPrivilage);
		return "privilage/addPrivilage";
	}
	
	@RequestMapping("updatePrivilage")
	@ResponseBody
	public ResultInfo updatePrivilage(SysPrivilage sysPrivilage) {
		Date date = new Date();
		sysPrivilage.setUpdateTime(date);
		sysPrivilageService.update(sysPrivilage);
		return ResultInfo.instance();
	}
	
	@RequestMapping("deletePrivilage")
	@ResponseBody
	public ResultInfo deletePrivilage(String id) {
		sysPrivilageService.deleteById(id);
		return ResultInfo.instance();
	}
}
