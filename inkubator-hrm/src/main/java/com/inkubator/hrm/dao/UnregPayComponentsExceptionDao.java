package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.UnregPayComponentsException;
import com.inkubator.hrm.entity.UnregPayComponentsExceptionId;
import com.inkubator.hrm.web.search.UnregPayComponentExceptionSearchParameter;

/**
 *
 * @author rizkykojek
 */
public interface UnregPayComponentsExceptionDao extends IDAO<UnregPayComponentsException> {
	
	public List<UnregPayComponentsException> getByParam(UnregPayComponentExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(UnregPayComponentExceptionSearchParameter searchParameter);

	public UnregPayComponentsException getEntityByPK(UnregPayComponentsExceptionId id);
	
	public List<UnregPayComponentsException> getAllDataByUnregSalaryId(Long unregSalaryId);

}
