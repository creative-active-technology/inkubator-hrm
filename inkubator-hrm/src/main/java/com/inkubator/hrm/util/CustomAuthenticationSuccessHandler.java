/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationSuccessHandler;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
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
public class CustomAuthenticationSuccessHandler extends AuthenticationSuccessHandler {

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setBahasa((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE));
            String number = RandomNumberUtil.getRandomNumber(15);
            loginHistory.setId(Long.parseLong(number));
            loginHistory.setIpAddress(request.getRemoteAddr());
            loginHistory.setLoginDate(new Date());
            loginHistory.setUserName(authentication.getName());
            this.loginHistoryService.save(loginHistory);
            FacesUtil.setSessionAttribute(HRMConstant.USER_LOGIN_ID, number);
            PushContext pushContext = PushContextFactory.getDefault().getPushContext();
            pushContext.push(HRMConstant.NOTIFICATION_CHANEL_SOCKET, new FacesMessage("Hahahh")); 
            LOGGER.info("Success Login");
            response.sendRedirect(request.getContextPath() + "/protected/home.htm");
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
}
