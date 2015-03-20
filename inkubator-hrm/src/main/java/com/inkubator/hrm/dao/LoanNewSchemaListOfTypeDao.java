/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewSchemaListOfTypeId;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaListOfTypeDao extends IDAO<LoanNewSchemaListOfType>{
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchema(Long loanNewSchema);
    
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId);

    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeId(LoanNewSchemaListOfTypeId loanNewSchemaListOfTypeId);

    public Long getTotalByLoanTypeAndSchema(Long typeId, Long schemaId);
    
    public Long getTotalByNotLoanTypeAndSchema(Long typeId, Long schemaId);
}
