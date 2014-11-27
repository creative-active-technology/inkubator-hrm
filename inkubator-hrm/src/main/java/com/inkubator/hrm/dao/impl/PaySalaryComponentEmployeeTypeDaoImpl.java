/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PaySalaryComponentEmployeeTypeDao;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import java.util.List;
import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "paySalaryComponentEmployeeTypeDao")
@Lazy
public class PaySalaryComponentEmployeeTypeDaoImpl extends IDAOImpl<PaySalaryEmpType> implements PaySalaryComponentEmployeeTypeDao{

    @Override
    public Class<PaySalaryEmpType> getEntityClass() {
        return PaySalaryEmpType.class;
    }

    @Override
    public List<PaySalaryEmpType> getEntityByPaySalaryComponentId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("paySalaryComponent.id", id));
        return criteria.list();
    }
    
}
