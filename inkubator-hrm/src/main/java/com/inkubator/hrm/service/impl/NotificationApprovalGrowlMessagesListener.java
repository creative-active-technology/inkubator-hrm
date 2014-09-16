/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.web.model.ApprovalPushMessageModel;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rizkykojek
 */
public class NotificationApprovalGrowlMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private JsonConverter jsonConverter;

//    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 50, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            ApprovalPushMessageModel model = (ApprovalPushMessageModel) jsonConverter.getClassFromJson(json, ApprovalPushMessageModel.class);

            PushContext pushContext = PushContextFactory.getDefault().getPushContext();
            pushContext.push(HRMConstant.NOTIFICATION_APPROVAL_CHANEL_SOCKET, model);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

}
