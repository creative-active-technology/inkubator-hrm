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
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.EmpData;
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
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class JadwalKerjaUpdateMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private AttendanceStatusDao attendanceStatusDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            JSONObject jSONObject = new JSONObject(textMessage.getText());
            long workingGroupId = Long.parseLong(jSONObject.getString("id"));
            List<TempJadwalKaryawan> data = this.tempJadwalKaryawanDao.getByGroupKerjadId(workingGroupId);
            tempJadwalKaryawanDao.deleteBacth(data);
            WtGroupWorking groupWorking = this.wtGroupWorkingDao.getEntiyByPK(workingGroupId);
            Date now = new Date();
            Date startDate = groupWorking.getBeginTime();
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
//            Date beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
            List<WtScheduleShift> dataScheduleShift = new ArrayList<>(groupWorking.getWtScheduleShifts());
            List<EmpData> datas = new ArrayList<>(groupWorking.getEmpDatas());
//            Collections.sort(dataScheduleShift, shortByDate1);
            List<WtScheduleShift> sortedDataScheduleShift = Lambda.sort(dataScheduleShift, Lambda.on(WtScheduleShift.class).getScheduleDate());
            List<TempJadwalKaryawan> dataToSave = new ArrayList<>();
            for (EmpData data1 : datas) {
                int i = 0;// Penting pisisi code harus di sini
                for (WtScheduleShift dataScheduleShift1 : sortedDataScheduleShift) {
                    TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
                    jadwalKaryawan.setEmpData(data1);
                    jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
//                    jadwalKaryawan.setWtWorkingHour(dataScheduleShift1.getWtWorkingHour());
                    WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
                    if (holiday != null && groupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                        jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
                    } else {
                        jadwalKaryawan.setWtWorkingHour(dataScheduleShift1.getWtWorkingHour());
                    }
                    jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
                    jadwalKaryawan.setCreatedBy(jSONObject.getString("createdBy"));
                    jadwalKaryawan.setCreatedOn(new Date());
                    jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                    dataToSave.add(jadwalKaryawan);
//                    this.tempJadwalKaryawanDao.save(jadwalKaryawan);
                    i++;
                }
            }
            tempJadwalKaryawanDao.saveBatch(dataToSave);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
//    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
//        @Override
//        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
//            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
//        }
//    };
}
