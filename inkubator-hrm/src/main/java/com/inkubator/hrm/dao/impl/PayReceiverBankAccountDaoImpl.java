/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayReceiverBankAccountDao;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "payReceiverBankAccountDao")
@Lazy
public class PayReceiverBankAccountDaoImpl extends IDAOImpl<PayReceiverBankAccount> implements PayReceiverBankAccountDao {

    @Override
    public Class<PayReceiverBankAccount> getEntityClass() {
        return PayReceiverBankAccount.class;
    }

}
