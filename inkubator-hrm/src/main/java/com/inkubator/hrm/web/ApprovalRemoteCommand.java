package com.inkubator.hrm.web;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "approvalRemoteCommand")
@RequestScoped
public class ApprovalRemoteCommand {

    public void showNotification() {
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String approverUserId = params.get("approverUserId");
        String requestUserId = params.get("requestUserId");
        String approverFullName = params.get("approverFullName");
        String requestFullName = params.get("requestFullName");
        String approvalName = params.get("approvalName");

        if (StringUtils.equals(approverUserId, UserInfoUtil.getUserName())) {
            String infoMessages = StringUtils.EMPTY;
            switch (approvalName) {
                case HRMConstant.BUSINESS_TRAVEL:
                    infoMessages = messages.getString("businesstravel.submission_of_business_travel") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.LOAN:
                    infoMessages = messages.getString("loan.submission_of_loan") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.REIMBURSEMENT:
                    infoMessages = messages.getString("reimbursment.submission_of_reimbursment") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.SHIFT_SCHEDULE:
                    infoMessages = messages.getString("workschedule.submission_of_employee_working_schedule") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.LEAVE:
                    infoMessages = messages.getString("leaveimplementation.submission_of_leave") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.LEAVE_CANCELLATION:
                    infoMessages = messages.getString("leaveimplementation.cancellation_of_leave") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                case HRMConstant.OVERTIME:
                    infoMessages = messages.getString("overtimeimplementation.submission_of_overtime") + " " + requestFullName + " " + messages.getString("approval.need_approval_from") + " " + approverFullName;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Executed", infoMessages));
                    break;
                default:
                    break;
            }
        }

    }
}
