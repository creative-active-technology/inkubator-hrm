/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.validator;

import com.inkubator.hrm.entity.RecruitMppPeriod;
import com.inkubator.hrm.service.RecruitMppPeriodService;
import com.inkubator.hrm.web.model.RecruitHireApplyModel;
import com.inkubator.webcore.util.ServiceWebUtil;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@FacesValidator(value = "mppEffectiveDateRangeValidator")
public class MppEffectiveDateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object obj) throws ValidatorException {
    	RecruitHireApplyModel model = (RecruitHireApplyModel) component.getAttributes().get("model");
        Date effectiveDate = (Date) obj;
        
        if (model == null || effectiveDate == null) {
            return; // Let required="true" do its job.
        }        
        
        if(model.getRecruitMppPeriodId() == null){
        	return; // Let required="true" do its job.
        }
        
        RecruitMppPeriodService recruitMppPeriodService = (RecruitMppPeriodService) ServiceWebUtil.getService("recruitMppPeriodService");
        RecruitMppPeriod recruitMppPeriod = null;
        
		try {
			recruitMppPeriod = recruitMppPeriodService.getEntiyByPK(model.getRecruitMppPeriodId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (!isDateInRange(effectiveDate, recruitMppPeriod.getPeriodeStart(), recruitMppPeriod.getPeriodeEnd())) {
            String validatorMessage = (String) component.getAttributes().get("validatorMessage");
            throw new ValidatorException(new FacesMessage(validatorMessage));
        }

    }
    
    private Boolean isDateInRange(Date dateToCheck, Date dateFrom, Date dateEnd){
    	return dateToCheck.after(dateFrom) && dateToCheck.before(dateEnd);
    }
}
