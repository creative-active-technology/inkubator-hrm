/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.OverTimeDistributionDao;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.web.search.OverTimeDistributionSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "overTimeDistributionDao")
@Lazy
public class OverTimeDistributionDaoImpl extends IDAOImpl<OverTimeDistribution> implements OverTimeDistributionDao{

    @Override
    public Class<OverTimeDistribution> getEntityClass() {
        return OverTimeDistribution.class;
    }

    @Override
    public List<OverTimeDistribution> getByParamWithDetail(OverTimeDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalOverTimeDistributionByParam(OverTimeDistributionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public OverTimeDistribution getEntityByParamWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (OverTimeDistribution) criteria.uniqueResult();
    }

    @Override
    public List<OverTimeDistribution> getAllDataByIdWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("wtOverTime", FetchMode.JOIN);
        return criteria.list();
    }
    
    private void doSearch(OverTimeDistributionSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getEmpData() != null) {
            criteria.add(Restrictions.like("empData", searchParameter.getEmpData(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
    
    public void saveBatch(List<OverTimeDistribution> data) {
         int counter = 0;
        for (OverTimeDistribution overTimeDistribution : data) {
            getCurrentSession().save(overTimeDistribution);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
    
}
