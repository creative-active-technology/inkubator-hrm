/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.IpPermit;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.service.IpPermitService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Deni Husni FR
 */
public class HrmUserInfoUtil extends UserInfoUtil {

    public static String getRealName() {
        HrmUserService hrmUserService = (HrmUserService) ServiceWebUtil.getService("hrmUserService");
        String hrmUserName = getUserName();
        try {
            return hrmUserService.getByUserIdOrEmail(hrmUserName).getRealName();
        } catch (Exception ex) {
            Logger.getLogger(HrmUserInfoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getRequestRemoteAddrByJSF() {
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
//                   .getRequest(); 
//        return request.getRemoteAddr();

        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return httpServletRequest.getRemoteAddr();
    }

    public static String getRequestRemoteAddrBySpring() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        return request.getRemoteAddr();
    }

    public static Boolean isValidRemoteAddress() throws Exception {
        String ipClient = getRequestRemoteAddrByJSF();
        String riversIp = StringUtils.reverse(ipClient);
        String ipHeaderReverse = StringUtils.substringAfter(riversIp, ".");
        String ipEnd = StringUtils.substringBefore(riversIp, ".");
        int ipLast = Integer.parseInt(ipEnd);
        String ip = StringUtils.remove(ipHeaderReverse, ".");
        int ipHeader = Integer.parseInt(StringUtils.reverse(ip));
        IpPermitService ipPermitService = (IpPermitService) ServiceWebUtil.getService("ipPermitService");
        List<IpPermit> dataToCheck = ipPermitService.getByIpHeader(ipHeader);
        for (IpPermit ipPermit : dataToCheck) {
            int fromAddres2 = ipPermit.getFromAddress2();
            int untilAddress2 = ipPermit.getUntilAddress2();
            if (ipLast >= fromAddres2 && ipLast <= untilAddress2) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    
    public static EmpData getEmpData() {
        HrmUserService hrmUserService = (HrmUserService) ServiceWebUtil.getService("hrmUserService");
        String hrmUserName = getUserName();
        try {
            return hrmUserService.getByUserIdOrEmail(hrmUserName).getEmpData();
        } catch (Exception ex) {
            Logger.getLogger(HrmUserInfoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getRealNameByUserName(String hrmUserName) {
        HrmUserService hrmUserService = (HrmUserService) ServiceWebUtil.getService("hrmUserService");
        try {
            return hrmUserService.getByUserIdOrEmail(hrmUserName).getRealName();
        } catch (Exception ex) {
            Logger.getLogger(HrmUserInfoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
