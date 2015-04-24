/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplatesJobTitle;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplatesJobTitleId;
import com.inkubator.hrm.entity.SystemScoring;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.RecruitSelectionTypeTemplatesService;
import com.inkubator.hrm.service.SystemScoringService;
import com.inkubator.hrm.web.model.OrganisasiLevelModel;
import com.inkubator.hrm.web.model.SelectionTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
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
    @ManagedProperty(value = "#{systemScoringService}")
    private SystemScoringService systemScoringService;
    @ManagedProperty(value = "#{recruitSelectionTypeTemplatesService}")
    private RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService;
    private SelectionTypeModel selectionTypeModel;
    private Map<String, Long> mapScoring = new TreeMap<>();
    private Boolean isEdit;

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
        if (param.contains("a")) {
            try {
                selectionTypeModel = new SelectionTypeModel();
                List<SystemScoring> scorings = systemScoringService.getAllData();
                for (SystemScoring scoring : scorings) {
                    mapScoring.put(scoring.getName(), scoring.getId());
                }
                List<Jabatan> sourceData = jabatanService.getAllData();
                DualListModel<Jabatan> dualModel = new DualListModel<>();
                dualModel.setSource(sourceData);
                selectionTypeModel.setDualListModel(dualModel);
                selectionTypeModel.setParentId(Long.parseLong(param.substring(1)));
                root = recruitSelectionTypeTemplatesService.cretaeNodeBreakEndPoint(param);
                isEdit = Boolean.FALSE;
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }

        if (param.contains("e")) {
            try {
                selectionTypeModel = new SelectionTypeModel();
                List<SystemScoring> scorings = systemScoringService.getAllData();
                for (SystemScoring scoring : scorings) {
                    mapScoring.put(scoring.getName(), scoring.getId());
                }
                RecruitSelectionTypeTemplates curreSelectionTypeTemplates = recruitSelectionTypeTemplatesService.getWithJabatan((Long.parseLong(param.substring(1))));
                List<Jabatan> sourceData = jabatanService.getAllData();
                List<Jabatan> targetData = curreSelectionTypeTemplates.getListJabatan();
                Collections.reverse(targetData);
//                sourceData.removeAll(targetData); gak bisa di gunakan karena object Jabatan berbeda
                for (Jabatan targetData1 : targetData) {
                    sourceData.remove(targetData1);
                }
                DualListModel<Jabatan> dualModel = new DualListModel<>(sourceData, targetData);
                selectionTypeModel.setDualListModel(dualModel);
                String paraMinusOneParent = "e" + curreSelectionTypeTemplates.getRecruitSelectionTypeTemplates().getId();
                if (!curreSelectionTypeTemplates.getRecruitSelectionTypeTemplates().getCode().equals("ROOT")) {
                    root = recruitSelectionTypeTemplatesService.cretaeNodeBreakEndPoint(paraMinusOneParent);
                } else {
                    root = new DefaultTreeNode();
                }
                isEdit = Boolean.TRUE;
                selectionTypeModel.setCode(curreSelectionTypeTemplates.getCode());
                selectionTypeModel.setDescription(curreSelectionTypeTemplates.getDescription());
                selectionTypeModel.setIsActive(curreSelectionTypeTemplates.getIsActive());
                selectionTypeModel.setIsCategory(curreSelectionTypeTemplates.getIsCategory());
                selectionTypeModel.setName(curreSelectionTypeTemplates.getName());
                selectionTypeModel.setParentId(curreSelectionTypeTemplates.getRecruitSelectionTypeTemplates().getId());
                selectionTypeModel.setSelectionTemplateId(curreSelectionTypeTemplates.getId());
                selectionTypeModel.setSystemScoringId(curreSelectionTypeTemplates.getSystemScoring().getId());
                selectionTypeModel.setTargetNilai(curreSelectionTypeTemplates.getTargetNilai());
                
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
    }

    public String doBack() {
        return "/protected/recruitment/selection_type_view.htm?faces-redirect=true";
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public void setRecruitSelectionTypeTemplatesService(RecruitSelectionTypeTemplatesService recruitSelectionTypeTemplatesService) {
        this.recruitSelectionTypeTemplatesService = recruitSelectionTypeTemplatesService;
    }

    public SelectionTypeModel getSelectionTypeModel() {
        return selectionTypeModel;
    }

    public void setSelectionTypeModel(SelectionTypeModel selectionTypeModel) {
        this.selectionTypeModel = selectionTypeModel;
    }

    public void setSystemScoringService(SystemScoringService systemScoringService) {
        this.systemScoringService = systemScoringService;
    }

    public Map<String, Long> getMapScoring() {
        return mapScoring;
    }

    public void setMapScoring(Map<String, Long> mapScoring) {
        this.mapScoring = mapScoring;
    }

    public String doSave() {
        String redirect = null;
        if (isEdit) {
             System.out.println(" editiitit mode");
            try {
                Set<RecruitSelectionTypeTemplatesJobTitle> templatesJobTitles = new HashSet<>();
                List<Jabatan> listSelectedJabatan = selectionTypeModel.getDualListModel().getTarget();
                RecruitSelectionTypeTemplates rstt = getEntityFromViewModel(selectionTypeModel);
                for (Jabatan jabatan : listSelectedJabatan) {
                    RecruitSelectionTypeTemplatesJobTitle jobTitle = new RecruitSelectionTypeTemplatesJobTitle();
                    jobTitle.setId(new RecruitSelectionTypeTemplatesJobTitleId(rstt.getId(), jabatan.getId()));
                    jobTitle.setJabatan(jabatan);
                    jobTitle.setRecruitSelectionTypeTemplates(rstt);
                    templatesJobTitles.add(jobTitle);
                }
                rstt.setRecruitSelectionTypeTemplatesJobTitles(templatesJobTitles);
                recruitSelectionTypeTemplatesService.update(rstt);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                redirect = "/protected/recruitment/selection_typel_detil.htm?faces-redirect=true&execution=d" + rstt.getId();
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        } else {
            try {
                System.out.println(" Sveeeeeee mode");
                Set<RecruitSelectionTypeTemplatesJobTitle> templatesJobTitles = new HashSet<>();
                List<Jabatan> listSelectedJabatan = selectionTypeModel.getDualListModel().getTarget();
                RecruitSelectionTypeTemplates rstt = getEntityFromViewModel(selectionTypeModel);
                rstt.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
                for (Jabatan jabatan : listSelectedJabatan) {
                    RecruitSelectionTypeTemplatesJobTitle jobTitle = new RecruitSelectionTypeTemplatesJobTitle();
                    jobTitle.setId(new RecruitSelectionTypeTemplatesJobTitleId(rstt.getId(), jabatan.getId()));
                    jobTitle.setJabatan(jabatan);
                    jobTitle.setRecruitSelectionTypeTemplates(rstt);
                    templatesJobTitles.add(jobTitle);
                }
                rstt.setRecruitSelectionTypeTemplatesJobTitles(templatesJobTitles);
                recruitSelectionTypeTemplatesService.save(rstt);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                redirect = "/protected/recruitment/selection_typel_detil.htm?faces-redirect=true&execution=d" + rstt.getId();
            } catch (BussinessException ex) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        return redirect;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    private RecruitSelectionTypeTemplates getEntityFromViewModel(SelectionTypeModel model) throws Exception {
        RecruitSelectionTypeTemplates typeTemplates = new RecruitSelectionTypeTemplates();
        if (model.getSelectionTemplateId() != null) {
            typeTemplates.setId(model.getSelectionTemplateId());

        }
        typeTemplates.setCode(model.getCode());
        typeTemplates.setDescription(model.getDescription());
        typeTemplates.setIsActive(model.getIsActive());
        typeTemplates.setIsCategory(model.getIsCategory());
        typeTemplates.setName(model.getName());
        typeTemplates.setRecruitSelectionTypeTemplates(new RecruitSelectionTypeTemplates(model.getParentId()));
        typeTemplates.setSystemScoring(new SystemScoring(model.getSystemScoringId()));
        typeTemplates.setTargetNilai(model.getTargetNilai());
        return typeTemplates;
    }
}
