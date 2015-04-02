/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import java.util.List;

/**
 *
 * @author Deni
 */
public interface LoanNewSchemaListOfTypeDao extends IDAO<LoanNewSchemaListOfType>{
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchema(Long loanNewSchema);
    
    public List<LoanNewSchemaListOfType> getEntityByLoanNewSchemaWhereStatusActive(Long loanNewSchema);
    
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeIdWithDetail(Long id);

    public LoanNewSchemaListOfType getEntityByLoanNewSchemaListOfTypeId(Long id);

    public Long getTotalByLoanTypeAndSchema(Long typeId, Long schemaId);
    
    public Long getTotalBySchemaAndTypeAndStatusActive(Long schemaId, Long typeId);
    
    public LoanNewSchemaListOfType getEntityByLoanNewSchemaIdAndLoanNewTypeIdWithDetail(Long loanNewSchemaId, Long loanNewTypeId);
    
    public List<LoanNewSchemaListOfType> getAllDataByLoanSchemaId(Long loanSchemaId);
}
