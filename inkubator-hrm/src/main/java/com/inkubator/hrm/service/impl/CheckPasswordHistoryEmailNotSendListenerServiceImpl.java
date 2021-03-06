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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PasswordHistoryDao;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class CheckPasswordHistoryEmailNotSendListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;
    @Autowired
    private PasswordHistoryDao passwordHistoryDao;
    @Autowired
    private VelocityTemplateSender velocityTemplateSender;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog schedulerLog = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            schedulerLog = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            checkPasswordHistoryEmailNotSend();
            schedulerLog.setStatusMessages("FINISH");
            super.doUpdateSchedulerLogSchedulerLog(schedulerLog);
        } catch (Exception ex) {
            if (schedulerLog != null) {
                schedulerLog.setStatusMessages(ex.getMessage());
                super.doUpdateSchedulerLogSchedulerLog(schedulerLog);
            }
            LOGGER.error(ex, ex);
        }
    }

    public void checkPasswordHistoryEmailNotSend() throws Exception {
        List<PasswordHistory> passwordHistories = passwordHistoryDao.getByEmailNotification(HRMConstant.EMAIL_NOTIFICATION_NOT_YET_SEND);
        LOGGER.warn("--------------- TOTAL sending email which will be processed : " + passwordHistories.size());
        for (PasswordHistory passwordHistory : passwordHistories) {
//            try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            String passwordDecrypted = AESUtil.getAESDescription(passwordHistory.getPassword(), HRMConstant.KEYVALUE, HRMConstant.AES_ALGO);
            VelocityTempalteModel vtm = new VelocityTempalteModel();
            PasswordHistory pass = new PasswordHistory();
            BeanUtils.copyProperties(passwordHistory, pass);
            pass.setPassword(passwordDecrypted);
            List<String> toSend = new ArrayList<>();
            List<String> toSentCC = new ArrayList<>();
            vtm.setFrom(ownerEmail);
            toSend.add(passwordHistory.getEmailAddress());
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setSubject("User Notification");
            Map maptoSend = new HashMap();
//                passwordHistory.setPassword(passwordDecrypted);
            if (pass.getLocalId().equals("en")) {
                vtm.setTemplatePath("email_user_confirmation_en.vm");
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_UPDATE)) {
                    maptoSend.put("headerInfo", "Your password in OPTIMA HR has been updated. <br/>");
                }
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_NEW)) {
                    maptoSend.put("headerInfo", "Your account in OPTIMA HR already created.<br/>");
                }

                //Password reset use different email template
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_RESET)) {
                    String headerInfo = "On " + dateFormat.format(passwordHistory.getCreatedOn())
                            + " Time " + timeFormat.format(passwordHistory.getCreatedOn())
                            + " you or someone else has been asked to reset the password for your account at OPTIMA-HR Application. <br/>";
                    maptoSend.put("headerInfo", headerInfo);
                    vtm.setTemplatePath("email_user_password_reset_en.vm");
                    vtm.setSubject("Password Reset at OPTIMA-HR Application");
                }
            }
            if (pass.getLocalId().equals("in")) {
                vtm.setTemplatePath("email_user_confirmation.vm");
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_UPDATE)) {
                    maptoSend.put("headerInfo", "Password Anda pada Aplikasi OPTIMA HR berhasil diubah. <br/>");
                }
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_NEW)) {
                    maptoSend.put("headerInfo", "Anda telah terdaftar di Aplikasi OPTIMA HR <br/>");
                }

                //Password reset use different email template
                if (pass.getRequestType().equalsIgnoreCase(HRMConstant.USER_RESET)) {
                    String headerInfo = "Pada Tanggal " + dateFormat.format(passwordHistory.getCreatedOn())
                            + " Waktu " + timeFormat.format(passwordHistory.getCreatedOn())
                            + " Anda atau seseorang telah meminta diresetkan password untuk account anda di aplikasi OPTIMA-HR. <br/>";
                    maptoSend.put("headerInfo", headerInfo);
                    vtm.setTemplatePath("email_user_password_reset.vm");
                    vtm.setSubject("Reset Password di aplikasi OPTIMA-HR");
                }
            }
            Gson gson = new GsonBuilder().create();
            TypeToken<List<String>> token = new TypeToken<List<String>>() {
            };
            List<String> dataRole = gson.fromJson(passwordHistory.getListRole(), token.getType());
            maptoSend.put("role", dataRole);
            maptoSend.put("user", pass);
            maptoSend.put("ownerAdministrator", ownerAdministrator);
            maptoSend.put("ownerCompany", ownerCompany);
            maptoSend.put("applicationUrl", applicationUrl);
            maptoSend.put("applicationName", applicationName);

            velocityTemplateSender.sendMail(vtm, maptoSend);

//        passwordHistory.setEmailNotification(1);
//        passwordHistory.setPassword(HashingUtils.getHashSHA256(passwordHistory.getPassword()));
//        this.passwordHistoryDao.update(passwordHistory);
            LOGGER.info("Sending Email Success");
            passwordHistory.setEmailNotification(HRMConstant.EMAIL_NOTIFICATION_SEND);
            passwordHistory.setPassword(HashingUtils.getHashSHA256(passwordDecrypted));
            passwordHistoryDao.update(passwordHistory); // update emailNotification and password

//            } catch (Exception e) {
//                LOGGER.error("Error", e);
//            }
        }
    }

    private void passwordHistorySendingEmail(PasswordHistory passwordHistory, String pass) throws Exception {
        VelocityTempalteModel vtm = new VelocityTempalteModel();
        List<String> toSend = new ArrayList<>();
        List<String> toSentCC = new ArrayList<>();
        vtm.setFrom(ownerEmail);
        toSend.add(passwordHistory.getEmailAddress());
        vtm.setTo(toSend.toArray(new String[toSend.size()]));
        vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
        vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
        vtm.setSubject("User Notification");
        Map maptoSend = new HashMap();
        passwordHistory.setPassword(pass);
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

//        passwordHistory.setEmailNotification(1);
//        passwordHistory.setPassword(HashingUtils.getHashSHA256(passwordHistory.getPassword()));
//        this.passwordHistoryDao.update(passwordHistory);
        LOGGER.info("Sending Email Success");
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public void setOwnerAdministrator(String ownerAdministrator) {
        this.ownerAdministrator = ownerAdministrator;
    }

}
