package com.inkubator.hrm.web.validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Deni Husni FR
 */
@FacesValidator(value = "birthDateValidator")
public class BirthDateValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Date birthDate = (Date) obj;

        if (birthDate == null) {
            return; // Let required="true" do its job.
        }

        // Check if begin time is bigger/equal with end time
        if (birthDate.after(new Date()) || birthDate.equals(new Date())) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }
    }

}
