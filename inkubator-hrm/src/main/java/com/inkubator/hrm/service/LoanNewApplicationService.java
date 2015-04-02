package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationService extends IService<LoanNewApplication>, BaseApprovalService {

    public List<LoanNewApplicationInstallment> getAllDataLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception;

    public List<EmpData> getListApproverByListAppDefintion(List<ApprovalDefinition> listAppDef) throws Exception;

    public Long getCurrentMaxId();

    public String saveWithApproval(LoanNewApplication entity) throws Exception;

    public String saveWithRevised(LoanNewApplication entity, Long approvalActivityId) throws Exception;
}
