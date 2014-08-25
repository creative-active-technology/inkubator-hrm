package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BusinessTravelComponent;

/**
 *
 * @author rizkykojek
 */
public interface BusinessTravelComponentService extends IService<BusinessTravelComponent> {
	
	public List<BusinessTravelComponent> getAllDataByBusinessTravelId(Long businessTravelId) throws Exception;

}
