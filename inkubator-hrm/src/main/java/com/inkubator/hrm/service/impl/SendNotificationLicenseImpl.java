/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.LicenseAppDao;
import com.inkubator.hrm.entity.LicenseApp;
import com.inkubator.hrm.service.SendNotificationLicense;
import com.inkubator.webcore.WebCoreConstant;
import com.inkubator.webcore.model.LiscenseModel;
import com.inkubator.webcore.util.LicenseUtil;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author denifahri
 */
public class SendNotificationLicenseImpl extends IServiceImpl implements SendNotificationLicense {

    private String applicationUrl;
    private String ownerEmail;
    @Autowired
    private LicenseAppDao licenseAppDao;
    @Autowired
    private VelocityTemplateSender velocityTemplateSender;

    @Override
    @Scheduled(cron = "${cron.sendNotification}")
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void sendNotification() throws Exception {
        LicenseApp licenseApp = licenseAppDao.getByStatusAndName(WebCoreConstant.LICENSE_ACTIVE, WebCoreConstant.IIT_OPTIMAHR);
        LiscenseModel liscenseModel = LicenseUtil.getLicenseFromString(licenseApp.getLicenseValue());
        InetAddress ip = InetAddress.getLocalHost();
        VelocityTempalteModel vtm = new VelocityTempalteModel();
        List<String> toSend = new ArrayList<>();
        List<String> toSentCC = new ArrayList<>();
        vtm.setFrom(ownerEmail);
        toSend.add("rizal2_dhfr@yahoo.com");
        vtm.setTo(toSend.toArray(new String[toSend.size()]));
        vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
        vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
        vtm.setSubject("Application License Notification");
        Map maptoSend = new HashMap();
        maptoSend.put("sendDate", new Date());
        maptoSend.put("ipAddress", ip.getHostAddress());
        maptoSend.put("serverName", ip.getHostName());
        maptoSend.put("appName", WebCoreConstant.IIT_OPTIMAHR);
        maptoSend.put("startDate", liscenseModel.getStartDate());
        maptoSend.put("endDate", liscenseModel.getEndDate());
        maptoSend.put("licenseType", liscenseModel.getLicenseType());
        maptoSend.put("licenseStatus", liscenseModel.getLicenseStatus());
        vtm.setTemplatePath("email_license_notif.vm");
        velocityTemplateSender.sendMail(vtm, maptoSend);
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

}
