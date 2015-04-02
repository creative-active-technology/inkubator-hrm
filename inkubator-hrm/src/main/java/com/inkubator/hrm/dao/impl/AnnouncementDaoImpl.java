package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WebGenX
 */
@Repository(value = "announcementDao")
@Lazy
public class AnnouncementDaoImpl extends IDAOImpl<Announcement> implements AnnouncementDao {

    @Override
    public Class<Announcement> getEntityClass() {
        return Announcement.class;
    }

    @Override
    public List<Announcement> getByParam(AnnouncementSearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAnnouncementByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAnnouncementByParam(AnnouncementSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAnnouncementByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAnnouncementByParam(AnnouncementSearchParameter parameter, Criteria criteria) {
        if (parameter.getAnnouncementContent() != null) {
            criteria.add(Restrictions.like("announcementContent", parameter.getAnnouncementContent(), MatchMode.ANYWHERE));
        }
        if (parameter.getSubject() != null) {
            criteria.add(Restrictions.like("subject", parameter.getSubject(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
