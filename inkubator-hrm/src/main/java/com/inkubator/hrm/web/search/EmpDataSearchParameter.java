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
public class EmpDataSearchParameter extends SearchParameter {

    private String NIK;
    private String name;
//    private Date joinDate;
    private String jabatanKode;
    private String jabatanName;

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

//    public Date getJoinDate() {
//        return joinDate;
//    }
//
//    public void setJoinDate(Date joinDate) {
//        this.joinDate = joinDate;
//    }
    public String getJabatanKode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatanKode")) {
            jabatanKode = getParameter();
        } else {
            jabatanKode = null;
        }
        return jabatanKode;
    }

    public void setJabatanKode(String jabatanKode) {
        this.jabatanKode = jabatanKode;
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

}
