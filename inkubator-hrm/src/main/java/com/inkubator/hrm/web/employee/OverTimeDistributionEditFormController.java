/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.entity.OverTimeDistributionId;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.OverTimeDistributionService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.web.model.OverTimeDistributionModel;
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
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "overTimeDistributionEditFormController")
@ViewScoped
public class OverTimeDistributionEditFormController extends BaseController{
    @ManagedProperty(value = "#{overTimeDistributionService}")
    private OverTimeDistributionService overTimeDistributionService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    private WtOverTime selected;
    private OverTimeDistributionModel model;
    private Boolean isEdit;

    private Map<String, Long> wtOverTimeDropDown = new HashMap<String, Long>();
    private List<WtOverTime> wtOverTimeList = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String empDataId = FacesUtil.getRequestParameter("empDataId");
        String overTimeId = FacesUtil.getRequestParameter("overTimeId");
        
        model = new OverTimeDistributionModel();
        try {
            wtOverTimeList = wtOverTimeService.getAllData();
            if (empDataId != null && overTimeId != null) {
                
                isEdit = Boolean.TRUE;
                OverTimeDistribution overTimeDistribution = overTimeDistributionService.getEntityByParamWithDetail(Long.parseLong(empDataId), Long.parseLong(overTimeId));
                model.setOldEmpId(overTimeDistribution.getEmpData().getId());
                model.setOldOverTimeId(overTimeDistribution.getWtOverTime().getId());
                model.setEmpDataId(overTimeDistribution.getEmpData().getId());
                model.setEmpData(overTimeDistribution.getEmpData().getNikWithFullName());
                model.setWtOverTimeId(overTimeDistribution.getWtOverTime().getId());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        for (WtOverTime wtOverTime : wtOverTimeList) {
                wtOverTimeDropDown.put(wtOverTime.getName(), wtOverTime.getId());
            }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        overTimeDistributionService = null;
        model = null;
        empDataService = null;
        selected = null;
        isEdit = null;
        wtOverTimeDropDown = null;
        wtOverTimeList = null;

    }
    
    public void doSave() {
        OverTimeDistribution overTimeDistribution = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                overTimeDistributionService.update(overTimeDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                overTimeDistributionService.save(overTimeDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private OverTimeDistribution getEntityFromViewModel(OverTimeDistributionModel model) {
        OverTimeDistribution overTimeDistribution = new OverTimeDistribution();
        overTimeDistribution.setId(new OverTimeDistributionId(model.getOldOverTimeId(), model.getOldEmpId()));
        overTimeDistribution.setEmpData(new EmpData(model.getEmpDataId()));
        overTimeDistribution.setWtOverTime(new WtOverTime(model.getWtOverTimeId()));
        return overTimeDistribution;
    }

    public OverTimeDistributionService getOverTimeDistributionService() {
        return overTimeDistributionService;
    }

    public void setOverTimeDistributionService(OverTimeDistributionService overTimeDistributionService) {
        this.overTimeDistributionService = overTimeDistributionService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public WtOverTimeService getWtOverTimeService() {
        return wtOverTimeService;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public WtOverTime getSelected() {
        return selected;
    }

    public void setSelected(WtOverTime selected) {
        this.selected = selected;
    }

    public OverTimeDistributionModel getModel() {
        return model;
    }

    public void setModel(OverTimeDistributionModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Map<String, Long> getWtOverTimeDropDown() {
        return wtOverTimeDropDown;
    }

    public void setWtOverTimeDropDown(Map<String, Long> wtOverTimeDropDown) {
        this.wtOverTimeDropDown = wtOverTimeDropDown;
    }

    public List<WtOverTime> getWtOverTimeList() {
        return wtOverTimeList;
    }

    public void setWtOverTimeList(List<WtOverTime> wtOverTimeList) {
        this.wtOverTimeList = wtOverTimeList;
    }
    
    
}
