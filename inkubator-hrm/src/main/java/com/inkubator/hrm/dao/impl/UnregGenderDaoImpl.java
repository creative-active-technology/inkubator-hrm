/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregGenderDao;
import com.inkubator.hrm.entity.UnregGender;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "unregGenderDao")
@Lazy
public class UnregGenderDaoImpl extends IDAOImpl<UnregGender> implements UnregGenderDao {

    @Override
    public Class<UnregGender> getEntityClass() {
        return UnregGender.class;
    }

	@Override
	public List<UnregGender> getAllDataByUnregSalaryId(Long unregSalaryId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("unregSalary", "us");
        criteria.setFetchMode("gender", FetchMode.JOIN);
        criteria.add(Restrictions.eq("us.id", unregSalaryId));
        return criteria.list();
	}
    
}
