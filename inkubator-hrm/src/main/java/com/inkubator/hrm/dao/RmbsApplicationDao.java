package com.inkubator.hrm.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.model.RmbsHistoryViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationDao extends IDAO<RmbsApplication> {

	public BigDecimal getTotalNominalByEmpDataIdAndRmbsTypeIdAndDateBetween(Long empDataId, Long rmbsTypeId, Date startDate, Date endDate);

	public Long getCurrentMaxId();
	
	public RmbsApplication getEntityByPkWithDetail(Long id);
	
	public List<RmbsHistoryViewModel> getAllDataHistoryBetweenDate(Long empDataId, Date startDate, Date endDate);
	
	
	//pageable
	public List<RmbsApplicationUndisbursedViewModel> getUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalUndisbursedActivityByParam(RmbsApplicationUndisbursedSearchParameter parameter);
	
	public List<RmbsApplication> getUndisbursedByParam(int firstResult, int maxResults, Order orderable);

	public Long getTotalUndisbursedByParam();

	public List<RmbsApplication> getReimbursementHistoryByParam(Long empDataId, int first, int pageSize, Order orderable);

	public Long getTotalReimbursementHistoryByParam(Long empDataId);

}
