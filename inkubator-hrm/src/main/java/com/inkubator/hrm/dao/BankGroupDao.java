/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.web.search.BankGroupSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface BankGroupDao extends IDAO<BankGroup> {
    public List<BankGroup> getByParam(BankGroupSearchParameter searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalBankGroupByParam(BankGroupSearchParameter searchParameter);
}
