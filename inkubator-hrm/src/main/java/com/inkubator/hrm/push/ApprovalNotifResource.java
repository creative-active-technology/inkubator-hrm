/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.push;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import com.inkubator.hrm.web.model.ApprovalPushMessageModel;

/**
 *
 * @author rizkykojek
 */
@PushEndpoint("/notificationsApproval")
public class ApprovalNotifResource {

    @OnMessage(encoders = {JSONEncoder.class})
    public ApprovalPushMessageModel onMessage(ApprovalPushMessageModel message) {
        return message;
    }
}
