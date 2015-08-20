/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationInstallmentDao;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationInstallmentDao")
@Lazy
public class LoanNewApplicationInstallmentDaoImpl extends IDAOImpl<LoanNewApplicationInstallment> implements LoanNewApplicationInstallmentDao{

    @Override
    public Class<LoanNewApplicationInstallment> getEntityClass() {
        return LoanNewApplicationInstallment.class;
    }

    @Override
    public LoanNewApplicationInstallment getEntityByIdWithDetail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public Long getTotalInstallmentByLoanNewApplicationId(Integer loanNewApplicationId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("loanNewApplication", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanNewApplication.id", loanNewApplicationId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
    
}
