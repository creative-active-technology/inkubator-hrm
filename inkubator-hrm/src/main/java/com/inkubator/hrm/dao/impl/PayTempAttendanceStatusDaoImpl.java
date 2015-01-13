package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempAttendanceStatusDao;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;

public class PayTempAttendanceStatusDaoImpl extends
		IDAOImpl<PayTempAttendanceStatus> implements PayTempAttendanceStatusDao {


	@Override
	public Class<PayTempAttendanceStatus> getEntityClass() {
		return PayTempAttendanceStatus.class;
	}

}
