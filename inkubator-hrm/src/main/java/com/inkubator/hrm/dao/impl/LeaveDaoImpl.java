package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LeaveDao;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.web.search.LeaveSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "leaveDao")
@Lazy
public class LeaveDaoImpl extends IDAOImpl<Leave> implements LeaveDao {

    @Override
    public Class<Leave> getEntityClass() {
        return Leave.class;
    }

    @Override
    public List<Leave> getByParam(LeaveSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(LeaveSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LeaveSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNameAndNotId(String name, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Leave getEntityByPkFetchAttendStatus(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("attendanceStatus", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (Leave) criteria.uniqueResult();
    }

	@Override
	public Leave getEntityByPkFetchApprovalDefinition(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("approvalDefinitionLeaves", FetchMode.JOIN);
        criteria.setFetchMode("approvalDefinitionLeaves.approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (Leave) criteria.uniqueResult();
	}
}
