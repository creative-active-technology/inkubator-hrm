package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface TempAttendanceRealizationDao extends IDAO<TempAttendanceRealization> {

	public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable);

	public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId);
	
	public void deleteAllData();

}
