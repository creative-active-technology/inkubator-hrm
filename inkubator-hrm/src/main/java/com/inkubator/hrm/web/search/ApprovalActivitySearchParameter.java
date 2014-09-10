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
     private String approvedBy;
     private String name;

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

    public String getApprovedBy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "approvedBy")) {
            approvedBy = getParameter();
        } else {
            approvedBy = null;
        }
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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
    
    
    
}
