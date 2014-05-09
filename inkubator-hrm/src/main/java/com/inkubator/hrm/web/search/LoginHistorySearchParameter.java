/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.util.Date;

/**
 *
 * @author Deni Husni FR
 */
public class LoginHistorySearchParameter extends SearchParameter {

    private String userName;
    private String ipAddress;
    private Date loginDateStart;
    private Date loginDateFinish;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginDateStart() {
        return loginDateStart;
    }

    public void setLoginDateStart(Date loginDateStart) {
        this.loginDateStart = loginDateStart;
    }

    public Date getLoginDateFinish() {
        return loginDateFinish;
    }

    public void setLoginDateFinish(Date loginDateFinish) {
        this.loginDateFinish = loginDateFinish;
    }
    
    

}
