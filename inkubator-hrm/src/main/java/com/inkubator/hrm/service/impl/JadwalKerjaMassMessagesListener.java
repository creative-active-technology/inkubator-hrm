/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
public class JadwalKerjaMassMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private AttendanceStatusDao attendanceStatusDao;
    @Autowired
    private EmpDataDao empDataDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            JSONObject jSONObject = new JSONObject(textMessage.getText());
            long workingGroupId = Long.parseLong(jSONObject.getString("groupWorkingId"));
            String listEmp = jSONObject.getString("listEmpId");
            Gson gson = new GsonBuilder().create();
            List<TempJadwalKaryawan> dataToDelete = new ArrayList<>();
            TypeToken<List<Long>> token = new TypeToken<List<Long>>() {
            };
            List<Long> dataEmpId = gson.fromJson(listEmp, token.getType());
            Date now = new Date();
            WtGroupWorking groupWorking = wtGroupWorkingDao.getEntiyByPK(workingGroupId);
            Date startDate = groupWorking.getBeginTime();//tidak ditempatkan di dalam loop karena untuk groupworking yang sama
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
            List<TempJadwalKaryawan> dataToSave = new ArrayList<>();
            for (Long id : dataEmpId) {
                dataToDelete.addAll(tempJadwalKaryawanDao.getAllByEmpId(id));
                List<WtScheduleShift> dataScheduleShift = new ArrayList<>(groupWorking.getWtScheduleShifts());
//                Collections.sort(dataScheduleShift, shortByDate1);
                 List<WtScheduleShift> sortedDataScheduleShift = Lambda.sort(dataScheduleShift, Lambda.on(WtScheduleShift.class).getScheduleDate());
                int i = 0;
                for (WtScheduleShift wtScheduleShift : sortedDataScheduleShift) {
                    TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
                    jadwalKaryawan.setEmpData(empDataDao.getEntiyByPK(id));
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
