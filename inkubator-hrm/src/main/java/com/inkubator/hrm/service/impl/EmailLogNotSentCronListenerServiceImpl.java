package com.inkubator.hrm.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.inkubator.hrm.dao.EmailLogDao;
import com.inkubator.hrm.entity.EmailLog;
import com.inkubator.hrm.entity.EmailLogAttachment;
import com.inkubator.hrm.entity.SchedulerLog;

/**
 *
 * @author rizkykojek
 */
public class EmailLogNotSentCronListenerServiceImpl extends BaseSchedulerDinamicListenerImpl implements MessageListener {

	private String ownerEmail;
	
	@Autowired
	private EmailLogDao emailLogDao;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void onMessage(Message message) {
		SchedulerLog log = null;
        try {
            TextMessage textMessage = (TextMessage) message;
            log = schedulerLogDao.getEntiyByPK(Long.parseLong(textMessage.getText()));
            this.sendingAllEmailNotSent();
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

	private void sendingAllEmailNotSent() throws MessagingException {
		LOGGER.warn("Process Checking Email Log not sent ===========================================================");
		List<EmailLog> listAllEmailNotSent = emailLogDao.getAllDataEmailNotSent();
		
		for(EmailLog emailLog : listAllEmailNotSent){
			// use the true flag to indicate you need a multipart message
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper ;
			if(emailLog.getEmailLogAttachments().isEmpty()) {
				helper = new MimeMessageHelper(message);
            } else {
            	helper = new MimeMessageHelper(message, true);
            }
			
			helper.setTo(emailLog.getMailTo());			
			helper.setSentDate(new Date());
			helper.setTo(emailLog.getMailTo());
			helper.setFrom(ownerEmail);
			helper.setSubject(emailLog.getMailSubject());
            helper.setText(emailLog.getMailContent(), emailLog.getIsContentHtml());
			
            if(!emailLog.getMailCc().isEmpty()) {
            	helper.setCc(emailLog.getMailCc().toArray(new String[emailLog.getMailCc().size()]));
            }
            if(!emailLog.getMailBcc().isEmpty()) {
            	helper.setBcc(emailLog.getMailBcc().toArray(new String[emailLog.getMailBcc().size()]));
            }
            if(!emailLog.getEmailLogAttachments().isEmpty()){
            	for(EmailLogAttachment attachment : emailLog.getEmailLogAttachments()){
            		InputStreamSource is = new ByteArrayResource(attachment.getAttachment());
            		if(attachment.getIsInlineResources()){
            			//file as inline resources
            			helper.addInline(attachment.getContentId(), is, attachment.getContentType());
            		} else {
            			//file as attachment
            			helper.addAttachment(attachment.getFileName(), is, attachment.getContentType());
            		}
            	}
            }			
	        this.javaMailSender.send(message);
	        
	        
	        /** if succeed, then status of update email log */
	        emailLog.setSentOn(new Date());
	        emailLog.setSentStatus(Boolean.TRUE);
	        emailLogDao.update(emailLog);
		}
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	
}
