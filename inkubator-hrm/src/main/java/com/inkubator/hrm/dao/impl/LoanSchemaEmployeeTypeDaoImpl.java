/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanSchemaEmployeeTypeDao;
import com.inkubator.hrm.entity.LoanSchemaEmployeeType;
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
@Repository(value = "loanSchemaEmployeeTypeDao")
@Lazy
public class LoanSchemaEmployeeTypeDaoImpl extends IDAOImpl<LoanSchemaEmployeeType> implements LoanSchemaEmployeeTypeDao{

    @Override
    public Class<LoanSchemaEmployeeType> getEntityClass() {
        return LoanSchemaEmployeeType.class;
    }

    @Override
    public List<LoanSchemaEmployeeType> getByUserId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("loanSchema", "ls");
        criteria.add(Restrictions.eq("ls.id", id));
        criteria.addOrder(Order.desc("employeeType"));
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        return criteria.list();
    }
    
}
