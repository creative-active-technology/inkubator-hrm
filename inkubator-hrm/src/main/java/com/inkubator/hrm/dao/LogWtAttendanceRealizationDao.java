package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.web.model.LogWtAttendanceRealizationModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LogWtAttendanceRealizationDao extends IDAO<LogWtAttendanceRealization> {

    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable);

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId);

    public void deleteByPeriodId(Long periodId);

    public Long getTotalPaidOvertimeByParam(Long wtPeriodId);
    
    public List<LogWtAttendanceRealization> getAllDataByParam(LogWtAttendanceRealizationModel model, int firstResult,int maxResults, Order orderable);
    
    public Long getTotalDataByParam(LogWtAttendanceRealizationModel model);
    
    public List<LogWtAttendanceRealization> getAllDataByPeriodId(Long wtPeriodId);
        
}
