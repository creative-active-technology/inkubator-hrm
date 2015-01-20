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

	public List<TempProcessReadFinger> getByParam(Long empDataId, Date startDate, Date endDate, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(Long empDataId, Date startDate, Date endDate) throws Exception;

	public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter);

	public void deleteByScheduleDate(Date fromPeriode, Date untilPeriode);
	
}
