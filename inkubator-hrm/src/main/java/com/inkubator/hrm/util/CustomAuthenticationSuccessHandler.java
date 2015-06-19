/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.common.util.DateFormatter;
import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LoginHistory;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.LoginHistoryService;
import com.inkubator.securitycore.util.AuthenticationSuccessHandler;
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Autowired
    private HrmUserService hrmUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        try {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setLanguange((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE));
            FacesUtil.setSessionAttribute("realName", HrmUserInfoUtil.getRealName());
            try {
//                FacesUtil.setSessionAttribute(HRMConstant.COMPANY_ACTIVE, hrmUserService.getCompanyId(authentication.getName()));
                EmpData data = hrmUserService.getByUserIdWithEmpIdAndBioId(authentication.getName()).getEmpData();
                FacesUtil.setSessionAttribute(HRMConstant.BIODATA_ID, data.getBioData().getId());
                FacesUtil.setSessionAttribute(HRMConstant.EMP_DATA_ID, data.getId());
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }

            String number = RandomNumberUtil.getRandomNumber(15);
            loginHistory.setId(Long.parseLong(number));
            String ipHere = IpUtil.getIpFromRequest(request);
            LOGGER.info(ipHere + " IP Remote");
            loginHistory.setIpAddress(ipHere);
            loginHistory.setLoginDate(new Date());
            loginHistory.setHrmUser(new HrmUser(authentication.getName()));
            loginHistory.setAppName(HRMConstant.APP_NAME);
            try {
                this.loginHistoryService.saveAndPushMessage(loginHistory);
            } catch (Exception ex) {
                Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            LOGGER.info(authentication.getName() + " Success Login");
            response.sendRedirect(request.getContextPath() + "/protected/home.htm");
        } catch (NumberFormatException | IOException ex) {
            LOGGER.error("Error", ex);
        }

    }
}
