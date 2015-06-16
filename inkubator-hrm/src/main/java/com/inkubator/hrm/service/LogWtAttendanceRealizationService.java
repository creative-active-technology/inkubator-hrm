package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LogWtAttendanceRealizationService extends IService<LogWtAttendanceRealization> {
    
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception;

}