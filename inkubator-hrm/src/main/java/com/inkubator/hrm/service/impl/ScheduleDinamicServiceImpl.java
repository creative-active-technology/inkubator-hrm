/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.CommonUtilConstant;
import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.SchedulerConfigDao;
import com.inkubator.hrm.dao.SchedulerLogDao;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.service.ScheduleDinamicService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
public class ScheduleDinamicServiceImpl extends IServiceImpl implements ScheduleDinamicService {

    @Autowired
    private SchedulerConfigDao schedulerConfigDao;
    @Autowired
    private JmsTemplate jmsTemplateUserPassNotif;
    @Autowired
    private JmsTemplate jmsTemplateApprovalNotif;
    @Autowired
    private JmsTemplate jmsTemplateDelAccessHis;
    @Autowired
    private JmsTemplate jmsTemplateDelLogHis;
    @Autowired
    private JmsTemplate jmsTemplateHolidayUpdate;
    @Autowired
    private JmsTemplate jmsTemplateScheduleWork;
    @Autowired
    private JmsTemplate jmsTemplateDelTempScheduleWork;
    @Autowired
    private JmsTemplate jmsTemplateAutoApprovalMat;
    @Autowired
    private JmsTemplate jmsTemplateAddBalanceLeave;
    @Autowired
    private JmsTemplate jmsTemplateAddBalancePermit;
    @Autowired
    private JmsTemplate jmsTemplateCompanyPolicySend;
    @Autowired
    private JmsTemplate jmsTemplateAttendaceCalculatte;
    @Autowired
    private JmsTemplate jmsTemplateAnnoucmentGeneratingLog;
    @Autowired
    private JmsTemplate jmsTemplateAnnoucmentSendingNotif;
    @Autowired
    private JmsTemplate jmsTemplateDeleteMonitoringLog;
    @Autowired
    private JmsTemplate jmsTemplatePasswordComplexity;
    @Autowired
    private JmsTemplate jmsTemplateRecruitmetConfigEmp;
    @Autowired
    protected SchedulerLogDao schedulerLogDao;
//    @Override
//    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void initMethode() throws Exception {
//        List<SchedulerConfig> dataToUpdateLastExecution = this.schedulerConfigDao.getAllWithIsTimeDiv(Boolean.TRUE);
////        for (SchedulerConfig config : dataToUpdateLastExecution) {
//            Date now = new Date();
//            String nowString = new SimpleDateFormat("dd MM yyyy HH:mm").format(now);
////            config.setLastExecution(new SimpleDateFormat("dd MM yyyy HH:mm").parse(nowString));
//            SchedulerConfig config=dataToUpdateLastExecution.get(0);
//            config.setLastExecution(new SimpleDateFormat("dd MM yyyy HH:mm").parse(nowString));
//            this.schedulerConfigDao.update(config);
//           
////            }
////        }
//    }

    @Scheduled(cron = "${cron.dinamic.scheduler}")
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void doDinamicCheck() throws Exception {
        LOGGER.info(" Eksekusi pada :" + new SimpleDateFormat("dd MMMM yyyy HH:mm:ss").format(new Date()));
        List<SchedulerConfig> allScheduleActive = schedulerConfigDao.getAllData(Boolean.TRUE);
        for (SchedulerConfig config : allScheduleActive) {
            if ("REPEAT".equals(config.getSchedullerType())) {
                if ("EVERY_DAY".equals(config.getRepeateType())) {
                    doCheckCurrentDate(config);
                }
                if ("EVERY_WEEK".equals(config.getRepeateType())) {
                    if (new SimpleDateFormat("uu").format(new Date()).equals(new SimpleDateFormat("uu").format(config.getDateStartExecution()))) {
                        doCheckCurrentDate(config);
                    }

                }
                if ("EVERY_MONTH".equals(config.getRepeateType())) {
                    if (new SimpleDateFormat("dd").format(new Date()).equals(new SimpleDateFormat("dd").format(config.getDateStartExecution()))) {
                        doCheckCurrentDate(config);
                    }

                }
                if ("EVERTY_YEAR".equals(config.getRepeateType())) {
                    if (new SimpleDateFormat("dd/MM").format(new Date()).equals(new SimpleDateFormat("dd/MM").format(config.getDateStartExecution()))) {
                        doCheckCurrentDate(config);
                    }

                }
            }

            if ("ONCE".equals(config.getSchedullerType())) {
                doCheckCurrentDateEquale(config);
            }

            if ("RANGE_TIME".equals(config.getSchedullerType())) {
                doCheckCurrentDateRange(config);
            }
        }
    }

    private void doCheckCurrentDate(SchedulerConfig config) {

        try {
            Date doDateExecution = config.getDateStartExecution();
            String currentDateString = new SimpleDateFormat("dd MM yyyy").format(new Date());
            Date currentDate = new SimpleDateFormat("dd MM yyyy").parse(currentDateString);
            if (doDateExecution.equals(currentDate) || doDateExecution.before(currentDate)) {
                if (config.getIsTimeDiv()) {
                    Date lastExecution = config.getLastExecution();
//                    Date currentDateTime = new Date();
                    if (lastExecution != null) {
                        Integer hour = config.getHourDiv();
                        Integer minute = config.getMinuteDiv();
//                        Integer totalDiv = (hour + minute) / 1000;
                        Date lasExePlusHour = DateTimeUtil.getDateFrom(lastExecution, hour, CommonUtilConstant.DATE_FORMAT_HOURS);
                        Date lastExePlusMinute = DateTimeUtil.getDateFrom(lasExePlusHour, minute, CommonUtilConstant.DATE_FORMAT_MINUTES);
                        Date newDate = new Date();
                        String dateString = new SimpleDateFormat("dd MM yyyy HH:mm").format(newDate);
                        Date forComparison = new SimpleDateFormat("dd MM yyyy HH:mm").parse(dateString);
//                        Long total = currentDateTime.getTime() - lastExecution.getTime();
                        LOGGER.info("===========================PROSES SCHEDULER Begin ===============================================");
                        if (lastExePlusMinute.equals(forComparison)) {
                            config.setLastExecution(forComparison);
                            LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                            LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                            LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                            LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                            LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                            LOGGER.info("===========================PROSES SCHEDULER===============================================");
                            schedulerConfigDao.update(config);
                            executionSchedullerViaMessaging(config);
                            LOGGER.info("++++++++++++++++++++++++++==Pengiriman Message Untuk Proses Pngulangan Semua Jeda Waktu ++++++++++++++++++++++  :" + config.getHourDiv() + " JAM" + config.getMinuteDiv() + " MIN");
                        }
                    }
                } else {
                    LOGGER.info("===========================PROSES SCHEDULER JEDA WAKTU===============================================");
                    String currentDateStringTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    Date currentDateTime = new SimpleDateFormat("HH:mm:ss").parse(currentDateStringTime);
                    if (currentDateTime.getTime() == config.getSchedullerTime().getTime()) {
                        LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                        LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                        LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                        LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                        LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                        LOGGER.info("===========================PROSES SCHEDULER===============================================");
                        executionSchedullerViaMessaging(config);
                        LOGGER.info("++++++++++++++++++++++++++Pengiriman Message Untuk Proses Pada Waktu Tertentu ++++++++++++++++++++++  :" + config.getSchedullerTime());
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    private void doCheckCurrentDateEquale(SchedulerConfig config) {
        try {
            Date doDateExecution = config.getDateStartExecution();
            String currentDateString = new SimpleDateFormat("dd MM yyyy").format(new Date());
            Date currentDate = new SimpleDateFormat("dd MM yyyy").parse(currentDateString);
            if (doDateExecution.equals(currentDate)) {
                if (config.getIsTimeDiv()) {
                    Date lastExecution = config.getLastExecution();
//                    Date currentDateTime = new Date();
                    if (lastExecution != null) {
                        Integer hour = config.getHourDiv();
                        Integer minute = config.getMinuteDiv();
//                         Integer totalDiv = (hour + minute) / 1000;
                        Date lasExePlusHour = DateTimeUtil.getDateFrom(lastExecution, hour, CommonUtilConstant.DATE_FORMAT_HOURS);
                        Date lastExePlusMinute = DateTimeUtil.getDateFrom(lasExePlusHour, minute, CommonUtilConstant.DATE_FORMAT_MINUTES);
                        Date newDate = new Date();
                        String dateString = new SimpleDateFormat("dd MM yyyy HH:mm").format(newDate);
                        Date forComparison = new SimpleDateFormat("dd MM yyyy HH:mm").parse(dateString);
                        if (lastExePlusMinute.equals(forComparison)) {
                            config.setLastExecution(forComparison);
                            schedulerConfigDao.update(config);
                            LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                            LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                            LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                            LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                            LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                            LOGGER.info("===========================PROSES SCHEDULER===============================================");
                            executionSchedullerViaMessaging(config);
                            LOGGER.info("++++++++++++++++++++++++++==Pengiriman Message Untuk Proses Pngulangan Semua Jeda Waktu ++++++++++++++++++++++  :" + config.getHourDiv() + " JAM" + config.getMinuteDiv() + " MIN");
                        }
                    }
                } else {
                    String currentDateStringTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    Date currentDateTime = new SimpleDateFormat("HH:mm:ss").parse(currentDateStringTime);
                    if (currentDateTime.getTime() == config.getSchedullerTime().getTime()) {
                        LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                        LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                        LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                        LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                        LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                        LOGGER.info("===========================PROSES SCHEDULER===============================================");
                        executionSchedullerViaMessaging(config);
                        LOGGER.info("++++++++++++++++++++++++++Pengiriman Message Untuk Proses Pada Waktu Tertentu ++++++++++++++++++++++  :" + config.getSchedullerTime());
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    private void doCheckCurrentDateRange(SchedulerConfig config) {
        try {
            Date doDateExecution = config.getDateStartExecution();
            Date endRangeExecution = config.getEndDate();
            String currentDateString = new SimpleDateFormat("dd MM yyyy").format(new Date());
            Date currentDate = new SimpleDateFormat("dd MM yyyy").parse(currentDateString);
            if (doDateExecution.equals(currentDate) || (doDateExecution.before(currentDate) && endRangeExecution.after(currentDate)) || endRangeExecution.equals(currentDate)) {
                if (config.getIsTimeDiv()) {
                    Date lastExecution = config.getLastExecution();
//                    Date currentDateTime = new Date();
                    if (lastExecution != null) {
                        Integer hour = config.getHourDiv();
                        Integer minute = config.getMinuteDiv();
                        Date lasExePlusHour = DateTimeUtil.getDateFrom(lastExecution, hour, CommonUtilConstant.DATE_FORMAT_HOURS);
                        Date lastExePlusMinute = DateTimeUtil.getDateFrom(lasExePlusHour, minute, CommonUtilConstant.DATE_FORMAT_MINUTES);
                        Date newDate = new Date();
                        String dateString = new SimpleDateFormat("dd MM yyyy HH:mm").format(newDate);
                        Date forComparison = new SimpleDateFormat("dd MM yyyy HH:mm").parse(dateString);
                        if (lastExePlusMinute.equals(forComparison)) {
                            config.setLastExecution(forComparison);
                            schedulerConfigDao.update(config);
                            LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                            LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                            LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                            LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                            LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                            LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                            LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                            LOGGER.info("===========================PROSES SCHEDULER===============================================");
                            executionSchedullerViaMessaging(config);
                            LOGGER.info("++++++++++++++++++++++++++==Pengiriman Message Untuk Proses Pngulangan Semua Jeda Waktu ++++++++++++++++++++++  :" + config.getHourDiv() + " JAM" + config.getMinuteDiv() + " MIN");
                        }
                    }
                } else {
                    String currentDateStringTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    Date currentDateTime = new SimpleDateFormat("HH:mm:ss").parse(currentDateStringTime);
                    if (currentDateTime.getTime() == config.getSchedullerTime().getTime()) {
                        LOGGER.info("===========================PROSES SCHEDULER ===============================================");
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getName());
                        LOGGER.info("Jenis Jadwal :" + config.getSchedullerType());
                        LOGGER.info("Kategori Jenis Pengulangan :" + config.getRepeateType());
                        LOGGER.info("MULAI TANGGAL PENGULANGAN :" + config.getStartDate());
                        LOGGER.info("SAMPAI TANGGAL PENGULANGAN :" + config.getEndDate());
                        LOGGER.info("Waktu Pengulapan Pada Pukul :" + config.getSchedullerTime());
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getHourDiv() + " JAM");
                        LOGGER.info("Waktu Pengungalan Jeda Setiap  :" + config.getMinuteDiv() + " MINUTE");
                        LOGGER.info("===========================PROSES SCHEDULER===============================================");
                        executionSchedullerViaMessaging(config);
                        LOGGER.info("++++++++++++++++++++++++++Pengiriman Message Untuk Proses Pada Waktu Tertentu ++++++++++++++++++++++  :" + config.getSchedullerTime());
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    private void executionSchedullerViaMessaging(SchedulerConfig config) {
        final long configId = config.getId();
        SchedulerLog schedulerLog = new SchedulerLog();
        schedulerLog.setSchedulerConfig(new SchedulerConfig(configId));
        final SchedulerLog log = saveLog(schedulerLog);
        switch (config.getName()) {
            case "USER_PASSWORD_NOTIFICATION":
                jmsTemplateUserPassNotif.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "APROVAL_MATRIX_NOTIF":
                jmsTemplateApprovalNotif.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "DELETE_ACESS_HISTORY":
                jmsTemplateDelAccessHis.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "DELETE_LOG_HISTORY":
                jmsTemplateDelLogHis.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "HOLIDAY_UPDATE":
                jmsTemplateHolidayUpdate.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "SCHEDULE_WORK":
                jmsTemplateScheduleWork.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "DELETE_TEMP_SCHEDULE_WORK":
                jmsTemplateDelTempScheduleWork.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "AUTO_APPROVAL_MATRIX":
                jmsTemplateAutoApprovalMat.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "ADD_BALANCE_LEAVE":
                jmsTemplateAddBalanceLeave.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;

            case "ADD_BALANCE_PERMIT":
                jmsTemplateAddBalancePermit.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;

            case "COMPANY_POLICY_SEND":
                jmsTemplateCompanyPolicySend.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;

            case "ATTENDANCE_CALCULATE":
                jmsTemplateAttendaceCalculatte.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;

            case "ANNOUNCMENT_GENERATING_LOG":
                jmsTemplateAnnoucmentGeneratingLog.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;

            case "ANNOUNCMENT_SENDING_NOTIF":
                jmsTemplateAnnoucmentSendingNotif.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "MONITORING_SCHEDULE_LOG_DELETE":
                jmsTemplateDeleteMonitoringLog.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
            case "PASSWORD_COMPLEXITY_NOTIF":
                jmsTemplatePasswordComplexity.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
                 case "RECRUITMENT_CONFIG_EMP":
                jmsTemplateRecruitmetConfigEmp.send(new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage(String.valueOf(log.getId()));
                    }
                });
                break;
                
            default:
                break;

        }

    }

    private SchedulerLog saveLog(SchedulerLog schedulerLog) {
        schedulerLog.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        schedulerLog.setSchedulerConfig(schedulerConfigDao.getEntiyByPK(schedulerLog.getSchedulerConfig().getId()));
        schedulerLog.setStartExecution(new Date());
        schedulerLog.setStatusMessages("PROCESS- IF STILL PROCESS-PLEASE SEE LOG FOR DETAILS");
        schedulerLogDao.save(schedulerLog);
        return schedulerLog;
    }
}
