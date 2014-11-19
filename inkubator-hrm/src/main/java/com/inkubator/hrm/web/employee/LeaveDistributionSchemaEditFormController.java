/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.service.LeaveDistributionService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.web.model.LeaveDistributionSchemaModel;
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
@ManagedBean(name = "leaveDistributionSchemaEditFormController")
@ViewScoped
public class LeaveDistributionSchemaEditFormController extends BaseController{
    @ManagedProperty(value = "#{leaveDistributionService}")
    private LeaveDistributionService service;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    private LeaveDistribution selected;
    private LeaveDistributionSchemaModel model;
    private Boolean isEdit;

    private Map<String, Long> leaveSchemeDropDown = new HashMap<String, Long>();
    private List<Leave> leaveList = new ArrayList<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("empDataId");
        model = new LeaveDistributionSchemaModel();
        try {
            leaveList = leaveService.getAllData();
            if (param != null) {
                
                isEdit = Boolean.TRUE;
                LeaveDistribution leaveDistribution = service.getEntityByParamWithDetail(Long.parseLong(param));
                model.setId(leaveDistribution.getId());
                model.setEmpDataId(leaveDistribution.getEmpData().getId());
                model.setEmpData(leaveDistribution.getEmpData().getNikWithFullName());
                model.setLeaveId(leaveDistribution.getLeave().getId());
                model.setBalance(leaveDistribution.getBalance());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        
        for (Leave leave : leaveList) {
                leaveSchemeDropDown.put(leave.getName(), leave.getId());
            }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        leaveService = null;
        model = null;
        service = null;
        selected = null;
        isEdit = null;
        leaveSchemeDropDown = null;
        leaveList = null;

    }
    
    public void doSave() {
        LeaveDistribution leaveDistribution = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(leaveDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(leaveDistribution);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private LeaveDistribution getEntityFromViewModel(LeaveDistributionSchemaModel model) {
        LeaveDistribution leaveDistribution = new LeaveDistribution();
        if (model.getId() != null) {
            leaveDistribution.setId(model.getId());
        }
        leaveDistribution.setEmpData(new EmpData(model.getEmpDataId()));
        leaveDistribution.setLeave(new Leave(model.getLeaveId()));
        leaveDistribution.setBalance(model.getBalance());
        return leaveDistribution;
    }
    
    public LeaveDistributionService getService() {
        return service;
    }

    public void setService(LeaveDistributionService service) {
        this.service = service;
    }

    public LeaveDistribution getSelected() {
        return selected;
    }

    public void setSelected(LeaveDistribution selected) {
        this.selected = selected;
    }

    public LeaveDistributionSchemaModel getModel() {
        return model;
    }

    public void setModel(LeaveDistributionSchemaModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public Map<String, Long> getLeaveSchemeDropDown() {
        return leaveSchemeDropDown;
    }

    public void setLeaveSchemeDropDown(Map<String, Long> leaveSchemeDropDown) {
        this.leaveSchemeDropDown = leaveSchemeDropDown;
    }

    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
    }
    
    
}
