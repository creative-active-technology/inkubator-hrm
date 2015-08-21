/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;

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
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;
    @Autowired
    private JmsTemplate jmsTemplateJadwalKerjaEmail;
    @Autowired
    private HrmUserDao hrmUserDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            JSONObject jSONObject = new JSONObject(textMessage.getText());
            long workingGroupId = Long.parseLong(jSONObject.getString("groupWorkingId"));
            String listEmp = jSONObject.getString("listEmpId");
            Date createOn = new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(jSONObject.getString("createDate"));
            String createBy = jSONObject.getString("createBy");

            Gson gson = new GsonBuilder().create();
//            List<TempJadwalKaryawan> dataToDelete = new ArrayList<>();
            TypeToken<List<Long>> token = new TypeToken<List<Long>>() {
            };
            List<Long> dataEmpId = gson.fromJson(listEmp, token.getType());
            //Date now = new Date();
            WtGroupWorking groupWorking = wtGroupWorkingDao.getEntiyByPK(workingGroupId);
            Date startDate = groupWorking.getBeginTime();//tidak ditempatkan di dalam loop karena untuk groupworking yang sama
            Date endDate = groupWorking.getEndTime();
            int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
            int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, createOn) + 1;
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
            List<TempJadwalKaryawan> dataToSave = new ArrayList<>();
            TempJadwalKaryawan jadwalKaryawan;
            for (Long id : dataEmpId) {
//                dataToDelete.addAll(tempJadwalKaryawanDao.getAllByEmpId(id)); for bussiner process Sake so must be close
                List<WtScheduleShift> dataScheduleShift = new ArrayList<>(groupWorking.getWtScheduleShifts());
//                Collections.sort(dataScheduleShift, shortByDate1);
                List<WtScheduleShift> sortedDataScheduleShift = Lambda.sort(dataScheduleShift, Lambda.on(WtScheduleShift.class).getScheduleDate());
                int i = 0;
                for (WtScheduleShift wtScheduleShift : sortedDataScheduleShift) {
                    String onlyDate = new SimpleDateFormat("yyyy-MM-dd").format(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                    Date olnyDate = new SimpleDateFormat("yyyy-MM-dd").parse(onlyDate);
                    jadwalKaryawan = tempJadwalKaryawanDao.getByEmpId(id, olnyDate);
                    if (jadwalKaryawan != null) {
                        jadwalKaryawan.setUpdatedBy(createBy);
                        jadwalKaryawan.setUpdatedOn(new Date());
//                jadwalKaryawan = tempJadwalKaryawanDao.getByEmpId(empData.getId(), olnyDate);
                    } else {
                        jadwalKaryawan = new TempJadwalKaryawan();
                        jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                        jadwalKaryawan.setEmpData(empDataDao.getEntiyByPK(id));
                        jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
                        jadwalKaryawan.setCreatedBy(createBy);
                        jadwalKaryawan.setCreatedOn(createOn);
                       
                    }
//                    TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
//                    jadwalKaryawan.setEmpData(empDataDao.getEntiyByPK(id));
//                    jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
//                    jadwalKaryawan.setWtWorkingHour(wtScheduleShift.getWtWorkingHour());
                    WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
                    if (holiday != null && groupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                        jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
                    } else {
                        jadwalKaryawan.setWtWorkingHour(wtScheduleShift.getWtWorkingHour());
                    }
//                    WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
//                    if (holiday != null || wtScheduleShift.getWtWorkingHour().getCode().equalsIgnoreCase("OFF")) {
//                        jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("OFF"));
//                    } else {
//                        jadwalKaryawan.setAttendanceStatus(attendanceStatusDao.getByCode("HD1"));
//                    }
                    jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
                    dataToSave.add(jadwalKaryawan);
                    i++;
                }

            }
//            tempJadwalKaryawanDao.deleteBacth(dataToDelete);
            tempJadwalKaryawanDao.saveBatch(dataToSave);
            
            //sending email process
            this.sendingEmailJadwalKaryawan(dataToSave, jSONObject.getString("locale"));
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private void sendingEmailJadwalKaryawan(List<TempJadwalKaryawan> listAll, String locale){
    	//initialization
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
    	SimpleDateFormat fullDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale(locale));
    	SimpleDateFormat timeFormat =  new SimpleDateFormat("HH:mm");
    	
    	//grouping by empData
    	Group<TempJadwalKaryawan> groupByEmpData = Lambda.group(listAll, Lambda.by(Lambda.on(TempJadwalKaryawan.class).getEmpData().getId()));
    	
    	//looping based on empData
    	for (String key : groupByEmpData.keySet()) {
    		List<TempJadwalKaryawan> listJadwalEmployee = groupByEmpData.find(key);
    		listJadwalEmployee = Lambda.sort(listJadwalEmployee, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
    		
    		EmpData empData = empDataDao.getEntiyByPK(listJadwalEmployee.get(0).getEmpData().getId());
    		HrmUser user = hrmUserDao.getByEmpDataId(empData.getId());
    		Date startDate = listJadwalEmployee.get(0).getTanggalWaktuKerja();
    		Date endDate = listJadwalEmployee.get(listJadwalEmployee.size()-1).getTanggalWaktuKerja();
    		List<String> jadwals = new ArrayList<String>();
    		for (TempJadwalKaryawan tempJK : listJadwalEmployee) {
    			TempJadwalKaryawan jadwal = tempJadwalKaryawanDao.getEntiyByPK(tempJK.getId());
				StringBuffer sb = new StringBuffer();
				sb.append(fullDateFormat.format(jadwal.getTanggalWaktuKerja()));
				sb.append(", ");
				sb.append(timeFormat.format(jadwal.getWtWorkingHour().getWorkingHourBegin()));
				sb.append(" - ");
				sb.append(timeFormat.format(jadwal.getWtWorkingHour().getWorkingHourEnd()));
				sb.append(". ");
				jadwals.add(sb.toString());
			}
    		
    		//only send the working schedule to the employee that have a valid email
    		if(user != null && StringUtils.isNotEmpty(user.getEmailAddress())) {
	    		final JsonObject jsonObj = new JsonObject();
	            jsonObj.addProperty("startDate", simpleDateFormat.format(startDate));
	            jsonObj.addProperty("endDate", simpleDateFormat.format(endDate));
	            jsonObj.addProperty("userEmail", user.getEmailAddress());
	            jsonObj.addProperty("locale", locale);
	            jsonObj.addProperty("userName", empData.getBioData().getFullName());
	            jsonObj.addProperty("userNik", empData.getNik());
	            jsonObj.addProperty("listSchedule", new GsonBuilder().create().toJson(jadwals));
	            
	            //send messaging, to trigger sending email
	            jmsTemplateJadwalKerjaEmail.send(new MessageCreator() {
	                @Override
	                public Message createMessage(Session session) throws JMSException {
	                    return session.createTextMessage(jsonObj.toString());
	                }
	            });
    		}
    	}
    }
    
    
//    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
//        @Override
//        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
//            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
//        }
//    };
}
