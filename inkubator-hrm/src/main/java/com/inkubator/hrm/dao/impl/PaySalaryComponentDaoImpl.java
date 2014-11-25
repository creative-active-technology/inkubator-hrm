/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "paySalaryComponentDao")
@Lazy
public class PaySalaryComponentDaoImpl extends IDAOImpl<PaySalaryComponent> implements PaySalaryComponentDao {

    @Override
    public Class<PaySalaryComponent> getEntityClass() {
        return PaySalaryComponent.class;
    }

    @Override
    public List<PaySalaryComponent> getByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryJurnal", FetchMode.JOIN);
        criteria.setFetchMode("taxComponent", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalResourceTypeByParam(PaySalaryComponentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PaySalaryComponent getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryJurnal", FetchMode.JOIN);
        criteria.setFetchMode("taxComponent", FetchMode.JOIN);;
        criteria.add(Restrictions.eq("id", id));
        return (PaySalaryComponent) criteria.uniqueResult();
    }
    
    private void doSearchByParam(PaySalaryComponentSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        } 
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchComponentUploadByParam(searchParameter, criteria);
        criteria.setFetchMode("payTempUploadDatas", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchComponentUploadByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, Criteria criteria) {
		if (StringUtils.isNotEmpty(searchParameter.getName())) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        } 
        if (StringUtils.isNotEmpty(searchParameter.getCode())) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        } 
        criteria.add(Restrictions.isNotNull("id"));
        criteria.createAlias("modelComponent", "modelComponent");
        criteria.add(Restrictions.eq("modelComponent.spesific", HRMConstant.MODEL_COMP_UPLOAD));
	}
    

}
