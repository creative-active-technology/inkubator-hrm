package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface SavingTypeDao extends IDAO<SavingType> {
    public List<SavingType> getByParam(SavingTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalSavingTypeByParam(SavingTypeSearchParameter searchParameter);
}
