/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import com.inkubator.hrm.web.model.LoanApplicationFormModel;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesValidator(value = "loanNewTerminValidator")
public class LoanNewTerminValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        LoanApplicationFormModel model = (LoanApplicationFormModel) component.getAttributes().get("model");
        Integer termin = (Integer) obj;
        
        if (model == null || termin == null) {
            return; // Let required="true" do its job.
        }        
        
        if (termin > model.getSelectedLoanNewSchemaListOfType().getMaxPeriode()) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }

    }
}
