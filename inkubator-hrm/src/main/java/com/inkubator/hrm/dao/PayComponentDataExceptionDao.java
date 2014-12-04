/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PayComponentDataExceptionDao extends IDAO<PayComponentDataException> {
    public List<PayComponentDataException> getByParamWithDetailForDetail(String searchParameter, String paySalaryComponentId, int firstResult, int maxResults, Order order);

    public Long getTotalByParamForDetail(String searchParameter, String paySalaryComponentId);
    
    public PayComponentDataException getByPaySalaryComponentId(Long id);
    
    public List<PayComponentDataException> getByPaySalaryComponent(Long id);
    
}

