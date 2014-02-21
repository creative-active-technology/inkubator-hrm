/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationLogoutSuccessHandler;
import com.inkubator.webcore.util.FacesUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Deni Husni FR
 */
public class CustomAuthenticationLogoutSuccessHandler extends AuthenticationLogoutSuccessHandler {

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
         System.out.println(" Logut tersekserererk");
         System.out.println(request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID));
        try {
            if (request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID) != null) {
                System.out.println(" Logut tersekserererk");
                String id = (String) request.getSession().getAttribute(HRMConstant.USER_LOGIN_ID);
                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setId(Long.parseLong(id));
                this.loginHistoryService.update(loginHistory);
                LOGGER.info("Step to Logout");
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
}
