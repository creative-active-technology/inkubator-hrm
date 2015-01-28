/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.LoanPaymentDetail;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.LoanPaymentDetailService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.LoanDiscountDetailLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.Date;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanDiscountDetailController")
@ViewScoped
public class LoanDiscountDetailController extends BaseController {

    @ManagedProperty(value = "#{loanPaymentDetailService}")
    private LoanPaymentDetailService loanPaymentDetailService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private LoanPaymentDetail selectedLoanPaymentDetail;
    private LazyDataModel<LoanPaymentDetail> lazyDataModel;
    private Date endDatePeriod;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        try {
            String paymentDetail = FacesUtil.getRequestParameter("execution");
            WtPeriode wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            endDatePeriod = wtPeriode.getUntilPeriode();
            selectedLoanPaymentDetail = loanPaymentDetailService.getEntityByPkAndEndWtPeriodActiveWithDetail(Long.valueOf(paymentDetail.substring(1)), endDatePeriod);
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoanDiscountDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        loanPaymentDetailService = null;
        selectedLoanPaymentDetail = null;
        wtPeriodeService = null;
        endDatePeriod = null;
    }

    public String doBack(){
        return "/protected/payroll/loan_discount_view.htm?faces-redirect=true";
    }
    
    public LoanPaymentDetailService getLoanPaymentDetailService() {
        return loanPaymentDetailService;
    }

    public void setLoanPaymentDetailService(LoanPaymentDetailService loanPaymentDetailService) {
        this.loanPaymentDetailService = loanPaymentDetailService;
    }

    public LoanPaymentDetail getSelectedLoanPaymentDetail() {
        return selectedLoanPaymentDetail;
    }

    public void setSelectedLoanPaymentDetail(LoanPaymentDetail selectedLoanPaymentDetail) {
        this.selectedLoanPaymentDetail = selectedLoanPaymentDetail;
    }

    public LazyDataModel<LoanPaymentDetail> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoanDiscountDetailLazyDataModel(loanPaymentDetailService, selectedLoanPaymentDetail.getLoan().getEmpData().getId(), selectedLoanPaymentDetail.getLoan().getId(), endDatePeriod);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoanPaymentDetail> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public Date getEndDatePeriod() {
        return endDatePeriod;
    }

    public void setEndDatePeriod(Date endDatePeriod) {
        this.endDatePeriod = endDatePeriod;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }
    
    
}
