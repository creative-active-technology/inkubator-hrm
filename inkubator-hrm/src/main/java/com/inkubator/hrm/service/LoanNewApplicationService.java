package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewApplicationInstallment;
import com.inkubator.hrm.web.search.LeaveSearchParameter;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewApplicationService extends IService<LoanNewApplication> {
    public List<LoanNewApplicationInstallment> getAllDataLoanNewApplicationInstallment(Double interestRate, Integer termin, Date loanPaymentDate, Double nominalPrincipal, Integer typeOfInterest) throws Exception;
}
