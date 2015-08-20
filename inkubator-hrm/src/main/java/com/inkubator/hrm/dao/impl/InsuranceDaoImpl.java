/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.InsuranceDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.entity.Insurance;
import com.inkubator.hrm.web.search.BankSearchParameter;
import com.inkubator.hrm.web.search.InsuranceSearchParameter;

/**
 *
 * @author Deni
 */
@Repository(value = "insuranceDao")
@Lazy
public class InsuranceDaoImpl extends IDAOImpl<Insurance> implements InsuranceDao{

	@Override
	public List<Insurance> getByParam(InsuranceSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(InsuranceSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Insurance getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (Insurance) criteria.uniqueResult();
	}

	@Override
	public Class<Insurance> getEntityClass() {
		return Insurance.class;
	}
    
	private void doSearchByParam(InsuranceSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode() != null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName() != null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (parameter.getInsuranceProduct() != null) {
            criteria.add(Restrictions.like("insuranceProduct", parameter.getInsuranceProduct(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
