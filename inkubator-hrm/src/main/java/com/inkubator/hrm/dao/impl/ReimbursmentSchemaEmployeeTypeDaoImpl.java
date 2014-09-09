/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.ReimbursmentSchemaEmployeeTypeDao;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
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
@Repository(value = "reimbursmentSchemaEmployeeTypeDao")
@Lazy
public class ReimbursmentSchemaEmployeeTypeDaoImpl extends IDAOImpl<ReimbursmentSchemaEmployeeType> implements ReimbursmentSchemaEmployeeTypeDao{

    @Override
    public Class<ReimbursmentSchemaEmployeeType> getEntityClass() {
        return ReimbursmentSchemaEmployeeType.class;
    }

    @Override
    public List<ReimbursmentSchemaEmployeeType> getByUserId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("reimbursmentSchema", "rs");
        criteria.add(Restrictions.eq("rs.id", id));
        criteria.addOrder(Order.desc("employeeType"));
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        return criteria.list();
    }
    
}
