/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregPayComponentDao;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregPayComponentsId;
import com.inkubator.hrm.web.search.UnregPayComponentSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "unregPayComponentDao")
@Lazy
public class UnregPayComponentDaoImpl extends IDAOImpl<UnregPayComponents> implements UnregPayComponentDao {

    @Override
    public Class<UnregPayComponents> getEntityClass() {
        return UnregPayComponents.class;
    }

    @Override
    public List<UnregPayComponents> getByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryComponent.modelComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryComponent.taxComponent", FetchMode.JOIN);
        criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(Long unregSalaryId, UnregPayComponentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doSearchByParam(UnregPayComponentSearchParameter searchParameter, Criteria criteria) {
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public UnregPayComponents getEntityByPK(UnregPayComponentsId unregPayComponentsId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", unregPayComponentsId));
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        return (UnregPayComponents) criteria.uniqueResult();
    }
}
