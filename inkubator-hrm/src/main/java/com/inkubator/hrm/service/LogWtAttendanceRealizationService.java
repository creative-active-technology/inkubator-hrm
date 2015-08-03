package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.WtAttendanceCalculationSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LogWtAttendanceRealizationService extends IService<LogWtAttendanceRealization> {
    
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(WtAttendanceCalculationSearchParameter searchParameter, Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(WtAttendanceCalculationSearchParameter searchParameter, Long wtPeriodId) throws Exception;
    
    public void deleteByPeriodId(Long periodId) throws Exception;

	public void afterMonthEndProcess() throws Exception;

}
