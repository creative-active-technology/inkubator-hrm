package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.web.search.UnregPayComponentsSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface UnregPayComponentsService extends IService<UnregPayComponents> {

	public List<UnregPayComponents> getByParam(UnregPayComponentsSearchParameter searchParameter, int first, int pageSize, Order order);

	public Long getTotalByParam(UnregPayComponentsSearchParameter searchParameter);

	public UnregPayComponents getEntityByPkWithDetail(Long unregPayComponentsId);

}
