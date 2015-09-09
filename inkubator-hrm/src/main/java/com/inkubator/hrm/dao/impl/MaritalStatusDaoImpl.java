/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.MaritalStatusDao;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.web.search.MaritalStatusSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "maritalStatusDao")
@Lazy
public class MaritalStatusDaoImpl extends IDAOImpl<MaritalStatus> implements MaritalStatusDao {

    @Override
    public Class<MaritalStatus> getEntityClass() {
        return MaritalStatus.class;
    }

    @Override
    public List<MaritalStatus> getByParam(MaritalStatusSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        System.out.println(order.toString());
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMaritalStatusByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalMaritalStatusByParam(MaritalStatusSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchMaritalStatusByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getByMaritalStatusName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchMaritalStatusByParam(MaritalStatusSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
