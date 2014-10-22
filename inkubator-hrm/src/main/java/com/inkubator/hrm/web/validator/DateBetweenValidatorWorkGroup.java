package com.inkubator.hrm.web.validator;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.text.ParseException;
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

/**
 *
 * @author Deni Husni FR
 */
@FacesValidator(value = "dateBetweenValidatorWorkGroup")
public class DateBetweenValidatorWorkGroup implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Date beginTime = (Date) obj;

        // Obtain the component and submitted value of the end time component.
        UIInput endTimeComponent = (UIInput) component.getAttributes().get("endTime");
        UIInput endTimeComponent1 = (UIInput) component.getAttributes().get("jenis");
        SimpleDateFormat parser = new SimpleDateFormat("dd MMMM yyyy", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        Date endTime = null;
        String kondisi = (String) endTimeComponent1.getSubmittedValue();
        System.out.println(" nili kondisi " + kondisi);
        try {
            endTime = parser.parse((String) endTimeComponent.getSubmittedValue());
        } catch (Exception e) {
            
        }

        // Check if they both are filled in.
        if (beginTime == null || endTime == null) {
            return; // Let required="true" do its job.
        }

        // Check if begin time is bigger/equal with end time
        if (beginTime.after(endTime) || beginTime.equals(endTime)) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }

    }

}
