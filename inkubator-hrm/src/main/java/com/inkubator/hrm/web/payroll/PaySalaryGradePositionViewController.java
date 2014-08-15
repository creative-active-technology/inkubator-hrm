/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.JabatanLazyDataModel;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
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
@ManagedBean(name = "paySalaryGradePositionController")
@ViewScoped
public class PaySalaryGradePositionViewController extends BaseController{
    private JabatanSearchParameter jabatanSearchParameter;
    private LazyDataModel<Jabatan> lazyJabatanDataModel;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private Jabatan selectedJabatan;

    
    @PreDestroy
    private void cleanAndExit() {
        jabatanSearchParameter = null;
        jabatanService = null;
        lazyJabatanDataModel = null;
        selectedJabatan = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        jabatanSearchParameter = new JabatanSearchParameter();

    }
    
    public JabatanSearchParameter getJabatanSearchParameter() {
        return jabatanSearchParameter;
    }

    public void setJabatanSearchParameter(JabatanSearchParameter jabatanSearchParameter) {
        this.jabatanSearchParameter = jabatanSearchParameter;
    }

    public LazyDataModel<Jabatan> getLazyJabatanDataModel() {
        if (lazyJabatanDataModel == null) {
            lazyJabatanDataModel = new JabatanLazyDataModel(jabatanSearchParameter, jabatanService);
        }
        return lazyJabatanDataModel;
    }

    public void setLazyJabatanDataModel(LazyDataModel<Jabatan> lazyJabatanDataModel) {
        this.lazyJabatanDataModel = lazyJabatanDataModel;
    }

    public JabatanService getJabatanService() {
        return jabatanService;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }
    
    public void doSearch() {
        lazyJabatanDataModel = null;
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyJabatanDataModel = null;
        super.onDialogReturn(event);
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 260);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedJabatan.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("pay_salary_grade_position_form", options, dataToSend);
    }
    
    public void doDetail() throws Exception{
        selectedJabatan = this.jabatanService.getByIdWithSalaryGrade(selectedJabatan.getId());
    }
}
