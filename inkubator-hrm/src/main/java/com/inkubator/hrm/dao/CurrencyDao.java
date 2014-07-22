/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Currency;
import com.inkubator.hrm.web.search.CurrencySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni
 */
public interface CurrencyDao extends IDAO<Currency>{
    public List<Currency> getByParam(CurrencySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalCurrencyByParam(CurrencySearchParameter searchParameter);

    public Long getByCurrencyCode(String code);
    
    public Long getTotalByCodeAndNotId(String code, Long id);
    
    public Currency getCurrencyByIdWithCountry(Long id);
}
