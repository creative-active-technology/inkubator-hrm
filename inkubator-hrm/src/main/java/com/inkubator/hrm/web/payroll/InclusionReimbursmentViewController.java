/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.InclusionReimbursmentService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.InclusionReimbursmentLazyDataModel;
import com.inkubator.hrm.web.model.InclusionReimbursmentModel;
import com.inkubator.webcore.controller.BaseController;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "inclusionReimbursmentViewController")
@ViewScoped
public class InclusionReimbursmentViewController extends BaseController{
    @ManagedProperty(value = "#{inclusionReimbursmentService}")
    private InclusionReimbursmentService inclusionReimbursmentService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private String parameter;
    private LazyDataModel<Reimbursment> lazy;
    private Reimbursment selected;
    private InclusionReimbursmentModel inclusionReimbursmentModel;
    private Integer jumlahKaryawan;
    private BigDecimal jmlNominalReimbursment;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            inclusionReimbursmentModel = new InclusionReimbursmentModel();
            WtPeriode wtPeriode = wtPeriodeService.getEntityByStatusActive();
            if(wtPeriode != null){
                inclusionReimbursmentModel.setStartDate(wtPeriode.getFromPeriode());
                inclusionReimbursmentModel.setEndDate(wtPeriode.getUntilPeriode());
            }
            List<Reimbursment> listReimbursment = inclusionReimbursmentService.getByWtPeriodeWhereComponentPayrollIsActive(inclusionReimbursmentModel);
            jumlahKaryawan = listReimbursment.size();
            jmlNominalReimbursment = new BigDecimal(0);
            for (Reimbursment listReimbursement : listReimbursment) {
                jmlNominalReimbursment = jmlNominalReimbursment.add(listReimbursement.getNominal());
            }
        } catch (Exception ex) {
            Logger.getLogger(InclusionReimbursmentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        jumlahKaryawan = null;
        lazy = null;
        parameter = null;
        inclusionReimbursmentService = null;
        wtPeriodeService = null;
        selected = null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }

    public InclusionReimbursmentService getInclusionReimbursmentService() {
        return inclusionReimbursmentService;
    }

    public void setInclusionReimbursmentService(InclusionReimbursmentService inclusionReimbursmentService) {
        this.inclusionReimbursmentService = inclusionReimbursmentService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public InclusionReimbursmentModel getInclusionReimbursmentModel() {
        return inclusionReimbursmentModel;
    }

    public void setInclusionReimbursmentModel(InclusionReimbursmentModel inclusionReimbursmentModel) {
        this.inclusionReimbursmentModel = inclusionReimbursmentModel;
    }

    public LazyDataModel<Reimbursment> getLazy() {
        if(lazy == null){
            lazy = new InclusionReimbursmentLazyDataModel(inclusionReimbursmentService, inclusionReimbursmentModel, parameter);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<Reimbursment> lazy) {
        this.lazy = lazy;
    }

    public Reimbursment getSelected() {
        return selected;
    }

    public void setSelected(Reimbursment selected) {
        this.selected = selected;
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

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    
    
}
