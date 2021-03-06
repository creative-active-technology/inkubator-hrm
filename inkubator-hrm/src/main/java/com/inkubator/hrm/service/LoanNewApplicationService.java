package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationStatusViewModel;
import com.inkubator.hrm.web.model.LoanNewCancellationFormModel;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import com.inkubator.hrm.web.search.LoanStatusSearchParameter;

import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationService extends IService<LoanNewApplication>, BaseApprovalService {

    public List<LoanNewApplicationInstallment> getAllDataLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception;

    public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef, Long empDataId) throws Exception;

    public Long getCurrentMaxId() throws Exception;

    public String saveWithApproval(LoanNewApplication entity) throws Exception;

    public String saveWithRevised(LoanNewApplication entity, Long approvalActivityId, String activityNumber) throws Exception;
    
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) throws Exception;
    
    public void cancelLoanApplicationAndSaveToLoanNewCancellation(LoanNewCancellationFormModel loanNewCancellationFormModel) throws Exception;
    
    public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter) throws Exception;
    
    public List<LoanNewApplicationBoxViewModel> getUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) throws Exception;
    
    public List<LoanHistoryViewModel> getListLoanHistoryByEmpDataId(Long empDataId) throws Exception;
    
    public List<LoanNewApplicationStatusViewModel> getAllDataLoanNewApplicationStatus(LoanStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalDataLoanNewApplicationStatus(LoanStatusSearchParameter parameter) throws Exception;
    
    public LoanNewApplication getEntityWithDetailByActivityNumber(String activityNumber) throws Exception;
}
