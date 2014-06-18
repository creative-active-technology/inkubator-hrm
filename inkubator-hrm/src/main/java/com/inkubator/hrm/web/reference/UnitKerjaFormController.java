/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.model.UnitKerjaModel;
import com.inkubator.hrm.web.search.UnitKerjaSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "unitKerjaFormController")
@ViewScoped
public class UnitKerjaFormController extends BaseController{
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    private UnitKerjaModel unitKerjaModel;
    private UnitKerja selectedUnitKerja;
    private Boolean isEdit;

    public UnitKerjaService getUnitKerjaService() {
        return unitKerjaService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public UnitKerjaModel getUnitKerjaModel() {
        return unitKerjaModel;
    }

    public void setUnitKerjaModel(UnitKerjaModel unitKerjaModel) {
        this.unitKerjaModel = unitKerjaModel;
    }
    

    public UnitKerja getSelectedUnitKerja() {
        return selectedUnitKerja;
    }

    public void setSelectedUnitKerja(UnitKerja selectedUnitKerja) {
        this.selectedUnitKerja = selectedUnitKerja;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        unitKerjaModel = new UnitKerjaModel();
        try {
            if (param != null) {

                isEdit = Boolean.TRUE;
                UnitKerja unitKerja = unitKerjaService.getEntiyByPK(Long.parseLong(param));
                unitKerjaModel.setId(unitKerja.getId());
                unitKerjaModel.setCode(unitKerja.getCode());
                unitKerjaModel.setName(unitKerja.getName());
                unitKerjaModel.setLocation(unitKerja.getLocation());


            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        UnitKerja unitKerja = getEntityFromViewModel(unitKerjaModel);
        try {
            if (isEdit) {
                unitKerjaService.update(unitKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                unitKerjaService.save(unitKerja);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
            //cleanAndExit();

//        cleanAndExit();
    }
    
    private UnitKerja getEntityFromViewModel(UnitKerjaModel unitKerjaModel) {
        UnitKerja unitKerja = new UnitKerja();
        if (unitKerjaModel.getId() != null) {
            unitKerja.setId(unitKerjaModel.getId());
        }
        unitKerja.setCode(unitKerjaModel.getCode());
        unitKerja.setName(unitKerjaModel.getName());
        unitKerja.setLocation(unitKerjaModel.getLocation());
        return unitKerja;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        unitKerjaModel = null;
        unitKerjaService = null;
        selectedUnitKerja = null;
        isEdit = null;

    }
}
