/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Deni
 */
public class JabatanSpesifikasiSearchParameter extends SearchParameter{
     private String specificationAbility;
     private String jabatan;
     private String value;

    public String getSpecificationAbility() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "specificationAbility")) {
            specificationAbility = getParameter();
        } else {
            specificationAbility = null;
        }
        return specificationAbility;
    }

    public void setSpecificationAbility(String specificationAbility) {
        this.specificationAbility = specificationAbility;
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

    public String getValue() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "value")) {
            value = getParameter();
        } else {
            value = null;
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
     
     
}
