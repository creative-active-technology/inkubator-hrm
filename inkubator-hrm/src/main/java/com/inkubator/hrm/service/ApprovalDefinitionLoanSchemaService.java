/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanSchema;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface ApprovalDefinitionLoanSchemaService extends IService<ApprovalDefinitionLoanSchema> {

    public List<ApprovalDefinitionLoanSchema> getByLoanNewSchemaId(Long loanNewSchemaId);

}
