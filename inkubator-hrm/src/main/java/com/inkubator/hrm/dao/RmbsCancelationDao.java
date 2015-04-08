package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsCancelation;

/**
*
* @author rizkykojek
*/
public interface RmbsCancelationDao extends IDAO<RmbsCancelation> {

	public Long getCurrentMaxId();

}
