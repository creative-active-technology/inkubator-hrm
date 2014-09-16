/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtScheduleShiftDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.web.model.GroupWorkingModel;
import com.inkubator.hrm.web.model.ScheduleShiftModel;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "wtGroupWorkingService")
@Lazy
public class WtGroupWorkingServiceImpl extends IServiceImpl implements WtGroupWorkingService {

    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;
    @Autowired
    private WtScheduleShiftDao wtScheduleShiftDao;

    @Override
    public WtGroupWorking getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtGroupWorking getEntiyByPK(Long id) throws Exception {
        return this.wtGroupWorkingDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(WtGroupWorking entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(WtGroupWorking entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(WtGroupWorking enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking saveData(WtGroupWorking entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking updateData(WtGroupWorking entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking saveOrUpdateData(WtGroupWorking entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public WtGroupWorking getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(WtGroupWorking entity) throws Exception {
        this.wtGroupWorkingDao.delete(entity);
    }

    @Override
    public void softDelete(WtGroupWorking entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WtGroupWorking> getAllData() throws Exception {
       return this.wtGroupWorkingDao.getAllData();
    }

    @Override
    public List<WtGroupWorking> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<WtGroupWorking> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WtGroupWorking> getByParam(WtGroupWorkingSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.wtGroupWorkingDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalWtGroupWorkingByParam(WtGroupWorkingSearchParameter searchParameter) throws Exception {
        return this.wtGroupWorkingDao.getTotalWtGroupWorkingByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(GroupWorkingModel model) throws Exception {
        long totalDuplicates = wtGroupWorkingDao.getTotalByCode(model.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("working_group.error_duplicate_code");
        }

        WtGroupWorking groupWorking = new WtGroupWorking();
        groupWorking.setBeginTime(model.getBeginTime());
        groupWorking.setCode(model.getCode());
        groupWorking.setCreatedBy(UserInfoUtil.getUserName());
        groupWorking.setCreatedOn(new Date());
        groupWorking.setEndTime(model.getEndTime());
        groupWorking.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        groupWorking.setIsActive(Boolean.FALSE);
        groupWorking.setIsPeriodic(model.getIsPeriodic());
        groupWorking.setName(model.getName());
        groupWorking.setOvertimeBasedOnAttendance(model.getOvertimeBasedOnAttendance());
        groupWorking.setOvertimeBasedOnRequest(model.getOvertimeBasedOnRequest());
        groupWorking.setWorkingTimePerday(model.getWorkingTimePerday());
        groupWorking.setWorkingTimePerweek(model.getWorkingTimePerweek());
        Set<WtScheduleShift> wtScheduleShifts = new HashSet<>();

        List<ScheduleShiftModel> dataShiftModels = model.getDataShiftModels();

        for (ScheduleShiftModel scheduleShiftModel : dataShiftModels) {
            if (scheduleShiftModel.getJamKerjaId() != null || scheduleShiftModel.getJamKerjaId2() != null) {
              
                if (scheduleShiftModel.getJamKerjaId() != null) {
                    WtScheduleShift shift1 = new WtScheduleShift();
                    shift1.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                    shift1.setCreatedBy(UserInfoUtil.getUserName());
                    shift1.setCreatedOn(new Date());
                    shift1.setScheduleDate(scheduleShiftModel.getTanggalKerja());
                    shift1.setWtGroupWorking(groupWorking);
                    shift1.setWtWorkingHour(wtWorkingHourDao.getEntiyByPK(scheduleShiftModel.getJamKerjaId()));
                    wtScheduleShifts.add(shift1);
                  
                }
                if (scheduleShiftModel.getJamKerjaId2() != null) {
                    WtScheduleShift shift2 = new WtScheduleShift();
                    shift2.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                    shift2.setCreatedBy(UserInfoUtil.getUserName());
                    shift2.setCreatedOn(new Date());
                    shift2.setScheduleDate(scheduleShiftModel.getTanggalKerja2());
                    shift2.setWtGroupWorking(groupWorking);
                    shift2.setWtWorkingHour(wtWorkingHourDao.getEntiyByPK(scheduleShiftModel.getJamKerjaId2()));
                    wtScheduleShifts.add(shift2);
                    
                }

            }
        }

        groupWorking.setWtScheduleShifts(wtScheduleShifts);
        this.wtGroupWorkingDao.save(groupWorking);

        for (WtScheduleShift wtScheduleShift : wtScheduleShifts) {
         
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtGroupWorking getByPKIdWithDetail(Long id) throws Exception {
        return this.wtGroupWorkingDao.getByPKIdWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WtGroupWorking getByCode(String code) throws Exception {
        return this.wtGroupWorkingDao.getByCode(code);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(GroupWorkingModel model) throws Exception {
        long totalDuplicates = wtGroupWorkingDao.getTotalByCodeAndNotId(model.getCode(), model.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("working_group.error_duplicate_code");
        }
        WtGroupWorking groupWorking = this.wtGroupWorkingDao.getEntiyByPK(model.getId());
        groupWorking.getWtScheduleShifts().clear();
        groupWorking.setBeginTime(model.getBeginTime());
        groupWorking.setCode(model.getCode());
//        groupWorking.setCreatedBy(UserInfoUtil.getUserName());
//        groupWorking.setCreatedOn(new Date());
        groupWorking.setEndTime(model.getEndTime());
//        groupWorking.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        groupWorking.setIsActive(Boolean.FALSE);
        groupWorking.setIsPeriodic(model.getIsPeriodic());
        groupWorking.setName(model.getName());
        groupWorking.setOvertimeBasedOnAttendance(model.getOvertimeBasedOnAttendance());
        groupWorking.setOvertimeBasedOnRequest(model.getOvertimeBasedOnRequest());
        groupWorking.setWorkingTimePerday(model.getWorkingTimePerday());
        groupWorking.setWorkingTimePerweek(model.getWorkingTimePerweek());
        groupWorking.setUpdatedBy(UserInfoUtil.getUserName());
        groupWorking.setUpdatedOn(new Date());
        this.wtGroupWorkingDao.saveAndMerge(groupWorking);
//        Set<WtScheduleShift> wtScheduleShifts = new HashSet<>();

        List<ScheduleShiftModel> dataShiftModels = model.getDataShiftModels();

        for (ScheduleShiftModel scheduleShiftModel : dataShiftModels) {
            if (scheduleShiftModel.getJamKerjaId() != null || scheduleShiftModel.getJamKerjaId2() != null) {
            
                if (scheduleShiftModel.getJamKerjaId() != null) {
                    WtScheduleShift shift1 = new WtScheduleShift();
                    shift1.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                    shift1.setCreatedBy(UserInfoUtil.getUserName());
                    shift1.setCreatedOn(new Date());
                    shift1.setScheduleDate(scheduleShiftModel.getTanggalKerja());
                    shift1.setWtGroupWorking(groupWorking);
                    shift1.setWtWorkingHour(wtWorkingHourDao.getEntiyByPK(scheduleShiftModel.getJamKerjaId()));
                    wtScheduleShiftDao.save(shift1);
//                    wtScheduleShifts.add(shift1);
//                    System.out.println("Nilai " + shift1.getScheduleDate());
                }
                if (scheduleShiftModel.getJamKerjaId2() != null) {
                    WtScheduleShift shift2 = new WtScheduleShift();
                    shift2.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                    shift2.setCreatedBy(UserInfoUtil.getUserName());
                    shift2.setCreatedOn(new Date());
                    shift2.setScheduleDate(scheduleShiftModel.getTanggalKerja2());
                    shift2.setWtGroupWorking(groupWorking);
                    shift2.setWtWorkingHour(wtWorkingHourDao.getEntiyByPK(scheduleShiftModel.getJamKerjaId2()));
                    wtScheduleShiftDao.save(shift2);
//                    wtScheduleShifts.add(shift2);
//                    System.out.println("Nilai " + shift2.getScheduleDate());
                }

            }

        }

    }

    @Override
    public List<WtGroupWorking> workingGroupIsAcive() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
