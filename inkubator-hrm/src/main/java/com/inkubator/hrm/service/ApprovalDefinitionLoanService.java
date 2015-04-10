/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionLoanService extends IService<ApprovalDefinitionLoan> {
    public List<ApprovalDefinitionLoan> getByLoanId(Long id) throws Exception;
    
    public void deleteLoanNewSchemaAppDef(Long appDefId, Long loanNewSchemaId) throws Exception;
    
    public List<ApprovalDefinitionLoan> getByLoanIdWithDetail(Long id)throws Exception;
}
