package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface LoanPaymentDetailService extends IService<LoanPaymentDetail> {

    public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId);

    public List<LoanPaymentDetail> getByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel, int firstResult, int maxResults, Order order) throws Exception;

    public List<LoanPaymentDetail> getByWtPeriodeWhereComponentPayrollIsActive(LoanPaymentDetailModel loanPaymentDetailModel) throws Exception;

    public Long getTotalResourceTypeByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel) throws Exception;

}
