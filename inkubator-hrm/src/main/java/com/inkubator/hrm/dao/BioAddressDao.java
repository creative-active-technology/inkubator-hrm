package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioAddress;

/**
 *
 * @author rizkykojek
 */
public interface BioAddressDao extends IDAO<BioAddress> {

	List<BioAddress> getAllDataByBioDataId(Long bioDataId);
	
}
