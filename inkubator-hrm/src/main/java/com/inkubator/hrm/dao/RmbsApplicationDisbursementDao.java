package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.web.search.RmbsDisbursementSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface RmbsApplicationDisbursementDao extends IDAO<RmbsApplicationDisbursement> {

	public List<RmbsApplicationDisbursement> getByParam(RmbsDisbursementSearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RmbsDisbursementSearchParameter parameter);

}
