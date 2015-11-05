/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author denifahri
 */
@ManagedBean(name = "offeringAndProhabitDetilController")
@ViewScoped
public class OfferingAndProhabitDetilController extends BaseController {

    @ManagedProperty(value = "#{recruitLettersService}")
    private RecruitLettersService recruitLettersService;

    private RecruitLetters selectedRecruitLetters;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution");
            selectedRecruitLetters = recruitLettersService.getByPkWithDetail(Long.parseLong(id.substring(1)));
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {

    }

    public RecruitLetters getSelectedRecruitLetters() {
        return selectedRecruitLetters;
    }

    public void setSelectedRecruitLetters(RecruitLetters selectedRecruitLetters) {
        this.selectedRecruitLetters = selectedRecruitLetters;
    }

    public void setRecruitLettersService(RecruitLettersService recruitLettersService) {
        this.recruitLettersService = recruitLettersService;
    }

    public String doEdit() {
        if (selectedRecruitLetters.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_OFFERING)) {
            return "/protected/recruitment/offering_letter_form.htm?faces-redirect=true&execution=e" + selectedRecruitLetters.getId();
        }

        if (selectedRecruitLetters.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_PROBATION)) {
            return "/protected/recruitment/probation_letter_form.htm?faces-redirect=true&execution=e" + selectedRecruitLetters.getId();
        }

        if (selectedRecruitLetters.getLeterTypeId().equals(HRMConstant.LETTER_TYPE_REJECT)) {
            return "/protected/recruitment/reject_letter_form.htm?faces-redirect=true&execution=e" + selectedRecruitLetters.getId();
        }
        return null;
    }

    public String doBack() {
        return "/protected/recruitment/offering_letter_view.htm?faces-redirect=true";
    }

    public String doDistribution() {
        try {
            recruitLettersService.doDistributionRescruitLetter(selectedRecruitLetters);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.info", "offering_module.distributin_selection_success",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/recruitment/offering_letter_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        return null;
    }
}
