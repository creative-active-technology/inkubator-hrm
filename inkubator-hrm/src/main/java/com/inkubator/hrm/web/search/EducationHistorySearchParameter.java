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
public class EducationHistorySearchParameter extends SearchParameter{
    private String certificateNumber;

    public String getCertificateNumber() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "certificateNumber")){
            certificateNumber = getParameter();
        } else {
            certificateNumber = null;
        }
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
    
    
}
