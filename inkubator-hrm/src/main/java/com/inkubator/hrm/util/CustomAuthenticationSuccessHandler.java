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
import com.inkubator.webcore.util.FacesUtil;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    protected transient Logger LOGGER = Logger.getLogger(getClass());
    @Autowired
    private LoginHistoryService loginHistoryService;
    @Autowired
    private HrmUserService hrmUserService;

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
     LOGGER.warn("Login sukses di akses");
        try {
            LoginHistory loginHistory = new LoginHistory();
            loginHistory.setLanguange((String) FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE));
            FacesUtil.setSessionAttribute("realName", HrmUserInfoUtil.getRealName());
            HrmUser hrmUser = hrmUserService.getByUserIdOrEmail(authentication.getName());
            FacesUtil.setSessionAttribute(HRMConstant.COMPANY_ACTIVE, hrmUserService.getCompanyId(hrmUser.getUserId()));
            EmpData data = hrmUserService.getByUserIdWithEmpIdAndBioId(authentication.getName()).getEmpData();
            FacesUtil.setSessionAttribute(HRMConstant.BIODATA_ID, data.getBioData().getId());
            FacesUtil.setSessionAttribute(HRMConstant.EMP_DATA_ID, data.getId());
            FacesUtil.setSessionAttribute(HRMConstant.COMPANY_NAME, hrmUserService.getCompanyName(authentication.getName()));
            String number = RandomNumberUtil.getRandomNumber(15);
            loginHistory.setId(Long.parseLong(number));
            String ipHere = IpUtil.getIpFromRequest(request);
            loginHistory.setIpAddress(ipHere);
            loginHistory.setLoginDate(new Date());
            loginHistory.setHrmUser(new HrmUser(authentication.getName()));
            loginHistory.setAppName(HRMConstant.APP_NAME);
            this.loginHistoryService.saveAndPushMessage(loginHistory);;
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParameter != null && StringUtils.hasText(request
                        .getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }
        clearAuthenticationAttributes(request);
        String targetUrl = savedRequest.getRedirectUrl();
        LOGGER.warn("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
