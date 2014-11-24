/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.entity.PaySalaryComponent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "paySalaryComponentDao")
@Lazy
public class PaySalaryComponentDaoImpl extends IDAOImpl<PaySalaryComponent> implements PaySalaryComponentDao {

    @Override
    public Class<PaySalaryComponent> getEntityClass() {
        return PaySalaryComponent.class;
    }


}
