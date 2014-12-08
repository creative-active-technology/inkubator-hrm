/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempKalkulasiEmpPajakDao;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiEmpPajakDao")
@Lazy
public class PayTempKalkulasiEmpPajakDaoImpl extends IDAOImpl<PayTempKalkulasiEmpPajak> implements PayTempKalkulasiEmpPajakDao{

    @Override
    public Class<PayTempKalkulasiEmpPajak> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
