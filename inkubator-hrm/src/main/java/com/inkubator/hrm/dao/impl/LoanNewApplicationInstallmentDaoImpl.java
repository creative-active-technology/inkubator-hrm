/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationInstallmentDao;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.entity.WtPeriode;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
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

	@Override
	public List<LoanNewApplicationInstallment> getAllDataDisbursedByEmpDataIdAndLoanTypeIdAndPeriodDate(Long empDataId, Long loanTypeId, Date startPeriodDate, Date endPeriodDate) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("loanNewApplication", "loanNewApplication", JoinType.INNER_JOIN);
		criteria.createAlias("loanNewApplication.empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("loanNewApplication.loanNewType", "loanNewType", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("loanNewType.id", loanTypeId));
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.le("installmentDate", endPeriodDate));
		criteria.add(Restrictions.ge("installmentDate", startPeriodDate));
		criteria.add(Restrictions.ge("loanNewApplication.loanStatus", HRMConstant.LOAN_DISBURSED));
		return criteria.list();
	}

	@Override
	public List<LoanNewApplicationInstallment> getListByLoanNewApplicationId(Integer loanNewApplicationId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("loanNewApplication", "loanNewApplication", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("loanNewApplication.id", loanNewApplicationId));
		return criteria.list();
	}

	@Override
	public LoanNewApplicationInstallment getLastPaidTerminInstallment(Integer LoanNewApplicationid) {
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("loanNewApplication", "loanNewApplication", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("loanNewApplication.id", LoanNewApplicationid));
        
        DetachedCriteria criteriaWtPeriod = DetachedCriteria.forClass(WtPeriode.class)
                .add(Restrictions.eq("payrollType", HRMConstant.PERIODE_PAYROLL_ACTIVE))
                .setProjection(Property.forName("fromPeriode"));
        
        criteria.add(Subqueries.propertyLt("installmentDate", criteriaWtPeriod));
        criteria.addOrder(Order.desc("numOfInstallment"));
        criteria.setMaxResults(1);
        
        LoanNewApplicationInstallment currentNextInstallment = (LoanNewApplicationInstallment) criteria.uniqueResult();
        return currentNextInstallment;
	}
    
}
