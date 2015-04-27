package com.inkubator.hrm.batch;

import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.hrm.service.AnnouncementLogService;

/**
*
* @author rizkykojek
*/
public class EmailingAnnouncementLogReader implements ItemReader<AnnouncementLog> {

	private List<AnnouncementLog> announcementLogs;
	
	public EmailingAnnouncementLogReader(AnnouncementLogService announcementLogService, Long announcementId, Date planExecutionDate) throws Exception{
		announcementLogs = announcementLogService.getAllDataEmailNotSentByParam(announcementId, planExecutionDate);
	}
	
	@Override
	public AnnouncementLog read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		AnnouncementLog object = null;
		
		if(announcementLogs.size()>0) {
			object = announcementLogs.remove(0);
		}
		
		return object;
	}
	
}
