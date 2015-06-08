package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LogAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface LogAttendanceRealizationDao extends IDAO<LogAttendanceRealization> {

    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable);

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId);
}
