/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;


import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.hamcrest.Matcher;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.lambdaj.Lambda;
import ch.lambdaj.group.Group;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.AttendanceStatusDao;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.BioEducationHistoryDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.dao.TaxFreeDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.entity.BioAddress;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioIdCard;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.TaxFree;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.util.ResourceBundleUtil;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.model.EmployeeRestModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
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
        if(empData.getGolonganJabatan() != null){
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
    public List<EmpData> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    @Cacheable(value = "totalEmployeeByGender")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByGender() throws Exception {
        Long male = empDataDao.getTotalByGender(HRMConstant.GLOBAL_MALE);
        Long female = empDataDao.getTotalByGender(HRMConstant.GLOBAL_FEMALE);

        Map<String, Long> results = new HashMap<String, Long>();
        results.put("male", male);
        results.put("female", female);
        results.put("lastUpdate", new Date().getTime());

        return results;
    }

    @Override
    @Cacheable(value = "totalEmployeeByAge")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByAge() throws Exception {
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
    @Cacheable(value = "totalEmployeeByDepartment")
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public Map<String, Long> getTotalByDepartment() throws Exception {
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
    public List<EmpData> getAllDataWithRelation() throws Exception {
        return empDataDao.getAllDataWithRelation();
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
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<EmpData> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception {
        return empDataDao.getAllDataReportOfEmployeesFamilyByParam(searchParameter, firstResult, maxResults, orderable);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter) throws Exception {
        return empDataDao.getTotalReportOfEmployeesFamilyByParam(searchParameter);
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
        }else{
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
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
	public Boolean isEmpDataWithNullWtGroupWorkingExist() throws Exception {
		return empDataDao.isEmpDataWithNullWtGroupWorkingExist();
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public Map<String,List<DepAttendanceRealizationViewModel>> getListDepAttendanceByDepartmentIdAndRangeDate(Date dateFrom, Date dateUntill) throws Exception {
		Map<String,List<DepAttendanceRealizationViewModel>> mapResult = new HashMap<String, List<DepAttendanceRealizationViewModel>>();
		
			
		//Dapatkan departemen dan jumlah karyawannya
		 List<Department> departments = departmentDao.getAllData();
	        Map<Department, Long> results = new HashMap<Department, Long>();
	        for (Department department : departments) {
	            Long total = empDataDao.getTotalByDepartmentId(department.getId());
	            results.put(department, total);
	        }
	        
	        //sorting by value (dari yang besar ke yang kecil)
	        results = MapUtil.sortByValueDesc(results);
	        
	        /*
	         * Jika jumlah departemen lebih besar dari 5, maka 5 departemen dengan total karyawan terbesar, 
	         * masing - masing di jadikan satu model sendiri - sendiri, 
	         * sedangkan sisanya di satukan jadi satu model dengan label Departemen lainnya (key resourceBundle : 'department.other_department')
	         */
	        if(results.size() > 5){        	
	        	
	        	List<Department> listOtherDepartment = new ArrayList<Department>();
	        	List<DepAttendanceRealizationViewModel> listOtherDepAttendance = new ArrayList<DepAttendanceRealizationViewModel>();
	        	List<DepAttendanceRealizationViewModel> listOtherDepAttendanceDistinctWeekNumber = new ArrayList<DepAttendanceRealizationViewModel>();
	        	
	        	int i = 1;
	        	for(Map.Entry<Department, Long> entry : results.entrySet()){
	        		if(i <= 5){
	        			List<DepAttendanceRealizationViewModel> listDepAttendance = empDataDao.getListDepAttendanceByDepartmentIdAndRangeDate(entry.getKey().getId(), dateFrom, dateUntill);
		    			mapResult.put(StringsUtils.slicePerWord(entry.getKey().getDepartmentName(), 25), listDepAttendance);	        			
	        		}else{
	        			List<DepAttendanceRealizationViewModel> listDepAttendance = empDataDao.getListDepAttendanceByDepartmentIdAndRangeDate(entry.getKey().getId(), dateFrom, dateUntill);
	        			listOtherDepAttendance.addAll(listDepAttendance);
	        			listOtherDepartment.add(entry.getKey());
	        		}
	        		
	        		i++;	        		
	        	}
	        	
	        	Group<DepAttendanceRealizationViewModel> groupAttendance = Lambda.group(listOtherDepAttendance, Lambda.by(Lambda.on(DepAttendanceRealizationViewModel.class).getWeekNumber()));
	        	for (String key : groupAttendance.keySet()) {
	        		List<DepAttendanceRealizationViewModel> listGroupedAttendance = groupAttendance.find(key);
	        		DepAttendanceRealizationViewModel model = new DepAttendanceRealizationViewModel();
	        		Double totalPercentage = Lambda.sum(listGroupedAttendance, Lambda.on(DepAttendanceRealizationViewModel.class).getAttendancePercentage().doubleValue());
	        		Double percentage = totalPercentage / (listGroupedAttendance.size() - 5);
	        		if(percentage.isNaN() || percentage.isInfinite()){
	        			percentage = 0.0;
	        		}
	        		model.setWeekNumber(Integer.valueOf(key));
	        		model.setAttendancePercentage(new BigDecimal(percentage));
	        		listOtherDepAttendanceDistinctWeekNumber.add(model);	        		
	        		
	        	}
	        	
	        	mapResult.put(ResourceBundleUtil.getAsString("department.other_department"), listOtherDepAttendanceDistinctWeekNumber);
	        	
	        	
	        }else{	        	
	        	for(Map.Entry<Department, Long> entry : results.entrySet()){
	        		List<DepAttendanceRealizationViewModel> listDepAttendance = empDataDao.getListDepAttendanceByDepartmentIdAndRangeDate(entry.getKey().getId(), dateFrom, dateUntill);
	    			mapResult.put(StringsUtils.slicePerWord(entry.getKey().getDepartmentName(), 25), listDepAttendance);
	        	}
	        }
	        
		
		 
		
		return mapResult;
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
    			try {
	    			EmployeeRestModel model = this.bindToRestModel(empData, bioData);
	    			listModel.add(model);
    			} catch (NullPointerException ex){
    				LOGGER.error("Employee Nik : "+ empData.getNikWithFullName() +", Error Null Pointer " + ex);
    			}
    			
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
			model = this.bindToRestModel(empData, bioData);
		}
		return model;
	}
	
	private EmployeeRestModel bindToRestModel(EmpData empData,BioData bioData) throws ParseException{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", new Locale("en"));
		
		EmployeeRestModel model = new EmployeeRestModel();
		model.setNik(empData.getNik());
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
