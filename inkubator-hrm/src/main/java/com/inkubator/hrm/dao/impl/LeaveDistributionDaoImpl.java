/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LeaveDistributionDao;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.web.search.LeaveDistributionSearchParameter;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "leaveDistributionDao")
@Lazy
public class LeaveDistributionDaoImpl extends IDAOImpl<LeaveDistribution> implements LeaveDistributionDao {

    @Override
    public Class<LeaveDistribution> getEntityClass() {
        return LeaveDistribution.class;
    }

    @Override
    public List<LeaveDistribution> getByParamWithDetail(LeaveDistributionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        //criteria.setFetchMode("empData", FetchMode.JOIN);
        //criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        //criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalLeaveDistributionByParam(LeaveDistributionSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearch(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearch(LeaveDistributionSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("leave", "leave", JoinType.INNER_JOIN);
        
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            /*Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);*/
        	
            criteria.add(Restrictions.ilike("bioData.combineName", searchParameter.getEmpData().toLowerCase(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(searchParameter.getLeave())) {
            criteria.add(Restrictions.like("leave.name", searchParameter.getLeave(), MatchMode.ANYWHERE));
        }
        if (searchParameter.getNik() != null) {
            criteria.add(Restrictions.like("empData.nik", searchParameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public LeaveDistribution getEntityByParamWithDetail(Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", empId));
        return (LeaveDistribution) criteria.uniqueResult();
    }

    @Override
    public List<LeaveDistribution> getAllDataByEmpIdWithDetail() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public void saveBatch(List<LeaveDistribution> data) {
        int counter = 0;
        for (LeaveDistribution distribution : data) {
            getCurrentSession().save(distribution);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<LeaveDistribution> getAllDataByLeaveIdAndIsActiveEmployee(Long leaveId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("leave.id", leaveId));
        criteria.createAlias("empData", "empData");
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("empData.status", HRMConstant.EMP_PLACEMENT));
        disjunction.add(Restrictions.eq("empData.status", HRMConstant.EMP_ROTATION));
        criteria.add(disjunction);
        return criteria.list();
    }

    @Override
    public List<LeaveDistribution> getAllDataByEndDateLessThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.lt("endDate", date));
        return criteria.list();
    }

    @Override
    public List<LeaveDistribution> getAllDataByEmpIdFetchLeave(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.setFetchMode("leave", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public LeaveDistribution getEntityByLeaveIdAndEmpDataId(Long leaveId, Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("leave.id", leaveId));
        return (LeaveDistribution) criteria.uniqueResult();
    }

	@Override
	public List<LeaveDistribution> getAllDataByEmpDataId(Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.setFetchMode("leave", FetchMode.JOIN);
        return criteria.list();
	}

	@Override
	public Long getTotalLeaveDistributionNameAndEmpDataAndNotId(String name, Long empDataId, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("leave", "leave", JoinType.INNER_JOIN);
		
		criteria.add(Restrictions.eq("leave.name", name));
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.ne("id", id));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}


}
