/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "departmentHirarkiViewController")
@ViewScoped
public class DepartmentHirarkiViewController extends BaseController {

    private TreeNode root;
    private TreeNode selectedNode;
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    private Long selectedOrganisasi;
    private Map<String, Long> companys = new TreeMap<>();
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    private String companyName;

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
//        selectedOrganisasi = Long.parseLong("488477874");
        super.initialization();
        companyName = " - ";
        root = new DefaultTreeNode();
        try {
            List<Company> listCompany = companyService.getAllData();
            for (Company cop : listCompany) {
                companys.put(cop.getName(), cop.getId());
            }
//            Department department = departmentService.getByLevelOneAndCompany("ROOT", selectedOrganisasi);
//            root = createNodes(department, null);
//            root.setExpanded(false);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }

    private TreeNode createNodes(Department department, TreeNode root1) throws Exception {

        if (root1 == null) {
            root = new DefaultTreeNode(department, null);
            List<Department> data1 = departmentService.listChildGetByParentId(department.getId());
            for (Department dep : data1) {
                TreeNode node = new DefaultTreeNode(dep, root);
                node.setExpanded(true);
                if (!dep.getDepartments().isEmpty()) {
                    createNodes(dep, node);
                }
            }
            System.out.println(" heere master");
        } else {
            List<Department> data1 = departmentService.listChildGetByParentId(department.getId());
            for (Department dep2 : data1) {
                TreeNode node = new DefaultTreeNode(dep2, root1);
                node.setExpanded(false);
                if (!dep2.getDepartments().isEmpty()) {
                    createNodes(dep2, node);
                }
            }
            System.out.println(" heere anak");
        }
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Map<String, Long> getCompanys() {
        return companys;
    }

    public void setCompanys(Map<String, Long> companys) {
        this.companys = companys;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Long getSelectedOrganisasi() {
        return selectedOrganisasi;
    }

    public void setSelectedOrganisasi(Long selectedOrganisasi) {
        this.selectedOrganisasi = selectedOrganisasi;
    }

    public void doChangeCompany() throws Exception {

        Department department = departmentService.getByLevelOneAndCompany("ROOT", selectedOrganisasi);
        if (department != null) {
            root = createNodes(department, null);
            root.setExpanded(true);
            companyName = department.getCompany().getName();
        } else {
            root = new DefaultTreeNode();
            companyName = " - ";
        }

    }

    public String doAdd() {
        System.out.println(" rediererer lho");
        Department department = (Department) selectedNode.getData();

        return "/protected/organisation/organiztion_level_form.htm?faces-redirect=true&execution=a" + department.getId();
    }

    public String doEdit() {
        System.out.println(" rediererer lho");
        Department department = (Department) selectedNode.getData();

        return "/protected/organisation/organiztion_level_form.htm?faces-redirect=true&execution=e" + department.getId();
    }

    public String doDetil() {
        System.out.println(" rediererer lho");
        Department department = (Department) selectedNode.getData();

        return "/protected/organisation/organiztion_level_form.htm?faces-redirect=true&execution=d" + department.getId();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
