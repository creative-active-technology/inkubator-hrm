package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.web.search.BioAddressSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface BioAddressService extends IService<BioAddress> {

	public void updateMapCoordinate(Long id, String latitude, String longitude) throws Exception;

	public List<BioAddress> getByParam(BioAddressSearchParameter parameter, int first, int pageSize, Order orderable);

	public Long getTotalByParam(BioAddressSearchParameter parameter);
	
}
