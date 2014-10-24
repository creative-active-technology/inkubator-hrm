package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
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
	
}
