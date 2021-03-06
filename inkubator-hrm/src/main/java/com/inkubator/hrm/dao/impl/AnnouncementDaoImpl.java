package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;

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
    public Long getTotalByParam(AnnouncementSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAnnouncementByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAnnouncementByParam(AnnouncementSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getSubject())) {
            criteria.add(Restrictions.like("subject", parameter.getSubject(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public Announcement getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("company", FetchMode.JOIN);
		criteria.setFetchMode("announcementEmpTypes", FetchMode.JOIN);
		criteria.setFetchMode("announcementEmpTypes.employeeType", FetchMode.JOIN);
		criteria.setFetchMode("announcementGoljabs", FetchMode.JOIN);
		criteria.setFetchMode("announcementGoljabs.golonganJabatan", FetchMode.JOIN);
		criteria.setFetchMode("announcementUnits", FetchMode.JOIN);
		criteria.setFetchMode("announcementUnits.unitKerja", FetchMode.JOIN);
		return (Announcement) criteria.uniqueResult();
	}

	@Override
	public List<Announcement> getAllDataValidForGeneratingLog(Date planExecutionDate) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT announcement " +
    			"FROM Announcement AS announcement " +
    			"WHERE status = :status " +
    			"AND (timeModel = :timeDaily OR (timeModel = :timePeriod AND periodeStartDate <= :date AND periodeEndDate >= :date)) ");
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("status", HRMConstant.ANNOUNCEMENT_STATUS_APPROVED)
    			.setParameter("timeDaily", HRMConstant.ANNOUNCEMENT_TIME_DAILY)
    			.setParameter("timePeriod", HRMConstant.ANNOUNCEMENT_TIME_PERIOD)
    			.setParameter("date", planExecutionDate);
    	
		return hbm.list();
	}
}
