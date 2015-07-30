package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.web.model.LogWtAttendanceRealizationModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LogWtAttendanceRealizationService extends IService<LogWtAttendanceRealization> {
    
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception;
    
    public void deleteByPeriodId(Long periodId) throws Exception;

    public void afterMonthEndProcess() throws Exception;
    
    public List<LogWtAttendanceRealization> getPaidOvertimeByParam(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalPaidOvertimeByParam(Long wtPeriodId) throws Exception;
    
    public List<LogWtAttendanceRealization> getAllDataByParam(LogWtAttendanceRealizationModel model, int firstResult, int maxResults, Order orderable) throws Exception;
    
    public Long getTotalDataByParam(LogWtAttendanceRealizationModel model) throws Exception;


}
