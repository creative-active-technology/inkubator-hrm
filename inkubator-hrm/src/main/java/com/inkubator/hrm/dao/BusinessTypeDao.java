/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.web.search.BusinessTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface BusinessTypeDao extends IDAO<BusinessType> {
    public List<BusinessType> getByParam(BusinessTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalBusinessTypeByParam(BusinessTypeSearchParameter searchParameter);
    
}
