/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.web;

import com.inkubator.sms.gateway.entity.ModemDefinition;
import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.service.ModemDefinitionService;
import com.inkubator.sms.gateway.service.SmsActivityService;
import com.inkubator.sms.gateway.web.model.SmsSendModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.smslib.InboundMessage;
import org.smslib.Service;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "sendingSMSController")
@ViewScoped
public class SendingSMSController extends BaseController {

    private List<SmsActivity> dataTableSMSActivities = new ArrayList<>();
    @ManagedProperty(value = "#{modemDefinitionService}")
    private ModemDefinitionService modemDefinitionService;
    @ManagedProperty(value = "#{smsActivityService}")
    private SmsActivityService smsActivityService;
    private List<ModemDefinition> listModemDefinitions = new ArrayList<>();
    private Map<String, Long> mapOfModem = new HashMap<>();
    private SmsSendModel smsSendModel;

    public void setModemDefinitionService(ModemDefinitionService modemDefinitionService) {
        this.modemDefinitionService = modemDefinitionService;
    }

    @Override
    public void initialization() {
        try {
            listModemDefinitions = this.modemDefinitionService.getAllData();
            for (ModemDefinition definition : listModemDefinitions) {
                mapOfModem.put(definition.getModemId() + "-" + definition.getManufacture(), definition.getId());
            }
            smsSendModel = new SmsSendModel();
            dataTableSMSActivities = smsActivityService.getListBySendDate(new Date());
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    public Map<String, Long> getMapOfModem() {
        return mapOfModem;
    }

    public void setMapOfModem(Map<String, Long> mapOfModem) {
        this.mapOfModem = mapOfModem;
    }

    public void doAdd() {
        smsSendModel.getListPhone().add(smsSendModel.getPhoneNumber());
        if (smsSendModel.getListPhoneAsString() != null) {
            smsSendModel.setListPhoneAsString(smsSendModel.getListPhoneAsString() + ";" + smsSendModel.getPhoneNumber());
        } else {
            smsSendModel.setListPhoneAsString(smsSendModel.getPhoneNumber());
        }

        smsSendModel.setPhoneNumber("");
    }

    public SmsSendModel getSmsSendModel() {
        return smsSendModel;
    }

    public void setSmsSendModel(SmsSendModel smsSendModel) {
        this.smsSendModel = smsSendModel;
    }

    public void doReset() {
        smsSendModel = new SmsSendModel();
    }

    public void doSend() {
        if (Service.getInstance().getServiceStatus().equals(Service.ServiceStatus.STOPPED)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Proses Kirim SMS", "Service belum jalan");
            FacesUtil.getFacesContext().addMessage(null, msg);
        } else {
//            if (Service.getInstance().getGateway(smsSendModel.getModemIdName()) == null) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Proses Kirim SMS", "Modem belum terkoneksi");
//                FacesUtil.getFacesContext().addMessage(null, msg);
//            } else {
            try {
                smsActivityService.sendSms(smsSendModel);
                dataTableSMSActivities = smsActivityService.getListBySendDate(new Date());
                smsSendModel = new SmsSendModel();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Proses Kirim SMS", "SMS berhasil terkirim");
                FacesUtil.getFacesContext().addMessage(null, msg);
            } catch (Exception ex) {
                LOGGER.error(ex, ex);
            }
//            }

        }

    }

    public SmsActivityService getSmsActivityService() {
        return smsActivityService;
    }

    public void setSmsActivityService(SmsActivityService smsActivityService) {
        this.smsActivityService = smsActivityService;
    }

    public List<SmsActivity> getDataTableSMSActivities() {
        return dataTableSMSActivities;
    }

    public void setDataTableSMSActivities(List<SmsActivity> dataTableSMSActivities) {
        this.dataTableSMSActivities = dataTableSMSActivities;
    }

    public void readSMS() {
        System.out.println(" hahahahhhah di coba");
        List<InboundMessage> data = new ArrayList<>();

        try {
            System.out.println("step 1");
            Service.getInstance().readMessages(data, InboundMessage.MessageClasses.ALL);
            System.out.println("step 2");
            for (InboundMessage data1 : data) {
                System.out.println("nilai isi sms " + data1);
                System.out.println(" isi sms " + data1.getText());
                Service.getInstance().deleteMessage(data1);
            }

        } catch (Exception ex) {
            Logger.getLogger(SendingSMSController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
