/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ResourceNameDao;
import com.inkubator.hrm.entity.ResourceName;
import com.inkubator.hrm.web.search.ResourceNameSearchParameter;
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
 * @author Deni
 */
@Repository(value = "resourceNameDao")
@Lazy
public class ResourceNameDaoImpl extends IDAOImpl<ResourceName> implements ResourceNameDao{

    @Override
    public Class<ResourceName> getEntityClass() {
        return ResourceName.class;
    }

    @Override
    public List<ResourceName> getByParam(ResourceNameSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("resourceType", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalResourceNameByParam(ResourceNameSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(ResourceNameSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getCode()!=null) {
        	criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getName()!=null) {
        	criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        } 
        if (searchParameter.getResourceType()!=null) {
        	criteria.add(Restrictions.like("resourceType", searchParameter.getResourceType(), MatchMode.ANYWHERE));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
    
}
