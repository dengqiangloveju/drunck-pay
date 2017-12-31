package com.drunck.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drunck.domain.SysAdmin;
import com.drunck.service.CacheService;
import com.drunck.utils.ResultInfo;

@Controller
@RequestMapping("cache")
public class CacheController {
	@Resource
	private CacheService cacheService;
	
	@RequestMapping("save")
	@ResponseBody
	public ResultInfo save(SysAdmin sysAdmin) {
		cacheService.save(sysAdmin);
		return ResultInfo.instance();
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(SysAdmin sysAdmin) {
		cacheService.save(sysAdmin);
		return ResultInfo.instance();
	}
	
	@RequestMapping("queryById")
	@ResponseBody
	public ResultInfo queryById(String id) {
		ResultInfo info = new ResultInfo();
		info.setStatus(true);
		info.setObj(cacheService.queryById(id));
		return info;
	}
	
	@RequestMapping("deleteById")
	@ResponseBody
	public ResultInfo deleteById(String id) {
		cacheService.deleteById(id);
		return ResultInfo.instance();
	}
	
	@RequestMapping("testSession")
	@ResponseBody
	public ResultInfo testSession(SysAdmin sysAdmin, HttpSession httpSession) {
		ResultInfo info = new ResultInfo();
		info.setStatus(true);
		info.setMsg(httpSession.getId());
		return info;
	}
}
