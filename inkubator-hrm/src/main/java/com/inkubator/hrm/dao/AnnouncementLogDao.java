package com.inkubator.hrm.dao;
import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.AnnouncementLogSearchParameter;
/**
 *
 * @author WebGenX
 */
public interface AnnouncementLogDao extends IDAO<AnnouncementLog>{
public List<AnnouncementLog> getByParam(AnnouncementLogSearchParameter searchParameter, int firstResult, int maxResults, Order order);
public Long getTotalAnnouncementLogByParam(AnnouncementLogSearchParameter searchParameter);
}
