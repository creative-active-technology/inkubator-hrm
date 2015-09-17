/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;


/**
 *
 * @author deni
 */
@FacesConverter(value = "modelReferensiConverter")
public class ModelReferensiConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String message = StringUtils.EMPTY;
        Integer data = (Integer) value;
      
        System.out.println(" Data ");
        String paySalaryComponentId = component.getAttributes().get("paySalaryComponentId").toString();
        System.out.println(" Data " + paySalaryComponentId);
        try {
            //get specifik from paysalaryComponent->modelComponent->specifik
            PaySalaryComponentService paySalaryComponentService = (PaySalaryComponentService) ServiceWebUtil.getService("paySalaryComponentService");
            PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.valueOf(paySalaryComponentId));
            //if specific == loan schema
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_LOAN)) {
                LoanSchemaService loanSchemaService = (LoanSchemaService) ServiceWebUtil.getService("loanSchemaService");
                message = loanSchemaService.getLoanSchemaNameByPk(Long.valueOf(paySalaryComponent.getModelReffernsil()));
            }
            //if specific == reimbursment schema
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
                ReimbursmentSchemaService reimbursmentSchemaService = (ReimbursmentSchemaService) ServiceWebUtil.getService("reimbursmentSchemaService");
                message = reimbursmentSchemaService.getReimbursmentSchemaNameByPk(Long.valueOf(paySalaryComponent.getModelReffernsil()));
            }
            //if specific ==benefit group
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
                BenefitGroupService benefitGroupService = (BenefitGroupService) ServiceWebUtil.getService("benefitGroupService");
                message = benefitGroupService.getBenefitGroupNameByPk(Long.valueOf(paySalaryComponent.getModelReffernsil()));
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelReferensiConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

}
