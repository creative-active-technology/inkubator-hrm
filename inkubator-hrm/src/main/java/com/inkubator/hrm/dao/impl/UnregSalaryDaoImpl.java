/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregSalaryDao;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
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
 * @author deni
 */
@Repository(value = "unregSalaryDao")
@Lazy
public class UnregSalaryDaoImpl extends IDAOImpl<UnregSalary> implements UnregSalaryDao {

    @Override
    public Class<UnregSalary> getEntityClass() {
        return UnregSalary.class;
    }

    @Override
    public List<UnregSalary> getByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("wtPeriode", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(UnregSalarySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(UnregSalarySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public UnregSalary getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("wtPeriode", FetchMode.JOIN);
        return (UnregSalary) criteria.uniqueResult();
    }
}
