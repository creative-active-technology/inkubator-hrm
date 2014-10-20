package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "leaveImplementationDao")
@Lazy
public class LeaveImplementationDaoImpl extends IDAOImpl<LeaveImplementation> implements LeaveImplementationDao {

	@Override
	public Class<LeaveImplementation> getEntityClass() {
		return LeaveImplementation.class;
		
	}

	@Override
	public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(LeaveImplementationSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(LeaveImplementationSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getLeave())) {
        	criteria.createAlias("leave", "leave", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("leave.name", parameter.getLeave(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
        	criteria.createAlias("empData.bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotEmpty(parameter.getNumberFilling())){
        	criteria.add(Restrictions.like("numberFilling", parameter.getNumberFilling(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
	
	@Override
	public LeaveImplementation getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
		criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
		criteria.setFetchMode("leave", FetchMode.JOIN);
		return (LeaveImplementation) criteria.uniqueResult();
	}

	@Override
	public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.addOrder(orderable);
		return criteria.list();
	}

	@Override
	public long getTotalByNumberFilling(String numberFilling) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public long getTotalByNumberFillingAndNotId(String numberFilling, Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}
