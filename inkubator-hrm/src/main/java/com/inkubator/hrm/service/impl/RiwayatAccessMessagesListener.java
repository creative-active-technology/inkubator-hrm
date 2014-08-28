/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service.impl;

import com.inkubator.common.util.JsonConverter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.datacore.service.impl.IServiceImpl;
import com.inkubator.hrm.dao.RiwayatAksesDao;
import com.inkubator.hrm.entity.RiwayatAkses;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deni Husni FR
 */
public class RiwayatAccessMessagesListener extends IServiceImpl implements MessageListener {

    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private RiwayatAksesDao riwayatAksesDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String json = textMessage.getText();
            RiwayatAkses riwayatAccess = (RiwayatAkses) jsonConverter.getClassFromJson(json, RiwayatAkses.class);
            riwayatAccess.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(14)));
            String b = StringUtils.reverse(riwayatAccess.getPathUrl());
            String c = StringUtils.substringBefore(b, "/");
            String d;
            if (StringUtils.containsAny(c, "htm")) {
                d = StringUtils.substringAfter(c, ".");
                riwayatAccess.setName(StringUtils.reverse(d));

            } else {
                d = StringUtils.reverse(c);
                riwayatAccess.setName(d);
            }
        
            riwayatAksesDao.save(riwayatAccess);
        } catch (JMSException ex) {
            LOGGER.error("Error", ex);
        }
    }

}
