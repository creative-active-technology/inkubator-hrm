/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedullerConfigDao;
import com.inkubator.hrm.entity.SchedullerConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedullerConfigDao")
@Lazy
public class SchedullerConfigDaoImpl extends IDAOImpl<SchedullerConfig> implements SchedullerConfigDao {

    @Override
    public Class<SchedullerConfig> getEntityClass() {
        return SchedullerConfig.class;
    }

}
