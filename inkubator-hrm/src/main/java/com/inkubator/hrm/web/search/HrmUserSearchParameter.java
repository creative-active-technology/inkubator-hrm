/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni Husni FR
 */
public class HrmUserSearchParameter extends SearchParameter {

    private String userName;
    private String realName;
    private String roleName;

    public String getUserName() {

        if (StringUtils.equalsIgnoreCase(getKeyParam(), "userId")) {
            userName = getParameter();
        } else {
            userName = null;
        }

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {

        if (StringUtils.equalsIgnoreCase(getKeyParam(), "realName")) {
            realName = getParameter();
        } else {
            realName = null;
        }
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoleName() {
        
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "roleName")) {
            roleName = getParameter();
        } else {
            roleName = null;
        }
        
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
