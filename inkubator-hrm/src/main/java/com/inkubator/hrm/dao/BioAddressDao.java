package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.web.search.BioAddressSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface BioAddressDao extends IDAO<BioAddress> {

	public List<BioAddress> getByParam(BioAddressSearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalByParam(BioAddressSearchParameter parameter);
	
}
