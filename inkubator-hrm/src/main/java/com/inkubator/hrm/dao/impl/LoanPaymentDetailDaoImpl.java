package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanPaymentDetailDao;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "loanPaymentDetailDao")
@Lazy
public class LoanPaymentDetailDaoImpl extends IDAOImpl<LoanPaymentDetail> implements LoanPaymentDetailDao {

	@Override
	public Class<LoanPaymentDetail> getEntityClass() {
		return LoanPaymentDetail.class;
		
	}

	@Override
	public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("loan.id", loanId));
		return criteria.list();
	}

	@Override
	public void save(List<LoanPaymentDetail> loanPaymentDetails, Loan loan) {
		Session session = getCurrentSession();
		int i=1;
		for(LoanPaymentDetail loanPaymentDetail: loanPaymentDetails){			
			loanPaymentDetail.setCreatedBy(UserInfoUtil.getUserName());
			loanPaymentDetail.setCreatedOn(new Date());
			loanPaymentDetail.setLoan(loan);
			session.save(loanPaymentDetail);
			if(i % 100 == 0){
				//flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
			}
			i++;
		}		
	}
}
