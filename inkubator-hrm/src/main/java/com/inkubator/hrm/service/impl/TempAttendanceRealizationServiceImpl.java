/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import ch.lambdaj.Lambda;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.dao.TempAttendanceRealizationDao;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matchers;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.ImplementationOfOverTimeDao;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.dao.LeaveImplementationDateDao;
import com.inkubator.hrm.dao.MedicalCareDao;
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.dao.TempJadwalKaryawanDao;
import com.inkubator.hrm.dao.TempProcessReadFingerDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.dao.WtHitungLemburDao;
import com.inkubator.hrm.dao.WtHitungLemburJamDao;
import com.inkubator.hrm.dao.WtHolidayDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.dao.WtWorkingHourDao;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.LeaveImplementationDate;
import com.inkubator.hrm.entity.MedicalCare;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtHitungLemburJam;
import com.inkubator.hrm.entity.WtHoliday;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.entity.WtScheduleShift;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.web.model.DetilAttendateRelaization;
import com.inkubator.hrm.web.model.DetilRealizationAttendanceModel;
import com.inkubator.hrm.web.model.RealizationAttendanceModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.WorkingTimeDeviation;
import com.inkubator.hrm.web.model.WorkingTimeDeviationDetailModel;

import java.util.ArrayList;

import org.hibernate.criterion.Order;

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
    @Autowired
    private WtHitungLemburDao wtHitungLemburDao;
    @Autowired
    private WtHitungLemburJamDao wtHitungLemburJamDao;
    @Autowired
    private WtHolidayDao wtHolidayDao;
    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private WtWorkingHourDao wtWorkingHourDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private ImplementationOfOverTimeDao implementationOfOverTimeDao;

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
            System.out.println(" Waktu kerjanya adalaha :" + tempJadwal.getTanggalWaktuKerja());
            if (tempJadwal.getTanggalWaktuKerja().equals(new Date())) {
                break;
            }

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

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteAllData() throws Exception {
        tempAttendanceRealizationDao.deleteAllData();
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TempAttendanceRealizationViewModel calculateEmpTempAttendanceRealization(Long empDataId, Long wtPeriodId) throws Exception {

        TempAttendanceRealizationViewModel tempAttendanceRealizationViewModel = new TempAttendanceRealizationViewModel();
        EmpData empData = empDataDao.getByEmpIdWithDetail(empDataId);
        String religionCode = empData.getBioData().getReligion().getCode();

        WtGroupWorking wtGroupWorking = wtGroupWorkingDao.getEntiyByPK(empData.getWtGroupWorking().getId());
        WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(wtPeriodId);

        List<MedicalCare> listMedicalCares = medicalCareDao.getListWhereStartDateBetweenDateFromAndDateUntillByEmpId(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<ImplementationOfOverTime> listImplementationOfOverTime = implementationOfOverTimeDao.getAllEmpOtImplBetweenStartDateAndEndDate(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<LeaveImplementation> listLeaveImplementation = leaveImplementationDao.getListByStartDateBetweenDateAndEmpId(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<PermitImplementation> listPermitImplementation = permitImplementationDao.getListByStartDateBetweenDateAndEmpId(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<BusinessTravel> listBusinessTravel = businessTravelDao.getListByStartDateBetweenDateAndEmpIdAndNotOff(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());

        Integer attendanceDaysSchedule = this.getTotalWorkingDaysBetween(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        Integer attendanceDaysPresent = tempProcessReadFingerDao.getEmpTotalAttendanceBetweenDateFromAndDateUntill(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode()).intValue();
        Integer sick = calculateAndCheckTotalSick(listMedicalCares, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), empDataId);
        Integer leave = calculateAndCheckTotalLeave(listLeaveImplementation, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), empDataId);
        Integer permit = calculateAndCheckTotalPermit(listPermitImplementation, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), empDataId);
        Integer duty = calculateAndCheckTotalDuty(listBusinessTravel, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), empDataId);
        Float overtime = calculateAndCheckTotalOverTime(listImplementationOfOverTime, wtPeriode, empDataId, religionCode);

        tempAttendanceRealizationViewModel.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        tempAttendanceRealizationViewModel.setAbsenStatus(HRMConstant.WT_PERIOD_STATUS_ACTIVE);
        tempAttendanceRealizationViewModel.setAttendanceDaysPresent(attendanceDaysPresent);
        tempAttendanceRealizationViewModel.setAttendanceDaysSchedule(attendanceDaysSchedule);
        tempAttendanceRealizationViewModel.setDuty(duty);
        tempAttendanceRealizationViewModel.setEmpId(empDataId);
        tempAttendanceRealizationViewModel.setEmpName(empData.getBioData().getFullName());
        tempAttendanceRealizationViewModel.setLeaves(leave);
        tempAttendanceRealizationViewModel.setNik(empData.getNik());
        tempAttendanceRealizationViewModel.setOverTime(overtime);
        tempAttendanceRealizationViewModel.setPermit(permit);
        tempAttendanceRealizationViewModel.setSick(sick);
        tempAttendanceRealizationViewModel.setWtGroupWorkingId(wtGroupWorking.getId());
        tempAttendanceRealizationViewModel.setWtGroupWorkingName(wtGroupWorking.getName());
        tempAttendanceRealizationViewModel.setWtPeriodId(wtPeriodId);

        return tempAttendanceRealizationViewModel;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchFileUpload(TempAttendanceRealizationViewModel model) throws Exception {
        //Boolean isInsertable = this.payTempUploadDataDao.getEntityByNikAndComponentId(model.getNik(), model.getPaySalaryComponentId()) == null;
        Boolean isInsertable = Boolean.TRUE;
        //skip jika data sudah ada di database(tidak boleh duplikat)
        if (isInsertable) {
            EmpData empData = empDataDao.getEntiyByPK(model.getEmpId());
            WtGroupWorking wtGroupWorking = wtGroupWorkingDao.getEntiyByPK(model.getWtGroupWorkingId());
            WtPeriode wtPeriode = wtPeriodeDao.getEntiyByPK(model.getWtPeriodId());

            if (empData != null) {
                TempAttendanceRealization entity = new TempAttendanceRealization();
                entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
                entity.setAttendanceDaysPresent(model.getAttendanceDaysPresent());
                entity.setAttendanceDaysSchedule(model.getAttendanceDaysSchedule());
                entity.setCreatedBy(model.getCreatedBy());
                entity.setCreatedOn(new Date());
                entity.setDuty(model.getDuty());
                entity.setEmpData(empData);
                entity.setLeave(model.getLeaves());
                entity.setOvertime(model.getOverTime());
                entity.setPermit(model.getPermit());
                entity.setSick(model.getSick());
                entity.setWtGroupWorking(wtGroupWorking);
                entity.setWtPeriod(wtPeriode);

                this.tempAttendanceRealizationDao.save(entity);

            }
        }
    }

    private Integer calculateAndCheckTotalSick(List<MedicalCare> listMedicalCares, Date dateFrom, Date dateUntill, Long empDataId) {
        Integer totalSick = 0;

        List<Date> listDateInSelectedPeriod = getRangeOfDates(dateFrom, dateUntill);
        List<TempJadwalKaryawan> listEmpOffSchedule = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDateAndOffDay(empDataId, dateFrom, dateUntill);
        List<Date> listEmpOffDate = new ArrayList<Date>();

        if (!listEmpOffSchedule.isEmpty()) {
            listEmpOffDate = Lambda.extract(listEmpOffSchedule, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
            Collections.sort(listEmpOffDate);
        }

        //Get List Public Holiday Schedule
        List<WtHoliday> listPublicHoliday = wtHolidayDao.getBetweenDate(dateFrom, dateFrom);
        List<Date> listPublicHolidayDate = new ArrayList<Date>();

        if (!listPublicHoliday.isEmpty()) {
            listPublicHolidayDate = Lambda.extract(listPublicHoliday, Lambda.on(WtHoliday.class).getHolidayDate());
            Collections.sort(listPublicHolidayDate);
        }

        //Looping List MedicalCare
        for (MedicalCare medicalCare : listMedicalCares) {

            //get total sickDays and list sickDates
            int totalSickDaysPerRecord = medicalCare.getTotalDays();
            List<Date> listSickDates = getRangeOfDates(medicalCare.getStartDate(), medicalCare.getEndDate());
            Collections.sort(listSickDates);

            //Check whether sick startDate and end Date is in Period date
            Boolean isSickStartDateInPeriodRange = isDateIsBetWeenTwoDates(medicalCare.getStartDate(), dateFrom, dateUntill);
            Boolean isSickEndDateInPeriodRange = isDateIsBetWeenTwoDates(medicalCare.getEndDate(), dateFrom, dateUntill);

            //if sick startDate and end Date is in Period date, loop the list and check one By One
            if (isSickStartDateInPeriodRange && isSickEndDateInPeriodRange) {

                for (Date sickDate : listSickDates) {

                    //Check if Sick was on public holiday
                    Boolean isSickOnPublicHoliday = isDateIsInListDate(sickDate, listPublicHolidayDate);
                    Boolean isSickOnOffSchedule = Boolean.FALSE;

                    if (!listEmpOffSchedule.isEmpty()) {

                        //Check if Sick was on Off day Schedule
                        isSickOnOffSchedule = isDateIsInListDate(sickDate, listEmpOffDate);

                    }

                    //if sick was on off day schedule, or on public holiday, decrement totalSickDaysPerRecord because actually it didn't affect on employee total Attendance 
                    if (isSickOnOffSchedule || isSickOnPublicHoliday) {
                        totalSickDaysPerRecord--;
                    }
                }

            } else {

                for (Date sickDate : listSickDates) {

                    //Check if Sick is in period list date
                    Boolean isSickDateInPeriod = isDateIsInListDate(sickDate, listDateInSelectedPeriod);

                    if (isSickDateInPeriod) {

                        //Check if Sick was on public holiday
                        Boolean isSickOnPublicHoliday = isDateIsInListDate(sickDate, listPublicHolidayDate);
                        Boolean isSickOnOffSchedule = Boolean.FALSE;

                        if (!listEmpOffSchedule.isEmpty()) {

                            //Check if Sick was on Off day Schedule
                            isSickOnOffSchedule = isDateIsInListDate(sickDate, listEmpOffDate);

                        }

                        //if sick was on off day schedule, or on public holiday, decrement totalSickDaysPerRecord because actually it didn't affect on employee total Attendance 
                        if (isSickOnOffSchedule || isSickOnPublicHoliday) {
                            totalSickDaysPerRecord--;
                        }

                    } else {
                        // no need to check, just decrement, bacause sick date is out of list period date
                        totalSickDaysPerRecord--;
                    }

                }
            }

            //increment totalSick with each totalSickDaysPerRecord
            totalSick += totalSickDaysPerRecord;
        }

        //return totalSick
        return totalSick;
    }

    private Float calculateAndCheckTotalOverTime(List<ImplementationOfOverTime> listImplementationOfOverTime, WtPeriode wtPeriode, Long empDataId, String religionCode) {
        Float totalOvertime = 0f;

        //Get List Holiday Schedule
        List<TempJadwalKaryawan> listJadwalLibur = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDateAndOffDay(empDataId, wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<Date> listDateLibur = Lambda.extract(listJadwalLibur, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
        Collections.sort(listDateLibur);

        //Get List Public Non Religion Holiday Schedule
        List<WtHoliday> listPublicNonReligionHoliday = wtHolidayDao.getListPublicNonReligionHolidayBetweenDate(wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode());
        List<Date> listPublicNonReligionHolidayDate = Lambda.extract(listPublicNonReligionHoliday, Lambda.on(WtHoliday.class).getHolidayDate());
        Collections.sort(listPublicNonReligionHolidayDate);

        //Get List Public Religion Holiday Schedule
        List<WtHoliday> listPublicReligionHoliday = wtHolidayDao.getListPublicReligionHolidayByReligionCodeAndBetweenDate(wtPeriode.getFromPeriode(), wtPeriode.getUntilPeriode(), religionCode);
        List<Date> listPublicReligionHolidayDate = Lambda.extract(listPublicReligionHoliday, Lambda.on(WtHoliday.class).getHolidayDate());
        Collections.sort(listPublicReligionHolidayDate);

        //Looping List Overtime Implementation
        for (ImplementationOfOverTime otImpl : listImplementationOfOverTime) {

            List<WtHitungLemburJam> listWtHitungLemburJam = wtHitungLemburJamDao.getListByWtHitungLemburId(otImpl.getWtOverTime().getWtHitungLembur().getId());

            Boolean isImplementedOnScheduleHoliday = Collections.binarySearch(listDateLibur, otImpl.getImplementationDate(), new MyDateComparator()) >= 0;
            Boolean isImplementedOnPublicNonReligionHoliday = Collections.binarySearch(listPublicNonReligionHolidayDate, otImpl.getImplementationDate(), new MyDateComparator()) >= 0;
            Boolean isImplementedOnPublicReligionHoliday = Collections.binarySearch(listPublicReligionHolidayDate, otImpl.getImplementationDate(), new MyDateComparator()) >= 0;

            //get total Overtime in hours
            Float totalHoursOvertime = DateTimeUtil.getTotalHoursDifference(otImpl.getStartTime(), otImpl.getEndTime()).floatValue();

            //Initialize real total hours overtime
            Float realTotalOverTime = 0f;

            //Check implementOn and calculate real total hours based on implementOn
            if (isImplementedOnScheduleHoliday || isImplementedOnPublicNonReligionHoliday) {
                realTotalOverTime = countRealOverTime(totalHoursOvertime, listWtHitungLemburJam, "schduleOrPublicNonReligionHoliday");
            } else if (isImplementedOnPublicReligionHoliday) {
                realTotalOverTime = countRealOverTime(totalHoursOvertime, listWtHitungLemburJam, "publicReligionHoliday");
            } else {
                realTotalOverTime = countRealOverTime(totalHoursOvertime, listWtHitungLemburJam, "workingDay");
            }

            //increment totalOvertime with each realTotalOvertime record
            totalOvertime += realTotalOverTime;

        }

        //if totalOvertime Is Not A Number than reinitialize to 0
        if (totalOvertime.isNaN()) {
            totalOvertime = 0f;
        }

        //return totalOvertime of employee during period active
        return totalOvertime;
    }

    private Float countRealOverTime(Float totalOverTime, List<WtHitungLemburJam> listWtHitungLemburJam, String implementOn) {

        //Initialize totalRealOvertime
        Float totalRealOvertime = 0f;

        /* separate rounding value and decimal value from totalOvertime */
        Integer overtimeRounded = Math.round(totalOverTime);
        Float sisa = totalOverTime - overtimeRounded;

        /*Filter list WtHitungLemburJam, only record with field 'jamKe' value less or equal to overtimeRounded that will be invoke inside calculation process*/
        listWtHitungLemburJam = Lambda.select(listWtHitungLemburJam, Lambda.having(Lambda.on(WtHitungLemburJam.class).getJamKe(), Matchers.lessThanOrEqualTo(overtimeRounded)));

        WtHitungLemburJam wtHitungLemburJamSisa = null;
        Float realTotalOtFromWtHitungLemburJam = 0f;
        Float realSisa = 0f;

        //check implementOn
        switch (implementOn) {

            case "workingDay":

                /*if overtime was implemented on WorkingDay,
                 //sum real OverTime Value from field 'hariKerja' in listWtHitungLemburJam,
                 //and calculate realSisa from WtHitungLemburJam which field 'jamKe' equal overtimeRounded + 1*/
                realTotalOtFromWtHitungLemburJam = Lambda.sum(listWtHitungLemburJam, Lambda.on(WtHitungLemburJam.class).getHariKerja().floatValue());
                wtHitungLemburJamSisa = Lambda.selectFirst(listWtHitungLemburJam, Lambda.having(Lambda.on(WtHitungLemburJam.class).getJamKe(), Matchers.equalTo(overtimeRounded + 1)));
                if (null != wtHitungLemburJamSisa) {
                    realSisa = sisa * wtHitungLemburJamSisa.getHariKerja().floatValue();
                }

                break;

            case "publicReligionHoliday":

                /*if overtime was implemented on public Religion Holiday (from table WtHoliday with religion is not Null),
                 //sum real OverTime Value from field 'hariRaya' in listWtHitungLemburJam,
                 //and calculate realSisa from WtHitungLemburJam which field 'jamKe' equal overtimeRounded + 1*/
                realTotalOtFromWtHitungLemburJam = Lambda.sum(listWtHitungLemburJam, Lambda.on(WtHitungLemburJam.class).getHariRaya().floatValue());
                wtHitungLemburJamSisa = Lambda.selectFirst(listWtHitungLemburJam, Lambda.having(Lambda.on(WtHitungLemburJam.class).getJamKe(), Matchers.equalTo(overtimeRounded + 1)));
                if (null != wtHitungLemburJamSisa) {
                    realSisa = sisa * wtHitungLemburJamSisa.getHariRaya().floatValue();
                }

                break;

            case "schduleOrPublicNonReligionHoliday":

                /*if overtime was implemented on public holiday but not related to religion (ex Independence Day, 17 August) from table WtHoliday  or HolidaySchedule from table TempJadwalKaryawan,
                 //sum real OverTime Value from field 'hariLibur' in listWtHitungLemburJam,
                 //and calculate realSisa from WtHitungLemburJam which field 'jamKe' equal overtimeRounded + 1*/
                realTotalOtFromWtHitungLemburJam = Lambda.sum(listWtHitungLemburJam, Lambda.on(WtHitungLemburJam.class).getHariLibur().floatValue());
                wtHitungLemburJamSisa = Lambda.selectFirst(listWtHitungLemburJam, Lambda.having(Lambda.on(WtHitungLemburJam.class).getJamKe(), Matchers.equalTo(overtimeRounded + 1)));
                if (null != wtHitungLemburJamSisa) {
                    realSisa = sisa * wtHitungLemburJamSisa.getHariLibur().floatValue();
                }

                break;

            default:
                break;
        }

        //sum realTotalOtFromWtHitungLemburJam and realSisa to get totalRealOvertime
        totalRealOvertime = realTotalOtFromWtHitungLemburJam + realSisa;

        // return totalRealOvertime
        return totalRealOvertime;
    }

    private Integer calculateAndCheckTotalLeave(List<LeaveImplementation> listLeaveImplementation, Date dateFrom, Date dateUntill, Long empDataId) {

        Integer totalLeave = 0;
        List<Date> listDateInSelectedPeriod = getRangeOfDates(dateFrom, dateUntill);

        // Get List Public Holiday Schedule
        List<TempJadwalKaryawan> listEmpOffSchedule = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDateAndOffDay(empDataId, dateFrom, dateFrom);
        List<Date> listEmpOffDate = new ArrayList<Date>();

        if (!listEmpOffSchedule.isEmpty()) {
            listEmpOffDate = Lambda.extract(listEmpOffSchedule, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
            Collections.sort(listEmpOffDate);
        }

        // Get List Public Holiday Schedule
        List<WtHoliday> listPublicHoliday = wtHolidayDao.getBetweenDate(dateFrom, dateFrom);
        List<Date> listPublicHolidayDate = new ArrayList<Date>();

        if (!listPublicHoliday.isEmpty()) {
            listPublicHolidayDate = Lambda.extract(listPublicHoliday, Lambda.on(WtHoliday.class).getHolidayDate());
            Collections.sort(listPublicHolidayDate);
        }

        //Looping leaveImplementation
        for (LeaveImplementation leaveImpl : listLeaveImplementation) {

            // get totalLeaveDays, and list leaves date of each record
            int totalLeaveDaysPerRecord = getRangeOfDates(leaveImpl.getStartDate(), leaveImpl.getEndDate()).size();
            List<Date> listLeaveImplDate = getRangeOfDates(leaveImpl.getStartDate(), leaveImpl.getEndDate());
            Collections.sort(listLeaveImplDate);

            //check, whether leave startDate and end Date is in Period date
            Boolean isLeaveStartDateInPeriodRange = isDateIsBetWeenTwoDates(leaveImpl.getStartDate(), dateFrom, dateUntill);
            Boolean isLeaveEndDateInPeriodRange = isDateIsBetWeenTwoDates(leaveImpl.getEndDate(), dateFrom, dateUntill);

            //if leave startDate and end Date is in Period date, loop the list and check one By One
            if (isLeaveStartDateInPeriodRange && isLeaveEndDateInPeriodRange) {

                // If Leave dayType equal to HRMConstant.LEAVE_DAY_TYPE_WORKING, check again leave
                if (StringUtils.equals(HRMConstant.LEAVE_DAY_TYPE_WORKING, leaveImpl.getLeave().getDayType())) {

                    for (Date leaveDate : listLeaveImplDate) {

                        //Check if Leave was on public holiday
                        Boolean isLeaveOnPublicHoliday = isDateIsInListDate(leaveDate, listPublicHolidayDate);
                        Boolean isLeaveOnOffSchedule = Boolean.FALSE;

                        if (!listEmpOffSchedule.isEmpty()) {

                            //Check if Leave was on off day schedule
                            isLeaveOnOffSchedule = isDateIsInListDate(leaveDate, listEmpOffDate);

                        }

                        // if leave is implement on off day schedule, or public holiday and dayType of leave = HRMConstant.LEAVE_DAY_TYPE_WORKING, decrement totalLeaveDaysPerRecord
                        if (isLeaveOnOffSchedule || isLeaveOnPublicHoliday) {
                            totalLeaveDaysPerRecord--;
                        }
                    }
                }

            } else {

                for (Date leaveDate : listLeaveImplDate) {
                    Boolean isLeaveDateInPeriod = isDateIsInListDate(leaveDate, listDateInSelectedPeriod);
                    if (isLeaveDateInPeriod) {

                        // If Leave dayType equal to HRMConstant.LEAVE_DAY_TYPE_WORKING, check again leave
                        if (StringUtils.equals(HRMConstant.LEAVE_DAY_TYPE_WORKING, leaveImpl.getLeave().getDayType())) {

                            //Check if Leave was on public holiday
                            Boolean isLeaveOnPublicHoliday = isDateIsInListDate(leaveDate, listPublicHolidayDate);
                            Boolean isLeaveOnOffSchedule = Boolean.FALSE;

                            if (!listEmpOffSchedule.isEmpty()) {

                                //Check if Leave was on off day schedule
                                isLeaveOnOffSchedule = isDateIsInListDate(leaveDate, listEmpOffDate);

                            }

                            // if leave is implement on off day schedule, or public holiday and dayType of leave = HRMConstant.LEAVE_DAY_TYPE_WORKING, decrement totalLeaveDaysPerRecord
                            if (isLeaveOnOffSchedule || isLeaveOnPublicHoliday) {
                                totalLeaveDaysPerRecord--;
                            }

                        }

                    } else {

                        // no need to check, just decrement because leave was implemented out of date period range
                        totalLeaveDaysPerRecord--;

                    }
                }
            }

            //increment totalLeave with each totalLeaveDaysPerRecord
            totalLeave += totalLeaveDaysPerRecord;
        }

        //return totalLeave
        return totalLeave;
    }

    private Integer calculateAndCheckTotalPermit(List<PermitImplementation> listPermitImplementation, Date dateFrom, Date dateUntill, Long empDataId) {
        Integer totalPermit = 0;
        List<Date> listDateInSelectedPeriod = getRangeOfDates(dateFrom, dateUntill);

        //Get List Employee Off Schedule
        List<TempJadwalKaryawan> listEmpOffSchedule = tempJadwalKaryawanDao.getAllDataByEmpIdAndPeriodDateAndOffDay(empDataId, dateFrom, dateUntill);
        List<Date> listEmpOffDate = new ArrayList<Date>();

        if (!listEmpOffSchedule.isEmpty()) {
            listEmpOffDate = Lambda.extract(listEmpOffSchedule, Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja());
            Collections.sort(listEmpOffDate);
        }

        //Get List Public Holiday Schedule
        List<WtHoliday> listPublicHoliday = wtHolidayDao.getBetweenDate(dateFrom, dateFrom);
        List<Date> listPublicHolidayDate = new ArrayList<Date>();

        if (!listPublicHoliday.isEmpty()) {
            listPublicHolidayDate = Lambda.extract(listPublicHoliday, Lambda.on(WtHoliday.class).getHolidayDate());
            Collections.sort(listPublicHolidayDate);
        }

        //Looping List PermitImplementation
        for (PermitImplementation permitImplementation : listPermitImplementation) {

            //get total permit per record and list permitDates					
            List<Date> listSickDates = getRangeOfDates(permitImplementation.getStartDate(), permitImplementation.getEndDate());
            int totalPermitDaysPerRecord = listSickDates.size();
            Collections.sort(listSickDates);

            //Check whether permit startDate and end Date is in Period date
            Boolean isPermitStartDateInPeriodRange = isDateIsBetWeenTwoDates(permitImplementation.getStartDate(), dateFrom, dateUntill);
            Boolean isPermitEndDateInPeriodRange = isDateIsBetWeenTwoDates(permitImplementation.getEndDate(), dateFrom, dateUntill);

            //if permit startDate and end Date is in Period date, loop the list and check one By One
            if (isPermitStartDateInPeriodRange && isPermitEndDateInPeriodRange) {

                for (Date sickDate : listSickDates) {

                    //Check if permit was on public holiday
                    Boolean isPermitOnPublicHoliday = isDateIsInListDate(sickDate, listPublicHolidayDate);
                    Boolean isPermitOnOffSchedule = Boolean.FALSE;

                    if (!listEmpOffSchedule.isEmpty()) {

                        //Check if permit was on Off day Schedule
                        isPermitOnOffSchedule = isDateIsInListDate(sickDate, listEmpOffDate);

                    }

                    //if permit was on off day schedule, or on public holiday, decrement totalpermitDaysPerRecord because actually it didn't affect on employee total Attendance 
                    if (isPermitOnOffSchedule || isPermitOnPublicHoliday) {
                        totalPermitDaysPerRecord--;
                    }
                }

            } else {

                for (Date sickDate : listSickDates) {

                    //Check if permit is in period list date
                    Boolean isSickDateInPeriod = isDateIsInListDate(sickDate, listDateInSelectedPeriod);

                    if (isSickDateInPeriod) {

                        //Check if permit was on public holiday
                        Boolean isSickOnPublicHoliday = isDateIsInListDate(sickDate, listPublicHolidayDate);
                        Boolean isSickOnOffSchedule = Boolean.FALSE;

                        if (!listEmpOffSchedule.isEmpty()) {

                            //Check if permit was on Off day Schedule
                            isSickOnOffSchedule = isDateIsInListDate(sickDate, listEmpOffDate);

                        }

                        //if permit was on off day schedule, or on public holiday, decrement totalpermitDaysPerRecord because actually it didn't affect on employee total Attendance 
                        if (isSickOnOffSchedule || isSickOnPublicHoliday) {
                            totalPermitDaysPerRecord--;
                        }

                    } else {
                        // no need to check, just decrement, bacause permit date is out of list period date
                        totalPermitDaysPerRecord--;
                    }

                }
            }

            //increment totalPermit with each totalPermitDaysPerRecord
            totalPermit += totalPermitDaysPerRecord;
        }

        //return totalPermit
        return totalPermit;
    }

    private Integer calculateAndCheckTotalDuty(List<BusinessTravel> listBusinessTravel, Date dateFrom, Date dateUntill, Long empDataId) {
        Integer totalDuty = 0;
        List<Date> listDateInSelectedPeriod = getRangeOfDates(dateFrom, dateUntill);

        //Looping List listBusinessTravel
        for (BusinessTravel businessTravel : listBusinessTravel) {

            //get total days per record and list travelDates					
            List<Date> listTravelDates = getRangeOfDates(businessTravel.getStartDate(), businessTravel.getEndDate());
            int totalTravelDaysPerRecord = listTravelDates.size();

            Collections.sort(listTravelDates);

            //Check whether travel startDate and end Date is in Period date
            Boolean isTravelStartDateInPeriodRange = isDateIsBetWeenTwoDates(businessTravel.getStartDate(), dateFrom, dateUntill);
            Boolean isTravelEndDateInPeriodRange = isDateIsBetWeenTwoDates(businessTravel.getEndDate(), dateFrom, dateUntill);

            //if travel startDate or endDate is not in Period date, loop the list and check date, One By One.
            if (!isTravelStartDateInPeriodRange || !isTravelEndDateInPeriodRange) {

                for (Date travelDate : listTravelDates) {

                    //Check if travel is in period list date
                    Boolean isTravelDateInPeriod = isDateIsInListDate(travelDate, listDateInSelectedPeriod);

                    //if travel date is not in period list date
                    if (!isTravelDateInPeriod) {

                        // no need to check, just decrement, bacause travel date is out of list period date
                        totalTravelDaysPerRecord--;
                    }

                }

            }

            //increment totalDuty with each totalTravelDaysPerRecord
            totalDuty += totalTravelDaysPerRecord;
        }

        //return totalDuty
        return totalDuty;
    }

    private Boolean isDateIsBetWeenTwoDates(Date dateToCheck, Date dateFrom, Date dateUntill) {

        Boolean isGreaterOrEqThanStartDate = dateToCheck.compareTo(dateFrom) >= 0;
        Boolean isLessOrEqThanEndDate = dateToCheck.compareTo(dateUntill) <= 0;
        return isGreaterOrEqThanStartDate && isLessOrEqThanEndDate;
    }

    private Boolean isDateIsInListDate(Date dateToCheck, List<Date> listDate) {
        return Collections.binarySearch(listDate, dateToCheck, new MyDateComparator()) >= 0;
    }

    private List<TempJadwalKaryawan> getAllScheduleForView(Long workingGroupId, Date createDate) throws Exception {
        List<TempJadwalKaryawan> dataToShow = new ArrayList<>();
        WtGroupWorking selectedWtGroupWorking = wtGroupWorkingDao.getEntiyByPK(workingGroupId);
        Date startDate = selectedWtGroupWorking.getBeginTime();
        Date endDate = selectedWtGroupWorking.getEndTime();
        int numberOfDay = DateTimeUtil.getTotalDayDifference(startDate, endDate);
        int totalDateDif = DateTimeUtil.getTotalDayDifference(startDate, createDate) + 1;
        int num = numberOfDay + 1;
        int hasilBagi = (totalDateDif) / (num);
        Date beginScheduleDate;
        Date tanggalAkhirJadwal = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - 1, CommonUtilConstant.DATE_FORMAT_DAY);
        if (new SimpleDateFormat("ddMMyyyy").format(tanggalAkhirJadwal).equals(new SimpleDateFormat("ddMMyyyy").format(new Date()))) {
            beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num) - num, CommonUtilConstant.DATE_FORMAT_DAY);
        } else {
            beginScheduleDate = DateTimeUtil.getDateFrom(startDate, (hasilBagi * num), CommonUtilConstant.DATE_FORMAT_DAY);
        }
        int i = 0;
        List<WtScheduleShift> list = new ArrayList<>(selectedWtGroupWorking.getWtScheduleShifts());
        Collections.sort(list, shortByDate1);
        for (WtScheduleShift list1 : list) {
            TempJadwalKaryawan jadwalKaryawan = new TempJadwalKaryawan();
            jadwalKaryawan.setEmpData(new EmpData());
            jadwalKaryawan.setTanggalWaktuKerja(DateTimeUtil.getDateFrom(beginScheduleDate, i, CommonUtilConstant.DATE_FORMAT_DAY));
            WtHoliday holiday = wtHolidayDao.getWtHolidayByDate(jadwalKaryawan.getTanggalWaktuKerja());
            if (holiday != null && selectedWtGroupWorking.getTypeSequeace().equals(HRMConstant.NORMAL_SCHEDULE)) {
                jadwalKaryawan.setWtWorkingHour(wtWorkingHourDao.getByCode("OFF"));
            } else {
                jadwalKaryawan.setWtWorkingHour(list1.getWtWorkingHour());
            }
            jadwalKaryawan.setIsCollectiveLeave(Boolean.FALSE);
            jadwalKaryawan.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            jadwalKaryawan.getWtWorkingHour();
            jadwalKaryawan.getWtWorkingHour().getAttendanceStatus().getStatusKehadrian();
            dataToShow.add(jadwalKaryawan);
            i++;
        }
        return dataToShow;
    }

    private final Comparator<WtScheduleShift> shortByDate1 = new Comparator<WtScheduleShift>() {
        @Override
        public int compare(WtScheduleShift o1, WtScheduleShift o2) {
            return o1.getScheduleDate().compareTo(o2.getScheduleDate());
        }
    };

    private Integer getTotalWorkingDaysBetween(Long empDataId, Date startDate, Date endDate) throws Exception {
        EmpData empData = empDataDao.getEntiyByPK(empDataId);
        Integer totalWorkingDays = 0;
        List<TempJadwalKaryawan> tempJadwalKaryawans = new ArrayList<TempJadwalKaryawan>();

        //loop date-nya, check jadwal berdasarkan kelompok kerja		
        for (Date loop = startDate; loop.before(endDate) || DateUtils.isSameDay(loop, endDate); loop = DateUtils.addDays(loop, 1)) {
            TempJadwalKaryawan jadwal = Lambda.selectFirst(tempJadwalKaryawans, Lambda.having(Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja().getTime(), Matchers.equalTo(loop.getTime())));
            if (jadwal == null) {
                //jika tidak terdapat jadwal kerja di date tersebut, maka generate jadwal kerja temporary-nya, lalu check kembali jadwal kerja-nya
                List<TempJadwalKaryawan> jadwalKaryawans = this.getAllScheduleForView(empData.getWtGroupWorking().getId(), loop);
                tempJadwalKaryawans.addAll(jadwalKaryawans);
                jadwal = Lambda.selectFirst(tempJadwalKaryawans, Lambda.having(Lambda.on(TempJadwalKaryawan.class).getTanggalWaktuKerja().getTime(), Matchers.equalTo(loop.getTime())));
            }

            //selain "OFF"(hari libur) berarti termasuk jam kerja
            if (!StringUtils.equals(jadwal.getWtWorkingHour().getCode(), "OFF")) {
                totalWorkingDays++;
            }
        }
        return totalWorkingDays;
    }

    private static List<Date> getRangeOfDates(Date firstDate, Date secondDate) {
        List<Date> result = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        Date left;
        Date right;

        // Compare the dates to see which is less
        if (firstDate.compareTo(secondDate) < 0) {
            left = firstDate;
            right = secondDate;
        } else {
            right = firstDate;
            left = secondDate;
        }

        cal.clear();
        cal.setTime(left);  // Seed the calendar with the starting date

        for (int i = 0; left.compareTo(right) < 0; i++) {
            result.add(left);           // Add the date iterator value to the result set
            cal.add(Calendar.DATE, 1);  // Add one day
            left = cal.getTime();       // Update the date iterator to the new date
        }

        return result;
    }

    class MyDateComparator implements Comparator<Date> {

        DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

        public int compare(Date d1, Date d2) {
            return DATE_FORMAT.format(d1).compareTo(DATE_FORMAT.format(d2));
        }
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<WorkingTimeDeviation> getWorkingHourDeviation(TempAttendanceRealizationSearchParameter parameter, int firstResult, int maxResults, Order order) throws Exception {
//        WtPeriode wtPeriode = this.wtPeriodeDao.getEntityByAbsentTypeActive();
        List<WorkingTimeDeviation> dataToShow = new ArrayList<>();
        List<EmpData> getAllEmmp = this.empDataDao.getAllDataNotTerminatePaging(parameter, firstResult, maxResults, order);
        for (EmpData emp : getAllEmmp) {
            Long totalDeviation = tempProcessReadFingerDao.getTotalTimeDeviation(emp.getId());
            WorkingTimeDeviation deviation = new WorkingTimeDeviation();
//            deviation.setTotalDeviation(totalDeviation);
            deviation.setEmpId(emp.getId());
            TempAttendanceRealization attendanceRealization = tempAttendanceRealizationDao.getByEmp(emp.getId());
            if (attendanceRealization != null) {
                deviation.setTotalOverTime(attendanceRealization.getOvertime().longValue());
                deviation.setAttendaceRealization(attendanceRealization.getAttendanceDaysPresent() + "/" + attendanceRealization.getAttendanceDaysSchedule());
            }

//            Long totalOverTime = tempAttendanceRealizationDao.getTotalOverTime(emp.getId());
//            deviation.setTotalOverTime(totalOverTime);
            deviation.setEmpRealName(emp.getBioData().getFirstName() + " " + emp.getBioData().getLastName());
            if (totalDeviation != null) {
                deviation.setTotalDeviation(totalDeviation);
//                if (totalDeviation < 0) {
//                    long totalAbsolut = -1 * totalDeviation;
//                    if (totalAbsolut >= 60) {
//                        long jam = totalAbsolut / 60;
//                        long minute = totalAbsolut % 60;
//                        deviation.setMinuteDefect(minute);
//                        deviation.setHourDefect(jam);
//                    } else {
//                        deviation.setExtraHour(Long.valueOf("0"));
//                        deviation.setExtraMinute(totalDeviation);
//                    }
////                    Date date = new Date(totalDeviation * (-1));
////                    deviation.setDateDefect(date);
////                    deviation.setHourDefect(totalDeviation / 60);
////                    deviation.setMinuteDefect(totalDeviation * (-1));
//                } else {
////                    Date date = new Date(totalDeviation);
////                    deviation.setDateDefect(date);
//                    Long time = totalDeviation * 60 * 1000;
//                    Date date = new Date(time);
//                    if (totalDeviation >= 60) {
//                        long jam = totalDeviation / 60;
//                        long minute = totalDeviation % 60;
//                        deviation.setExtraMinute(minute);
//                        deviation.setExtraHour(jam);
//
//                    } else {
//                        deviation.setExtraHour(Long.valueOf("0"));
//                        deviation.setExtraMinute(totalDeviation);
//                    }

            }
            deviation.setOverTime(null);
            dataToShow.add(deviation);

        }
        return dataToShow;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalWorkingHourDeviation(TempAttendanceRealizationSearchParameter parameter) throws Exception {
        return empDataDao.getTotalNotTerminatePaging(parameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public WorkingTimeDeviationDetailModel getEntityByEmpDataId(Long id) throws Exception {
        WorkingTimeDeviationDetailModel workingTimeDeviationDetailModel = new WorkingTimeDeviationDetailModel();
        //get Periode active
        WtPeriode wtPeriode = this.wtPeriodeDao.getEntityByAbsentTypeActive();
        EmpData empData = this.empDataDao.getByIdWithBioData(id);

        long totalHour = 0;
        long workingTime = 0;
        long workingHour = 0;
        int marginIn = 0;
        int marginOut = 0;
        int marginMinutes = 0;
        int marginHour = 0;
        long totalWorkingTime = 0;
        int restMinutesFromMargin = 0;
        List<TempProcessReadFinger> listTempProcessReadFinger = tempProcessReadFingerDao.getAllDataByEmpDataId(id);
        for (TempProcessReadFinger tempProcessReadFinger : listTempProcessReadFinger) {
            //get hour from schedule in and schedule out
            workingTime = Math.abs(tempProcessReadFinger.getScheduleIn().getTime() - tempProcessReadFinger.getScheduleOut().getTime());
            workingHour = workingTime / (60 * 60 * 1000);
            //get hour from margin in and margin out
            marginIn = tempProcessReadFinger.getMarginIn();
            marginOut = tempProcessReadFinger.getMarginOut();

            //get total from marginHour and totalHour
            marginMinutes += marginIn + marginOut;
            totalHour += workingHour;
        }

        marginHour = marginMinutes / 60;
        totalWorkingTime = marginHour + totalHour;
        //insert data to workingTimeDeviationDetailModel
        workingTimeDeviationDetailModel.setFromPeriod(wtPeriode.getFromPeriode());
        workingTimeDeviationDetailModel.setToPeriod(wtPeriode.getUntilPeriode());
        workingTimeDeviationDetailModel.setNikAndFullName(empData.getNik() + " " + empData.getBioData().getFirstName() + " " + empData.getBioData().getLastName());
        workingTimeDeviationDetailModel.setTotalWorkingTime(totalWorkingTime);
        restMinutesFromMargin = marginMinutes - (marginHour * 60);
        workingTimeDeviationDetailModel.setTotalMarginHour(marginHour);
        workingTimeDeviationDetailModel.setTotalMarginMinutes(restMinutesFromMargin);
        return workingTimeDeviationDetailModel;
    }
}
