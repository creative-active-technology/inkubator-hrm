/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author deniarianto
 */
public class PasswordHistorySearchParameter extends SearchParameter{
    private String username;
    private String realname;
    private String emailAddress;
    private String phoneNumber;

    public String getUsername() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "username")){
            username = getParameter();
        } else {
            username = null;
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "realname")){
            realname = getParameter();
        } else {
            realname = null;
        }
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmailAddress() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "emailAddress")){
            emailAddress = getParameter();
        } else {
            emailAddress = null;
        }
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "phoneNumber")){
            phoneNumber = getParameter();
        } else {
            phoneNumber = null;
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
