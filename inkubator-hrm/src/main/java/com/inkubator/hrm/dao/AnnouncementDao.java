package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author WebGenX
 */
public interface AnnouncementDao extends IDAO<Announcement> {

    public List<Announcement> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(AnnouncementSearchParameter searchParameter);

	public Announcement getEntityByPkWithDetail(Long id);
	
	public List<Announcement> getAllDataValidForGeneratingLog(Date planExecutionDate);
}
