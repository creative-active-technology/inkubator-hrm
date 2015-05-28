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
import com.inkubator.hrm.dao.WtEmpCorrectionAttendanceDao;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.web.search.EmpCorrectionAttendanceSearchParameter;

/**
*
* @author rizkykojek
*/
@Repository(value = "wtEmpCorrectionAttendanceDao")
@Lazy
public class WtEmpCorrectionAttendanceDaoImpl extends IDAOImpl<WtEmpCorrectionAttendance> implements WtEmpCorrectionAttendanceDao {

	@Override
	public Class<WtEmpCorrectionAttendance> getEntityClass() {
		// TODO Auto-generated method stub
		return WtEmpCorrectionAttendance.class;
	}

	@Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }

	@Override
	public WtEmpCorrectionAttendance getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("wtEmpCorrectionAttendanceDetails", FetchMode.JOIN);
		criteria.setFetchMode("empData", FetchMode.JOIN);
		criteria.setFetchMode("empData.wtGroupWorking", FetchMode.JOIN);
		criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
		return (WtEmpCorrectionAttendance) criteria.uniqueResult();
	}

	@Override
	public List<WtEmpCorrectionAttendance> getByParam(EmpCorrectionAttendanceSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(EmpCorrectionAttendanceSearchParameter parameter) {        
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(EmpCorrectionAttendanceSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
        	Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if(parameter.getEmpDataId() != null){
        	criteria.add(Restrictions.eq("empData.id", parameter.getEmpDataId()));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }
	

}
