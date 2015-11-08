/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import com.inkubator.hrm.web.model.RecruitHireApplyModel;


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
@FacesValidator(value = "mppRequestMaxEmployeeValidator")
public class MppRequestMaxEmployeeValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
    	RecruitHireApplyModel model = (RecruitHireApplyModel) component.getAttributes().get("model");
        Integer maxRequest = (Integer) obj;
        
        if (model == null || maxRequest == null) {
            return; // Let required="true" do its job.
        }        
       
        if (maxRequest > model.getMpp()) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }

    }
}
