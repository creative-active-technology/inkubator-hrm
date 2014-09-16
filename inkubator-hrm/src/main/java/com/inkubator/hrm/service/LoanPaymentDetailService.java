package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanPaymentDetail;

/**
 *
 * @author rizkykojek
 */
public interface LoanPaymentDetailService extends IService<LoanPaymentDetail> {

	public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId);

}
