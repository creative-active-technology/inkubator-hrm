/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregEmpTypeDao;
import com.inkubator.hrm.entity.UnregEmpType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "unregEmpTypeDao")
@Lazy
public class UnregEmpTypeDaoImpl extends IDAOImpl<UnregEmpType> implements UnregEmpTypeDao {

    @Override
    public Class<UnregEmpType> getEntityClass() {
        return UnregEmpType.class;
    }

    @Override
    public List<UnregEmpType> getAllDataByUnregSalaryId(Long unregSalaryId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("unregSalary", "us");
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("us.id", unregSalaryId));
        return criteria.list();
    }

}
