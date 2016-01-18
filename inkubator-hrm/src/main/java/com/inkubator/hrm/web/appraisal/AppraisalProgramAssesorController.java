/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;
import com.inkubator.hrm.entity.AppraisalProgramEmpId;
import com.inkubator.hrm.service.AppraisalProgramEmpAssesorService;
import com.inkubator.hrm.service.AppraisalProgramEmpService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.List;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "appraisalProgramAssesorController")
@ViewScoped
public class AppraisalProgramAssesorController extends BaseController {

    private AppraisalProgram appraisalProgram;

    @ManagedProperty(value = "#{appraisalProgramEmpService}")
    private AppraisalProgramEmpService appraisalProgramEmpService;
    private AppraisalProgramEmp selected;
    private List<AppraisalProgramEmpAssesor> dataToShow;
    @ManagedProperty(value = "#{appraisalProgramEmpAssesorService}")
    private AppraisalProgramEmpAssesorService appraisalProgramEmpAssesorService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String appraisalProgramId = FacesUtil.getRequestParameter("execution").substring(1);
            String empDataId = FacesUtil.getRequestParameter("caution").substring(1);
            AppraisalProgramEmpId id = new AppraisalProgramEmpId(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
            selected = appraisalProgramEmpService.getByIdWithDetail(id);
            dataToShow=appraisalProgramEmpAssesorService.getAllBy(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
       
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalProgram = null;
    }

    public String doBack() {
        return "/protected/appraisal/appraisal_program_result.htm?faces-redirect=true";
    }

    public AppraisalProgram getAppraisalProgram() {
        return appraisalProgram;
    }

    public void setAppraisalProgram(AppraisalProgram appraisalProgram) {
        this.appraisalProgram = appraisalProgram;
    }

    public AppraisalProgramEmp getSelected() {
        return selected;
    }

    public void setSelected(AppraisalProgramEmp selected) {
        this.selected = selected;
    }

    public String doDistribusiPenilai() {
        return "/protected/appraisal/appraisal_program_assesor.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void setAppraisalProgramEmpService(AppraisalProgramEmpService appraisalProgramEmpService) {
        this.appraisalProgramEmpService = appraisalProgramEmpService;
    }

    public void setAppraisalProgramEmpAssesorService(AppraisalProgramEmpAssesorService appraisalProgramEmpAssesorService) {
        this.appraisalProgramEmpAssesorService = appraisalProgramEmpAssesorService;
    }

    public List<AppraisalProgramEmpAssesor> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<AppraisalProgramEmpAssesor> dataToShow) {
        this.dataToShow = dataToShow;
    }

    
}
