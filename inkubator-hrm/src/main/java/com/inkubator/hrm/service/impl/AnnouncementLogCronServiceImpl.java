package com.inkubator.hrm.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.AnnouncementLogDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.AnnouncementLog;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementLogCronService;
import com.inkubator.hrm.web.model.AnnouncementJsonModel;

/**
 *
 * @author rizkykojek
 */
public class AnnouncementLogCronServiceImpl extends IServiceImpl implements AnnouncementLogCronService {

    private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;

    @Autowired
    private VelocityTemplateSender velocityTemplateSender;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private AnnouncementLogDao announcementLogDao;
    @Autowired
    private AnnouncementDao announcementDao;
    @Autowired
    protected JmsTemplate jmsTemplateBroadcastAnnouncement;

    @Override
    @Scheduled(cron = "${cron.generating.announcement.log}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void generatingAnnouncementLog() throws Exception {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        Date planExecutionDate = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);

        List<Announcement> announcements = announcementDao.getAllDataValidForGeneratingLog(planExecutionDate);
        for (Announcement entity : announcements) {
            AnnouncementJsonModel jsonModel = new AnnouncementJsonModel();
            jsonModel.setIsGeneratingAnnouncementLog(Boolean.TRUE);
            jsonModel.setAnnouncementId(entity.getId());
            jsonModel.setViewModel(entity.getViewModel());
            jsonModel.setPlanExecutionDate(planExecutionDate);
            final String json = gson.toJson(jsonModel);

            jmsTemplateBroadcastAnnouncement.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(json);
                }
            });
        }
    }

    @Override
    @Scheduled(cron = "${cron.processing.email.announcement.not.sent}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void processingAllEmailNotSent() throws Exception {
        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
        AnnouncementJsonModel jsonModel = new AnnouncementJsonModel();
        jsonModel.setIsSendingAllEmailNotSent(Boolean.TRUE);
        final String json = gson.toJson(jsonModel);

        jmsTemplateBroadcastAnnouncement.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(json);
            }
        });
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeBatchSendingEmail(AnnouncementLog announcementLog) throws Exception {
        VelocityTempalteModel vtm = new VelocityTempalteModel();
        List<String> toSend = new ArrayList<>();
        List<String> toSentCC = new ArrayList<String>();
        List<String> toSentBCC = new ArrayList<String>();
        HrmUser user = hrmUserDao.getByEmpDataId(announcementLog.getEmpData().getId());

        if (user != null && StringUtils.isNotEmpty(user.getEmailAddress())) {
            vtm.setTemplatePath("email_announcement_broadcast.vm");
            vtm.setFrom(ownerEmail);
            vtm.setSubject(announcementLog.getAnnouncement().getSubject());
            toSend.add(user.getEmailAddress());
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
            Map<String, String> maptoSend = new HashMap<String, String>();
            maptoSend.put("empployeeName", announcementLog.getEmpData().getBioData().getFullName());
            maptoSend.put("content", announcementLog.getAnnouncement().getAnnouncementContent());
            maptoSend.put("ownerAdministrator", ownerAdministrator);
            maptoSend.put("ownerCompany", ownerCompany);
            maptoSend.put("applicationUrl", applicationUrl);
            maptoSend.put("applicationName", applicationName);
            velocityTemplateSender.sendMail(vtm, maptoSend);

            announcementLog.setIsAlreadyExecuted(Boolean.TRUE);
            announcementLog.setExecutionDate(new Date());
            announcementLog.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
            announcementLog.setUpdatedOn(new Date());
            announcementLogDao.update(announcementLog);
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
