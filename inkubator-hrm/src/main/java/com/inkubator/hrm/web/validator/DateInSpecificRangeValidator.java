package com.inkubator.hrm.web.validator;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesValidator(value = "dateInSpecificRangeValidator")
public class DateInSpecificRangeValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Date timeToCheck = (Date) obj;
        
        // Obtain the component and submitted value of start and end date component.
        UIInput startTimeComponent = (UIInput) component.getAttributes().get("startTime");
        UIInput endTimeComponent = (UIInput) component.getAttributes().get("endTime");
        
        //Jika status nya disabled, berarti bukan mandatory, tidak perlu melakukan pengecekan
        if(((Calendar)startTimeComponent).isDisabled() || ((Calendar)endTimeComponent).isDisabled() ){
        	return;
        }
        
        Date startDate = null;
        Date endDate = null;
        try {
        	startDate = (Date) startTimeComponent.getValue();
        	endDate = (Date) endTimeComponent.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if they both are filled in.
        if (timeToCheck == null || startDate == null || endDate == null ) {
            return; // Let required="true" do its job.
        }
        
        // Convert variables to DateTime (Joda Time)
        DateTime startDateTime = new DateTime(startDate);
        DateTime endDateTime = new DateTime(endDate);
        DateTime dateTimeToCheck = new DateTime(timeToCheck);
        Interval interval = new Interval(startDateTime, endDateTime);
        
        // Check if timeToCheck is in range of startDate and EndDate
        if (!interval.contains(dateTimeToCheck)) {
            String validatorMessage = (String) component.getAttributes().get("dateInSpecificRangeValidatorMessage");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", validatorMessage));
        }
    }

}
