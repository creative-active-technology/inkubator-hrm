package com.inkubator.hrm.batch;

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
public class EmailingAnnouncementNotSentLogReader implements ItemReader<AnnouncementLog> {

	private List<AnnouncementLog> announcementLogs;
	
	public EmailingAnnouncementNotSentLogReader(AnnouncementLogService announcementLogService) throws Exception{
		announcementLogs = announcementLogService.getAllDataEmailNotSent();
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
