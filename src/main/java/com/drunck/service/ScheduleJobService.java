package com.drunck.service;

import com.drunck.base.BaseService;
import com.drunck.domain.SysScheduleJob;

public interface ScheduleJobService extends BaseService {

	void createJob(SysScheduleJob scheduleJob);

	void updateJob(SysScheduleJob scheduleJob);

	void deleteJob(String id);

	void runOnce(String id);

	void pauseJob(String id);

	void resumeJob(String id);
	
	void scheduleJob();
}
