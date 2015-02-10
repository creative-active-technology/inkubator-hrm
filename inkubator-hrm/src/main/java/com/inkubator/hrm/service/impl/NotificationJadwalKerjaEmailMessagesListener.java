/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.datacore.service.impl.IServiceImpl;

/**
 *
 * @author rizkykojek
 */
public class NotificationJadwalKerjaEmailMessagesListener extends IServiceImpl implements MessageListener {

    private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;

    @Autowired
    private VelocityTemplateSender velocityTemplateSender;

    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = (JsonObject) gson.fromJson(json, JsonObject.class);
            String locale = jsonObject.get("locale").getAsString();
            String userEmail = jsonObject.get("userEmail").getAsString();
            
            List<String> toSend = new ArrayList<>();
            List<String> toSentCC = new ArrayList<String>();
            List<String> toSentBCC = new ArrayList<String>();
            toSend.add(userEmail);
            toSentCC.add("rizky.maulana@incubatechnology.com");
            toSentCC.add("rizkykojek@gmail.com");
            
            VelocityTempalteModel vtm = new VelocityTempalteModel();
            vtm.setFrom(ownerEmail);            
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
            
            Map maptoSend = new HashMap();
            maptoSend.put("startDate", jsonObject.get("startDate").getAsString());
            maptoSend.put("endDate", jsonObject.get("endDate").getAsString());
            maptoSend.put("userName", jsonObject.get("userName").getAsString());
            maptoSend.put("userNik", jsonObject.get("userNik").getAsString());
            maptoSend.put("ownerAdministrator", ownerAdministrator);
            maptoSend.put("ownerCompany", ownerCompany);
            maptoSend.put("applicationUrl", applicationUrl);
            maptoSend.put("applicationName", applicationName);
            List<String> listSchedule = gson.fromJson(jsonObject.get("listSchedule").getAsString(), new TypeToken<List<String>>(){}.getType());
            maptoSend.put("listSchedule", listSchedule);
            if (StringUtils.equals(locale, "en")) {                
            	vtm.setSubject("Employee Working Schedule");
                vtm.setTemplatePath("email_employee_working_schedule_en.vm");
            } else {
            	vtm.setSubject("Jadwal Kerja Karyawan");
                vtm.setTemplatePath("email_employee_working_schedule.vm");
            }
                
            velocityTemplateSender.sendMail(vtm, maptoSend);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public String getOwnerAdministrator() {
        return ownerAdministrator;
    }

    public void setOwnerAdministrator(String ownerAdministrator) {
        this.ownerAdministrator = ownerAdministrator;
    }

}
