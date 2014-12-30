/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author deni
 */
@ManagedBean(name = "UnregSalaryEmpSettingFormController")
@ViewScoped
public class UnregSalaryEmpSettingFormController extends BaseController{
    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private UnregSalaryModel model;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String unregSalaryId = FacesUtil.getRequestParameter("execution");
            model = new UnregSalaryModel();
            if (StringUtils.isNotEmpty(unregSalaryId)) {
                UnregSalary unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId.substring(1)));
                if (unregSalaryId != null) {
                    WtPeriode wtPeriode = wtPeriodeService.getEntiyByPK(unregSalary.getWtPeriode().getId());
                    model = getModelFromEntity(unregSalary, wtPeriode);
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        unregSalaryService = null;
        model = null;
        wtPeriodeService = null;
    }
    
    public UnregSalaryModel getModelFromEntity(UnregSalary entity, WtPeriode wtPeriode){
        UnregSalaryModel model = new UnregSalaryModel();
        model.setName(entity.getName());
        model.setYear(wtPeriode.getTahun());
        model.setMonth(wtPeriode.getBulan());
        return model;
    }

    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregSalaryModel getModel() {
        return model;
    }

    public void setModel(UnregSalaryModel model) {
        this.model = model;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }
    
    
}
