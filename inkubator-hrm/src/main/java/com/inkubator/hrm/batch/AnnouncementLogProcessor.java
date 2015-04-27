package com.inkubator.hrm.batch;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.AnnouncementService;

/**
*
* @author rizkykojek
*/
public class AnnouncementLogProcessor implements ItemProcessor<EmpData, AnnouncementLog> {

	private Announcement announcement;
	private Date planExecutionDate;
	
	public AnnouncementLogProcessor(AnnouncementService announcementService, Long announcementId) throws Exception{
		announcement = announcementService.getEntiyByPK(announcementId);
	}
	
	@Override
	public AnnouncementLog process(EmpData item) throws Exception {
		AnnouncementLog log = new AnnouncementLog();
		log.setAnnouncement(announcement);
		log.setEmpData(item);
		log.setIsAlreadyExecuted(Boolean.FALSE);
		log.setPlanExecutionDate(planExecutionDate);
		log.setCreatedBy(HRMConstant.SYSTEM_ADMIN);
		log.setCreatedOn(new Date());
		return log;
	}

	public Date getPlanExecutionDate() {
		return planExecutionDate;
	}

	public void setPlanExecutionDate(Date planExecutionDate) {
		this.planExecutionDate = planExecutionDate;
	}

}
