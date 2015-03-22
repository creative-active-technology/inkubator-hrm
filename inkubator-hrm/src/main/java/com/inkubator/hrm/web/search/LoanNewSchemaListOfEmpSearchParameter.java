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
 * @author Deni
 */
public class LoanNewSchemaListOfEmpSearchParameter extends SearchParameter{
    private String name;
    private String code;
    private String noSk;

    public String getName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "name")){
            name = getParameter();
        } else {
            name = null;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "code")){
            code = getParameter();
        } else {
            code = null;
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoSk() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "noSk")){
            noSk = getParameter();
        } else {
            noSk = null;
        }
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }
    
    
}
