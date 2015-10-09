/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedulerLogDao;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedulerLogDao")
@Lazy
public class SchedulerLogDaoImpl extends IDAOImpl<SchedulerLog> implements SchedulerLogDao{

    @Override
    public Class<SchedulerLog> getEntityClass() {
      return SchedulerLog.class;
    }

	@Override
	public List<SchedulerLog> getByParam(SchedulerLogSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(SchedulerLogSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);	
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(SchedulerLogSearchParameter searchParameter, Criteria criteria){
		criteria.createAlias("schedulerConfig", "schedulerConfig", JoinType.INNER_JOIN);
		if(searchParameter.getName() != null){
			criteria.add(Restrictions.like("schedulerConfig.name", searchParameter.getName(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.isNotNull("id"));
	}
    
}
