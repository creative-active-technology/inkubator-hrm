/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PasswordComplexityDao;
import com.inkubator.hrm.entity.PasswordComplexity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "passwordComplexityDao")
@Lazy
public class PasswordComplexityDaoImpl extends IDAOImpl<PasswordComplexity> implements PasswordComplexityDao {

    @Override
    public Class<PasswordComplexity> getEntityClass() {
        return PasswordComplexity.class;
    }

    @Override
    public PasswordComplexity getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (PasswordComplexity) criteria.uniqueResult();
    }

}
