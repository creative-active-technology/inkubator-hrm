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
public class PayTempAttendanceSearchParameter extends SearchParameter {

    private String NIK;
    private String name;
    private String workGroup;

    public String getNIK() {

        if (StringUtils.equalsIgnoreCase(getKeyParam(), "NIK")) {
            NIK = getParameter();
        } else {
            NIK = null;
        }
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

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

    public String getWorkGroup() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "workGroup")) {
            workGroup = getParameter();
        } else {
            workGroup = null;
        }
        return workGroup;
    }

    public void setWorkGroup(String workGroup) {
        this.workGroup = workGroup;
    }



}
