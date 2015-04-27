/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.AnnouncementJsonModel;

/**
 *
 * @author rizkykojek
 */
public class NotificationBroadcastAnnouncementMessagesListener extends IServiceImpl implements MessageListener {
    
	@Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobLauncher jobLauncherAsync;
    @Autowired
    private Job jobAnnouncementLog;
    @Autowired
    private Job jobEmailingAnnouncement;
    @Autowired
    private Job jobEmailingAnnouncementNotSent;
	
    @Override    
    public void onMessage(Message message) {
        try {            
        	String json = ((TextMessage) message).getText();        	
        	Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();        	
        	AnnouncementJsonModel model = gson.fromJson(json, AnnouncementJsonModel.class);
        	
        	/** proses untuk menggenerate log, sekaligus mengirim email per Announcement*/
        	if(BooleanUtils.isTrue(model.getIsGeneratingAnnouncementLog())){	        	
            	//creating announcement log (synchronous)
	        	JobParameters jobParameters = new JobParametersBuilder()
		            .addLong("announcementId", model.getAnnouncementId())
		            .addDate("planExecutionDate", model.getPlanExecutionDate())
		            .addDate("createdOn", new Timestamp(new Date().getTime()))
		            .toJobParameters();
	            jobLauncher.run(jobAnnouncementLog, jobParameters);
	            
	            //broadcast email announcement (asynchronous / fire and forget)
	            if(model.getViewModel() == HRMConstant.ANNOUNCEMENT_VIEW_MAIL) {		            
		            jobParameters = new JobParametersBuilder()
			            .addLong("announcementId", model.getAnnouncementId())
			            .addDate("planExecutionDate", model.getPlanExecutionDate())
			            .addDate("createdOn", new Timestamp(new Date().getTime()))
			            .toJobParameters();
		            jobLauncherAsync.run(jobEmailingAnnouncement, jobParameters);  
	            }
        	}        	
        	
        	/** proses untuk mengirim email semua Announcement, yang memang belum dikirim */
        	if(BooleanUtils.isTrue(model.getIsSendingAllEmailNotSent())){
        		JobParameters jobParameters = new JobParametersBuilder()
        			.addDate("createdOn", new Timestamp(new Date().getTime()))
        			.toJobParameters();
        		jobLauncherAsync.run(jobEmailingAnnouncementNotSent, jobParameters);
        	}
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }	
}
