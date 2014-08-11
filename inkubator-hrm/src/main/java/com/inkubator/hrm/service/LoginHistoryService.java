/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface LoginHistoryService extends IService<LoginHistory> {

	public List<LoginHistory> getByParam(int firstResult, int maxResults, Order order) throws Exception;
	
    public List<LoginHistory> getByParam(LoginHistorySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalLoginHistoryByParam(LoginHistorySearchParameter searchParameter) throws Exception;
    
    public void saveAndPushMessage(LoginHistory entity);
    
    public void updateAndPushMessage(LoginHistory entity);
}
