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
@FacesConverter(value = "empEliminationStatusConverter")
public class EmpEliminationStatusConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String messages = StringUtils.EMPTY;
        Integer data= (Integer) obj;
        if(Objects.equals(data, HRMConstant.EMP_ELIMINATION_WAITING_APPROVAL)){
        	messages = resourceBundle.getString("approvalactivity.waiting");
        } else if(Objects.equals(data, HRMConstant.EMP_ELIMINATION_APPROVED)){
        	messages = resourceBundle.getString("career.employee_elimination_approval_approved");
        } else if(Objects.equals(data, HRMConstant.EMP_ELIMINATION_REJECTED)){
        	messages = resourceBundle.getString("career.employee_elimination_approval_rejected");
        } 
        return messages;

    }
}
