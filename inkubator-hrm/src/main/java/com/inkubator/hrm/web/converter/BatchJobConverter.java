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
 * @author EKA
 */
@FacesConverter(value = "batchJobConverter")
public class BatchJobConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        
        String message = StringUtils.EMPTY;
        String data = (String) value;
        switch(data){
            case "jobSynchDataFingerRealization"    :
                message = resourceBundle.getString("batch.jobSynchDataFingerRealization");
                break;
            case "jobPaySalaryUpload"               :
                message = resourceBundle.getString("batch.jobPaySalaryUpload");
                break;
            case "jobPayAttendanceUpload"           :
                message = resourceBundle.getString("batch.jobPayAttendanceUpload");
                break;
            case "jobPayTempOvertimeUpload"         :
                message = resourceBundle.getString("batch.jobPayTempOvertimeUpload");
                break;
            case "jobPayEmployeeCalculation"        :
                message = resourceBundle.getString("batch.jobPayEmployeeCalculation");
                break;
            case "jobPayReceiverAccount"            :
                message = resourceBundle.getString("batch.jobPayReceiverAccount");
                break;
            case "jobMonthEndPayroll"               :
                message = resourceBundle.getString("batch.jobMonthEndPayroll");
                break;
            case "jobLoanUpload"                    :
                message = resourceBundle.getString("batch.jobLoanUpload");
                break;
            case "jobUnregCalculation"              :
                message = resourceBundle.getString("batch.jobUnregCalculation");
                break;
            case "jobUnregPayroll"                  :
                message = resourceBundle.getString("batch.jobUnregPayroll");
                break;
            case "jobAnnouncementLog"               :
                message = resourceBundle.getString("batch.jobAnnouncementLog");
                break;
            case "jobEmailingAnnouncementNotSent"   :
                message = resourceBundle.getString("batch.jobEmailingAnnouncementNotSent");
                break;
            default :
                message = data;
                break;
        }
        return message;
    }
    
}
