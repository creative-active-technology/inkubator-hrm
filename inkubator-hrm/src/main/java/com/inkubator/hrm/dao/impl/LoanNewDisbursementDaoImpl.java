/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewDisbursementDao;
import com.inkubator.hrm.entity.LoanNewDisbursement;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewDisbursementDao")
@Lazy
public class LoanNewDisbursementDaoImpl extends IDAOImpl<LoanNewDisbursement> implements LoanNewDisbursementDao {

    @Override
    public Class<LoanNewDisbursement> getEntityClass() {
        return LoanNewDisbursement.class;
    }

    @Override
    public LoanNewDisbursement getEntityWithDetail(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
