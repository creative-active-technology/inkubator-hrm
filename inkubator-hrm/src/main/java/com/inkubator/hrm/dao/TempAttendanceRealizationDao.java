package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.web.model.TempAttendanceRealizationMonthEndViewModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface TempAttendanceRealizationDao extends IDAO<TempAttendanceRealization> {

    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter searchParameter);

    public Long getTotalEmpLeav();

    public Long getTotalEmpPermit();

    public Long gettotalEmpOnDuty();

    public Long gettotalEmpOnSick();

    public Long getTotalEmpLeav(long empId);

    public Long getTotalEmpPermit(long empId);

    public Long gettotalEmpOnDuty(long empId);

    public Long gettotalEmpOnSick(long empId);

    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable);

    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId);
    
    public void deleteAllData();

    public Long totalDayPresent();

    public Long totalDaySchedule();
    
    public List<TempAttendanceRealizationMonthEndViewModel> getAllDataMonthEndByPeriodId(Long wtPeriodId);

    public Long getTotalOverTime(long empId);

    public TempAttendanceRealization getByEmp(long empId);

}
