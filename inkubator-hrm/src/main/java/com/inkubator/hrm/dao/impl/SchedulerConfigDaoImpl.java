/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.web.search.SchedulerConfigSearchParameter;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedulerConfigDao")
@Lazy
public class SchedulerConfigDaoImpl extends IDAOImpl<SchedulerConfig> implements SchedulerConfigDao {

    @Override
    public Class<SchedulerConfig> getEntityClass() {
        return SchedulerConfig.class;
    }

	@Override
	public List<SchedulerConfig> getByParam(SchedulerConfigSearchParameter searchParameter, int firstResult,
			int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);
		criteria.addOrder(order);
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(SchedulerConfigSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(searchParameter, criteria);	
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(SchedulerConfigSearchParameter searchParameter, Criteria criteria){
		/*if(searchParameter.getName() != null){
			criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
		}*/
		criteria.add(Restrictions.isNotNull("id"));
	}
	

}
