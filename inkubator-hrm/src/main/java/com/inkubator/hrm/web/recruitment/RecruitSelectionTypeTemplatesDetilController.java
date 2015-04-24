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
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Collections;
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
@ManagedBean(name = "recruitSelectionTypeTemplatesDetilController")
@ViewScoped
public class RecruitSelectionTypeTemplatesDetilController extends BaseController {

    private TreeNode root;
    @ManagedProperty(value = "#{recruitSelectionTypeTemplatesService}")
    private RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService;
    private RecruitSelectionTypeTemplates templates;
    private List<Jabatan> listJabatans;
//    private SelectionTypeModel selectionTypeModel;
//    private Map<String, Long> mapScoring = new TreeMap<>();
//    private Boolean isEdit;

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
        String param = FacesUtil.getRequestParameter("execution");
        if (param.contains("d")) {
            try {
                templates = recruitSelectionTypeTemplatesService.getWithJabatan((Long.parseLong(param.substring(1))));
                listJabatans = templates.getListJabatan();
                Collections.reverse(listJabatans);
                String paraMinusOneParent = "e" + templates.getRecruitSelectionTypeTemplates().getId();
                if (!templates.getRecruitSelectionTypeTemplates().getCode().equals("ROOT")) {
                    root = recruitSelectionTypeTemplatesService.cretaeNodeBreakEndPoint(paraMinusOneParent);
                } else {
                    root = new DefaultTreeNode();
                }
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
    }

    public String doBack() {
        return "/protected/recruitment/selection_type_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/recruitment/selection_typel_form.htm?faces-redirect=true&execution=e" + templates.getId();
    }

  

    public void setRecruitSelectionTypeTemplatesService(RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService) {
        this.recruitSelectionTypeTemplatesService = recruitSelectionTypeTemplatesService;
    }

    public RecruitSelectionTypeTemplates getTemplates() {
        return templates;
    }

    public void setTemplates(RecruitSelectionTypeTemplates templates) {
        this.templates = templates;
    }

    public List<Jabatan> getListJabatans() {
        return listJabatans;
    }

    public void setListJabatans(List<Jabatan> listJabatans) {
        this.listJabatans = listJabatans;
    }

}
