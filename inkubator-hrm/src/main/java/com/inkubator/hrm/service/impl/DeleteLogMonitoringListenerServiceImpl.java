/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class DeleteLogMonitoringListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    private int difMonthLogMonitoringToDelete;
//    @Autowired
//    private SchedulerLogDao schedulerLogDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            deleteLoginMonitoring();
            log.setStatusMessages("FINISH");
            super.doUpdateSchedulerLogSchedulerLog(log);
        } catch (Exception ex) {
            if (log != null) {
                log.setStatusMessages(ex.getMessage());
                super.doUpdateSchedulerLogSchedulerLog(log);
            }
            LOGGER.error(ex, ex);
        }
    }

    public void deleteLoginMonitoring() throws Exception {
        LOGGER.warn("Begin Running Dellete Log Monitoring Scheduler");
        List<SchedulerLog> dataToDelete = schedulerLogDao.getByMonthDif(difMonthLogMonitoringToDelete);
        LOGGER.warn("Ukuran Data to Delete " + dataToDelete.size());
        schedulerLogDao.deleteBatch(dataToDelete);
        LOGGER.warn("Finish Running Dellete Log Monitoring Scheduler");
    }

    public void setDifMonthLogMonitoringToDelete(int difMonthLogMonitoringToDelete) {
        this.difMonthLogMonitoringToDelete = difMonthLogMonitoringToDelete;
    }

}
