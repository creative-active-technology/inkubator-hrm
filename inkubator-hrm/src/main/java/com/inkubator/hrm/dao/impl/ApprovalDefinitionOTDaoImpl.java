/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ApprovalDefinitionOTDao;
import com.inkubator.hrm.entity.ApprovalDefinitionOT;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository
@Lazy
public class ApprovalDefinitionOTDaoImpl extends IDAOImpl<ApprovalDefinitionOT> implements ApprovalDefinitionOTDao {

    @Override
    public Class<ApprovalDefinitionOT> getEntityClass() {
        return ApprovalDefinitionOT.class;
    }
    
}
