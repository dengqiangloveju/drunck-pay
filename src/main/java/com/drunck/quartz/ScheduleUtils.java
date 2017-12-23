package com.drunck.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.alibaba.fastjson.JSONObject;
import com.drunck.domain.SysScheduleJob;
import com.drunck.exception.ScheduleException;

public class ScheduleUtils {
	public static void createScheduleJob(Scheduler scheduler, SysScheduleJob scheduleJob) {
		createScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(), scheduleJob.getCronExpression(), scheduleJob);
	}

	private static void createScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression, SysScheduleJob scheduleJob) {
		JobDetail jobDetail = JobBuilder.newJob(SyncJobFactory.class).withIdentity(jobName, jobGroup).build();

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();

		String jobTrigger = trigger.getKey().getName();

		scheduleJob.setJobTrigger(jobTrigger);

		jobDetail.getJobDataMap().put("jobParam", JSONObject.toJSONString(scheduleJob));
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("创建定时任务失败");
		}
	}

	public static void updateScheduleJob(Scheduler scheduler, SysScheduleJob scheduleJob) {
		updateScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(), scheduleJob.getCronExpression(), scheduleJob);
	}

	private static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup, String cronExpression, SysScheduleJob scheduleJob) {
		TriggerKey triggerKey = getTriggerKey(jobName, jobGroup);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		try {
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			trigger = (CronTrigger) trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("更新定时任务失败");
		}
	}

	public static void deleteAuthScheduleJob(Scheduler scheduler, String jobName, String jobGroup) {
		try {
			scheduler.deleteJob(getJobKey(jobName, jobGroup));
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("删除定时任务失败");
		}
	}

	public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("运行一次定时任务失败");
		}
	}

	public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("暂停定时任务失败");
		}
	}

	public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ScheduleException("暂停定时任务失败");
		}
	}

	public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
		return TriggerKey.triggerKey(jobName, jobGroup);
	}

	public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
			return (CronTrigger) scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		throw new ScheduleException("获取定时任务CronTrigger出现异常");
	}

	public static JobKey getJobKey(String jobName, String jobGroup) {
		return JobKey.jobKey(jobName, jobGroup);
	}
}
