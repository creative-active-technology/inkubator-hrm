/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PaySalaryEmpTypeDao;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "paySalaryEmpTypeDao")
@Lazy
public class PaySalaryEmpTypeDaoImpl extends IDAOImpl<PaySalaryEmpType> implements PaySalaryEmpTypeDao{

    @Override
    public Class<PaySalaryEmpType> getEntityClass() {
        return PaySalaryEmpType.class;
    }

    @Override
    public List<PaySalaryEmpType> getByUserId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "psc");
        criteria.add(Restrictions.eq("psc.id", id));
        criteria.addOrder(Order.desc("employeeType"));
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        return criteria.list();
    }
    
}
