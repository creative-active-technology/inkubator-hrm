/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationDao")
@Lazy
public class LoanNewApplicationDaoImpl extends IDAOImpl<LoanNewApplication> implements LoanNewApplicationDao {

    @Override
    public Class<LoanNewApplication> getEntityClass() {
        return LoanNewApplication.class;
    }

    @Override
    public LoanNewApplication getEntityByIdWithDetail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<LoanNewApplication> getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));
        criteria.add(Restrictions.ne("loanStatus", HRMConstant.LOAN_PAID));
        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchemaId)); 
        
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_DISBURSED));
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_PAID));
        criteria.add(disjunction);
        
        return criteria.list();
    }

}
