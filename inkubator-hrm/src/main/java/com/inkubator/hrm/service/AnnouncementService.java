package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author WebGenX
 */
public interface AnnouncementService extends IService<Announcement> {

    public List<Announcement> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalAnnouncementByParam(AnnouncementSearchParameter searchParameter) throws Exception;
}
