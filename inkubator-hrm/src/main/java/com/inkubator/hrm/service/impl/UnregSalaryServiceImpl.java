/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.EmployeeTypeDao;
import com.inkubator.hrm.dao.GolonganJabatanDao;
import com.inkubator.hrm.dao.ReligionDao;
import com.inkubator.hrm.dao.UnregDepartementDao;
import com.inkubator.hrm.dao.UnregEmpReligionDao;
import com.inkubator.hrm.dao.UnregEmpTypeDao;
import com.inkubator.hrm.dao.UnregGoljabDao;
import com.inkubator.hrm.dao.UnregSalaryDao;
import com.inkubator.hrm.dao.WtPeriodeDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.entity.UnregDepartement;
import com.inkubator.hrm.entity.UnregDepartementId;
import com.inkubator.hrm.entity.UnregEmpReligion;
import com.inkubator.hrm.entity.UnregEmpReligionId;
import com.inkubator.hrm.entity.UnregEmpType;
import com.inkubator.hrm.entity.UnregEmpTypeId;
import com.inkubator.hrm.entity.UnregGoljab;
import com.inkubator.hrm.entity.UnregGoljabId;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.hrm.web.model.UnregSalaryViewModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deni
 */
@Service(value = "unregSalaryService")
@Lazy
public class UnregSalaryServiceImpl extends IServiceImpl implements UnregSalaryService {

    @Autowired
    private UnregSalaryDao unregSalaryDao;
    @Autowired
    private WtPeriodeDao wtPeriodeDao;
    @Autowired
    private GolonganJabatanDao golonganJabatanDao;
    @Autowired
    private UnregGoljabDao unregGoljabDao;
    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private UnregDepartementDao unregDepartementDao;
    @Autowired
    private ReligionDao religionDao;
    @Autowired
    private UnregEmpReligionDao unregEmpReligionDao;
    @Autowired
    private UnregEmpTypeDao unregEmpTypeDao;
    @Autowired
    private EmployeeTypeDao employeeTypeDao;
    

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<UnregSalary> getByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return unregSalaryDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParam(UnregSalarySearchParameter searchParameter) throws Exception {
        return unregSalaryDao.getTotalByParam(searchParameter);
    }

    @Override
    public UnregSalary getEntiyByPK(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntiyByPK(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public UnregSalary getEntiyByPK(Long id) throws Exception {
        return unregSalaryDao.getEntiyByPK(id);
    }

    @Override
    public void save(UnregSalary entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UnregSalary entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrUpdate(UnregSalary enntity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary saveData(UnregSalary entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary updateData(UnregSalary entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary saveOrUpdateData(UnregSalary entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(String id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(String id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(String id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Integer id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Integer id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Integer id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Long id, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Long id, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UnregSalary getEntityByPkIsActive(Long id, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(UnregSalary entity) throws Exception {
        this.unregSalaryDao.delete(entity);
    }

    @Override
    public void softDelete(UnregSalary entity) throws Exception {
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
    public List<UnregSalary> getAllData() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllData(Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllData(Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllData(Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllDataPageAble(int firstResult, int maxResults, Order order) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UnregSalary> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saved(UnregSalaryModel model) throws Exception {
        // check duplicate code
        long totalDuplicates = unregSalaryDao.getTotalByCode(model.getCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("unregSalary.error_duplicate_salary_code");
        }
        
        WtPeriode wtPeriode = wtPeriodeDao.getEntityByMonthAndYear(String.valueOf(model.getMonth()), model.getYear());
        UnregSalary entity = new UnregSalary();
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        entity.setCode(model.getCode());
        entity.setName(model.getName());
        if(wtPeriode != null){
            entity.setWtPeriode(wtPeriode);
        }else{
            throw new BussinessException("unregSalary.basePeriode_not_available");
        }
        entity.setSalaryDate(model.getSalaryDate());
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        this.unregSalaryDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updated(UnregSalaryModel model) throws Exception {
        // check duplicate code
        long totalDuplicates = unregSalaryDao.getTotalByCodeAndNotId(model.getCode(), model.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("unregSalary.error_duplicate_salary_code");
        }
        
        WtPeriode wtPeriode = wtPeriodeDao.getEntityByMonthAndYear(String.valueOf(model.getMonth()), model.getYear());
        UnregSalary update = unregSalaryDao.getEntiyByPK(model.getId());
        update.setCode(model.getCode());
        update.setName(model.getName());
        if(wtPeriode != null){
            update.setWtPeriode(wtPeriode);
        }else{
            throw new BussinessException("unregSalary.basePeriode_not_available");
        }
        update.setSalaryDate(model.getSalaryDate());
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.unregSalaryDao.update(update);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public UnregSalary getEntityByPkWithDetail(Long id) throws Exception {
        return unregSalaryDao.getEntityByPkWithDetail(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Long unregSalaryId, Date startDate, Date endDate, List<GolonganJabatan> golonganJabatans, List<Department> departments, List<Religion> religions, List<EmployeeType> employeeTypes) throws Exception {
       unregSalaryDao.deleteAllDataByUnregSalaryId(unregSalaryId);
        //update unreg salary startDate period and endDate period
        UnregSalary update = unregSalaryDao.getEntiyByPK(unregSalaryId);
        update.setStartPeriodDate(startDate);
        update.setEndPeriodDate(endDate);
        update.setUpdatedBy(UserInfoUtil.getUserName());
        update.setUpdatedOn(new Date());
        this.unregSalaryDao.update(update);
        
        //save list golongan jabatan
        for (GolonganJabatan golonganJabatan : golonganJabatans) {
            UnregGoljab unregGoljab = new UnregGoljab();
            unregGoljab.setId(new UnregGoljabId(unregSalaryId, golonganJabatan.getId()));
            unregGoljab.setUnregSalary(unregSalaryDao.getEntiyByPK(unregSalaryId));
            unregGoljab.setGolonganJabatan(golonganJabatanDao.getEntiyByPK(golonganJabatan.getId()));
            unregGoljab.setCreatedBy(UserInfoUtil.getUserName());
            unregGoljab.setCreatedOn(new Date());
            unregGoljabDao.save(unregGoljab);
        }
        
        //save list departement
        for (Department department : departments) {
            UnregDepartement unregDepartement = new UnregDepartement();
            unregDepartement.setId(new UnregDepartementId(unregSalaryId, department.getId()));
            unregDepartement.setUnregSalary(unregSalaryDao.getEntiyByPK(unregSalaryId));
            unregDepartement.setDepartment(departmentDao.getEntiyByPK(department.getId()));
            unregDepartement.setCreatedBy(UserInfoUtil.getUserName());
            unregDepartement.setCreatedOn(new Date());
            unregDepartementDao.save(unregDepartement);
        }
        
        //save list religion
        for (Religion religion : religions) {
            UnregEmpReligion unregEmpReligion = new UnregEmpReligion();
            unregEmpReligion.setId(new UnregEmpReligionId(unregSalaryId, religion.getId()));
            unregEmpReligion.setUnregSalary(unregSalaryDao.getEntiyByPK(unregSalaryId));
            unregEmpReligion.setReligion(religionDao.getEntiyByPK(religion.getId()));
            unregEmpReligion.setCreatedBy(UserInfoUtil.getUserName());
            unregEmpReligion.setCreatedOn(new Date());
            unregEmpReligionDao.save(unregEmpReligion);
        }
        
        //save list employee type
        for (EmployeeType employeeType : employeeTypes) {
            UnregEmpType unregEmpType = new UnregEmpType();
            unregEmpType.setId(new UnregEmpTypeId(unregSalaryId, employeeType.getId()));
            unregEmpType.setUnregSalary(unregSalaryDao.getEntiyByPK(unregSalaryId));
            unregEmpType.setEmployeeType(employeeTypeDao.getEntiyByPK(employeeType.getId()));
            unregEmpType.setCreatedBy(UserInfoUtil.getUserName());
            unregEmpType.setCreatedOn(new Date());
            unregEmpTypeDao.save(unregEmpType);
        }
        System.out.println("haha");
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<UnregSalaryViewModel> getByParamWithViewModel(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return unregSalaryDao.getByParamWithViewModel(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalByParamViewModel(UnregSalarySearchParameter searchParameter) throws Exception {
        return unregSalaryDao.getTotalByParamViewModel(searchParameter);
    }

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
	public List<UnregSalary> getAllDataComponentByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
		List<UnregSalary> list = unregSalaryDao.getByParam(searchParameter, firstResult, maxResults, order);
		for(UnregSalary unregSalary : list){
			unregSalary.setTotalUnregPayComponents(unregSalary.getUnregPayComponentses().size());
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
	public Long getTotalComponentByParam(UnregSalarySearchParameter searchParameter) throws Exception {
		return unregSalaryDao.getTotalByParam(searchParameter);
		
	}

}
