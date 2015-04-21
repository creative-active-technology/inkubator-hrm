/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitSelectionTypeTemplatesService;
import com.inkubator.hrm.web.model.SelectionTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "recruitSelectionTypeTemplatesFormController")
@ViewScoped
public class RecruitSelectionTypeTemplatesFormController extends BaseController {

    private TreeNode root;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{recruitSelectionTypeTemplatesService}")
    private RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService;
    private SelectionTypeModel selectionTypeModel;
  

  

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    @PostConstruct
    @Override
    public void initialization() {
//        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            if (param.contains("a")) {
                try {
                    selectionTypeModel=new SelectionTypeModel();
                    List<Jabatan>sourceData=jabatanService.getAllData();
                } catch (Exception ex) {
                   LOGGER.error(ex, ex);
                }
                
            }
                
//          
//            }
//
//        } catch (Exception ex) {
//            LOGGER.error(ex, ex);
//        }
    }

  
 

   

    public String doBack() {
        return "/protected/organisation/organiztion_level.htm?faces-redirect=true";
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public void setRecruitSelectionTypeTemplatesService(RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService) {
        this.recruitSelectionTypeTemplatesService = recruitSelectionTypeTemplatesService;
    }
    
    

}
