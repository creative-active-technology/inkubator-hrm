/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Deni Husni FR,rizkykojek
 */
public class LoginHistorySearchParameter extends SearchParameter {

    private String userId;
    private String ipAddress;

    public String getUserId() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "userId")) {
            userId = getParameter();
        } else {
            userId = null;
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "ipAddress")) {
            ipAddress = getParameter();
        } else {
            ipAddress = null;
        }
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
