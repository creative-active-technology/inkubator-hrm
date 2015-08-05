package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.primefaces.model.UploadedFile;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.LoanCanceled;
import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.web.model.LoanCanceledModel;
import com.inkubator.hrm.web.model.LoanModel;
import com.inkubator.hrm.web.search.LoanSearchParameter;

import java.util.HashMap;

/**
 *
 * @author rizkykojek
 */
public interface LoanService extends IService<Loan>, BaseApprovalService {

    public List<Loan> getByParam(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalByParam(LoanSearchParameter parameter) throws Exception;

    public Loan getEntityByPkWithDetail(Long id) throws Exception;

    public String save(Loan entity, boolean isBypassApprovalChecking) throws Exception;

    public void update(Loan entity) throws Exception;

    public List<LoanPaymentDetail> getAllDataLoanPaymentDetails(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception;

    public Loan getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) throws Exception;
    
    public void executeBatchFileUpload(LoanModel report) throws Exception;

    public String updateFileAndDeleteData(UploadedFile file) throws Exception;
    
    public List<Loan> getByParamByStatusUndisbursed(LoanSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalByParamByStatusUndisbursed(LoanSearchParameter parameter) throws Exception;
    
    public void updateStatusAndDateDisbursementPaid(Long loanId, Date dateDisbursement) throws Exception;
    
    public List<Loan> getAllDataByEmpDataIdAndStatusDisbursed(Long empDataId) throws Exception;
    
        public HashMap<Long, String> getAllDataNotApprovedYet(String userId) throws Exception;
    
    public void cancelled(long approvalActivityId, LoanCanceled loanCancelation) throws Exception;
    
    public Long getCurrentMaxId() throws Exception;
}
