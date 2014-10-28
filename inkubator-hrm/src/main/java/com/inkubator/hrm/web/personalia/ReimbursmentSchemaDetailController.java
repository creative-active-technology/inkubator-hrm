/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "reimbursmentSchemaDetailController")
@ViewScoped
public class ReimbursmentSchemaDetailController extends BaseController{
    private ReimbursmentSchema selectedReimbursmentSchema;
    @ManagedProperty(value = "#{reimbursmentSchemaService}")
    private ReimbursmentSchemaService reimbursmentSchemaService;
    private List<ReimbursmentSchema> listReimbursmentSchema;
    private List<ReimbursmentSchemaEmployeeType> listReimbursmentSchemaEmployeeType;
    private Boolean isDisableRatio;
    private Boolean isMeasurementUnitOrNominal;
    @PostConstruct
    @Override
    public void initialization() {
        try {
            
            super.initialization();
            isMeasurementUnitOrNominal = Boolean.FALSE;
            isDisableRatio = Boolean.TRUE;
            String reimbursmentId = FacesUtil.getRequestParameter("execution");
            selectedReimbursmentSchema = reimbursmentSchemaService.getEntityByPkWithAllRelation(Long.parseLong(reimbursmentId.substring(1)));
            
            if(selectedReimbursmentSchema.getMeasurement() == HRMConstant.REIMBURSMENT_UNIT ){
                isMeasurementUnitOrNominal = Boolean.TRUE;
            }else{
                if(selectedReimbursmentSchema.getBasicValue() == HRMConstant.BASIC_VALUE_NOMINAL){
                    isDisableRatio = Boolean.FALSE;
                }
            }
            listReimbursmentSchemaEmployeeType = new ArrayList<>(selectedReimbursmentSchema.getReimbursmentSchemaEmployeeTypes());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    public String doBack() {
        return "/protected/personalia/reimbursment_schema_view.htm?faces-redirect=true";
    }
    
    public String doEdit() {
        return "/protected/personalia/reimbursment_schema_form.htm?faces-redirect=true&execution=e" + selectedReimbursmentSchema.getId();
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isMeasurementUnitOrNominal = null;
        isDisableRatio = null;
        selectedReimbursmentSchema = null;
        reimbursmentSchemaService = null;
        listReimbursmentSchema = null;
        listReimbursmentSchemaEmployeeType = null;
    }

    public ReimbursmentSchema getSelectedReimbursmentSchema() {
        return selectedReimbursmentSchema;
    }

    public void setSelectedReimbursmentSchema(ReimbursmentSchema selectedReimbursmentSchema) {
        this.selectedReimbursmentSchema = selectedReimbursmentSchema;
    }

    public ReimbursmentSchemaService getReimbursmentSchemaService() {
        return reimbursmentSchemaService;
    }

    public void setReimbursmentSchemaService(ReimbursmentSchemaService reimbursmentSchemaService) {
        this.reimbursmentSchemaService = reimbursmentSchemaService;
    }

    public List<ReimbursmentSchema> getListReimbursmentSchema() {
        return listReimbursmentSchema;
    }

    public void setListReimbursmentSchema(List<ReimbursmentSchema> listReimbursmentSchema) {
        this.listReimbursmentSchema = listReimbursmentSchema;
    }

    public List<ReimbursmentSchemaEmployeeType> getListReimbursmentSchemaEmployeeType() {
        return listReimbursmentSchemaEmployeeType;
    }

    public void setListReimbursmentSchemaEmployeeType(List<ReimbursmentSchemaEmployeeType> listReimbursmentSchemaEmployeeType) {
        this.listReimbursmentSchemaEmployeeType = listReimbursmentSchemaEmployeeType;
    }

    public Boolean getIsDisableRatio() {
        return isDisableRatio;
    }

    public void setIsDisableRatio(Boolean isDisableRatio) {
        this.isDisableRatio = isDisableRatio;
    }

    public Boolean getIsMeasurementUnitOrNominal() {
        return isMeasurementUnitOrNominal;
    }

    public void setIsMeasurementUnitOrNominal(Boolean isMeasurementUnitOrNominal) {
        this.isMeasurementUnitOrNominal = isMeasurementUnitOrNominal;
    }
    
    
}
