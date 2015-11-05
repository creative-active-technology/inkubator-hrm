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
@FacesConverter(value = "schedulerConfigNameConverter")
public class SchedulerConfigNameConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String str) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

        String messages = StringUtils.EMPTY;
        String data = (String) obj;

        switch (data) {
            case HRMConstant.SCHEDULER_CONFIG_NAME_USER_PASSWORD_NOTIFICATION:
                messages = resourceBundle.getString("scheduler_config.user_password_notification_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_APROVAL_MATRIX_NOTIF:
                messages = resourceBundle.getString("scheduler_config.approval_matrix_notification_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_DELETE_ACESS_HISTORY:
                messages = resourceBundle.getString("scheduler_config.delete_access_history_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_DELETE_LOG_HISTORY:
                messages = resourceBundle.getString("scheduler_config.delete_login_history_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_HOLIDAY_UPDATE:
                messages = resourceBundle.getString("scheduler_config.year_holiday_update_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_SCHEDULE_WORK:
                messages = resourceBundle.getString("scheduler_config.work_schedule_calculation_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_DELETE_TEMP_SCHEDULE_WORK:
                messages = resourceBundle.getString("scheduler_config.delete_temp_work_schedule_history_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_AUTO_APPROVAL_MATRIX:
                messages = resourceBundle.getString("scheduler_config.auto_approval_matrix_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_ADD_BALANCE_LEAVE:
                messages = resourceBundle.getString("scheduler_config.add_leave_balance_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_ADD_BALANCE_PERMIT:
                messages = resourceBundle.getString("scheduler_config.add_permit_balance_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_COMPANY_POLICY_SEND:
                messages = resourceBundle.getString("scheduler_config.company_policy_broadcast_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_ATTENDANCE_CALCULATE:
                messages = resourceBundle.getString("scheduler_config.employee_attendance_calculation_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_ANNOUNCMENT_GENERATING_LOG:
                messages = resourceBundle.getString("scheduler_config.create_notification_log_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_CONFIG_NAME_ANNOUNCMENT_SENDING_NOTIF:
                messages = resourceBundle.getString("scheduler_config.notification_broadcast_scheduler_process");
                break;

            case HRMConstant.SCHEDULER_MONITORING_LOG_DELETE:
                messages = resourceBundle.getString("scheduler_config.monitoring_log_delete");
                break;
            case HRMConstant.SCHEDULER_PASSWORD_COMPLEXITY:
                messages = resourceBundle.getString("scheduler_config.password_complexity");
                break;
            case HRMConstant.SCHEDULER_RECRUITMENT_CONFIG_EMP:
                messages = resourceBundle.getString("scheduler_config.recruitment_config_emp");
                break;
            default:

                break;
        }

        return messages;

    }
}
