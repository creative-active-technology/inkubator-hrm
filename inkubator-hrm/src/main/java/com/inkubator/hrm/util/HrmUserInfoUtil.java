/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.util;

import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.util.ServiceWebUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deni Husni FR
 */
public class HrmUserInfoUtil extends UserInfoUtil {

   
    public static  String getRealName() {
        HrmUserService hrmUserService=(HrmUserService) ServiceWebUtil.getService("hrmUserService");
        String hrmUserName = getUserName();
        try {
            return hrmUserService.getByUserIdOrEmail(hrmUserName).getRealName();
        } catch (Exception ex) {
            Logger.getLogger(HrmUserInfoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
