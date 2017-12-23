package com.drunck.quartz;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSONObject;
import com.drunck.domain.SysScheduleJob;
import com.drunck.invoker.ServiceInvoker;

public class SyncJobFactory extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(SyncJobFactory.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		String str = (String) mergedJobDataMap.get("jobParam");
	    SysScheduleJob scheduleJob = JSONObject.parseObject(str, SysScheduleJob.class);
	    if (scheduleJob==null) {
	      return;
	    }
	    logger.info("同步任务,执行时间：" + sdf.format(new Date()) + ",jobName:" + scheduleJob.getJobName());
	    try
	    {
	      Object serviceImpl = ServiceInvoker.invoke(Class.forName(scheduleJob.getServiceName()));

	      Method method = serviceImpl.getClass().getDeclaredMethod(scheduleJob.getServiceMethod(), new Class[0]);
	      method.invoke(serviceImpl, new Object[0]);
	    } catch (Exception e) {
	    	logger.error("定时器执行异常", e);
	    }
	}

}
