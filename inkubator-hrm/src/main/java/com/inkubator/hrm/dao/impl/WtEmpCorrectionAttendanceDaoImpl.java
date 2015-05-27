package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtEmpCorrectionAttendanceDao;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;

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
	

}
