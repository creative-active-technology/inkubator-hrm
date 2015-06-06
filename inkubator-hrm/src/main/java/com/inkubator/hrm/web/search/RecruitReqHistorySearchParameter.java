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
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitReqHistorySearchParameter extends SearchParameter {
    private String formCode;
    private String jabatanName;  
    private String userId;

    public String getFormCode() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "formCode")) {
            formCode = getParameter();
        } else {
            formCode = null;
        }
        return formCode;       
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getJabatanName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanName")) {
            jabatanName = getParameter();
        } else {
            jabatanName = null;
        }            
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

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
    
    
    
}
