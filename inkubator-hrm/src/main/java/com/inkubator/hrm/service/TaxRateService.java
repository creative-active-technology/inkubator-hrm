/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.TaxRate;

/**
 *
 * @author rizkykojek
 */
public interface TaxRateService extends IService<TaxRate> {
	
    public List<TaxRate> getByParam(int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam() throws Exception;
    
}
