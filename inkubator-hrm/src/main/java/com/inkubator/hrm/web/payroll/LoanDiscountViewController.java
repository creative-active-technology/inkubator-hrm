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
import com.inkubator.hrm.web.lazymodel.LoanDiscountLazyDataModel;
import com.inkubator.hrm.web.model.LoanPaymentDetailModel;
import com.inkubator.webcore.controller.BaseController;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author deni
 */
@ManagedBean(name = "loanDiscountViewController")
@ViewScoped
public class LoanDiscountViewController extends BaseController {

    @ManagedProperty(value = "#{loanPaymentDetailService}")
    private LoanPaymentDetailService loanPaymentDetailService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private String parameter;
    private LazyDataModel<LoanPaymentDetail> lazyDataModel;
    private LoanPaymentDetail selected;
    private LoanPaymentDetailModel loanPaymentDetailModel;
    private Integer jumlahKaryawan;
    private BigDecimal jmlNominalReimbursment;
    private Double jmlNominalLoan;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();

        try {
            loanPaymentDetailModel = new LoanPaymentDetailModel();
            WtPeriode wtPeriode;
            wtPeriode = wtPeriodeService.getEntityByPayrollTypeActive();
            if (wtPeriode != null) {
                loanPaymentDetailModel.setEndDataPeriod(wtPeriode.getUntilPeriode());
                loanPaymentDetailModel.setStartDatePeriod(wtPeriode.getFromPeriode());
            }
            wtPeriode = wtPeriodeService.getEntityByAbsentTypeActive();
            if (wtPeriode != null) {
                loanPaymentDetailModel.setStartDateAbsen(wtPeriode.getFromPeriode());
                loanPaymentDetailModel.setEndDateAbsen(wtPeriode.getUntilPeriode());
            }
            List<LoanPaymentDetail> listLoanPaymentDetail = loanPaymentDetailService.getByWtPeriodeWhereComponentPayrollIsActive(loanPaymentDetailModel);
            jumlahKaryawan = listLoanPaymentDetail.size();
            jmlNominalLoan = 0.0;
            for (LoanPaymentDetail loanPaymentDetail : listLoanPaymentDetail) {
                jmlNominalLoan = jmlNominalLoan + loanPaymentDetail.getTotalPayment();
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoanDiscountViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        jumlahKaryawan = null;
        lazyDataModel = null;
        parameter = null;
        loanPaymentDetailService = null;
        wtPeriodeService = null;
        selected = null;
        loanPaymentDetailModel = null;
        jmlNominalReimbursment = null;
        jmlNominalLoan = null;
    }

    public String doDetail() {
        return "/protected/payroll/loan_discount_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public LoanPaymentDetailService getLoanPaymentDetailService() {
        return loanPaymentDetailService;
    }

    public void setLoanPaymentDetailService(LoanPaymentDetailService loanPaymentDetailService) {
        this.loanPaymentDetailService = loanPaymentDetailService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<LoanPaymentDetail> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new LoanDiscountLazyDataModel(loanPaymentDetailService, loanPaymentDetailModel, parameter);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<LoanPaymentDetail> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public LoanPaymentDetail getSelected() {
        return selected;
    }

    public void setSelected(LoanPaymentDetail selected) {
        this.selected = selected;
    }

    public LoanPaymentDetailModel getLoanPaymentDetailModel() {
        return loanPaymentDetailModel;
    }

    public void setLoanPaymentDetailModel(LoanPaymentDetailModel loanPaymentDetailModel) {
        this.loanPaymentDetailModel = loanPaymentDetailModel;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public BigDecimal getJmlNominalReimbursment() {
        return jmlNominalReimbursment;
    }

    public void setJmlNominalReimbursment(BigDecimal jmlNominalReimbursment) {
        this.jmlNominalReimbursment = jmlNominalReimbursment;
    }

    public Double getJmlNominalLoan() {
        return jmlNominalLoan;
    }

    public void setJmlNominalLoan(Double jmlNominalLoan) {
        this.jmlNominalLoan = jmlNominalLoan;
    }

}
