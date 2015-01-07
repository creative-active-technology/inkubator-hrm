/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.service.ModemDefinitionService;
import com.inkubator.webcore.controller.BaseController;
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
@ManagedBean(name = "modemViewController")
@ViewScoped
public class ModemViewController extends BaseController {

    @ManagedProperty(value = "#{modemDefinitionService}")
    private ModemDefinitionService modemDefinitionService;
    private List<ModemDefinition> dataModemDefinitions = new ArrayList<>();
    private String param;
    private ModemDefinition selectedModemDefinition;

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
    @PostConstruct
    public void initialization() {
        try {
            dataModemDefinitions = this.modemDefinitionService.getAllByFullText(param);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doRedirectModemConfig() {
        return "/protected/modem_config.htm?faces-redirect=true";
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
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

    public void doDelete() {
        try {
            modemDefinitionService.delete(selectedModemDefinition);
            dataModemDefinitions = this.modemDefinitionService.getAllByFullText(param);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public void doSearch() {
        try {
            dataModemDefinitions = this.modemDefinitionService.getAllByFullText(param);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }
}
