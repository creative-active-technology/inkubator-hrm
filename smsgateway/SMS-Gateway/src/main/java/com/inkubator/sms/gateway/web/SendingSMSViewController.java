/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.service.SmsActivityService;
import com.inkubator.sms.gateway.web.lazymodel.SmsActivityLazy;
import com.inkubator.webcore.controller.BaseController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "sendingSMSViewController")
@ViewScoped
public class SendingSMSViewController extends BaseController {

    @ManagedProperty(value = "#{smsActivityService}")
    private SmsActivityService smsActivityService;
    private LazyDataModel<SmsActivity> lazyDataModel;
    private String parameter;

    @Override
    public void initialization() {
        super.initialization();

    }

    public void setSmsActivityService(SmsActivityService smsActivityService) {
        this.smsActivityService = smsActivityService;
    }

    public LazyDataModel<SmsActivity> getLazyDataModel() {
        if (lazyDataModel == null) {
            lazyDataModel = new SmsActivityLazy(parameter, smsActivityService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SmsActivity> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String doAddSms() {
        return "/protected/sending_sms.htm?faces-redirect=true";
    }

    public String doAddSchedule() {
        return "/protected/scheduller_sms.htm?faces-redirect=true";
    }
}
