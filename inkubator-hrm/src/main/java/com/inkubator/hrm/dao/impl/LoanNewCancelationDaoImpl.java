/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewCancelationDao;
import com.inkubator.hrm.entity.LoanNewCancelation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewCancelationDao")
@Lazy
public class LoanNewCancelationDaoImpl extends IDAOImpl<LoanNewCancelation> implements LoanNewCancelationDao {

    @Override
    public Class<LoanNewCancelation> getEntityClass() {
        return LoanNewCancelation.class;
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
