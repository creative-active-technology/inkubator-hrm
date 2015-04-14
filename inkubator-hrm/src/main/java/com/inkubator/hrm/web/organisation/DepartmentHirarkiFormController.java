/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.DepartementUnitLocation;
import com.inkubator.hrm.entity.DepartementUnitLocationId;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.hrm.web.model.OrganisasiLevelModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultTreeNode;
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
    private Boolean isEdit;

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
                organisasiLevelModel.setCompanyId(department.getCompany().getId());
                companyName = department.getCompany().getName();
                organisasiLevelModel.setCompanyCode(department.getCompany().getCode());
                isEdit = Boolean.FALSE;
            }

            if (param.contains("e")) {
                organisasiLevelModel = new OrganisasiLevelModel();
                Department department = this.departmentService.getDepartementWithUnitKerja((Long.parseLong(param.substring(1))));

                List<UnitKerja> sumberData = unitKerjaService.getAllData();
                List<UnitKerja> targetData = department.getListUnit();
                sumberData.removeAll(targetData);
                DualListModel<UnitKerja> dualModel = new DualListModel<>(sumberData, targetData);
                organisasiLevelModel.setDualListModel(dualModel);
                String paraMinusOneParent = "e" + department.getDepartment().getId();
                if (!department.getDepartment().getOrgLevel().equals("ROOT")) {
                    root = departmentService.cretaeNodeBreakEndPoint(paraMinusOneParent);
                } else {
                    root = new DefaultTreeNode();
                }
                organisasiLevelModel.setCompanyId(department.getCompany().getId());
                companyName = department.getCompany().getName();
                organisasiLevelModel.setCompanyCode(department.getCompany().getCode());

                String depCode = department.getDepartmentCode();
                String codeToInsert = StringsUtils.substringAfter(depCode, "-");
                organisasiLevelModel.setCode(codeToInsert);
                organisasiLevelModel.setCompanyId(department.getCompany().getId());
                organisasiLevelModel.setDepartemetId(department.getId());
                organisasiLevelModel.setDescription(department.getDescription());
                organisasiLevelModel.setIsActive(department.getIsActive());
                organisasiLevelModel.setIsNextHirarki(department.getIsNeckHierarki());
                organisasiLevelModel.setName(department.getDepartmentName());
                organisasiLevelModel.setOrgLevel(department.getOrgLevel());
                organisasiLevelModel.setParentId(department.getDepartment().getId());
                isEdit = Boolean.TRUE;

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
        String redirect = null;
        if (isEdit) {
            try {
                Set<DepartementUnitLocation> departementUnitLocations = new HashSet<>(0);
                List<UnitKerja> listUnitkerja = organisasiLevelModel.getDualListModel().getTarget();

                Department department = getEntityFromViewModel(organisasiLevelModel);
//                department.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                for (UnitKerja unitKerja : listUnitkerja) {
                    DepartementUnitLocation dul = new DepartementUnitLocation();
                    dul.setId(new DepartementUnitLocationId(department.getId(), unitKerja.getId()));
                    dul.setDepartment(department);
                    dul.setUnitKerja(unitKerja);
                    departementUnitLocations.add(dul);
                }
                department.setDepartementUnitLocations(departementUnitLocations);
                departmentService.updateOrganisasiLevel(department);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                redirect = "/protected/organisation/organiztion_level.htm?faces-redirect=true";
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        } else {
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
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                redirect = "/protected/organisation/organiztion_level.htm?faces-redirect=true";
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }

        return redirect;
    }

    private Department getEntityFromViewModel(OrganisasiLevelModel levelModel) throws Exception {
        Department department = new Department();
        if (levelModel.getDepartemetId() != null) {
            department.setId(levelModel.getDepartemetId());

        }
        department.setDepartmentCode(levelModel.getCompanyCode() + "-" + levelModel.getCode());
        department.setCompany(new Company(levelModel.getCompanyId()));
        department.setDepartment(new Department(levelModel.getParentId()));
        department.setDepartmentName(levelModel.getName());
        department.setIsNeckHierarki(levelModel.getIsNextHirarki());
        department.setIsActive(levelModel.getIsActive());
        department.setDescription(levelModel.getDescription());
        department.setOrgLevel(levelModel.getOrgLevel());
        return department;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

}
