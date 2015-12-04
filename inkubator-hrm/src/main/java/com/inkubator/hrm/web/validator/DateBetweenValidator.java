package com.inkubator.hrm.web.validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Deni Husni FR
 */
@FacesValidator(value = "dateBetweenValidator")
public class DateBetweenValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Date beginTime = (Date) obj;

        // Obtain the component and submitted value of the end time component.
        UIInput endTimeComponent = (UIInput) component.getAttributes().get("endTime");
        SimpleDateFormat parser = new SimpleDateFormat("dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        Date endTime = null;
        try {
            endTime = parser.parse((String) endTimeComponent.getSubmittedValue());
        } catch (Exception e) {
        	
        }
        
        // Check if they both are filled in.
        if (beginTime == null || endTime == null) {
            return; // Let required="true" do its job.
        }

        // Check if begin time is bigger than end time
        if (beginTime.after(endTime)) {
            String validatorMessage = (String) component.getAttributes().get("dateBetweenValidatorMessage");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", validatorMessage));
        }
    }

}
