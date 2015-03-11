/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewTypeDao;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
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
@Repository(value = "loanNewTypeDao")
@Lazy
public class LoanNewTypeDaoImpl extends IDAOImpl<LoanNewType> implements LoanNewTypeDao {

    @Override
    public Class<LoanNewType> getEntityClass() {
        return LoanNewType.class;
    }

    private void doSearchLoanTypeByParam(LoanNewTypeSearchParameter parameter, Criteria criteria) {
        if (parameter.getLoanTypeCode() != null) {
            criteria.add(Restrictions.like("loanTypeCode", parameter.getLoanTypeCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getLoanTypeName() != null) {
            criteria.add(Restrictions.like("loanTypeName", parameter.getLoanTypeName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoanNewType> getByParam(LoanNewTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("currency", FetchMode.JOIN);
        doSearchLoanTypeByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLoanTypeByParam(LoanNewTypeSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoanTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLoanNewTypeCode(String loanTypeCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeCode", loanTypeCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLoanNewTypeName(String loanTypeName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeName", loanTypeName));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public LoanNewType getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("currency", FetchMode.JOIN);       
        return (LoanNewType) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByLoanNewTypeCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLoanNewTypeNameAndNotId(String name, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeName", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
