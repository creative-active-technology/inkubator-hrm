/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
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
 * @author Deni Husni FR
 */
@Repository
@Lazy
public class ApprovalDefinitionDaoImpl extends IDAOImpl<ApprovalDefinition> implements ApprovalDefinitionDao {

    @Override
    public Class<ApprovalDefinition> getEntityClass() {
        return ApprovalDefinition.class;
    }

    @Override
    public List<ApprovalDefinition> getByParam(ApprovalDefinitionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchApprovalDefinitionByParam(searchParameter, criteria);
        criteria.setFetchMode("proscessToApprove", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchApprovalDefinitionByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchApprovalDefinitionByParam(ApprovalDefinitionSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getProcessName() != null) {
            criteria.createAlias("proscessToApprove", "pa", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("pa.code", searchParameter.getProcessName(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getApproverPosition() != null) {
            criteria.add(Restrictions.like("approverPosition", searchParameter.getApproverPosition(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getApproverIndividual() != null) {
            criteria.add(Restrictions.like("approverIndividual", searchParameter.getApproverIndividual(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getOnBehalfApproverPosition() != null) {
            criteria.add(Restrictions.like("onBehalfIndividual", searchParameter.getOnBehaltAppriverIndividual(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getOnBehaltAppriverIndividual() != null) {
            criteria.add(Restrictions.like("onBehalfPosition", searchParameter.getOnBehalfApproverPosition(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }
}
