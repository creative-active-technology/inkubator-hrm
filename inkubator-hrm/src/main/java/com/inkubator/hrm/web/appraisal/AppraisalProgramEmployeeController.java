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
import com.inkubator.hrm.service.AppraisalProgramEmpAssesorService;
import com.inkubator.hrm.service.AppraisalProgramService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deni.fahri
 */
@ManagedBean(name = "appraisalProgramEmployeeController")
@ViewScoped
public class AppraisalProgramEmployeeController extends BaseController {

    private AppraisalProgram appraisalProgram;

    @ManagedProperty(value = "#{appraisalProgramService}")
    private AppraisalProgramService appraisalProgramService;
    private List<AppraisalProgramEmp> dataEmpAssesor;
    @ManagedProperty(value = "#{appraisalProgramEmpAssesorService}")
    private AppraisalProgramEmpAssesorService appraisalProgramEmpAssesorService;
    private AppraisalProgramEmp selected;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            System.out.println(" nilainya "+FacesUtil.getRequestParameter("caution"));
            appraisalProgram = appraisalProgramService.getEntityByIdWithDetail(Long.parseLong(id));
            dataEmpAssesor = new ArrayList<>(appraisalProgram.getAppraisalProgramEmps());
            for (AppraisalProgramEmp appraisalProgramEmp : dataEmpAssesor) {
                appraisalProgramEmp.setTotalAssesor(appraisalProgramEmpAssesorService.getTotalAssesorByAppraisalIAndEmpId(appraisalProgram.getId(), appraisalProgramEmp.getEmpData().getId()));
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalProgram = null;
        appraisalProgramService = null;
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

    public AppraisalProgramService getAppraisalProgramService() {
        return appraisalProgramService;
    }

    public void setAppraisalProgramService(AppraisalProgramService appraisalProgramService) {
        this.appraisalProgramService = appraisalProgramService;
    }

    public List<AppraisalProgramEmp> getDataEmpAssesor() {
        return dataEmpAssesor;
    }

    public void setDataEmpAssesor(List<AppraisalProgramEmp> dataEmpAssesor) {
        this.dataEmpAssesor = dataEmpAssesor;
    }

    public void setAppraisalProgramEmpAssesorService(AppraisalProgramEmpAssesorService appraisalProgramEmpAssesorService) {
        this.appraisalProgramEmpAssesorService = appraisalProgramEmpAssesorService;
    }

    public AppraisalProgramEmp getSelected() {
        return selected;
    }

    public void setSelected(AppraisalProgramEmp selected) {
        this.selected = selected;
    }

    public String doDistribusiPenilai(){
            return "/protected/appraisal/appraisal_program_assesor.htm?faces-redirect=true&execution=e" + selected.getId().getAppraisalProgramId()+"&caution=c"+selected.getId().getEmpDataId();
    }
}
