package com.drunck.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.drunck.domain.SysScheduleJob;
import com.drunck.service.ScheduleJobService;
import com.drunck.utils.CommonUtil;
import com.drunck.utils.PageBean;
import com.drunck.utils.PageInfo;
import com.drunck.utils.ResultInfo;

@Controller
@RequestMapping("job")
public class ScheduleJobController {
	@Resource
	private ScheduleJobService scheduleJobService;
	
	@RequestMapping("list")
	public ModelAndView list(String jobGroup, String jobName, PageInfo pageInfo) {
		ModelAndView view = new ModelAndView();
		SysScheduleJob scheduleJob = new SysScheduleJob();
		if(CommonUtil.isNotEmpty(jobGroup)) {
			scheduleJob.setJobGroup(jobGroup);
		}
		if(CommonUtil.isNotEmpty(jobName)) {
			scheduleJob.setJobName(jobName);
		}
		PageBean pager = scheduleJobService.queryByPager(scheduleJob, pageInfo.getPageNo(), pageInfo.getPageSize());
		view.addObject("pager", pager);
		view.addObject("scheduleJob", scheduleJob);
		view.setViewName("common/task");
		return view;
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		return "common/addtask";
	}
	
	@RequestMapping("createJob")
	@ResponseBody
	public ResultInfo createJob(SysScheduleJob scheduleJob) {
		scheduleJob.setServiceName("com.drunck.service.impl.ScheduleJobServiceImpl");
		scheduleJob.setServiceMethod("scheduleJob");
		scheduleJobService.createJob(scheduleJob);
		return ResultInfo.instance();
	}
	
	@RequestMapping("updateJob")
	@ResponseBody
	public ResultInfo updateJob(SysScheduleJob scheduleJob) {
		scheduleJobService.updateJob(scheduleJob);
		return ResultInfo.instance();
	}
	
	@RequestMapping("deleteJob")
	@ResponseBody
	public ResultInfo deleteJob(String id) {
		scheduleJobService.deleteJob(id);
		return ResultInfo.instance();
	}
	
	@RequestMapping("runOnce")
	@ResponseBody
	public ResultInfo runOnce(String id) {
		scheduleJobService.runOnce(id);
		return ResultInfo.instance();
	}
	
	@RequestMapping("pauseJob")
	@ResponseBody
	public ResultInfo pauseJob(String id) {
		scheduleJobService.pauseJob(id);
		return ResultInfo.instance();
	}
	
	@RequestMapping("resumeJob")
	@ResponseBody
	public ResultInfo resumeJob(String id) {
		scheduleJobService.resumeJob(id);
		return ResultInfo.instance();
	}
}
