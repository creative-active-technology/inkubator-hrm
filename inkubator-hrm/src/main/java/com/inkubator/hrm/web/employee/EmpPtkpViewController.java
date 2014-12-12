/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.web.lazymodel.EmpDataLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PtkpViewLazyDataModel;
import com.inkubator.hrm.web.model.PtkpViewModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empPtkpViewController")
@ViewScoped
public class EmpPtkpViewController extends BaseController {

    private EmpDataSearchParameter empDataSearchParameter;
    private LazyDataModel<PtkpViewModel> ptkpViewLazyDataModel;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        empDataSearchParameter = new EmpDataSearchParameter();

    }

    @PreDestroy
    public void cleanAndExit() {
        empDataSearchParameter = null;
        ptkpViewLazyDataModel = null;
        empDataService = null;
        selectedEmpData = null;

    }

    public void doEdit() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedEmpData.getId()));
        dataToSend.put("empDataId", dataIsi);
        showDialog(dataToSend);
    }

    public void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 280);
        RequestContext.getCurrentInstance().openDialog("emp_ptkp_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        ptkpViewLazyDataModel = null;
        super.onDialogReturn(event);

    }

    public EmpDataSearchParameter getEmpDataSearchParameter() {
        return empDataSearchParameter;
    }

    public void setEmpDataSearchParameter(EmpDataSearchParameter empDataSearchParameter) {
        this.empDataSearchParameter = empDataSearchParameter;
    }

    public LazyDataModel<PtkpViewModel> getPtkpViewLazyDataModel() {
        if (ptkpViewLazyDataModel == null) {
            ptkpViewLazyDataModel = new PtkpViewLazyDataModel(empDataSearchParameter, empDataService);
        }
        return ptkpViewLazyDataModel;
    }

    public void setPtkpViewLazyDataModel(LazyDataModel<PtkpViewModel> ptkpViewLazyDataModel) {
        this.ptkpViewLazyDataModel = ptkpViewLazyDataModel;
    }

    public void doSearch() {

    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    
}
