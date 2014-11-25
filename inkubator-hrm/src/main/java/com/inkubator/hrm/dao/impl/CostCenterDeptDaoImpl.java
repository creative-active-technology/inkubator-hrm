/*
 * To change this template, choose Tools | Templates
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
import com.inkubator.hrm.dao.CostCenterDeptDao;
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.web.search.CostCenterDeptSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "costCenterDeptDao")
@Lazy
public class CostCenterDeptDaoImpl extends IDAOImpl<CostCenterDept> implements CostCenterDeptDao{

    @Override
    public Class<CostCenterDept> getEntityClass() {
        return CostCenterDept.class;
    }

    @Override
    public List<CostCenterDept> getByParam(CostCenterDeptSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(CostCenterDeptSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(CostCenterDeptSearchParameter parameter, Criteria criteria) {
        if (parameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        } 
        if(parameter.getCode()!=null){
        	criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public CostCenterDept getEntityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (CostCenterDept) criteria.uniqueResult();
    }
    
}
