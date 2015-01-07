/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.util;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.securitycore.util.AuthenticationSuccessHandler;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.sms.gateway.SMSGATEWAY;
import com.inkubator.sms.gateway.entity.LoginHistory;
import com.inkubator.sms.gateway.service.LoginHistoryService;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            loginHistory.setLanguange((String) FacesUtil.getSessionAttribute(SMSGATEWAY.BAHASA_ACTIVE));
            String number = RandomNumberUtil.getRandomNumber(15);
            loginHistory.setId(Long.parseLong(number));
            loginHistory.setIpAddress(request.getRemoteAddr());
            loginHistory.setLoginDate(new Date());
            loginHistory.setUserName(UserInfoUtil.getUserName());
            this.loginHistoryService.save(loginHistory);
            LOGGER.info(authentication.getName() + " Success Login");
            response.sendRedirect(request.getContextPath() + "/protected/home.htm");
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
}
