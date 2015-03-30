/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionLoanSchemaDao;
import com.inkubator.hrm.entity.ApprovalDefinitionLoanSchema;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "approvalDefinitionLoanSchemaDao")
@Lazy
public class ApprovalDefinitionLoanSchemaDaoImpl extends IDAOImpl<ApprovalDefinitionLoanSchema> implements ApprovalDefinitionLoanSchemaDao {

    @Override
    public Class<ApprovalDefinitionLoanSchema> getEntityClass() {
        return ApprovalDefinitionLoanSchema.class;
    }

    @Override
    public List<ApprovalDefinitionLoanSchema> getByLoanNewSchemaId(Long loanNewSchemaId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
