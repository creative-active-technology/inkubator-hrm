/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanCanceledDao;
import com.inkubator.hrm.entity.LoanCanceled;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "loanCanceledDao")
@Lazy
public class LoanCanceledDaoImpl extends IDAOImpl<LoanCanceled> implements LoanCanceledDao{

    @Override
    public Class<LoanCanceled> getEntityClass() {
        return LoanCanceled.class;
    }
    
    
}
