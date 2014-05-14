/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    private void doSearchSpiUserByParam(HrmUserSearchParameter parameter, Criteria criteria) {
        if (parameter.getUserName() != null) {
            criteria.add(Restrictions.like("userId", parameter.getUserName(), MatchMode.ANYWHERE));

        }

        if (parameter.getRealName() != null) {
            criteria.add(Restrictions.like("realName", parameter.getRealName(), MatchMode.ANYWHERE));
        }

        if (parameter.getRoleName() != null) {
            criteria.createAlias("hrmUserRoles", "h");
            criteria.createAlias("h.hrmRole", "hrmRole");
            criteria.add(Restrictions.like("hrmRole.roleName", parameter.getRoleName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<HrmUser> getByParam(HrmUserSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchSpiUserByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalHrmUserByParam(HrmUserSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchSpiUserByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
