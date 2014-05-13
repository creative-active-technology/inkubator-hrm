/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoginHistoryDao;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
@Repository(value = "loginHistoryDao")
@Lazy
public class LoginHistoryDaoImpl extends IDAOImpl<LoginHistory> implements LoginHistoryDao{

    @Override
    public Class<LoginHistory> getEntityClass() {
      return LoginHistory.class;
    }

    @Override
    public List<LoginHistory> getByParam(LoginHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoginHistByParam(searchParameter, criteria);
        criteria.createAlias("hrmUser", "hrmUser", JoinType.LEFT_OUTER_JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLoginHistoryByParam(LoginHistorySearchParameter searchParameter) {
    	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
    	criteria.createAlias("hrmUser", "hrmUser", JoinType.LEFT_OUTER_JOIN);
    	doSearchLoginHistByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchLoginHistByParam(LoginHistorySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getUserId()!=null) {
        	criteria.add(Restrictions.like("hrmUser.userId", searchParameter.getUserId(), MatchMode.ANYWHERE));
        } else if(searchParameter.getIpAddress()!=null){
        	criteria.add(Restrictions.like("ipAddress", searchParameter.getIpAddress(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
}
