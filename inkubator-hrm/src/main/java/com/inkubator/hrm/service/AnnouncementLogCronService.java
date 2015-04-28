package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.AnnouncementLog;

/**
 *
 * @author rizkykojek
 */
public interface AnnouncementLogCronService {

	public void generatingAnnouncementLog() throws Exception;
	
	public void executeBatchSendingEmail(AnnouncementLog announcementLog) throws Exception;

	public void processingAllEmailNotSent() throws Exception;
	
}
