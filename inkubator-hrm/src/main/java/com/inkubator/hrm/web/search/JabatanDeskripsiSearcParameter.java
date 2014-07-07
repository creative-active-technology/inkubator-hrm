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
public class JabatanDeskripsiSearcParameter extends SearchParameter {

    private String jobsName;
    private String parentJobsName;
  

    public String getJobsName() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "jobsName")) {
            jobsName = getParameter();
        } else {
            jobsName = null;
        }
        return jobsName;
    }

    public void setJobsName(String jobsName) {
        this.jobsName = jobsName;
    }

    public String getParentJobsName() {
         if (StringUtils.equalsIgnoreCase(getKeyParam(), "parentJobsName")) {
            parentJobsName = getParameter();
        } else {
            parentJobsName = null;
        }
        return parentJobsName;
    }

    public void setParentJobsName(String parentJobsName) {
        this.parentJobsName = parentJobsName;
    }

   

}
