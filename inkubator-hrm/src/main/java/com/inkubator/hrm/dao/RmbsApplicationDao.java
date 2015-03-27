package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsApplication;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationDao extends IDAO<RmbsApplication> {

	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate);

}
