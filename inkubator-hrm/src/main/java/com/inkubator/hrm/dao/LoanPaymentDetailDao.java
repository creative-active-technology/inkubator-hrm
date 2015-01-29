package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;

import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface LoanPaymentDetailDao extends IDAO<LoanPaymentDetail> {

    public List<LoanPaymentDetail> getAllDataByLoanId(Long loanId);

    public void save(List<LoanPaymentDetail> loanPaymentDetails, Loan loan);

    public Long getTotalUnpaidByEmpDataId(Long empDataId);

    public List<LoanPaymentDetail> getByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel, int firstResult, int maxResults, Order order);

    public List<LoanPaymentDetail> getByWtPeriodeWhereComponentPayrollIsActive(LoanPaymentDetailModel loanPaymentDetailModel);

    public Long getTotalByParam(String parameter, LoanPaymentDetailModel loanPaymentDetailModel);
    
    public Long getTotalUnPaidLoanByLoanId(Long loanId, Date periodEndDate);

    public List<LoanPaymentDetail> getAllDataByEmpDataIdAndLoanSchemaIdAndPeriodTime(Long empDataid, Long loanSchemaId, Date fromPeriode, Date untilPeriode);
    
    public Double getInstallmentByLoanId(Long loanId);
    
    public LoanPaymentDetail getEntityByPkWithDetail(Long id);
    
    public List<LoanPaymentDetail> getAllDataPaymentWithEmpIdAndLoanId(Long empDataId, Long loanId, Date endDatePeriod, int firstResult, int maxResults, Order order);
    
    public Long getTotalDataPaymentWithEmpIdAndLoanId(Long empDataId, Long loanId, Date endDatePeriod);
}
