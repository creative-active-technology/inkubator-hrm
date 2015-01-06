package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentsSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface UnregPayComponentsDao extends IDAO<UnregPayComponents> {

	public List<UnregPayComponents> getByParam(UnregPayComponentsSearchParameter searchParameter, int firstResult, int maxResults, Order order);

	public Long getTotalByParam(UnregPayComponentsSearchParameter searchParameter);

	public UnregPayComponents getEntityByPkWithDetail(Long unregPayComponentsId);

}
