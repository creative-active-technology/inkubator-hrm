/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.web.search.CurrencySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface CurrencyService extends IService<Currency>{
    public List<Currency> getByParam(CurrencySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalCurrencyByParam(CurrencySearchParameter searchParameter) throws Exception;

    public Long getByCurrencyCode(String code) throws Exception;
    
    public Currency getCurrencyByIdWithCountry(Long id) throws Exception;
}
