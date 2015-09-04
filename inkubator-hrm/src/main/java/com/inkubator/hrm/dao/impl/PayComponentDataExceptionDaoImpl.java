/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayComponentDataExceptionDao;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
@Repository(value = "payComponentDataExceptionDao")
@Lazy
public class PayComponentDataExceptionDaoImpl extends IDAOImpl<PayComponentDataException> implements PayComponentDataExceptionDao {

    @Override
    public Class<PayComponentDataException> getEntityClass() {
        return PayComponentDataException.class;
    }

    @Override
    public List<PayComponentDataException> getByParamWithDetailForDetail(String searchParameter, String paySalaryComponentId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamForDetail(searchParameter, criteria);
        criteria.add(Restrictions.eq("paySalaryComponent.id", Long.valueOf(paySalaryComponentId.substring(1))));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamForDetail(String searchParameter, String paySalaryComponentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamForDetail(searchParameter, criteria);
        criteria.add(Restrictions.eq("paySalaryComponent.id", Long.valueOf(paySalaryComponentId.substring(1))));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PayComponentDataException getByPaySalaryComponentId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (PayComponentDataException) criteria.uniqueResult();
    }

    @Override
    public List<PayComponentDataException> getByPaySalaryComponent(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.add(Restrictions.eq("paySalaryComponent.id", id));
        return criteria.list();
    }

    private void doSearchByParam(PayComponentDataExceptionSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getPaySalaryComponent() != null) {
            criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("paySalaryComponent.name", searchParameter.getPaySalaryComponent(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    private void doSearchByParamForDetail(String searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter)) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", searchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter, MatchMode.START));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PayComponentDataException getByEmpIdAndComponentId(Long empId, Long paySalaryCompId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "emp");
        criteria.createAlias("paySalaryComponent", "payComp");
        criteria.add(Restrictions.eq("emp.id", empId));
        criteria.add(Restrictions.eq("payComp.id", paySalaryCompId));
        return (PayComponentDataException) criteria.uniqueResult();

    }

    @Override
    public List<PayComponentDataException> getAllByEmpId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "emp");
        criteria.add(Restrictions.eq("emp.id", id));
        return criteria.list();
    }

	@Override
	public List<PayComponentDataException> getAllDataByReset(Boolean isReset) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("resetAfterMonth", isReset));
		return criteria.list();
	}
	
	@Override
	public Long getDuplicateEmpData(Long empDataId, Long paySalaryComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}
