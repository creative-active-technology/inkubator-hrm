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
public class AttendanceStatusSearchParamater extends SearchParameter {

    private String codeStatus;
    private String statusName;

    public String getCodeStatus() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "statusCode")) {
            codeStatus = getParameter();
        } else {
            codeStatus = null;
        }
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getStatusName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "statusName")) {
            statusName = getParameter();
        } else {
            statusName = null;
        }

        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
