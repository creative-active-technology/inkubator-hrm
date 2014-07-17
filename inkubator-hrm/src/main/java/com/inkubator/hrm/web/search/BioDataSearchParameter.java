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
public class BioDataSearchParameter extends SearchParameter {

    private String name;
    private String nickName;
    private String emailAddress;

    public String getName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "name")) {
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "nickName")) {
            nickName = getParameter();
        } else {
            nickName = null;
        }
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmailAddress() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "emailAddress")) {
            emailAddress = getParameter();
        } else {
            emailAddress = null;
        }
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
