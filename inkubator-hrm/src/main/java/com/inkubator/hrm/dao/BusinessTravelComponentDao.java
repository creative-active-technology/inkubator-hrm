package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BusinessTravelComponent;

/**
 *
 * @author rizkykojek
 */
public interface BusinessTravelComponentDao extends IDAO<BusinessTravelComponent> {

	public List<BusinessTravelComponent> getAllDataByBusinessTravelId(Long businessTravelId);

}
