/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

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

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregPayComponentDao;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentSearchParameter;

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
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getComponentModel())) {
            criteria.add(Restrictions.like("modelComponent.name", searchParameter.getComponentModel(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(searchParameter.getComponentName())) {
            criteria.add(Restrictions.like("paySalaryComponent.name", searchParameter.getComponentName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

}
