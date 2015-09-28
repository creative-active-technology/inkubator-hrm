/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedulerConfigDao")
@Lazy
public class SchedulerConfigDaoImpl extends IDAOImpl<SchedulerConfig> implements SchedulerConfigDao {

    @Override
    public Class<SchedulerConfig> getEntityClass() {
        return SchedulerConfig.class;
    }

}
