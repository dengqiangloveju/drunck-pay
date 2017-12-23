package com.drunck.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.drunck.base.BaseMapper;
import com.drunck.base.impl.BaseServiceImpl;
import com.drunck.domain.SysScheduleJob;
import com.drunck.mapper.SysScheduleJobMapper;
import com.drunck.quartz.ScheduleUtils;
import com.drunck.service.ScheduleJobService;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends BaseServiceImpl implements ScheduleJobService {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);
	@Resource
	private SysScheduleJobMapper scheduleJobMapper;
	@Resource
	private Scheduler scheduler;
	
	@Override
	public BaseMapper<?> getMapper() {
		return scheduleJobMapper;
	}

	@Override
	public void createJob(SysScheduleJob scheduleJob) {
		Date date = new Date();
		scheduleJob.setCreateTime(date);
		scheduleJob.setUpdateTime(date);
		scheduleJobMapper.insertSelective(scheduleJob);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}

	@Override
	public void updateJob(SysScheduleJob scheduleJob) {
		scheduleJob.setUpdateTime(new Date());
		scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
	}

	@Override
	public void deleteJob(String id) {
		SysScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(id);
		scheduleJobMapper.deleteByPrimaryKey(id);
		ScheduleUtils.deleteAuthScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}

	@Override
	public void runOnce(String id) {
		SysScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(id);
		ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
		
	}

	@Override
	public void pauseJob(String id) {
		SysScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(id);
		scheduleJob.setStatus("1");
		scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
		ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}

	@Override
	public void resumeJob(String id) {
		SysScheduleJob scheduleJob = scheduleJobMapper.selectByPrimaryKey(id);
		scheduleJob.setStatus("0");
		scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
		ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}

	@Override
	public void scheduleJob() {
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"===========>任务执行");
	}

}
