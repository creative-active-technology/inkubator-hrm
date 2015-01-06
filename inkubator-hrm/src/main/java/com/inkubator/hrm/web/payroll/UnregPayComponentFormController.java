/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.UnregPayComponents;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.UnregPayComponentService;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author deni
 */
@ManagedBean(name = "unregPayComponentFormController")
@ViewScoped
public class UnregPayComponentFormController extends BaseController {

    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{unregPayComponentService}")
    private UnregPayComponentService unregPayComponentService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    private UnregSalaryModel model;
    private Map<String, Long> dropDownPayComponentSalary = new HashMap<String, Long>();
    private List<PaySalaryComponent> listPaySalaryComponent = new ArrayList<>();
    private String unregSalaryId;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
             unregSalaryId = FacesUtil.getRequestParameter("unregSalaryId");
            System.out.println("hahahah" + unregSalaryId);
            model = new UnregSalaryModel();
            if (StringUtils.isNotEmpty(unregSalaryId)) {
                UnregSalary unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId));
                if (unregSalaryId != null) {
                    WtPeriode wtPeriode = wtPeriodeService.getEntiyByPK(unregSalary.getWtPeriode().getId());
                    model = getModelFromEntity(unregSalary, wtPeriode);
                }
            }
            listPaySalaryComponent = paySalaryComponentService.getAllData();
            for (PaySalaryComponent paySalaryComponent : listPaySalaryComponent) {
                dropDownPayComponentSalary.put(paySalaryComponent.getName(), paySalaryComponent.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        unregSalaryService = null;
        unregPayComponentService = null;
        wtPeriodeService = null;
        paySalaryComponentService = null;
        model = null;
        dropDownPayComponentSalary = null;
        listPaySalaryComponent = null;
    }

    public UnregSalaryModel getModelFromEntity(UnregSalary entity, WtPeriode wtPeriode) {
        UnregSalaryModel model = new UnregSalaryModel();
        model.setName(entity.getName());
        model.setYear(wtPeriode.getTahun());
        model.setMonth(wtPeriode.getBulan());
        return model;
    }

    public void doSave() {

        UnregPayComponents unregPayComponents = getEntityFromViewModel(model);
        try {
            unregPayComponentService.save(unregPayComponents);
            RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            cleanAndExit();
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

        private UnregPayComponents getEntityFromViewModel(UnregSalaryModel model) {
        UnregPayComponents unregPayComponents = new UnregPayComponents();
        unregPayComponents.setId(model.getPayComponentId());
        unregPayComponents.setPaySalaryComponent(new PaySalaryComponent(model.getPayComponentId()));
        unregPayComponents.setUnregSalary(new UnregSalary(Long.valueOf(unregSalaryId)));
        return unregPayComponents;
    }
        
    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregPayComponentService getUnregPayComponentService() {
        return unregPayComponentService;
    }

    public void setUnregPayComponentService(UnregPayComponentService unregPayComponentService) {
        this.unregPayComponentService = unregPayComponentService;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public UnregSalaryModel getModel() {
        return model;
    }

    public void setModel(UnregSalaryModel model) {
        this.model = model;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public Map<String, Long> getDropDownPayComponentSalary() {
        return dropDownPayComponentSalary;
    }

    public void setDropDownPayComponentSalary(Map<String, Long> dropDownPayComponentSalary) {
        this.dropDownPayComponentSalary = dropDownPayComponentSalary;
    }

    public List<PaySalaryComponent> getListPaySalaryComponent() {
        return listPaySalaryComponent;
    }

    public void setListPaySalaryComponent(List<PaySalaryComponent> listPaySalaryComponent) {
        this.listPaySalaryComponent = listPaySalaryComponent;
    }

}
