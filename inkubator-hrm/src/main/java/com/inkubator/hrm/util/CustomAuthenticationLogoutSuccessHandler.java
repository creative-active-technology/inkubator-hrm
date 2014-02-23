/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationLogoutSuccessHandler;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Deni Husni FR
 */
public class CustomAuthenticationLogoutSuccessHandler extends AuthenticationLogoutSuccessHandler {

    @Autowired
    private LoginHistoryService loginHistoryService;
    @Autowired
    private DateFormatter dateFormatter;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println(request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID));
        try {
            if (request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID) != null) {
                System.out.println(" Logut tersekserererk");
                String id = (String) request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID);
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(Long.parseLong(id));
                this.loginHistoryService.update(loginHistory);
                String bahasa = (String) request.getSession().getAttribute(HRMConstant.BAHASA_ACTIVE);
                PushContext pushContext = PushContextFactory.getDefault().getPushContext();
                String infoMessages = authentication.getName() + " berhasil logout pada : " + dateFormatter.getDateFullAsStringsWithActiveLocale(new Date(), new Locale(bahasa));
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information Login", infoMessages);
                pushContext.push(HRMConstant.NOTIFICATION_CHANEL_SOCKET, facesMessage);
                LOGGER.info("Step to Logout");
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
