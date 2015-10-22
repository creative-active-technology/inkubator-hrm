/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;


import com.inkubator.common.notification.model.SMSSend;
import com.inkubator.common.notification.model.VelocityTempalteModel;
import com.inkubator.common.notification.service.VelocityTemplateSender;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.PasswordComplexityDao;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.PasswordComplexity;
import com.inkubator.hrm.entity.SchedulerConfig;
import com.inkubator.hrm.entity.SchedulerLog;
import com.inkubator.webcore.util.FacesUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
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
    private JmsTemplate jmsTemplateSMS;
	@Autowired
	private JsonConverter jsonConverter;
	@Autowired
    private VelocityTemplateSender velocityTemplateSender;
	
	private String applicationUrl;
    private String applicationName;
    private String ownerEmail;
    private String ownerCompany;
    private String ownerAdministrator;


    public void calculatePasswordComplexity() throws Exception {
    	LOGGER.warn("Begin Running Passwrod Complexity  ");
    	
    	PasswordComplexity passwordComplexity = passwordComplexityDao.getAllData().get(0);
        updateSendExpiredPassword(passwordComplexity);
        updateSendPasswordPeriodAlmostExpired(passwordComplexity);
        
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

    private void updateSendExpiredPassword(PasswordComplexity passwordComplexity) throws Exception{
    	
    	LOGGER.warn(" ===== Begin Run updateSendExpiredPassword ===== ");
    	Integer numberOfMonthToExpired = passwordComplexity.getExpiredPeriod();
    	List<HrmUser> listUserWithPasswordToUpdateToExpired = hrmUserDao.getListUserWithExpiredPasswordButStatusStillNotUpdateToExpired(numberOfMonthToExpired);
    	LOGGER.warn("List User with Expired Password, But Status still not update to Expired :  " + listUserWithPasswordToUpdateToExpired.size() + " user");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String language = "en";
        
        LOGGER.warn("passwordComplexity.getEmailNotification() :  " + passwordComplexity.getEmailNotification());
        LOGGER.warn("passwordComplexity.getSmsNotification() :  " + passwordComplexity.getSmsNotification());
        
    	for(HrmUser user : listUserWithPasswordToUpdateToExpired){
    		
    		LOGGER.warn("Begin update status to Expired to User :  " + user.getRealName());
    		HrmUser hrmUserToUpdate = hrmUserDao.getEntiyByPK(user.getId());
    		hrmUserToUpdate.setIsExpired(HRMConstant.EXPIRED);
    		hrmUserToUpdate.setUpdatedOn(new Date());
    		hrmUserToUpdate.setUpdatedBy(HRMConstant.INKUBA_SYSTEM);
    		hrmUserDao.update(hrmUserToUpdate);
    		LOGGER.info("Finish update status to Expired to User :  " + user.getRealName());
    		
    		if(passwordComplexity.getEmailNotification()){
    			
    			LOGGER.warn("Begin Sending Email Notification to User :  " + hrmUserToUpdate.getRealName());
    			sendEmailPasswordExpiredNotification(hrmUserToUpdate, dateFormat, timeFormat, language);
    			LOGGER.warn("Finish Sending Email Notification to User :  " + hrmUserToUpdate.getRealName());
    			
    		}
    		
    		if(passwordComplexity.getSmsNotification()){
    			
    			LOGGER.warn("Begin Sending SMS Notification to User :  " + hrmUserToUpdate.getRealName());
    			sendSmsPasswordExpiredNotification(hrmUserToUpdate);
    			LOGGER.warn("Finish Sending SMS Notification to User :  " + hrmUserToUpdate.getRealName());
    		}
    		
    	}
    	
    	LOGGER.warn(" ===== Finish Run updateSendExpiredPassword ===== ");
    }
    
    private void sendEmailPasswordExpiredNotification(HrmUser hrmUser, SimpleDateFormat dateFormat, SimpleDateFormat timeFormat, String language) throws Exception{
    	
    	 VelocityTempalteModel vtm = new VelocityTempalteModel();
    	 Map maptoSend = new HashMap();
         List<String> toSend = new ArrayList<>();
         List<String> toSentCC = new ArrayList<String>();
         
         toSentCC.add("amjadicky@gmail.com");
         toSentCC.add("deni.arianto24@yahoo.com");
         toSentCC.add("rizal2_dhfr@yahoo.com");
         toSentCC.add("guntur@incubatechnology.com");
         toSentCC.add("rizkykojek@gmail.com");
         toSentCC.add("yosa.mareta@gmail.com");
         
         vtm.setFrom(ownerEmail);
         toSend.add(hrmUser.getEmailAddress());
         vtm.setTo(toSend.toArray(new String[toSend.size()]));
         vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
         vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
         
         switch (language) {
         
         case "in":
			vtm.setSubject("Notifikasi Password Kadaluwarsa");
			vtm.setTemplatePath("email_password_expired_notification.vm");
	        maptoSend.put("headerInfo", "Password akun pengguna anda pada Aplikasi " + applicationName + " telah kadaluarsa <br/>");
			break;
		
         case "en":
 			vtm.setSubject("Expired Password Notification");
 			vtm.setTemplatePath("email_password_expired_notification_en.vm");
 	        maptoSend.put("headerInfo", "Your password account at Application " + applicationName + " has been expired <br/>");
 			break;

         default:
			break;
		}
         
         maptoSend.put("user", hrmUser);
         maptoSend.put("ownerAdministrator", ownerAdministrator);
         maptoSend.put("ownerCompany", ownerCompany);
         maptoSend.put("applicationUrl", applicationUrl);
         maptoSend.put("applicationName", applicationName);
         velocityTemplateSender.sendMail(vtm, maptoSend);
    }
    
    private void sendSmsPasswordExpiredNotification(final HrmUser hrmUser){
    	
    	final SMSSend mSSend = new SMSSend();
        mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
        mSSend.setDestination(hrmUser.getPhoneNumber());
        mSSend.setContent("Dear " + hrmUser.getRealName() + " Password anda pada Aplikasi "
      		+ applicationName + " Telah Kadaluwarsa. Segera Hubungi Administrator untuk mengaktifkan kembali akun anda.");
    	
        jmsTemplateSMS.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonConverter.getJson(mSSend));
            }
        });
        
    }
    
    private void updateSendPasswordPeriodAlmostExpired(PasswordComplexity passwordComplexity) throws Exception{
    	LOGGER.warn(" ===== Begin Run updateSendPasswordPeriodAlmostExpired ===== ");
    	
    	Integer notificationPeriod = passwordComplexity.getNotificationPeriod();
    	Integer numberOfMonthToExpired = passwordComplexity.getExpiredPeriod();
    	List<HrmUser> listUserWithPasswordAlmostExpired = hrmUserDao.getListUserWithPasswordAlmostExpired(numberOfMonthToExpired, notificationPeriod);
    	LOGGER.warn("List User with Password almost expired, :  " + listUserWithPasswordAlmostExpired.size() + " user");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String language = "in";
        
        LOGGER.warn("passwordComplexity.getEmailNotification() :  " + passwordComplexity.getEmailNotification());
        LOGGER.warn("passwordComplexity.getSmsNotification() :  " + passwordComplexity.getSmsNotification());
        
    	for(HrmUser user : listUserWithPasswordAlmostExpired){
    		
    		if(passwordComplexity.getEmailNotification()){
    			
    			LOGGER.warn("Begin Sending Email Password Almost Expired Notification to User :  " + user.getRealName());
    			sendEmailPasswordAlmostExpiredNotification(user, dateFormat, timeFormat, language);
    			LOGGER.warn("Finish Sending Email Password Almost Expired Notification to User :  " + user.getRealName());
    			
    		}
    		
    		if(passwordComplexity.getSmsNotification()){
    			
    			LOGGER.warn("Begin Sending SMS Password Almost Expired Notification to User :  " + user.getRealName());
    			sendSmsPasswordAlmostExpiredNotification(user);
    			LOGGER.warn("Finish Sending SMS Password Almost Expired Notification to User :  " + user.getRealName());
    		}
    		
    	}
    	
    	LOGGER.warn(" ===== Finish Run updateSendPasswordPeriodAlmostExpired ===== ");
        
    }
    
    private void sendEmailPasswordAlmostExpiredNotification(HrmUser hrmUser, SimpleDateFormat dateFormat, SimpleDateFormat timeFormat, String language) throws Exception{
    	VelocityTempalteModel vtm = new VelocityTempalteModel();
   	 	Map maptoSend = new HashMap();
        List<String> toSend = new ArrayList<>();
        List<String> toSentCC = new ArrayList<String>();
        
        toSentCC.add("amjadicky@gmail.com");
        toSentCC.add("deni.arianto24@yahoo.com");
        toSentCC.add("rizal2_dhfr@yahoo.com");
        toSentCC.add("guntur@incubatechnology.com");
        toSentCC.add("rizkykojek@gmail.com");
        toSentCC.add("yosa.mareta@gmail.com");
        
        vtm.setFrom(ownerEmail);
        toSend.add(hrmUser.getEmailAddress());
        vtm.setTo(toSend.toArray(new String[toSend.size()]));
        vtm.setCc(toSentCC.toArray(new String[toSentCC.size()]));
        vtm.setBcc(toSentCC.toArray(new String[toSentCC.size()]));
        
        switch (language) {
        
        case "in":
			vtm.setSubject("Password Mendekati Kadaluwarsa");
			vtm.setTemplatePath("email_password_almost_expired_notification.vm");
	        maptoSend.put("headerInfo", "Masa berlaku Password akun pengguna anda pada Aplikasi " + applicationName + " mendekati kadaluarsa <br/>");
			break;
		
        case "en":
			vtm.setSubject("Password Almost Expired");
			vtm.setTemplatePath("email_password_almost_expired_notification_en.vm");
	        maptoSend.put("headerInfo", "Validity period of your password account at Application " + applicationName + " almost expired <br/>");
			break;

        default:
			break;
		}
        
        maptoSend.put("user", hrmUser);
        maptoSend.put("ownerAdministrator", ownerAdministrator);
        maptoSend.put("ownerCompany", ownerCompany);
        maptoSend.put("applicationUrl", applicationUrl);
        maptoSend.put("applicationName", applicationName);
        velocityTemplateSender.sendMail(vtm, maptoSend);
    }
    
    private void sendSmsPasswordAlmostExpiredNotification(final HrmUser hrmUser){
    	
    	final SMSSend mSSend = new SMSSend();
        mSSend.setFrom(HRMConstant.SYSTEM_ADMIN);
        mSSend.setDestination(hrmUser.getPhoneNumber());
        mSSend.setContent("Dear " + hrmUser.getRealName() + " Password anda pada Aplikasi "
      		+ applicationName + " mendekati Kadaluwarsa. Segera ubah password anda segera.");
    	
        jmsTemplateSMS.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(jsonConverter.getJson(mSSend));
            }
        });
        
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
