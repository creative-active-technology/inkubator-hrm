/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.service.ScheduleDinamicService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
public class ScheduleDinamicServiceImpl extends IServiceImpl implements ScheduleDinamicService {

    @Autowired
    private SchedulerConfigDao schedulerConfigDao;

    @Scheduled(cron = "${cron.dinamic.scheduler}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void doDinamicCheck() throws Exception {

        LOGGER.warn(" Eksekusi pada :" + new SimpleDateFormat("dd MMMM yyyy HH:mm:ss").format(new Date()));
        List<SchedulerConfig> allScheduleActive = schedulerConfigDao.getAllData(Boolean.TRUE);
        for (SchedulerConfig config : allScheduleActive) {
            if ("REPEAT".equals(config.getSchedullerType())) {
                if ("EVERY_DAY".equals(config.getRepeateType())) {
                    LOGGER.warn("Jenis Jadwal :" + config.getSchedullerType());
                    LOGGER.warn("Kategori Jenis Pengulangan :" + config.getRepeateType());
                    doCheckCurrentDate(config);
                }
                if ("EVERY_WEEK".equals(config.getRepeateType())) {
                    LOGGER.warn("Jenis Jadwal :" + config.getSchedullerType());
                    LOGGER.warn("Kategori Jenis Pengulangan :" + config.getRepeateType());
                    doCheckCurrentDate(config);
                }
                if ("EVERY_MONTH".equals(config.getRepeateType())) {
                    LOGGER.warn("Jenis Jadwal :" + config.getSchedullerType());
                    LOGGER.warn("Kategori Jenis Pengulangan :" + config.getRepeateType());
                    doCheckCurrentDate(config);
                }
                if ("EVERTY_YEAR".equals(config.getRepeateType())) {
                    LOGGER.warn("Jenis Jadwal :" + config.getSchedullerType());
                    LOGGER.warn("Kategori Jenis Pengulangan :" + config.getRepeateType());
                    doCheckCurrentDate(config);
                }
            }

            if ("ONCE".equals(config.getSchedullerType())) {
                LOGGER.warn(" Eksekusi pada :" + config.getSchedullerType());
            }

            if ("RANGE_TIME".equals(config.getSchedullerType())) {
                LOGGER.warn(" Eksekusi pada :" + config.getSchedullerType());
            }
        }
    }

    private void doCheckCurrentDate(SchedulerConfig config) {
        try {
            Date doDateExecution = config.getDateStartExecution();
            String currentDateString = new SimpleDateFormat("dd MM yyyy").format(new Date());
            Date currentDate = new SimpleDateFormat("dd MM yyyy").parse(currentDateString);
            if (doDateExecution.equals(currentDate) || doDateExecution.before(currentDate)) {
                if (config.getIsTimeDiv()) {
//                    LOGGER.warn("Di eksekusi tiap " + config.getTimeDivExecution());
                    Date lastExecution = config.getLastExecution();
                    LOGGER.warn("Last Eksekusinya adalahhhhhhh " + config.getLastExecution());
                    Date currentDateTime = new Date();
                    if (lastExecution != null) {
                        LOGGER.warn("Di eksekusi karena tiap jeda waktu dana daa lasst ++++++++++++++++++++++++==================");
                        LOGGER.warn(" Current Date :" + currentDateTime.getTime());
                        LOGGER.warn(" Lase Execution :" + lastExecution.getTime());
//                        LOGGER.warn(" Time Div :" +  config.getTimeDivExecution().getTime());
//                        if ((currentDateTime.getTime() - lastExecution.getTime()) == config.getTimeDivExecution().getTime()) {
//                            LOGGER.warn("Lakukan ekseskusi scheduler jeda waktu 00000000000000000000000000000 " + new Date());
//                        }
                    }
                } else {
                    String currentDateStringTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    Date currentDateTime = new SimpleDateFormat("HH:mm:ss").parse(currentDateStringTime);
                    LOGGER.warn("tanggal eksekusi :" + config.getSchedullerTime().getTime());
                    LOGGER.warn("tanggal sekarang :" + currentDateTime.getTime());
                    if (currentDateTime.getTime() == config.getSchedullerTime().getTime()) {

                        LOGGER.warn("Lakukan ekseskusi scheduler [pada :" + config.getSchedullerTime());
                    }
                }
                LOGGER.warn("Tanggalnya sekarang sama atau sudah lewat:");
            }
            LOGGER.warn("Taanggal Mulai eksekusi :" + config.getDateStartExecution());
            LOGGER.warn("Tanggal Hari ini :" + new Date());
        } catch (ParseException ex) {
            Logger.getLogger(ScheduleDinamicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
