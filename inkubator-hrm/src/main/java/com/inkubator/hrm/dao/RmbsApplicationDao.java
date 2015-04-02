package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationDao extends IDAO<RmbsApplication> {

	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate);
	
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalUndisbursedByParam(RmbsApplicationUndisbursedSearchParameter parameter);

}
