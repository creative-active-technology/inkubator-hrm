package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;
import java.util.Date;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface LoanPaymentDetailService extends IService<LoanPaymentDetail> {

    public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId) throws Exception;

    public List<LoanPaymentDetail> getByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel, int firstResult, int maxResults, Order order) throws Exception;

    public List<LoanPaymentDetail> getByWtPeriodeWhereComponentPayrollIsActive(LoanPaymentDetailModel loanPaymentDetailModel) throws Exception;

    public Long getTotalByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel) throws Exception;
    
    public Double getInstallmentByLoanId(Long loanId) throws Exception;

    public LoanPaymentDetail getEntityByPkAndEndWtPeriodActiveWithDetail(Long id, Date endDatePeriod) throws Exception;
    
    public List<LoanPaymentDetail> getAllDataPaymentWithEmpIdAndLoanId(Long empDataId, Long loanId, Date endDatePeriod, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDataPaymentWithEmpIdAndLoanId(Long empDataId, Long loanId, Date endDatePeriod) throws Exception;
}

