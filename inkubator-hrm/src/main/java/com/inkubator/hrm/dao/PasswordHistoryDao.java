/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.web.search.PasswordHistorySearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface PasswordHistoryDao extends IDAO<PasswordHistory> {

    public List<PasswordHistory> getByParam(PasswordHistorySearchParameter searchParameter,
            int firstResult, int maxResults, Order order);

    public Long getTotalLoginHistoryByParam(PasswordHistorySearchParameter searchParameter);

    public List<PasswordHistory> getByEmailNotification(Integer emailNotification);
}
