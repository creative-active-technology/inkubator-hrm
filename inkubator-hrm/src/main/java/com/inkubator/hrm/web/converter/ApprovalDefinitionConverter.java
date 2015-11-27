/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author arsyad_
 */
@FacesConverter(value = "approvalDefinitionConverter")
public class ApprovalDefinitionConverter implements Converter{
	
    
	@Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return null;
    }
	
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value){
		System.out.println(HRMConstant.APPROVAL_TYPE_DEPARTMENT + "=================================");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
		String messages = StringUtils.EMPTY;
		String data = (String) value;
		switch (data){
			case HRMConstant.REIMBURSEMENT					:
				messages = resourceBundle.getString("reimbursment.reimbursment");
				break;
			case HRMConstant.RECRUIT_MPP_APPLY				:
				messages = resourceBundle.getString("approvaldefinition.recruit_mpp_apply");
				break;
			case HRMConstant.LEAVE							:
				messages = resourceBundle.getString("global.leave");
				break;
			case HRMConstant.OVERTIME						:
				messages = resourceBundle.getString("global.overtime");
				break;
			case HRMConstant.RECRUITMENT_REQUEST			:
				messages = resourceBundle.getString("recruitment.recruitment_request");
				break;
			case HRMConstant.EMP_CORRECTION_ATTENDANCE		:
				messages = resourceBundle.getString("emp_correction_attendance.correction_attendance");
				break;
			case HRMConstant.LOAN							:
				messages = resourceBundle.getString("loan.loan");
				break;
			case HRMConstant.SHIFT_SCHEDULE					:
				messages = resourceBundle.getString("global.schedule_shift");
				break;
			case HRMConstant.BUSINESS_TRAVEL				:
				messages = resourceBundle.getString("businesstravel.business_travel");
				break;
			case HRMConstant.PERMIT							:
				messages = resourceBundle.getString("global.permit");
				break;
			case HRMConstant.EMPLOYEE_CAREER_TRANSITION							:
				messages = resourceBundle.getString("emp_career_transition.career_transition");
				break;
			case HRMConstant.APPROVAL_PROCESS				:
				messages = resourceBundle.getString("approvaldefinition.approval_process");
				break;
			case HRMConstant.APPROVAL_TYPE_POSITION			:
				messages = resourceBundle.getString("position.position");
				break;
			case HRMConstant.APPROVAL_TYPE_DEPARTMENT 		:
				messages = resourceBundle.getString("department.department");
				break;
			case HRMConstant.APPROVAL_TYPE_INDIVIDUAL 		:
				messages = resourceBundle.getString("global.individual");
				break;
			default	:
				messages = data;
				break;
		}
		
		return messages;

	}
}
