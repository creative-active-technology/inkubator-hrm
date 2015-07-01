package com.inkubator.hrm.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.entity.LeaveImplementationDate;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "leaveImplementationDateDao")
@Lazy
public class LeaveImplementationDateDaoImpl extends IDAOImpl<LeaveImplementationDate> implements LeaveImplementationDateDao {

    @Override
    public Class<LeaveImplementationDate> getEntityClass() {
        return LeaveImplementationDate.class;

    }

    @Override
    public LeaveImplementationDate getByLeavIdDateAndIsTrue(long leavId, Date doDate, Boolean param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("leaveImplementation", "lv");
        criteria.add(Restrictions.eq("lv.id", leavId));
        criteria.add(Restrictions.eq("actualDate", doDate));
        criteria.add(Restrictions.eq("isCancelled", param));
        return (LeaveImplementationDate) criteria.uniqueResult();
    }

	@Override
	public Long getTotalActualLeave(Date date) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("leaveImplementation", "leaveImplementation", JoinType.INNER_JOIN);
		criteria.createAlias("leaveImplementation.empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("actualDate", date));
        criteria.add(Restrictions.eq("isCancelled", Boolean.FALSE));
        criteria.add(Restrictions.not(Restrictions.eq("empData.status", HRMConstant.EMP_TERMINATION)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

}
