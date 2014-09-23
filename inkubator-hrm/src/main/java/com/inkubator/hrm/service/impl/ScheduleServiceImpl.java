/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.RiwayatAksesDao;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.service.ScheduleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class ScheduleServiceImpl extends IServiceImpl implements ScheduleService {

    private int difWeekToDelete;
    @Autowired
    private RiwayatAksesDao riwayatAksesDao;

    @Scheduled(cron = "${cron.delete.riwayat.akses.history}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteRiwayatAkses() throws Exception {
        LOGGER.info("Begin Running Dellete Riwayar Akses");
        List<RiwayatAkses> dataToDelete = riwayatAksesDao.getByWeekDif(difWeekToDelete);
          LOGGER.info("Ukuran Data to Delete "+dataToDelete.size());
        riwayatAksesDao.deleteBatch(dataToDelete);
        LOGGER.info("Finish Running Dellete Riwayar Akses");
    }

    public void setDifWeekToDelete(int difWeekToDelete) {
        this.difWeekToDelete = difWeekToDelete;
    }

}
