package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesConverter(value = "loanDisbursementStatusConverter")
public class LoanDisbursementStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(Objects.equals(data, HRMConstant.LOAN_CANCELED)){
        	messages = resourceBundle.getString("approvalactivity.cancelled");
        } else if(Objects.equals(data, HRMConstant.LOAN_DISBURSED)){
        	messages = resourceBundle.getString("loan.has_been_disbursed");
        } else if(Objects.equals(data, HRMConstant.LOAN_PAID)){
        	messages = resourceBundle.getString("loan.paid");
        } else if(Objects.equals(data, HRMConstant.LOAN_REJECTED)){
        	messages = resourceBundle.getString("approvalactivity.reject");
        } else if(Objects.equals(data, HRMConstant.LOAN_UNDISBURSED)){
        	messages = resourceBundle.getString("loan.undisbursed");
        } 
        return messages;

    }
}
