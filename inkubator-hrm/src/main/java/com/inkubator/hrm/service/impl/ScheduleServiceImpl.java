/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.RiwayatAksesDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RiwayatAkses;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.ScheduleService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private AttendanceStatusDao attendanceStatusDao;

    @Scheduled(cron = "${cron.delete.riwayat.akses.history}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void deleteRiwayatAkses() throws Exception {
        LOGGER.info("Begin Running Dellete Riwayar Akses");
        List<RiwayatAkses> dataToDelete = riwayatAksesDao.getByWeekDif(difWeekToDelete);
        LOGGER.info("Ukuran Data to Delete " + dataToDelete.size());
        riwayatAksesDao.deleteBatch(dataToDelete);
        LOGGER.info("Finish Running Dellete Riwayar Akses");
    }

    public void setDifWeekToDelete(int difWeekToDelete) {
        this.difWeekToDelete = difWeekToDelete;
    }

    @Override
    @Scheduled(cron = "${cron.calculate.schedule.working}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void calculateScheduleWorking() throws Exception {
          LOGGER.info("Begin Running Recalcualte Jadwal Kerja");
        Date now = new Date();
        Date lastDay = DateTimeUtil.getDateFrom(now, -1, CommonUtilConstant.DATE_FORMAT_DAY);
        String dayToInput = new SimpleDateFormat("dd-MM-yyyy").format(lastDay);
        System.out.println("tanggal param " + dayToInput);
        List<TempJadwalKaryawan> data = this.tempJadwalKaryawanDao.getAllByMaxEndDate(new SimpleDateFormat("dd-MM-yyyy").parse(dayToInput));
        List<EmpData> listEmpData = new ArrayList<>();
        List<TempJadwalKaryawan> dataToDelete = new ArrayList<>();
        for (TempJadwalKaryawan data1 : data) {
            listEmpData.add(data1.getEmpData());
        }
        List<TempJadwalKaryawan> dataToSave = new ArrayList<>();
        for (EmpData empData : listEmpData) {
            dataToDelete.addAll(tempJadwalKaryawanDao.getAllByEmpId(empData.getId()));
            WtGroupWorking groupWorking = empData.getWtGroupWorking();
            Date startDate = groupWorking.getBeginTime();
            Date endDate = groupWorking.getEndTime();
            int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
            int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, now) + 1;
            int num = numberOfDay + 1;
            int hasilBagi = (totalDateDif) / (num);
            String dayBegin = new SimpleDateFormat("EEEE").format(endDate);
            String dayNow = new SimpleDateFormat("EEEE").format(now);
            Date beginScheduleDate;
            if (dayBegin.endsWith(dayNow) && Objects.equals(groupWorking.getTypeSequeace(), HRMConstant.NORMAL_SCHEDULE)) {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - num, CommonUtilConstant.DATE_FORMAT_DAY);
            } else {
                beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
            }
            List<WtScheduleShift> dataScheduleShift = new ArrayList<>(groupWorking.getWtScheduleShifts());
            Collections.sort(dataScheduleShift, shortByDate1);
            int i = 0;
            for (WtScheduleShift wtScheduleShift : dataScheduleShift) {
                TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
                jadwalKaryawan.setEmpData(empData);
                jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                jadwalKaryawan.setWtWorkingHour(wtScheduleShift.getWtWorkingHour());
                WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
                if (holiday != null || wtScheduleShift.getWtWorkingHour().getCode().equalsIgnoreCase("OFF")) {
                    jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("OFF"));
                } else {
                    jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("HD1"));
                }
                jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
                jadwalKaryawan.setCreatedBy(HRMConstant.INKUBA_SYSTEM);
                jadwalKaryawan.setCreatedOn(new Date());
                jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                dataToSave.add(jadwalKaryawan);
                i++;
            }
        }
        tempJadwalKaryawanDao.deleteBacth(dataToDelete);
        tempJadwalKaryawanDao.saveBatch(dataToSave);
          LOGGER.info("Finish Running Kalkulasi Jadwal Kerja");

    }

    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
        @Override
        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
        }
    };
}
