/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface PayComponentDataExceptionService  extends IService<PayComponentDataException>{
    public List<PayComponentDataException> getByParamWithDetailForDetail(String searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParamForDetail(String searchParameter) throws Exception;
    
    public PayComponentDataException getByPaySalaryComponentId(Long id) throws Exception;
    
    public List<PayComponentDataException> getByPaySalaryComponent(Long id) throws Exception;


}
