/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.faces.context.ExternalContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.tools.generic.DateTool;
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
import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailHistoryDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.PasswordHistory;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetailHistory;
import com.inkubator.hrm.entity.WtEmpCorrectionAttendanceDetail;
import com.inkubator.hrm.json.util.DateJsonDeserializer;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class NotificationEmailRecruitmentSelectionScheduleMessagesListener extends IServiceImpl implements MessageListener {
    
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
    @Autowired
    private RecruitSelectionApplicantSchedulleDetailHistoryDao recruitSelectionApplicantSchedulleDetailHistoryDao;
    @Autowired
    private RecruitApplicantDao recruitApplicantDao;
    @Autowired
    private EmpDataDao empDataDao;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            LOGGER.info("Begin Send Email Recruitment Selection Schedule");
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
            RecruitSelectionApplicantSchedulleDetailHistory scheduleDetailHistory = (RecruitSelectionApplicantSchedulleDetailHistory) jsonConverter.getClassFromJson(json, RecruitSelectionApplicantSchedulleDetailHistory.class);
            RecruitSelectionApplicantSchedulleDetailHistory scheduleDetailHistoryFromDb = recruitSelectionApplicantSchedulleDetailHistoryDao.getEntiyByPK(scheduleDetailHistory.getId());
            String locale = "in";
            if(scheduleDetailHistoryFromDb.getEmailNotification() == 0){
            	RecruitApplicant applicant = recruitApplicantDao.getEntityByPkWithDetail(scheduleDetailHistoryFromDb.getApplicantId());
            	EmpData empData = empDataDao.getByIdWithBioData(scheduleDetailHistoryFromDb.getPicEmpId());
            	
            	
            	 VelocityTempalteModel vtm = new VelocityTempalteModel();
                 List<String> toSend = new ArrayList<>();
                 List<String> toSentCC = new ArrayList<String>();
                 List<String> toSentBCC = new ArrayList<String>();
                 
                 vtm.setFrom(ownerEmail);
                 toSend.add(scheduleDetailHistoryFromDb.getEmailAddress());
                 toSentCC.add("deni.arianto1606@gmail.com");
                 toSentCC.add("rizal2_dhfr@yahoo.com");
                 toSentCC.add("yosa.mareta@gmail.com");
                 toSentCC.add("guntur@incubatechnology.com");
                 toSentCC.add("rizkykojek@gmail.com");
                 toSentCC.add("amjadicky@gmail.com");
                 vtm.setTo(toSend.toArray(new String[toSend.size()]));
                 vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
                 vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
                 
                 Map maptoSend = new HashMap();
                 if (StringUtils.equals(locale, "en")) {
                     //not yet implemented

                 } else {
                     
                    /* maptoSend.put("ownerAdministrator", ownerAdministrator);
                     maptoSend.put("ownerCompany", ownerCompany);
                     maptoSend.put("applicationUrl", applicationUrl);
                     maptoSend.put("applicationName", applicationName);
                     velocityTemplateSender.sendMail(vtm, maptoSend);*/

                     //update scheduleDetailHistory, set EmailNotification == 1
                     if (scheduleDetailHistoryFromDb.getEmailNotification() == 0) {
                     	scheduleDetailHistoryFromDb.setEmailNotification(1);
                        this.recruitSelectionApplicantSchedulleDetailHistoryDao.update(scheduleDetailHistoryFromDb);
                     }
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
    

	private String getStatusDesc(Integer approvalStatus, String locale) {
        String statusDesc = StringUtils.EMPTY;
        
        if (StringUtils.equals(locale, "en")) {
            if (approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED) {
                statusDesc = "Request is approved";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED) {
                statusDesc = "Request is rejected";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_CANCELLED) {
                statusDesc = "Request is cancelled";
            }
        } else {
            if (approvalStatus == HRMConstant.APPROVAL_STATUS_APPROVED) {
                statusDesc = "Permohonan Disetujui";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_REJECTED) {
                statusDesc = "Permohonan Ditolak";
            } else if (approvalStatus == HRMConstant.APPROVAL_STATUS_CANCELLED) {
                statusDesc = "Permohonan Dibatalkan";
            }
        }
        
        return statusDesc;
    }
    
}
