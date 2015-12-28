/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedulerLogDao;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.search.SchedulerLogSearchParameter;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedulerLogDao")
@Lazy
public class SchedulerLogDaoImpl extends IDAOImpl<SchedulerLog> implements SchedulerLogDao {

    @Override
    public Class<SchedulerLog> getEntityClass() {
        return SchedulerLog.class;
    }

    @Override
    public List<SchedulerLog> getByParam(SchedulerLogSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(SchedulerLogSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(SchedulerLogSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("schedulerConfig", "schedulerConfig", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getName())) {
            criteria.add(Restrictions.eq("schedulerConfig.name", searchParameter.getName()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<SchedulerLog> getByMonthDif(int value) {
        Date now = new Date();
        Date parameter = DateTimeUtil.getDateFrom(now, -value, CommonUtilConstant.DATE_FORMAT_WEEK);

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.lt("startExecution", parameter));
        return criteria.list();
    }

    @Override
    public void deleteBatch(List<SchedulerLog> data) {
        int counter = 0;
        for (SchedulerLog dataToDelte : data) {
            getCurrentSession().delete(dataToDelte);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }
}
