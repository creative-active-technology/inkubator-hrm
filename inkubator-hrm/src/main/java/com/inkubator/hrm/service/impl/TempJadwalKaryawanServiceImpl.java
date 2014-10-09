/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;

import com.google.gson.JsonObject;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "tempJadwalKaryawanService")
@Lazy
public class TempJadwalKaryawanServiceImpl extends IServiceImpl implements TempJadwalKaryawanService {

    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;
    @Autowired
    private JmsTemplate jmsTemplateMassJadwalKerja;
    @Autowired
    private JsonConverter jsonConverter;

    @Override
    public TempJadwalKaryawan getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntiyByPK(Long id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(TempJadwalKaryawan enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan saveData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan updateData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan saveOrUpdateData(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempJadwalKaryawan getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(TempJadwalKaryawan entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempJadwalKaryawan> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.REPEATABLE_READ, timeout = 50)
    public List<TempJadwalKaryawan> getAllByEmpIdWithDetail(long empId) throws Exception {
        return this.tempJadwalKaryawanDao.getAllByEmpIdWithDetail(empId);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void savePenempatanJadwal(EmpData empData) throws Exception {
        List<TempJadwalKaryawan> dataMustDelete = this.tempJadwalKaryawanDao.getAllByEmpId(empData.getId());
        if (!dataMustDelete.isEmpty()) {
            for (TempJadwalKaryawan dataMustDelete1 : dataMustDelete) {
                tempJadwalKaryawanDao.delete(dataMustDelete1);
            }
        }
        EmpData data = empDataDao.getEntiyByPK(empData.getId());
        Date now = new Date();
        WtGroupWorking groupWorking = this.wtGroupWorkingDao.getByCode(empData.getWtGroupWorking().getCode());
        groupWorking.setIsActive(Boolean.TRUE);
        wtGroupWorkingDao.update(groupWorking);
        data.setWtGroupWorking(groupWorking);
        empDataDao.update(data);
        List<WtScheduleShift> list = new ArrayList<>(groupWorking.getWtScheduleShifts());
        Collections.sort(list, shortByDate1);
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
        int i = 0;
        for (WtScheduleShift list1 : list) {
            TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
            jadwalKaryawan.setEmpData(empData);
            jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
            WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
            if (holiday != null && groupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
            } else {
                jadwalKaryawan.setWtWorkingHour(list1.getWtWorkingHour());
            }
            jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
            jadwalKaryawan.setCreatedBy(UserInfoUtil.getUserName());
            jadwalKaryawan.setCreatedOn(new Date());
            jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            this.tempJadwalKaryawanDao.save(jadwalKaryawan);
            i++;
        }
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMassPenempatanJadwal(List<EmpData> data, long groupWorkingId) throws Exception {
        WtGroupWorking groupWorking = wtGroupWorkingDao.getEntiyByPK(groupWorkingId);
        groupWorking.setIsActive(Boolean.TRUE);
        wtGroupWorkingDao.update(groupWorking);
        for (EmpData empData : data) {
            empData.setWtGroupWorking(wtGroupWorkingDao.getEntiyByPK(groupWorkingId));
            this.empDataDao.update(empData);
        }

        List<Long> listIdEmp = Lambda.extract(data, Lambda.on(EmpData.class).getId());
        String dataToJson = jsonConverter.getJson(listIdEmp.toArray(new Long[listIdEmp.size()]));
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("listEmpId", dataToJson);
        jsonObject.addProperty("groupWorkingId", groupWorkingId);
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy"); 
        jsonObject.addProperty("createDate", dateFormat.format(new Date()));
        System.out.println(" json nya "+jsonObject.toString());
        this.jmsTemplateMassJadwalKerja.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session)
                    throws JMSException {
                return session.createTextMessage(jsonObject.toString());
            }
        });
    }
    
    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
        @Override
        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
        }
    };

}
