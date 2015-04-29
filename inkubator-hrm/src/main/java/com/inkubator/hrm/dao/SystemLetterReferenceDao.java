package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.SystemLetterReference;
import com.inkubator.datacore.dao.IDAO;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.SystemLetterReferenceSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface SystemLetterReferenceDao extends IDAO<SystemLetterReference> {

    public List<SystemLetterReference> getByParam(SystemLetterReferenceSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalSystemLetterReferenceByParam(SystemLetterReferenceSearchParameter searchParameter);
}
