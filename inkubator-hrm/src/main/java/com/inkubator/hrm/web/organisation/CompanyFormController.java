package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.entity.ApprovalDefinitionLeave;
import com.inkubator.hrm.entity.AttendanceStatus;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.Province;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AttendanceStatusService;
import com.inkubator.hrm.service.CityService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.service.ProvinceService;
import com.inkubator.hrm.web.model.CompanyModel;
import com.inkubator.hrm.web.model.LeaveModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "companyFormController")
@ViewScoped
public class CompanyFormController extends BaseController {

    private CompanyModel model;
    private Boolean isUpdate;
    private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{countryService}")
	private CountryService countryService;
	@ManagedProperty(value = "#{provinceService}")
	private ProvinceService provinceService;
	@ManagedProperty(value = "#{cityService}")
	private CityService cityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isUpdate = Boolean.FALSE;
            isRenderAvailabilityDate = Boolean.FALSE;
            isRenderEndOfPeriodMonth = Boolean.FALSE;

            appDefs = new ArrayList<ApprovalDefinition>(); 
            model = new LeaveModel();
            model.setIsActive(Boolean.TRUE);
            attendanceStatusList = attendanceStatusService.getAllData();

            String param = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(param)) {
                Leave leave = leaveService.getEntityByPkFetchApprovalDefinition(Long.parseLong(param.substring(1)));
                if (leave != null) {
                    getModelFromEntity(leave);
                    Set<ApprovalDefinitionLeave> setAppDefLeaves = leave.getApprovalDefinitionLeaves();
                    for(ApprovalDefinitionLeave appDefLeave : setAppDefLeaves){
                    	appDefs.add(appDefLeave.getApprovalDefinition());
                    }
                    
                    isUpdate = Boolean.TRUE;
                    isRenderAvailabilityDate = StringUtils.equals(model.getAvailability(), HRMConstant.LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE);
                    isRenderEndOfPeriodMonth = StringUtils.equals(model.getEndOfPeriod(), HRMConstant.LEAVE_END_OF_PERIOD_MONTH);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        model = null;
        isUpdate = null;
    }

    

	public void doReset() {
        if (isUpdate) {
            try {
                Leave leave = leaveService.getEntiyByPK(model.getId());
                if (leave != null) {
                    getModelFromEntity(leave);
                    isRenderAvailabilityDate = StringUtils.equals(model.getAvailability(), HRMConstant.LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE);
                    isRenderEndOfPeriodMonth = StringUtils.equals(model.getEndOfPeriod(), HRMConstant.LEAVE_END_OF_PERIOD_MONTH);
                }
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            model = new LeaveModel();
            isRenderAvailabilityDate = Boolean.FALSE;
            isRenderEndOfPeriodMonth = Boolean.FALSE;
        }
    }

    public String doSave() {
        Leave leave = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                leaveService.update(leave, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                leaveService.save(leave, appDefs);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/working_time/leave_detail.htm?faces-redirect=true&execution=e" + leave.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private Leave getEntityFromViewModel(LeaveModel model) {
        Leave leave = new Leave();
        if (model.getId() != null) {
            leave.setId(model.getId());
        }
        leave.setCode(model.getCode());
        leave.setName(model.getName());
        leave.setDescription(model.getDescription());
        leave.setDayType(model.getDayType());
        leave.setCalculation(model.getCalculation());
        leave.setAttendanceStatus(new AttendanceStatus(model.getAttendanceStatusId()));
        leave.setPeriodBase(model.getPeriodBase());
        leave.setAvailability(model.getAvailability());
        leave.setAvailabilityAtSpecificDate(model.getAvailabilityAtSpecificDate());
        leave.setIsTakingLeaveToNextYear(model.getIsTakingLeaveToNextYear());
        leave.setMaxTakingLeaveToNextYear(model.getMaxTakingLeaveToNextYear());
        leave.setBackwardPeriodLimit(model.getBackwardPeriodLimit());
        leave.setIsAllowedMinus(model.getIsAllowedMinus());
        leave.setMaxAllowedMinus(model.getMaxAllowedMinus());
        leave.setEffectiveFrom(model.getEffectiveFrom());
        leave.setSubmittedLimit(model.getSubmittedLimit());
        leave.setIsQuotaReduction(model.getIsQuotaReduction());
        leave.setEndOfPeriod(model.getEndOfPeriod());
        leave.setEndOfPeriodMonth(model.getEndOfPeriodMonth());
        leave.setIsOnlyOncePerEmployee(model.getIsOnlyOncePerEmployee());
        leave.setIsActive(model.getIsActive());
        leave.setQuotaPerPeriod(model.getQuotaPerPeriod());
        return leave;
    }

    private void getModelFromEntity(Leave leave) {
        model.setId(leave.getId());
        model.setCode(leave.getCode());
        model.setName(leave.getName());
        model.setDescription(leave.getDescription());
        model.setDayType(leave.getDayType());
        model.setCalculation(leave.getCalculation());
        model.setAttendanceStatusId(leave.getAttendanceStatus().getId());
        model.setPeriodBase(leave.getPeriodBase());
        model.setAvailability(leave.getAvailability());
        model.setAvailabilityAtSpecificDate(leave.getAvailabilityAtSpecificDate());
        model.setIsTakingLeaveToNextYear(leave.getIsTakingLeaveToNextYear());
        model.setMaxTakingLeaveToNextYear(leave.getMaxTakingLeaveToNextYear());
        model.setBackwardPeriodLimit(leave.getBackwardPeriodLimit());
        model.setIsAllowedMinus(leave.getIsAllowedMinus());
        model.setMaxAllowedMinus(leave.getMaxAllowedMinus());
        model.setEffectiveFrom(leave.getEffectiveFrom());
        model.setSubmittedLimit(leave.getSubmittedLimit());
        model.setIsQuotaReduction(leave.getIsQuotaReduction());
        model.setEndOfPeriod(leave.getEndOfPeriod());
        model.setEndOfPeriodMonth(leave.getEndOfPeriodMonth());
        model.setIsOnlyOncePerEmployee(leave.getIsOnlyOncePerEmployee());
        model.setIsActive(leave.getIsActive());
        model.setQuotaPerPeriod(leave.getQuotaPerPeriod());
    }

    public String doBack() {
        return "/protected/working_time/leave_view.htm?faces-redirect=true";
    }

    public void onChangeAvailability() {
        isRenderAvailabilityDate = StringUtils.equals(model.getAvailability(), HRMConstant.LEAVE_AVAILABILITY_INCREASES_SPECIFIC_DATE);
        if (isRenderAvailabilityDate == Boolean.FALSE) {
            model.setAvailabilityAtSpecificDate(null);
        }
    }

    public void onChangeEndOfPeriod() {
        isRenderEndOfPeriodMonth = StringUtils.equals(model.getEndOfPeriod(), HRMConstant.LEAVE_END_OF_PERIOD_MONTH);
        if (isRenderEndOfPeriodMonth == Boolean.FALSE) {
            model.setEndOfPeriodMonth(null);
        }
    }

    public void onChangeIsTakingLeaveToNextYear() {
        if (model.getIsTakingLeaveToNextYear() == Boolean.FALSE) {
            model.setMaxTakingLeaveToNextYear(null);
            model.setBackwardPeriodLimit(null);
        }
    }

    public void onChangeIsAllowedMinus() {
        if (model.getIsAllowedMinus() == Boolean.FALSE) {
            model.setMaxAllowedMinus(null);
        }
    }
    
    public void onChangeName(){
    	if(!appDefs.isEmpty()) {
    		Lambda.forEach(appDefs).setSpecificName(model.getName());
    	}
    }
    
    /** Start Approval Definition form */
    public void doDeleteAppDef() {
    	appDefs.remove(selectedAppDef);
    }
    
    public void doAddAppDef() {
    	Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> appDefName = new ArrayList<>();
        appDefName.add(HRMConstant.LEAVE);
        dataToSend.put("appDefName", appDefName);
        List<String> specificName = new ArrayList<>();
        specificName.add(model.getName());
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
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("approval_definition_popup_form", options, params);
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
