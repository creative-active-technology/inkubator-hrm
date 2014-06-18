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
public class JabatanSearchParameter extends SearchParameter {

    private String code;
    private String name;
    private String jabatan;
    private String departementName;
    private String costCenterName;
    private String unitKerjaName;

    public String getCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "code")) {
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getJabatan() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jabatan")) {
            jabatan = getParameter();
        } else {
            jabatan = null;
        }

        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getDepartementName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "departementName")) {
            departementName = getParameter();
        } else {
            departementName = null;
        }

        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }

    public String getCostCenterName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "costCenterName")) {
            costCenterName = getParameter();
        } else {
            costCenterName = null;
        }

        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getUnitKerjaName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "unitKerjaName")) {
            unitKerjaName = getParameter();
        } else {
            unitKerjaName = null;
        }

        return unitKerjaName;
    }

    public void setUnitKerjaName(String unitKerjaName) {
        this.unitKerjaName = unitKerjaName;
    }

}
