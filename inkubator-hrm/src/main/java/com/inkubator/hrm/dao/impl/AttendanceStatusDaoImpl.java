/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.web.search.AttendanceStatusSearchParamater;
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
@Repository(value = "attendanceStatusDao")
@Lazy
public class AttendanceStatusDaoImpl extends IDAOImpl<AttendanceStatus> implements AttendanceStatusDao {

    @Override
    public Class<AttendanceStatus> getEntityClass() {
        return AttendanceStatus.class;
    }

    @Override
    public List<AttendanceStatus> getByParam(AttendanceStatusSearchParamater searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAttendanceStatusByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAttendanceStatusyParam(AttendanceStatusSearchParamater searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchAttendanceStatusByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchAttendanceStatusByParam(AttendanceStatusSearchParamater searchParamater, Criteria criteria) {
        if (searchParamater.getCodeStatus() != null && !searchParamater.getCodeStatus().isEmpty()) {
            criteria.add(Restrictions.like("code", searchParamater.getCodeStatus(), MatchMode.START));
        }

        if (searchParamater.getStatusName() != null && !searchParamater.getStatusName().isEmpty()) {
            criteria.add(Restrictions.like("statusKehadrian", searchParamater.getStatusName(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalDuplicateByCode(String statusCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", statusCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalDuplicaByNameAndNotId(String statusCode, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", statusCode));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public AttendanceStatus getByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (AttendanceStatus) criteria.uniqueResult();
    }
}
