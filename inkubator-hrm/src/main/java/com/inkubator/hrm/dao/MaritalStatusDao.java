/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.web.search.MaritalStatusSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface MaritalStatusDao extends IDAO<MaritalStatus>{
    public List<MaritalStatus> getByParam(MaritalStatusSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalMaritalStatusByParam(MaritalStatusSearchParameter searchParameter);

    public Long getByMaritalStatusName(String name);
    
    public Long getTotalByNameAndNotId(String name, Long id);
}
