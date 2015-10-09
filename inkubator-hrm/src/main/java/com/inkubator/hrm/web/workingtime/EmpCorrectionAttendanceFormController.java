package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.primefaces.event.RowEditEvent;

import ch.lambdaj.Lambda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendance;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.WtEmpCorrectionAttendanceService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.EmpCorrectionAttendanceDetailModel;
import com.inkubator.hrm.web.model.EmpCorrectionAttendanceModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "empCorrectionAttendanceFormController")
@ViewScoped
public class EmpCorrectionAttendanceFormController extends BaseController {

    private Boolean isAdministator;
    private Boolean isRevised;
    
    private ApprovalActivity currentActivity;
    private ApprovalActivity askingRevisedActivity;

    private EmpCorrectionAttendanceModel model = new EmpCorrectionAttendanceModel();
    private WtEmpCorrectionAttendance empCorrectionAttendance = new WtEmpCorrectionAttendance();
    private List<WtEmpCorrectionAttendanceDetail> listEmpCorrectionAttendanceDetails = new ArrayList<WtEmpCorrectionAttendanceDetail>();    

    @ManagedProperty(value = "#{wtEmpCorrectionAttendanceService}")
    private WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService;
    @ManagedProperty(value = "#{wtWorkingHourService}")
    private WtWorkingHourService wtWorkingHourService;
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{tempProcessReadFingerService}")
    private TempProcessReadFingerService tempProcessReadFingerService;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;

    @PreDestroy
    public void cleanAndExit() {
        model = null;
        empCorrectionAttendance = null;
        listEmpCorrectionAttendanceDetails = null;
        wtEmpCorrectionAttendanceService = null;
        wtWorkingHourService = null;
        isRevised = null;
        askingRevisedActivity = null;
        approvalActivityService = null;
        currentActivity = null;
        isAdministator = null;
        empDataService = null;
        wtGroupWorkingService = null;
        tempProcessReadFingerService = null;
        wtPeriodeService = null;
        transactionCodeficationService = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            //initial
            isAdministator = Lambda.exists(UserInfoUtil.getRoles(), Matchers.containsString(HRMConstant.ADMINISTRATOR_ROLE));
            isRevised = Boolean.FALSE;
            model.setPeriod(wtPeriodeService.getEntityByAbsentTypeActive());
            model.setListWorkingHours(wtWorkingHourService.getAllData());

            //di cek terlebih dahulu, jika datangnya dari proses approval, artinya user akan melakukan revisi data yg masih dalam bentuk json	        
            String appActivityId = FacesUtil.getRequestParameter("activity");
            if (StringUtils.isNotEmpty(appActivityId)) {
                //parsing data from json to object 
                isRevised = Boolean.TRUE;
                currentActivity = approvalActivityService.getEntityByPkWithDetail(Long.parseLong(appActivityId.substring(1)));
                this.getModelFromJson(currentActivity.getPendingData());
                askingRevisedActivity = approvalActivityService.getEntityByActivityNumberAndSequence(currentActivity.getActivityNumber(),
                        currentActivity.getSequence() - 1);

            } else {
            	//set kodefikasi nomor
            	TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.EMP_CORRECTION_ATTENDANCE_KODE);
            	if(!ObjectUtils.equals(transactionCodefication, null)){
            		model.setRequestCode(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
            	}
            	
            	if (!isAdministator) { //jika bukan administrator, langsung di set empData berdasarkan yang login
            		model.setEmpData(HrmUserInfoUtil.getEmpData());
            		this.onChangeEmployee();
            	}
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    

    public String doBack() {
        return "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
    }

    public void doReset() {
    	model.setWorkingGroupName(null);
        model.setStartDate(null);
        model.setEndDate(null);
        model.setListDetail(null);
        model.setEmpData(null);
        model.setListDetail(null);
    }
    
    public void doSearchSchedule(){
    	try {
    		List<EmpCorrectionAttendanceDetailModel> listDetail = new ArrayList<EmpCorrectionAttendanceDetailModel>();
    		List<TempProcessReadFinger> listTempReadFinger = tempProcessReadFingerService.getAllDataByEmpDataIdAndScheduleDate(model.getEmpData().getId(), model.getStartDate(), model.getEndDate());
    		for(TempProcessReadFinger tempProcessReadFinger : listTempReadFinger){
    			EmpCorrectionAttendanceDetailModel detail = new EmpCorrectionAttendanceDetailModel();
    			detail.setAttendanceDate(tempProcessReadFinger.getScheduleDate());
    			detail.setAttendanceIn(tempProcessReadFinger.getFingerIn());
    			detail.setAttendanceOut(tempProcessReadFinger.getFingerOut());
    			detail.setWorkingHour(wtWorkingHourService.getEntiyByPK(tempProcessReadFinger.getWorkingHourId()));
    			listDetail.add(detail);
    		}
    		model.setListDetail(listDetail);
    	} catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doSave() {
        this.getEntityFromModel(model);
        String path = "";
        try {
            String message = wtEmpCorrectionAttendanceService.saveWithApproval(empCorrectionAttendance, listEmpCorrectionAttendanceDetails);
            if (StringUtils.equals(message, "success_need_approval")) {
                path = "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                path = "/protected/working_time/emp_correction_attendance_detail.htm?faces-redirect=true&execution=e" + empCorrectionAttendance.getId();
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return path;

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public String doRevised() {
        this.getEntityFromModel(model);
        String path = "";
        try {
            String message = wtEmpCorrectionAttendanceService.saveWithRevised(empCorrectionAttendance, listEmpCorrectionAttendanceDetails, currentActivity.getId());
            if (StringUtils.equals(message, "success_need_approval")) {
                path = "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                path = "/protected/working_time/emp_correction_attendance_view.htm?faces-redirect=true";
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return path;

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private void getEntityFromModel(EmpCorrectionAttendanceModel m) {
    	empCorrectionAttendance = new WtEmpCorrectionAttendance(); //inisialisasi object
        if (m.getId() != null) {
            empCorrectionAttendance.setId(m.getId());
        }
        empCorrectionAttendance.setEmpData(m.getEmpData());
        empCorrectionAttendance.setStartDate(m.getStartDate());
        empCorrectionAttendance.setEndDate(m.getEndDate());
        empCorrectionAttendance.setRequestCode(m.getRequestCode());
        empCorrectionAttendance.setRequestDate(m.getRequestDate());
        
        listEmpCorrectionAttendanceDetails = new ArrayList<>(); //inisialisasi object
        for(EmpCorrectionAttendanceDetailModel detail : m.getListDetail()){
        	WtEmpCorrectionAttendanceDetail empCorrectionAttendanceDetail = new WtEmpCorrectionAttendanceDetail();
        	empCorrectionAttendanceDetail.setAttendanceDate(detail.getAttendanceDate());
        	empCorrectionAttendanceDetail.setAttendanceIn(detail.getAttendanceIn());
        	empCorrectionAttendanceDetail.setAttendanceOut(detail.getAttendanceOut());
        	empCorrectionAttendanceDetail.setDescription(detail.getDescription());
        	empCorrectionAttendanceDetail.setCorrectionIn(detail.getCorrectionIn());
        	empCorrectionAttendanceDetail.setCorrectionOut(detail.getCorrectionOut());
        	empCorrectionAttendanceDetail.setWorkingHourId(detail.getWorkingHour().getId());
        	empCorrectionAttendanceDetail.setWorkingHourName(detail.getWorkingHour().getName());
        	empCorrectionAttendanceDetail.setScheduleIn(detail.getWorkingHour().getWorkingHourBegin());
        	empCorrectionAttendanceDetail.setScheduleOut(detail.getWorkingHour().getWorkingHourEnd());
        	listEmpCorrectionAttendanceDetails.add(empCorrectionAttendanceDetail);
        }
    }

    private void getModelFromJson(String json) {
        try {
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().registerTypeAdapter(Date.class, new DateJsonDeserializer()).create();
            WtEmpCorrectionAttendance entity = gson.fromJson(json, WtEmpCorrectionAttendance.class);
            model.setRequestCode(entity.getRequestCode());
            model.setRequestDate(entity.getRequestDate());
            model.setStartDate(entity.getStartDate());
            model.setEndDate(entity.getEndDate());
            model.setEmpData(empDataService.getByEmpIdWithDetail(entity.getEmpData().getId()));
            model.setWorkingGroupName(model.getEmpData().getWtGroupWorking().getName());

            List<EmpCorrectionAttendanceDetailModel> listDetail =  new ArrayList<EmpCorrectionAttendanceDetailModel>();
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            List<WtEmpCorrectionAttendanceDetail> empCorrectionAttendanceDetails = gson.fromJson(jsonObject.get("listDetail").getAsString(), new TypeToken<List<WtEmpCorrectionAttendanceDetail>>() {}.getType());
            for(WtEmpCorrectionAttendanceDetail detail : empCorrectionAttendanceDetails){
            	EmpCorrectionAttendanceDetailModel m = new EmpCorrectionAttendanceDetailModel();
            	m.setAttendanceDate(detail.getAttendanceDate());
            	m.setAttendanceIn(detail.getAttendanceIn());
            	m.setAttendanceOut(detail.getAttendanceOut());
            	m.setDescription(detail.getDescription());
            	m.setCorrectionIn(detail.getCorrectionIn());
            	m.setCorrectionOut(detail.getCorrectionOut());            	
            	m.setWorkingHour(wtWorkingHourService.getEntiyByPK(detail.getWorkingHourId()));
            	listDetail.add(m);
            }
            model.setListDetail(listDetail);

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }

    }

    public List<EmpData> doAutoCompleteEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }

    public void onChangeEmployee() {
        try {       
        	WtGroupWorking workingGroup = wtGroupWorkingService.getEntiyByPK(model.getEmpData().getWtGroupWorking().getId());
        	model.setWorkingGroupName(workingGroup.getName());
            model.setStartDate(null);
            model.setEndDate(null);
            model.setListDetail(null);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
    	try { 
	    
	    	EmpCorrectionAttendanceDetailModel detail = (EmpCorrectionAttendanceDetailModel) event.getObject();
	    	WtWorkingHour workingHour = wtWorkingHourService.getEntiyByPK(detail.getWorkingHour().getId()); 
	    	detail.setWorkingHour(workingHour);
    	} catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

	public Boolean getIsAdministator() {
		return isAdministator;
	}

	public void setIsAdministator(Boolean isAdministator) {
		this.isAdministator = isAdministator;
	}

	public Boolean getIsRevised() {
		return isRevised;
	}

	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	public ApprovalActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(ApprovalActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ApprovalActivity getAskingRevisedActivity() {
		return askingRevisedActivity;
	}

	public void setAskingRevisedActivity(ApprovalActivity askingRevisedActivity) {
		this.askingRevisedActivity = askingRevisedActivity;
	}

	public EmpCorrectionAttendanceModel getModel() {
		return model;
	}

	public void setModel(EmpCorrectionAttendanceModel model) {
		this.model = model;
	}

	public WtEmpCorrectionAttendance getEmpCorrectionAttendance() {
		return empCorrectionAttendance;
	}

	public void setEmpCorrectionAttendance(WtEmpCorrectionAttendance empCorrectionAttendance) {
		this.empCorrectionAttendance = empCorrectionAttendance;
	}

	public List<WtEmpCorrectionAttendanceDetail> getListEmpCorrectionAttendanceDetails() {
		return listEmpCorrectionAttendanceDetails;
	}

	public void setListEmpCorrectionAttendanceDetails(List<WtEmpCorrectionAttendanceDetail> listEmpCorrectionAttendanceDetails) {
		this.listEmpCorrectionAttendanceDetails = listEmpCorrectionAttendanceDetails;
	}

	public WtEmpCorrectionAttendanceService getWtEmpCorrectionAttendanceService() {
		return wtEmpCorrectionAttendanceService;
	}

	public void setWtEmpCorrectionAttendanceService(WtEmpCorrectionAttendanceService wtEmpCorrectionAttendanceService) {
		this.wtEmpCorrectionAttendanceService = wtEmpCorrectionAttendanceService;
	}

	public WtWorkingHourService getWtWorkingHourService() {
		return wtWorkingHourService;
	}

	public void setWtWorkingHourService(WtWorkingHourService wtWorkingHourService) {
		this.wtWorkingHourService = wtWorkingHourService;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
	}

	public WtGroupWorkingService getWtGroupWorkingService() {
		return wtGroupWorkingService;
	}

	public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
		this.wtGroupWorkingService = wtGroupWorkingService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}    
	
}
