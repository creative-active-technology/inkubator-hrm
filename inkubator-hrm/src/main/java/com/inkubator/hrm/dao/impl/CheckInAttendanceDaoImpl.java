/*
 <<<<<<< HEAD
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CheckInAttendanceDao;
import com.inkubator.hrm.entity.CheckInAttendance;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.search.CheckInAttendanceSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "checkInAttendanceDao")
@Lazy
public class CheckInAttendanceDaoImpl extends IDAOImpl<CheckInAttendance> implements CheckInAttendanceDao {

    @Override
    public Class<CheckInAttendance> getEntityClass() {
        return CheckInAttendance.class;
    }

    @Override
    public List<CheckInAttendance> getByParamWithDetail(CheckInAttendanceSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalCheckInAttendanceByParam(CheckInAttendanceSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(CheckInAttendanceSearchParameter searchParameter, Criteria criteria) {
    	
        if (searchParameter.getEmpData() != null) {
        	criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        	criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        	Disjunction disjunction = Restrictions.disjunction();
        	disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
        	disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
        	criteria.add(disjunction);
        }
        if (searchParameter.getIpAddress() != null) {
        	criteria.add(Restrictions.like("ipAddress", searchParameter.getIpAddress(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public CheckInAttendance getByEmpIdAndCheckIn(long id, Date checkInDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ep", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ep.id", id));
        criteria.add(Restrictions.eq("checkDate", checkInDate));
        return (CheckInAttendance) criteria.uniqueResult();
    }

    @Override
    public CheckInAttendance getAttendancWithMaxCreatedDate(long id) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("createdOn").max());
        DetachedCriteria maxDate = DetachedCriteria.forClass(getEntityClass())
                .createAlias("empData", "ep")
                .add(Restrictions.eq("ep.id", id))
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "ep", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ep.id", id));
        criteria.add(Property.forName("createdOn").eq(maxDate));
        return (CheckInAttendance) criteria.uniqueResult();
    }

	@Override
	public CheckInAttendance getEntityByEmpDataIdAndCheckDate(Long empDataId, Date checkDate) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.eq("checkDate", checkDate));
		return (CheckInAttendance) criteria.uniqueResult();
	}

}
