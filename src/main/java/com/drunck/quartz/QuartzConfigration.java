package com.drunck.quartz;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@ConditionalOnBean(DataSource.class)
public class QuartzConfigration {
	// 指定quartz.properties
	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	// 获取工厂bean
	@Bean(name = "scheduler")
	public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		try {
			schedulerFactoryBean.setDataSource(dataSource);
			schedulerFactoryBean.setQuartzProperties(quartzProperties());
			schedulerFactoryBean.setSchedulerName("scheduler");
			/*必须的，QuartzScheduler 延时启动，应用启动完后 QuartzScheduler 再启动*/
			schedulerFactoryBean.setStartupDelay(30);
			/*通过applicationContextSchedulerContextKey属性配置spring上下文*/
			schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
			/*可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了*/
			schedulerFactoryBean.setOverwriteExistingJobs(true);
			/*设置自动启动*/
			schedulerFactoryBean.setAutoStartup(true);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return schedulerFactoryBean;
	}
}
