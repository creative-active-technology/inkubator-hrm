/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SystemScoringIndexDao;
import com.inkubator.hrm.entity.SystemScoringIndex;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "systemScoringIndexDao")
@Lazy
public class SystemScoringIndexDaoImpl extends IDAOImpl<SystemScoringIndex> implements SystemScoringIndexDao{

    @Override
    public Class<SystemScoringIndex> getEntityClass() {
        return SystemScoringIndex.class;
    }

    @Override
    public List<SystemScoringIndex> getAllByParam(Long systemScoringId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("systemScoring.id", systemScoringId));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(Long systemScoringId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("systemScoring.id", systemScoringId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalBylabelMask(String labelMask) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("labelMask", labelMask));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalBylabelMaskAndNotId(String labelMask, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("labelMask", labelMask));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByValue(Integer value) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("value", value));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByValueAndNotId(Integer value, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("value", value));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Integer getLastOrderScala(Long systemScoringId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("systemScoring.id", systemScoringId));
        criteria.addOrder(Order.desc("orderScala"));
        return (Integer) criteria.setProjection(Projections.property("orderScala")).setMaxResults(1).uniqueResult();
    }

    @Override
    public SystemScoringIndex getEntityBySystemScoringIdAndOrderScala(Long systemScoringId, int orderScala) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("systemScoring.id", systemScoringId));
        criteria.add(Restrictions.eq("orderScala", orderScala));
        return (SystemScoringIndex) criteria.uniqueResult();
    }
    
    @Override
    public List<SystemScoringIndex> getAllDataBySystemScoringIdAndGreaterOrderScala(Long systemScoringId, int orderScala){
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("systemScoring.id", systemScoringId));
        criteria.add(Restrictions.gt("orderScala", orderScala));
        return criteria.list();
    }
    
}
