/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.entity.RecruitLetters;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.RecruitLettersService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
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

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

}
