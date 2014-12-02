/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.PayComponentDataException;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PayComponentDataExceptionService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.PayComponentDataExceptionLazyDataModel;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "payComponentDataExceptionDetailController")
@ViewScoped
public class PayComponentDataExceptionDetailController extends BaseController {

    @ManagedProperty(value = "#{payComponentDataExceptionService}")
    private PayComponentDataExceptionService payComponentDataExceptionService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    private String parameter;
    private LazyDataModel<PayComponentDataException> lazyDataModel;
    private PayComponentDataExceptionModel payComponentDataExceptionModel;
    private BigDecimal jmlNominalComponentException;
    private Integer jumlahKaryawan;
    private String paySalaryComponentId;
    private PayComponentDataException selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        payComponentDataExceptionModel = new PayComponentDataExceptionModel();
        try {
            paySalaryComponentId = FacesUtil.getRequestParameter("execution");
            PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntiyByPK(Long.parseLong(paySalaryComponentId.substring(1)));
            WtPeriode wtPeriode = wtPeriodeService.getEntityByStatusActive();
            List<PayComponentDataException> listPayComponentDataException = payComponentDataExceptionService.getByPaySalaryComponent(Long.parseLong(paySalaryComponentId.substring(1)));
            payComponentDataExceptionModel = getModelFromEntity(paySalaryComponent, listPayComponentDataException.size(), wtPeriode);
            //hitung jumlah nominal
            jmlNominalComponentException = new BigDecimal(0);
            for (PayComponentDataException listPayComponentNominal : listPayComponentDataException) {
                jmlNominalComponentException = jmlNominalComponentException.add(listPayComponentNominal.getNominal());
            }
            jumlahKaryawan = listPayComponentDataException.size();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PayComponentDataExceptionDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    private void cleanAndExit() {
        paySalaryComponentId = null;
        lazyDataModel = null;
        parameter = null;
        payComponentDataExceptionService = null;
        wtPeriodeService = null;
        payComponentDataExceptionModel = null;
        jmlNominalComponentException = null;
        jumlahKaryawan = null;
        selected = null;
    }

    public void hitungNominal() throws Exception{
        List<PayComponentDataException> listPayComponentDataException = payComponentDataExceptionService.getByPaySalaryComponent(Long.parseLong(paySalaryComponentId.substring(1)));
        for (PayComponentDataException listPayComponentNominal : listPayComponentDataException) {
            jmlNominalComponentException = jmlNominalComponentException.add(listPayComponentNominal.getNominal());
        }
    }
    
    private PayComponentDataExceptionModel getModelFromEntity(PaySalaryComponent paySalaryComponent, Integer jumlahKaryawan, WtPeriode wtPeriod) {
        PayComponentDataExceptionModel model = new PayComponentDataExceptionModel();
        model.setComponentName(paySalaryComponent.getName());
        model.setJumlahKaryawan(jumlahKaryawan);
        if (wtPeriod != null) {
            model.setStartDate(wtPeriod.getFromPeriode());
            model.setEndDate(wtPeriod.getUntilPeriode());
        }
        return model;
    }

    public void doSearch() {
        lazyDataModel = null;
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataModel = null;
        List<PayComponentDataException> listPayComponentDataException;
        try {
            listPayComponentDataException = payComponentDataExceptionService.getByPaySalaryComponent(Long.parseLong(paySalaryComponentId.substring(1)));
            jmlNominalComponentException = new BigDecimal(0);
            for (PayComponentDataException listPayComponentNominal : listPayComponentDataException) {
                jmlNominalComponentException = jmlNominalComponentException.add(listPayComponentNominal.getNominal());
            }
            jumlahKaryawan = listPayComponentDataException.size();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PayComponentDataExceptionDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.onDialogReturn(event);
    }
    
    public void doAdd() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(paySalaryComponentId));
        dataToSend.put("paySalaryComponentId", dataIsi);
        showDialog(dataToSend);
    }
    
    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> paySalaryComponenId = new ArrayList<>();
        List<String> payComponentExceptionId = new ArrayList<>();
        paySalaryComponenId.add(String.valueOf(paySalaryComponentId));
        payComponentExceptionId.add(String.valueOf(selected.getId()));
        dataToSend.put("paySalaryComponentId", paySalaryComponenId);
        dataToSend.put("payComponentExceptionId", payComponentExceptionId);
        showDialog(dataToSend);
    }
    
    public void showDialog(Map<String, List<String>> params){
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("pay_component_exception_form", options, params);
    }

    public PayComponentDataExceptionService getPayComponentDataExceptionService() {
        return payComponentDataExceptionService;
    }

    public void setPayComponentDataExceptionService(PayComponentDataExceptionService payComponentDataExceptionService) {
        this.payComponentDataExceptionService = payComponentDataExceptionService;
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

    public LazyDataModel<PayComponentDataException> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new PayComponentDataExceptionLazyDataModel(payComponentDataExceptionService, payComponentDataExceptionModel, parameter);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<PayComponentDataException> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public PayComponentDataExceptionModel getPayComponentDataExceptionModel() {
        return payComponentDataExceptionModel;
    }

    public void setPayComponentDataExceptionModel(PayComponentDataExceptionModel payComponentDataExceptionModel) {
        this.payComponentDataExceptionModel = payComponentDataExceptionModel;
    }

    public BigDecimal getJmlNominalComponentException() {
        return jmlNominalComponentException;
    }

    public void setJmlNominalComponentException(BigDecimal jmlNominalComponentException) {
        this.jmlNominalComponentException = jmlNominalComponentException;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public PayComponentDataException getSelected() {
        return selected;
    }

    public void setSelected(PayComponentDataException selected) {
        this.selected = selected;
    }

    
}
