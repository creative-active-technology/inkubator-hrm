/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanCanceledDao;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.web.search.LoanCanceledSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
 * @author deni
 */
@Repository(value = "loanCanceledDao")
@Lazy
public class LoanCanceledDaoImpl extends IDAOImpl<LoanCanceled> implements LoanCanceledDao {

    @Override
    public Class<LoanCanceled> getEntityClass() {
        return LoanCanceled.class;
    }

    @Override
    public List<LoanCanceled> getByParam(LoanCanceledSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        doSearchByParam(searchParameter, criteria);
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.setFetchMode("loan.empData", FetchMode.JOIN);
        criteria.setFetchMode("loan.empData.bioData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loan.statusPencairan", HRMConstant.LOAN_CANCELED));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(LoanCanceledSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LoanCanceledSearchParameter searchParameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(searchParameter.getLoanSchema())) {
            criteria.createAlias("loanSchema", "loanSchema", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("loanSchema.name", searchParameter.getLoanSchema(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(searchParameter.getEmployee())) {
            criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
            criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", searchParameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public LoanCanceled getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.createAlias("loan", "loan", JoinType.INNER_JOIN);
        criteria.createAlias("loan.loanSchema", "loanSchema", JoinType.INNER_JOIN);
        criteria.setFetchMode("loan.empData", FetchMode.JOIN);
        criteria.setFetchMode("loan.empData.bioData", FetchMode.JOIN);
        return (LoanCanceled) criteria.uniqueResult();

    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult() ;
    }
}
