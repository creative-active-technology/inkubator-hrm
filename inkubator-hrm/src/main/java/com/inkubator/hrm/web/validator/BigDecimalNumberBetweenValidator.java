/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Deni
 */
@FacesValidator(value = "bigDecimalNumberBetweenValidator")
public class BigDecimalNumberBetweenValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        BigDecimal beginNumber = (BigDecimal) value;

        // Obtain the component and submitted value of the end number component.
        UIInput endTimeComponent = (UIInput) component.getAttributes().get("endNumberInteger");
        String data = (String) endTimeComponent.getSubmittedValue();

        BigDecimal endNumber = new BigDecimal(data);
        // Check if they both are filled in.
        if (beginNumber == null || data == null) {
            return; // Let required="true" do its job.
        }
        int result;
        result = beginNumber.compareTo(endNumber); // compare beginNumber and endNumber
        // Check if begin time is bigger/equal with end time
        if (result == 1) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }
    }
    
}
