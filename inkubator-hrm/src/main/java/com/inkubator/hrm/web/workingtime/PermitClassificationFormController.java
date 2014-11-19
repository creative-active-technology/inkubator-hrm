package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PermitClassificationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "permitClassificationFormController")
@ViewScoped
public class PermitClassificationFormController extends BaseController {

    private PermitClassificationModel permitClassificationModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{permitClassificationService}")
    private PermitClassificationService permitClassificationService;
    @ManagedProperty(value = "#{attendanceStatusService}")
    private AttendanceStatusService attendanceStatusService;
    private Map<String, Long> attendanceStatuss = new TreeMap<>();
    private Boolean disabled;
    private Boolean hidden;
    private Integer minimum;
    private Integer maximum;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            permitClassificationModel = new PermitClassificationModel();
            isUpdate = Boolean.FALSE;
            disabled = Boolean.TRUE;
            hidden = Boolean.FALSE;
            List<AttendanceStatus> listAttendanceStatuss = attendanceStatusService.getAllData();

            for (AttendanceStatus attendanceStatus : listAttendanceStatuss) {
                attendanceStatuss.put(attendanceStatus.getStatusKehadrian(), attendanceStatus.getId());
            }

            MapUtil.sortByValue(attendanceStatuss);

            if (param != null) {
                try {
                    PermitClassification permitClassification = permitClassificationService.getEntityByPKWithDetail(Long.parseLong(param.substring(1)));
                    permitClassificationModel.setId(permitClassification.getId());
                    permitClassificationModel.setCode(permitClassification.getCode());
                    permitClassificationModel.setName(permitClassification.getName());
                    permitClassificationModel.setStatus(permitClassification.getStatus());
                    permitClassificationModel.setAttendanceStatusId(permitClassification.getAttendanceStatus().getId());
                    permitClassificationModel.setCalculation(permitClassification.getCalculation());
                    permitClassificationModel.setBasePeriod(permitClassification.getBasePeriod());
                    permitClassificationModel.setAvailibility(permitClassification.getAvailibility());
                    permitClassificationModel.setDateIncreased(permitClassification.getDateIncreased());
                    permitClassificationModel.setQuantity(permitClassification.getQuantity());
                    permitClassificationModel.setLimitByDay(permitClassification.getLimitByDay());
                    permitClassificationModel.setOnePerEmployee(permitClassification.getOnePerEmployee());
                    permitClassificationModel.setMaxPerMonth(permitClassification.getMaxPerMonth());
                    permitClassificationModel.setSalaryCut(permitClassification.getSalaryCut());
                    permitClassificationModel.setAttachmentRequired(permitClassification.getAttachmentRequired());
                    permitClassificationModel.setDescription(permitClassification.getDescription());
                    isUpdate = Boolean.TRUE;
                    disabled = Boolean.FALSE;
                    if (permitClassification.getAvailibility().equals(HRMConstant.AVALILIBILITY_PER_DATE)) {
                        hidden = Boolean.TRUE;
                    }

                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        permitClassificationService = null;
//        permitClassificationModel = null;
        isUpdate = null;
    }

    public PermitClassificationModel getPermitClassificationModel() {
        return permitClassificationModel;
    }

    public void setPermitClassificationModel(PermitClassificationModel permitClassificationModel) {
        this.permitClassificationModel = permitClassificationModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setPermitClassificationService(PermitClassificationService permitClassificationService) {
        this.permitClassificationService = permitClassificationService;
    }

    public void setAttendanceStatusService(AttendanceStatusService attendanceStatusService) {
        this.attendanceStatusService = attendanceStatusService;
    }

    public Map<String, Long> getAttendanceStatuss() {
        return attendanceStatuss;
    }

    public void setAttendanceStatuss(Map<String, Long> attendanceStatuss) {
        this.attendanceStatuss = attendanceStatuss;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public String doBack() {
        return "/protected/working_time/permit_classification_view.htm?faces-redirect=true";
    }

    public String doSave() {

        try {
            PermitClassification permitClassification = getEntityFromViewModel(permitClassificationModel);

            if (isUpdate) {
                permitClassificationService.update(permitClassification);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                permitClassificationService.save(permitClassification);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/permit_classification_detail.htm?faces-redirect=true&execution=e" + permitClassification.getId();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private PermitClassification getEntityFromViewModel(PermitClassificationModel permitClassificationModel) throws BussinessException {
        PermitClassification permitClassification = new PermitClassification();

        if (permitClassificationModel.getId() != null) {
            permitClassification.setId(permitClassificationModel.getId());
        }

        permitClassification.setCode(permitClassificationModel.getCode());
        permitClassification.setName(permitClassificationModel.getName());
        permitClassification.setStatus(permitClassificationModel.getStatus());
        permitClassification.setAttendanceStatus(new AttendanceStatus(permitClassificationModel.getAttendanceStatusId()));
        permitClassification.setCalculation(permitClassificationModel.getCalculation());
        permitClassification.setBasePeriod(permitClassificationModel.getBasePeriod());
        permitClassification.setAvailibility(permitClassificationModel.getAvailibility());
        permitClassification.setDateIncreased(permitClassificationModel.getDateIncreased());
        permitClassification.setQuantity(permitClassificationModel.getQuantity());
        permitClassification.setLimitByDay(permitClassificationModel.getLimitByDay());
        permitClassification.setOnePerEmployee(permitClassificationModel.getOnePerEmployee());
        permitClassification.setMaxPerMonth(permitClassificationModel.getMaxPerMonth());
        permitClassification.setSalaryCut(permitClassificationModel.getSalaryCut());
        permitClassification.setAttachmentRequired(permitClassificationModel.getAttachmentRequired());
        permitClassification.setDescription(permitClassificationModel.getDescription());
        return permitClassification;
    }

    public void availibilityChanged(ValueChangeEvent event) {
        try {

            Integer availibility = Integer.parseInt(String.valueOf(event.getNewValue()));

            if (availibility.equals(HRMConstant.AVAILIBILITY_FULL)) {
                disabled = Boolean.FALSE;
                hidden = Boolean.TRUE;
                minimum = 1;
                maximum = 365;

            }

            if (availibility.equals(HRMConstant.AVALILIBILITY_PER_MONTH)) {
                disabled = Boolean.FALSE;
                hidden = Boolean.TRUE;
                minimum = 1;
                maximum = 22;
            }

            if (availibility.equals(HRMConstant.AVALILIBILITY_PER_DATE)) {
                disabled = Boolean.FALSE;
                hidden = Boolean.FALSE;
                minimum = 1;
                maximum = 365;

            }

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doReset() {
        if (isUpdate) {
            try {
                PermitClassification permitClassification = permitClassificationService.getEntityByPKWithDetail(permitClassificationModel.getId());
                permitClassificationModel.setId(permitClassification.getId());
                permitClassificationModel.setCode(permitClassification.getCode());
                permitClassificationModel.setName(permitClassification.getName());
                permitClassificationModel.setStatus(permitClassification.getStatus());
                permitClassificationModel.setAttendanceStatusId(permitClassification.getAttendanceStatus().getId());
                permitClassificationModel.setCalculation(permitClassification.getCalculation());
                permitClassificationModel.setBasePeriod(permitClassification.getBasePeriod());
                permitClassificationModel.setAvailibility(permitClassification.getAvailibility());
                permitClassificationModel.setDateIncreased(permitClassification.getDateIncreased());
                permitClassificationModel.setQuantity(permitClassification.getQuantity());
                permitClassificationModel.setLimitByDay(permitClassification.getLimitByDay());
                permitClassificationModel.setOnePerEmployee(permitClassification.getOnePerEmployee());
                permitClassificationModel.setMaxPerMonth(permitClassification.getMaxPerMonth());
                permitClassificationModel.setSalaryCut(permitClassification.getSalaryCut());
                permitClassificationModel.setAttachmentRequired(permitClassification.getAttachmentRequired());
                permitClassificationModel.setDescription(permitClassification.getDescription());
                isUpdate = Boolean.TRUE;
                disabled = Boolean.FALSE;
                if (permitClassification.getAvailibility().equals(HRMConstant.AVALILIBILITY_PER_DATE)) {
                    hidden = Boolean.FALSE;
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            permitClassificationModel = new PermitClassificationModel();
            isUpdate = Boolean.FALSE;
            disabled = Boolean.TRUE;
            hidden = Boolean.TRUE;
        }
    }
}
