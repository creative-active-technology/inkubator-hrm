package com.inkubator.hrm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.HRMConstant;

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

        
        // Check if they both are filled in.
        if (beginNumber == null || data == null) {
            return; // Let required="true" do its job.
        }
        
        // get required attributes value.
        String constraint = (String) component.getAttributes().get("validatorConstraint");
        String validatorMessage = (String) component.getAttributes().get("validatorMessage");

        Integer endNumber = Integer.parseInt(data);
        if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_EQUALS) && beginNumber == endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        } else if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_NOT_EQUALS) && beginNumber != endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        } else if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_GREATER_EQUALS) && beginNumber >= endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        } else if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_GREATER_THAN) && beginNumber > endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        } else if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_LESS_EQUALS) && beginNumber <= endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        } else if (StringUtils.equals(constraint, HRMConstant.COMPARATOR_LESS_THAN) && beginNumber < endNumber) {
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }
    }

}
