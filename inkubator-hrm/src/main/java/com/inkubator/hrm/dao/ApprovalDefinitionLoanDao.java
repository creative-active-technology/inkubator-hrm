/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.ApprovalDefinitionLoan;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanId;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface ApprovalDefinitionLoanDao extends IDAO<ApprovalDefinitionLoan> {
    public List<ApprovalDefinitionLoan> getByLoanId(Long id);
    
    public ApprovalDefinitionLoan getEntityByPk(Long appDefId, Long loanNewschemaId);
}
