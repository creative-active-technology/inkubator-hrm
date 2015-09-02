/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalDefinitionDao;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalDefinitionSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
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
        if (searchParameter.getApprovalName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getApprovalName(), MatchMode.START));
        }
        if (searchParameter.getProcessName() != null) {

            criteria.add(Restrictions.like("processType", searchParameter.getProcessName(), MatchMode.START));
        }
        if (searchParameter.getApproverPosition() != null) {
            criteria.createAlias("jabatanByApproverPosition", "ap", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("ap.name", searchParameter.getApproverPosition(), MatchMode.START));
        }
        if (searchParameter.getApproverIndividual() != null) {
            criteria.createAlias("hrmUserByApproverIndividual", "au", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("au.realName", searchParameter.getApproverIndividual(), MatchMode.START));
        }

        if (searchParameter.getOnBehalfApproverPosition() != null) {
            criteria.createAlias("jabatanByOnBehalfPosition", "ap", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("ap.name", searchParameter.getApproverPosition(), MatchMode.START));
        }
        if (searchParameter.getOnBehaltAppriverIndividual() != null) {
            criteria.createAlias("hrmUserByOnBehalfIndividual", "au", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("au.realName", searchParameter.getApproverIndividual(), MatchMode.START));
        }
        if (searchParameter.getApproverType() != null) {
            criteria.add(Restrictions.like("approverType", searchParameter.getApproverType(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalSameAprrovalProsesExist(String approvalName, String procesName, int sequance) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", approvalName));
        criteria.add(Restrictions.eq("processType", procesName));
        criteria.add(Restrictions.eq("sequence", sequance));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

    }

    @Override
    public Long getTotalApprovalExistWithSequenceOne(String approvalName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", approvalName));
        criteria.add(Restrictions.eq("sequence", 1));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalDataWithSequenceLower(String approvalName, int sequance) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("processType", HRMConstant.ON_APPROVE_INFO));
        disjunction.add(Restrictions.eq("processType", HRMConstant.ON_REJECT_INFO));
        criteria.add(Restrictions.eq("name", approvalName));
        criteria.add(Restrictions.le("sequence", sequance));
        criteria.add(disjunction);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalDataWithSequenceLowerAndNotId(String approvalName, int sequance, long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("processType", HRMConstant.ON_APPROVE_INFO));
        disjunction.add(Restrictions.eq("processType", HRMConstant.ON_REJECT_INFO));
        criteria.add(Restrictions.eq("name", approvalName));
        criteria.add(Restrictions.le("sequence", sequance));
        criteria.add(Restrictions.ne("id", id));
        criteria.add(disjunction);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<ApprovalDefinition> getAllDataByNameAndProcessType(String name, String processType, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("processType", processType));
        criteria.add(Restrictions.eq("isActive", true));// query bagi approval definisi yang active saja
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSequenceGreater(String name, String processType, int sequence) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("processType", processType));
        criteria.add(Restrictions.gt("sequence", sequence));
        criteria.addOrder(Order.asc("sequence"));
        return criteria.list();
    }

    @Override
    public Long getTotalSameAprrovalProsesExistAndNotId(String approvalName, String procesName, int sequance, long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", approvalName));
        criteria.add(Restrictions.eq("processType", procesName));
        criteria.add(Restrictions.eq("sequence", sequance));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSpecificNameAndSequenceGreater(String name, String processType, String specificName, int sequence) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("processType", processType));
        criteria.add(Restrictions.eq("specificName", specificName));
        criteria.add(Restrictions.eq("isNoLongerInUse", false));
        criteria.add(Restrictions.gt("sequence", sequence));
        criteria.addOrder(Order.asc("sequence"));
        return criteria.list();
    }

    @Override
    public List<ApprovalDefinition> getAllDataByNameAndProcessTypeAndSpecificName(String name, String processType, String specificName, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("processType", processType));
        criteria.add(Restrictions.eq("specificName", specificName));
        criteria.add(Restrictions.eq("isNoLongerInUse", false));
        criteria.addOrder(order);
        return criteria.list();
    }
}
