/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    
    @Override
    public Boolean isValueBetweenLowRateAndTopRate(Double lowRate, Double topRate) {    	
    	Disjunction disjunction = Restrictions.disjunction();
    	
    	Conjunction conjunctionLowRate = Restrictions.conjunction();
    	conjunctionLowRate.add(Restrictions.lt("lowRate", lowRate));
    	conjunctionLowRate.add(Restrictions.gt("topRate", lowRate));
    	disjunction.add(conjunctionLowRate);
    	
    	Conjunction conjunctionTopRate = Restrictions.conjunction();
    	conjunctionTopRate.add(Restrictions.lt("lowRate", topRate));
    	conjunctionTopRate.add(Restrictions.gt("topRate", topRate));
    	disjunction.add(conjunctionTopRate);
    	
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
    	criteria.add(disjunction);
    	
    	return !criteria.list().isEmpty();
    }
    
    @Override
    public Boolean isValueBetweenLowRateAndTopRate(Double lowRate, Double topRate, Long excludeId) {    	
    	Disjunction disjunction = Restrictions.disjunction();
    	
    	Conjunction conjunctionLowRate = Restrictions.conjunction();
    	conjunctionLowRate.add(Restrictions.gt("lowRate", lowRate));
    	conjunctionLowRate.add(Restrictions.lt("topRate", lowRate));
    	disjunction.add(conjunctionLowRate);
    	
    	Conjunction conjunctionTopRate = Restrictions.conjunction();
    	conjunctionTopRate.add(Restrictions.gt("lowRate", topRate));
    	conjunctionTopRate.add(Restrictions.lt("topRate", topRate));
    	disjunction.add(conjunctionTopRate);
    	
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
    	criteria.add(disjunction);
    	criteria.add(Restrictions.ne("id", excludeId));
    	
    	return criteria.list().isEmpty();
    }
    
}
