/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.DepartementUnitLocation;
import com.inkubator.hrm.entity.DepartementUnitLocationId;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.model.OrganisasiLevelModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "departmentHirarkiFormController")
@ViewScoped
public class DepartmentHirarkiFormController extends BaseController {
    
    private TreeNode root;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private String companyName;
    private OrganisasiLevelModel organisasiLevelModel;
    
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            if (param.contains("a")) {
                organisasiLevelModel = new OrganisasiLevelModel();
                List<UnitKerja> sourceData = unitKerjaService.getAllData();
                DualListModel<UnitKerja> dualModel = new DualListModel<>();
                dualModel.setSource(sourceData);
                organisasiLevelModel.setDualListModel(dualModel);
                organisasiLevelModel.setParentId(Long.parseLong(param.substring(1)));
                root = departmentService.cretaeNodeBreakEndPoint(param);
                Department department = (Department) root.getData();
                companyName = department.getCompany().getName();
                organisasiLevelModel.setCompanyCode(department.getCompany().getCode());
            }
            
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public OrganisasiLevelModel getOrganisasiLevelModel() {
        return organisasiLevelModel;
    }
    
    public void setOrganisasiLevelModel(OrganisasiLevelModel organisasiLevelModel) {
        this.organisasiLevelModel = organisasiLevelModel;
    }
    
    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }
    
    public String doSave() {
        try {
            Set<DepartementUnitLocation> departementUnitLocations = new HashSet<>(0);
            List<UnitKerja> listUnitkerja = organisasiLevelModel.getDualListModel().getTarget();
            
            Department department = getEntityFromViewModel(organisasiLevelModel);
            department.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
            for (UnitKerja unitKerja : listUnitkerja) {
                DepartementUnitLocation dul = new DepartementUnitLocation();
                dul.setId(new DepartementUnitLocationId(department.getId(), unitKerja.getId()));
                dul.setDepartment(department);
                dul.setUnitKerja(unitKerja);
                departementUnitLocations.add(dul);
            }
            department.setDepartementUnitLocations(departementUnitLocations);
            departmentService.saveOrganisasiLevel(department);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return "/protected/organisation/organiztion_level.htm?faces-redirect=true";
    }
    
    private Department getEntityFromViewModel(OrganisasiLevelModel levelModel) throws Exception {
        Department department = new Department();
        if (levelModel.getDepartemetId() != null) {
            department.setId(levelModel.getDepartemetId());
        }
        department.setDepartmentCode(levelModel.getCode());
        department.setCompany(new Company(levelModel.getCompanyId()));
        department.setDepartment(new Department(levelModel.getParentId()));
        department.setDepartmentName(levelModel.getName());
        department.setIsNeckHierarki(levelModel.getIsNextHirarki());
        department.setIsActive(levelModel.getIsActive());
        department.setDescription(levelModel.getDescription());
        department.setOrgLevel(levelModel.getOrgLevel());
        return department;
    }
}
