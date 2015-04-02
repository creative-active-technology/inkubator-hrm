/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaListOfTypeService extends IService<LoanNewSchemaListOfType> {

    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchema(Long loanNewSchema) throws Exception;

    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(Long id) throws Exception;

    public void update(LoanNewSchemaListOfType entity, Long oldId) throws Exception;

    public LoanNewSchemaListOfType getEntityByLoanNewSchemaIdAndLoanNewTypeIdWithDetail(Long loanNewSchemaId, Long loanNewTypeId);
    
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchemaWhereStatusActive(Long loanNewSchema) throws Exception;
}
