package com.inkubator.hrm.web.validator;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.service.PasswordComplexityService;
import com.inkubator.hrm.util.StringsUtils;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author rizkykojek
 */
@FacesValidator(value = "passwordComplexityValidator")
public class PasswordComplexityValidator implements Validator {

//    @ManagedProperty(value = "#{passwordComplexityService}")
//    private PasswordComplexityService passwordComplexityService;
//
//    public void setPasswordComplexityService(PasswordComplexityService passwordComplexityService) {
//        this.passwordComplexityService = passwordComplexityService;
//    }
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
//        try {
        String password = (String) obj;

        // Obtain the component and submitted value of the end time component.
        // Check if they both are filled in.
        if (password == null) {
            return; // Let required="true" do its job.
        }
        PasswordComplexityService passwordComplexityService = (PasswordComplexityService) ServiceWebUtil.getService("passwordComplexityService");
        PasswordComplexity complexity = null;
        try {
            complexity = passwordComplexityService.getByCode(HRMConstant.PASSWORD_CONFIG_CODE);
        } catch (Exception ex) {
            Logger.getLogger(PasswordComplexityValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        int size = password.length();
        Locale loc = Locale.forLanguageTag(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        if (complexity != null) {

            if (size < complexity.getMinCharacter()) {

                ResourceBundle messages = ResourceBundle.getBundle("messages", loc);
                FacesMessage msg = new FacesMessage(messages.getString("password_config.min_character") + " " + complexity.getMinCharacter());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);

                System.out.println("Ini adalah pesannya" + messages.getString("password_config.min_character"));
//                String validatorMessage = (String) component.getAttributes().get("validatorMessage");
                throw new ValidatorException(msg);
            }
            if (size > complexity.getMaxCharacter()) {
                System.out.println("DSVDFDSGDGFDG");
                ResourceBundle messages = ResourceBundle.getBundle("messages", loc);
                FacesMessage msg = new FacesMessage(messages.getString("password_config.max_character") + " " + complexity.getMaxCharacter());
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            if (complexity.getHasUpperCase()) {
                if (!StringsUtils.isHaveUpperCase(password)) {
                    ResourceBundle messages = ResourceBundle.getBundle("messages", loc);
                    FacesMessage msg = new FacesMessage(messages.getString("password_config.max_character") + " " + complexity.getMaxCharacter());
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(msg);
                }
            }

            if (complexity.getHasUpperCase()) {
                if (!StringsUtils.isHaveUpperCase(password)) {
                    ResourceBundle messages = ResourceBundle.getBundle("messages", loc);
                    FacesMessage msg = new FacesMessage(messages.getString("password_config.must_have_upper") + " " + complexity.getMaxCharacter());
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(msg);
                }
            }
            if (complexity.getHasLowerCase()) {
                if (!StringsUtils.isHaveLowerCase(password)) {
                    ResourceBundle messages = ResourceBundle.getBundle("messages", loc);
                    FacesMessage msg = new FacesMessage(messages.getString("password_config.max_character") + " " + complexity.getMaxCharacter());
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(msg);
                }
            }

        }

//            if (size >= complexity.getMaxCharacter()) {
//                String validatorMessage = (String) component.getAttributes().get("validatorMessage");
//                throw new ValidatorException(new FacesMessage(validatorMessage));
//            }
        // Check if begin time is bigger/equal with end time
//        if (beginTime.after(endTime) || beginTime.equals(endTime)) {
//            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
//            throw new ValidatorException(new FacesMessage(validatorMessage));
//        }
//        } catch (Exception ex) {
//            Logger.getLogger(PasswordComplexityValidator.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
