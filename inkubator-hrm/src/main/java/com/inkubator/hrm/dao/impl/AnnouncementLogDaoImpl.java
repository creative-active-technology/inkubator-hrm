package com.inkubator.hrm.dao.impl;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.datacore.dao.impl.IDAOImpl;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.AnnouncementLogDao;
import com.inkubator.hrm.web.search.AnnouncementLogSearchParameter;

/**
 * 
 * @author WebGenX
 */
@Repository(value = "announcementLogDao")
@Lazy
public class AnnouncementLogDaoImpl extends IDAOImpl<AnnouncementLog> implements AnnouncementLogDao {
	
	@Override
	public Class<AnnouncementLog> getEntityClass() {
		return AnnouncementLog.class;
	}

	@Override
	public List<AnnouncementLog> getByParam(AnnouncementLogSearchParameter parameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchAnnouncementLogByParam(parameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalAnnouncementLogByParam(AnnouncementLogSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchAnnouncementLogByParam(parameter, criteria);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchAnnouncementLogByParam(AnnouncementLogSearchParameter parameter, Criteria criteria) {
		if (parameter.getCreatedBy() != null) {
			criteria.add(Restrictions.like("createdBy", parameter.getCreatedBy(), MatchMode.ANYWHERE));
		}
		if (parameter.getUpdatedBy() != null) {
			criteria.add(Restrictions.like("updatedBy", parameter.getUpdatedBy(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.isNotNull("id"));
	}

	@Override
	public List<AnnouncementLog> getAllDataEmailNotSent() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("announcement", "announcement");
		criteria.add(Restrictions.eq("isAlreadyExecuted", Boolean.FALSE));
		criteria.add(Restrictions.eq("announcement.viewModel", HRMConstant.ANNOUNCEMENT_VIEW_MAIL));
		
		criteria.setFetchMode("announcement", FetchMode.JOIN);
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public List<AnnouncementLog> getAllDataEmailNotSentByParam(Long announcementId, Date planExecutionDate) {
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("announcement", "announcement");
		criteria.add(Restrictions.eq("announcement.id", announcementId));
		criteria.add(Restrictions.eq("planExecutionDate", planExecutionDate));
		criteria.add(Restrictions.eq("isAlreadyExecuted", Boolean.FALSE));
		criteria.add(Restrictions.eq("announcement.viewModel", HRMConstant.ANNOUNCEMENT_VIEW_MAIL));
		
		criteria.setFetchMode("announcement", FetchMode.JOIN);
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		return criteria.list();
	}
	
	@Override
	public List<AnnouncementLog> getAllDataWebViewByEmpDataIdAndPlanExecutionDate(Long empDataId, Date planExecutionDate){
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("announcement", "announcement");
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.eq("planExecutionDate", planExecutionDate));
		criteria.add(Restrictions.eq("isAlreadyExecuted", Boolean.FALSE));
		criteria.add(Restrictions.eq("announcement.viewModel", HRMConstant.ANNOUNCEMENT_VIEW_WEB));
		
		criteria.setFetchMode("announcement", FetchMode.JOIN);
		return criteria.list();
	}
	
}
