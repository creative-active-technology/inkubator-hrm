/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.inkubator.hrm.web.model.LoanApplicationFormModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesValidator(value = "loanNewMaxMinLoanAmountValidator")
public class LoanNewMaxMinLoanAmountValidator implements Validator {
     @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        LoanApplicationFormModel model = (LoanApplicationFormModel) component.getAttributes().get("model");
        Double nominalLoan = ((Number) obj).doubleValue();
        
        if (model == null || nominalLoan == null) {
            return; // Let required="true" do its job.
        }      
        
         if (model.getSelectedLoanNewSchemaListOfType() == null) {
            return; // Let required="true" do its job.
        }  
        
        if (nominalLoan < model.getSelectedLoanNewSchemaListOfType().getMinimumAllocation()) {
            String validatorLowerThanMinMessage = (String) component.getAttributes().get("validatorLowerThanMinMessage");
            throw new ValidatorException(new FacesMessage(validatorLowerThanMinMessage));
        }
        
        if (nominalLoan > model.getSelectedLoanNewSchemaListOfType().getMaximumAllocation()) {
            String validatorMaxExceedMessage = (String) component.getAttributes().get("validatorMaxExceedMessage");
            throw new ValidatorException(new FacesMessage(validatorMaxExceedMessage));
        }

    }
}
