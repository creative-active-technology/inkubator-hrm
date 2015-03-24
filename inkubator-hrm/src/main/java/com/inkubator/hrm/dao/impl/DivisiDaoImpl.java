/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DivisiDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Divisi;
import com.inkubator.hrm.web.search.DivisiSearchParameter;
import java.util.List;
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
 * @author EKA
 */
@Repository(value="divisiDao")
@Lazy
public class DivisiDaoImpl extends IDAOImpl<Divisi> implements DivisiDao{

    @Override
    public Class<Divisi> getEntityClass() {
        return Divisi.class;
    }

    @Override
    public List<Divisi> getByParam(DivisiSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.setFetchMode("department", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalDivisiByParam(DivisiSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(DivisiSearchParameter searchParameter, Criteria criteria){
        if(searchParameter.getName() != null){
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if(searchParameter.getCode() != null){
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Divisi getDepartmentNameByDivisiId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("department", FetchMode.JOIN);
        return (Divisi) criteria.uniqueResult();
    }
    
}