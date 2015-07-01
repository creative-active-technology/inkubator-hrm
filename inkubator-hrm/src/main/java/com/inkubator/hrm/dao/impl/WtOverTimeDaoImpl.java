/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtOverTimeDao;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.web.search.WtOverTimeSearchParameter;
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
 * @author Deni Husni FR
 */
@Repository(value = "wtOverTimeDao")
@Lazy
public class WtOverTimeDaoImpl extends IDAOImpl<WtOverTime> implements WtOverTimeDao {

    @Override
    public Class<WtOverTime> getEntityClass() {
        return WtOverTime.class;
    }

    @Override
    public List<WtOverTime> getByParam(WtOverTimeSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtOverTimeByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtOverTimeByParam(WtOverTimeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtOverTimeByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchWtOverTimeByParam(WtOverTimeSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getOverTimecode() != null && !searchParameter.getOverTimecode().isEmpty()) {
            criteria.add(Restrictions.like("code", searchParameter.getOverTimecode(), MatchMode.START));
        }

        if (searchParameter.getOverTimeName() != null && !searchParameter.getOverTimeName().isEmpty()) {
            criteria.add(Restrictions.like("name", searchParameter.getOverTimeName(), MatchMode.START));
        }

        if (searchParameter.getMinTime() != null && searchParameter.getMinTime() != 0) {
            criteria.add(Restrictions.eq("minimumTime", searchParameter.getMinTime()));
        }

        if (searchParameter.getMaxTime() != null && searchParameter.getMaxTime() != 0) {
            criteria.add(Restrictions.eq("maximumTime", searchParameter.getMaxTime()));
        }
    }

    @Override
    public Long getTotalDuplicateByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalDuplicaByCodeAndNotId(String code, Long id) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public WtOverTime getEntityByPkFetchApprovalDefinition(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("approvalDefinitionOTs", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinitionOTs.approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (WtOverTime) criteria.uniqueResult();
    }

    @Override
    public WtOverTime getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (WtOverTime) criteria.uniqueResult();
    }
}
