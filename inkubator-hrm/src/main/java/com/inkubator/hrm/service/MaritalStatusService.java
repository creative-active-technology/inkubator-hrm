/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.web.search.MaritalStatusSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface MaritalStatusService extends IService<MaritalStatus>{
    public List<MaritalStatus> getByParam(MaritalStatusSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalMaritalStatusByParam(MaritalStatusSearchParameter searchParameter) throws Exception;

    public Long getByMaritalStatusName(String name) throws Exception;
    
}
