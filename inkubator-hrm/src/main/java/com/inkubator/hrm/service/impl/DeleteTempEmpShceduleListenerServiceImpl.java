/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.dao.LoginHistoryDao;
import com.inkubator.hrm.dao.RiwayatAksesDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
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
public class DeleteTempEmpShceduleListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    private int difNumberOfMonthTempEmployeeScheduleToDelete;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
     
        try {
            TextMessage textMessage = (TextMessage) msg;
            SchedulerLog schedulerLog = new SchedulerLog();
            schedulerLog.setSchedulerConfig(new SchedulerConfig(Long.parseLong(textMessage.getText())));
            SchedulerLog log = super.doSaveSchedulerLogSchedulerLog(schedulerLog);
            deleteTempEmployeeSchedule();
            log.setStatusMessages("FINISH");
            schedulerLogDao.update(log);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public void deleteTempEmployeeSchedule() throws Exception {
        LOGGER.error("Begin Running Delete Temporary Employee Schedule");
        List<TempJadwalKaryawan> dataToDelete = tempJadwalKaryawanDao.getByMonthDif(difNumberOfMonthTempEmployeeScheduleToDelete);
        LOGGER.error("Size of Data to Delete " + dataToDelete.size());
        tempJadwalKaryawanDao.deleteBacth(dataToDelete);
        LOGGER.error("Finish Running Delete Temporary Employee Schedule");
    }

    public void setDifNumberOfMonthTempEmployeeScheduleToDelete(int difNumberOfMonthTempEmployeeScheduleToDelete) {
        this.difNumberOfMonthTempEmployeeScheduleToDelete = difNumberOfMonthTempEmployeeScheduleToDelete;
    }
}
