/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationLogoutSuccessHandler;

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
        try {
            if (request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID) != null) {
                Long id = (Long) request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID);
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(id);
                this.loginHistoryService.updateAndPushMessage(loginHistory);                
                LOGGER.info(authentication.getName() + " Success Logout");
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
