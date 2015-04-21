package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import com.inkubator.hrm.web.model.LoanNewDisbursementFormModel;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface LoanNewDisbursementService extends IService<LoanNewDisbursement> {
	public void disburseLoanApplication(LoanNewDisbursement loanNewDisbursement, List<Integer> listLoanNewApplicationId) throws Exception;
	
        public Long getCurrentMaxId() throws Exception;
}
