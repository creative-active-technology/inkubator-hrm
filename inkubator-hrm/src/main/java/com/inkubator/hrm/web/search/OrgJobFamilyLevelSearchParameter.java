package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class OrgJobFamilyLevelSearchParameter extends SearchParameter {

    private String createdBy;

    public String getCreatedBy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "createdBy")) {
            createdBy = getParameter();
        } else {
            createdBy = null;
        }
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
