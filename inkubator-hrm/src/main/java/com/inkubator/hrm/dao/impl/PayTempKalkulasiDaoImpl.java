/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiDao")
@Lazy
public class PayTempKalkulasiDaoImpl extends IDAOImpl<PayTempKalkulasi> implements PayTempKalkulasiDao {

    @Override
    public Class<PayTempKalkulasi> getEntityClass() {
        return PayTempKalkulasi.class;
    }

}
