package com.inkubator.hrm.web.payroll;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PayTempAttendanceStatusLazyDataModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.PayTempAttendanceSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "payAttendanceViewController")
@ViewScoped
public class PayAttendanceViewController extends BaseController{
    @ManagedProperty(value = "#{payTempAttendanceStatusService}")
    private PayTempAttendanceStatusService payTempAttendanceStatusService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private PayTempAttendanceSearchParameter payTempAttendanceSearchParameter;
    private LazyDataModel<PayTempAttendanceStatus> lazy;
    private PayTempAttendanceStatus selected;
    private PayTempAttendanceStatusModel payTempAttendanceStatusModel;
    private String jumlahKaryawan;
    private Integer jumlahHariKerja;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            payTempAttendanceSearchParameter = new PayTempAttendanceSearchParameter();
            payTempAttendanceStatusModel = new PayTempAttendanceStatusModel();
            WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            if(wtPeriode != null){
            	payTempAttendanceStatusModel.setStartDate(wtPeriode.getFromPeriode());
            	payTempAttendanceStatusModel.setEndDate(wtPeriode.getUntilPeriode());
                if(null != wtPeriode.getWorkingDays()){
                    jumlahHariKerja = wtPeriode.getWorkingDays();
                }else{
                    jumlahHariKerja = 0;
                }                               
            }else{
                jumlahHariKerja = 0;
            }           
            jumlahKaryawan = String.valueOf(payTempAttendanceStatusService.getTotalResourceTypeByParam(payTempAttendanceSearchParameter, payTempAttendanceStatusModel));           
        } catch (Exception ex) {
            Logger.getLogger(PayAttendanceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        jumlahKaryawan = null;
        lazy = null;
        payTempAttendanceSearchParameter = null;
        payTempAttendanceStatusService = null;
        wtPeriodeService = null;
        selected = null;
        payTempAttendanceStatusModel = null;
        jumlahHariKerja = null;
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            lazy = null;
            jumlahKaryawan = String.valueOf(payTempAttendanceStatusService.getTotalResourceTypeByParam(payTempAttendanceSearchParameter, payTempAttendanceStatusModel));            
        } catch (Exception ex) {
            Logger.getLogger(PayAttendanceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public void onDialogTaReturn(SelectEvent event) {
        try {
           jumlahHariKerja = (Integer)event.getObject();
        } catch (Exception ex) {
            Logger.getLogger(PayAttendanceViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doEditTotalAttendance(){
        
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 150);

        RequestContext.getCurrentInstance().openDialog("total_attendance_edit", options, null);
    }
    public void doSearch() {
        lazy = null;
    }
    
    public void doSynchronized(){
    	try {
			payTempAttendanceStatusService.synchronizedAttendanceStatus();
		} catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    public void doUpload() {
        Map<String, List<String>> dataToSend = new HashMap<>();     
        this.showDialogUpload(dataToSend);
    }

    private void showDialogUpload(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>(); 
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 360);
        RequestContext.getCurrentInstance().openDialog("pay_attendance_upload", options, params);
    }
    
    public int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }
    
    public PayTempAttendanceStatusService getPayTempAttendanceStatusService() {
		return payTempAttendanceStatusService;
	}

	public void setPayTempAttendanceStatusService(
			PayTempAttendanceStatusService payTempAttendanceStatusService) {
		this.payTempAttendanceStatusService = payTempAttendanceStatusService;
	}

	public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    
    public PayTempAttendanceStatusModel getPayTempAttendanceStatusModel() {
		return payTempAttendanceStatusModel;
	}

	public void setPayTempAttendanceStatusModel(
			PayTempAttendanceStatusModel payTempAttendanceStatusModel) {
		this.payTempAttendanceStatusModel = payTempAttendanceStatusModel;
	}

	public LazyDataModel<PayTempAttendanceStatus> getLazy() {
        if(lazy == null){
            lazy = new PayTempAttendanceStatusLazyDataModel(payTempAttendanceStatusService, payTempAttendanceStatusModel, payTempAttendanceSearchParameter);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<PayTempAttendanceStatus> lazy) {
        this.lazy = lazy;
    }

    public PayTempAttendanceStatus getSelected() {
        return selected;
    }

    public void setSelected(PayTempAttendanceStatus selected) {
        this.selected = selected;
    }

    public String getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(String jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public Integer getJumlahHariKerja() {
		return jumlahHariKerja;
	}

    public void setJumlahHariKerja(Integer jumlahHariKerja) {
		this.jumlahHariKerja = jumlahHariKerja;
	}

    public PayTempAttendanceSearchParameter getPayTempAttendanceSearchParameter() {
        return payTempAttendanceSearchParameter;
    }

    public void setPayTempAttendanceSearchParameter(PayTempAttendanceSearchParameter payTempAttendanceSearchParameter) {
        this.payTempAttendanceSearchParameter = payTempAttendanceSearchParameter;
    }

}
