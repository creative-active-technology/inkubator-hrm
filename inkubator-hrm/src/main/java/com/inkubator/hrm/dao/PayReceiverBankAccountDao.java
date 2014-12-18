/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author denifahri
 */
public interface PayReceiverBankAccountDao extends IDAO<PayReceiverBankAccount> {

    public List<PayReceiverBankAccountModel> getByParam(PayReceiverBankAccountSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalByParam(PayReceiverBankAccountSearchParameter searchParameter);
    
    public List<PayReceiverBankAccount> getAllByEmpId(long id) ;
}
