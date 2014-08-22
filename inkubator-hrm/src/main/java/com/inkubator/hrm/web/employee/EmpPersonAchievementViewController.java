/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.web.lazymodel.EmpPersonAchievementLazyDataModel;
import com.inkubator.hrm.web.search.EmpPersonAchievementSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "empPersonAchievementViewController")
@ViewScoped
public class EmpPersonAchievementViewController extends BaseController{
    @ManagedProperty(value = "#{empPersonAchievementService}")
    private EmpPersonAchievementService service;
    private EmpPersonAchievementSearchParameter search;
    private LazyDataModel<EmpPersonAchievement> lazy;
    private EmpPersonAchievement selected;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        search = new EmpPersonAchievementSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit() {
        search=null;
        lazy=null;
        service=null;
        selected=null;
        
    }
    
    public void doSearch() {
        lazy = null;
    }
    
    public void doSelectEntityWithAllRelation() {
        try {
            selected = this.service.getEntityByPkWithEmployee(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
       super.onDialogReturn(event);

    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selected.getId()));
        dataToSend.put("empPersonId", values);
        showDialog(dataToSend);

    }

    public void doAdd() {
        showDialog(null);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("emp_person_achievement_form", options, params);
    }

    public EmpPersonAchievementService getService() {
        return service;
    }

    public void setService(EmpPersonAchievementService service) {
        this.service = service;
    }

    public EmpPersonAchievementSearchParameter getSearch() {
        return search;
    }

    public void setSearch(EmpPersonAchievementSearchParameter search) {
        this.search = search;
    }

    public LazyDataModel<EmpPersonAchievement> getLazy() {
        if (lazy == null) {
            lazy = new EmpPersonAchievementLazyDataModel(search, service);
        }
        return lazy;
    }

    public void setLazy(LazyDataModel<EmpPersonAchievement> lazy) {
        this.lazy = lazy;
    }

    public EmpPersonAchievement getSelected() {
        return selected;
    }

    public void setSelected(EmpPersonAchievement selected) {
        this.selected = selected;
    }
    
    
}
