package com.inkubator.hrm.service;
import com.inkubator.hrm.entity.AnnouncementLog;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.AnnouncementLogSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface AnnouncementLogService extends IService<AnnouncementLog>{
	
	public List<AnnouncementLog> getByParam(AnnouncementLogSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception ;
	
	public Long getTotalAnnouncementLogByParam(AnnouncementLogSearchParameter searchParameter) throws Exception;

	public List<AnnouncementLog> getAllDataEmailNotSent() throws Exception;

	public List<AnnouncementLog> getAllDataEmailNotSentByParam(Long announcementId, Date planExecutionDate) throws Exception;
	
}
