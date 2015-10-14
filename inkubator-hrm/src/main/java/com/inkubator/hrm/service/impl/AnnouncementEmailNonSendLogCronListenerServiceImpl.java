package com.inkubator.hrm.service.impl;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.AnnouncementJsonModel;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author deni.fahri
 */
public class AnnouncementEmailNonSendLogCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    @Autowired
    protected JmsTemplate jmsTemplateBroadcastAnnouncement;

//    @Override
//    @Scheduled(cron = "${cron.generating.announcement.log}")
//    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    public void generatingAnnouncementLog() throws Exception {
//          LOGGER.warn("Proseccc Generate Annoucment Log Running ===========================================================");
//        Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//        Date planExecutionDate = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
//
//        List<Announcement> announcements = announcementDao.getAllDataValidForGeneratingLog(planExecutionDate);
//        for (Announcement entity : announcements) {
//            AnnouncementJsonModel jsonModel = new AnnouncementJsonModel();
//            jsonModel.setIsGeneratingAnnouncementLog(Boolean.TRUE);
//            jsonModel.setAnnouncementId(entity.getId());
//            jsonModel.setViewModel(entity.getViewModel());
//            jsonModel.setPlanExecutionDate(planExecutionDate);
//            final String json = gson.toJson(jsonModel);
//
//            jmsTemplateBroadcastAnnouncement.send(new MessageCreator() {
//                @Override
//                public Message createMessage(Session session) throws JMSException {
//                    return session.createTextMessage(json);
//                }
//            });
//        }
//    }
//    @Override
//    @Scheduled(cron = "${cron.processing.email.announcement.not.sent}")
//    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
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

//    @Override
//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void executeBatchSendingEmail(AnnouncementLog announcementLog) throws Exception {
//        VelocityTempalteModel vtm = new VelocityTempalteModel();
//        List<String> toSend = new ArrayList<>();
//        List<String> toSentCC = new ArrayList<String>();
//        List<String> toSentBCC = new ArrayList<String>();
//        HrmUser user = hrmUserDao.getByEmpDataId(announcementLog.getEmpData().getId());
//
//        if (user != null && StringUtils.isNotEmpty(user.getEmailAddress())) {
//            vtm.setTemplatePath("email_announcement_broadcast.vm");
//            vtm.setFrom(ownerEmail);
//            vtm.setSubject(announcementLog.getAnnouncement().getSubject());
//            toSend.add(user.getEmailAddress());
//            vtm.setTo(toSend.toArray(new String[toSend.size()]));
//            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
//            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));
//            Map<String, String> maptoSend = new HashMap<String, String>();
//            maptoSend.put("empployeeName", announcementLog.getEmpData().getBioData().getFullName());
//            maptoSend.put("content", announcementLog.getAnnouncement().getAnnouncementContent());
//            maptoSend.put("ownerAdministrator", ownerAdministrator);
//            maptoSend.put("ownerCompany", ownerCompany);
//            maptoSend.put("applicationUrl", applicationUrl);
//            maptoSend.put("applicationName", applicationName);
//            velocityTemplateSender.sendMail(vtm, maptoSend);
//
//            announcementLog.setIsAlreadyExecuted(Boolean.TRUE);
//            announcementLog.setExecutionDate(new Date());
//            announcementLog.setUpdatedBy(HRMConstant.SYSTEM_ADMIN);
//            announcementLog.setUpdatedOn(new Date());
//            announcementLogDao.update(announcementLog);
//        }
//    }


   

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            SchedulerLog schedulerLog = new SchedulerLog();
            schedulerLog.setSchedulerConfig(new SchedulerConfig(Long.parseLong(textMessage.getText())));
            log = super.doSaveSchedulerLogSchedulerLog(schedulerLog);
            processingAllEmailNotSent();
            log.setStatusMessages("FINISH");
            super.doUpdateSchedulerLogSchedulerLog(log);
        } catch (Exception ex) {
            if (log != null) {
                log.setStatusMessages(ex.getMessage());
                super.doUpdateSchedulerLogSchedulerLog(log);
            }
            LOGGER.error(ex, ex);
        }
    }

}
