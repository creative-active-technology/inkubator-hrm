package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class AnnouncementLogSearchParameter extends SearchParameter {

    private String createdBy;
    private String updatedBy;

    public String getCreatedBy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "createdBy")) {
            createdBy = getParameter();
        } else {
            createdBy = null;
        }
        return createdBy;
    }

    public String getUpdatedBy() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "updatedBy")) {
            updatedBy = getParameter();
        } else {
            updatedBy = null;
        }
        return updatedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
