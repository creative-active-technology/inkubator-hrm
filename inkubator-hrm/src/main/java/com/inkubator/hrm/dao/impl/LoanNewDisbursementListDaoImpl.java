/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewDisbursementDao;
import com.inkubator.hrm.dao.LoanNewDisbursementListDao;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import com.inkubator.hrm.entity.LoanNewDisbursementList;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewDisbursementListDao")
@Lazy
public class LoanNewDisbursementListDaoImpl extends IDAOImpl<LoanNewDisbursementList> implements LoanNewDisbursementListDao {

    @Override
    public Class<LoanNewDisbursementList> getEntityClass() {
        return LoanNewDisbursementList.class;
    }

    @Override
    public LoanNewDisbursementList getEntityWithDetail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
