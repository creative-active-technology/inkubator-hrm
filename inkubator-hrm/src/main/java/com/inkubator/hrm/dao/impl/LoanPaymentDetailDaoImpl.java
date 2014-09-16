package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanPaymentDetailDao;
import com.inkubator.hrm.entity.LoanPaymentDetail;

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
}
