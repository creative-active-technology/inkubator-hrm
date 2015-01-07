/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.sms.gateway.entity.SmsActivity;
import com.inkubator.sms.gateway.entity.TaskDefinition;
import com.inkubator.sms.gateway.web.model.SmsSendModel;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface SmsActivityService extends IService<SmsActivity> {

    public void sendSms(SmsSendModel ssm) throws Exception;

    public List<SmsActivity> getListBySendDate(Date date) throws Exception;

    public List<SmsActivity> getAllByFullTextService(String parameter, int minResult, int maxResult, Order order) throws Exception;

    public Integer getTotalByFullTextService(String parameter) throws Exception;
}
