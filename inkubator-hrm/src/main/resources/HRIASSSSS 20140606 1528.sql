-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hrm
--

CREATE DATABASE IF NOT EXISTS hrm;
USE hrm;

--
-- Definition of table `approval_activity`
--

DROP TABLE IF EXISTS `approval_activity`;
CREATE TABLE `approval_activity` (
  `id` bigint(20) NOT NULL,
  `approval_def_id` bigint(20) DEFAULT NULL,
  `approval_request` varchar(45) DEFAULT NULL,
  `approval_status` int(11) DEFAULT NULL,
  `approval_time` datetime DEFAULT NULL,
  `approved_by` varchar(45) DEFAULT NULL,
  `approval_commment` text,
  `approval_count` int(11) DEFAULT NULL,
  `reject_count` int(11) DEFAULT NULL,
  `pending_data` text,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_approval_def_id_idx` (`approval_def_id`),
  CONSTRAINT `fk_app_dep_act` FOREIGN KEY (`approval_def_id`) REFERENCES `approval_definition` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `approval_activity`
--

/*!40000 ALTER TABLE `approval_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `approval_activity` ENABLE KEYS */;


--
-- Definition of table `approval_definition`
--

DROP TABLE IF EXISTS `approval_definition`;
CREATE TABLE `approval_definition` (
  `id` bigint(20) NOT NULL,
  `process_to_approve_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `min_approver` int(4) DEFAULT NULL,
  `min_rejector` int(4) DEFAULT NULL,
  `approver_type` varchar(45) DEFAULT NULL,
  `approver_individual` varchar(100) DEFAULT NULL,
  `approver_position` varchar(100) DEFAULT NULL,
  `allow_on_behalf` int(4) DEFAULT NULL,
  `on_behalf_type` varchar(45) DEFAULT NULL,
  `on_behalf_individual` varchar(100) DEFAULT NULL,
  `on_behalf_position` varchar(100) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_approval_dep_process_idx` (`process_to_approve_id`),
  CONSTRAINT `fk_approval_dep_process` FOREIGN KEY (`process_to_approve_id`) REFERENCES `proscess_to_approve` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `approval_definition`
--

/*!40000 ALTER TABLE `approval_definition` DISABLE KEYS */;
INSERT INTO `approval_definition` (`id`,`process_to_approve_id`,`name`,`sequence`,`min_approver`,`min_rejector`,`approver_type`,`approver_individual`,`approver_position`,`allow_on_behalf`,`on_behalf_type`,`on_behalf_individual`,`on_behalf_position`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,299944026286798,'Aproval Pembuatan User',1,2,2,'position',NULL,'hr_team',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `approval_definition` ENABLE KEYS */;


--
-- Definition of table `attendance_status`
--

DROP TABLE IF EXISTS `attendance_status`;
CREATE TABLE `attendance_status` (
  `id` bigint(20) NOT NULL,
  `code` varchar(8) DEFAULT NULL,
  `status_kehadrian` varchar(60) DEFAULT NULL,
  `description` text,
  `is_present` int(11) DEFAULT NULL,
  `is_pay` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `upated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `UK_fadv6tojai0ily9m548s6msm1` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attendance_status`
--

/*!40000 ALTER TABLE `attendance_status` DISABLE KEYS */;
INSERT INTO `attendance_status` (`id`,`code`,`status_kehadrian`,`description`,`is_present`,`is_pay`,`created_by`,`created_on`,`updated_by`,`upated_on`,`version`) VALUES 
 (339744757,'THD','Tidak Hadir','Tidak Masuk Kerja',0,0,'deni.fahri','2014-05-29 20:17:35',NULL,NULL,0),
 (686372548,'HD1','Hadir Penuh','Kehadiran penuh sejak jam masuk sampai jam pulang',1,1,'deni.fahri','2014-05-28 10:14:02','deni.fahri','2014-05-29 08:39:47',6),
 (763506625,'HD2','Hadir Setengah Hari','Kehadiran tidak full',1,1,'deni.fahri','2014-05-28 10:19:26',NULL,NULL,0);
/*!40000 ALTER TABLE `attendance_status` ENABLE KEYS */;


--
-- Definition of table `biodata`
--

DROP TABLE IF EXISTS `biodata`;
CREATE TABLE `biodata` (
  `id` bigint(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `biodata`
--

/*!40000 ALTER TABLE `biodata` DISABLE KEYS */;
/*!40000 ALTER TABLE `biodata` ENABLE KEYS */;


--
-- Definition of table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `department_code` varchar(20) DEFAULT NULL,
  `department_name` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`,`department_code`,`department_name`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'HR','Human Resource Dept','deni,.fahri',NULL,NULL,NULL,0),
 (2,'FINANCE','Finanance','deni.fahri',NULL,NULL,NULL,0),
 (3,'IT','Information Technology','deni.fahri',NULL,NULL,NULL,0),
 (4,'BOD','Directorat',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Definition of table `education_level`
--

DROP TABLE IF EXISTS `education_level`;
CREATE TABLE `education_level` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `level` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3y1c37setyv5b54k069stgjpa` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `education_level`
--

/*!40000 ALTER TABLE `education_level` DISABLE KEYS */;
INSERT INTO `education_level` (`id`,`created_by`,`created_on`,`level`,`name`,`updated_by`,`updated_on`,`version`) VALUES 
 (125916676,'deni.fahri','2014-05-21 10:46:10',1,'Ibtidaiyah','deni.fahri','2014-06-03 20:42:41',5),
 (275788202,'deni.fahri','2014-06-03 20:42:27',1,'SMP','deni.fahri','2014-06-03 20:42:54',2),
 (587245498,'deni.fahri','2014-05-21 10:44:11',1,'SD',NULL,NULL,0);
/*!40000 ALTER TABLE `education_level` ENABLE KEYS */;


--
-- Definition of table `employee_type`
--

DROP TABLE IF EXISTS `employee_type`;
CREATE TABLE `employee_type` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_skiuwllqq27o0y3mttp1mhq30` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee_type`
--

/*!40000 ALTER TABLE `employee_type` DISABLE KEYS */;
INSERT INTO `employee_type` (`id`,`created_by`,`created_on`,`name`,`updated_by`,`updated_on`,`version`) VALUES 
 (304552797,'deni.fahri','2014-05-23 14:59:36','Perbantuan','deni.fahri','2014-06-03 20:58:41',2),
 (455289367,'deni.fahri','2014-05-23 14:59:30','Kontrak',NULL,NULL,0),
 (756452800,'deni.fahri','2014-05-23 14:59:43','Outsource',NULL,NULL,0);
/*!40000 ALTER TABLE `employee_type` ENABLE KEYS */;


--
-- Definition of table `family_relation`
--

DROP TABLE IF EXISTS `family_relation`;
CREATE TABLE `family_relation` (
  `id` bigint(20) NOT NULL,
  `relasi_name` varchar(60) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `family_relation`
--

/*!40000 ALTER TABLE `family_relation` DISABLE KEYS */;
INSERT INTO `family_relation` (`id`,`relasi_name`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (101914678,'Anak','deni.fahri','2014-05-21 21:42:19',NULL,NULL,0),
 (150804752,'Paman','deni.fahri','2014-05-21 21:43:07',NULL,NULL,0),
 (211301285,'Nenek','deni.fahri','2014-05-21 21:43:01',NULL,NULL,0),
 (221075506,'Bapak','deni.fahri','2014-05-21 21:42:31',NULL,NULL,0),
 (247486183,'Adik','deni.fahri','2014-05-21 21:42:39','deni.fahri','2014-06-03 21:03:27',2),
 (338102128,'Kakek','deni.fahri','2014-05-21 21:42:56',NULL,NULL,0),
 (530843594,'Ibu','deni.fahri','2014-05-21 21:42:46',NULL,NULL,0);
/*!40000 ALTER TABLE `family_relation` ENABLE KEYS */;


--
-- Definition of table `golongan_jabatan`
--

DROP TABLE IF EXISTS `golongan_jabatan`;
CREATE TABLE `golongan_jabatan` (
  `id` bigint(20) NOT NULL,
  `golongan_jabatan_code` varchar(4) DEFAULT NULL,
  `kelompok` int(11) DEFAULT NULL,
  `lembur` int(11) DEFAULT NULL,
  `pangkat_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `upated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `golongan_jabatan_code_UNIQUE` (`golongan_jabatan_code`),
  UNIQUE KEY `UK_k3ox6tva1q1ovrnpmlemcv1b4` (`golongan_jabatan_code`),
  KEY `fk_gol_pangkat_idx` (`pangkat_id`),
  CONSTRAINT `fk_gol_pangkat` FOREIGN KEY (`pangkat_id`) REFERENCES `pangkat` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `golongan_jabatan`
--

/*!40000 ALTER TABLE `golongan_jabatan` DISABLE KEYS */;
INSERT INTO `golongan_jabatan` (`id`,`golongan_jabatan_code`,`kelompok`,`lembur`,`pangkat_id`,`created_by`,`created_on`,`updated_by`,`upated_on`,`version`) VALUES 
 (1,'A',1,1,1,'deni.fahri',NULL,NULL,NULL,0),
 (2,'B',1,1,1,'deni.fahri',NULL,NULL,NULL,0),
 (3,'C',1,1,1,'deni.fahri',NULL,NULL,NULL,0),
 (4,'D',1,1,2,'deni.fahri',NULL,NULL,NULL,0),
 (5,'E',1,1,2,'deni.fahri',NULL,NULL,NULL,0),
 (6,'F',1,0,4,'deni.fahri',NULL,NULL,NULL,0),
 (7,'H',2,0,3,'deni.fahri',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `golongan_jabatan` ENABLE KEYS */;


--
-- Definition of table `grade`
--

DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` bigint(20) NOT NULL,
  `grade_name` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `grade_name_UNIQUE` (`grade_name`),
  UNIQUE KEY `UK_aj2bu738l4tfnobnemkqjshmp` (`grade_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grade`
--

/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` (`id`,`grade_name`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'Kelompok 1',NULL,NULL,NULL,NULL,0),
 (2,'Kelompok 2',NULL,NULL,NULL,NULL,0),
 (3,'Kelompok 3',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;


--
-- Definition of table `hrm_role`
--

DROP TABLE IF EXISTS `hrm_role`;
CREATE TABLE `hrm_role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(45) DEFAULT NULL,
  `description` text,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`),
  UNIQUE KEY `UK_sqqwoh66mnifl4mv7wa7c0t4d` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hrm_role`
--

/*!40000 ALTER TABLE `hrm_role` DISABLE KEYS */;
INSERT INTO `hrm_role` (`id`,`role_name`,`description`,`created_by`,`updated_by`,`created_on`,`updated_on`,`version`) VALUES 
 (204407722,'USER_HR_ROLE','Role for user HR Department','deni.fahri','deni.fahri','2014-05-13 10:53:07','2014-06-03 20:26:11',3),
 (796032181,'ADMINISTRATOR_ROLE','Role for everything','deni.fahri','deni.fahri','2014-05-13 10:53:36','2014-05-15 06:09:09',1),
 (233964018660,'SUPERVISOR_HR_ROLE','Role for super user HR','master.admin','deni.fahri','2014-02-26 14:29:21','2014-05-15 06:04:58',1),
 (535462647392,'SUPERVISOR_FINANCE_ROLE','Role for supervisor Finance','master.admin','deni.fahri','2014-02-26 14:30:34','2014-05-15 06:09:02',1),
 (549637095202,'USER_FINANCE_ROLE','Role for user Finance','master.admin','deni.fahri','2014-02-26 14:29:44','2014-05-15 06:04:49',1);
/*!40000 ALTER TABLE `hrm_role` ENABLE KEYS */;


--
-- Definition of table `hrm_user`
--

DROP TABLE IF EXISTS `hrm_user`;
CREATE TABLE `hrm_user` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `password` text,
  `is_active` int(11) DEFAULT NULL,
  `is_lock` int(11) DEFAULT NULL,
  `is_expired` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `PHONE_NUMBER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `UK_g0vxnh34i4oc7j3dx7e3st75y` (`user_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  UNIQUE KEY `UK_rpqx9opaxjyuox8tstol6qa3y` (`email_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hrm_user`
--

/*!40000 ALTER TABLE `hrm_user` DISABLE KEYS */;
INSERT INTO `hrm_user` (`id`,`user_id`,`real_name`,`email_address`,`password`,`is_active`,`is_lock`,`is_expired`,`created_by`,`updated_by`,`created_on`,`updated_on`,`version`,`PHONE_NUMBER`) VALUES 
 (1,'deni.fahri','Deni Husni FR','rizal2_dhfr@yahoo.com','f89a34d294751b4bb49587b4419da31837209f64d23604844f166bae02e25259',1,0,0,NULL,'deni.fahri',NULL,'2014-05-23 14:29:03',8,'+64287887051607'),
 (322967931517,'rizky.maulana','Rizky Maulana','rizky.maulana@incubatechnology.com','f798b5e63f7c48efcc63103f79288eed9a3972c1f9659670ce9c2a2a8531aa97',1,0,0,'deni.fahri',NULL,'2014-05-21 09:37:40',NULL,0,'+99989898'),
 (330221900492,'master.admin','Master Admin','no_replayoptimahr@gmail.com','2dcead6f684f4b39e829b8655be965b5b31f4e065fc422de3cc18617821c1879',1,0,0,'deni.fahri',NULL,'2014-05-19 19:35:42',NULL,0,'+123456789'),
 (716958501133,'hanif.abyan','Hanif Abyan A','deni.fahri@incubatechnology.com','f89a34d294751b4bb49587b4419da31837209f64d23604844f166bae02e25259',1,0,0,'deni.fahri','rizal2_dhfr@yahoo.com','2014-05-19 17:09:03','2014-05-19 19:51:01',4,'+343433434');
/*!40000 ALTER TABLE `hrm_user` ENABLE KEYS */;


--
-- Definition of table `hrm_user_role`
--

DROP TABLE IF EXISTS `hrm_user_role`;
CREATE TABLE `hrm_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_user_idx` (`role_id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `hrm_role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `hrm_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hrm_user_role`
--

/*!40000 ALTER TABLE `hrm_user_role` DISABLE KEYS */;
INSERT INTO `hrm_user_role` (`user_id`,`role_id`,`description`) VALUES 
 (1,204407722,NULL),
 (1,796032181,NULL),
 (1,233964018660,NULL),
 (1,535462647392,NULL),
 (1,549637095202,NULL),
 (322967931517,796032181,NULL),
 (330221900492,796032181,NULL),
 (716958501133,204407722,NULL);
/*!40000 ALTER TABLE `hrm_user_role` ENABLE KEYS */;


--
-- Definition of table `leave`
--

DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `id` bigint(20) NOT NULL,
  `aproval_level` int(11) NOT NULL,
  `availability` int(11) NOT NULL,
  `availabity_at_specific_date` datetime DEFAULT NULL,
  `backward_period_limit` int(11) DEFAULT NULL,
  `calculation` int(11) NOT NULL,
  `code` varchar(4) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `day_type` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `effective_from` int(11) NOT NULL,
  `end_of_period` int(11) NOT NULL,
  `end_of_period_month` int(11) DEFAULT NULL,
  `is_allowed_minus` tinyint(1) NOT NULL,
  `is_only_once_per_employee` tinyint(1) NOT NULL,
  `is_quota_reduction` tinyint(1) NOT NULL,
  `is_taking_leave_to_next_year` tinyint(1) NOT NULL,
  `max_allowed_minus` int(11) DEFAULT NULL,
  `max_taking_leave_to_next_year` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `period_base` int(11) NOT NULL,
  `submitted_limit` int(11) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `attendance_status_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3th8e1s79atpybm2chwaiue00` (`code`),
  UNIQUE KEY `UK_mk9o5veq7yssiy03bhpw0kapw` (`name`),
  KEY `FK_nkkwosnwq96snogqh5pspunwa` (`attendance_status_id`),
  CONSTRAINT `FK_nkkwosnwq96snogqh5pspunwa` FOREIGN KEY (`attendance_status_id`) REFERENCES `attendance_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `leave`
--

/*!40000 ALTER TABLE `leave` DISABLE KEYS */;
/*!40000 ALTER TABLE `leave` ENABLE KEYS */;


--
-- Definition of table `login_history`
--

DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history` (
  `id` bigint(20) NOT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `LOGIN_DATE` datetime DEFAULT NULL,
  `LOG_OUT_DATE` datetime DEFAULT NULL,
  `IP_ADDRESS` varchar(45) DEFAULT NULL,
  `LANGUANGE` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_login_idx` (`USER_ID`),
  CONSTRAINT `FK_USER_ID_LOG` FOREIGN KEY (`USER_ID`) REFERENCES `hrm_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_history`
--

/*!40000 ALTER TABLE `login_history` DISABLE KEYS */;
INSERT INTO `login_history` (`id`,`USER_ID`,`LOGIN_DATE`,`LOG_OUT_DATE`,`IP_ADDRESS`,`LANGUANGE`) VALUES 
 (100689152058435,1,'2014-05-18 14:45:59',NULL,'127.0.0.1','in'),
 (100844021449445,1,'2014-05-23 10:24:57',NULL,'127.0.0.1','in'),
 (101016886896660,1,'2014-06-03 15:10:37',NULL,'127.0.0.1','in'),
 (101185155317140,1,'2014-05-10 07:07:07',NULL,'127.0.0.1','in'),
 (101574105301105,1,'2014-06-02 15:50:19',NULL,'127.0.0.1','in'),
 (103753838424669,1,'2014-05-11 08:33:21',NULL,'127.0.0.1','in'),
 (103999668193864,1,'2014-05-23 14:59:00',NULL,'127.0.0.1','in'),
 (104593811787042,1,'2014-05-12 21:03:45',NULL,'127.0.0.1','in'),
 (105598173096694,1,'2014-06-02 15:54:05',NULL,'127.0.0.1','in'),
 (107687262980568,1,'2014-06-04 14:17:10',NULL,'127.0.0.1','in'),
 (108730379565413,1,'2014-05-11 11:50:24',NULL,'127.0.0.1','in'),
 (109497809770765,1,'2014-05-19 17:51:33','2014-05-19 17:51:45','127.0.0.1','in'),
 (109665462815515,1,'2014-05-27 15:08:27',NULL,'127.0.0.1','in'),
 (112721758907527,1,'2014-06-06 15:10:26',NULL,'127.0.0.1','in'),
 (113794436826676,1,'2014-06-06 14:38:58',NULL,'127.0.0.1','in'),
 (114474398480368,1,'2014-05-21 11:35:57',NULL,'127.0.0.1','in'),
 (114698757453657,1,'2014-05-12 17:13:00',NULL,'127.0.0.1','in'),
 (115085210868302,1,'2014-05-22 13:19:47',NULL,'127.0.0.1','in'),
 (117372397650850,1,'2014-05-14 13:01:47','2014-05-14 13:10:42','127.0.0.1','in'),
 (117737583838921,1,'2014-06-06 13:36:20',NULL,'127.0.0.1','in'),
 (117896364407164,1,'2014-06-03 20:41:02',NULL,'127.0.0.1','in'),
 (118324346172034,1,'2014-06-04 20:26:43',NULL,'127.0.0.1','in'),
 (118349900851645,1,'2014-05-17 06:51:22','2014-05-17 06:53:15','127.0.0.1','en'),
 (118986521701414,1,'2014-05-10 21:27:44',NULL,'127.0.0.1','in'),
 (119737033462211,1,'2014-05-29 09:08:43',NULL,'127.0.0.1','in'),
 (120070907366463,1,'2014-05-14 13:52:44',NULL,'127.0.0.1','in'),
 (124757215006189,1,'2014-05-19 13:36:52',NULL,'127.0.0.1','in'),
 (125558169426041,1,'2014-06-04 10:06:46',NULL,'127.0.0.1','in'),
 (125618314805088,1,'2014-05-14 15:05:04','2014-05-14 15:16:04','127.0.0.1','in'),
 (127471070535697,1,'2014-05-13 19:36:33',NULL,'127.0.0.1','in'),
 (128320092435622,1,'2014-05-14 19:57:31','2014-05-14 19:58:43','127.0.0.1','in'),
 (130146446981511,1,'2014-05-09 15:02:07',NULL,'127.0.0.1','in'),
 (132443824884986,1,'2014-05-12 10:15:34',NULL,'127.0.0.1','in'),
 (133339335801184,1,'2014-05-11 08:51:00',NULL,'127.0.0.1','in'),
 (133521166586595,1,'2014-05-11 18:31:20',NULL,'127.0.0.1','in'),
 (134292645558762,1,'2014-05-11 18:52:17',NULL,'127.0.0.1','in'),
 (134909957078296,1,'2014-05-30 09:39:41',NULL,'127.0.0.1','in'),
 (135185122753942,1,'2014-05-23 09:54:09',NULL,'127.0.0.1','in'),
 (137152178687059,1,'2014-06-03 20:57:33',NULL,'127.0.0.1','in'),
 (138303093109396,1,'2014-06-05 21:56:47','2014-06-05 21:57:20','127.0.0.1','in'),
 (138508005054264,1,'2014-05-29 08:30:03','2014-05-29 08:35:12','127.0.0.1','in'),
 (140106870415173,1,'2014-06-02 15:45:16',NULL,'127.0.0.1','in'),
 (140745332202803,1,'2014-05-12 11:16:59',NULL,'127.0.0.1','in'),
 (141800758641189,1,'2014-05-31 10:09:36','2014-05-31 10:10:03','127.0.0.1','in'),
 (143284082441127,1,'2014-05-10 21:29:29',NULL,'127.0.0.1','in'),
 (143589383656340,1,'2014-05-17 07:13:43','2014-05-17 07:49:01','127.0.0.1','in'),
 (144034868476317,1,'2014-05-16 10:30:29',NULL,'127.0.0.1','in'),
 (145382971928247,1,'2014-05-09 15:17:26',NULL,'127.0.0.1','in'),
 (147778701379420,1,'2014-05-19 18:50:37','2014-05-19 18:52:04','127.0.0.1','in'),
 (148114127971787,1,'2014-05-14 19:51:29','2014-05-14 19:52:42','127.0.0.1','in'),
 (148124780186072,1,'2014-05-22 12:18:49',NULL,'127.0.0.1','in'),
 (148516137371719,1,'2014-05-09 14:47:31',NULL,'127.0.0.1','in'),
 (148948382418791,1,'2014-05-22 20:55:44',NULL,'127.0.0.1','in'),
 (150860565818443,1,'2014-05-30 18:50:58','2014-05-30 18:54:39','127.0.0.1','in'),
 (150978948341996,1,'2014-05-15 09:42:20',NULL,'127.0.0.1','in'),
 (151575547361765,1,'2014-05-12 10:44:15','2014-05-12 10:46:14','127.0.0.1','in'),
 (152300412157683,1,'2014-05-16 13:45:59',NULL,'127.0.0.1','in'),
 (154533493174590,1,'2014-06-04 09:49:55','2014-06-04 10:06:22','127.0.0.1','in'),
 (155607507441278,1,'2014-05-14 20:01:03','2014-05-14 20:04:44','127.0.0.1','in'),
 (157555047064160,1,'2014-06-05 07:09:41','2014-06-05 07:25:01','127.0.0.1','in'),
 (158531700436971,1,'2014-05-10 21:38:57',NULL,'127.0.0.1','in'),
 (160944812413260,1,'2014-05-17 19:26:57',NULL,'127.0.0.1','in'),
 (161447070164316,1,'2014-05-18 18:15:14','2014-05-18 18:26:18','127.0.0.1','in'),
 (163826019879523,1,'2014-05-10 12:45:42','2014-05-10 13:15:19','127.0.0.1','in'),
 (164162154583054,1,'2014-06-03 20:25:46',NULL,'127.0.0.1','in'),
 (164309542971605,1,'2014-05-14 14:06:49',NULL,'127.0.0.1','in'),
 (164528755877041,1,'2014-05-22 21:11:32',NULL,'127.0.0.1','in'),
 (167710965618043,1,'2014-05-12 13:48:31',NULL,'127.0.0.1','in'),
 (168519028914264,1,'2014-05-12 20:23:26',NULL,'127.0.0.1','in'),
 (169196319451399,1,'2014-05-15 13:35:14',NULL,'127.0.0.1','en'),
 (169887440809869,1,'2014-05-25 15:44:43',NULL,'127.0.0.1','in'),
 (171511737365148,1,'2014-06-02 13:32:58',NULL,'127.0.0.1','in'),
 (173805550344237,1,'2014-05-22 11:37:33',NULL,'127.0.0.1','in'),
 (175627637709260,1,'2014-05-12 20:28:14',NULL,'127.0.0.1','in'),
 (175647604767952,1,'2014-05-30 10:15:11',NULL,'127.0.0.1','in'),
 (176884441296211,1,'2014-05-13 14:32:35',NULL,'127.0.0.1','in'),
 (179016215469974,1,'2014-05-24 10:21:24',NULL,'127.0.0.1','in'),
 (180255445545276,1,'2014-06-06 07:38:45',NULL,'127.0.0.1','in'),
 (181181845758241,1,'2014-05-12 20:52:17',NULL,'127.0.0.1','in'),
 (181717588998826,1,'2014-05-24 07:29:45',NULL,'127.0.0.1','in'),
 (182024306645296,1,'2014-05-10 13:29:52','2014-05-10 13:31:00','127.0.0.1','in'),
 (182318020293959,1,'2014-06-06 10:47:03',NULL,'127.0.0.1','in'),
 (184630688378312,1,'2014-05-16 10:26:23',NULL,'127.0.0.1','in'),
 (184825644949419,1,'2014-05-30 14:29:57','2014-05-30 14:55:09','127.0.0.1','en'),
 (185486415428917,1,'2014-05-20 09:19:41',NULL,'127.0.0.1','in'),
 (189109364516487,1,'2014-05-12 10:09:18','2014-05-12 10:12:31','127.0.0.1','in'),
 (190784780235741,1,'2014-05-26 09:37:51',NULL,'127.0.0.1','in'),
 (192264964333800,1,'2014-05-20 22:13:30',NULL,'127.0.0.1','in'),
 (195590033819155,1,'2014-05-20 10:54:38','2014-05-20 11:04:10','127.0.0.1','in'),
 (197976190321090,1,'2014-05-14 14:30:20',NULL,'127.0.0.1','in'),
 (198187633271417,1,'2014-05-19 14:38:22','2014-05-19 14:38:32','127.0.0.1','in'),
 (198522643863200,1,'2014-06-03 13:41:34',NULL,'127.0.0.1','in'),
 (199151049463474,1,'2014-05-24 08:27:13',NULL,'127.0.0.1','in'),
 (199340463904450,1,'2014-05-09 15:03:09',NULL,'127.0.0.1','in'),
 (201106905143102,1,'2014-05-13 11:23:33','2014-05-13 11:28:21','127.0.0.1','in'),
 (201313816905356,1,'2014-06-02 13:24:12',NULL,'127.0.0.1','en'),
 (201894344671663,1,'2014-05-18 08:43:10','2014-05-18 09:38:27','127.0.0.1','in'),
 (201920180852942,1,'2014-05-21 09:04:29',NULL,'127.0.0.1','in'),
 (205482571258156,1,'2014-06-02 16:29:16',NULL,'127.0.0.1','en'),
 (206531674879070,1,'2014-05-14 13:50:23',NULL,'127.0.0.1','in'),
 (207050672340092,1,'2014-05-16 10:04:56',NULL,'127.0.0.1','in'),
 (208509774307528,1,'2014-05-14 14:26:41',NULL,'127.0.0.1','in'),
 (209800654716871,1,'2014-05-19 19:46:21','2014-05-19 19:59:35','127.0.0.1','in'),
 (209861905645552,1,'2014-06-02 15:49:14',NULL,'127.0.0.1','in'),
 (211281261518211,1,'2014-05-29 09:19:33',NULL,'127.0.0.1','in'),
 (211623765257645,1,'2014-06-03 10:35:48',NULL,'127.0.0.1','in'),
 (212469086617683,1,'2014-05-28 14:10:09',NULL,'127.0.0.1','in'),
 (213269858950669,1,'2014-05-30 18:57:46','2014-05-30 18:57:53','127.0.0.1','in'),
 (213627086150009,1,'2014-05-22 14:09:36','2014-05-22 14:09:53','127.0.0.1','in'),
 (213905905177610,1,'2014-05-18 06:32:49','2014-05-18 06:47:50','127.0.0.1','in'),
 (213915040973855,1,'2014-05-11 16:26:48',NULL,'127.0.0.1','in'),
 (216279420701150,1,'2014-05-17 19:35:13',NULL,'127.0.0.1','in'),
 (221841024842836,1,'2014-05-22 13:11:26',NULL,'127.0.0.1','in'),
 (221863143284732,1,'2014-05-12 14:05:29',NULL,'127.0.0.1','in'),
 (221927922319269,1,'2014-05-21 12:14:21',NULL,'127.0.0.1','en'),
 (222928071639856,1,'2014-05-14 21:27:52',NULL,'127.0.0.1','in'),
 (223125698855710,1,'2014-05-11 11:52:19',NULL,'127.0.0.1','in'),
 (224902988618188,1,'2014-05-11 15:43:02','2014-05-11 15:45:36','127.0.0.1','in'),
 (225163061906138,1,'2014-06-02 16:24:20','2014-06-02 16:29:10','127.0.0.1','in'),
 (225307909986122,1,'2014-05-28 16:19:08',NULL,'127.0.0.1','in'),
 (225457261136158,1,'2014-05-11 12:10:25',NULL,'127.0.0.1','in'),
 (225946467123550,1,'2014-05-18 18:26:43','2014-05-18 18:30:36','127.0.0.1','in'),
 (226573078147297,1,'2014-05-12 09:11:20',NULL,'127.0.0.1','in'),
 (226688591323234,1,'2014-05-22 20:49:28',NULL,'127.0.0.1','in'),
 (226717840862956,1,'2014-05-20 14:20:23',NULL,'127.0.0.1','in'),
 (228094376270635,1,'2014-05-26 10:59:34',NULL,'127.0.0.1','in'),
 (228432590379624,1,'2014-05-11 14:24:19',NULL,'127.0.0.1','in'),
 (228857859684993,1,'2014-05-23 10:04:14','2014-05-23 10:11:45','127.0.0.1','in'),
 (230085327127027,1,'2014-05-12 10:04:17','2014-05-12 10:04:26','127.0.0.1','in'),
 (231380333648260,1,'2014-05-20 22:08:26',NULL,'127.0.0.1','in'),
 (232367079550649,1,'2014-05-12 10:25:54',NULL,'127.0.0.1','in'),
 (232485027018490,1,'2014-05-22 14:09:58',NULL,'127.0.0.1','en'),
 (234320065825462,1,'2014-05-23 14:28:47','2014-05-23 14:29:10','127.0.0.1','in'),
 (234575294328917,1,'2014-06-06 06:02:31',NULL,'127.0.0.1','in'),
 (235281033099580,1,'2014-05-31 06:12:28','2014-05-31 06:32:59','127.0.0.1','in'),
 (236544769092466,1,'2014-05-30 08:15:05',NULL,'127.0.0.1','in'),
 (237019778139555,1,'2014-05-21 12:47:47',NULL,'127.0.0.1','in'),
 (239687756403457,1,'2014-05-21 17:10:00',NULL,'127.0.0.1','in'),
 (239884397739938,1,'2014-06-02 15:51:08',NULL,'127.0.0.1','in'),
 (239926986048274,1,'2014-05-15 13:15:39',NULL,'127.0.0.1','in'),
 (240498836404324,1,'2014-05-10 13:31:29','2014-05-10 13:32:13','127.0.0.1','in'),
 (241799887980749,1,'2014-06-02 10:04:02',NULL,'127.0.0.1','in'),
 (242883915401326,1,'2014-05-11 12:30:17',NULL,'127.0.0.1','in'),
 (244236387293553,1,'2014-06-02 16:48:06',NULL,'127.0.0.1','in'),
 (244613437210697,1,'2014-05-10 13:16:57','2014-05-10 13:29:48','127.0.0.1','in'),
 (244768439289035,1,'2014-05-22 13:52:28',NULL,'127.0.0.1','in'),
 (245332002281320,1,'2014-05-11 15:39:09',NULL,'127.0.0.1','in'),
 (245994688631225,1,'2014-05-10 21:35:44',NULL,'127.0.0.1','in'),
 (246779644405131,1,'2014-05-20 12:34:06',NULL,'127.0.0.1','in'),
 (249095347148852,1,'2014-06-02 16:47:25',NULL,'127.0.0.1','in'),
 (249510574182504,1,'2014-05-15 20:47:51',NULL,'127.0.0.1','in'),
 (250000940540479,1,'2014-05-24 06:54:45',NULL,'127.0.0.1','in'),
 (251662903663279,1,'2014-05-21 12:19:07',NULL,'127.0.0.1','en'),
 (252808300880547,1,'2014-05-11 12:44:38',NULL,'127.0.0.1','in'),
 (253698724052045,1,'2014-05-13 22:38:14',NULL,'127.0.0.1','in'),
 (255136919013707,1,'2014-05-26 11:04:21',NULL,'127.0.0.1','in'),
 (258464844215048,1,'2014-05-30 12:58:46','2014-05-30 13:01:07','127.0.0.1','in'),
 (259431767627434,1,'2014-06-03 13:05:16',NULL,'127.0.0.1','in'),
 (260588330612440,1,'2014-06-06 12:54:53',NULL,'127.0.0.1','in'),
 (261255016499083,1,'2014-05-27 14:58:49',NULL,'127.0.0.1','in'),
 (261594194467847,1,'2014-06-05 09:38:17',NULL,'127.0.0.1','in'),
 (261741127383693,1,'2014-05-11 18:51:23',NULL,'127.0.0.1','in'),
 (263015796525478,1,'2014-05-13 11:18:10',NULL,'127.0.0.1','in'),
 (264017598833710,1,'2014-05-26 09:38:27',NULL,'127.0.0.1','in'),
 (264159261561674,1,'2014-05-14 09:43:12',NULL,'127.0.0.1','in'),
 (265587704349636,1,'2014-05-29 08:00:26',NULL,'127.0.0.1','in'),
 (270012332002688,1,'2014-06-01 17:19:25',NULL,'127.0.0.1','in'),
 (270448714541246,1,'2014-05-19 19:03:03','2014-05-19 19:03:53','127.0.0.1','in'),
 (270978167513495,1,'2014-05-22 20:41:53',NULL,'127.0.0.1','in'),
 (271794279657443,1,'2014-05-21 12:21:18',NULL,'127.0.0.1','in'),
 (271954066707578,1,'2014-05-26 12:02:01',NULL,'127.0.0.1','in'),
 (272413059950417,1,'2014-05-09 14:49:11',NULL,'127.0.0.1','in'),
 (272544141532747,1,'2014-05-11 16:29:31',NULL,'127.0.0.1','in'),
 (272833060552214,1,'2014-05-09 15:23:06',NULL,'127.0.0.1','in'),
 (272898849398073,1,'2014-05-23 09:36:11',NULL,'127.0.0.1','in'),
 (273296019614632,1,'2014-05-26 11:57:00','2014-05-26 12:01:45','127.0.0.1','in'),
 (273548884243181,1,'2014-05-28 14:42:12',NULL,'127.0.0.1','in'),
 (273591723686776,1,'2014-05-11 14:27:40',NULL,'127.0.0.1','in'),
 (275492075836674,1,'2014-05-21 10:36:19',NULL,'127.0.0.1','in'),
 (275528557719062,1,'2014-05-11 14:26:00',NULL,'127.0.0.1','in'),
 (275877688189228,1,'2014-05-20 22:39:55','2014-05-20 22:41:57','127.0.0.1','in'),
 (276171940860618,1,'2014-05-11 06:15:39',NULL,'127.0.0.1','in'),
 (278324323866513,1,'2014-05-12 10:46:23',NULL,'127.0.0.1','in'),
 (279738735886967,1,'2014-05-12 12:39:17',NULL,'127.0.0.1','in'),
 (280727120670427,1,'2014-05-11 18:52:55',NULL,'127.0.0.1','in'),
 (281204626583720,1,'2014-05-11 19:01:49',NULL,'127.0.0.1','in'),
 (281248730822172,1,'2014-05-14 20:23:20',NULL,'127.0.0.1','in'),
 (281984056054081,1,'2014-05-11 12:06:47',NULL,'127.0.0.1','in'),
 (282453200614141,1,'2014-05-13 10:29:53','2014-05-13 10:30:04','127.0.0.1','in'),
 (283232002319383,1,'2014-06-02 16:13:30',NULL,'127.0.0.1','in'),
 (283739222254432,1,'2014-05-12 11:32:51','2014-05-12 11:33:01','127.0.0.1','in'),
 (283964788014713,1,'2014-06-05 09:23:42','2014-06-05 09:36:55','127.0.0.1','in'),
 (285000811623565,1,'2014-05-27 18:26:55',NULL,'127.0.0.1','in'),
 (286227785642445,1,'2014-05-14 19:52:46','2014-05-14 19:53:04','127.0.0.1','en'),
 (286234776002137,1,'2014-05-20 22:43:30',NULL,'127.0.0.1','in'),
 (286389484934923,1,'2014-06-03 10:33:24',NULL,'127.0.0.1','in'),
 (286440948944205,1,'2014-06-05 12:37:11',NULL,'127.0.0.1','in'),
 (287138799095183,1,'2014-05-22 14:12:33',NULL,'127.0.0.1','in'),
 (287743769955762,1,'2014-05-21 09:43:46','2014-05-21 09:48:12','127.0.0.1','in'),
 (289106607946270,1,'2014-05-21 09:33:10',NULL,'127.0.0.1','in'),
 (290301034267743,1,'2014-05-19 17:04:43','2014-05-19 17:12:26','127.0.0.1','in'),
 (291131319797804,1,'2014-05-15 20:11:17',NULL,'127.0.0.1','in'),
 (292306645643899,1,'2014-05-12 20:22:27',NULL,'127.0.0.1','in'),
 (293872238925231,1,'2014-05-11 11:55:55',NULL,'127.0.0.1','in'),
 (295056833537555,1,'2014-06-03 21:03:08',NULL,'127.0.0.1','in'),
 (295530280448561,1,'2014-05-31 06:33:15',NULL,'127.0.0.1','in'),
 (296287428008703,1,'2014-05-12 20:26:00',NULL,'127.0.0.1','in'),
 (298495172498493,1,'2014-05-29 12:56:22',NULL,'127.0.0.1','in'),
 (299004940821102,1,'2014-05-23 14:20:49','2014-05-23 14:28:18','127.0.0.1','in'),
 (300781443872309,1,'2014-06-04 14:42:10',NULL,'127.0.0.1','in'),
 (300913175560598,1,'2014-05-20 09:46:38',NULL,'127.0.0.1','in'),
 (301132012002015,1,'2014-05-27 06:24:42',NULL,'127.0.0.1','in'),
 (301448144858245,1,'2014-05-11 19:00:57',NULL,'127.0.0.1','in'),
 (301584298734876,1,'2014-05-11 12:06:02',NULL,'127.0.0.1','in'),
 (301913596726926,1,'2014-05-12 20:46:07',NULL,'127.0.0.1','in'),
 (302626805353819,1,'2014-06-06 14:19:16',NULL,'127.0.0.1','in'),
 (302849661094396,1,'2014-05-29 08:27:21','2014-05-29 08:29:48','127.0.0.1','in'),
 (303677039991898,1,'2014-05-18 11:07:43',NULL,'127.0.0.1','in'),
 (305444329274192,1,'2014-05-22 11:00:35',NULL,'127.0.0.1','in'),
 (305788411904187,1,'2014-05-22 12:15:53','2014-05-22 12:16:07','127.0.0.1','in'),
 (305894194073410,1,'2014-06-02 15:40:23',NULL,'127.0.0.1','in'),
 (307330137606998,1,'2014-05-15 20:55:55',NULL,'127.0.0.1','in'),
 (307955695270543,1,'2014-05-21 11:24:52',NULL,'127.0.0.1','in'),
 (308851486811408,1,'2014-05-14 14:15:35',NULL,'127.0.0.1','in'),
 (309580750087784,1,'2014-05-15 13:22:34','2014-05-15 13:35:09','127.0.0.1','in'),
 (310191397764945,1,'2014-05-19 17:55:41',NULL,'127.0.0.1','in'),
 (311044240613985,1,'2014-05-20 13:24:17',NULL,'127.0.0.1','in'),
 (311181174664968,1,'2014-06-02 16:14:46',NULL,'127.0.0.1','in'),
 (314897156372837,1,'2014-05-19 16:58:14',NULL,'127.0.0.1','in'),
 (317822255171502,1,'2014-05-10 21:46:56','2014-05-10 22:11:11','127.0.0.1','in'),
 (319756045355112,1,'2014-06-03 10:55:55',NULL,'127.0.0.1','in'),
 (322354691367014,1,'2014-06-05 13:00:34',NULL,'127.0.0.1','in'),
 (325537348836457,1,'2014-05-20 09:55:11',NULL,'127.0.0.1','in'),
 (325598050497697,1,'2014-05-20 09:47:05',NULL,'127.0.0.1','in'),
 (325891156866418,1,'2014-05-14 15:45:24','2014-05-14 15:45:39','127.0.0.1','en'),
 (326298430900695,1,'2014-06-04 11:08:58',NULL,'127.0.0.1','in'),
 (326664819318859,1,'2014-05-27 09:42:39','2014-05-27 09:43:04','127.0.0.1','in'),
 (327193673953593,1,'2014-05-12 09:14:16',NULL,'127.0.0.1','in'),
 (328532777577319,1,'2014-05-15 20:58:26',NULL,'127.0.0.1','in'),
 (328794645221947,1,'2014-05-13 17:37:01',NULL,'127.0.0.1','in'),
 (331435658374147,1,'2014-05-11 12:40:46',NULL,'127.0.0.1','in'),
 (332004414142236,1,'2014-05-20 22:33:54','2014-05-20 22:33:56','127.0.0.1','in'),
 (332735169063277,1,'2014-05-29 07:22:45','2014-05-29 07:24:21','127.0.0.1','in'),
 (333463864464972,1,'2014-05-17 06:42:30',NULL,'127.0.0.1','en'),
 (333872185502512,1,'2014-06-04 09:47:04','2014-06-04 09:49:51','127.0.0.1','en'),
 (334062217241645,1,'2014-05-27 06:27:24',NULL,'127.0.0.1','in'),
 (334583433984863,1,'2014-05-13 17:39:59','2014-05-13 17:46:18','127.0.0.1','in'),
 (337068311795381,1,'2014-05-23 13:19:12',NULL,'127.0.0.1','in'),
 (338212813720740,1,'2014-05-16 15:48:26',NULL,'127.0.0.1','in'),
 (338423560638802,1,'2014-05-31 12:16:52',NULL,'127.0.0.1','in'),
 (339955230107892,1,'2014-06-02 13:18:41','2014-06-02 13:22:11','127.0.0.1','in'),
 (340220445982919,1,'2014-05-26 10:21:39',NULL,'127.0.0.1','in'),
 (340243478594199,1,'2014-06-05 14:20:20',NULL,'127.0.0.1','in'),
 (341171706500728,1,'2014-06-06 10:07:57',NULL,'127.0.0.1','in'),
 (341214974923765,1,'2014-05-29 09:03:13','2014-05-29 09:05:43','127.0.0.1','in'),
 (342240811346635,1,'2014-05-12 14:09:22',NULL,'127.0.0.1','in'),
 (345411984448484,1,'2014-05-11 12:27:33',NULL,'127.0.0.1','in'),
 (345667482358277,1,'2014-05-11 18:48:40',NULL,'127.0.0.1','in'),
 (346176569985274,1,'2014-05-09 15:22:19',NULL,'127.0.0.1','in'),
 (346313016621969,1,'2014-05-20 10:15:05',NULL,'127.0.0.1','in'),
 (347050154216554,1,'2014-06-05 14:33:00',NULL,'127.0.0.1','in'),
 (349941513014052,1,'2014-05-22 14:00:13',NULL,'127.0.0.1','in'),
 (350686307194760,1,'2014-05-31 06:36:50',NULL,'127.0.0.1','in'),
 (351367242847269,1,'2014-05-21 12:18:52','2014-05-21 12:19:03','127.0.0.1','in'),
 (351798424669754,1,'2014-05-22 12:19:53','2014-05-22 12:21:46','127.0.0.1','in'),
 (352345943738265,1,'2014-05-09 15:18:40',NULL,'127.0.0.1','in'),
 (356084705127330,1,'2014-05-12 10:20:38',NULL,'127.0.0.1','en'),
 (356809438363866,1,'2014-05-14 17:28:29',NULL,'127.0.0.1','in'),
 (356859348151329,1,'2014-05-22 09:05:57','2014-05-22 09:07:44','127.0.0.1','in'),
 (358099020116888,1,'2014-05-16 16:10:13',NULL,'127.0.0.1','in'),
 (360437914300703,1,'2014-06-05 14:16:36',NULL,'127.0.0.1','in'),
 (360699533482440,1,'2014-05-22 12:16:12',NULL,'127.0.0.1','en'),
 (361039425260891,1,'2014-05-24 10:15:40',NULL,'127.0.0.1','in'),
 (366344843425378,1,'2014-05-13 13:04:38',NULL,'127.0.0.1','in'),
 (368192532749601,1,'2014-05-31 11:19:04',NULL,'127.0.0.1','in'),
 (368626952924562,1,'2014-05-14 15:45:42',NULL,'127.0.0.1','in'),
 (369693612333153,1,'2014-06-04 14:57:16',NULL,'127.0.0.1','in'),
 (370606746389604,1,'2014-05-17 07:13:30','2014-05-17 07:13:39','127.0.0.1','in'),
 (370898471140637,1,'2014-05-27 06:20:14','2014-05-27 06:20:42','127.0.0.1','in'),
 (371194601320942,1,'2014-05-12 13:26:37',NULL,'127.0.0.1','in'),
 (371863826082206,1,'2014-05-13 21:16:23','2014-05-13 21:29:21','127.0.0.1','in'),
 (372101453281316,1,'2014-05-12 17:31:02',NULL,'127.0.0.1','in'),
 (373286780019134,1,'2014-05-29 08:38:01','2014-05-29 08:54:44','127.0.0.1','in'),
 (373905952860043,1,'2014-06-06 06:55:31',NULL,'127.0.0.1','in'),
 (374227011942750,1,'2014-06-05 12:07:22',NULL,'127.0.0.1','in'),
 (378432161355606,1,'2014-05-13 15:38:50',NULL,'127.0.0.1','in'),
 (379536284886417,1,'2014-05-30 13:01:23','2014-05-30 13:01:32','127.0.0.1','in'),
 (380339599579699,1,'2014-05-23 09:47:38',NULL,'127.0.0.1','in'),
 (381856825326722,1,'2014-05-14 14:10:58','2014-05-14 14:12:02','127.0.0.1','in'),
 (383119736562518,1,'2014-05-11 12:39:53',NULL,'127.0.0.1','in'),
 (384307732184768,1,'2014-05-16 15:43:27',NULL,'127.0.0.1','in'),
 (384740813055251,1,'2014-05-09 14:56:38',NULL,'127.0.0.1','in'),
 (385211997945593,1,'2014-05-22 21:04:00','2014-05-22 21:10:23','127.0.0.1','en'),
 (385667104217373,1,'2014-05-22 15:14:43','2014-05-22 15:14:52','127.0.0.1','in'),
 (390864111047257,1,'2014-05-28 14:15:22',NULL,'127.0.0.1','in'),
 (391732711861092,1,'2014-05-21 12:39:58',NULL,'127.0.0.1','in'),
 (392607738490814,1,'2014-05-22 10:19:00',NULL,'127.0.0.1','in'),
 (393087386713652,1,'2014-06-05 10:05:42',NULL,'127.0.0.1','in'),
 (394630295192044,1,'2014-05-21 12:31:21',NULL,'127.0.0.1','in'),
 (394729579156842,1,'2014-05-27 06:21:04','2014-05-27 06:24:39','127.0.0.1','in'),
 (399121447011877,1,'2014-06-06 07:45:39',NULL,'127.0.0.1','in'),
 (399877820668957,1,'2014-05-14 13:42:29',NULL,'127.0.0.1','in'),
 (402015528638209,1,'2014-05-14 14:12:07',NULL,'127.0.0.1','en'),
 (403669680234657,1,'2014-05-11 08:14:26',NULL,'127.0.0.1','in'),
 (403730237192310,1,'2014-05-31 10:10:07','2014-05-31 10:10:23','127.0.0.1','in'),
 (405439038942089,1,'2014-06-06 07:34:51',NULL,'127.0.0.1','in'),
 (405542845286562,1,'2014-05-17 08:26:50','2014-05-17 08:27:12','127.0.0.1','in'),
 (408726298666724,1,'2014-05-10 22:25:13','2014-05-10 22:27:13','127.0.0.1','in'),
 (410043177974639,1,'2014-06-04 11:22:55',NULL,'127.0.0.1','in'),
 (411488303095155,1,'2014-05-11 11:55:26','2014-05-11 11:55:42','127.0.0.1','in'),
 (412060436638928,1,'2014-05-27 14:41:08',NULL,'127.0.0.1','in'),
 (415693717175631,1,'2014-05-12 10:12:35','2014-05-12 10:14:59','127.0.0.1','en'),
 (417274158260614,1,'2014-05-19 18:31:41','2014-05-19 18:35:29','127.0.0.1','in'),
 (417983378189565,1,'2014-05-29 08:58:27',NULL,'127.0.0.1','in'),
 (418162753711504,1,'2014-05-10 10:49:28','2014-05-10 10:51:19','127.0.0.1','in'),
 (418245069410256,1,'2014-05-28 14:27:01',NULL,'127.0.0.1','in'),
 (419623465534923,1,'2014-05-28 09:39:40',NULL,'127.0.0.1','in'),
 (421134633218490,1,'2014-06-04 15:01:49','2014-06-04 15:23:17','127.0.0.1','in'),
 (421544895202778,1,'2014-05-22 12:26:48',NULL,'127.0.0.1','in'),
 (421993764713958,1,'2014-05-17 20:48:30',NULL,'127.0.0.1','in'),
 (422115783191426,1,'2014-05-29 08:37:00','2014-05-29 08:37:42','127.0.0.1','in'),
 (423938556428961,1,'2014-05-31 06:02:35','2014-05-31 06:09:18','127.0.0.1','in'),
 (426236917825858,1,'2014-05-21 10:12:48','2014-05-21 10:16:12','127.0.0.1','in'),
 (428439690638235,1,'2014-05-13 15:33:27',NULL,'127.0.0.1','in'),
 (428717955655676,1,'2014-05-10 14:08:42',NULL,'127.0.0.1','in'),
 (428965607054213,1,'2014-05-18 12:19:05',NULL,'127.0.0.1','in'),
 (429531912057286,1,'2014-05-16 09:13:56',NULL,'127.0.0.1','in'),
 (429672581079167,1,'2014-05-30 09:10:04',NULL,'127.0.0.1','in'),
 (430286231927399,1,'2014-05-14 14:32:11',NULL,'127.0.0.1','in'),
 (430343969472128,1,'2014-05-14 07:33:56',NULL,'127.0.0.1','in'),
 (430948913837030,1,'2014-06-06 13:45:33',NULL,'127.0.0.1','in'),
 (433954847049838,1,'2014-06-03 21:57:08',NULL,'127.0.0.1','in'),
 (434071447063573,1,'2014-06-05 09:59:46','2014-06-05 10:05:30','127.0.0.1','in'),
 (434234308016344,1,'2014-05-29 20:16:47',NULL,'127.0.0.1','in'),
 (438383533687887,1,'2014-05-27 12:11:36','2014-05-27 12:35:50','127.0.0.1','in'),
 (440294163849635,1,'2014-05-22 15:16:14','2014-05-22 15:17:12','127.0.0.1','in'),
 (440494301206448,1,'2014-05-15 06:07:35','2014-05-15 08:05:28','127.0.0.1','in'),
 (441660772547164,1,'2014-06-06 14:06:44',NULL,'127.0.0.1','in'),
 (442814817750712,1,'2014-05-13 10:46:33',NULL,'127.0.0.1','in'),
 (443317099640332,1,'2014-06-03 13:36:49',NULL,'127.0.0.1','in'),
 (443911269524340,1,'2014-05-26 11:16:25','2014-05-26 11:22:25','127.0.0.1','en'),
 (444237168796472,1,'2014-05-18 07:39:46',NULL,'127.0.0.1','in'),
 (446144938353266,1,'2014-05-14 13:54:35',NULL,'127.0.0.1','in'),
 (446922412524028,1,'2014-05-14 09:05:05',NULL,'127.0.0.1','in'),
 (447075708366229,1,'2014-05-20 11:04:20',NULL,'127.0.0.1','in'),
 (447398115425986,1,'2014-05-12 13:04:55',NULL,'127.0.0.1','in'),
 (450493591614932,1,'2014-05-13 15:54:17',NULL,'127.0.0.1','in'),
 (454635314738138,1,'2014-06-02 15:46:43',NULL,'127.0.0.1','in'),
 (456883041491232,1,'2014-05-22 10:13:08','2014-05-22 10:18:47','127.0.0.1','in'),
 (457216664783789,1,'2014-05-20 11:28:08',NULL,'127.0.0.1','in'),
 (458824358311731,1,'2014-05-31 11:29:39',NULL,'127.0.0.1','in'),
 (460055929939878,1,'2014-05-17 08:10:47','2014-05-17 08:24:01','127.0.0.1','in'),
 (460167892690349,1,'2014-05-11 18:59:12',NULL,'127.0.0.1','in'),
 (462813541731597,1,'2014-05-17 06:49:17','2014-05-17 06:51:14','127.0.0.1','in'),
 (464709327530954,1,'2014-05-13 10:28:04','2014-05-13 10:28:18','127.0.0.1','in'),
 (466080743862561,1,'2014-06-06 14:58:55',NULL,'127.0.0.1','in'),
 (466155147039508,1,'2014-05-10 21:26:55',NULL,'127.0.0.1','in'),
 (466839168909846,1,'2014-05-28 11:40:11',NULL,'127.0.0.1','in'),
 (466901301374065,1,'2014-06-03 13:38:24',NULL,'127.0.0.1','in'),
 (468070934712259,1,'2014-05-23 10:12:12',NULL,'127.0.0.1','in'),
 (469924716529000,1,'2014-06-04 13:25:55','2014-06-04 13:33:45','127.0.0.1','in'),
 (470029432235581,1,'2014-05-21 06:19:44','2014-05-21 06:27:35','127.0.0.1','en'),
 (470737058970350,1,'2014-06-06 15:07:24',NULL,'127.0.0.1','in'),
 (471670438144587,1,'2014-05-13 11:08:38',NULL,'127.0.0.1','in'),
 (472035406472027,1,'2014-05-13 16:18:06',NULL,'127.0.0.1','in'),
 (472190744264073,1,'2014-05-14 12:54:47',NULL,'127.0.0.1','in'),
 (472392638347686,1,'2014-05-31 11:55:24',NULL,'127.0.0.1','in'),
 (472943536240335,1,'2014-05-09 14:55:47',NULL,'127.0.0.1','in'),
 (473027533349437,1,'2014-05-11 18:47:48',NULL,'127.0.0.1','in'),
 (474766075130974,1,'2014-05-10 07:10:52',NULL,'127.0.0.1','in'),
 (475157567461947,1,'2014-06-06 10:30:49',NULL,'127.0.0.1','in'),
 (476064929949995,1,'2014-05-22 11:03:09',NULL,'127.0.0.1','in'),
 (477510739189061,1,'2014-05-18 14:33:46',NULL,'127.0.0.1','in'),
 (479933920073364,1,'2014-05-11 06:20:10',NULL,'127.0.0.1','in'),
 (480355021468998,1,'2014-05-21 09:48:23',NULL,'127.0.0.1','in'),
 (480892134099150,1,'2014-05-21 06:35:29',NULL,'127.0.0.1','en'),
 (482108710027040,1,'2014-05-12 21:02:43',NULL,'127.0.0.1','in'),
 (482606928615666,1,'2014-05-22 13:46:20',NULL,'127.0.0.1','in'),
 (482733439657813,1,'2014-05-27 12:41:58',NULL,'127.0.0.1','in'),
 (482844772693471,1,'2014-05-17 06:38:04','2014-05-17 06:42:27','127.0.0.1','in'),
 (486166754243666,1,'2014-05-12 10:04:30','2014-05-12 10:04:40','127.0.0.1','en'),
 (486647312215865,1,'2014-05-18 18:13:08','2014-05-18 18:15:03','127.0.0.1','in'),
 (487664764664083,1,'2014-05-11 11:51:36',NULL,'127.0.0.1','in'),
 (487924349284703,1,'2014-05-11 18:46:12',NULL,'127.0.0.1','in'),
 (488472497667010,1,'2014-05-17 06:45:50','2014-05-17 06:45:59','127.0.0.1','in'),
 (489194436729857,1,'2014-05-13 11:09:55',NULL,'127.0.0.1','in'),
 (489237988009224,1,'2014-05-11 18:49:50',NULL,'127.0.0.1','in'),
 (489534191312551,1,'2014-05-18 18:11:33','2014-05-18 18:12:48','127.0.0.1','in'),
 (490201751395187,1,'2014-05-11 12:42:30',NULL,'127.0.0.1','in'),
 (491165983020475,1,'2014-05-15 21:08:57',NULL,'127.0.0.1','in'),
 (497788969184491,1,'2014-05-14 07:39:34','2014-05-14 07:39:51','127.0.0.1','in'),
 (499318572274019,1,'2014-06-06 06:33:44',NULL,'127.0.0.1','in'),
 (500608383467764,1,'2014-05-26 10:15:53',NULL,'127.0.0.1','in'),
 (500668743235595,1,'2014-05-12 17:13:41',NULL,'127.0.0.1','in'),
 (502977178931298,1,'2014-05-19 19:05:00','2014-05-19 19:05:07','127.0.0.1','in'),
 (503502667632048,1,'2014-06-06 10:52:30',NULL,'127.0.0.1','in'),
 (504125303102995,1,'2014-05-11 11:49:42',NULL,'127.0.0.1','in'),
 (504511346095096,1,'2014-05-11 11:53:02',NULL,'127.0.0.1','in'),
 (504748603470158,1,'2014-05-29 08:55:37','2014-05-29 08:56:13','127.0.0.1','in'),
 (504798086255697,1,'2014-05-13 11:28:25',NULL,'127.0.0.1','en'),
 (506181864704961,1,'2014-05-20 14:16:39',NULL,'127.0.0.1','in'),
 (506182455736484,1,'2014-05-20 14:31:38',NULL,'127.0.0.1','in'),
 (506466699921065,1,'2014-06-05 11:17:42',NULL,'127.0.0.1','in'),
 (507313625628250,1,'2014-05-12 13:57:00',NULL,'127.0.0.1','in'),
 (507679210080914,1,'2014-05-24 12:07:13',NULL,'127.0.0.1','in'),
 (507796253446707,1,'2014-05-11 09:35:36','2014-05-11 09:43:31','127.0.0.1','in'),
 (508321932001537,1,'2014-05-24 12:40:43','2014-05-24 13:26:43','127.0.0.1','in'),
 (509938495470392,1,'2014-06-02 15:52:48',NULL,'127.0.0.1','in'),
 (510114221717844,1,'2014-05-12 10:06:49',NULL,'127.0.0.1','en'),
 (512157571748429,1,'2014-05-12 12:42:17','2014-05-12 12:48:02','127.0.0.1','in'),
 (514241556671585,1,'2014-05-12 12:40:49',NULL,'127.0.0.1','in'),
 (514267901616806,1,'2014-05-30 15:32:41',NULL,'127.0.0.1','in'),
 (515041148964097,1,'2014-05-13 09:43:30',NULL,'127.0.0.1','in'),
 (516023047014860,1,'2014-05-14 09:55:54',NULL,'127.0.0.1','en'),
 (516953594408859,1,'2014-05-25 18:44:43',NULL,'127.0.0.1','in'),
 (517795868345752,1,'2014-05-10 13:32:26','2014-05-10 14:08:38','127.0.0.1','in'),
 (518489799096668,1,'2014-05-23 13:21:05','2014-05-23 13:21:09','127.0.0.1','in'),
 (519554736918820,1,'2014-05-11 16:28:11',NULL,'127.0.0.1','in'),
 (519857219918616,1,'2014-06-05 21:59:07','2014-06-05 22:06:50','127.0.0.1','in'),
 (519943598905167,1,'2014-05-12 14:11:04',NULL,'127.0.0.1','in'),
 (520090784502692,1,'2014-05-18 07:32:43','2014-05-18 07:39:36','127.0.0.1','in'),
 (520638567051814,1,'2014-05-11 12:16:30',NULL,'127.0.0.1','in'),
 (521527553278980,1,'2014-06-06 15:16:14',NULL,'127.0.0.1','in'),
 (522040825240750,1,'2014-05-19 13:35:58','2014-05-19 13:36:44','127.0.0.1','in'),
 (522180753275625,1,'2014-05-22 20:57:51',NULL,'127.0.0.1','in'),
 (522420008191701,1,'2014-05-20 09:29:17','2014-05-20 09:37:05','127.0.0.1','in'),
 (523776866620884,1,'2014-05-22 14:15:26',NULL,'127.0.0.1','in'),
 (524159492038177,1,'2014-05-15 20:04:49',NULL,'127.0.0.1','in'),
 (524544434685244,1,'2014-05-24 07:08:49',NULL,'127.0.0.1','in'),
 (524933936631364,1,'2014-05-11 11:54:51','2014-05-11 11:55:00','127.0.0.1','in'),
 (525122725361808,1,'2014-05-12 21:01:54',NULL,'127.0.0.1','in'),
 (526933498964100,1,'2014-05-12 11:18:32','2014-05-12 11:26:32','127.0.0.1','in'),
 (528282504744362,1,'2014-05-27 14:30:08',NULL,'127.0.0.1','in'),
 (530411475699559,1,'2014-05-30 14:55:14','2014-05-30 15:00:32','127.0.0.1','in'),
 (530478554908016,1,'2014-05-22 15:14:56','2014-05-22 15:16:10','127.0.0.1','en'),
 (532114152154398,1,'2014-05-25 18:32:13',NULL,'127.0.0.1','in'),
 (532595341104343,1,'2014-05-30 15:00:45',NULL,'127.0.0.1','in'),
 (534285212217517,1,'2014-06-05 09:41:00','2014-06-05 09:59:14','127.0.0.1','in'),
 (536567638651094,1,'2014-05-10 21:31:27',NULL,'127.0.0.1','in'),
 (536681416277410,1,'2014-05-16 10:12:07',NULL,'127.0.0.1','in'),
 (538059332751321,1,'2014-05-28 10:13:15',NULL,'127.0.0.1','in'),
 (538181441934121,1,'2014-06-06 12:59:10',NULL,'127.0.0.1','in'),
 (538368435892586,1,'2014-05-26 13:06:13',NULL,'127.0.0.1','in'),
 (538523630759399,1,'2014-05-21 12:30:09',NULL,'127.0.0.1','in'),
 (543357562782232,1,'2014-06-03 13:26:40',NULL,'127.0.0.1','in'),
 (544243229415991,1,'2014-05-29 07:33:57','2014-05-29 07:37:43','127.0.0.1','in'),
 (545008400812398,1,'2014-05-21 09:11:15','2014-05-21 09:12:14','127.0.0.1','in'),
 (545113187356755,1,'2014-06-06 10:20:37',NULL,'127.0.0.1','in'),
 (545493128545623,1,'2014-05-12 20:18:15','2014-05-12 20:22:01','127.0.0.1','in'),
 (545541582446695,1,'2014-05-10 22:29:27',NULL,'127.0.0.1','in'),
 (545591270084274,330221900492,'2014-05-21 09:12:19','2014-05-21 09:12:26','127.0.0.1','in'),
 (545892464243713,1,'2014-05-21 10:03:27',NULL,'127.0.0.1','in'),
 (547205523837004,1,'2014-06-02 16:45:21',NULL,'127.0.0.1','in'),
 (547430659102921,1,'2014-05-25 18:38:47',NULL,'127.0.0.1','in'),
 (547950436943806,1,'2014-05-24 07:44:02',NULL,'127.0.0.1','in'),
 (548521159234682,1,'2014-05-22 07:08:48','2014-05-22 07:28:43','127.0.0.1','in'),
 (550810070808462,1,'2014-05-29 07:38:05','2014-05-29 08:00:21','127.0.0.1','in'),
 (552384320946316,1,'2014-05-12 16:42:18',NULL,'127.0.0.1','in'),
 (552760241356880,1,'2014-05-11 18:57:24',NULL,'127.0.0.1','in'),
 (554340472906419,1,'2014-05-23 09:47:04','2014-05-23 09:47:19','127.0.0.1','in'),
 (554671735343163,1,'2014-05-26 09:29:42','2014-05-26 09:36:37','127.0.0.1','in'),
 (555095579298023,1,'2014-05-14 15:37:10','2014-05-14 15:45:19','127.0.0.1','in'),
 (555578532982185,1,'2014-05-31 10:51:54',NULL,'127.0.0.1','in'),
 (555674756035304,1,'2014-05-23 10:32:27',NULL,'127.0.0.1','in'),
 (556834663571354,1,'2014-05-22 14:30:08',NULL,'127.0.0.1','in'),
 (557971374545288,1,'2014-05-17 06:29:33','2014-05-17 06:34:00','127.0.0.1','in'),
 (558301134477812,1,'2014-05-31 13:42:33',NULL,'127.0.0.1','in'),
 (558306567178389,1,'2014-05-15 08:05:31','2014-05-15 08:09:59','127.0.0.1','in'),
 (559222960559687,1,'2014-05-30 14:24:46','2014-05-30 14:29:52','127.0.0.1','in'),
 (561746417664072,1,'2014-05-12 10:42:31',NULL,'127.0.0.1','in'),
 (562042407725378,1,'2014-05-11 12:34:33',NULL,'127.0.0.1','in'),
 (562778324674821,1,'2014-05-12 21:07:02',NULL,'127.0.0.1','in'),
 (564146279822431,330221900492,'2014-05-21 09:12:39','2014-05-21 09:13:59','127.0.0.1','in'),
 (564410564051005,1,'2014-06-03 14:46:42',NULL,'127.0.0.1','in'),
 (565856652073693,1,'2014-05-13 10:28:22',NULL,'127.0.0.1','en'),
 (566798881568667,330221900492,'2014-05-19 19:36:42','2014-05-19 19:37:00','127.0.0.1','in'),
 (568670321255761,1,'2014-05-18 18:32:36','2014-05-18 18:49:21','127.0.0.1','in'),
 (569347357146928,1,'2014-05-27 10:55:54',NULL,'127.0.0.1','in'),
 (573189905415730,1,'2014-06-05 09:37:34','2014-06-05 09:38:13','127.0.0.1','en'),
 (573222277053411,1,'2014-05-19 14:38:52',NULL,'127.0.0.1','en'),
 (573874982319040,1,'2014-05-13 11:35:52',NULL,'127.0.0.1','in'),
 (575053069998553,1,'2014-05-18 10:45:25','2014-05-18 10:57:34','127.0.0.1','in'),
 (575696208303545,1,'2014-05-31 14:57:22',NULL,'127.0.0.1','in'),
 (578585392456699,1,'2014-05-23 14:49:46',NULL,'127.0.0.1','in'),
 (579139304190906,1,'2014-05-31 10:10:27','2014-05-31 10:11:41','127.0.0.1','in'),
 (579575220305428,1,'2014-05-21 13:35:28',NULL,'127.0.0.1','in'),
 (581485234700162,1,'2014-06-06 10:14:52','2014-06-06 10:20:12','127.0.0.1','in'),
 (581988233061347,1,'2014-05-11 18:53:55','2014-05-11 18:54:12','127.0.0.1','in'),
 (582716974100695,1,'2014-05-17 07:58:23','2014-05-17 07:58:29','127.0.0.1','in'),
 (582845769994296,1,'2014-05-11 13:48:38',NULL,'127.0.0.1','in'),
 (583251885340940,1,'2014-05-10 21:36:50',NULL,'127.0.0.1','in'),
 (585538805853642,1,'2014-05-21 10:16:16',NULL,'127.0.0.1','en'),
 (585991387475046,1,'2014-05-31 10:12:46','2014-05-31 10:13:54','127.0.0.1','in'),
 (588266552502149,1,'2014-05-13 09:11:29',NULL,'127.0.0.1','in'),
 (588764771418732,1,'2014-05-13 10:30:08',NULL,'127.0.0.1','en'),
 (589810233056613,1,'2014-05-11 14:32:35',NULL,'127.0.0.1','in'),
 (590652647126246,1,'2014-06-05 06:55:07','2014-06-05 06:58:12','127.0.0.1','in'),
 (591565798417536,1,'2014-05-29 09:17:28','2014-05-29 09:18:34','127.0.0.1','in'),
 (591768453735274,1,'2014-05-09 15:24:38',NULL,'127.0.0.1','in'),
 (592505405371868,1,'2014-05-17 06:57:58','2014-05-17 07:13:20','127.0.0.1','in'),
 (595192940632058,1,'2014-06-05 14:23:26',NULL,'127.0.0.1','in'),
 (595273200454359,1,'2014-05-31 14:24:56',NULL,'127.0.0.1','in'),
 (596711848986142,1,'2014-05-27 18:54:57',NULL,'127.0.0.1','in'),
 (599236093780150,1,'2014-05-22 12:26:31','2014-05-22 12:26:41','127.0.0.1','en'),
 (604871850583274,1,'2014-05-12 13:45:03',NULL,'127.0.0.1','in'),
 (604990079405378,1,'2014-05-11 12:43:56',NULL,'127.0.0.1','in'),
 (605021205149127,1,'2014-05-11 19:00:26','2014-05-11 19:00:47','127.0.0.1','in'),
 (605137229318385,1,'2014-05-10 10:47:55','2014-05-10 10:49:14','127.0.0.1','en'),
 (607433225165458,1,'2014-05-22 15:17:18',NULL,'127.0.0.1','en'),
 (610206619443284,1,'2014-06-06 13:59:32',NULL,'127.0.0.1','in'),
 (612463065795650,1,'2014-05-17 07:49:45','2014-05-17 07:55:09','127.0.0.1','in'),
 (613014567325043,1,'2014-05-16 09:26:00',NULL,'127.0.0.1','in'),
 (613336733480799,1,'2014-05-21 09:28:52',NULL,'127.0.0.1','in'),
 (614455155865476,1,'2014-05-11 06:31:16','2014-05-11 06:34:32','127.0.0.1','in'),
 (614466875515429,1,'2014-06-06 10:02:48',NULL,'127.0.0.1','in'),
 (615484993533338,1,'2014-05-28 15:18:15',NULL,'127.0.0.1','in'),
 (615783088054326,1,'2014-05-27 09:43:08',NULL,'127.0.0.1','in'),
 (616952979948831,1,'2014-05-14 15:49:23',NULL,'127.0.0.1','in'),
 (618154239248331,1,'2014-05-21 12:13:54','2014-05-21 12:14:16','127.0.0.1','in'),
 (619007191448445,716958501133,'2014-05-19 19:25:09','2014-05-19 19:25:26','127.0.0.1','in'),
 (619729210704586,1,'2014-05-09 14:39:57',NULL,'127.0.0.1','in'),
 (620765721582445,1,'2014-05-15 08:10:23',NULL,'127.0.0.1','in'),
 (620960890470618,1,'2014-05-12 11:33:05',NULL,'127.0.0.1','en'),
 (623872320320582,1,'2014-05-25 17:54:44',NULL,'127.0.0.1','in'),
 (625207287285277,1,'2014-05-22 21:16:11',NULL,'127.0.0.1','in'),
 (625733621759174,1,'2014-05-12 12:12:47',NULL,'127.0.0.1','in'),
 (626302175435949,1,'2014-05-14 20:08:14','2014-05-14 20:23:04','127.0.0.1','in'),
 (626506472970832,1,'2014-05-18 18:31:29','2014-05-18 18:32:26','127.0.0.1','in'),
 (627362961663140,1,'2014-05-27 13:37:02',NULL,'127.0.0.1','in'),
 (627615760110829,1,'2014-05-27 14:59:48',NULL,'127.0.0.1','in'),
 (628450695397032,1,'2014-05-19 19:33:54','2014-05-19 19:36:00','127.0.0.1','in'),
 (629678684123163,1,'2014-06-04 13:34:02','2014-06-04 14:17:05','127.0.0.1','in'),
 (629887890118569,1,'2014-05-22 11:06:34',NULL,'127.0.0.1','in'),
 (630459498609927,1,'2014-05-11 19:34:11','2014-05-11 19:36:20','127.0.0.1','en'),
 (630572613353648,1,'2014-06-06 10:27:02',NULL,'127.0.0.1','in'),
 (632109888715270,1,'2014-06-06 15:25:05',NULL,'127.0.0.1','in'),
 (634384784186859,1,'2014-05-27 11:19:31',NULL,'127.0.0.1','in'),
 (635696417646004,1,'2014-05-30 10:03:14','2014-05-30 10:08:49','127.0.0.1','in'),
 (635942070933857,1,'2014-06-05 06:45:23','2014-06-05 06:54:48','127.0.0.1','in'),
 (640418385236622,1,'2014-05-11 07:22:15',NULL,'127.0.0.1','in'),
 (641289679577115,1,'2014-05-30 12:50:04',NULL,'127.0.0.1','in'),
 (641699562210822,1,'2014-06-06 06:38:44',NULL,'127.0.0.1','in'),
 (641868768912094,1,'2014-06-05 14:28:58',NULL,'127.0.0.1','in'),
 (642512109028719,1,'2014-06-02 09:53:58','2014-06-02 10:01:31','127.0.0.1','in'),
 (643054812623447,1,'2014-05-12 20:57:11',NULL,'127.0.0.1','in'),
 (643767851950346,1,'2014-05-11 12:31:32',NULL,'127.0.0.1','in'),
 (645252205182498,1,'2014-05-21 21:40:53',NULL,'127.0.0.1','in'),
 (646104494783452,1,'2014-05-24 07:25:11',NULL,'127.0.0.1','in'),
 (648605008727651,1,'2014-05-30 10:09:08',NULL,'127.0.0.1','in'),
 (648938924771347,1,'2014-05-12 10:23:31','2014-05-12 10:23:46','127.0.0.1','in'),
 (649797022444215,1,'2014-05-30 10:31:18',NULL,'127.0.0.1','in'),
 (650700794791742,1,'2014-05-09 15:01:10',NULL,'127.0.0.1','in'),
 (650761276705165,1,'2014-05-21 13:46:55',NULL,'127.0.0.1','en'),
 (651367986842441,1,'2014-05-22 09:13:42',NULL,'127.0.0.1','in'),
 (651460951925683,1,'2014-05-22 11:09:16',NULL,'127.0.0.1','in'),
 (651626040521206,1,'2014-05-16 13:45:08','2014-05-16 13:45:53','127.0.0.1','in'),
 (653101551605741,1,'2014-05-19 18:42:43',NULL,'127.0.0.1','in'),
 (654417518832690,1,'2014-05-11 12:29:26','2014-05-11 12:30:02','127.0.0.1','in'),
 (655320252176739,1,'2014-05-14 13:10:51','2014-05-14 13:15:22','127.0.0.1','in'),
 (656259479847889,1,'2014-05-09 14:59:53',NULL,'127.0.0.1','in'),
 (656599413883744,1,'2014-05-10 09:22:50',NULL,'127.0.0.1','in'),
 (656736926099550,1,'2014-05-31 11:38:45',NULL,'127.0.0.1','in'),
 (659071002097526,1,'2014-05-10 16:15:39',NULL,'127.0.0.1','in'),
 (659217612501603,1,'2014-05-24 07:21:15',NULL,'127.0.0.1','in'),
 (660204862672923,1,'2014-05-30 13:56:18','2014-05-30 14:24:34','127.0.0.1','in'),
 (660250580810846,1,'2014-05-14 14:33:19',NULL,'127.0.0.1','in'),
 (661933350452101,1,'2014-05-11 12:19:10',NULL,'127.0.0.1','in'),
 (661968910300232,1,'2014-05-25 18:42:08','2014-05-25 18:44:39','127.0.0.1','in'),
 (662243391998067,1,'2014-05-12 13:10:10',NULL,'127.0.0.1','in'),
 (664203091718769,1,'2014-06-02 09:47:57',NULL,'127.0.0.1','in'),
 (664502193581251,1,'2014-06-06 06:47:06',NULL,'127.0.0.1','in'),
 (666906334776565,1,'2014-05-13 16:32:22',NULL,'127.0.0.1','in'),
 (669695425451051,1,'2014-05-11 12:35:44',NULL,'127.0.0.1','in'),
 (670027845208488,1,'2014-05-11 18:45:44','2014-05-11 18:45:59','127.0.0.1','in'),
 (671258700885866,1,'2014-05-22 07:05:08',NULL,'127.0.0.1','en'),
 (671542608253403,1,'2014-05-30 12:44:26','2014-05-30 12:45:29','127.0.0.1','in'),
 (671998968156698,1,'2014-05-11 12:43:14',NULL,'127.0.0.1','in'),
 (672061595081989,1,'2014-05-09 14:39:26',NULL,'127.0.0.1','in'),
 (673311675285807,1,'2014-05-17 06:46:03',NULL,'127.0.0.1','en'),
 (675791054783167,1,'2014-05-22 21:19:22',NULL,'127.0.0.1','in'),
 (676039942505995,1,'2014-05-12 20:59:11',NULL,'127.0.0.1','in'),
 (676897179306776,1,'2014-05-21 13:46:38','2014-05-21 13:46:51','127.0.0.1','in'),
 (679343020732784,1,'2014-05-20 09:02:32','2014-05-20 09:19:34','127.0.0.1','in'),
 (680020903394357,1,'2014-06-02 13:11:25','2014-06-02 13:18:33','127.0.0.1','in'),
 (685730259575367,1,'2014-06-02 12:16:38','2014-06-02 13:11:02','127.0.0.1','in'),
 (685802799383785,330221900492,'2014-05-21 09:14:05',NULL,'127.0.0.1','in'),
 (687375168592987,1,'2014-05-29 09:08:03','2014-05-29 09:08:24','127.0.0.1','in'),
 (691724776504616,1,'2014-05-11 11:57:21',NULL,'127.0.0.1','in'),
 (692009793898781,1,'2014-05-29 08:35:33','2014-05-29 08:36:47','127.0.0.1','in'),
 (693977433439348,1,'2014-05-18 14:12:52',NULL,'127.0.0.1','in'),
 (697592317418215,1,'2014-05-27 11:06:44',NULL,'127.0.0.1','in'),
 (697616791557888,1,'2014-05-11 18:54:23','2014-05-11 18:55:57','127.0.0.1','in'),
 (699672155205811,1,'2014-05-21 12:24:46',NULL,'127.0.0.1','in'),
 (699816320569074,1,'2014-05-12 13:39:18',NULL,'127.0.0.1','in'),
 (699867303506134,1,'2014-05-12 11:26:45',NULL,'127.0.0.1','in'),
 (700121358528721,1,'2014-05-10 21:35:10',NULL,'127.0.0.1','in'),
 (701951279303090,1,'2014-05-11 06:34:48',NULL,'127.0.0.1','in'),
 (702171504725685,1,'2014-05-31 15:00:37',NULL,'127.0.0.1','in'),
 (704882432168270,1,'2014-05-11 19:11:54','2014-05-11 19:14:14','127.0.0.1','in'),
 (706574666562413,1,'2014-05-14 15:48:45',NULL,'127.0.0.1','in'),
 (707604143106606,1,'2014-05-12 10:19:28',NULL,'127.0.0.1','in'),
 (709238462630581,1,'2014-05-12 16:08:06',NULL,'127.0.0.1','in'),
 (709309569160460,1,'2014-05-10 10:51:31',NULL,'127.0.0.1','in'),
 (710598753288459,1,'2014-05-27 11:38:19',NULL,'127.0.0.1','in'),
 (710982194118629,1,'2014-05-21 13:32:11',NULL,'127.0.0.1','in'),
 (710989524582588,1,'2014-05-31 10:54:37',NULL,'127.0.0.1','in'),
 (711250424600912,1,'2014-05-24 08:23:11',NULL,'127.0.0.1','in'),
 (712165127590271,1,'2014-05-19 19:04:15','2014-05-19 19:04:41','127.0.0.1','in'),
 (714902403145617,1,'2014-05-09 14:45:01',NULL,'127.0.0.1','in'),
 (715230337555329,1,'2014-05-19 19:25:31','2014-05-19 19:25:52','127.0.0.1','in'),
 (715587761076988,1,'2014-05-11 19:14:25',NULL,'127.0.0.1','in'),
 (715618666809677,1,'2014-05-23 14:38:01',NULL,'127.0.0.1','in'),
 (716446590775443,1,'2014-05-12 16:49:48',NULL,'127.0.0.1','in'),
 (717301429981851,1,'2014-05-11 12:33:55','2014-05-11 12:34:20','127.0.0.1','in'),
 (721047587394400,1,'2014-05-12 14:02:21',NULL,'127.0.0.1','in'),
 (722646461498636,1,'2014-05-15 05:42:49','2014-05-15 06:04:04','127.0.0.1','in'),
 (724174818476555,1,'2014-05-21 15:11:05','2014-05-21 15:11:37','127.0.0.1','in'),
 (725110219927864,1,'2014-05-14 10:00:40',NULL,'127.0.0.1','in'),
 (725732851363177,1,'2014-05-11 12:21:40',NULL,'127.0.0.1','in'),
 (725962431408914,1,'2014-05-22 14:13:25',NULL,'127.0.0.1','in'),
 (726511790471086,1,'2014-05-20 22:33:40','2014-05-20 22:33:47','127.0.0.1','in'),
 (726786490726510,1,'2014-05-24 17:36:46','2014-05-24 18:14:19','127.0.0.1','in'),
 (727760380889227,1,'2014-06-03 10:40:15',NULL,'127.0.0.1','in'),
 (728059939621818,1,'2014-05-30 12:39:59',NULL,'127.0.0.1','in'),
 (728362460035577,1,'2014-06-06 07:41:21',NULL,'127.0.0.1','in'),
 (730191746724287,1,'2014-05-30 18:54:51','2014-05-30 18:57:30','127.0.0.1','in'),
 (732842161271210,1,'2014-05-12 19:44:44',NULL,'127.0.0.1','in'),
 (733341415336751,1,'2014-05-26 16:36:34',NULL,'127.0.0.1','in'),
 (733527866612297,1,'2014-05-11 19:26:15','2014-05-11 19:34:01','127.0.0.1','in'),
 (735408306329933,1,'2014-05-12 20:49:42',NULL,'127.0.0.1','in'),
 (735604408882065,1,'2014-05-25 18:36:16',NULL,'127.0.0.1','in'),
 (736695383132348,1,'2014-05-14 14:20:46',NULL,'127.0.0.1','in'),
 (737475656841126,1,'2014-05-19 19:05:37','2014-05-19 19:24:56','127.0.0.1','en'),
 (739742739423072,1,'2014-05-21 06:12:50','2014-05-21 06:18:52','127.0.0.1','in'),
 (739971546158930,1,'2014-05-12 17:26:28',NULL,'127.0.0.1','in'),
 (740301230064671,1,'2014-05-19 18:35:39','2014-05-19 18:37:18','127.0.0.1','in'),
 (740764246704802,1,'2014-05-21 15:11:44',NULL,'127.0.0.1','en'),
 (741319975523091,1,'2014-06-04 21:28:06',NULL,'127.0.0.1','in'),
 (742524770901864,1,'2014-06-02 15:56:34',NULL,'127.0.0.1','in'),
 (742847853577371,1,'2014-05-27 10:02:08',NULL,'127.0.0.1','in'),
 (744209926678102,1,'2014-06-03 21:58:25',NULL,'127.0.0.1','in'),
 (744608182006275,1,'2014-05-18 15:52:59',NULL,'127.0.0.1','in'),
 (746851706943553,1,'2014-05-11 11:51:03','2014-05-11 11:51:17','127.0.0.1','in'),
 (746865494918411,1,'2014-05-20 09:56:17','2014-05-20 09:56:43','127.0.0.1','in'),
 (747397845278046,1,'2014-05-22 21:31:12',NULL,'127.0.0.1','in'),
 (748467135876755,1,'2014-05-26 12:57:10',NULL,'127.0.0.1','in'),
 (748760569682589,1,'2014-05-20 14:23:09',NULL,'127.0.0.1','in'),
 (750293768823372,1,'2014-05-17 20:42:22',NULL,'127.0.0.1','in'),
 (751310093199038,1,'2014-05-12 14:03:16',NULL,'127.0.0.1','in'),
 (751481250923807,1,'2014-06-03 09:21:56','2014-06-03 09:22:24','127.0.0.1','in'),
 (753527050188808,1,'2014-05-22 07:04:51','2014-05-22 07:05:03','127.0.0.1','in'),
 (753855315721938,1,'2014-06-03 12:59:00',NULL,'127.0.0.1','in'),
 (754628551359224,1,'2014-06-02 15:48:40','2014-06-02 15:48:58','127.0.0.1','in'),
 (755254397574341,1,'2014-06-05 20:56:45','2014-06-05 21:56:41','127.0.0.1','in'),
 (756802513841618,1,'2014-05-22 13:10:29','2014-05-22 13:10:39','127.0.0.1','in'),
 (757079371306292,1,'2014-05-09 14:49:59',NULL,'127.0.0.1','in'),
 (759737570906333,1,'2014-06-03 10:15:32','2014-06-03 10:26:36','127.0.0.1','in'),
 (760086342759334,1,'2014-05-22 21:01:06','2014-05-22 21:03:54','127.0.0.1','in'),
 (761286927795759,1,'2014-06-03 13:44:20',NULL,'127.0.0.1','in'),
 (762996437338712,1,'2014-05-23 09:46:34','2014-05-23 09:46:48','127.0.0.1','in'),
 (763877687300636,1,'2014-06-02 15:25:27',NULL,'127.0.0.1','in'),
 (764472669900076,1,'2014-05-29 07:24:37','2014-05-29 07:33:50','127.0.0.1','in'),
 (765240629681412,1,'2014-05-16 13:10:42',NULL,'127.0.0.1','in'),
 (766228392144343,1,'2014-05-21 10:59:28',NULL,'127.0.0.1','in'),
 (770403294929119,1,'2014-05-20 09:56:57',NULL,'127.0.0.1','in'),
 (771364521268236,1,'2014-05-14 14:50:47',NULL,'127.0.0.1','in'),
 (772364864009505,1,'2014-05-18 19:02:13','2014-05-18 19:02:45','127.0.0.1','in'),
 (772959226247813,1,'2014-05-26 10:23:59',NULL,'127.0.0.1','in'),
 (773563268541025,1,'2014-05-13 10:49:31',NULL,'127.0.0.1','in'),
 (773585598965656,1,'2014-05-29 10:32:41',NULL,'127.0.0.1','in'),
 (774193087038025,1,'2014-05-23 13:35:05',NULL,'127.0.0.1','in'),
 (774451229809206,1,'2014-05-22 13:49:06',NULL,'127.0.0.1','in'),
 (774662089260910,1,'2014-05-19 18:52:42','2014-05-19 18:52:46','127.0.0.1','in'),
 (774991081575469,1,'2014-05-11 12:32:44',NULL,'127.0.0.1','in'),
 (777259872914587,1,'2014-05-11 14:58:06',NULL,'127.0.0.1','in'),
 (779596885106399,1,'2014-05-23 13:37:11',NULL,'127.0.0.1','in'),
 (783168098354794,1,'2014-05-17 08:27:28',NULL,'127.0.0.1','in'),
 (783303989577239,1,'2014-05-14 09:18:42',NULL,'127.0.0.1','in'),
 (787411930470427,1,'2014-05-23 10:18:22','2014-05-23 10:24:42','127.0.0.1','in'),
 (788655555934359,1,'2014-05-12 13:42:02',NULL,'127.0.0.1','in'),
 (789213016831997,1,'2014-06-06 13:55:40',NULL,'127.0.0.1','in'),
 (789974592896766,1,'2014-05-10 10:42:51','2014-05-10 10:43:23','127.0.0.1','in'),
 (790044169193774,1,'2014-05-24 07:42:03',NULL,'127.0.0.1','in'),
 (790603074023218,1,'2014-05-18 06:48:07','2014-05-18 07:32:28','127.0.0.1','in'),
 (790713457276942,1,'2014-05-23 14:30:04',NULL,'127.0.0.1','in'),
 (794104993788104,1,'2014-05-10 22:11:30','2014-05-10 22:11:40','127.0.0.1','in'),
 (795207681431498,1,'2014-05-14 19:54:53','2014-05-14 19:56:07','127.0.0.1','in'),
 (795873029562444,1,'2014-05-12 16:01:13',NULL,'127.0.0.1','in'),
 (796257519490278,1,'2014-05-10 22:27:18',NULL,'127.0.0.1','in'),
 (798575625230013,1,'2014-05-14 15:16:30','2014-05-14 15:37:01','127.0.0.1','in'),
 (802148268925712,1,'2014-05-26 10:13:21',NULL,'127.0.0.1','in'),
 (802324861221661,1,'2014-05-30 19:02:39','2014-05-30 19:15:40','127.0.0.1','in'),
 (802485829543803,1,'2014-05-15 06:04:09','2014-05-15 06:07:32','127.0.0.1','en'),
 (803292115252772,1,'2014-06-06 15:19:33',NULL,'127.0.0.1','in'),
 (803481381919174,1,'2014-05-20 22:21:27','2014-05-20 22:24:53','127.0.0.1','in'),
 (805349155220393,1,'2014-05-11 11:53:48',NULL,'127.0.0.1','in'),
 (805440932709854,1,'2014-05-18 10:57:48','2014-05-18 11:07:24','127.0.0.1','in'),
 (805496126667969,1,'2014-05-27 12:36:10',NULL,'127.0.0.1','in'),
 (807055617185856,330221900492,'2014-05-19 19:37:18','2014-05-19 19:46:17','127.0.0.1','en'),
 (807497556747171,1,'2014-05-14 20:04:57','2014-05-14 20:07:55','127.0.0.1','in'),
 (810454051887672,1,'2014-05-18 09:38:32',NULL,'127.0.0.1','in'),
 (810760954198721,1,'2014-05-22 14:03:39',NULL,'127.0.0.1','in'),
 (811959755658263,1,'2014-05-20 09:37:08','2014-05-20 09:46:16','127.0.0.1','in'),
 (812853126859078,1,'2014-06-02 11:10:47',NULL,'127.0.0.1','in'),
 (812896678681804,1,'2014-05-22 13:16:20',NULL,'127.0.0.1','in'),
 (813330111733135,1,'2014-05-11 14:45:40',NULL,'127.0.0.1','in'),
 (813759597180045,1,'2014-05-21 09:36:55',NULL,'127.0.0.1','in'),
 (815302576504218,1,'2014-06-06 06:49:58',NULL,'127.0.0.1','in'),
 (817571590265666,1,'2014-05-11 12:04:48',NULL,'127.0.0.1','in'),
 (818700061213118,1,'2014-05-11 18:56:07',NULL,'127.0.0.1','in'),
 (819217867709064,1,'2014-05-29 09:16:32','2014-05-29 09:17:24','127.0.0.1','in'),
 (822878837821666,1,'2014-05-18 08:40:44','2014-05-18 08:43:00','127.0.0.1','in'),
 (823347210147130,1,'2014-05-19 13:39:52',NULL,'127.0.0.1','in'),
 (824342607101895,1,'2014-05-12 17:30:12',NULL,'127.0.0.1','in'),
 (824976077922568,1,'2014-06-06 10:57:54',NULL,'127.0.0.1','in'),
 (825060140883025,1,'2014-05-11 11:54:27','2014-05-11 11:54:39','127.0.0.1','in'),
 (825077677341708,1,'2014-06-03 10:06:08',NULL,'127.0.0.1','in'),
 (825578475763126,1,'2014-05-13 09:58:04',NULL,'127.0.0.1','in'),
 (826874644101926,1,'2014-05-17 08:24:05','2014-05-17 08:26:57','127.0.0.1','in'),
 (827126241157438,1,'2014-05-25 18:40:01',NULL,'127.0.0.1','in'),
 (827888493234039,1,'2014-05-13 13:09:44',NULL,'127.0.0.1','in'),
 (828751108474524,1,'2014-05-12 10:23:50',NULL,'127.0.0.1','en'),
 (829821718332340,1,'2014-05-13 15:43:19',NULL,'127.0.0.1','in'),
 (830424284889553,1,'2014-05-20 08:58:24',NULL,'127.0.0.1','in'),
 (830589148430238,1,'2014-05-16 10:37:57',NULL,'127.0.0.1','in'),
 (832249986968246,1,'2014-05-21 09:50:59',NULL,'127.0.0.1','in'),
 (833671912543008,1,'2014-06-06 15:21:42',NULL,'127.0.0.1','in'),
 (833700355447545,1,'2014-05-13 10:19:31',NULL,'127.0.0.1','in'),
 (834995189168510,1,'2014-05-13 15:46:31',NULL,'127.0.0.1','in'),
 (835605528586784,1,'2014-05-17 06:53:18',NULL,'127.0.0.1','in'),
 (835684596688703,1,'2014-05-17 08:27:08','2014-05-17 08:27:33','127.0.0.1','in'),
 (835847285845564,1,'2014-06-03 12:57:28',NULL,'127.0.0.1','in'),
 (836430403978997,1,'2014-06-05 11:22:24',NULL,'127.0.0.1','in'),
 (836611218259596,1,'2014-06-04 09:44:56','2014-06-04 09:47:00','127.0.0.1','in'),
 (838047545076515,1,'2014-05-13 21:29:34',NULL,'127.0.0.1','in'),
 (838504753238976,1,'2014-05-11 12:23:16',NULL,'127.0.0.1','in'),
 (838778228745652,1,'2014-05-22 15:13:04',NULL,'127.0.0.1','en'),
 (838951089979463,1,'2014-05-21 10:38:32',NULL,'127.0.0.1','in'),
 (839782708952367,1,'2014-05-22 15:12:47','2014-05-22 15:13:00','127.0.0.1','in'),
 (840052709062281,1,'2014-05-15 13:08:53',NULL,'127.0.0.1','in'),
 (840316894458739,1,'2014-05-31 10:14:07',NULL,'127.0.0.1','in'),
 (843054010680415,1,'2014-06-03 20:38:10','2014-06-03 20:40:33','127.0.0.1','in'),
 (843448997439888,1,'2014-05-31 06:09:22','2014-05-31 06:12:04','127.0.0.1','in'),
 (843726110619473,1,'2014-05-17 06:34:20','2014-05-17 06:37:42','127.0.0.1','in'),
 (844149853437653,1,'2014-05-24 10:41:12',NULL,'127.0.0.1','in'),
 (846926984417630,1,'2014-05-15 20:03:00',NULL,'127.0.0.1','in'),
 (847712477124068,1,'2014-05-22 10:58:36',NULL,'127.0.0.1','in'),
 (848020749925573,1,'2014-05-16 10:56:53',NULL,'127.0.0.1','in'),
 (848517631186275,1,'2014-05-12 12:48:20',NULL,'127.0.0.1','in'),
 (850871896803208,1,'2014-05-30 12:45:35',NULL,'127.0.0.1','en'),
 (853260096113274,1,'2014-05-26 10:37:14',NULL,'127.0.0.1','in'),
 (855049009973331,1,'2014-05-30 19:27:25',NULL,'127.0.0.1','in'),
 (855063449897406,1,'2014-05-24 08:14:50',NULL,'127.0.0.1','in'),
 (857869765439857,1,'2014-05-11 12:37:22','2014-05-11 12:37:39','127.0.0.1','in'),
 (860916121759681,1,'2014-05-14 13:15:59',NULL,'127.0.0.1','in'),
 (861091592828843,1,'2014-06-05 14:12:23',NULL,'127.0.0.1','in'),
 (862790284839442,1,'2014-05-14 17:12:24',NULL,'127.0.0.1','in'),
 (864567344883604,1,'2014-05-14 12:22:11',NULL,'127.0.0.1','in'),
 (864660924929185,1,'2014-05-10 22:11:44','2014-05-10 22:24:57','127.0.0.1','in'),
 (864792350240324,1,'2014-06-01 16:27:36',NULL,'127.0.0.1','en'),
 (865037523384581,1,'2014-05-13 14:29:56',NULL,'127.0.0.1','in'),
 (865081319349911,1,'2014-05-29 08:57:03','2014-05-29 08:58:14','127.0.0.1','in'),
 (866138338209927,1,'2014-05-13 09:53:47',NULL,'127.0.0.1','in'),
 (866601883024412,1,'2014-05-30 13:02:36','2014-05-30 13:04:57','127.0.0.1','in'),
 (866994882874788,1,'2014-05-14 19:53:06',NULL,'127.0.0.1','in'),
 (867877604441678,1,'2014-05-16 09:43:26',NULL,'127.0.0.1','in'),
 (868964906828031,1,'2014-05-11 11:45:32',NULL,'127.0.0.1','in'),
 (869285663934603,1,'2014-05-25 14:27:26',NULL,'127.0.0.1','in'),
 (870148433433894,1,'2014-05-15 20:09:43',NULL,'127.0.0.1','in'),
 (872147485573999,1,'2014-06-05 11:33:45',NULL,'127.0.0.1','in'),
 (872305566732904,1,'2014-06-04 20:55:15',NULL,'127.0.0.1','in'),
 (873985035792071,1,'2014-05-09 15:24:29',NULL,'127.0.0.1','in'),
 (874402196609162,1,'2014-05-27 15:10:32',NULL,'127.0.0.1','in'),
 (874715050224054,1,'2014-05-21 10:23:42',NULL,'127.0.0.1','in'),
 (874717038052801,1,'2014-05-20 22:42:17','2014-05-20 22:43:23','127.0.0.1','in'),
 (876052734622694,1,'2014-06-02 13:59:19',NULL,'127.0.0.1','in'),
 (876887500055028,1,'2014-05-11 12:09:41',NULL,'127.0.0.1','in'),
 (881309681379800,1,'2014-05-30 09:56:14','2014-05-30 10:03:01','127.0.0.1','in'),
 (881564270802625,1,'2014-05-14 12:57:41',NULL,'127.0.0.1','in'),
 (882126530846342,1,'2014-05-16 09:50:35',NULL,'127.0.0.1','in'),
 (882768499611244,1,'2014-05-09 14:56:47',NULL,'127.0.0.1','in'),
 (883568296118005,1,'2014-05-14 07:39:55',NULL,'127.0.0.1','en'),
 (883625711467970,1,'2014-05-12 16:39:38',NULL,'127.0.0.1','in'),
 (885599212573065,1,'2014-05-10 22:28:36',NULL,'127.0.0.1','in'),
 (886118742466998,1,'2014-05-12 20:30:11',NULL,'127.0.0.1','in'),
 (886982737760493,1,'2014-05-24 12:22:56',NULL,'127.0.0.1','in'),
 (887273619164275,1,'2014-05-13 10:52:01',NULL,'127.0.0.1','in'),
 (887835661678347,1,'2014-05-09 23:37:31',NULL,'127.0.0.1','in'),
 (888112080781917,1,'2014-05-21 11:06:28','2014-05-21 11:24:29','127.0.0.1','in'),
 (889053526449179,1,'2014-05-13 21:31:06',NULL,'127.0.0.1','in'),
 (891338384965684,1,'2014-05-11 14:53:22',NULL,'127.0.0.1','in'),
 (892263653703255,1,'2014-05-22 12:23:10',NULL,'127.0.0.1','en'),
 (892377864667048,1,'2014-05-20 12:46:21',NULL,'127.0.0.1','in'),
 (892532855113322,1,'2014-06-03 09:23:58',NULL,'127.0.0.1','in'),
 (893095963466900,1,'2014-05-12 10:06:36','2014-05-12 10:06:44','127.0.0.1','in'),
 (893466842603098,1,'2014-06-06 07:00:48',NULL,'127.0.0.1','in'),
 (893557501861390,1,'2014-06-02 13:22:20','2014-06-02 13:24:06','127.0.0.1','in'),
 (896512398934420,1,'2014-05-12 13:59:01',NULL,'127.0.0.1','in'),
 (897543995170308,1,'2014-05-11 12:26:50',NULL,'127.0.0.1','in'),
 (897910571632641,716958501133,'2014-05-19 19:26:05','2014-05-19 19:33:43','127.0.0.1','in'),
 (898981468974837,1,'2014-05-27 10:20:45',NULL,'127.0.0.1','in'),
 (899718521119678,1,'2014-05-11 12:02:55',NULL,'127.0.0.1','in'),
 (900047317567244,1,'2014-06-04 15:23:21','2014-06-04 16:00:18','127.0.0.1','en'),
 (903087328585097,1,'2014-06-03 10:27:00',NULL,'127.0.0.1','in'),
 (904221829589192,1,'2014-05-31 10:30:34',NULL,'127.0.0.1','in'),
 (905426556342833,1,'2014-06-05 14:18:47',NULL,'127.0.0.1','in'),
 (905575713476271,1,'2014-05-27 12:37:29',NULL,'127.0.0.1','in'),
 (905892148429078,1,'2014-05-09 14:08:14',NULL,'127.0.0.1','in'),
 (906797130434125,1,'2014-05-12 10:31:34',NULL,'127.0.0.1','in'),
 (907592806485628,1,'2014-05-22 14:20:23',NULL,'127.0.0.1','in'),
 (909340454608632,1,'2014-05-11 06:24:23',NULL,'127.0.0.1','in'),
 (909500299180300,1,'2014-06-06 14:14:35',NULL,'127.0.0.1','in'),
 (910143405111631,1,'2014-05-10 21:03:58',NULL,'127.0.0.1','in'),
 (911264200751485,1,'2014-05-22 07:28:59','2014-05-22 07:35:54','127.0.0.1','en'),
 (912079922850795,1,'2014-05-18 18:49:26','2014-05-18 19:02:02','127.0.0.1','in'),
 (912939967854837,1,'2014-05-12 10:37:18',NULL,'127.0.0.1','in'),
 (913068819004938,1,'2014-05-30 19:18:47','2014-05-30 19:27:10','127.0.0.1','in'),
 (913398461431533,1,'2014-05-11 15:52:22',NULL,'127.0.0.1','in'),
 (915017345153839,1,'2014-05-26 11:11:52','2014-05-26 11:16:21','127.0.0.1','in'),
 (916227112279329,1,'2014-05-27 11:47:08',NULL,'127.0.0.1','in'),
 (919171775371294,1,'2014-05-12 12:36:40',NULL,'127.0.0.1','in'),
 (920306276695202,1,'2014-05-12 20:32:16',NULL,'127.0.0.1','in'),
 (920890150924688,1,'2014-06-03 14:02:58',NULL,'127.0.0.1','in'),
 (921189732153765,1,'2014-05-18 13:22:36',NULL,'127.0.0.1','in'),
 (921848970863878,1,'2014-05-12 21:10:06',NULL,'127.0.0.1','in'),
 (922076056415487,1,'2014-05-27 12:40:21',NULL,'127.0.0.1','in'),
 (935185813150693,1,'2014-05-22 12:26:19','2014-05-22 12:26:26','127.0.0.1','in'),
 (936396786469378,1,'2014-05-11 12:01:05','2014-05-11 12:02:44','127.0.0.1','in'),
 (944512109781940,1,'2014-06-05 13:54:43',NULL,'127.0.0.1','in'),
 (961008569252840,1,'2014-05-11 09:43:36',NULL,'127.0.0.1','en'),
 (966006406857923,1,'2014-06-06 13:53:21',NULL,'127.0.0.1','in');
/*!40000 ALTER TABLE `login_history` ENABLE KEYS */;


--
-- Definition of table `pangkat`
--

DROP TABLE IF EXISTS `pangkat`;
CREATE TABLE `pangkat` (
  `id` bigint(20) NOT NULL,
  `pangkat_code` varchar(8) DEFAULT NULL,
  `pangkat_name` varchar(60) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pangkat_code_UNIQUE` (`pangkat_code`),
  UNIQUE KEY `UK_h4nbxuln1c299m79otncso0pk` (`pangkat_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pangkat`
--

/*!40000 ALTER TABLE `pangkat` DISABLE KEYS */;
INSERT INTO `pangkat` (`id`,`pangkat_code`,`pangkat_name`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'OPR','Operator','deni.fahri',NULL,NULL,NULL,0),
 (2,'ADM','Admin','deni.fahri',NULL,NULL,NULL,0),
 (3,'DIR','Direktur','deni.fahri',NULL,NULL,NULL,0),
 (4,'SPV','Supervisor','deni,fahri',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `pangkat` ENABLE KEYS */;


--
-- Definition of table `password_complexity`
--

DROP TABLE IF EXISTS `password_complexity`;
CREATE TABLE `password_complexity` (
  `id` bigint(20) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `has_number` int(11) DEFAULT NULL,
  `has_special_character` int(11) DEFAULT NULL,
  `has_upper_case` int(11) DEFAULT NULL,
  `has_lower_case` int(11) DEFAULT NULL,
  `min_character` int(11) DEFAULT NULL,
  `max_character` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `email_notification` int(11) DEFAULT NULL,
  `expired_period` int(11) DEFAULT NULL,
  `notification_period` int(11) DEFAULT NULL,
  `password_must_different` int(11) DEFAULT NULL,
  `sms_notification` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `UK_6w3eagckdrtahb48toi0ux5xn` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `password_complexity`
--

/*!40000 ALTER TABLE `password_complexity` DISABLE KEYS */;
INSERT INTO `password_complexity` (`id`,`code`,`has_number`,`has_special_character`,`has_upper_case`,`has_lower_case`,`min_character`,`max_character`,`created_by`,`updated_by`,`created_on`,`updated_on`,`version`,`email_notification`,`expired_period`,`notification_period`,`password_must_different`,`sms_notification`) VALUES 
 (77563,'passconfigcode',1,1,1,1,5,15,'master.admin','master.admin','2014-02-24 09:23:29','2014-02-24 13:36:22',4,1,6,2,1,1);
/*!40000 ALTER TABLE `password_complexity` ENABLE KEYS */;


--
-- Definition of table `password_history`
--

DROP TABLE IF EXISTS `password_history`;
CREATE TABLE `password_history` (
  `id` bigint(20) NOT NULL,
  `password` text,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` timestamp NULL DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `email_notification` int(1) DEFAULT NULL,
  `sms_notification` int(1) DEFAULT NULL,
  `request_type` varchar(45) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `list_role` varchar(255) DEFAULT NULL,
  `local_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pass_history_pass_idx` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `password_history`
--

/*!40000 ALTER TABLE `password_history` DISABLE KEYS */;
INSERT INTO `password_history` (`id`,`password`,`created_by`,`created_on`,`user_name`,`email_notification`,`sms_notification`,`request_type`,`real_name`,`email_address`,`phone_number`,`list_role`,`local_id`) VALUES 
 (153750459545,'f89a34d294751b4bb49587b4419da31837209f64d23604844f166bae02e25259','deni.fahri','2014-05-16 16:19:37','hanif.abyan',1,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343343434','[\"ADMINISTRATOR_ROLE\",\"USER_HR_ROLE\"]','in'),
 (155864934817,'bedbf561608b1ade0efb5271381f2bed8362d058e243594eb26e23874fb626e2','deni.fahri','2014-05-16 16:10:58','hanif.abyan',1,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343343434','[\"SUPERVISOR_HR_ROLE\",\"ADMINISTRATOR_ROLE\",\"USER_HR_ROLE\"]','in'),
 (226128924262,'f798b5e63f7c48efcc63103f79288eed9a3972c1f9659670ce9c2a2a8531aa97','deni.fahri','2014-05-21 09:08:37','rizky.maulana',1,-1,'user_create_new','Rizky Maulana ','rizky.maulana@incubatechnology.com','+9898989898','[\"ADMINISTRATOR_ROLE\"]','in'),
 (287256648453,'bedbf561608b1ade0efb5271381f2bed8362d058e243594eb26e23874fb626e2','deni.fahri','2014-05-16 13:49:36','hanif.abyan',1,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+83458475845485','[]','in'),
 (288333402628,'f798b5e63f7c48efcc63103f79288eed9a3972c1f9659670ce9c2a2a8531aa97','deni.fahri','2014-05-21 09:29:30','rizky.maulana',1,-1,'user_create_new','Rizky Maulana','rizky.maulana@incubatechnology.com','+998876655','[\"ADMINISTRATOR_ROLE\"]','in'),
 (448182018205,'bXrdF7V+pDaecsdKhzyu2jAdXlpTX0SC2VBt/o5EUWMe5VeGnLrabBQnUTQ3YsncatutNtTyO9hWQ1SbAV+QtAxoCqQvZf4zhoPwJluXQr4=','deni.fahri','2014-05-16 10:38:38','abyan123',0,-1,'user_create_new','Hanif Abyan A','abyan@yahoo.com','+34534435435','[]','in'),
 (451252191104,'bedbf561608b1ade0efb5271381f2bed8362d058e243594eb26e23874fb626e2','deni.fahri','2014-05-16 15:58:41','hanif.abyan',1,-1,'user_create_new','Deni Husni Fahri Rizal','deni.fahri@incubatechnology.com','+343343434','[]','in'),
 (529941105726,'h9TsY3WxnaDr4oizYZMgG3xjmXWD8aFm1tMZtvcIg0c=','deni.fahri','2014-05-21 09:11:52','rizky.maulana',0,-1,'user_create_new','Rizky Maulana','rizky.maulana@incubatechnology.com','+98765432','[\"ADMINISTRATOR_ROLE\"]','in'),
 (576054957929,'bedbf561608b1ade0efb5271381f2bed8362d058e243594eb26e23874fb626e2','deni.fahri','2014-05-16 15:49:32','hanif.abyan',1,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343343434','[]','in'),
 (596054011241,'+ZAaaeQQu85CILU2iI9h4g==','deni.fahri','2014-05-19 17:09:03','h',0,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343433434','[\"USER_HR_ROLE\"]','in'),
 (596101919743,'1OxY2IboELZ9UdOgHCKTWA==','deni.fahri','2014-05-19 19:35:42','master.admin',0,-1,'user_create_new','Master Admin','no_replayoptimahr@gmail.com','+123456789','[\"ADMINISTRATOR_ROLE\"]','in'),
 (643180875751,'bedbf561608b1ade0efb5271381f2bed8362d058e243594eb26e23874fb626e2','deni.fahri','2014-05-16 16:06:43','hanif.abyan',1,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343343434','[]','in'),
 (867540056158,'+ZAaaeQQu85CILU2iI9h4g==','deni.fahri','2014-05-19 13:40:47','hanif.abyan',0,-1,'user_create_new','Hanif Abyan A','deni.fahri@incubatechnology.com','+343423234324','[\"USER_HR_ROLE\"]','in'),
 (915954193714,'f798b5e63f7c48efcc63103f79288eed9a3972c1f9659670ce9c2a2a8531aa97','deni.fahri','2014-05-21 09:37:39','rizky.maulana',1,-1,'user_create_new','Rizky Maulana','rizky.maulana@incubatechnology.com','+99989898','[\"ADMINISTRATOR_ROLE\"]','in'),
 (962568358277,'f798b5e63f7c48efcc63103f79288eed9a3972c1f9659670ce9c2a2a8531aa97','deni.fahri','2014-05-21 09:33:36','rizky.maulana',1,-1,'user_create_new','Rizky Maulana','rizky.maulana@incubatechnology.com','+99898989898','[\"ADMINISTRATOR_ROLE\"]','in');
/*!40000 ALTER TABLE `password_history` ENABLE KEYS */;


--
-- Definition of table `physical_ability`
--

DROP TABLE IF EXISTS `physical_ability`;
CREATE TABLE `physical_ability` (
  `id` bigint(20) NOT NULL,
  `physical_ability_name` varchar(45) DEFAULT NULL,
  `scala_nilai` varchar(25) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `physical_ability_name_UNIQUE` (`physical_ability_name`),
  UNIQUE KEY `UK_ajvfux6tt2kjctb9t5jxxrg7h` (`physical_ability_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `physical_ability`
--

/*!40000 ALTER TABLE `physical_ability` DISABLE KEYS */;
/*!40000 ALTER TABLE `physical_ability` ENABLE KEYS */;


--
-- Definition of table `physical_ability_position`
--

DROP TABLE IF EXISTS `physical_ability_position`;
CREATE TABLE `physical_ability_position` (
  `physical_ability_id` bigint(20) NOT NULL,
  `position_id` bigint(20) NOT NULL,
  `skor` int(11) DEFAULT NULL,
  PRIMARY KEY (`physical_ability_id`,`position_id`),
  KEY `fk_position_idx` (`position_id`),
  CONSTRAINT `fk_physical` FOREIGN KEY (`physical_ability_id`) REFERENCES `physical_ability` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `physical_ability_position`
--

/*!40000 ALTER TABLE `physical_ability_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `physical_ability_position` ENABLE KEYS */;


--
-- Definition of table `pos_biaya`
--

DROP TABLE IF EXISTS `pos_biaya`;
CREATE TABLE `pos_biaya` (
  `id` bigint(20) NOT NULL,
  `pos_biaya_code` varchar(6) DEFAULT NULL,
  `pos_biaya_name` varchar(100) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pos_biaya_code_UNIQUE` (`pos_biaya_code`),
  UNIQUE KEY `UK_1groyt9py1wk8hu3aq0i67pix` (`pos_biaya_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pos_biaya`
--

/*!40000 ALTER TABLE `pos_biaya` DISABLE KEYS */;
INSERT INTO `pos_biaya` (`id`,`pos_biaya_code`,`pos_biaya_name`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'PGJH','Biaya Gaji','deni.fahri',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `pos_biaya` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` bigint(20) NOT NULL,
  `sk_id` bigint(20) DEFAULT NULL,
  `position_code` varchar(8) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `level` int(4) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `main_job` varchar(45) DEFAULT NULL,
  `desciprtion` text,
  `cost_center_id` bigint(20) DEFAULT NULL,
  `education_id` bigint(20) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `grade_id` bigint(20) DEFAULT NULL,
  `gol_jabatan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parent_id_idx` (`parent_id`),
  KEY `fk_deparment_id_idx` (`department_id`),
  KEY `fk_sk_position_idx` (`sk_id`),
  KEY `fk_pos_biaya_idx` (`cost_center_id`),
  KEY `fk_level_education_idx` (`education_id`),
  KEY `fk_grade_idx` (`grade_id`),
  KEY `fk_gol_jab_pos_idx` (`gol_jabatan_id`),
  CONSTRAINT `fk_deparment_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_gol_jab_pos` FOREIGN KEY (`gol_jabatan_id`) REFERENCES `golongan_jabatan` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_grade` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_pos_biaya` FOREIGN KEY (`cost_center_id`) REFERENCES `pos_biaya` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_sk_position` FOREIGN KEY (`sk_id`) REFERENCES `sk_jabatan` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` (`id`,`sk_id`,`position_code`,`parent_id`,`level`,`department_id`,`main_job`,`desciprtion`,`cost_center_id`,`education_id`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`,`grade_id`,`gol_jabatan_id`) VALUES 
 (1,1,'DIRUT',NULL,1,4,'Memingpin Perusahaan',NULL,1,8,'deni.fahri',NULL,NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;


--
-- Definition of table `proscess_to_approve`
--

DROP TABLE IF EXISTS `proscess_to_approve`;
CREATE TABLE `proscess_to_approve` (
  `id` bigint(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `description` text,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `UK_tkstvl3g0ieaq03mw31jmsqyu` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proscess_to_approve`
--

/*!40000 ALTER TABLE `proscess_to_approve` DISABLE KEYS */;
INSERT INTO `proscess_to_approve` (`id`,`code`,`description`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (299944026286798,'CREATE USER','Approval Process untuk pembuatan User','deni.fahri','2014-03-04 10:17:06',NULL,NULL,0),
 (446363571673153,'PERMIT REQUEST','Approval Process untuk permohonan izin','deni.fahri','2014-03-04 10:10:09','deni.fahri','2014-03-04 10:11:54',1),
 (588888319838653,'UPDATE USER','Aproval Process untuk updata data User','deni.fahri','2014-03-04 11:09:55',NULL,NULL,0),
 (602841193685689,'LEAP REQUEST','Approval Process Leap Request','deni.fahri','2014-03-04 10:57:55','deni.fahri','2014-03-04 11:06:06',1),
 (690040960520366,'DELETE USER','Approval Process untuk penghapusan data User','deni.fahri','2014-03-04 10:12:58','deni.fahri','2014-03-04 11:07:47',3);
/*!40000 ALTER TABLE `proscess_to_approve` ENABLE KEYS */;


--
-- Definition of table `question_group`
--

DROP TABLE IF EXISTS `question_group`;
CREATE TABLE `question_group` (
  `qid` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(60) NOT NULL,
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_group`
--

/*!40000 ALTER TABLE `question_group` DISABLE KEYS */;
INSERT INTO `question_group` (`qid`,`label`) VALUES 
 (1,'CHIP COST SIZE'),
 (2,'Module Pack'),
 (3,'Card Production'),
 (4,'CHIP COST OS'),
 (5,'FORM FACTOR'),
 (6,'CIF'),
 (7,'FOB'),
 (8,'Packaging Cost');
/*!40000 ALTER TABLE `question_group` ENABLE KEYS */;


--
-- Definition of table `religion`
--

DROP TABLE IF EXISTS `religion`;
CREATE TABLE `religion` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ra13dxctce8waokisi0kqxpm1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `religion`
--

/*!40000 ALTER TABLE `religion` DISABLE KEYS */;
INSERT INTO `religion` (`id`,`created_by`,`created_on`,`name`,`updated_by`,`updated_on`,`version`) VALUES 
 (524661840,'deni.fahri','2014-05-14 17:42:32','Kristen','deni.fahri','2014-05-21 10:53:41',2),
 (729316493,'deni.fahri','2014-05-14 17:42:09','Islam','deni.fahri',NULL,0),
 (866370844,'deni.fahri','2014-05-21 10:53:11','Budha','deni.fahri','2014-05-21 10:53:47',1);
/*!40000 ALTER TABLE `religion` ENABLE KEYS */;


--
-- Definition of table `sk_jabatan`
--

DROP TABLE IF EXISTS `sk_jabatan`;
CREATE TABLE `sk_jabatan` (
  `id` bigint(20) NOT NULL,
  `sk_nomor` varchar(45) DEFAULT NULL,
  `sk_tanggal` date DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sk_nomor_UNIQUE` (`sk_nomor`),
  UNIQUE KEY `UK_94c1vx8dpg3g1qt0brhcltfq3` (`sk_nomor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sk_jabatan`
--

/*!40000 ALTER TABLE `sk_jabatan` DISABLE KEYS */;
INSERT INTO `sk_jabatan` (`id`,`sk_nomor`,`sk_tanggal`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'KEPKOM:12/12/2013',NULL,'deni.fahri',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `sk_jabatan` ENABLE KEYS */;


--
-- Definition of table `specification_ability`
--

DROP TABLE IF EXISTS `specification_ability`;
CREATE TABLE `specification_ability` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `scale_value` varchar(255) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `option_ability` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8oe25yaacttlejsjoiofuqwig` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `specification_ability`
--

/*!40000 ALTER TABLE `specification_ability` DISABLE KEYS */;
INSERT INTO `specification_ability` (`id`,`created_by`,`created_on`,`name`,`scale_value`,`updated_by`,`updated_on`,`option_ability`,`version`) VALUES 
 (421524773,'deni.fahri','2014-05-23 09:55:48','Buta Warna Coy','0|100','deni.fahri','2014-05-24 13:12:00','Ya|Tidak',3),
 (438030633,'deni.fahri','2014-05-23 09:58:05','English Toefl','0|33|66|100',NULL,NULL,'Tidak Bisa|Basic|Advance|Expert',0);
/*!40000 ALTER TABLE `specification_ability` ENABLE KEYS */;


--
-- Definition of table `uraian_tugas`
--

DROP TABLE IF EXISTS `uraian_tugas`;
CREATE TABLE `uraian_tugas` (
  `id` bigint(20) NOT NULL,
  `position_id` bigint(20) DEFAULT NULL,
  `uraian_tugas` text,
  `faktor_nilai` double DEFAULT NULL,
  `skala_prioritas` int(11) DEFAULT NULL,
  `skala_nilai` double DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tug_position_idx` (`position_id`),
  CONSTRAINT `fk_tug_position` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `uraian_tugas`
--

/*!40000 ALTER TABLE `uraian_tugas` DISABLE KEYS */;
/*!40000 ALTER TABLE `uraian_tugas` ENABLE KEYS */;


--
-- Definition of table `wt_group_working`
--

DROP TABLE IF EXISTS `wt_group_working`;
CREATE TABLE `wt_group_working` (
  `id` bigint(20) NOT NULL,
  `code` varchar(15) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `is_periodic` tinyint(1) DEFAULT NULL,
  `working_time_perday` double DEFAULT NULL,
  `working_time_perweek` double DEFAULT NULL,
  `overtime_based_on_attendance` tinyint(1) DEFAULT NULL,
  `overtime_based_on_request` tinyint(1) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_group_working`
--

/*!40000 ALTER TABLE `wt_group_working` DISABLE KEYS */;
INSERT INTO `wt_group_working` (`id`,`code`,`name`,`begin_time`,`end_time`,`is_periodic`,`working_time_perday`,`working_time_perweek`,`overtime_based_on_attendance`,`overtime_based_on_request`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`,`is_active`) VALUES 
 (1,'KLSJ1','Kelompok Menderita','2014-06-01 00:00:00','2014-06-30 00:00:00',0,9,45,NULL,NULL,NULL,NULL,NULL,NULL,0,1),
 (2,'KLSJ2','Kelompok Menderita','2014-05-01 00:00:00','2014-05-30 00:00:00',1,9,45,NULL,NULL,NULL,NULL,NULL,NULL,0,1);
/*!40000 ALTER TABLE `wt_group_working` ENABLE KEYS */;


--
-- Definition of table `wt_holiday`
--

DROP TABLE IF EXISTS `wt_holiday`;
CREATE TABLE `wt_holiday` (
  `id` bigint(20) NOT NULL,
  `holiday_name` varchar(45) DEFAULT NULL,
  `religion_id` bigint(20) DEFAULT NULL,
  `holiday_date` date DEFAULT NULL,
  `is_every_year` int(11) DEFAULT NULL,
  `is_colective_leave` int(11) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `holiday_name_UNIQUE` (`holiday_name`),
  UNIQUE KEY `UK_ix99jfd82h5k7itgbs7m7md92` (`holiday_name`),
  KEY `fk_holiday_religion_idx` (`religion_id`),
  CONSTRAINT `fk_holiday_religion` FOREIGN KEY (`religion_id`) REFERENCES `religion` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_holiday`
--

/*!40000 ALTER TABLE `wt_holiday` DISABLE KEYS */;
INSERT INTO `wt_holiday` (`id`,`holiday_name`,`religion_id`,`holiday_date`,`is_every_year`,`is_colective_leave`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (231962758,'Isra Mi\'raj',729316493,'2014-06-19',1,1,'deni.fahri','2014-05-26 11:17:33','rizal2_dhfr@yahoo.com','2014-05-29 12:57:49',2),
 (245983335,'Kenaikan Isa Almasih',524661840,'2014-05-29',1,0,'deni.fahri','2014-05-30 09:21:57',NULL,NULL,0),
 (268717040,'Kemerdekaan RI',NULL,'2014-08-17',1,1,'deni.fahri','2014-05-26 11:17:13',NULL,NULL,0),
 (555746201,'Hari Buruh Internasional',NULL,'2014-05-01',1,0,'deni.fahri','2014-05-30 15:30:20',NULL,NULL,0),
 (739976912,'Hari Raya Waisak',866370844,'2014-05-27',1,0,'deni.fahri','2014-05-30 09:21:30',NULL,NULL,0),
 (978588124,'Tahun Baru',NULL,'2014-01-01',1,1,'deni.fahri','2014-05-26 11:16:53',NULL,NULL,0);
/*!40000 ALTER TABLE `wt_holiday` ENABLE KEYS */;


--
-- Definition of table `wt_over_time`
--

DROP TABLE IF EXISTS `wt_over_time`;
CREATE TABLE `wt_over_time` (
  `id` bigint(20) NOT NULL,
  `code` varchar(6) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `minimum_time` time DEFAULT NULL,
  `maximum_time` time DEFAULT NULL,
  `over_time_calculation` int(11) DEFAULT NULL,
  `ot_rounding` int(11) DEFAULT NULL,
  `start_time_factor` time DEFAULT NULL,
  `finish_time_factor` time DEFAULT NULL,
  `value_price` double DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `UK_gbl7jlyejg4m40p0ox1g1su34` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_over_time`
--

/*!40000 ALTER TABLE `wt_over_time` DISABLE KEYS */;
INSERT INTO `wt_over_time` (`id`,`code`,`name`,`description`,`minimum_time`,`maximum_time`,`over_time_calculation`,`ot_rounding`,`start_time_factor`,`finish_time_factor`,`value_price`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (852087493,'OT2','Lembur Sore','Lembur setelah jam kerja Pagi','02:00:00','05:10:00',1,2,'17:10:00','22:00:00',13333.88,'deni.fahri','2014-06-03 10:59:31','deni.fahri','2014-06-04 11:09:20',9);
/*!40000 ALTER TABLE `wt_over_time` ENABLE KEYS */;


--
-- Definition of table `wt_periode`
--

DROP TABLE IF EXISTS `wt_periode`;
CREATE TABLE `wt_periode` (
  `id` bigint(20) NOT NULL,
  `tahun` varchar(10) DEFAULT NULL,
  `bulan` varchar(20) DEFAULT NULL,
  `from_periode` date DEFAULT NULL,
  `until_periode` date DEFAULT NULL,
  `absen` varchar(20) DEFAULT NULL,
  `payroll_type` varchar(20) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_periode`
--

/*!40000 ALTER TABLE `wt_periode` DISABLE KEYS */;
INSERT INTO `wt_periode` (`id`,`tahun`,`bulan`,`from_periode`,`until_periode`,`absen`,`payroll_type`,`created_by`,`created_on`,`updated_by`,`updated_on`,`version`) VALUES 
 (1,'2014','5','2014-05-01','2014-05-31','Active','Void','deni.fahri',NULL,NULL,NULL,0),
 (2,'2014','6','2014-06-01','2014-06-30','Active','Void','deni.fahri',NULL,NULL,NULL,0),
 (3,'2014','7','2014-07-01','2014-07-31','Not Active','Void','deni.fahri',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `wt_periode` ENABLE KEYS */;


--
-- Definition of table `wt_schedule_shift`
--

DROP TABLE IF EXISTS `wt_schedule_shift`;
CREATE TABLE `wt_schedule_shift` (
  `id` bigint(20) NOT NULL,
  `group_working_id` bigint(20) DEFAULT NULL,
  `working_hour_id` bigint(20) DEFAULT NULL,
  `schedule_date` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_group_working_idx` (`group_working_id`),
  KEY `fk_working_hour_idx` (`working_hour_id`),
  CONSTRAINT `fk_group_working` FOREIGN KEY (`group_working_id`) REFERENCES `wt_group_working` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_working_hour` FOREIGN KEY (`working_hour_id`) REFERENCES `wt_working_hour` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_schedule_shift`
--

/*!40000 ALTER TABLE `wt_schedule_shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `wt_schedule_shift` ENABLE KEYS */;


--
-- Definition of table `wt_working_hour`
--

DROP TABLE IF EXISTS `wt_working_hour`;
CREATE TABLE `wt_working_hour` (
  `id` bigint(20) NOT NULL,
  `arrive_limit_begin` int(11) NOT NULL,
  `arrive_limit_end` int(11) NOT NULL,
  `break_finish_limit_begin` int(11) DEFAULT NULL,
  `break_finish_limit_end` int(11) DEFAULT NULL,
  `break_hour_begin` time DEFAULT NULL,
  `break_hour_end` time DEFAULT NULL,
  `break_start_limit_begin` int(11) DEFAULT NULL,
  `break_start_limit_end` int(11) DEFAULT NULL,
  `code` varchar(10) NOT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `go_home_limit_begin` int(11) NOT NULL,
  `go_home_limit_end` int(11) NOT NULL,
  `is_manage_break_time` tinyint(1) NOT NULL,
  `is_penalty_arrive_late` tinyint(1) NOT NULL,
  `is_penalty_break_finish_late` tinyint(1) DEFAULT NULL,
  `is_penalty_break_start_early` tinyint(1) DEFAULT NULL,
  `is_penalty_go_home_early` tinyint(1) NOT NULL,
  `max_hour` time NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `working_hour_begin` time NOT NULL,
  `working_hour_end` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4j45e0xrgh2ofw4j5i3w23rkl` (`code`),
  UNIQUE KEY `UK_ibyha9hjxw46xnsgrahpvgr14` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `wt_working_hour`
--

/*!40000 ALTER TABLE `wt_working_hour` DISABLE KEYS */;
INSERT INTO `wt_working_hour` (`id`,`arrive_limit_begin`,`arrive_limit_end`,`break_finish_limit_begin`,`break_finish_limit_end`,`break_hour_begin`,`break_hour_end`,`break_start_limit_begin`,`break_start_limit_end`,`code`,`created_by`,`created_on`,`description`,`go_home_limit_begin`,`go_home_limit_end`,`is_manage_break_time`,`is_penalty_arrive_late`,`is_penalty_break_finish_late`,`is_penalty_break_start_early`,`is_penalty_go_home_early`,`max_hour`,`name`,`updated_by`,`updated_on`,`version`,`working_hour_begin`,`working_hour_end`) VALUES 
 (687836707,0,10,NULL,NULL,NULL,NULL,NULL,NULL,'SHIF2','deni.fahri','2014-06-06 14:28:59','sfsdfdsf',0,-10,0,1,0,0,0,'12:00:00','Waktu Kerja Siang',NULL,NULL,0,'14:00:00','22:00:00'),
 (733981331,0,10,NULL,NULL,NULL,NULL,NULL,NULL,'SHIF1','deni.fahri','2014-06-03 09:25:30','sddsfdsf',-10,0,0,1,0,0,1,'08:00:00','Waktu kerja Pagi',NULL,NULL,0,'08:00:00','18:00:00');
/*!40000 ALTER TABLE `wt_working_hour` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
