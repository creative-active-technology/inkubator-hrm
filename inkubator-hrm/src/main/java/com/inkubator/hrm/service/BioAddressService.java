package com.inkubator.hrm.service;

import java.util.List;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BioAddress;

/**
 *
 * @author rizkykojek
 */
public interface BioAddressService extends IService<BioAddress> {

	public void updateMapCoordinate(Long id, String latitude, String longitude) throws Exception;

	public List<BioAddress> getAllDataByBioDataId(Long bioDataId) throws Exception;

	public BioAddress getEntityByPKWithDetail(long id) throws Exception;
	
}
