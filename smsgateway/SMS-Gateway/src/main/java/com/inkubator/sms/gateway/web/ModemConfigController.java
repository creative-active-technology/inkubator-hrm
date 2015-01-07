/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.common.notification.model.SerialGateWay;
import com.inkubator.sms.gateway.BussinessException;
import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.service.ModemDefinitionService;
import com.inkubator.sms.gateway.service.impl.ModemManageService;
import com.inkubator.sms.gateway.web.model.ModemDefinitionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.smslib.Service;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "modemConfigController")
@ViewScoped
public class ModemConfigController extends BaseController {
    
    @ManagedProperty(value = "#{modemDefinitionService}")
    private ModemDefinitionService modemDefinitionService;
    @ManagedProperty(value = "#{modemManageService}")
    private ModemManageService modemManageService;
    private List<ModemDefinition> dataModemDefinitions = new ArrayList<>();
    private ModemDefinitionModel modemDefinitionModel;
    private ModemDefinition selectedDefinition;
    private Boolean isEdit;
    
    public void setModemDefinitionService(ModemDefinitionService modemDefinitionService) {
        this.modemDefinitionService = modemDefinitionService;
    }
    
    public List<ModemDefinition> getDataModemDefinitions() {
        return dataModemDefinitions;
    }
    
    public void setDataModemDefinitions(List<ModemDefinition> dataModemDefinitions) {
        this.dataModemDefinitions = dataModemDefinitions;
    }
    
    @Override
    public void initialization() {
        String modemId = FacesUtil.getRequestParameter("execution");
        if (modemId != null) {
            try {
                isEdit = Boolean.TRUE;
                selectedDefinition = this.modemDefinitionService.getEntiyByPK(Long.parseLong(modemId.substring(1)));
                modemDefinitionModel = getModelFromEnity(selectedDefinition);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        } else {
            isEdit = Boolean.FALSE;
            try {
                modemDefinitionModel = new ModemDefinitionModel();
//                dataModemDefinitions = this.modemDefinitionService.getAllData();
                isEdit = Boolean.FALSE;
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
        }
        
    }
    
    public String doRedirectModemConfig() {
        return "/protected/modem_config.htm?faces-redirect=true";
        
    }
    
    public ModemDefinitionModel getModemDefinitionModel() {
        return modemDefinitionModel;
    }
    
    public void setModemDefinitionModel(ModemDefinitionModel modemDefinitionModel) {
        this.modemDefinitionModel = modemDefinitionModel;
    }
    
    public String doSave() {
        String redirect = null;
        try {
            ModemDefinition md = getEntityFromModel(modemDefinitionModel);
            if (isEdit) {
                modemDefinitionService.update(md);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proses Update", "Data berhasil diupdate");
                FacesUtil.getFacesContext().addMessage(null, msg);
                FacesUtil.getExternalContext().getFlash().setKeepMessages(true);
                redirect = "/protected/modem_config_view.htm?faces-redirect=true";
            } else {
                modemDefinitionService.save(md);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proses Simpan", "Data berhasil disimpan");
                FacesUtil.getFacesContext().addMessage(null, msg);
                FacesUtil.getExternalContext().getFlash().setKeepMessages(true);
                redirect = "/protected/modem_config_view.htm?faces-redirect=true";
            }
            dataModemDefinitions = this.modemDefinitionService.getAllData();
            modemDefinitionModel = new ModemDefinitionModel();
        } catch (BussinessException ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proses Simpan", ex.getMessage());
            FacesUtil.getFacesContext().addMessage(null, msg);
            
        } catch (Exception ex) {
            
            LOGGER.error(ex, ex);
        }
        return redirect;
    }
    
    private ModemDefinition getEntityFromModel(ModemDefinitionModel model) {
        ModemDefinition md = new ModemDefinition();
        if (model.getId() != null) {
            md.setId(model.getId());
        }
        md.setComId(model.getComId());
        md.setCurrentValue(model.getCurrentValue());
        md.setManufacture(model.getManufacetur());
        md.setModel(model.getModel());
        md.setModemId(model.getModemId());
        md.setPinNumber(model.getPinNumber());
        md.setPricePerSms(model.getPricePerSms());
        md.setSmscNumber(model.getSmscNumber());
        md.setBaudRate(model.getBaudRate());
        md.setCheckPulsa(model.getCheckPulsa());
        md.setPhoneNumber(model.getPhoneNumber());
        return md;
    }
    
    public ModemDefinitionModel getModelFromEnity(ModemDefinition definition) {
        ModemDefinitionModel definitionModel = new ModemDefinitionModel();
        definitionModel.setId(definition.getId());
        definitionModel.setBaudRate(definition.getBaudRate());
        definitionModel.setComId(definition.getComId());
        definitionModel.setCurrentValue(definition.getCurrentValue());
        definitionModel.setManufacetur(definition.getManufacture());
        definitionModel.setModel(definition.getModel());
        definitionModel.setModemId(definition.getModemId());
        definitionModel.setPinNumber(definition.getPinNumber());
        definitionModel.setPricePerSms(definition.getPricePerSms());
        definitionModel.setCurrentValue(definition.getCurrentValue());
        definitionModel.setSmscNumber(definition.getSmscNumber());
        definitionModel.setPhoneNumber(definition.getPhoneNumber());
        definitionModel.setCheckPulsa(definition.getCheckPulsa());
        return definitionModel;
    }
    
    public ModemDefinition getSelectedDefinition() {
        return selectedDefinition;
    }
    
    public void setSelectedDefinition(ModemDefinition selectedDefinition) {
        this.selectedDefinition = selectedDefinition;
    }
    
    public void doEdit() {
        try {
            isEdit = Boolean.TRUE;
            selectedDefinition = this.modemDefinitionService.getEntiyByPK(selectedDefinition.getId());
            modemDefinitionModel = getModelFromEnity(selectedDefinition);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
    
    public Boolean getIsEdit() {
        return isEdit;
    }
    
    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    public void doDelete() {
        try {
            modemDefinitionService.delete(selectedDefinition);
            dataModemDefinitions = this.modemDefinitionService.getAllData();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
    
    public void doConnect() {
        try {
            if (Service.getInstance().getServiceStatus().equals(Service.ServiceStatus.STOPPED)) {
                dataModemDefinitions = this.modemDefinitionService.getAllData();
                for (ModemDefinition dataModemDefinition : dataModemDefinitions) {
                    if (Service.getInstance().getGateway(dataModemDefinition.getModemId()) == null) {
                        SerialGateWay gateWay = new SerialGateWay();
                        gateWay.setBaudRate(dataModemDefinition.getBaudRate());
                        gateWay.setComPort(dataModemDefinition.getComId());
                        gateWay.setInBound(true);
                        gateWay.setManaufactur(dataModemDefinition.getManufacture());
                        gateWay.setModelName(dataModemDefinition.getModel());
                        gateWay.setModemId(dataModemDefinition.getModemId());
                        gateWay.setOutBound(true);
                        gateWay.setPinNumber(String.valueOf(dataModemDefinition.getPinNumber()));
                        gateWay.setSmscNumber(dataModemDefinition.getSmscNumber());
                        modemManageService.startServiceAndAddGateway(gateWay);
                    }
                }
                Service.getInstance().startService(false);
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
    
    public void setModemManageService(ModemManageService modemManageService) {
        this.modemManageService = modemManageService;
    }
    
}
