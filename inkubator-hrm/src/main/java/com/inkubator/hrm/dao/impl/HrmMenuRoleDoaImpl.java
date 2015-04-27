/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmMenuRoleDao;
import com.inkubator.hrm.entity.HrmMenuRole;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni.fahri
 */
@Repository(value = "hrmMenuRoleDao")
@Lazy
public class HrmMenuRoleDoaImpl extends IDAOImpl<HrmMenuRole> implements HrmMenuRoleDao {

    @Override
    public Class<HrmMenuRole> getEntityClass() {
        return HrmMenuRole.class;
    }

    @Override
    public List<HrmMenuRole> getByLevelOneAndRoleName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("hrmRole", "hr", JoinType.INNER_JOIN);
        criteria.createAlias("hrmMenu", "hm", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("hr.roleName", name));
        criteria.add(Restrictions.eq("hm.menuLevel", 1));
        criteria.addOrder(Order.asc("hm.orderLevelMenu"));
        return criteria.list();
    }

}
