package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface TempProcessReadFingerDao extends IDAO<TempProcessReadFinger> {

    public List<TempProcessReadFinger> getByParam(Long empDataId, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalByParam(Long empDataId) throws Exception;

    public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter);

    public void deleteByScheduleDateAndIsNotCorrection(Date fromPeriode, Date untilPeriode);

    public TempProcessReadFinger getEntityByEmpDataIdAndScheduleDateAndScheduleInAndScheduleOut(Long empDataId, Date scheduleDate, Date scheduleIn, Date scheduleOut);

    public List<TempProcessReadFinger> getAllDataByEmpDataIdAndScheduleDate(Long empDataId, Date startDate, Date endDate);

    public TempProcessReadFinger getEntityByEmpDataIdAndScheduleDate(Long empDataId, Date scheduleDate);
    
    public Long getEmpTotalAttendanceBetweenDateFromAndDateUntill(Long empDataId, Date dateFrom, Date dateUntill);

    public Long getTotalTimeDeviation(long empid);

    public List<TempProcessReadFinger> getAllDataByEmpDataId(Long id);

}
