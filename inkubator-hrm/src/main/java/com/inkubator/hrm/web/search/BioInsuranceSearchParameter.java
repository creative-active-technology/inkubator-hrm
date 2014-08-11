package com.inkubator.hrm.web.search;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;

/**
 *
 * @author Taufik Hidayat
 */
public class BioInsuranceSearchParameter extends SearchParameter {

    private String noPolicy;
    private String instance;

    public String getNoPolicy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "noPolicy")) {
            noPolicy = getParameter();
        } else {
            noPolicy = null;
        }
        return noPolicy;
    }

    public void setNoPolicy(String noPolicy) {
        this.noPolicy = noPolicy;
    }

    public String getInstance() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "instance")) {
            instance = getParameter();
        } else {
            instance = null;
        }
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

}
