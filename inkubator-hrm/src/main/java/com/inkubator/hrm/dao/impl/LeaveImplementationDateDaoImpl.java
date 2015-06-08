package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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

}
