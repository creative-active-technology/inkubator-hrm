/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.push;

import javax.faces.application.FacesMessage;
import org.atmosphere.config.service.Singleton;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author deni.fahri
 */
@PushEndpoint("/notificationsLogin")
@Singleton
public class LoginNotifResource {

    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
        return message;
    }
}
