/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface PaySalaryComponentService extends IService<PaySalaryComponent> {

    public List<PaySalaryComponent> getByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalResourceTypeByParam(PaySalaryComponentSearchParameter searchParameter) throws Exception;

    public PaySalaryComponent getEntityByPkWithDetail(Long id) throws Exception;

    public Map<String, Long> returnComponentChange(Long id) throws Exception;
}
