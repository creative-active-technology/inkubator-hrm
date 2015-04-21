/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import com.inkubator.hrm.service.RecruitSelectionTypeTemplatesService;
import com.inkubator.webcore.controller.BaseController;
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
@ManagedBean(name = "recruitSelectionTypeTemplatesViewController")
@ViewScoped
public class RecruitSelectionTypeTemplatesViewController extends BaseController {
    
    private TreeNode root;
    private TreeNode selectedNode;
    @ManagedProperty(value = "#{recruitSelectionTypeTemplatesService}")
    private RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService;
    
    public TreeNode getRoot() {
        return root;
    }
    
    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        root = new DefaultTreeNode();
        try {
            
            RecruitSelectionTypeTemplates data = recruitSelectionTypeTemplatesService.getLevelOne("ROOT");
            System.out.println(" sfsdfsdfdsf "+data);
            root = createNodes(data, null);
            root.setExpanded(false);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        
    }
    
    private TreeNode createNodes(RecruitSelectionTypeTemplates data, TreeNode root1) throws Exception {
        
        if (root1 == null) {
            root = new DefaultTreeNode(data, null);
            List<RecruitSelectionTypeTemplates> data1 = recruitSelectionTypeTemplatesService.getByParentId(data.getId());
            for (RecruitSelectionTypeTemplates dep : data1) {
                TreeNode node = new DefaultTreeNode(dep, root);
                node.setExpanded(true);
                if (!dep.getRecruitSelectionTypeTemplateses().isEmpty()) {
                    createNodes(dep, node);
                }
            }
            
        } else {
             List<RecruitSelectionTypeTemplates> data1 = recruitSelectionTypeTemplatesService.getByParentId(data.getId());
            for (RecruitSelectionTypeTemplates dep2 : data1) {
                TreeNode node = new DefaultTreeNode(dep2, root1);
                node.setExpanded(false);
                if (!dep2.getRecruitSelectionTypeTemplateses().isEmpty()) {
                    createNodes(dep2, node);
                }
            }
            
        }
        return root;
    }
    
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
    
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public String doAdd() {
        RecruitSelectionTypeTemplates department = (RecruitSelectionTypeTemplates) selectedNode.getData();
        return "/protected/recruitment/selection_typel_form.htm?faces-redirect=true&execution=a" + department.getId();
    }
    
    public String doEdit() {
        RecruitSelectionTypeTemplates department = (RecruitSelectionTypeTemplates) selectedNode.getData();
        return "/protected/recruitment/selection_typel_form.htm?faces-redirect=true&execution=e" + department.getId();
    }
    
    public String doDetil() {
        RecruitSelectionTypeTemplates department = (RecruitSelectionTypeTemplates) selectedNode.getData();
        return "/protected/recruitment/organiztion_level_detail.htm?faces-redirect=true&execution=d" + department.getId();
    }
    
    public void setRecruitSelectionTypeTemplatesService(RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService) {
        this.recruitSelectionTypeTemplatesService = recruitSelectionTypeTemplatesService;
    }
    
}
