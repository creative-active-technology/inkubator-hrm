package com.inkubator.hrm.web.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author rizkykojek
 */
@FacesValidator(value="timeBetweenValidator")
public class TimeBetweenValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent component, Object obj)throws ValidatorException {
		Date beginTime = (Date) obj;
		
		// Obtain the component and submitted value of the end time component.
		UIInput endTimeComponent = (UIInput) component.getAttributes().get("endTime");
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");		
        Date endTime = null;
		try {
			endTime = parser.parse((String) endTimeComponent.getSubmittedValue());
		} catch (ParseException e) {
			
		}
        
        // Check if they both are filled in.
        if(beginTime == null || endTime ==null){
        	return; // Let required="true" do its job.
        }
        
        // Check if begin time is bigger/equal with end time
        if(beginTime.after(endTime) || beginTime.equals(endTime)){
        	String validatorMessage = (String) component.getAttributes().get("validatorMessage");
        	throw new ValidatorException(new FacesMessage(validatorMessage));
        }
	}

}
