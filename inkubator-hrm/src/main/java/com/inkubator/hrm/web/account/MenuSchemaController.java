/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.webcore.controller.BaseController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "menuSchemaController")
@RequestScoped
public class MenuSchemaController extends BaseController {

    private TreeNode root;
    @ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            root = new DefaultTreeNode(new HrmMenu(), null);
            root.setExpanded(true);
            this.createNodes(null, null);
        } catch (Exception ex) {
           LOGGER.error(ex, ex);
        }
    }

  

    public TreeNode getRoot() {
        return root;
    }

    public String doBack() {
        return "/protected/account/menu_view.htm?faces-redirect=true";
    }

    @PreDestroy
    public void cleanAndExit() {
        root = null;
        hrmMenuService = null;
    }

    public void setHrmMenuService(HrmMenuService hrmMenuService) {
        this.hrmMenuService = hrmMenuService;
    }

    private void createNodes(Long parentId, TreeNode parentNode) throws Exception {
        if (parentNode == null) {
            List<HrmMenu> menus = hrmMenuService.getAllDataFetchChildByLevel(1);
            for (HrmMenu m : menus) {
//                boolean isSelected = selectedMenuIds.contains(m.getId());
                TreeNode node = new DefaultTreeNode(m, root);
                node.setExpanded(Boolean.TRUE);
//                node.setSelected(isSelected);
                if (!m.getHrmMenus().isEmpty()) {
                    this.createNodes(m.getId(), node);
                }
            }

        } else {
            List<HrmMenu> menus = hrmMenuService.getAllDataFetchChildByParentId(parentId);
            for (HrmMenu m : menus) {
//                boolean isSelected = selectedMenuIds.contains(m.getId());
                TreeNode node = new DefaultTreeNode(m, parentNode);
                node.setExpanded(Boolean.TRUE);
//                node.setSelected(isSelected);
                if (!m.getHrmMenus().isEmpty()) {
                    this.createNodes(m.getId(), node);
                }
            }
        }
    }
}
