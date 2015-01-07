/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.service.impl;

import com.inkubator.common.notification.model.SMSSend;
import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.sms.gateway.dao.SmsActivityDao;
import com.inkubator.sms.gateway.entity.SmsActivity;
import java.io.IOException;
import java.util.Date;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class SmsMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private SmsActivityDao smsActivityDao;
    @Autowired
    private JsonConverter jsonConverter;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {

            LOGGER.info("Sending SMS Begin");
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            SMSSend smss = (SMSSend) jsonConverter.getClassFromJson(json, SMSSend.class);
            SmsActivity activity = new SmsActivity();
            activity.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            activity.setContentSms(smss.getContent());
            activity.setDestination(smss.getDestination());
            activity.setIsSchedule(false);
            activity.setFromSms(smss.getFrom());
            activity.setIsSend(true);
            activity.setSendDate(new Date());
            activity.setSendTime(new Date());
            activity.setPriceSms(smss.getPricePerSms());
            smsActivityDao.save(activity);
            OutboundMessage msg = new OutboundMessage(smss.getDestination(), smss.getContent());
            msg.setFlashSms(false);
            Service.getInstance().sendMessage(msg,smss.getModemId());
            LOGGER.info("Sending SMS Done");
        } catch (JMSException | NumberFormatException | TimeoutException | GatewayException | IOException | InterruptedException ex) {
            LOGGER.error("Error", ex);
        }
//        } catch (JMSException | TimeoutException | GatewayException | IOException | InterruptedException ex) {
//            LOGGER.error("Error", ex);
//        }
    }

}
