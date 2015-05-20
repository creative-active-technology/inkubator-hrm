/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtScheduleShiftDao;
import com.inkubator.hrm.entity.WtScheduleShift;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "wtScheduleShiftDao")
@Lazy
public class WtScheduleShiftDaoImpl extends IDAOImpl<WtScheduleShift> implements WtScheduleShiftDao {

    @Override
    public Class<WtScheduleShift> getEntityClass() {
        return WtScheduleShift.class;
    }

    @Override
    public List<WtScheduleShift> getByParam(Long workingGroupId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtScheduleShiftByParam(workingGroupId, criteria);
        criteria.createAlias("wtWorkingHour", "wtWorkingHour", JoinType.INNER_JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalWtScheduleShiftByParam(Long workingGroupId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchWtScheduleShiftByParam(workingGroupId, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchWtScheduleShiftByParam(Long workingGroupId, Criteria criteria) {
        if (workingGroupId != null) {
            criteria.createAlias("wtGroupWorking", "wg");
            criteria.add(Restrictions.eq("wg.id", workingGroupId));

        }

    }

    @Override
    public void saveAndMerge(WtScheduleShift scheduleShift) {
        getCurrentSession().update(scheduleShift);
        getCurrentSession().flush();

    }

    @Override
    public void saveBatach(List<WtScheduleShift> dataToBacth) {
        int counter = 0;
        for (WtScheduleShift dataToBacth1 : dataToBacth) {
            getCurrentSession().save(dataToBacth1);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<WtScheduleShift> getAllByWorkingGroupId(long workingGroupId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("wtGroupWorking", "wg");
        criteria.add(Restrictions.eq("wg.id", workingGroupId));
        return criteria.list();
    }

}
