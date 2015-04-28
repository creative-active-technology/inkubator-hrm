/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.dao.CostCenterDeptDao;
import com.inkubator.hrm.dao.DepartementUnitLocationDao;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.dao.UnregDepartementDao;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.entity.DepartementUnitLocation;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.entity.UnregDepartement;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
import com.inkubator.securitycore.util.UserInfoUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Order;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author deniarianto
 */
@Service(value = "departmentService")
@Lazy
public class DepartmentServiceImpl extends IServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private CostCenterDeptDao costCenterDeptDao;
    @Autowired
    private UnregDepartementDao unregDepartementDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private DepartementUnitLocationDao departementUnitLocationDao;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Department> getByParam(DepartmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception {
        return this.departmentDao.getByParam(searchParameter, firstResult, maxResults, order);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getTotalDepartmentByParam(DepartmentSearchParameter searchParameter) throws Exception {
        return this.departmentDao.getTotalDepartmentByParam(searchParameter);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Long getByDepartmentName(String name) throws Exception {
        return this.departmentDao.getByDepartmentCode(name);
    }

    @Override
    public Department getEntiyByPK(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntiyByPK(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Department getEntiyByPK(Long id) {
        return this.departmentDao.getEntiyByPK(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(Department entity) throws Exception {
        // check duplicate name
        long totalDuplicates = departmentDao.getByDepartmentCode(entity.getDepartmentCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }
        entity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
        CostCenterDept costCenterDept = costCenterDeptDao.getEntiyByPK(entity.getCostCenterDept().getId());
        entity.setCostCenterDept(costCenterDept);
        entity.setCreatedBy(UserInfoUtil.getUserName());
        entity.setCreatedOn(new Date());
        entity.setDescription(entity.getDescription());
        this.departmentDao.save(entity);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(Department entity) throws Exception {
        long totalDuplicates = departmentDao.getTotalByCodeAndNotId(entity.getDepartmentCode(), entity.getId());
        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }

        CostCenterDept costCenterDept = costCenterDeptDao.getEntiyByPK(entity.getCostCenterDept().getId());
        Department departmentUpdate = this.departmentDao.getEntiyByPK(entity.getId());
        departmentUpdate.setDepartmentCode(entity.getDepartmentCode());
        departmentUpdate.setDepartmentName(entity.getDepartmentName());
        departmentUpdate.setCostCenterDept(costCenterDept);
        departmentUpdate.setUpdatedBy(UserInfoUtil.getUserName());
        departmentUpdate.setUpdatedOn(new Date());
        departmentUpdate.setDescription(entity.getDescription());
        this.departmentDao.update(departmentUpdate);
    }

    @Override
    public void saveOrUpdate(Department enntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department saveData(Department entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department updateData(Department entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department saveOrUpdateData(Department entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(String id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(String id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(String id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Integer id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Integer id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Integer id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Long id, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Long id, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getEntityByPkIsActive(Long id, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Department entity) {
        this.departmentDao.delete(entity);
    }

    @Override
    public void softDelete(Department entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getTotalDataIsActive(Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Department> getAllData() {
        return this.departmentDao.getAllData();
    }

    @Override
    public List<Department> getAllData(Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllData(Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllData(Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllDataPageAble(int firstResult, int maxResults, Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Boolean isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Integer isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAllDataPageAbleIsActive(int firstResult, int maxResults, Order order, Byte isActive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Department getEntityByPkWithDetail(Long id) throws Exception {
        return departmentDao.getEntityByPkWithDetail(id);

    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 30)
    public Department getEntityByUnregSalaryIdWithDetail(Long unregSalaryId) throws Exception {
        Department department = new Department();
        List<Department> departments = new ArrayList<>();
        for (UnregDepartement unregDepartement : this.unregDepartementDao.getAllDataByUnregSalaryId(unregSalaryId)) {
            departments.add(unregDepartement.getDepartment());
        }

        department.setListDepartments(departments);
        return department;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Department getByLevelOneAndCompany(String orgLevel, Long companyId) throws Exception {
        Department department = this.departmentDao.getByLevelOneAndCompany(orgLevel, companyId);

        if (department == null) {
            department = new Department();
            department.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            Company company = companyDao.getEntiyByPK(companyId);
            department.setCompany(company);
            department.setDepartmentCode("ROOT-" + company.getId());
            department.setDepartmentName("ROOT-" + company.getId());
            department.setIsActive(Boolean.TRUE);
            department.setOrgLevel("ROOT");
            department.setCreatedBy(UserInfoUtil.getUserName());
            department.setCreatedOn(new Date());
            departmentDao.save(department);
         
            Department d = new Department();
            d.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            d.setCompany(company);
            d.setDepartmentCode(company.getCode() + "-BD");
            d.setDepartmentName("Board of director");
            d.setDepartment(department);
            d.setIsActive(Boolean.TRUE);
            d.setOrgLevel(HRMConstant.ORGANISASI);
            d.setCreatedBy(UserInfoUtil.getUserName());
            d.setCreatedOn(new Date());
            departmentDao.save(d);

        }

        return department;
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public List<Department> listChildGetByParentId(Long parentId) throws Exception {
        return this.departmentDao.listChildGetByParentId(parentId);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.SUPPORTS, timeout = 50)
    public TreeNode cretaeNodeBreakEndPoint(String param) throws Exception {
        TreeNode root;
        Department endPointDepartment = departmentDao.getEntiyByPK(Long.parseLong(param.substring(1)));
     
        List<Department> data = new ArrayList<>();
        data.add(endPointDepartment);
        gerParent(endPointDepartment, data);
        Collections.reverse(data);
        Department master = data.get(0);
        master.getCompany().getName();// digunakan untuk di tampilkan di xhtml dan tidak mengguakan fetch karena cuma perlu nama saja
        data.remove(0);
        root = new DefaultTreeNode(master, null);
        int i = 1;
        TreeNode before = new DefaultTreeNode();
        for (Department data1 : data) {
            if (i == 1) {
                TreeNode node = new DefaultTreeNode(data1, root);
                node.setExpanded(true);
                before = node;
            } else {
                TreeNode node = new DefaultTreeNode(data1, before);
                node.setExpanded(true);
                before = node;
            }
            i++;
        }
        return root;
    }

    private void gerParent(Department department, List<Department> list) {
        Department data = department.getDepartment();
        list.add(data);
        if (data.getDepartment() != null) {
            gerParent(data, list);
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveOrganisasiLevel(Department department) throws Exception {

        long totalDuplicates = departmentDao.getByDepartmentCode(department.getDepartmentCode());
        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }
        Department parent = this.departmentDao.getEntiyByPK(department.getDepartment().getId());
        Company company = this.companyDao.getEntiyByPK(department.getCompany().getId());
        department.setCreatedBy(UserInfoUtil.getUserName());
        department.setDepartment(parent);
        department.setCompany(company);
        department.setCreatedOn(new Date());
        this.departmentDao.save(department);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public Department getDepartementWithUnitKerja(Long departementId) throws Exception {
        Department department = this.departmentDao.getEntiyByPK(departementId);
        List<UnitKerja> dataToShow = new ArrayList<>();
        for (DepartementUnitLocation location : this.departementUnitLocationDao.getByDepartementId(departementId)) {
          location.getUnitKerja().getCity().getCityName();
            dataToShow.add(location.getUnitKerja());
        }
        department.setListUnit(dataToShow);
        department.getCompany().getCode();
        department.getCompany().getName();
        department.getDepartment().getOrgLevel();
        return department;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 30)
    public void updateOrganisasiLevel(Department department) throws Exception {
        long totalDuplicates = departmentDao.getTotalByCodeAndNotId(department.getDepartmentCode(), department.getId());
    
        if (totalDuplicates > 0) {
            throw new BussinessException("department.error_duplicate_department_name");
        }

        Department dep = this.departmentDao.getEntiyByPK(department.getId());
        dep.getDepartementUnitLocations().clear();
        dep.setCompany(this.companyDao.getEntiyByPK(department.getCompany().getId()));
        dep.setDepartment(this.departmentDao.getEntiyByPK(department.getDepartment().getId()));
        dep.setDepartmentCode(department.getDepartmentCode());
        dep.setDepartmentName(department.getDepartmentName());
        dep.setDescription(department.getDescription());
        dep.setIsActive(department.getIsActive());
        dep.setIsNeckHierarki(department.getIsNeckHierarki());
        dep.setOrgLevel(department.getOrgLevel());
        dep.setUpdatedBy(UserInfoUtil.getUserName());
        dep.setUpdatedOn(new Date());
        this.departmentDao.saveAndMerge(dep);
        Set<DepartementUnitLocation> dataToSave = department.getDepartementUnitLocations();
        for (DepartementUnitLocation depLoc : dataToSave) {
            depLoc.setDepartment(dep);
            this.departementUnitLocationDao.save(depLoc);
        }

    }

}
