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
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
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
        return this.departmentDao.getAllWithSpecificCompany();
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

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, timeout = 30)
    public DefaultDiagramModel createDiagramModel(long companyId) throws Exception {
        DefaultDiagramModel ddm = new DefaultDiagramModel();
        Department department = this.departmentDao.getByLevelOneAndCompany("ORG", companyId);
        List<Department> depLevelDep = this.departmentDao.getByOrgLevelAndCompany("DEP", companyId);
//        List<Department> depLevelDeV = new ArrayList<>();
//        List<Element> data1 = new ArrayList<>();

        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");

        int jarak = 0;
        int x = 40;
        int y = 5;
        int jarak1 = 0;
        int jarak2 = 0;
        Element master = new Element();
        master.setData(department.getDepartmentName());
        master.setX(x + "em");
        master.setY(y + "em");

        master.setDraggable(true);
        master.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        ddm.addElement(master);
        for (Department dep : depLevelDep) {

//            depLevelDeV.addAll(dep.getDepartments());
            Element child = new Element(dep.getDepartmentName(), (jarak + 10) + "em", (y + 10) + "em");
            child.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
            child.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
            jarak = jarak + dep.getDepartmentName().length() / 2 + 7;
            ddm.addElement(child);
            ddm.connect(new Connection(master.getEndPoints().get(0), child.getEndPoints().get(0), connector));
//            data1.add(child);

            for (Department depLevelDeV1 : dep.getDepartments()) {
                Element chElement = new Element(depLevelDeV1.getDepartmentName(), (jarak1 + 2) + "em", (y + 25) + "em");
                jarak1 = jarak1 + (depLevelDeV1.getDepartmentName().length() / 2) + 2;
                System.out.println(" jarakkna " + jarak1);
                chElement.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                  chElement.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
                ddm.addElement(chElement);
                if (depLevelDeV1.getDepartment() == dep) {
                    ddm.connect(new Connection(child.getEndPoints().get(1), chElement.getEndPoints().get(0), connector));
                }
                for (Department depLevelDeV2 : depLevelDeV1.getDepartments()) {
                    Element chElement2 = new Element(depLevelDeV2.getDepartmentName(), (jarak2 + 2) + "em", (y + 35) + "em");
                    jarak2 = jarak2 + (depLevelDeV2.getDepartmentName().length() / 2) + 2;
                    System.out.println(" jarakkna " + jarak1);
                    chElement.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                    ddm.addElement(chElement);
                    if (depLevelDeV2.getDepartment() == dep) {
                        ddm.connect(new Connection(chElement.getEndPoints().get(1), chElement2.getEndPoints().get(0), connector));
                    }
                }
            }
//            depLevelDeV = new ArrayList<>();
        }
//        jarak = 0;
//        for (Department depLevelDeV1 : depLevelDeV) {
//            Element chElement = new Element(depLevelDeV1.getDepartmentName(), (jarak + 7) + "em", (y + 25) + "em");
//            jarak = jarak + depLevelDeV1.getDepartmentName().length() / 2 + 7;
//            chElement.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
//            ddm.addElement(chElement);
//            ddm.connect(new Connection(data1.get(0).getEndPoints().get(1), chElement.getEndPoints().get(0), connector));
//        }
//        for (Element data11 : data1) {
//            ddm.connect(new Connection(master.getEndPoints().get(0), data11.getEndPoints().get(0), connector));
//        }
//        master.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
        return ddm;
    }

    private DefaultDiagramModel doCreate(Department department, DefaultDiagramModel diagramModel, Element element, int jarak) {
//        System.out.println(" kepanggil beeberapa akalai " + department.getDepartmentName());
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        List<Department> data = new ArrayList<>(department.getDepartments());
        diagramModel.setMaxConnections(-1);
        int x = 40;
        int y = 6;

//        System.out.println(" tekeksusus");
        Element child = null;
        for (Department data1 : data) {
//            System.out.println(" nili organisan leel ua " + data1.getOrgLevel());
            if (data1.getOrgLevel().equals("DEP")) {

                child = new Element(data1.getDepartmentName(), (jarak + 10) + "em", (y + 10) + "em");
                jarak = jarak + data1.getDepartmentName().length() / 2 + 10;
            }
            if (data1.getOrgLevel().equals("DEV")) {
                System.out.println(" Jaral unutk dev  " + jarak + 5);
                child = new Element(data1.getDepartmentName(), (jarak + 5) + "em", (y + 20) + "em");
                jarak = jarak + (data1.getDepartmentName().length()) + 10;
            }
//            if (data1.getOrgLevel().equals("DIR")) {
//                child = new Element(data1.getDepartmentName(), (jarak + 10) + "em", (y + 30) + "em");
//                jarak = jarak + data1.getDepartmentName().length() / 2 + 20;
//            }
//            if (data1.getOrgLevel().equals("SKR")) {
//                child = new Element(data1.getDepartmentName(), (jarak + 10) + "em", (y + 40) + "em");
//                jarak = jarak + data1.getDepartmentName().length() / 2 + 25;
//            }

            child.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
            child.addEndPoint(new DotEndPoint(EndPointAnchor.BOTTOM));
//            System.out.println(" jarakanya " + (jarak));

            diagramModel.addElement(child);
//            diagramModel.connect(new Connection(element.getEndPoints().get(0), child.getEndPoints().get(0), connector));
            if (data1.getDepartments() != null) {

                doCreate(data1, diagramModel, child, jarak);
            }
        }
//        for (Department data1 : data) {
//            if (data1.getDepartments() != null) {
//                jarak = 0;
//                doCreate(data1, diagramModel, child, jarak);
//            }
//        }

        return diagramModel;
    }

}
