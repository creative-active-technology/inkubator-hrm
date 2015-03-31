/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanSchema;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface ApprovalDefinitionLoanSchemaDao extends IDAO<ApprovalDefinitionLoanSchema> {
    public List<ApprovalDefinitionLoanSchema> getByLoanNewSchemaId(Long loanNewSchemaId);
    
    //public ApprovalDefinitionLoanSchema getEntityByPk(Long appDefId, Long loanNewschemaId);
}
