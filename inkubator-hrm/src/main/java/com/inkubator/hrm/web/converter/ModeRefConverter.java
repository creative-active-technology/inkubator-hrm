/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.service.RmbsTypeService;
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
 * @author deni.fahri
 */
@FacesConverter(value = "modeRefConverter")
public class ModeRefConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String message = StringUtils.EMPTY;
        // Pay Salary Component id
        String paySalaryComponentId = component.getAttributes().get("paySalaryComponentId").toString();
      
        try {
            //get specifik from paysalaryComponent->modelComponent->specifik
            PaySalaryComponentService paySalaryComponentService = (PaySalaryComponentService) ServiceWebUtil.getService("paySalaryComponentService");
            PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.valueOf(paySalaryComponentId));
          
            //if specific == loan schema
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_LOAN)) {
                LoanNewTypeService loanSchemaService = (LoanNewTypeService) ServiceWebUtil.getService("loanNewTypeService");

                LoanNewType loanNewType = loanSchemaService.getEntiyByPK(Long.valueOf(paySalaryComponent.getModelReffernsil()));
                message = loanNewType.getLoanTypeName();

            }
            //if specific == reimbursment schema
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
//             
                RmbsTypeService rmbsTypeService = (RmbsTypeService) ServiceWebUtil.getService("rmbsTypeService");
                RmbsType rmbsType = rmbsTypeService.getEntiyByPK(Long.valueOf(paySalaryComponent.getModelReffernsil()));
                message = rmbsType.getName();
            }
            //if specific ==benefit group
            if (Objects.equals(paySalaryComponent.getModelComponent().getSpesific(), HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
                BenefitGroupService benefitGroupService = (BenefitGroupService) ServiceWebUtil.getService("benefitGroupService");
                BenefitGroup benefitGroup = benefitGroupService.getEntiyByPK(Long.valueOf(paySalaryComponent.getModelReffernsil()));
                message = benefitGroup.getName();
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelReferensiConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

}
