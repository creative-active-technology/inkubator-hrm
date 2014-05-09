/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationSuccessHandler;
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
    @Autowired
    private DateFormatter dateFormatter;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setLanguange((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE));
            String number = RandomNumberUtil.getRandomNumber(15);
            loginHistory.setId(Long.parseLong(number));
            loginHistory.setIpAddress(request.getRemoteAddr());
            loginHistory.setLoginDate(new Date());
            loginHistory.setHrmUser(new HrmUser(authentication.getName()));
            this.loginHistoryService.saveAndPushMessage(loginHistory);
            LOGGER.info(authentication.getName() + " Success Login");
            response.sendRedirect(request.getContextPath() + "/protected/home.htm");
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }
}
