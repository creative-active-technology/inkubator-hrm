/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.web.search.LoginHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface LoginHistoryDao extends IDAO<LoginHistory> {

    public List<LoginHistory> getByParam(LoginHistorySearchParameter searchParameter,
            int firstResult, int maxResults, Order order);

    public Long getTotalLoginHistoryByParam(LoginHistorySearchParameter searchParameter);
}
