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

import com.google.gson.JsonObject;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.HrmUser;
import java.util.Objects;

/**
 *
 * @author rizkykojek
 */
public class NotificationApprovalMessagesListener extends IServiceImpl implements MessageListener {

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
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private HrmUserDao hrmUserDao;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(json, JsonObject.class);
            String locale = jsonObject.get("locale").getAsString();
            ApprovalActivity appActivity = approvalActivityDao.getEntiyByPK(jsonObject.get("approvalActivityId").getAsLong());
            HrmUser approverUser = hrmUserDao.getByUserId(appActivity.getApprovedBy());
            HrmUser requesterUser = hrmUserDao.getByUserId(appActivity.getRequestBy());

            VelocityTempalteModel vtm = new VelocityTempalteModel();
            List<String> toSend = new ArrayList<>();
            List<String> toSentCC = new ArrayList<String>();
            List<String> toSentBCC = new ArrayList<String>();

            vtm.setFrom(ownerEmail);
            /*if(appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING) {
             //kirim email ke approver nya jika status waiting
             toSend.add(approverUser.getEmailAddress()); 
             } else {
             //kirim email ke requester nya jika statusnya sudah di approved/rejected. Dan cc email (if any)
             toSend.add(requesterUser.getEmailAddress()); 
             for(JsonElement el:jsonObject.get("ccEmailAddresses").getAsJsonArray()){
             toSentCC.add(el.getAsString());
             }
             }*/
            toSend.add("guntur@incubatechnology.com");
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));

            Map maptoSend = new HashMap();
            if (StringUtils.equals(locale, "en")) {
                vtm.setSubject("Permohonan Perjalanan Dinas");
                //not yet implemented

            } else {
                vtm.setSubject("Permohonan Perjalanan Dinas");
                if (Objects.equals(appActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING)) {
                    //configure email parameter based on approval name
                    switch (appActivity.getApprovalDefinition().getName()) {
                        case HRMConstant.BUSINESS_TRAVEL:
                            vtm.setTemplatePath("email_travel_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("start").getAsString());
                            maptoSend.put("end", jsonObject.get("end").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", jsonObject.get("totalAmount").getAsString());
                            break;

                        case HRMConstant.REIMBURSEMENT:
                            break;

                        case HRMConstant.LOAN:
                            break;

                        default:
                            break;
                    }
                } else if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED)
                        || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
                    //configure email parameter based on approval name	
                    switch (appActivity.getApprovalDefinition().getName()) {
                        case HRMConstant.BUSINESS_TRAVEL:
                            vtm.setTemplatePath("email_travel_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("start").getAsString());
                            maptoSend.put("end", jsonObject.get("end").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", jsonObject.get("totalAmount").getAsString());
                            if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
                                maptoSend.put("statusDesc", "Permohonan Diterima");
                            } else if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
                                maptoSend.put("statusDesc", "Permohonan Ditolak");
                            }
                            break;

                        case HRMConstant.REIMBURSEMENT:
                            break;

                        case HRMConstant.LOAN:
                            break;

                        default:
                            break;
                    }
                }

                maptoSend.put("ownerAdministrator", ownerAdministrator);
                maptoSend.put("ownerCompany", ownerCompany);
                maptoSend.put("applicationUrl", applicationUrl);
                maptoSend.put("applicationName", applicationName);
                velocityTemplateSender.sendMail(vtm, maptoSend);

                //update approval activity, set notification true
                if (appActivity.getNotificationSend() == false) {
                    appActivity.setNotificationSend(true);
                    this.approvalActivityDao.update(appActivity);
                }
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
