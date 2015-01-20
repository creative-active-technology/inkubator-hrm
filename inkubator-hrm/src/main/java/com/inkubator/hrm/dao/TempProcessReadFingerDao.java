package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TempProcessReadFinger;

/**
 *
 * @author rizkykojek
 */
public interface TempProcessReadFingerDao extends IDAO<TempProcessReadFinger> {

	public List<TempProcessReadFinger> getByParam(Long empDataId, Date startDate, Date endDate, int firstResult, int maxResults, Order orderable) throws Exception;

	public Long getTotalByParam(Long empDataId, Date startDate, Date endDate) throws Exception;
	
}
