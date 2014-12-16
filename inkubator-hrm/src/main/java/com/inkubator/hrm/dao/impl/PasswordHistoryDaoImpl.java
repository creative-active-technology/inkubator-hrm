/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PasswordHistoryDao;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.web.search.PasswordHistorySearchParameter;
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
@Repository(value = "passwordHistoryDao")
@Lazy
public class PasswordHistoryDaoImpl extends IDAOImpl<PasswordHistory> implements PasswordHistoryDao{

    @Override
    public Class<PasswordHistory> getEntityClass() {
       return PasswordHistory.class;
    }

    @Override
    public List<PasswordHistory> getByParam(PasswordHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPasswordHistByParam(searchParameter, criteria);
//        criteria.createAlias("hrmUser", "hrmUser", JoinType.LEFT_OUTER_JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLoginHistoryByParam(PasswordHistorySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//    	criteria.createAlias("hrmUser", "hrmUser", JoinType.LEFT_OUTER_JOIN);
    	doSearchPasswordHistByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchPasswordHistByParam(PasswordHistorySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getUsername()!=null) {
        	criteria.add(Restrictions.like("userName", searchParameter.getUsername(), MatchMode.START));
        } 
        if(searchParameter.getRealname()!=null){
        	criteria.add(Restrictions.like("realName", searchParameter.getRealname(), MatchMode.START));
        }
        if(searchParameter.getEmailAddress()!=null){
        	criteria.add(Restrictions.like("emailAddress", searchParameter.getEmailAddress(), MatchMode.START));
        }
        if(searchParameter.getPhoneNumber()!=null){
        	criteria.add(Restrictions.like("phoneNumber", searchParameter.getPhoneNumber(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

   
    
     @Override
    public List<PasswordHistory> getByEmailNotification(Integer emailNotification) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("emailNotification", emailNotification));
        return criteria.list();

    }
}
