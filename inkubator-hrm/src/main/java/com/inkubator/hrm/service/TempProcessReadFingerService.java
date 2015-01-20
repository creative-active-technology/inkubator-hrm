package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface TempProcessReadFingerService extends IService<TempProcessReadFinger> {
	
	public List<TempProcessReadFinger> getByParam(Long empDataId, Long periodeId, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(Long empDataId, Long periodeId) throws Exception;
	
	public List<DataFingerRealizationModel> getDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalDataFingerRealizationByParam(DataFingerRealizationSearchParameter searchParameter) throws Exception;
	
	public void synchDataFingerRealization() throws Exception;
	
}
