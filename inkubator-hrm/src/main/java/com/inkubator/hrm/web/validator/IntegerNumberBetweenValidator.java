package com.inkubator.hrm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Deni Husni FR
 */
@FacesValidator(value = "integerNumberBetweenValidator")
public class IntegerNumberBetweenValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Integer beginNumber = (Integer) obj;

        // Obtain the component and submitted value of the end number component.
        UIInput endTimeComponent = (UIInput) component.getAttributes().get("endNumberInteger");
        String data = (String) endTimeComponent.getSubmittedValue();

        Integer endNumber = Integer.parseInt(data);
        // Check if they both are filled in.
        if (beginNumber == null || data == null) {
            return; // Let required="true" do its job.
        }

        // Check if begin time is bigger/equal with end time
        if (beginNumber <= endNumber) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }
    }

}
