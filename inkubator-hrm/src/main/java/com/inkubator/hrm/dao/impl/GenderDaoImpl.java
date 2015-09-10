/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.GenderDao;
import com.inkubator.hrm.entity.Gender;

/**
 *
 * @author Deni
 */
@Repository(value = "genderDaoImpl")
@Lazy
public class GenderDaoImpl extends IDAOImpl<Gender> implements GenderDao{

    @Override
    public Class<Gender> getEntityClass() {
        return Gender.class;
    }

	@Override
	public List<Integer> getAllGender() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setProjection(Projections.property("name"));
        return criteria.list();
	}
    
}
