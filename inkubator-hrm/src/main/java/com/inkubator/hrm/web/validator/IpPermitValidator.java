/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Deni
 */
@FacesValidator(value = "ipPermitValidator")
public class IpPermitValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        //for ipadress 255.255.255
        String ipAddressFrom = (String) value;
        
        //for ipaddress from
        UIInput fromIpComponent = (UIInput) component.getAttributes().get("untilAdress1");
        String fromIp = (String) fromIpComponent.getSubmittedValue();
        
        //for ipaddress until
        UIInput untilIpComponent = (UIInput) component.getAttributes().get("untilAdress2");
        String untilIp = (String) untilIpComponent.getSubmittedValue();
        
        System.out.println(fromIp + "data yeuhhh0" + untilIp);
        if(ipAddressFrom == null || fromIp == null || untilIp == null){
            return;
        }
        Integer ipAddress = Integer.valueOf(StringUtils.replace(ipAddressFrom, ".", ""));
        Integer ipFrom = Integer.valueOf(fromIp);
        Integer ipUntil = Integer.valueOf(untilIp);
        
        //validate if ipAddress greater than 255.255.255
        if(ipAddress > 255255255){
            System.out.println("masuk 255255255");
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
                throw new ValidatorException(new FacesMessage(validatorMessage));
        }
        //validate if from ip greater than 255
        if(ipFrom > 255){
            System.out.println("masuk 255 kesatu");
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
                throw new ValidatorException(new FacesMessage(validatorMessage));
        }
        //validate if until ip greater than 255
        if(ipUntil > 255){
            System.out.println("masuk 255 kedua");
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
                throw new ValidatorException(new FacesMessage(validatorMessage));
        }
        
    
    }
    
}
