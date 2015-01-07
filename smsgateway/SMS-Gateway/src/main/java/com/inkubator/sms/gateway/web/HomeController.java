/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.service.SmsActivityService;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "homeController")
@ViewScoped
public class HomeController extends BaseController {

    @ManagedProperty(value = "#{smsActivityService}")
    private SmsActivityService smsActivityService;
    private List<SmsActivity> dataTableSMSActivities = new ArrayList<>();
    private GChartModel chartModel = null;

    public void setSmsActivityService(SmsActivityService smsActivityService) {
        this.smsActivityService = smsActivityService;
    }

    public List<SmsActivity> getDataTableSMSActivities() {
        return dataTableSMSActivities;
    }

    public void setDataTableSMSActivities(List<SmsActivity> dataTableSMSActivities) {
        this.dataTableSMSActivities = dataTableSMSActivities;
    }

    @Override
    @PostConstruct
    public void initialization() {
        try {
            dataTableSMSActivities = smsActivityService.getListBySendDate(new Date());
            Locale englishLocale = Locale.ENGLISH;

            Map<String, Object> colorAxis = new HashMap<String, Object>();
            colorAxis.put("colors", new String[]{"white", "orange"});

            chartModel = new GChartModelBuilder().setChartType(GChartType.GEO)
                    .addColumns("Country", "Popularity")
                    .addRow(Locale.GERMANY.getDisplayCountry(englishLocale), 1200)
                    .addRow(Locale.FRANCE.getDisplayCountry(englishLocale), 1800)
                    .addRow("Russia", 1800)
                    .addRow(Locale.ITALY.getDisplayCountry(englishLocale), 2000)
                    .addRow(Locale.CHINA.getDisplayCountry(englishLocale), 2200)
                    .addRow(Locale.US.getDisplayCountry(englishLocale), 2500)
                    .addOption("colorAxis", colorAxis)
                    .build();
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public String doRedirectModemConfig() {
        return "/protected/modem_config.htm?faces-redirect=true";

    }

    public String doRedirectSms() {
        return "/protected/sending_sms.htm?faces-redirect=true";
    }

    public String doRedirectSchedullerSms() {
        return "/protected/scheduller_sms.htm?faces-redirect=true";
    }

    public GChartModel getChartModel() {
        return chartModel;
    }

    public void setChartModel(GChartModel chartModel) {
        this.chartModel = chartModel;
    }

}
