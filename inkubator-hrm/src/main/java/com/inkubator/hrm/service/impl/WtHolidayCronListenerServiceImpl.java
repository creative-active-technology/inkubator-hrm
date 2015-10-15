/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.entity.WtHoliday;
import java.util.Date;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class WtHolidayCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    private int difWeekToDelete;
    @Autowired
    private WtHolidayDao wtHolidayDao;

    public void updateWtHolidayDateWhereIsEveryYearIsOne() throws Exception {
        LOGGER.info("Begin Running Update WtHoliday");
        //ambil bulan dan taun januari sekarang
        DateTime monthAndYearNow = new DateTime();
        List<WtHoliday> dataToUpdate = wtHolidayDao.getByYearDif(difWeekToDelete);
        LOGGER.info("Ukuran Data to Update " + dataToUpdate.size());
        Date date = new Date();
        WtHoliday newData;
        Long totalDuplicat;
        for (WtHoliday wtHoliday : dataToUpdate) {
            //ambil bulan dan tahun yang akan di update
            DateTime monthAndYearBefore = new DateTime(wtHoliday.getHolidayDate());
            //jika tahun di database lebih kecil tahun sekarang
            if (monthAndYearBefore.getYear() < monthAndYearNow.getYear() && monthAndYearBefore.getMonthOfYear() >= monthAndYearNow.getMonthOfYear()) {
//           
                //cari nama + tahun, jika sudah ada skip, karena unik
                totalDuplicat = wtHolidayDao.getTotalWtHolidayByHolidayName(wtHoliday.getHolidayName() + " " + monthAndYearNow.getYear());
                if (totalDuplicat == 0) {
                    Date updateHolidayDate = DateTimeUtil.getDateFrom(wtHoliday.getHolidayDate(), 1, CommonUtilConstant.DATE_FORMAT_YEAR);
                    newData = new WtHoliday();
                    newData.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                    newData.setHolidayName(wtHoliday.getHolidayName() + " " + monthAndYearNow.getYear());
                    newData.setIsColectiveLeave(wtHoliday.getIsColectiveLeave());
                    newData.setIsEveryYear(wtHoliday.getIsEveryYear());
                    if (wtHoliday.getReligion() != null) {
                        newData.setReligion(wtHoliday.getReligion());
                    }
                    newData.setCreatedBy(HRMConstant.INKUBA_SYSTEM);
                    newData.setCreatedOn(new Date());
                    newData.setHolidayDate(updateHolidayDate);
                    wtHolidayDao.save(newData);
                }
            }
        }
        LOGGER.info("Finish Running Update Wt Holiday");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            SchedulerLog schedulerLog = new SchedulerLog();
            schedulerLog.setSchedulerConfig(new SchedulerConfig(Long.parseLong(textMessage.getText())));
            log = super.doSaveSchedulerLogSchedulerLog(schedulerLog);
            updateWtHolidayDateWhereIsEveryYearIsOne();
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

    public void setDifWeekToDelete(int difWeekToDelete) {
        this.difWeekToDelete = difWeekToDelete;
    }
    
    
}
