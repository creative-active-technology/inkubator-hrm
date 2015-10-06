/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.SchedulerLogDao;
import com.inkubator.hrm.entity.SchedulerLog;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "schedulerLogDao")
@Lazy
public class SchedulerLogDaoImpl extends IDAOImpl<SchedulerLog> implements SchedulerLogDao{

    @Override
    public Class<SchedulerLog> getEntityClass() {
      return SchedulerLog.class;
    }
    
}
