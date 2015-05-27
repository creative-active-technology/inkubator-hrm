package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.WtEmpCorrectionAttendanceDetailDao;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;

/**
*
* @author rizkykojek
*/
@Repository(value = "wtEmpCorrectionAttendanceDetailDao")
@Lazy
public class WtEmpCorrectionAttendanceDetailDaoImpl extends IDAOImpl<WtEmpCorrectionAttendanceDetail> implements WtEmpCorrectionAttendanceDetailDao {

	@Override
	public Class<WtEmpCorrectionAttendanceDetail> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
