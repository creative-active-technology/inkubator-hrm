package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

/**
 *
 * @author WebGenX
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

    public Long totalDayPresent();

    public Long totalDaySchedule();

}
