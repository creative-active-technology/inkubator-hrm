/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.BankGroup;
import com.inkubator.hrm.web.search.BankGroupSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author EKA
 */
public interface BankGroupService extends IService<BankGroup>{
    public List<BankGroup> getByParam(BankGroupSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;
    
    public Long getTotalBankGroupByParam(BankGroupSearchParameter searchParameter) throws Exception;
}
