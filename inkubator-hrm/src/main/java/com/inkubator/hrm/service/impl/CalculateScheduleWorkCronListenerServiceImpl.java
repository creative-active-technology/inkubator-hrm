/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class CalculateScheduleWorkCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

//    private int difWeekToDelete;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;

    public void calculateScheduleWorking() throws Exception {
        LOGGER.warn("Begin Running Recalcualte Jadwal Kerja");
        Date now = new Date();
        Date lastDay = DateTimeUtil.getDateFrom(now, -1, CommonUtilConstant.DATE_FORMAT_DAY);
        String dayToInput = new SimpleDateFormat("dd-MM-yyyy").format(lastDay);

        List<TempJadwalKaryawan> data = this.tempJadwalKaryawanDao.getAllByMaxEndDate(new SimpleDateFormat("dd-MM-yyyy").parse(dayToInput));
        List<EmpData> listEmpData = new ArrayList<>();
        List<TempJadwalKaryawan> dataToDelete = new ArrayList<>();
        for (TempJadwalKaryawan data1 : data) {
            listEmpData.add(data1.getEmpData());
        }
        List<TempJadwalKaryawan> dataToSave = new ArrayList<>();
        TempJadwalKaryawan jadwalKaryawan;
        for (EmpData empData : listEmpData) {
            dataToDelete.addAll(tempJadwalKaryawanDao.getAllByEmpId(empData.getId()));
            WtGroupWorking groupWorking = empData.getWtGroupWorking();
            Date startDate = groupWorking.getBeginTime();//harus disini karena untuk emproyee yang berbeda
            Date endDate = groupWorking.getEndTime();
            int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
            int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, now) + 1;
            int num = numberOfDay + 1;
            int hasilBagi = (totalDateDif) / (num);
            Date tanggalAkhirJadwal = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - 1, CommonUtilConstant.DATE_FORMAT_DAY);
//        String dayBegin = new SimpleDateFormat("EEEE").format(endDate);
//        String dayNow = new SimpleDateFormat("EEEE").format(now);
            Date beginScheduleDate;
            if (new SimpleDateFormat("ddMMyyyy").format(tanggalAkhirJadwal).equals(new SimpleDateFormat("ddMMyyyy").format(new Date()))) {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - num, CommonUtilConstant.DATE_FORMAT_DAY);
            } else {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
            }
            List<WtScheduleShift> dataScheduleShift = new ArrayList<>(groupWorking.getWtScheduleShifts());
//            Collections.sort(dataScheduleShift, shortByDate1);
            List<WtScheduleShift> sortedDataScheduleShift = Lambda.sort(dataScheduleShift, Lambda.on(WtScheduleShift.class).getScheduleDate());
            int i = 0;
            for (WtScheduleShift wtScheduleShift : sortedDataScheduleShift) {
                String onlyDate = new SimpleDateFormat("yyyy-MM-dd").format(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                Date olnyDate = new SimpleDateFormat("yyyy-MM-dd").parse(onlyDate);
                jadwalKaryawan = tempJadwalKaryawanDao.getByEmpId(empData.getId(), olnyDate);
                if (jadwalKaryawan != null) {
                    jadwalKaryawan.setUpdatedBy(HRMConstant.INKUBA_SYSTEM);
                    jadwalKaryawan.setUpdatedOn(new Date());
//                jadwalKaryawan = tempJadwalKaryawanDao.getByEmpId(empData.getId(), olnyDate);
                } else {
                    jadwalKaryawan = new TempJadwalKaryawan();
                    jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));

                    jadwalKaryawan.setCreatedBy(HRMConstant.INKUBA_SYSTEM);
                    jadwalKaryawan.setCreatedOn(new Date());
                    jadwalKaryawan.setEmpData(empData);
                    jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                }
//                jadwalKaryawan = new TempJadwalKaryawan();

//                jadwalKaryawan.setWtWorkingHour(wtScheduleShift.getWtWorkingHour());
//                WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
//                if (holiday != null || wtScheduleShift.getWtWorkingHour().getCode().equalsIgnoreCase("OFF")) {
//                    jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("OFF"));
//                } else {
//                    jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("HD1"));
//                }
                WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
                if (holiday != null && groupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                    jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
                } else {
                    jadwalKaryawan.setWtWorkingHour(wtScheduleShift.getWtWorkingHour());
                }
                jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
                dataToSave.add(jadwalKaryawan);
                i++;
            }
        }
//        tempJadwalKaryawanDao.deleteBacth(dataToDelete);
        tempJadwalKaryawanDao.saveBatch(dataToSave);
        LOGGER.info("Finish Running Kalkulasi Jadwal Kerja");

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            calculateScheduleWorking();
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

}
