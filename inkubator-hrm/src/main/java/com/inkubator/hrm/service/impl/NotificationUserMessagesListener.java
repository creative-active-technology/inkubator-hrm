/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.common.util.AESUtil;
import com.inkubator.common.util.HashingUtils;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PasswordHistoryDao;
import com.inkubator.hrm.entity.PasswordHistory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class NotificationUserMessagesListener extends IServiceImpl implements MessageListener {

	private String applicationUrl;
	private String applicationName;
	private String ownerEmail;
	private String ownerCompany;
	private String ownerAdministrator;
	
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private VelocityTemplateSender velocityTemplateSender;
    @Autowired
    private PasswordHistoryDao passwordHistoryDao;
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            PasswordHistory passwordHistory = (PasswordHistory) jsonConverter.getClassFromJson(json, PasswordHistory.class);
            passwordHistory.setPassword(AESUtil.getAESDescription(passwordHistory.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO));
            if (Objects.equals(passwordHistory.getEmailNotification(), HRMConstant.EMAIL_NOTIFICATION_NOT_YET_SEND)) {
                VelocityTempalteModel vtm = new VelocityTempalteModel();
                List<String> toSend = new ArrayList<>();
                List<String> toSentCC = new ArrayList<String>();
                vtm.setFrom(ownerEmail);
                toSend.add(passwordHistory.getEmailAddress());
                vtm.setTo(toSend.toArray(new String[toSend.size()]));
                vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
                vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
                vtm.setSubject("User Notification");
                Map maptoSend = new HashMap();
                if (passwordHistory.getLocalId().equals("en")) {
                    vtm.setTemplatePath("email_user_confirmation_en.vm");
                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_UPDATE)) {
                        maptoSend.put("headerInfo", "Your password in OPTIMA HR has been updated. <br/>");
                    }
                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_NEW)) {
                        maptoSend.put("headerInfo", "Your account in OPTIMA HR already created.<br/>");
                    }

                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_RESET)) {
                        maptoSend.put("headerInfo", "Your Password in OPTIMA HR has been reset. <br/>");
                    }
                }
                if (passwordHistory.getLocalId().equals("in")) {
                    vtm.setTemplatePath("email_user_confirmation.vm");
                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_UPDATE)) {
                        maptoSend.put("headerInfo", "Password Anda pada Aplikasi OPTIMA HR berhasil diubah. <br/>");
                    }
                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_NEW)) {
                        maptoSend.put("headerInfo", "Anda telah terdaftar di Aplikasi OPTIMA HR <br/>");
                    }

                    if (passwordHistory.getRequestType().equalsIgnoreCase(HRMConstant.USER_RESET)) {
                        maptoSend.put("headerInfo", "Password Anda pada Aplikasi OPTIMA HR berhasil direset. <br/>");
                    }
                }
                Gson gson = new GsonBuilder().create();
                TypeToken<List<String>> token = new TypeToken<List<String>>() {
                };
                List<String> dataRole = gson.fromJson(passwordHistory.getListRole(), token.getType());
                maptoSend.put("role", dataRole);
                maptoSend.put("user", passwordHistory);
                maptoSend.put("ownerAdministrator", ownerAdministrator);
                maptoSend.put("ownerCompany", ownerCompany);
                maptoSend.put("applicationUrl", applicationUrl);
                maptoSend.put("applicationName", applicationName);
                velocityTemplateSender.sendMail(vtm, maptoSend);
                passwordHistory.setEmailNotification(1);
                passwordHistory.setPassword(HashingUtils.getHashSHA256(passwordHistory.getPassword()));
                this.passwordHistoryDao.update(passwordHistory);
            }
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
