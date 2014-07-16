/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.InterestType;
import com.inkubator.hrm.web.search.InterestTypeSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface InterestTypeDao extends IDAO<InterestType>{
    public List<InterestType> getByParam(InterestTypeSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalInterestTypeByParam(InterestTypeSearchParameter searchParameter);

    public Long getByInterestTypeName(String name);
    
    public Long getTotalByNameAndNotId(String name, Long id);
}
