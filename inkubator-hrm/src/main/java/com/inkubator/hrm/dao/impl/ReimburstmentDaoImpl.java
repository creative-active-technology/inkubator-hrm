/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ReimbursmentDao;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.web.search.ReimbursmentSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
 * @author Deni
 */
@Repository(value = "reimbursmentDao")
@Lazy
public class ReimburstmentDaoImpl extends IDAOImpl<Reimbursment> implements ReimbursmentDao {

    @Override
    public Class<Reimbursment> getEntityClass() {
        return Reimbursment.class;
    }

    @Override
    public List<Reimbursment> getAllDataWithDetail(ReimbursmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReimbursmentByParam(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchema", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReimburstByParam(ReimbursmentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReimbursmentByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Reimbursment getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchema", FetchMode.JOIN);
        return (Reimbursment) criteria.uniqueResult();
    }

    @Override
    public Long getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("code", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReimbursmentByParam(ReimbursmentSearchParameter searchParameter, Criteria criteria) {

        criteria.createAlias("empData", "ed", JoinType.INNER_JOIN);
        criteria.createAlias("ed.bioData", "bio", JoinType.INNER_JOIN);
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getEmpData() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("ed.nik", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.firstName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", searchParameter.getEmpData(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<Reimbursment> getAllDataWithEmpIdAndReimbursmentSchemaId(Long empId, Long reimbursmentSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.eq("reimbursmentSchema.id", reimbursmentSchemaId));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchema", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Reimbursment getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", approvalActivityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchema", FetchMode.JOIN);
        return (Reimbursment) criteria.uniqueResult();
    }

    @Override
    public Reimbursment getEntityByReimbursmentNoWithDetail(String reimburmentNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", reimburmentNo));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("reimbursmentSchema", FetchMode.JOIN);
        return (Reimbursment) criteria.uniqueResult();
    }

    @Override
    public List<Reimbursment> getAllDataByEmpDataIdAndReimbursmentSchemaIdAndPeriodTime(Long empDataid, Long reimbursmentSchemaId, Date fromPeriode, Date untilPeriode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataid));
        criteria.add(Restrictions.eq("reimbursmentSchema.id", reimbursmentSchemaId));
        criteria.add(Restrictions.ge("claimDate", fromPeriode));
        criteria.add(Restrictions.le("claimDate", untilPeriode));
        return criteria.list();
    }
}
