/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.DetilAttendateRelaization;
import com.inkubator.hrm.web.model.DetilRealizationAttendanceModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import java.util.ArrayList;


/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Service(value = "tempAttendanceRealizationService")
@Lazy
public class TempAttendanceRealizationServiceImpl extends IServiceImpl implements TempAttendanceRealizationService {
    
    @Autowired
    private TempAttendanceRealizationDao tempAttendanceRealizationDao;
    
    @Autowired
    private WtPeriodeDao wtPeriodeDao;
    @Autowired
    private TempProcessReadFingerDao tempProcessReadFingerDao;
    @Autowired
    private TempJadwalKaryawanDao tempJadwalKaryawanDao;
    @Autowired
    private LeaveImplementationDao leaveImplementationDao;
    @Autowired
    private LeaveImplementationDateDao leaveImplementationDateDao;
    @Autowired
    private BusinessTravelDao businessTravelDao;
    @Autowired
    private MedicalCareDao medicalCareDao;
    @Autowired
    private PermitImplementationDao permitImplementationDao;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

    public void delete(TempAttendanceRealization tempAttendanceRealization) throws Exception {
        tempAttendanceRealizationDao.delete(tempAttendanceRealization);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(TempAttendanceRealization tempAttendanceRealization) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = tempAttendanceRealizationDao.getTotalByCode(tempAttendanceRealization.getCode());
//if (totalDuplicates > 0) {
//throw new BussinessException("tempAttendanceRealization.error_duplicate_code")
//}
        tempAttendanceRealization.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        tempAttendanceRealization.setCreatedBy(UserInfoUtil.getUserName());
        tempAttendanceRealization.setCreatedOn(new Date());
        tempAttendanceRealizationDao.save(tempAttendanceRealization);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(TempAttendanceRealization tempAttendanceRealization) throws Exception {
//Uncomment below if u have unik code
//long totalDuplicates = tempAttendanceRealizationDao.getTotalByCodeAndNotId(tempAttendanceRealization.getCode(),tempAttendanceRealization.getId())
//if (totalDuplicates > 0) {
//throw new BussinessException("tempAttendanceRealization.error_duplicate_code")
//}
        TempAttendanceRealization tempAttendanceRealization1 = tempAttendanceRealizationDao.getEntiyByPK(tempAttendanceRealization.getId());
//        tempAttendanceRealization1.setUpdatedBy(UserInfoUtil.getUserName());
//        tempAttendanceRealization1.setUpdatedOn(new Date());
        tempAttendanceRealization1.setWtGroupWorking(tempAttendanceRealization.getWtGroupWorking());
        tempAttendanceRealization1.setLeave(tempAttendanceRealization.getLeave());
        tempAttendanceRealization1.setEmpData(tempAttendanceRealization.getEmpData());
        tempAttendanceRealization1.setDuty(tempAttendanceRealization.getDuty());
        tempAttendanceRealization1.setAttendanceDaysPresent(tempAttendanceRealization.getAttendanceDaysPresent());
        tempAttendanceRealization1.setPermit(tempAttendanceRealization.getPermit());
        tempAttendanceRealization1.setAttendanceDaysSchedule(tempAttendanceRealization.getAttendanceDaysSchedule());
        tempAttendanceRealization1.setSick(tempAttendanceRealization.getSick());
        tempAttendanceRealizationDao.update(tempAttendanceRealization1);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public TempAttendanceRealization getEntiyByPK(Long id) {
        return this.tempAttendanceRealizationDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<TempAttendanceRealization> getByParam(TempAttendanceRealizationSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.tempAttendanceRealizationDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalTempAttendanceRealizationByParam(TempAttendanceRealizationSearchParameter searchParameter) throws Exception {
        return this.tempAttendanceRealizationDao.getTotalTempAttendanceRealizationByParam(searchParameter);
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(TempAttendanceRealization enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization updateData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization saveOrUpdateData(TempAttendanceRealization entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TempAttendanceRealization getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void softDelete(TempAttendanceRealization entity) throws Exception {
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
    public List<TempAttendanceRealization> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TempAttendanceRealization> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public RealizationAttendanceModel getStatisticEmpAttendaceRealization() throws Exception {
        WtPeriode wtPeriode = this.wtPeriodeDao.getEntityByAbsentTypeActive();

        RealizationAttendanceModel attendanceModel = new RealizationAttendanceModel();
        attendanceModel.setStardDate(wtPeriode.getFromPeriode());
        attendanceModel.setEndDate(wtPeriode.getUntilPeriode());
        attendanceModel.setTotalCuti(tempAttendanceRealizationDao.getTotalEmpLeav());
        attendanceModel.setTotalIzin(tempAttendanceRealizationDao.getTotalEmpPermit());
        attendanceModel.setTotalOnDuty(tempAttendanceRealizationDao.gettotalEmpOnDuty());
        attendanceModel.setTotalSick(tempAttendanceRealizationDao.gettotalEmpOnSick());
        attendanceModel.setTotaldayPresent(tempAttendanceRealizationDao.totalDayPresent());
        attendanceModel.setTotaldaySchedule(tempAttendanceRealizationDao.totalDaySchedule());
        return attendanceModel;

    }

    @Override
    public Long getTotalEmpLeav() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalEmpPermit() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long gettotalEmpOnDuty() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
//    public DetilRealizationAttendanceModel getStatisticEmpAttendaceDetil(long empId) throws Exception {
////        WtPeriode wtPeriode = this.wtPeriodeDao.getEntityByAbsentTypeActive();
//        DetilRealizationAttendanceModel attendanceModel = new DetilRealizationAttendanceModel();
////        attendanceModel.setStardDate(wtPeriode.getFromPeriode());
////        attendanceModel.setEndDate(wtPeriode.getUntilPeriode());
////        attendanceModel.setTotalCuti(tempAttendanceRealizationDao.getTotalEmpLeav(empId));
////        attendanceModel.setTotalIzin(tempAttendanceRealizationDao.getTotalEmpPermit(empId));
////        attendanceModel.setTotalOnDuty(tempAttendanceRealizationDao.gettotalEmpOnDuty(empId));
////        attendanceModel.setTotalSick(tempAttendanceRealizationDao.gettotalEmpOnSick(empId));
//        return attendanceModel;
//    }
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public DetilRealizationAttendanceModel getStatisticEmpAttendaceDetil(long empId) throws Exception {
        WtPeriode wtPeriode = this.wtPeriodeDao.getEntityByAbsentTypeActive();
        DetilRealizationAttendanceModel attendanceModel = new DetilRealizationAttendanceModel();

//        Perhatia dua data yang di perbandingkan harus sama soring ascrnding atau descending 
        List<TempJadwalKaryawan> dataToCalculate = tempJadwalKaryawanDao.getAllByEmpIdWithDetailWithFromAndUntilPeriod(empId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
//        List<TempProcessReadFinger> presentAteendace = tempProcessReadFingerDao.getAllDataByEmpDataIdAndScheduleDate(empId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<DetilAttendateRelaization> dataToShow = new ArrayList<>();
        for (TempJadwalKaryawan tempJadwal : dataToCalculate) {
//            System.out.println("kayarannna " + empId);
//            System.out.println("jadwalnya " + tempJadwal.getTanggalWaktuKerja());
            TempProcessReadFinger finger = tempProcessReadFingerDao.getEntityByEmpDataIdAndScheduleDate(empId, tempJadwal.getTanggalWaktuKerja());
            DetilAttendateRelaization attendateRelaization = new DetilAttendateRelaization();

            LeaveImplementation leaveImplementation = leaveImplementationDao.getByEmpStardDateEndDate(empId, tempJadwal.getTanggalWaktuKerja());
            BusinessTravel businessTravel = businessTravelDao.getByEmpIdAndDate(empId, tempJadwal.getTanggalWaktuKerja());
            MedicalCare medicalCare = medicalCareDao.getByEmpIdAndDate(empId, tempJadwal.getTanggalWaktuKerja());
            PermitImplementation permitImplementation = permitImplementationDao.getByEmpStardDateEndDate(empId, tempJadwal.getTanggalWaktuKerja());

            if (finger != null) {

                attendateRelaization.setAsentDate(finger.getScheduleDate());
                attendateRelaization.setRealisasiStatus(finger.getWorkingHourName());
                attendateRelaization.setRealisasiAttendace(tempJadwal.getWtWorkingHour().getAttendanceStatus().getStatusKehadrian());
                attendateRelaization.setIsAttendaceKnowing(true);
            } else {
                if (leaveImplementation != null) {
                    long leavId = leaveImplementation.getId();
                    LeaveImplementationDate leaveImplementationDate = leaveImplementationDateDao.getByLeavIdDateAndIsTrue(leavId, tempJadwal.getTanggalWaktuKerja(), false);
                    attendateRelaization.setAsentDate(leaveImplementationDate.getActualDate());
                    attendateRelaization.setRealisasiStatus(leaveImplementationDate.getLeaveImplementation().getLeave().getName() + " no." + leaveImplementationDate.getLeaveImplementation().getNumberFilling());
                    attendateRelaization.setRealisasiAttendace(leaveImplementationDate.getLeaveImplementation().getLeave().getAttendanceStatus().getStatusKehadrian());
                    attendateRelaization.setIsAttendaceKnowing(true);
                } else if (businessTravel != null) {
                    attendateRelaization.setAsentDate(tempJadwal.getTanggalWaktuKerja());
                    attendateRelaization.setRealisasiStatus(ResourceBundleUtil.getAsString("global.travel_to") + " " + businessTravel.getDestination());
                    attendateRelaization.setRealisasiAttendace(businessTravel.getTravelType().getAttendanceStatus().getStatusKehadrian());
                    attendateRelaization.setIsAttendaceKnowing(true);
                } else if (medicalCare != null) {
                    attendateRelaization.setAsentDate(tempJadwal.getTanggalWaktuKerja());
                    attendateRelaization.setRealisasiStatus(medicalCare.getDisease().getName());
                    attendateRelaization.setRealisasiAttendace(ResourceBundleUtil.getAsString("global.sick"));
                    attendateRelaization.setIsAttendaceKnowing(true);
                } else if (permitImplementation != null) {
                    attendateRelaization.setAsentDate(tempJadwal.getTanggalWaktuKerja());
                    attendateRelaization.setRealisasiStatus(permitImplementation.getPermitClassification().getName());
                    attendateRelaization.setRealisasiAttendace(permitImplementation.getPermitClassification().getAttendanceStatus().getStatusKehadrian());
                    attendateRelaization.setIsAttendaceKnowing(true);
                } else {
                    if (tempJadwal.getWtWorkingHour().getCode().equals("OFF")) {
                        attendateRelaization.setAsentDate(tempJadwal.getTanggalWaktuKerja());
                        attendateRelaization.setRealisasiStatus(tempJadwal.getWtWorkingHour().getName());
                        attendateRelaization.setRealisasiAttendace(ResourceBundleUtil.getAsString("menu.holiday"));
                        attendateRelaization.setIsAttendaceKnowing(true);
                    } else {
                        attendateRelaization.setAsentDate(tempJadwal.getTanggalWaktuKerja());
                        attendateRelaization.setRealisasiStatus(ResourceBundleUtil.getAsString("global.unknow"));
                        attendateRelaization.setRealisasiAttendace(ResourceBundleUtil.getAsString("global.unknow"));
                        attendateRelaization.setIsAttendaceKnowing(false);
                    }

                }
            }

            dataToShow.add(attendateRelaization);
            attendanceModel.setDataToShow(dataToShow);
//            Date temp1 = tempJadwal.getTanggalWaktuKerja();
//            Date temp2;
//            System.out.println(" tanggalalgla nya 1" + temp1);
//
//            if (i <= sizePresentDay) {
//                temp2 = presentAteendace.get(i).getScheduleDate();
//                System.out.println(" tanggalalgla nya 2" + temp2);
//                if (temp1.equals(temp2)) {
//                    System.out.println("tanggal nya samaaaa");
//                }
//            }
//            i++;
        }

        attendanceModel.setStardDate(wtPeriode.getFromPeriode());
        attendanceModel.setEndDate(wtPeriode.getUntilPeriode());
        attendanceModel.setTotalCuti(tempAttendanceRealizationDao.getTotalEmpLeav(empId));
        attendanceModel.setTotalIzin(tempAttendanceRealizationDao.getTotalEmpPermit(empId));
        attendanceModel.setTotalOnDuty(tempAttendanceRealizationDao.gettotalEmpOnDuty(empId));
        attendanceModel.setTotalSick(tempAttendanceRealizationDao.gettotalEmpOnSick(empId));
//        attendanceModel.setDataToShow(tempProcessReadFingerDao.getAllDataByEmpDataIdAndScheduleDate(empId, attendanceModel.getStardDate(), attendanceModel.getEndDate()));
        return attendanceModel;
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<TempAttendanceRealizationViewModel> getListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId, int firstResult, int maxResults, Order orderable) throws Exception {
        return tempAttendanceRealizationDao.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalListTempAttendanceRealizationViewModelByWtPeriodId(Long wtPeriodId) throws Exception {
        return tempAttendanceRealizationDao.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId);
    }
}
