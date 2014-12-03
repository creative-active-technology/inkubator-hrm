/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TaxRateDao;
import com.inkubator.hrm.entity.TaxRate;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "taxRateDao")
@Lazy
public class TaxRateDaoImpl extends IDAOImpl<TaxRate> implements TaxRateDao{

    @Override
    public Class<TaxRate> getEntityClass() {
        return TaxRate.class;
    }

    @Override
    public List<TaxRate> getByParam(int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
