package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsDisbursement;

/**
 *
 * @author rizkykojek
 */
public interface RmbsDisbursementDao extends IDAO<RmbsDisbursement> {
	
	public Long getCurrentMaxId();

}
