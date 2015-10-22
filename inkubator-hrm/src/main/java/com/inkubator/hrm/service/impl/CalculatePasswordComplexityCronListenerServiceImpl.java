/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;


import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.PasswordComplexityDao;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;

import java.util.Date;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class CalculatePasswordComplexityCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

	@Autowired
	private PasswordComplexityDao passwordComplexityDao;
	@Autowired
	private HrmUserDao hrmUserDao;
	@Autowired
    protected JmsTemplate jmsTemplatePasswordComplexity;
	@Autowired
	private JsonConverter jsonConverter;
	
	private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;


    public void calculatePasswordComplexity() throws Exception {
        LOGGER.warn("Begin Running Passwrod Complexity  ");
        updateSendExpiredPassword();
        sendEmailPasswordPeriodAlmostExpired();
        LOGGER.warn("Finish Running Passwrod Complexity  ");

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void onMessage(Message msg) {
        SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) msg;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            calculatePasswordComplexity();
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

    private void updateSendExpiredPassword() throws Exception{
    	PasswordComplexity passwordComplexity = passwordComplexityDao.getAllData().get(0);
    	Integer numberOfMonthToExpired = passwordComplexity.getExpiredPeriod();
    	List<HrmUser> listUserWithPasswordToUpdateToExpired = hrmUserDao.getListUserWithExpiredPasswordButStatusStillNotUpdateToExpired(numberOfMonthToExpired);
    	for(HrmUser user : listUserWithPasswordToUpdateToExpired){
    		
    		HrmUser hrmUserToUpdate = hrmUserDao.getEntiyByPK(user.getUserId());
    		hrmUserToUpdate.setIsExpired(HRMConstant.EXPIRED);
    		hrmUserToUpdate.setUpdatedOn(new Date());
    		hrmUserToUpdate.setUpdatedBy(HRMConstant.INKUBA_SYSTEM);
    		hrmUserDao.update(hrmUserToUpdate);
    		
    		if(passwordComplexity.getEmailNotification()){
    			
    		}
    		
    	}
    }
    
    private void sendEmailPasswordExpiredNotification(HrmUser hrmUser){
    	
    }
    
    private void sendEmailPasswordPeriodAlmostExpired() throws Exception{
    	
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
