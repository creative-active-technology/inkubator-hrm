/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.KlasifikasiKerjaJabatanDao;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.dao.TaxFreeDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.AnnouncementEmpType;
import com.inkubator.hrm.entity.AnnouncementGoljab;
import com.inkubator.hrm.entity.AnnouncementUnit;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.KlasifikasiKerjaJabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.entity.TaxFree;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.model.EmployeeRestModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

/**
 *
 * @author Deni Husni FR
 */
@Service(value = "empDataService")
@Lazy
public class EmpDataServiceImpl extends IServiceImpl implements EmpDataService {

    @Autowired
    private EmpDataDao empDataDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private BioDataDao bioDataDao;
    @Autowired
    private EmployeeTypeDao employeeTypeDao;
    @Autowired
    private JabatanDao jabatanDao;
    @Autowired
    private PaySalaryGradeDao paySalaryGradeDao;
    @Autowired
    private WtGroupWorkingDao wtGroupWorkingDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;
    @Autowired
    private EmpCareerHistoryDao empCareerHistoryDao;
    @Autowired
    private AttendanceStatusDao attendanceStatusDao;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private TaxFreeDao taxFreeDao;
    @Autowired
    private BioEducationHistoryDao bioEducationHistoryDao;
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    private KlasifikasiKerjaJabatanDao klasifikasiKerjaJabatanDao;
    @Autowired
    private EducationLevelDao educationLevelDao;

    @Override
    public EmpData getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getEntiyByPK(Long id) throws Exception {
        EmpData empData = empDataDao.getEntiyByPK(id);
        empData.getBioData().getFirstName();
        empData.getBioData().getLastName();
        empData.getJabatanByJabatanId().getName();
        if (empData.getGolonganJabatan() != null) {
            empData.getGolonganJabatan().getPangkat().getPangkatCode();
        }
        if (empData.getWtGroupWorking() != null) {
            empData.getWtGroupWorking().getCode();
        }

        return empData;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(EmpData entity) throws Exception {
        long totalDuplicates = empDataDao.getTotalByNIK(entity.getNik());
        if (totalDuplicates > 0) {
            throw new BussinessException("emp_data.error_nik_duplicate");
        }

//        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        entity.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        Jabatan jabatan = jabatanDao.getEntiyByPK(entity.getJabatanByJabatanId().getId());
        entity.setJabatanByJabatanId(jabatan);
        entity.setStatus(HRMConstant.EMP_PLACEMENT);
        entity.setJabatanByJabatanGajiId(jabatan);
        entity.setGolonganJabatan(jabatan.getGolonganJabatan());
        PaySalaryGrade paySalaryGrade = paySalaryGradeDao.getEntiyByPK(entity.getPaySalaryGrade().getId());
        entity.setPaySalaryGrade(paySalaryGrade);
        double min = paySalaryGrade.getMinSalary().doubleValue();
        double max = paySalaryGrade.getMaxSalary().doubleValue();
        String basicSalaryDecript = AESUtil.getAESDescription(entity.getBasicSalary(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        double valueDecript = Double.parseDouble(basicSalaryDecript);
        if (valueDecript > max || valueDecript < min) {
            throw new BussinessException("emp_data.error_salary_range");
        }
        entity.setRotasiDate(entity.getJoinDate());
        empDataDao.save(entity);
        EmpCareerHistory careerHistory = new EmpCareerHistory();
        careerHistory.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        careerHistory.setCreatedBy(UserInfoUtil.getUserName());
        careerHistory.setCreatedOn(new Date());
        careerHistory.setGolonganJabatan(jabatan.getGolonganJabatan());
        careerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        careerHistory.setJabatan(jabatan);
        careerHistory.setNik(entity.getNik());
        careerHistory.setNoSk(entity.getNoSk());
        careerHistory.setSalary(entity.getBasicSalary());
        careerHistory.setTglPenganngkatan(entity.getJoinDate());
        careerHistory.setStatus(HRMConstant.EMP_PLACEMENT);
        careerHistory.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        empCareerHistoryDao.save(careerHistory);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(EmpData entity) throws Exception {
        long totalDuplicates = empDataDao.getTotalByNikandNotId(entity.getNik(), entity.getId());

        if (totalDuplicates > 0) {
            throw new BussinessException("emp_data.error_nik_duplicate");
        }
        EmpData empData = this.empDataDao.getEntiyByPK(entity.getId());
        empData.setBasicSalary(entity.getBasicSalary());
        empData.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        empData.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        Jabatan jabatan = jabatanDao.getEntiyByPK(entity.getJabatanByJabatanId().getId());
        empData.setJabatanByJabatanId(jabatan);
        empData.setJabatanByJabatanGajiId(jabatan);
        empData.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        empData.setHeatlyPremi(entity.getHeatlyPremi());
        empData.setInsentifStatus(entity.getInsentifStatus());
        empData.setIsFinger(entity.getIsFinger());
        empData.setJoinDate(entity.getJoinDate());
        empData.setNik(entity.getNik());
        empData.setNoSk(entity.getNoSk());
        empData.setRotasiDate(entity.getRotasiDate());
        PaySalaryGrade paySalaryGrade = paySalaryGradeDao.getEntiyByPK(entity.getPaySalaryGrade().getId());
        empData.setPaySalaryGrade(paySalaryGrade);
        double min = paySalaryGrade.getMinSalary().doubleValue();
        double max = paySalaryGrade.getMaxSalary().doubleValue();
        String basicSalaryDecript = AESUtil.getAESDescription(entity.getBasicSalary(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        double valueDecript = Double.parseDouble(basicSalaryDecript);
        if (valueDecript > max || valueDecript < min) {
            throw new BussinessException("emp_data.error_salary_range");
        }
        empData.setPpip(entity.getPpip());
        empData.setPpmp(entity.getPpmp());
        empData.setPtkpNumber(entity.getPtkpNumber());
        empData.setPtkpStatus(entity.getPtkpStatus());
        empData.setUpdatedBy(UserInfoUtil.getUserName());
        empData.setUpdatedOn(new Date());
        if (entity.getWtGroupWorking() != null) {
            empData.setWtGroupWorking(wtGroupWorkingDao.getEntiyByPK(entity.getWtGroupWorking().getId()));
        }
        this.empDataDao.update(empData);
        EmpCareerHistory careerHistory = this.empCareerHistoryDao.getByBioIdandStatus(empData.getBioData().getId(), HRMConstant.EMP_PLACEMENT);
        careerHistory.setNik(empData.getNik());
        careerHistory.setNoSk(empData.getNoSk());
        careerHistory.setStatus(empData.getStatus());
        careerHistory.setSalary(empData.getBasicSalary());
        careerHistory.setTglPenganngkatan(empData.getJoinDate());
        careerHistory.setUpdateBy(UserInfoUtil.getUserName());
        careerHistory.setUpdatedOn(new Date());
        careerHistory.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        this.empCareerHistoryDao.update(careerHistory);
    }

    @Override
    public void saveOrUpdate(EmpData enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData saveData(EmpData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData updateData(EmpData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData saveOrUpdateData(EmpData entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpData getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(EmpData entity) throws Exception {
        this.empDataDao.delete(entity);
    }

    @Override
    public void softDelete(EmpData entity) throws Exception {
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllData() throws Exception {
        return empDataDao.getAllData();
    }

    @Override
    public List<EmpData> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpData> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Cacheable(value = "totalEmployeeByGender", key = "#companyId")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByGender(Long companyId) throws Exception {
        Long male = empDataDao.getTotalByGender(HRMConstant.GLOBAL_MALE);
        Long female = empDataDao.getTotalByGender(HRMConstant.GLOBAL_FEMALE);

        Map<String, Long> results = new HashMap<String, Long>();
        results.put("male", male);
        results.put("female", female);
        results.put("lastUpdate", new Date().getTime());

        return results;
    }

    @Override
    @Cacheable(value = "totalEmployeeByAge", key = "#companyId")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByAge(Long companyId) throws Exception {
        Date now = new Date();
        Date startDate = new Date();
        Date endDate = new Date();

        //age less than 26
        startDate = DateTimeUtil.getDateFrom(now, -26, CommonUtilConstant.DATE_FORMAT_YEAR);
        Long lessThan26 = empDataDao.getTotalByAgeLessThan(startDate);

        //age between 26 - 30
        startDate = DateTimeUtil.getDateFrom(now, -30, CommonUtilConstant.DATE_FORMAT_YEAR);
        endDate = DateTimeUtil.getDateFrom(now, -26, CommonUtilConstant.DATE_FORMAT_YEAR);
        Long between26And30 = empDataDao.getTotalByAgeBetween(startDate, endDate);

        //age between 31 - 35
        startDate = DateTimeUtil.getDateFrom(now, -35, CommonUtilConstant.DATE_FORMAT_YEAR);
        endDate = DateTimeUtil.getDateFrom(now, -31, CommonUtilConstant.DATE_FORMAT_YEAR);
        Long between31And35 = empDataDao.getTotalByAgeBetween(startDate, endDate);

        //age between 36 - 40
        startDate = DateTimeUtil.getDateFrom(now, -40, CommonUtilConstant.DATE_FORMAT_YEAR);
        endDate = DateTimeUtil.getDateFrom(now, -36, CommonUtilConstant.DATE_FORMAT_YEAR);
        Long between36And40 = empDataDao.getTotalByAgeBetween(startDate, endDate);

        //age more than 40
        startDate = DateTimeUtil.getDateFrom(now, -40, CommonUtilConstant.DATE_FORMAT_YEAR);
        Long moreThan40 = empDataDao.getTotalByAgeMoreThan(startDate);

        Map<String, Long> results = new HashMap<String, Long>();
        results.put("lessThan26", lessThan26);
        results.put("between26And30", between26And30);
        results.put("between31And35", between31And35);
        results.put("between36And40", between36And40);
        results.put("moreThan40", moreThan40);
        results.put("lastUpdate", new Date().getTime());

        return results;
    }

    @Override
    @Cacheable(value = "totalEmployeeByDepartment", key = "#companyId")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByDepartment(Long companyId) throws Exception {
        List<Department> departments = departmentDao.getAllData();
        Map<String, Long> results = new HashMap<String, Long>();
        for (Department department : departments) {
            Long total = empDataDao.getTotalByDepartmentId(department.getId());
            results.put(StringsUtils.slicePerWord(department.getDepartmentName(), 25), total);
        }
        //sorting by value (dari yang besar ke yang kecil)
        results = MapUtil.sortByValueDesc(results);
        results.put("lastUpdate", new Date().getTime());

        return results;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.empDataDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) throws Exception {
        return this.empDataDao.getTotalEmpDataByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getByEmpIdWithDetail(long id) throws Exception {
        EmpData empData = empDataDao.getByEmpIdWithDetail(id);
        empData.getJabatanByJabatanId().getDepartment().getDepartmentName();
        empData.getJabatanByJabatanId().getUnitKerja().getName();
        empData.getGolonganJabatan().getCode();
        empData.getGolonganJabatan().getPangkat().getPangkatName();
        return this.empDataDao.getByEmpIdWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getByBioDataIdWithDepartment(long id) throws Exception {
        return empDataDao.getByBioDataWithDepartment(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByNameOrNik(String param) throws Exception {
        return empDataDao.getAllDataByNameOrNik(param);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getByIdWithDetail(long id) throws Exception {
        return empDataDao.getByIdWithDetail(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getEntityByNik(String nik) throws Exception {
        return empDataDao.getEntityByNik(nik);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doSaveRotasi(EmpData entity) throws Exception {
        //check if nik employee is duplicate
        long totalDuplicates = empDataDao.getTotalByNikandNotId(entity.getNik(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("emp_data.error_nik_duplicate");
        }

        //check if the employee still have pending task approval
        HrmUser user = hrmUserDao.getByEmpDataId(entity.getId());
        long totalPendingTask = 0;
        if (user != null) {
            totalPendingTask = approvalActivityDao.getPendingTask(user.getUserId()).size();
        } else {
            throw new BussinessException("emp_data.error_donot_have_user");
        }

        if (totalPendingTask > 0) {
            throw new BussinessException("emp_data.error_cannot_do_rotation");
        }

        EmpData empData = this.empDataDao.getEntiyByPK(entity.getId());
        empData.setBasicSalary(entity.getBasicSalary());
        empData.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        empData.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        Jabatan jabatan = jabatanDao.getEntiyByPK(entity.getJabatanByJabatanId().getId());
        empData.setJabatanByJabatanId(jabatan);
        empData.setJabatanByJabatanGajiId(jabatan);
        empData.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(entity.getGolonganJabatan().getId()));
        empData.setHeatlyPremi(entity.getHeatlyPremi());
        empData.setInsentifStatus(entity.getInsentifStatus());
        empData.setIsFinger(entity.getIsFinger());
        empData.setJoinDate(entity.getJoinDate());
        empData.setNik(entity.getNik());
        empData.setNoSk(entity.getNoSk());
        empData.setRotasiDate(entity.getRotasiDate());
        PaySalaryGrade paySalaryGrade = paySalaryGradeDao.getEntiyByPK(entity.getPaySalaryGrade().getId());
        empData.setPaySalaryGrade(paySalaryGrade);
        double min = paySalaryGrade.getMinSalary().doubleValue();
        double max = paySalaryGrade.getMaxSalary().doubleValue();
        String basicSalaryDecript = AESUtil.getAESDescription(entity.getBasicSalary(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
        double valueDecript = Double.parseDouble(basicSalaryDecript);
        if (valueDecript > max || valueDecript < min) {
            throw new BussinessException("emp_data.error_salary_range");
        }
        empData.setPpip(entity.getPpip());
        empData.setPpmp(entity.getPpmp());
        empData.setPtkpNumber(entity.getPtkpNumber());
        empData.setPtkpStatus(entity.getPtkpStatus());
        empData.setUpdatedBy(UserInfoUtil.getUserName());
        empData.setUpdatedOn(new Date());
        empData.setStatus(HRMConstant.EMP_ROTATION);
        if (entity.getWtGroupWorking() != null) {
            empData.setWtGroupWorking(wtGroupWorkingDao.getEntiyByPK(entity.getWtGroupWorking().getId()));
        }
        this.empDataDao.update(empData);
        EmpCareerHistory careerHistory = new EmpCareerHistory();
        careerHistory.setBioData(bioDataDao.getEntiyByPK(entity.getBioData().getId()));
        careerHistory.setCreatedBy(UserInfoUtil.getUserName());
        careerHistory.setCreatedOn(new Date());
        careerHistory.setGolonganJabatan(jabatan.getGolonganJabatan());
        careerHistory.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        careerHistory.setJabatan(jabatan);
        careerHistory.setNik(entity.getNik());
        careerHistory.setNoSk(entity.getNoSk());
        careerHistory.setSalary(entity.getBasicSalary());
        careerHistory.setTglPenganngkatan(entity.getRotasiDate());
        careerHistory.setStatus(HRMConstant.EMP_ROTATION);
        careerHistory.setEmployeeType(employeeTypeDao.getEntiyByPK(entity.getEmployeeType().getId()));
        empCareerHistoryDao.save(careerHistory);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataNotExistInUserByParam(String param, int firstResult, int maxResults, Order order) throws Exception {
        return empDataDao.getAllDataNotExistInUserByParam(param, firstResult, maxResults, order);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalNotExistInUserByParam(String param) throws Exception {
        return empDataDao.getTotalNotExistInUserByParam(param);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model) throws Exception {
        return empDataDao.getTotalBySearchEmployee(model);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getEmployeelBySearchEmployeeLeave(DistributionLeaveSchemeModel model) throws Exception {
        return empDataDao.getEmployeeBySearchEmployeeLeave(model);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model) throws Exception {
        return empDataDao.getEmployeeByOtSearchParameter(model);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getEmpDataByListId(List<Long> data) throws Exception {
        return this.empDataDao.getEmpDataByListId(data);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.empDataDao.getAllDataReportEmpWorkingGroupByParam(param, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param) throws Exception {
        return this.empDataDao.getTotalReportEmpWorkingGroupByParam(param);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getEmployeelBySearchEmployeePermit(PermitDistributionModel model) throws Exception {
        return empDataDao.getEmployeeBySearchEmployeePermit(model);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.empDataDao.getAllDataReportEmpDepartmentJabatanByParam(param, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param) throws Exception {
        return this.empDataDao.getTotalReportEmpDepartmentJabatanByParam(param);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<EmpData> getEmployeeBySearchEmployeeFingerException(WtFingerExceptionModel model) throws Exception {
        return empDataDao.getEmployeeBySearchEmployeeFingerException(model);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveForPtkp(EmpData empData) throws Exception {
        EmpData update = empDataDao.getEmpDataWithBiodata(empData.getId());
        update.setPtkpNumber(empData.getPtkpNumber());
        update.setPtkpStatus(empData.getPtkpStatus());
        TaxFree taxFree = null;
        String status = HRMConstant.TF_STATUS_TIDAK_KAWIN;
        Integer ptkpNumber = HRMConstant.TF_INC_PERSON_ZERO;
        /*
         Jika gender = female, dianggap tidak punya tanggungan dan tidak menikah
         Jika tanggungan lebih besar dari 3,  tanggungan max tetap 3
         */
//        if(update.getBioData().getGender() == HRMConstant.GLOBAL_FEMALE){
//            status = HRMConstant.TF_STATUS_TIDAK_KAWIN;
//        }else{
        status = (empData.getPtkpStatus() == Boolean.TRUE) ? "K" : "TK";
        if (empData.getPtkpStatus() == Boolean.TRUE) {
            ptkpNumber = (empData.getPtkpNumber() > 3) ? 3 : empData.getPtkpNumber();
        } else {
            ptkpNumber = 0;
        }

        taxFree = taxFreeDao.getEntityByTfStatusAndIncPerson(status, ptkpNumber);
        update.setTaxFree(taxFree);
        this.empDataDao.update(update);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getEmpDataWithBiodata(Long id) throws Exception {
        return empDataDao.getEmpDataWithBiodata(id);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataNotTerminate() throws Exception {
        return this.empDataDao.getAllDataNotTerminate();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalEmpDataNotTerminate() throws Exception {
        return this.empDataDao.getTotalEmpDataNotTerminate();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByTaxFreeIsNull() throws Exception {
        return this.empDataDao.getTotalByTaxFreeIsNull();

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate) throws Exception {
        return this.empDataDao.getAllDataNotTerminateAndJoinDateLowerThan(payrollCalculationDate);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter param, int firstResult, int maxResults, Order orderable) throws Exception {
        return this.empDataDao.getAllDataSalaryConfirmationByParam(param, firstResult, maxResults, orderable);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalSalaryConfirmationByParam(SalaryConfirmationParameter param) throws Exception {
        return this.empDataDao.getTotalSalaryConfirmationByParam(param);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    public EmpData getByPKBankTransfer(long id) throws Exception {
        EmpData data = this.empDataDao.getByPKBankTransfer(id);
        return data;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public EmpData getByEmpDataByBioDataId(long bioDataid) {
        return this.empDataDao.getByEmpDataByBioDataId(bioDataid);
    }

    @Override
    public List<EmpDataMatrixModel> getAllDataByAbsisAndOrdinateAndGoljab(String absis, String ordinate, long golJabId) throws Exception {
        return getAllDataByAbsisAndOrdinateAndGoljab(absis, ordinate, golJabId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public BioDataModel getEmpNameWithNearestBirthDate() {
        return this.empDataDao.getEmpNameWithNearestBirthDate();
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByDepartementAndEducation(List<Department> listDepartement, List<EducationLevel> listEducation, int firstResult, int maxResults, Order order) {
        List<Long> listDepartmentId = new ArrayList<Long>();
        List<Long> listEducationLevelId = new ArrayList<Long>();
        for (EducationLevel educationLevel : listEducation) {
            listEducationLevelId.add(educationLevel.getId());
        }

        for (Department department : listDepartement) {
            listDepartmentId.add(department.getId());
        }
        return empDataDao.getAllDataByDepartementAndEducation(listDepartmentId, listEducationLevelId, firstResult, maxResults, order);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalDataByDepartementAndEducation(List<Department> listDepartement, List<EducationLevel> listEducation) throws Exception {
        List<Long> listDepartmentId = new ArrayList<Long>();
        List<Long> listEducationLevelId = new ArrayList<Long>();
        for (EducationLevel educationLevel : listEducation) {
            listEducationLevelId.add(educationLevel.getId());
        }

        for (Department department : listDepartement) {
            listDepartmentId.add(department.getId());
        }

        return empDataDao.getTotalDataByDepartementAndEducation(listDepartmentId, listEducationLevelId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<ReportEmployeeEducationViewModel> getAllDataByDepartementAndEducationWithHql(List<Department> departementId, List<EducationLevel> educationId, int firstResult, int maxResults, Order order) {
        List<Long> listDepartmentId = new ArrayList<Long>();
        List<Long> listEducationLevelId = new ArrayList<Long>();
        for (EducationLevel educationLevel : educationId) {
            listEducationLevelId.add(educationLevel.getId());
        }

        for (Department department : departementId) {
            listDepartmentId.add(department.getId());
        }
        return empDataDao.getAllDataByDepartementAndEducationWithHql(listDepartmentId, listEducationLevelId, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, int firstResult, int maxResults, Order order) {
        return empDataDao.getReportRekapJabatanByParam(listDepartmentId, listEmpTypeId, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId) {
        return empDataDao.getTotalReportRekapJabatanByParam(listDepartmentId, listEmpTypeId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<ReportEmpPensionPreparationModel> getReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges, int firstResult, int maxResults, Order order) {
        return empDataDao.getReportPensionPreparementByParam(listDepartmentId, listEmpTypeId, listEmpAges, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges) {
        return empDataDao.getTotalReportPensionPreparementByParam(listDepartmentId, listEmpTypeId, listEmpAges);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByDepartmentAndReligionAndGolJabAndEmpType(List<Long> departmentIds, List<Long> religionIds, List<Long> golJabIds, List<Long> empTypeIds) {
        return empDataDao.getAllDataByDepartmentAndReligionAndGolJabAndEmpType(departmentIds, religionIds, golJabIds, empTypeIds);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByParamWithDetail(List<Department> department, List<GolonganJabatan> golJab, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik, int firstResult, int maxResults, Order order) throws Exception {
        Long[] deptId = new Long[department.size()];
        int i = 0;
        for (Department dept : department) {
            deptId[i] = dept.getId();
        }

        return empDataDao.getAllDataByParamWithDetail(department, golJab, empTypeName, listAge, listJoinDate, listNik, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<String> getAllNikBetween(String from, String until) throws Exception {
        return empDataDao.getAllNikBetween(from, until);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParamWithDetail(List<Department> deptId, List<GolonganJabatan> golJabId, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik) throws Exception {
        return empDataDao.getTotalByParamWithDetail(deptId, golJabId, empTypeName, listAge, listJoinDate, listNik);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId, int firstResult, int maxResults, Order order) throws Exception {
        return empDataDao.getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(empTypeId, golJabId, unitKerjaId, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId) throws Exception {
        return empDataDao.getTotalDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(empTypeId, golJabId, unitKerjaId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataByAnnouncementId(Long announcementId) {
        Announcement announcement = announcementDao.getEntiyByPK(announcementId);
        Long companyId = announcement.getCompany().getId();
        List<Long> empTypes = Lambda.extract(announcement.getAnnouncementEmpTypes(), Lambda.on(AnnouncementEmpType.class).getEmployeeType().getId());
        List<Long> golJabs = Lambda.extract(announcement.getAnnouncementGoljabs(), Lambda.on(AnnouncementGoljab.class).getGolonganJabatan().getId());
        List<Long> unitKerjas = Lambda.extract(announcement.getAnnouncementUnits(), Lambda.on(AnnouncementUnit.class).getUnitKerja().getId());
        return empDataDao.getAllDataByCompanyIdAndEmpTypeAndGolJabAndUnitKerja(companyId, empTypes, golJabs, unitKerjas);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalKaryawanByJabatanId(Long jabatanId) throws Exception {
        return empDataDao.getTotalKaryawanByJabatanId(jabatanId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getByParam(String nikOrNameSearchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return empDataDao.getByParam(nikOrNameSearchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalEmpDataByParam(String nikOrNameSearchParameter) throws Exception {
        return empDataDao.getTotalEmpDataByParam(nikOrNameSearchParameter);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public String getBioDataNameByEmpDataId(Long id) throws Exception {
		return empDataDao.getBioDataNameByEmpDataId(id);
	}

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<SearchEmployeeCandidateViewModel> getAllDataEmpCandidateByParamWithDetail(List<Long> listJabatanId, List<Long> listReligionId, List<Integer> listAge, List<Integer> listJoinDate, Double gpa, Long educationLevelId,  String gender, int firstResult, int maxResults, Order order) throws Exception {
        List<SearchEmployeeCandidateViewModel> listSearchEmployeeCandidateViewModels = this.empDataDao.getAllDataEmpCandidateByParamWithDetail(listJabatanId, listReligionId, listAge, listJoinDate, gpa, educationLevelId, gender, firstResult, maxResults, order);
        EducationLevel educationLevel = educationLevelDao.getEntiyByPK(educationLevelId);
        
        // Set Detail Position Criteria of each Data 
        for (SearchEmployeeCandidateViewModel searchEmployeeCandidateViewModel : listSearchEmployeeCandidateViewModels) {
            List<KlasifikasiKerjaJabatan> listKlasifikasiKerjaJabatans = klasifikasiKerjaJabatanDao.getByJabatanId(searchEmployeeCandidateViewModel.getIdJabatan());
            String kriteria = StringsUtils.EMPTY;
            
            for (KlasifikasiKerjaJabatan klasifikasiKerjaJabatan : listKlasifikasiKerjaJabatans) {
                Boolean isNotLastRecord = listKlasifikasiKerjaJabatans.indexOf(klasifikasiKerjaJabatan) < listKlasifikasiKerjaJabatans.size() - 1;
                kriteria += klasifikasiKerjaJabatan.getKlasifikasiKerja().getDescription()  +  (isNotLastRecord ? ", " : "");
            }
            searchEmployeeCandidateViewModel.setKriteria(kriteria);
            searchEmployeeCandidateViewModel.setLastEducationLevelName(educationLevel.getName());
            
        }

        return listSearchEmployeeCandidateViewModels;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalEmpCandidateByParamWithDetail(List<Long> listJabatanId, List<Long> listReligionId, List<Integer> listAge, List<Integer> listJoinDate, Double gpa, Long educationLevelId, String gender) throws Exception {
        return this.empDataDao.getTotalEmpCandidateByParamWithDetail(listJabatanId, listReligionId, listAge, listJoinDate, gpa, educationLevelId, gender);
    }
    
    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmployeeRestModel> getAllDataRestModel(String nikOrName) throws Exception{
    	List<EmpData> listEmpData = empDataDao.getAllDataWithoutJoinCompany(nikOrName);
    	List<EmployeeRestModel> listModel =  new ArrayList<EmployeeRestModel>();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", new Locale("en"));
    	
    	for(EmpData empData : listEmpData){
    		BioData bioData = empData.getBioData();
    		if(empData != null && bioData != null) {
    			EmployeeRestModel model = this.bindToModel(bioData);
    			listModel.add(model);
    		}
    	}
    	
    	return listModel;
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public EmployeeRestModel getRestModelByNik(String nik) throws Exception {
		EmployeeRestModel model = null;
		EmpData empData = empDataDao.getEntityByNik(nik);		
		
		if(empData != null && empData.getBioData() != null) {
			BioData bioData = empData.getBioData();
			model = this.bindToModel(bioData);
		}
		return model;
	}
	
	private EmployeeRestModel bindToModel(BioData bioData) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", new Locale("en"));
		
		EmployeeRestModel model = new EmployeeRestModel();
		model.setTitle(bioData.getTitle());
		model.setFirstname(bioData.getFirstName());
		model.setLastname(bioData.getLastName());
		model.setPlaceOfBirth(bioData.getCity().getCityName());
		model.setDateOfBirth(dateFormat.format(bioData.getDateOfBirth()));
		model.setGender(bioData.getGender().equals(HRMConstant.GLOBAL_MALE)? 1 : 2);
		model.setMaritalStatus(bioData.getMaritalStatus().getName());
		model.setHomePhone("");
		model.setHandPhone(bioData.getMobilePhone());
		model.setEmail(bioData.getPersonalEmail());
		
		for(BioAddress address : bioData.getBioAddresses()){
			model.setAddressLine1(address.getAddressDetail());
			model.setAddressLine2(address.getNotes());
			model.setCity(address.getCity().getCityName());
			model.setState(address.getCity().getProvince().getProvinceName());
			model.setCountry(address.getCity().getProvince().getCountry().getCountryName());
			model.setZipCode("");
			break;
		}
		
		model.setNationality(bioData.getNationality().getNationalityName());		
		for(BioIdCard idCard : bioData.getBioIdCards()){
			model.setIdentityType(idCard.getType());
			model.setIdentityNumber(idCard.getCardNumber());
			model.setIdentityIssuingPlace(idCard.getCity().getCityName());
			model.setIdentityIssuingDate(dateFormat.format(idCard.getIssuedDate()));
			model.setExpiryDate(dateFormat.format(idCard.getValidDate()));
			break;
		}
		
		return model;
	}
}
