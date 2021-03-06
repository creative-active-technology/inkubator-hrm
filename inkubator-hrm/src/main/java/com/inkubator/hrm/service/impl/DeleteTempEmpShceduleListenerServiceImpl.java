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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            deleteTempEmployeeSchedule();
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

    public void deleteTempEmployeeSchedule() throws Exception {
        LOGGER.warn("Begin Running Delete Temporary Employee Schedule");
        List<TempJadwalKaryawan> dataToDelete = tempJadwalKaryawanDao.getByMonthDif(difNumberOfMonthTempEmployeeScheduleToDelete);
        LOGGER.warn("Size of Data to Delete " + dataToDelete.size());
        tempJadwalKaryawanDao.deleteBacth(dataToDelete);
        tempJadwalKaryawanDao.deleteTempJadwalBeforeTMB();
        LOGGER.warn("Finish Running Delete Temporary Employee Schedule");

    }

    public void setDifNumberOfMonthTempEmployeeScheduleToDelete(int difNumberOfMonthTempEmployeeScheduleToDelete) {
        this.difNumberOfMonthTempEmployeeScheduleToDelete = difNumberOfMonthTempEmployeeScheduleToDelete;
    }
}
