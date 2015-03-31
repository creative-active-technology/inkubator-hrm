/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationDao")
@Lazy
public class LoanNewApplicationDaoImpl extends IDAOImpl<LoanNewApplication> implements LoanNewApplicationDao {

    @Override
    public Class<LoanNewApplication> getEntityClass() {
        return LoanNewApplication.class;
    }

    @Override
    public LoanNewApplication getEntityByIdWithDetail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
