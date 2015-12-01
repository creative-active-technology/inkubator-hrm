/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CareerTerminationTypeDao;
import com.inkubator.hrm.entity.CareerTerminationType;
import com.inkubator.hrm.web.search.CareerTerminationTypeSearchParameter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "careerTerminationTypeDao")
@Lazy
public class CareerTerminationTypeDaoImpl extends IDAOImpl<CareerTerminationType> implements CareerTerminationTypeDao {

    @Override
    public Class<CareerTerminationType> getEntityClass() {
        return CareerTerminationType.class;
    }

	@Override
	public List<CareerTerminationType> getListByParam(CareerTerminationTypeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
	    doSearchTerminationTypeByParam(searchParameter, criteria);
	    criteria.addOrder(orderable);
	    criteria.setFirstResult(firstResult);
	    criteria.setMaxResults(maxResults);
        criteria.createAlias("systemLetterReference", "systemLetterReference", JoinType.INNER_JOIN);
        criteria.createAlias("systemCareerConst", "systemCareerConst", JoinType.INNER_JOIN);
	    return criteria.list();
	}

	@Override
	public Long getTotalByParam(CareerTerminationTypeSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchTerminationTypeByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchTerminationTypeByParam(CareerTerminationTypeSearchParameter searchParameter, Criteria criteria) {
        if (StringUtils.isNotBlank(searchParameter.getCode())) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (StringUtils.isNotBlank(searchParameter.getName())) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public CareerTerminationType getEntityWithDetailById(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("systemLetterReference", FetchMode.JOIN);
        criteria.setFetchMode("systemCareerConst", FetchMode.JOIN);
        return (CareerTerminationType) criteria.uniqueResult();
	}
    
}
