/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.RecruitSelectionType;
import com.inkubator.hrm.entity.RecruitSelectionTypeField;
import com.inkubator.hrm.service.RecruitSelectionTypeFieldService;
import com.inkubator.hrm.service.RecruitSelectionTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "recruitSelectionTypeDetailController")
@ViewScoped
public class RecruitSelectionTypeDetailController extends BaseController {

    @ManagedProperty(value = "#{recruitSelectionTypeService}")
    private RecruitSelectionTypeService recruitSelectionTypeService;
    @ManagedProperty(value = "#{recruitSelectionTypeFieldService}")
    private RecruitSelectionTypeFieldService recruitSelectionTypeFieldService;
    private RecruitSelectionType selectedRecruitSelectionType;
    private List<RecruitSelectionTypeField> listRecruitSelectionTypeField;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String recruitSelectionTypeId = FacesUtil.getRequestParameter("execution");
            selectedRecruitSelectionType = recruitSelectionTypeService.getEntiyByPK(Long.valueOf(recruitSelectionTypeId));
            listRecruitSelectionTypeField = recruitSelectionTypeFieldService.getEntityByRecruitSelectionTypeId(Long.valueOf(recruitSelectionTypeId));
        } catch (Exception ex) {
            Logger.getLogger(RecruitSelectionTypeDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        recruitSelectionTypeService = null;
        selectedRecruitSelectionType = null;
        listRecruitSelectionTypeField = null;
        recruitSelectionTypeFieldService = null;

    }

    public String doBack() {
        return "/protected/recruitment/recruit_selection_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/recruitment/recruit_selection_form.htm?faces-redirect=true&execution=" + selectedRecruitSelectionType.getId();

    }

    public RecruitSelectionTypeService getRecruitSelectionTypeService() {
        return recruitSelectionTypeService;
    }

    public void setRecruitSelectionTypeService(RecruitSelectionTypeService recruitSelectionTypeService) {
        this.recruitSelectionTypeService = recruitSelectionTypeService;
    }

    public RecruitSelectionType getSelectedRecruitSelectionType() {
        return selectedRecruitSelectionType;
    }

    public void setSelectedRecruitSelectionType(RecruitSelectionType selectedRecruitSelectionType) {
        this.selectedRecruitSelectionType = selectedRecruitSelectionType;
    }

    public RecruitSelectionTypeFieldService getRecruitSelectionTypeFieldService() {
        return recruitSelectionTypeFieldService;
    }

    public void setRecruitSelectionTypeFieldService(RecruitSelectionTypeFieldService recruitSelectionTypeFieldService) {
        this.recruitSelectionTypeFieldService = recruitSelectionTypeFieldService;
    }

    public List<RecruitSelectionTypeField> getListRecruitSelectionTypeField() {
        return listRecruitSelectionTypeField;
    }

    public void setListRecruitSelectionTypeField(List<RecruitSelectionTypeField> listRecruitSelectionTypeField) {
        this.listRecruitSelectionTypeField = listRecruitSelectionTypeField;
    }

}
