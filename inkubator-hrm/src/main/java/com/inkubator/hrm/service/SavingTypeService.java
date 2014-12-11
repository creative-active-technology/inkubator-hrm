package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author rizkykojek
 */
public interface SavingTypeService extends IService<SavingType> {
    public List<SavingType> getByParam(SavingTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalSavingTypeByParam(SavingTypeSearchParameter searchParameter) throws Exception;
}
