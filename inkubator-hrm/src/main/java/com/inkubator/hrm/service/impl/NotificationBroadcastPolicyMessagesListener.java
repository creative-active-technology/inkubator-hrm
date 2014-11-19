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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.datacore.service.impl.IServiceImpl;

/**
 *
 * @author rizkykojek
 */
public class NotificationBroadcastPolicyMessagesListener extends IServiceImpl implements MessageListener {
    
	@Autowired
    private VelocityTemplateSender velocityTemplateSender;
	private String applicationName;
	private String ownerCompany;
	private String ownerEmail;
	
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {            
            String json = ((TextMessage) message).getText();
            JsonObject jsonObject = (JsonObject) new GsonBuilder().create().fromJson(json, JsonObject.class);
            
            Map<String, String> maptoSend = new HashMap<String, String>();
            maptoSend.put("content", jsonObject.get("content").getAsString());
            maptoSend.put("ownerCompany", ownerCompany);
            maptoSend.put("applicationName", applicationName);
            
            List<String> toSend = new ArrayList<>();
            toSend.add(jsonObject.get("to").getAsString());
            List<String> toSentCC = new ArrayList<String>();
            List<String> toSentBCC = new ArrayList<String>();
            
            VelocityTempalteModel vtm = new VelocityTempalteModel();
            vtm.setFrom(ownerEmail);
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
            vtm.setSubject(jsonObject.get("subject").getAsString());
            vtm.setTemplatePath("email_company_policy_broadcast.vm");
            velocityTemplateSender.sendMail(vtm, maptoSend);            
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
    
}
