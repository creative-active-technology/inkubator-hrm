package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.PayTempUploadData;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository(value = "payTempAttendanceStatusDao")
@Lazy
public class PayTempAttendanceStatusDaoImpl extends
		IDAOImpl<PayTempAttendanceStatus> implements PayTempAttendanceStatusDao {


	@Override
	public Class<PayTempAttendanceStatus> getEntityClass() {
		return PayTempAttendanceStatus.class;
	}
        
       @Override
    public List<PayTempAttendanceStatus> getAllByNik(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "emp");     
        criteria.add(Restrictions.eq("emp.nik", nik));
        return criteria.list();

    }

}
