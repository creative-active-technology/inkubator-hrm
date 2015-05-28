package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;

/**
*
* @author rizkykojek
*/
public interface WtEmpCorrectionAttendanceDao extends IDAO<WtEmpCorrectionAttendance> {

	public Long getCurrentMaxId();

	public WtEmpCorrectionAttendance getEntityByPkWithDetail(Long id);
	
	public List<WtEmpCorrectionAttendance> getByParam(String parameter, int firstResult, int maxResult, Order orderable);
	
	public Long getTotalByParam(String parameter);
	
}
