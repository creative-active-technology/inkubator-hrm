package com.inkubator.hrm.web.converter;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taufik Hidayat
 */
@FacesConverter(value = "modelCompSpesificConverter")
public class ModelCompSpesificConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {

        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        Integer data = (Integer) obj;
        
        String message = StringUtils.EMPTY;
        if (data.equals(HRMConstant.MODEL_COMP_BASIC_SALARY)) {
        	message = messages.getString("modelComponent.modelComponent_basic");
        } else if (data.equals(HRMConstant.MODEL_COMP_CEIL)) {
        	message = messages.getString("modelComponent.modelComponent_ceil");
        } else if (data.equals(HRMConstant.MODEL_COMP_TAX)) {
            message = messages.getString("modelComponent.modelComponent_tax");
        } else if (data.equals(HRMConstant.MODEL_COMP_UPLOAD)) {
            message = messages.getString("modelComponent.modelComponent_upload");
        } else if (data.equals(HRMConstant.MODEL_COMP_FORMULA)) {
        	message = messages.getString("modelComponent.modelComponent_formula");
        } else if (data.equals(HRMConstant.MODEL_COMP_LOAN)) {
        	message = messages.getString("modelComponent.modelComponent_loan_deduction");
        } else if (data.equals(HRMConstant.MODEL_COMP_REIMBURSEMENT)) {
        	message = messages.getString("modelComponent.modelComponent_reimbursement");
        } else if (data.equals(HRMConstant.MODEL_COMP_BENEFIT_TABLE)) {
        	message = messages.getString("modelComponent.modelComponent_benefit");
        }else if (data.equals(HRMConstant.MODEL_COMP_TAKE_HOME_PAY)) {
        	message = messages.getString("modelComponent.modelComponent_take_home_pay");
        }

        return message;

    }
}
