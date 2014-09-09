/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

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
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String reimbursmentId = FacesUtil.getRequestParameter("execution");
            selectedReimbursmentSchema = reimbursmentSchemaService.getEntityByPkWithAllRelation(Long.parseLong(reimbursmentId.substring(1)));
            listReimbursmentSchemaEmployeeType = new ArrayList<>(selectedReimbursmentSchema.getReimbursmentSchemaEmployeeTypes());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    public String doBack() {
        return "/protected/personalia/reimbursment_view.htm?faces-redirect=true";
    }
    
    @PreDestroy
    public void cleanAndExit() {
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
    
    
}
