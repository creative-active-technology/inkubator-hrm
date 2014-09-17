package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanPaymentDetail;

/**
 *
 * @author rizkykojek
 */
public interface LoanPaymentDetailDao extends IDAO<LoanPaymentDetail> {

	public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId);

}
