/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.web.lazymodel.CostCenterLazyDataModel;
import com.inkubator.hrm.web.search.CostCenterSearchParameter;
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
 * @author deniarianto
 */
@ManagedBean(name = "costCenterController")
@ViewScoped
public class CostCenterViewController extends BaseController{
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    private CostCenterSearchParameter costCenterSearchParameter;
    private LazyDataModel<CostCenter> lazyDataCostCenter;
    private CostCenter selectedCostCenter;
    List<String> dataIsi2;

    public List<String> getDataIsi2() {
        return dataIsi2;
    }

    public void setDataIsi2(List<String> dataIsi2) {
        this.dataIsi2 = dataIsi2;
    }
    
    
    public CostCenterService getCostCenterService() {
        return costCenterService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

    public CostCenterSearchParameter getCostCenterSearchParameter() {
        return costCenterSearchParameter;
    }

    public void setCostCenterSearchParameter(CostCenterSearchParameter costCenterSearchParameter) {
        this.costCenterSearchParameter = costCenterSearchParameter;
    }

    public LazyDataModel<CostCenter> getLazyDataCostCenter() {
        if(lazyDataCostCenter == null){
            lazyDataCostCenter = new CostCenterLazyDataModel(costCenterSearchParameter, costCenterService);
        }
        return lazyDataCostCenter;
    }

    public void setLazyDataCostCenter(LazyDataModel<CostCenter> lazyDataCostCenter) {
        this.lazyDataCostCenter = lazyDataCostCenter;
    }

    public CostCenter getSelectedCostCenter() {
        return selectedCostCenter;
    }

    public void setSelectedCostCenter(CostCenter selectedCostCenter) {
        this.selectedCostCenter = selectedCostCenter;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        costCenterSearchParameter = new CostCenterSearchParameter();
        
    }
    
    public void doSearch() {
        lazyDataCostCenter = null;
    }
    
    public void doDetail() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 180);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedCostCenter.getId()));
        dataToSend.put("execution", dataIsi);
        RequestContext.getCurrentInstance().openDialog("cost_center_detail", options, dataToSend);
    }
    
    public void doDelete() {
        try {
            this.costCenterService.delete(selectedCostCenter);
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
    
    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 420);
        //options.put("closable", false);
        //options.put("height", "auto");
        //options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("cost_center_form", options, null);
    }
        
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 420);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedCostCenter.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("cost_center_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataCostCenter = null;
       super.onDialogReturn(event);

    }

    public void onDelete() {
        try {
            selectedCostCenter = this.costCenterService.getEntiyByPK(selectedCostCenter.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        costCenterSearchParameter=null;
        lazyDataCostCenter=null;
        costCenterService=null;
        selectedCostCenter=null;
        
    }
    
    public void doSelectEntity() {
        try {
            selectedCostCenter = this.costCenterService.getEntiyByPK(selectedCostCenter.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
