package com.inkubator.hrm.web.workingtime;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionPermit;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PermitClassificationModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
    private List<ApprovalDefinition> appDefs;
    private ApprovalDefinition selectedAppDef;
    private int indexOfAppDefs;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("execution");
            permitClassificationModel = new PermitClassificationModel();
            isUpdate = Boolean.FALSE;
            disabled = Boolean.TRUE;
            appDefs = new ArrayList<ApprovalDefinition>(); 
            List<AttendanceStatus> listAttendanceStatuss = attendanceStatusService.getAllData();

            for (AttendanceStatus attendanceStatus : listAttendanceStatuss) {
                attendanceStatuss.put(attendanceStatus.getStatusKehadrian(), attendanceStatus.getId());
            }

            MapUtil.sortByValue(attendanceStatuss);

            if (param != null) {
                try {
                    PermitClassification permitClassification = permitClassificationService.getEntityByPkFetchApprovalDefinition(Long.parseLong(param.substring(1)));
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
                    permitClassificationModel.setIsActive(permitClassification.getIsActive());
                    isUpdate = Boolean.TRUE;
                    if (permitClassification.getAvailibility().equals(HRMConstant.PERMIT_AVALILIBILITY_PER_DATE)) {
                    	disabled = Boolean.FALSE;
                    }
                    if (permitClassification.getAvailibility().equals(HRMConstant.PERMIT_AVALILIBILITY_PER_MONTH)) {
                    	disabled = Boolean.TRUE;
                    }
                    Set<ApprovalDefinitionPermit> setAppDefPermits = permitClassification.getApprovalDefinitionPermits();
                    for(ApprovalDefinitionPermit appDefPermit : setAppDefPermits){
                    	appDefs.add(appDefPermit.getApprovalDefinition());
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
        permitClassificationModel = null;
        isUpdate = null;
        attendanceStatusService = null;
        attendanceStatuss = null;
        disabled = null;
        appDefs = null;
        selectedAppDef = null;
        
    }

    public List<ApprovalDefinition> getAppDefs() {
        return appDefs;
    }

    public void setAppDefs(List<ApprovalDefinition> appDefs) {
        this.appDefs = appDefs;
    }

    public ApprovalDefinition getSelectedAppDef() {
        return selectedAppDef;
    }

    public void setSelectedAppDef(ApprovalDefinition selectedAppDef) {
        this.selectedAppDef = selectedAppDef;
    }

    public int getIndexOfAppDefs() {
        return indexOfAppDefs;
    }

    public void setIndexOfAppDefs(int indexOfAppDefs) {
        this.indexOfAppDefs = indexOfAppDefs;
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

    

    public String doBack() {
        return "/protected/working_time/permit_classification_view.htm?faces-redirect=true";
    }

    public String doSave() {

        try {
            PermitClassification permitClassification = getEntityFromViewModel(permitClassificationModel);

            if (isUpdate) {
                permitClassificationService.update(permitClassification, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                permitClassificationService.save(permitClassification, appDefs);
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
        permitClassification.setIsActive(permitClassificationModel.getIsActive());
        return permitClassification;
    }

    public void availibilityChanged(ValueChangeEvent event) {
        try {
            Integer availibility = Integer.parseInt(String.valueOf(event.getNewValue()));

            if (availibility.equals(HRMConstant.PERMIT_AVAILIBILITY_FULL)) {
                disabled = Boolean.TRUE;
                permitClassificationModel.setDateIncreased(null);
            } else if (availibility.equals(HRMConstant.PERMIT_AVALILIBILITY_PER_MONTH)) {
                disabled = Boolean.TRUE;
                permitClassificationModel.setDateIncreased(null);
            } else if (availibility.equals(HRMConstant.PERMIT_AVALILIBILITY_PER_DATE)) {
                disabled = Boolean.FALSE;
                permitClassificationModel.setDateIncreased(1);
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
                permitClassificationModel.setIsActive(permitClassification.getIsActive());
                isUpdate = Boolean.TRUE;
                if (permitClassification.getAvailibility().equals(HRMConstant.PERMIT_AVALILIBILITY_PER_DATE)) {
                	disabled = Boolean.FALSE;
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            permitClassificationModel = new PermitClassificationModel();
            isUpdate = Boolean.FALSE;
        }
    }
    
    public void onChangeName(){
    	if(!appDefs.isEmpty()) {
    		Lambda.forEach(appDefs).setSpecificName(permitClassificationModel.getName());
    	}
    }
    
    /** Start Approval Definition form */
    public void doDeleteAppDef() {
    	appDefs.remove(selectedAppDef);
    }
    
    public void doAddAppDef() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> appDefName = new ArrayList<>();
        appDefName.add(HRMConstant.PERMIT);
        dataToSend.put("appDefName", appDefName);
        List<String> specificName = new ArrayList<>();
        specificName.add(permitClassificationModel.getName());
        dataToSend.put("specificName", specificName);
    	this.showDialogAppDef(dataToSend);
    }
    
    public void doEditAppDef() {
    	indexOfAppDefs = appDefs.indexOf(selectedAppDef);    	
    	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(gson.toJson(selectedAppDef));
        dataToSend.put("jsonAppDef", values);
        this.showDialogAppDef(dataToSend);
    }
    
    private void showDialogAppDef(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 1100);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("appr_def_popup_form", options, params);
    }
    
    public void onDialogReturnAddAppDef(SelectEvent event) {
        ApprovalDefinition appDef = (ApprovalDefinition) event.getObject();
        appDefs.add(appDef);
    }
    
    public void onDialogReturnEditAppDef(SelectEvent event) {
        ApprovalDefinition dataUpdated = (ApprovalDefinition) event.getObject();
        appDefs.remove(indexOfAppDefs);
		appDefs.add(indexOfAppDefs, dataUpdated);
    }    
    /** End Approval Definition form */
}
