/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanTypeDao;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanType;
import com.inkubator.hrm.web.search.BankSearchParameter;
import com.inkubator.hrm.web.search.LoanTypeSearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanTypeDao")
@Lazy
public class LoanTypeDaoImpl extends IDAOImpl<LoanType> implements LoanTypeDao {
     @Override
    public Class<LoanType> getEntityClass() {
        return LoanType.class;
    }
    
    private void doSearchLoanTypeByParam(LoanTypeSearchParameter parameter, Criteria criteria) {
        if (parameter.getLoanTypeCode() != null) {
            criteria.add(Restrictions.like("loanTypeCode", parameter.getLoanTypeCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getLoanTypeName()!= null) {
            criteria.add(Restrictions.like("loanTypeName", parameter.getLoanTypeName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoanType> getByParam(LoanTypeSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoanTypeByParam(parameter, criteria);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLoanTypeByParam(LoanTypeSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoanTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLoanTypeCode(String loanTypeCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeCode", loanTypeCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLoanTypeName(String loanTypeName) {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("loanTypeName", loanTypeName));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public LoanType getEntityWithRelationByPk(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("currency", FetchMode.JOIN);       
        return (LoanType) criteria.uniqueResult();
    }

    @Override
    public void saveAndMerge(LoanType loanType) {
       getCurrentSession().update(loanType);
       getCurrentSession().flush();
    }
}
