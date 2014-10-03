/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;
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
@Repository(value = "leaveDistributionDao")
@Lazy
public class LeaveDistributionDaoImpl extends IDAOImpl<LeaveDistribution> implements LeaveDistributionDao {

    @Override
    public Class<LeaveDistribution> getEntityClass() {
        return LeaveDistribution.class;
    }

    @Override
    public List<LeaveDistribution> getByParamWithDetail(LeaveDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLeaveDistributionByParam(LeaveDistributionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearch(LeaveDistributionSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getEmpData() != null) {
            criteria.add(Restrictions.like("empData", searchParameter.getEmpData(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public LeaveDistribution getEntityByParamWithDetail(Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", empId));
        return (LeaveDistribution) criteria.uniqueResult();
    }

    @Override
    public List<LeaveDistribution> getAllDataByEmpIdWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        return criteria.list();
    }
    
    @Override
    public void saveBatch(List<LeaveDistribution> data) {
        int counter = 0;
        for (LeaveDistribution distribution : data) {
            getCurrentSession().save(distribution);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
}
