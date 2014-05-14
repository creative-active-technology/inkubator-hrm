/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmUserRoleDao;
import com.inkubator.hrm.entity.HrmUserRole;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "hrmUserRoleDao")
@Lazy
public class HrmUserRoleDaoImpl extends IDAOImpl<HrmUserRole> implements HrmUserRoleDao {

    @Override
    public Class<HrmUserRole> getEntityClass() {
        return HrmUserRole.class;
    }

    @Override
    public List<HrmUserRole> getByUserId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("hrmUser", "su");
        criteria.add(Restrictions.eq("su.id", id));
        criteria.addOrder(Order.desc("hrmRole"));
        criteria.setFetchMode("hrmUser", FetchMode.JOIN);
        return criteria.list();
    }

}
