/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.ImplementationOfOverTime;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.entity.TransactionCodefication;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.ImplementationOfOverTimeService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.TransactionCodeficationService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.util.KodefikasiUtil;
import com.inkubator.hrm.web.model.ImplementationOfOverTimeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "implementationOfOverTimeFormController")
@ViewScoped
public class ImplementationOfOverTimeFormController extends BaseController {
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{implementationOfOverTimeService}")
    private ImplementationOfOverTimeService implementationOfOverTimeService;
    @ManagedProperty(value = "#{tempJadwalKaryawanService}")
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    @ManagedProperty(value = "#{transactionCodeficationService}")
    private TransactionCodeficationService transactionCodeficationService;
    
    private Boolean isUpdate;
    private List<WtOverTime> listWtOverTime = new ArrayList<>();
    private ImplementationOfOverTimeModel model;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try{
            String implementOfOTId = FacesUtil.getRequestParameter("implementOfOTId");
            model = new ImplementationOfOverTimeModel();
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(implementOfOTId)) {
                ImplementationOfOverTime implementationOfOverTime = implementationOfOverTimeService.getEntityByPkWithDetail(Long.parseLong(implementOfOTId));
                if(implementOfOTId != null){
                    model = getModelFromEntity(implementationOfOverTime);
                    listWtOverTime = wtOverTimeService.getAllDataByEmpDataIdAndIsActive(model.getEmpData().getId(), Boolean.TRUE);
                    isUpdate = Boolean.TRUE;
                }
			} else {
				TransactionCodefication transactionCodefication = transactionCodeficationService.getEntityByModulCode(HRMConstant.OVERTIME_KODE);
				if (!ObjectUtils.equals(transactionCodefication, null)) {
					model.setImplementationNumber(KodefikasiUtil.getKodefikasiOnlyPattern(transactionCodefication.getCode()));
				}
			}
        }catch (Exception e){
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        listWtOverTime = null;
        isUpdate = null;
        wtOverTimeService = null;
        model = null;
        empDataService = null;
        tempJadwalKaryawanService = null;
        implementationOfOverTimeService = null;
        transactionCodeficationService = null;
    }
    
    public List<EmpData> doAutoCompletEmployee(String param) {
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
	    	listWtOverTime = wtOverTimeService.getAllDataByEmpDataIdAndIsActive(model.getEmpData().getId(), Boolean.TRUE);
	    	this.onChangeImplDate();
    	} catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    public void onChangeImplDate() throws Exception{
    	if(model.getEmpData() != null && model.getImplementationDate() != null) {
	        TempJadwalKaryawan jadwalKaryawan = tempJadwalKaryawanService.getEntityByEmpDataIdAndTanggalWaktuKerja(model.getEmpData().getId(), model.getImplementationDate());
	        if(jadwalKaryawan != null && jadwalKaryawan.getWtWorkingHour().getIsManageOvertime()){
	        	model.setWtOverTimeId(jadwalKaryawan.getWtWorkingHour().getWtOverTime().getId());
                model.setStartTime(jadwalKaryawan.getWtWorkingHour().getStartOvertime());
                model.setEndTime(jadwalKaryawan.getWtWorkingHour().getEndOvertime());
                model.setCalculationMethod(0);
                model = this.setRelativeHourAndMinute(model);
	        }else{
	            model.setStartTime(null);
	            model.setEndTime(null);
	            model.setWtOverTimeId(null);
	            model.setRelativeHour(null);
	            model.setRelativeMinute(null);
	        }    
    	}        
    }
    
    private ImplementationOfOverTimeModel getModelFromEntity(ImplementationOfOverTime entity) {
        ImplementationOfOverTimeModel model = new ImplementationOfOverTimeModel();
        model.setId(entity.getId());
        if (entity.getEmpData() != null) {
            model.setEmpData(entity.getEmpData());
        }
        model.setWtOverTimeId(entity.getWtOverTime().getId());
        model.setStartTime(entity.getStartTime());
        model.setEndTime(entity.getEndTime());
        model.setImplementationDate(entity.getImplementationDate());
        model.setImplementationNumber(entity.getCode());
        model.setDescription(entity.getDescription());
        model.setRelativeHour(entity.getRelativeHour());
        model.setRelativeMinute(entity.getRelativeMinute());
        if(model.getStartTime() != null  && model.getEndTime() != null){
        	model.setCalculationMethod(0);
        } else {
        	model.setCalculationMethod(1);
        }
        return model;
    }

    private ImplementationOfOverTime getEntityFromViewModel(ImplementationOfOverTimeModel model) {
        ImplementationOfOverTime implementationOfOT = new ImplementationOfOverTime();
        if (model.getId() != null) {
            implementationOfOT.setId(model.getId());
        }
        implementationOfOT.setEmpData(model.getEmpData());
        implementationOfOT.setWtOverTime(new WtOverTime(model.getWtOverTimeId()));        
        implementationOfOT.setImplementationDate(model.getImplementationDate());
        implementationOfOT.setCode(model.getImplementationNumber());
        implementationOfOT.setDescription(model.getDescription());
        
        if(model.getCalculationMethod() == 0){        	
        	model = this.setRelativeHourAndMinute(model);   
        	
        	implementationOfOT.setRelativeHour(model.getRelativeHour());
        	implementationOfOT.setRelativeMinute(model.getRelativeMinute());
        	implementationOfOT.setStartTime(model.getStartTime());
            implementationOfOT.setEndTime(model.getEndTime());
            
        } else {
        	implementationOfOT.setRelativeHour(model.getRelativeHour());
        	implementationOfOT.setRelativeMinute(model.getRelativeMinute());
        	implementationOfOT.setStartTime(null);
        	implementationOfOT.setEndTime(null);
        }
        
        return implementationOfOT;
    }
    
    public void doResetData() throws Exception {
        if(isUpdate){
        	ImplementationOfOverTime implementationOfOverTime = implementationOfOverTimeService.getEntityByPkWithDetail(model.getId());
    		model = getModelFromEntity(implementationOfOverTime);

    	}else{
            model.setEmpData(null);
            model.setImplementationDate(null);
            model.setStartTime(null);
            model.setWtOverTimeId(null);
            model.setStartTime(null);
            model.setEndTime(null);
            model.setRelativeHour(null);
            model.setRelativeMinute(null);
            model.setDescription(null);
            model.setCalculationMethod(null);
    	}
    }
    
    public void doSaved() {
        ImplementationOfOverTime implementationOfOT = getEntityFromViewModel(model);
        
        try {        	
            if (isUpdate) {
                implementationOfOverTimeService.update(implementationOfOT);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
            	String message = implementationOfOverTimeService.save(implementationOfOT, false);
            	if(StringUtils.equals(message, "success_need_approval")){
            		RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION_WITH_APPROVAL);                        
            	} else {                        
            		RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            	}
            }
            
        } catch (BussinessException ex) { 
        	ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.getString("global.error"), ex.getMessage());
            FacesUtil.getFacesContext().addMessage(null, msg); 
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private ImplementationOfOverTimeModel setRelativeHourAndMinute(ImplementationOfOverTimeModel mo){
    	DateTime startDT = new DateTime(mo.getStartTime());
    	DateTime endDT = new DateTime(mo.getEndTime());
    	
    	Integer relativeHour = Hours.hoursBetween(startDT, endDT).getHours();
    	Integer relativeMinute = Minutes.minutesBetween(startDT.plusHours(relativeHour), endDT).getMinutes();
    	
    	mo.setRelativeHour(relativeHour);
    	mo.setRelativeMinute(relativeMinute);
    	
    	return mo;
    }    
    
    public TransactionCodeficationService getTransactionCodeficationService() {
		return transactionCodeficationService;
	}

	public void setTransactionCodeficationService(TransactionCodeficationService transactionCodeficationService) {
		this.transactionCodeficationService = transactionCodeficationService;
	}

	public WtOverTimeService getWtOverTimeService() {
        return wtOverTimeService;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public ImplementationOfOverTimeService getImplementationOfOverTimeService() {
        return implementationOfOverTimeService;
    }

    public void setImplementationOfOverTimeService(ImplementationOfOverTimeService implementationOfOverTimeService) {
        this.implementationOfOverTimeService = implementationOfOverTimeService;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public List<WtOverTime> getListWtOverTime() {
        return listWtOverTime;
    }

    public void setListWtOverTime(List<WtOverTime> listWtOverTime) {
        this.listWtOverTime = listWtOverTime;
    }

    public ImplementationOfOverTimeModel getModel() {
        return model;
    }

    public void setModel(ImplementationOfOverTimeModel model) {
        this.model = model;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public TempJadwalKaryawanService getTempJadwalKaryawanService() {
        return tempJadwalKaryawanService;
    }

    public void setTempJadwalKaryawanService(TempJadwalKaryawanService tempJadwalKaryawanService) {
        this.tempJadwalKaryawanService = tempJadwalKaryawanService;
    }    
    
}
