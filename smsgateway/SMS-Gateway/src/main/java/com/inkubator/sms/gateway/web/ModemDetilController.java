/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.service.ModemDefinitionService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "modemDetilController")
@ViewScoped
public class ModemDetilController extends BaseController {

    @ManagedProperty(value = "#{modemDefinitionService}")
    private ModemDefinitionService modemDefinitionService;
    private ModemDefinition selectedModemDefinition;

    @Override
    @PostConstruct
    public void initialization() {
        String modemId = FacesUtil.getRequestParameter("execution");
        try {
            selectedModemDefinition = this.modemDefinitionService.getEntiyByPK(Long.parseLong(modemId.substring(1)));
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doRedirectModemConfig() {
        return "/protected/modem_config.htm?faces-redirect=true";
    }



    public String doAdd() {
        return "/protected/modem_config_form.htm?faces-redirect=true";
    }

    public ModemDefinition getSelectedModemDefinition() {
        return selectedModemDefinition;
    }

    public void setSelectedModemDefinition(ModemDefinition selectedModemDefinition) {
        this.selectedModemDefinition = selectedModemDefinition;
    }

    public String doEdit() {
        return "/protected/modem_config_form.htm?faces-redirect=true&execution=e" + selectedModemDefinition.getId();
    }

    public String doDeteail() {
        return "/protected/modem_config_detail.htm?faces-redirect=true&execution=e" + selectedModemDefinition.getId();
    }

    public void setModemDefinitionService(ModemDefinitionService modemDefinitionService) {
        this.modemDefinitionService = modemDefinitionService;
    }

    

    
}
