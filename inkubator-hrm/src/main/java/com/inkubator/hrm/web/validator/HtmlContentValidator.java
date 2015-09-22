package com.inkubator.hrm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesValidator(value = "htmlContentValidator")
public class HtmlContentValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
        String content = (String) obj;
       
        // Check if content are filled in.
        if (content == null) {
            return; // Let required="true" do its job.
        }
        
        // Removes html tag, check if extractedContent still blank, return validatorException
        String extractedContent = content.replaceAll("\\<.*?>","");
        if (StringUtils.isBlank(extractedContent)) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }
    }

}
