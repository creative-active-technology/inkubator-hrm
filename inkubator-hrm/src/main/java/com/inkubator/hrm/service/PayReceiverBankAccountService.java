/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayReceiverBankAccountService extends IService<PayReceiverBankAccount> {

    public List<PayReceiverBankAccountModel> getByParam(PayReceiverBankAccountSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalByParam(PayReceiverBankAccountSearchParameter searchParameter) throws Exception;

	public List<PayReceiverBankAccount> getAllDataWithDetail() throws Exception;
}
