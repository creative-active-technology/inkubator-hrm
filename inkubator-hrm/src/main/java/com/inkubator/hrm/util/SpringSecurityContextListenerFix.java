/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.hrm.HRMConstant;
import java.util.Locale;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextListener;

/**
 *
 * @author Deni Husni FR
 */
public class SpringSecurityContextListenerFix extends RequestContextListener {

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        super.requestInitialized(requestEvent);
        HttpServletRequest request = (HttpServletRequest) requestEvent.getServletRequest();
        HttpSession sesion = request.getSession(false);
        Locale idioma;
        if (sesion != null) {
//            System.out.println(sesion.getAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            if (sesion.getAttribute(HRMConstant.BAHASA_ACTIVE) != null) {
                idioma = new Locale(sesion.getAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                idioma = new Locale("in");
            }
            LocaleContextHolder.setLocale(idioma);
        } else {
            idioma = new Locale("in");
            LocaleContextHolder.setLocale(idioma);
        }
    }
}
