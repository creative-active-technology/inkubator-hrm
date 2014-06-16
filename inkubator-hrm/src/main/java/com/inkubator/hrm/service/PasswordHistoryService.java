/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.web.search.PasswordHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author deniarianto
 */
public interface PasswordHistoryService extends IService<PasswordHistory>{
    public List<PasswordHistory> getByParam(PasswordHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalLoginHistoryByParam(PasswordHistorySearchParameter searchParameter) throws Exception;
    
    public void saveAndPushMessage(PasswordHistory entity);
    
    public void updateAndPushMessage(PasswordHistory entity);
}
