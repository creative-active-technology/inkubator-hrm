/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.HrmUser;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "hrmUserDao")
@Lazy
public class HrmUserDaoImpl extends IDAOImpl<HrmUser> implements HrmUserDao {

    @Override
    public Class<HrmUser> getEntityClass() {
        return HrmUser.class;
    }

    @Override
    public HrmUser getByUserName(String userName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("userId", userName));
        return (HrmUser) criteria.uniqueResult();
    }

}
