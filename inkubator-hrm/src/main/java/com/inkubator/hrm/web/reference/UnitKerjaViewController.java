/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.lazymodel.UnitKerjaLazyDataModel;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
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
@ManagedBean(name = "unitKerjaController")
@ViewScoped
public class UnitKerjaViewController  extends BaseController{
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    private UnitKerjaSearchParameter unitKerjaSearchParameter;
    private LazyDataModel<UnitKerja> lazyDataUnitKerja;
    private UnitKerja selectedUnitKerja;

    public UnitKerjaService getUnitKerjaService() {
        return unitKerjaService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public UnitKerjaSearchParameter getUnitKerjaSearchParameter() {
        return unitKerjaSearchParameter;
    }

    public void setUnitKerjaSearchParameter(UnitKerjaSearchParameter unitKerjaSearchParameter) {
        this.unitKerjaSearchParameter = unitKerjaSearchParameter;
    }

    public LazyDataModel<UnitKerja> getLazyDataUnitKerja() {
        if(lazyDataUnitKerja == null){
            lazyDataUnitKerja = new UnitKerjaLazyDataModel(unitKerjaSearchParameter, unitKerjaService);
        }
        return lazyDataUnitKerja;
    }

    public void setLazyDataUnitKerja(LazyDataModel<UnitKerja> lazyDataUnitKerja) {
        this.lazyDataUnitKerja = lazyDataUnitKerja;
    }

    public UnitKerja getSelectedUnitKerja() {
        return selectedUnitKerja;
    }

    public void setSelectedUnitKerja(UnitKerja selectedUnitKerja) {
        this.selectedUnitKerja = selectedUnitKerja;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        unitKerjaSearchParameter = new UnitKerjaSearchParameter();
    }
    
    public void doSearch() {
        lazyDataUnitKerja = null;
    }
    
    public void doDetail() {
        try {
            selectedUnitKerja = this.unitKerjaService.getEntityByPkWithCity(selectedUnitKerja.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doDelete() {
        try {
            this.unitKerjaService.delete(selectedUnitKerja);
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
        options.put("contentHeight", 320);
        //options.put("closable", false);
        //options.put("height", "auto");
        //options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("unit_kerja_form", options, null);
    }
    
    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 320);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selectedUnitKerja.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("unit_kerja_form", options, dataToSend);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        lazyDataUnitKerja = null;
        super.onDialogReturn(event);
    }
    
    public void onDelete() {
        try {
            selectedUnitKerja = this.unitKerjaService.getEntiyByPK(selectedUnitKerja.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        unitKerjaSearchParameter=null;
        lazyDataUnitKerja=null;
        unitKerjaService=null;
        selectedUnitKerja=null;
    }
    
    public void doSelectEntity() {
        try {
            selectedUnitKerja = this.unitKerjaService.getEntiyByPK(selectedUnitKerja.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
