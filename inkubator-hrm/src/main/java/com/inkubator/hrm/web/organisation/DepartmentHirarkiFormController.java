/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.model.OrganisasiLevelModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "departmentHirarkiFormController")
@ViewScoped
public class DepartmentHirarkiFormController extends BaseController {

    private TreeNode root;

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
                root = departmentService.cretaeNodeBreakEndPoint(param);
                Department department = (Department) root.getData();
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

}
