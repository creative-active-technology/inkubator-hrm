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
public class ApprovalActivitySearchParameter extends SearchParameter {
    private String requestBy;

    public String getRequestBy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "requestBy")) {
            requestBy = getParameter();
        } else {
            requestBy = null;
        }
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
    
    
}
