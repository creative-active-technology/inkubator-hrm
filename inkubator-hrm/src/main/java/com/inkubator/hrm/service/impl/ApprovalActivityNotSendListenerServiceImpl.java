/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.RmbsTypeDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.ApprovalActivityNotSendService;
import com.inkubator.webcore.util.FacesUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class ApprovalActivityNotSendListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

    private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;
    private String serverName;
    @Autowired
    private VelocityTemplateSender velocityTemplateSender;
    @Autowired
    private ApprovalActivityDao approvalActivityDao;
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private RmbsTypeDao rmbsTypeDao;

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

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            sendNotificationApprovalNotSend();
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

    public void sendNotificationApprovalNotSend() throws Exception {
        LOGGER.warn("Proses krim approval activiei karena schcedullerrerere");
        List<ApprovalActivity> dataToSend = approvalActivityDao.getDataNotSendEmailYet();
        for (ApprovalActivity appActivity : dataToSend) {
            Date deadline = DateUtils.addDays(appActivity.getCreatedTime(), appActivity.getApprovalDefinition().getDelayTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", new Locale(appActivity.getLocale()));
            JsonObject jsonObject = (JsonObject) jsonConverter.getClassFromJson(appActivity.getPendingData(), JsonObject.class);
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
             
             toSentCC = getCcEmailAddressesOnApproveOrReject(appActivity);
             toSend.add(requesterUser.getEmailAddress());
             }
             }*/
//            toSend.add("deni.arianto24@yahoo.com");
//            toSentCC.add("amjadicky@gmail.com");
            toSend.add("rizal2_dhfr@yahoo.com");
//            toSentCC.add("rizkykojek@gmail.com");
            vtm.setTo(toSend.toArray(new String[toSend.size()]));
            vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
            vtm.setBcc(toSentBCC.toArray(new String[toSentBCC.size()]));

            Map maptoSend = new HashMap();
            if (StringUtils.equals(appActivity.getLocale(), "en")) {
                vtm.setSubject("Permohonan Perjalanan Dinas");
                //not yet implemented

            } else {
                if (Objects.equals(appActivity.getApprovalStatus(), HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)) {

                    switch (appActivity.getApprovalDefinition().getName()) {
                        case HRMConstant.BUSINESS_TRAVEL:
                            double totalAmount = 0;
                            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//                            String pendingData = appActivity.getPendingData();

                            JsonObject businessTravelObj = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
                            List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(businessTravelObj.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>() {
                            }.getType());
                            for (BusinessTravelComponent btc : businessTravelComponents) {
                                totalAmount = totalAmount + btc.getPayByAmount();
                            }
                            vtm.setSubject("Permohonan Perjalanan Dinas");
                            vtm.setTemplatePath("email_travel_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("startDate").getAsString());
                            maptoSend.put("end", jsonObject.get("endDate").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", new DecimalFormat("###,###").format(totalAmount));

                            break;

                        case HRMConstant.REIMBURSEMENT:
                            vtm.setSubject("Permohonan Penggantian Biaya");
                            vtm.setTemplatePath("email_reimbursment_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            Gson gs = JsonUtil.getHibernateEntityGsonBuilder().create();
                            RmbsApplication application = gs.fromJson(appActivity.getPendingData(), RmbsApplication.class);
                            RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(application.getRmbsType().getId());
                            maptoSend.put("reimbursementType", rmbsType.getName());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("nominal", jsonObject.get("nominal").getAsString());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("createdOn").getAsString());
                            maptoSend.put("deadline", dateFormat.format(deadline));
//                            maptoSend.put("urlLinkToApprove", jsonObject.get(HRMConstant.CONTEXT_PATH).getAsString() + "" + HRMConstant.REIMBURSMENT_APPROVAL_PAGE + "" + "?faces-redirect=true&execution=e" + appActivity.getId());
                            break;

                        case HRMConstant.LOAN:
                            vtm.setSubject("Permohonan Pinjaman Lunak");
                            vtm.setTemplatePath("email_loan_waiting_approval.vm");
                            maptoSend.put("approverName", approverUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("loanSchemaName", jsonObject.get("loanSchemaName").getAsString());
                            maptoSend.put("nominalPrincipal", jsonObject.get("nominalPrincipal").getAsString());
                            maptoSend.put("interestRate", jsonObject.get("interestRate").getAsString());
                            maptoSend.put("nominalInstallment", jsonObject.get("nominalInstallment").getAsString());
                            maptoSend.put("interestInstallment", jsonObject.get("interestInstallment").getAsString());
                            maptoSend.put("totalNominalInstallment", jsonObject.get("totalNominalInstallment").getAsString());
                            break;

                        default:
                            break;
                    }
                } else if ((appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED)
                        || (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED)) {
                    //configure email parameter based on approval name	
                    switch (appActivity.getApprovalDefinition().getName()) {
                        case HRMConstant.BUSINESS_TRAVEL:
                            double totalAmount = 0;
                            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
//                            String pendingData = appActivity.getPendingData();

                            JsonObject businessTravelObj = gson.fromJson(appActivity.getPendingData(), JsonObject.class);
                            List<BusinessTravelComponent> businessTravelComponents = gson.fromJson(businessTravelObj.get("businessTravelComponents"), new TypeToken<List<BusinessTravelComponent>>() {
                            }.getType());
                            for (BusinessTravelComponent btc : businessTravelComponents) {
                                totalAmount = totalAmount + btc.getPayByAmount();
                            }
                            vtm.setSubject("Permohonan Perjalanan Dinas");
                            vtm.setTemplatePath("email_travel_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("businessTravelNo", jsonObject.get("businessTravelNo").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("destination", jsonObject.get("destination").getAsString());
                            maptoSend.put("start", jsonObject.get("startDate").getAsString());
                            maptoSend.put("end", jsonObject.get("endDate").getAsString());
                            maptoSend.put("description", jsonObject.get("description").getAsString());
                            maptoSend.put("totalAmount", new DecimalFormat("###,###").format(totalAmount));
                            if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
                                maptoSend.put("statusDesc", "Permohonan Diterima");
                            } else if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
                                maptoSend.put("statusDesc", "Permohonan Ditolak");
                            }
                            break;

                        case HRMConstant.REIMBURSEMENT:
                            vtm.setSubject("Permohonan Pergantian Biaya");
                            vtm.setTemplatePath("email_reimbursment_approved_or_rejected.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            Gson gs = JsonUtil.getHibernateEntityGsonBuilder().create();
                            RmbsApplication application = gs.fromJson(appActivity.getPendingData(), RmbsApplication.class);
                            RmbsType rmbsType = rmbsTypeDao.getEntiyByPK(application.getRmbsType().getId());
                            maptoSend.put("reimbursementType", rmbsType.getName());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("nominal", jsonObject.get("nominal").getAsString());
                            maptoSend.put("applicationDate", jsonObject.get("applicationDate").getAsString());
                            maptoSend.put("proposeDate", jsonObject.get("createdOn").getAsString());
                            if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
                                maptoSend.put("statusDesc", "Permohonan Diterima");
                            } else if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
                                maptoSend.put("statusDesc", "Permohonan Ditolak");
                            }
                            break;

                        case HRMConstant.LOAN:
                            vtm.setSubject("Permohonan Pinjaman Lunak");
                            vtm.setTemplatePath("email_loan_approved_or_rejected_approval.vm");
                            maptoSend.put("requesterName", requesterUser.getEmpData().getBioData().getFullName());
                            maptoSend.put("nik", requesterUser.getEmpData().getNik());
                            maptoSend.put("proposeDate", jsonObject.get("proposeDate").getAsString());
                            maptoSend.put("loanSchemaName", jsonObject.get("loanSchemaName").getAsString());
                            maptoSend.put("nominalPrincipal", jsonObject.get("nominalPrincipal").getAsString());
                            maptoSend.put("interestRate", jsonObject.get("interestRate").getAsString());
                            maptoSend.put("nominalInstallment", jsonObject.get("nominalInstallment").getAsString());
                            maptoSend.put("interestInstallment", jsonObject.get("interestInstallment").getAsString());
                            maptoSend.put("totalNominalInstallment", jsonObject.get("totalNominalInstallment").getAsString());
                            if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_APPROVED) {
                                maptoSend.put("statusDesc", "Permohonan Diterima");
                            } else if (appActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_REJECTED) {
                                maptoSend.put("statusDesc", "Permohonan Ditolak");
                            }
                            break;

                        default:
                            break;
                    }
                }
                if (jsonObject.get(HRMConstant.CONTEXT_PATH).getAsString() != null) {
                    String urlLinkToApprove = serverName + "" + jsonObject.get(HRMConstant.CONTEXT_PATH).getAsString() + "" + HRMConstant.REIMBURSMENT_APPROVAL_PAGE + "" + "?faces-redirect=true&execution=e" + appActivity.getId();
                    System.out.println(" Ini adlaaha link nya : " + urlLinkToApprove);
                    maptoSend.put("urlLinkToApprove", urlLinkToApprove);
                } else {
                    maptoSend.put("urlLinkToApprove", applicationUrl);
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
        }

    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
