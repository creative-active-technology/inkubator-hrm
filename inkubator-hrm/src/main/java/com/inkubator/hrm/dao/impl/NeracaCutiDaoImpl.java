/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.NeracaCutiDao;
import com.inkubator.hrm.entity.NeracaCuti;
import com.inkubator.hrm.web.search.NeracaCutiSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "neracaCutiDao")
@Lazy
public class NeracaCutiDaoImpl extends IDAOImpl<NeracaCuti> implements NeracaCutiDao{

    @Override
    public Class<NeracaCuti> getEntityClass() {
        return NeracaCuti.class;
    }

    @Override
    public List<NeracaCuti> getByParam(NeracaCutiSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leaveDistribution", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.leave", FetchMode.JOIN);
        doSearch(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalNeracaCutiByParam(NeracaCutiSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public NeracaCuti getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("leaveDistribution", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData", FetchMode.JOIN);
        criteria.setFetchMode("leaveDistribution.empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (NeracaCuti) criteria.uniqueResult();
    }
    
        private void doSearch(NeracaCutiSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getLeaveDistribution()!=null) {
                        criteria.createAlias("leaveDistribution", "ld", JoinType.INNER_JOIN);
                        criteria.createAlias("ld.empData", "emp", JoinType.INNER_JOIN);
        	criteria.add(Restrictions.like("emp.firstName", searchParameter.getLeaveDistribution(), MatchMode.ANYWHERE));
        } 
        criteria.add(Restrictions.isNotNull("id"));
    }
}
