/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "departmentHirarkiDetailController")
@ViewScoped
public class DepartmentHirarkiDetailController extends BaseController {

    private TreeNode root;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private String companyName;
    private Department department;
    List<UnitKerja> listUnitKerja;

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
            if (param.contains("d")) {
                department = this.departmentService.getDepartementWithUnitKerja((Long.parseLong(param.substring(1))));
                listUnitKerja = department.getListUnit();
                String paraMinusOneParent = "e" + department.getDepartment().getId();
                if (!department.getDepartment().getOrgLevel().equals("ROOT")) {
                    root = departmentService.cretaeNodeBreakEndPoint(paraMinusOneParent);
                } else {
                    root = new DefaultTreeNode();
                }
                companyName = department.getCompany().getName();
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

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public String doBack() {
        return "/protected/organisation/organiztion_level.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/organisation/organiztion_level_form.htm?faces-redirect=true&execution=e" + department.getId();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<UnitKerja> getListUnitKerja() {
        return listUnitKerja;
    }

    public void setListUnitKerja(List<UnitKerja> listUnitKerja) {
        this.listUnitKerja = listUnitKerja;
    }

}
