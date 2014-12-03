/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.TaxRate;

/**
 *
 * @author rizkykojek
 */
public interface TaxRateDao extends IDAO<TaxRate>{
	
    public List<TaxRate> getByParam(int firstResult, int maxResults, Order order);

    public Long getTotalByParam();
    
}
