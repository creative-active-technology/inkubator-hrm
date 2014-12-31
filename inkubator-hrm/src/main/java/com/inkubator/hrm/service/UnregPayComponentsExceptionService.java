package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface UnregPayComponentsExceptionService extends IService<UnregPayComponentsException> {

	public List<UnregPayComponentsException> getByParam(UnregPayComponentExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(UnregPayComponentExceptionSearchParameter searchParameter);

	public UnregPayComponentsException getEntityByPK(UnregPayComponentsExceptionId id);

	public void  update(UnregPayComponentsException entity, Long oldEmpDataId) throws Exception;
    
}
