package com.inkubator.hrm.web.payroll;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.entity.PayTempAttendanceStatus;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.service.PayTempAttendanceStatusService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LoanLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PayTempAttendanceStatusLazyDataModel;
import com.inkubator.hrm.web.model.LoanModel;
import com.inkubator.hrm.web.model.PayTempAttendanceStatusModel;
import com.inkubator.hrm.web.search.LoanSearchParameter;
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
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "loanUploadViewController")
@ViewScoped
public class LoanUploadViewController extends BaseController{
    @ManagedProperty(value = "#{loanService}")
    private LoanService loanService;   
    private LoanSearchParameter loanSearchParameter;
    private LazyDataModel<Loan> lazy;
    private Loan selected;
    private LoanModel loanModel;     
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            loanSearchParameter = new LoanSearchParameter();
            loanModel = new LoanModel();                     
            
        } catch (Exception ex) {
            Logger.getLogger(LoanUploadViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {       
        lazy = null;
        loanSearchParameter = null;
        loanService = null;        
        selected = null;
        loanModel = null;
        
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        try {
            lazy = null;                        
        } catch (Exception ex) {
            Logger.getLogger(LoanUploadViewController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void doSelectEntity() {
        try {
            selected = this.loanService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
     public String doDetail() {
        return "/protected/payroll/loan_upload_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
     
     public void doDelete() {
        try {
            loanService.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete BusinessTravel", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete BusinessTravel", ex);
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
        RequestContext.getCurrentInstance().openDialog("loan_upload", options, params);
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

    public LoanService getLoanService() {
        return loanService;
    }

    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    public LoanSearchParameter getLoanSearchParameter() {
        return loanSearchParameter;
    }

    public void setLoanSearchParameter(LoanSearchParameter loanSearchParameter) {
        this.loanSearchParameter = loanSearchParameter;
    }

    public Loan getSelected() {
        return selected;
    }

    public void setSelected(Loan selected) {
        this.selected = selected;
    }

    public LoanModel getLoanModel() {
        return loanModel;
    }

    public void setLoanModel(LoanModel loanModel) {
        this.loanModel = loanModel;
    }
    
    public LazyDataModel<Loan> getLazy() {
        if (lazy == null) {
            lazy = new LoanLazyDataModel(loanSearchParameter, loanService);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<Loan> lazy) {
        this.lazy = lazy;
    }

}
