package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.SystemLetterReference;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface SystemLetterReferenceService extends IService<SystemLetterReference> {

    public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter searchParameter) throws Exception;
}
