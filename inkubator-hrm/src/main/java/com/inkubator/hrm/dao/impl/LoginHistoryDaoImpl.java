/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoginHistoryDao;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
@Repository(value = "loginHistoryDao")
@Lazy
public class LoginHistoryDaoImpl extends IDAOImpl<LoginHistory> implements LoginHistoryDao {

    @Override
    public Class<LoginHistory> getEntityClass() {
        return LoginHistory.class;
    }

    @Override
    public List<LoginHistory> getByParam(int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("hrmUser", "hrmUser", JoinType.LEFT_OUTER_JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
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
        if (searchParameter.getUserId() != null) {
            criteria.add(Restrictions.like("hrmUser.userId", searchParameter.getUserId(), MatchMode.START));
        }
        if (searchParameter.getIpAddress() != null) {
            criteria.add(Restrictions.like("ipAddress", searchParameter.getIpAddress(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoginHistory> getByWeekDif(int value) {
        Date now = new Date();
        Date parameter = DateTimeUtil.getDateFrom(now, -value, CommonUtilConstant.DATE_FORMAT_WEEK);
        
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.lt("loginDate", parameter));
        return criteria.list();
    }

    @Override
    public void deleteBatch(List<LoginHistory> data) {
        int counter = 0;
        for (LoginHistory dataToDelte : data) {
            getCurrentSession().delete(dataToDelte);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

}
