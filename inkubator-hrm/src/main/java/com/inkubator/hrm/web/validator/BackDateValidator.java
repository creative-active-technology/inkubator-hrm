package com.inkubator.hrm.web.validator;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.time.DateUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@FacesValidator(value = "backDateValidator")
public class BackDateValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        Date date = (Date) obj;
        Date now  = new Date();

        if (date == null) {
            return; // Let required="true" do its job.
        }

        // Check if begin time is bigger/equal with end time
        if (!DateUtils.isSameDay(date, now) && date.before(now)) {
        	String locale = FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString();
        	ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(locale));
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", resourceBundle.getString("global.error_cannot_backdate")));
        }
    }

}
