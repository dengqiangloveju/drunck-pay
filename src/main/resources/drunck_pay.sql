/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.39 : Database - drunck_pay
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`drunck_pay` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `drunck_pay`;

/*Table structure for table `qrtz_blob_triggers` */

DROP TABLE IF EXISTS `qrtz_blob_triggers`;

CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_blob_triggers` */

/*Table structure for table `qrtz_calendars` */

DROP TABLE IF EXISTS `qrtz_calendars`;

CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_calendars` */

/*Table structure for table `qrtz_cron_triggers` */

DROP TABLE IF EXISTS `qrtz_cron_triggers`;

CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_cron_triggers` */

insert  into `qrtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values ('scheduler','aaa','jobGroup','0/10 * * * * ?','Asia/Shanghai');

/*Table structure for table `qrtz_fired_triggers` */

DROP TABLE IF EXISTS `qrtz_fired_triggers`;

CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_fired_triggers` */

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_job_details` */

insert  into `qrtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values ('scheduler','aaa','jobGroup',NULL,'com.drunck.quartz.SyncJobFactory','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0jobParamt\0�{\"createTime\":1513783422674,\"description\":\"fds\",\"jobGroup\":\"jobGroup\",\"jobName\":\"aaa\",\"jobTrigger\":\"aaa\",\"serviceMethod\":\"scheduleJob\",\"serviceName\":\"com.drunck.service.impl.ScheduleJobServiceImpl\",\"status\":\"0\",\"updateTime\":1513783422674}x\0');

/*Table structure for table `qrtz_locks` */

DROP TABLE IF EXISTS `qrtz_locks`;

CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_locks` */

insert  into `qrtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('scheduler','STATE_ACCESS'),('scheduler','TRIGGER_ACCESS');

/*Table structure for table `qrtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;

CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_paused_trigger_grps` */

/*Table structure for table `qrtz_scheduler_state` */

DROP TABLE IF EXISTS `qrtz_scheduler_state`;

CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_scheduler_state` */

insert  into `qrtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('scheduler','WIN-GLI6IIITU3N1523520561797',1523531426149,15000);

/*Table structure for table `qrtz_simple_triggers` */

DROP TABLE IF EXISTS `qrtz_simple_triggers`;

CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simple_triggers` */

/*Table structure for table `qrtz_simprop_triggers` */

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;

CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_simprop_triggers` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_triggers` */

insert  into `qrtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values ('scheduler','aaa','jobGroup','aaa','jobGroup',NULL,1523177380000,1523177370000,5,'PAUSED','CRON',1513783422000,0,NULL,0,'');

/*Table structure for table `sys_admin` */

DROP TABLE IF EXISTS `sys_admin`;

CREATE TABLE `sys_admin` (
  `id` varchar(100) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_admin` */

insert  into `sys_admin`(`id`,`user_name`,`password`,`enable`,`create_time`,`update_time`,`is_del`) values ('fbdc6f6fe71d43a2b114c57e19415fa9','admin','admin','','2017-12-14 21:47:46','2017-12-14 21:47:46','\0'),('fc1364209fb944b5a7233e270a1279dd','user','user','','2017-12-14 16:58:38','2017-12-14 16:58:38','\0');

/*Table structure for table `sys_privilage` */

DROP TABLE IF EXISTS `sys_privilage`;

CREATE TABLE `sys_privilage` (
  `id` varchar(100) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `res_key` varchar(50) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `is_menu` bit(1) DEFAULT NULL,
  `parent_id` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_index` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_privilage` */

insert  into `sys_privilage`(`id`,`name`,`res_key`,`url`,`description`,`is_menu`,`parent_id`,`create_time`,`update_time`) values ('01','基础信息','BASE',NULL,'基础信息','','0','2017-12-24 19:49:28','2018-04-04 13:57:16'),('02','定时任务列表','JOB_LIST','/job/list','定时任务列表','','06','2017-12-24 19:50:45','2017-12-24 19:50:50'),('03','定时任务添加','JOB_ADD','/job/createJob','定时任务添加','\0','02','2017-12-24 19:52:41','2017-12-24 19:52:45'),('04','定时任务修改','JOB_UPDATE','/job/updateJob','定时任务修改','\0','02','2017-12-24 19:54:55','2017-12-24 19:55:01'),('05','定时任务删除','JOB_DELETE','/job/deleteJob','定时任务删除','\0','02','2017-12-24 19:55:28','2017-12-24 19:55:32'),('06','通用组件','COMMON',NULL,'通用组件','','0','2018-04-08 10:15:16','2018-04-08 10:15:18'),('07','角色列表','ROLE_LIST','/role/list','角色列表','','01','2018-04-08 10:16:14','2018-04-08 10:16:16'),('08','角色添加','ROLE_ADD','/role/createRole','角色添加','\0','07','2018-04-08 10:18:40','2018-04-08 10:18:44'),('09','角色修改','ROLE_UPDATE','/role/updateRole','角色修改','\0','07','2018-04-08 10:20:11','2018-04-08 10:20:14'),('10','角色删除','ROLE_DELETE','/role/deleteRole','角色删除','\0','07','2018-04-08 10:50:08','2018-04-08 10:21:10'),('11','用户列表','USER_LIST','/user/list','用户列表','','01','2018-04-08 15:38:38','2018-04-08 15:38:41'),('12','用户添加','USER_ADD','/user/createUser','用户添加','\0','11','2018-04-08 15:38:43','2018-04-08 15:38:45'),('13','用户修改','USER_UPDATE','/user/updateUser','用户修改','\0','11','2018-04-08 15:38:47','2018-04-08 15:38:49'),('14','用户删除','USER_DELETE','/user/deleteUser','用户删除','\0','11','2018-04-08 15:38:52','2018-04-08 15:38:54'),('15','资源列表','PRIVILAGE_LIST','/privilage/list','资源列表','','01','2018-04-09 10:43:13','2018-04-09 10:43:15');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(100) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`create_time`,`update_time`) values ('01','超级管理员','2017-12-23 16:02:46','2017-12-23 16:02:49'),('02','普通用户','2017-12-23 16:03:06','2017-12-23 16:03:08');

/*Table structure for table `sys_role_privilage` */

DROP TABLE IF EXISTS `sys_role_privilage`;

CREATE TABLE `sys_role_privilage` (
  `id` varchar(100) NOT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  `privilage_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `up_index` (`role_id`,`privilage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_privilage` */

insert  into `sys_role_privilage`(`id`,`role_id`,`privilage_id`) values ('01','01','01'),('02','01','02'),('03','01','03'),('04','01','04'),('05','01','05'),('06','01','06'),('07','01','07'),('08','01','08'),('09','01','09'),('10','01','10'),('11','01','11'),('12','01','12'),('13','01','13'),('14','01','14'),('15','01','15');

/*Table structure for table `sys_schedule_job` */

DROP TABLE IF EXISTS `sys_schedule_job`;

CREATE TABLE `sys_schedule_job` (
  `id` varchar(100) NOT NULL,
  `job_name` varchar(30) DEFAULT NULL,
  `job_group` varchar(100) DEFAULT NULL,
  `job_trigger` varchar(100) DEFAULT NULL,
  `cron_expression` varchar(100) DEFAULT NULL,
  `service_name` varchar(300) DEFAULT NULL,
  `service_method` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_schedule_job` */

insert  into `sys_schedule_job`(`id`,`job_name`,`job_group`,`job_trigger`,`cron_expression`,`service_name`,`service_method`,`status`,`description`,`create_time`,`update_time`) values ('79560c5f43264c9abc1dac164460fb55','aaa','jobGroup','aaa','0/10 * * * * ?','com.drunck.service.impl.ScheduleJobServiceImpl','scheduleJob','1','fds','2017-12-20 23:23:43','2017-12-26 22:50:59');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ur_index` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`) values ('1','fbdc6f6fe71d43a2b114c57e19415fa9','01'),('2','fc1364209fb944b5a7233e270a1279dd','02');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
