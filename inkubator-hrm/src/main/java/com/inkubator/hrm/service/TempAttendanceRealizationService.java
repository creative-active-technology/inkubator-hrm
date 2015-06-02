package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface TempAttendanceRealizationService extends IService<TempAttendanceRealization> {

    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter searchParameter) throws Exception;

    public RealizationAttendanceModel getStatisticEmpAttendaceRealization() throws Exception;
}
