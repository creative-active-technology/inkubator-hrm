/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.dao.PaySalaryGradeDao;
import com.inkubator.hrm.dao.WtGroupWorkingDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        empCareerHistoryDao.save(careerHistory);

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(EmpData entity) throws Exception {
        long totalDuplicates = empDataDao.getTotalByNIKandId(entity.getNik(), entity.getId());
        System.out.println(" nilai total " + totalDuplicates);
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
        System.out.println(" niaiaiai " + empData.getGolonganJabatan().getId());
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

        return results;
    }

//>>>>>>> origin/master
//    @Override
//    @Cacheable(value = "totalEmployeeByGender")
//    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
//    public Map<String, Long> getTotalByGender() throws Exception {
//        Long male = empDataDao.getTotalByGender(HRMConstant.GLOBAL_MALE);
//        Long female = empDataDao.getTotalByGender(HRMConstant.GLOBAL_FEMALE);
//
//        Map<String, Long> results = new HashMap<String, Long>();
//        results.put("male", male);
//        results.put("female", female);
//
//        return results;
//    }
//    @Override
//    @Cacheable(value = "totalEmployeeByAge")
//    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
//    public Map<String, Long> getTotalByAge() throws Exception {
//        Date now = new Date();
//        Date startDate = new Date();
//        Date endDate = new Date();
//
//        //age less than 26
//        startDate = DateTimeUtil.getDateFrom(now, -26, CommonUtilConstant.DATE_FORMAT_YEAR);
//        Long lessThan26 = empDataDao.getTotalByAgeLessThan(startDate);
//
//        //age between 26 - 30
//        startDate = DateTimeUtil.getDateFrom(now, -30, CommonUtilConstant.DATE_FORMAT_YEAR);
//        endDate = DateTimeUtil.getDateFrom(now, -26, CommonUtilConstant.DATE_FORMAT_YEAR);
//        Long between26And30 = empDataDao.getTotalByAgeBetween(startDate, endDate);
//
//        //age between 31 - 35
//        startDate = DateTimeUtil.getDateFrom(now, -35, CommonUtilConstant.DATE_FORMAT_YEAR);
//        endDate = DateTimeUtil.getDateFrom(now, -31, CommonUtilConstant.DATE_FORMAT_YEAR);
//        Long between31And35 = empDataDao.getTotalByAgeBetween(startDate, endDate);
//
//        //age between 36 - 40
//        startDate = DateTimeUtil.getDateFrom(now, -40, CommonUtilConstant.DATE_FORMAT_YEAR);
//        endDate = DateTimeUtil.getDateFrom(now, -36, CommonUtilConstant.DATE_FORMAT_YEAR);
//        Long between36And40 = empDataDao.getTotalByAgeBetween(startDate, endDate);
//
//        //age more than 40
//        startDate = DateTimeUtil.getDateFrom(now, -40, CommonUtilConstant.DATE_FORMAT_YEAR);
//        Long moreThan40 = empDataDao.getTotalByAgeMoreThan(startDate);
//
//        Map<String, Long> results = new HashMap<String, Long>();
//        results.put("lessThan26", lessThan26);
//        results.put("between26And30", between26And30);
//        results.put("between31And35", between31And35);
//        results.put("between36And40", between36And40);
//        results.put("moreThan40", moreThan40);
//
//        return results;
//    }
//    @Override
//    @Cacheable(value = "totalEmployeeByDepartment")
//    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
//    public Map<String, Long> getTotalByDepartment() throws Exception {
//        List<Department> departments = departmentDao.getAllData();
//        Map<String, Long> results = new HashMap<String, Long>();
//        for (Department department : departments) {
//            Long total = empDataDao.getTotalByDepartmentId(department.getId());
//            results.put(StringsUtils.slicePerWord(department.getDepartmentName(), 25), total);
//        }
//        //sorting by value (dari yang besar ke yang kecil)
//        results = MapUtil.sortByValueDesc(results);
//
//        return results;
//    }
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
        long totalDuplicates = empDataDao.getTotalByNIKandId(entity.getNik(), entity.getId());
        System.out.println(" nilai total " + totalDuplicates);
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
        System.out.println(" niaiaiai " + empData.getGolonganJabatan().getId());
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
        empCareerHistoryDao.save(careerHistory);
    }

}
