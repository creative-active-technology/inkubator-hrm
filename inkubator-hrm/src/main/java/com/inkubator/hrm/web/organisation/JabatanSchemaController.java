/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanSchemaController")
@ViewScoped
public class JabatanSchemaController extends BaseController {
    
    private TreeNode root;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private TreeNode selectedNode;
    private List<EmpData> listEmp = new ArrayList<>();
    private String namaJabatan;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        
        try {
            Jabatan jabatan = jabatanService.getJabatanByLevelOne(Integer.parseInt("1"));
            doViewSchema(jabatan, null);
            root.setExpanded(true);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private TreeNode doViewSchema(Jabatan jabatan, TreeNode root1) throws Exception {
        
        if (root1 == null) {
            
            root = new DefaultTreeNode(jabatan, null);
            List<Jabatan> data1 = jabatanService.getJabatanByParentCode(jabatan.getCode());
            for (Jabatan jabatan1 : data1) {
                TreeNode node = new DefaultTreeNode(jabatan1, root);
                node.setExpanded(true);
                if (!jabatan1.getJabatans().isEmpty()) {
                    doViewSchema(jabatan1, node);
                }
            }
        } else {
            
            List<Jabatan> data1 = jabatanService.getJabatanByParentCode(jabatan.getCode());
            for (Jabatan jabatan1 : data1) {
                TreeNode node = new DefaultTreeNode(jabatan1, root1);
                node.setExpanded(true);
                if (!jabatan1.getJabatans().isEmpty()) {
                    doViewSchema(jabatan1, node);
                }
            }
        }
        return root;
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public String doBack() {
        return "/protected/organisation/job_title_view.htm?faces-redirect=true";
    }
    
    @PreDestroy
    public void cleanAndExit() {
        root = null;
        jabatanService = null;
    }
    
    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }
    
    public void onNodeSelect(NodeSelectEvent event) {
        try {
            Jabatan jabatan = (Jabatan) selectedNode.getData();
            listEmp = empDataService.getAllByJabatanAndCompanyAndStatus(jabatan.getId(), HRMConstant.EMP_TERMINATION);
            namaJabatan=jabatan.getName();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        
    }
    
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
    
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public List<EmpData> getListEmp() {
        return listEmp;
    }

    public void setListEmp(List<EmpData> listEmp) {
        this.listEmp = listEmp;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }
    
}
