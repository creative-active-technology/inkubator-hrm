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
 * @author EKA
 */
public class BatchJobExecutionSearchParameter extends SearchParameter{
    private String jobName;
    private String status;

    public String getJobName() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "jobName")){
            jobName = getParameter();
        } else{
            jobName = null;
        }
        return jobName;
    }

    public String getStatus() {
        if(StringUtils.equalsIgnoreCase(getKeyParam(), "status")){
            status = getParameter();
        } else{
            status = null;
        }
        return status;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
