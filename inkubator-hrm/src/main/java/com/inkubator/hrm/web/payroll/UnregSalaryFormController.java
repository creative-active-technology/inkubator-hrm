/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.util.MonthAsStringUtil;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.util.HashMapSortByValueUtil;
import com.inkubator.hrm.web.model.UnregSalaryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deni
 */
@ManagedBean(name = "unregSalaryFormController")
@ViewScoped
public class UnregSalaryFormController extends BaseController {

    @ManagedProperty(value = "#{unregSalaryService}")
    private UnregSalaryService unregSalaryService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;
    private UnregSalary selected;
    private UnregSalaryModel model;
    private Boolean isUpdate;
    private HashMap<String, Integer> dropDownMonths;
    private List<WtPeriode> dropDownYears;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            String unregSalaryId = FacesUtil.getRequestParameter("unregSalaryId");
            model = new UnregSalaryModel();
            isUpdate = Boolean.FALSE;
            //dropdown base period bulan
            dropDownMonths = new HashMap<String, Integer>();
            for (int i = 1; i < 13; i++) {
                dropDownMonths.put(MonthAsStringUtil.getMonth(i), i);
            }
            dropDownYears = wtPeriodeService.getAllYears();
            System.out.println(dropDownYears.size());
            dropDownMonths = HashMapSortByValueUtil.sortByValues(dropDownMonths);
            if (StringUtils.isNotEmpty(unregSalaryId)) {
                UnregSalary unregSalary = unregSalaryService.getEntiyByPK(Long.parseLong(unregSalaryId));
                if (unregSalaryId != null) {
                    WtPeriode wtPeriode = wtPeriodeService.getEntiyByPK(unregSalary.getWtPeriode().getId());
                    model = getModelFromEntity(unregSalary, wtPeriode);
                    isUpdate = Boolean.TRUE;
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        unregSalaryService = null;
        selected = null;
        dropDownMonths = null;
        wtPeriodeService = null;
        dropDownYears = null;
    }

    private UnregSalaryModel getModelFromEntity(UnregSalary entity, WtPeriode wtPeriode) {
        UnregSalaryModel model = new UnregSalaryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setYear(wtPeriode.getTahun());
        model.setMonth(wtPeriode.getBulan());
        model.setSalaryDate(entity.getSalaryDate());
        return model;
    }
    
    public void doSave() {
        try {
            if (isUpdate) {
                unregSalaryService.updated(model);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                unregSalaryService.saved(model);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public UnregSalaryService getUnregSalaryService() {
        return unregSalaryService;
    }

    public void setUnregSalaryService(UnregSalaryService unregSalaryService) {
        this.unregSalaryService = unregSalaryService;
    }

    public UnregSalary getSelected() {
        return selected;
    }

    public void setSelected(UnregSalary selected) {
        this.selected = selected;
    }

    public UnregSalaryModel getModel() {
        return model;
    }

    public void setModel(UnregSalaryModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public HashMap<String, Integer> getDropDownMonths() {
        return dropDownMonths;
    }

    public void setDropDownMonths(HashMap<String, Integer> dropDownMonths) {
        this.dropDownMonths = dropDownMonths;
    }

    public WtPeriodeService getWtPeriodeService() {
        return wtPeriodeService;
    }

    public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
        this.wtPeriodeService = wtPeriodeService;
    }

    public List<WtPeriode> getDropDownYears() {
        return dropDownYears;
    }

    public void setDropDownYears(List<WtPeriode> dropDownYears) {
        this.dropDownYears = dropDownYears;
    }

}
