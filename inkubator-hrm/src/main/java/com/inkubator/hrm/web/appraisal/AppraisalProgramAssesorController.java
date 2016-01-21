/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.appraisal;

import com.inkubator.hrm.HRMConstant;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;
import com.inkubator.hrm.entity.AppraisalProgramEmpId;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.AppraisalProgramEmpAssesorService;
import com.inkubator.hrm.service.AppraisalProgramEmpService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.AppraisalProgramEmpAssesorModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

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
    private AppraisalProgramEmpAssesor selectedAppraisalProgramEmpAssesor;
    private String appraisalProgramId;
    private String empDataId;
    private AppraisalProgramEmpAssesorModel assesorModel;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Boolean isEdit;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            appraisalProgramId = FacesUtil.getRequestParameter("execution").substring(1);
            empDataId = FacesUtil.getRequestParameter("caution").substring(1);
            AppraisalProgramEmpId id = new AppraisalProgramEmpId(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
            selected = appraisalProgramEmpService.getByIdWithDetail(id);
            dataToShow = appraisalProgramEmpAssesorService.getAllBy(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));

        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
        appraisalProgram = null;
    }

    public String doBack() {
        return "/protected/appraisal/appraisal_program_employee.htm?faces-redirect=true&execution=e" + appraisalProgramId;
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

    public void doDetail() {

    }

    public AppraisalProgramEmpAssesor getSelectedAppraisalProgramEmpAssesor() {
        return selectedAppraisalProgramEmpAssesor;
    }

    public void setSelectedAppraisalProgramEmpAssesor(AppraisalProgramEmpAssesor selectedAppraisalProgramEmpAssesor) {
        this.selectedAppraisalProgramEmpAssesor = selectedAppraisalProgramEmpAssesor;
    }

    public void doDelete() {
        try {
            appraisalProgramEmpAssesorService.delete(selectedAppraisalProgramEmpAssesor);
            dataToShow = appraisalProgramEmpAssesorService.getAllBy(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete menu ", ex);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public void doAdd() {
        assesorModel = new AppraisalProgramEmpAssesorModel();
        isEdit = Boolean.FALSE;
    }

    public AppraisalProgramEmpAssesorModel getAssesorModel() {
        return assesorModel;
    }

    public void setAssesorModel(AppraisalProgramEmpAssesorModel assesorModel) {
        this.assesorModel = assesorModel;
    }

    public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param), HrmUserInfoUtil.getCompanyId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }

    public void onChangeEmployee() {
        try {

            EmpData empData = empDataService.getByEmpIdWithDetail(assesorModel.getEmpData().getId());
            assesorModel.setJabatanId(empData.getJabatanByJabatanId().getId());
            assesorModel.setJabatanName(empData.getJabatanByJabatanId().getName());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public void doSave() {
        if (isEdit) {
            System.out.println(" edittt tlhooooooo");
            try {
                AppraisalProgramEmpAssesor apea = new AppraisalProgramEmpAssesor();
                apea.setId(assesorModel.getId());
                apea.setEmpDataByAssesorEmpId(assesorModel.getEmpData());
                apea.setAppraisalProgram(new AppraisalProgram(Long.parseLong(appraisalProgramId)));
                apea.setEmpDataByEmpId(new EmpData(Long.parseLong(empDataId)));
                apea.setScala(assesorModel.getScala());
                appraisalProgramEmpAssesorService.update(apea);
                dataToShow = appraisalProgramEmpAssesorService.getAllBy(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        } else {
            try {
                AppraisalProgramEmpAssesor apea = new AppraisalProgramEmpAssesor();
                apea.setEmpDataByAssesorEmpId(assesorModel.getEmpData());
                apea.setAppraisalProgram(new AppraisalProgram(Long.parseLong(appraisalProgramId)));
                apea.setEmpDataByEmpId(new EmpData(Long.parseLong(empDataId)));
                apea.setScala(assesorModel.getScala());
                appraisalProgramEmpAssesorService.save(apea);
                dataToShow = appraisalProgramEmpAssesorService.getAllBy(Long.parseLong(appraisalProgramId), Long.parseLong(empDataId));
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }

    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void doEdit() {
        assesorModel = new AppraisalProgramEmpAssesorModel();
        assesorModel.setId(selectedAppraisalProgramEmpAssesor.getId());
        assesorModel.setEmpData(selectedAppraisalProgramEmpAssesor.getEmpDataByAssesorEmpId());
        assesorModel.setAppraisalProgramId(selectedAppraisalProgramEmpAssesor.getAppraisalProgram().getId());
        assesorModel.setEmpId(selectedAppraisalProgramEmpAssesor.getEmpDataByEmpId().getId());
        assesorModel.setScala(selectedAppraisalProgramEmpAssesor.getScala());
        isEdit = Boolean.TRUE;
        onChangeEmployee();
    }

    
    
}
