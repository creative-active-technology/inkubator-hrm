/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.dao.SchedulerLogDao;
import com.inkubator.hrm.entity.SchedulerLog;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
public class BaseSchedulerDinamicListenerImpl extends IServiceImpl {

    @Autowired
    protected SchedulerLogDao schedulerLogDao;
    @Autowired
    protected SchedulerConfigDao schedulerConfigDao;

//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    protected SchedulerLog doSaveSchedulerLogSchedulerLog(SchedulerLog schedulerLog) {
        schedulerLog.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        schedulerLog.setSchedulerConfig(schedulerConfigDao.getEntiyByPK(schedulerLog.getSchedulerConfig().getId()));
        schedulerLog.setStartExecution(new Date());
        schedulerLog.setStatusMessages("PROCESS");
        schedulerLogDao.save(schedulerLog);
        return schedulerLog;

    }

    protected SchedulerLog doUpdateSchedulerLogSchedulerLog(SchedulerLog schedulerLog) {
        schedulerLog.setEndExecution(new Date());
        schedulerLogDao.update(schedulerLog);
        return schedulerLog;
    }
}
