package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class RecruitHireApplySearchParameter extends SearchParameter {

    private String reason;
    private String reqHireCode;
    private String maritalStatus;

    public String getReason() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "reason")) {
            reason = getParameter();
        } else {
            reason = null;
        }
        return reason;
    }

    public String getReqHireCode() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "reqHireCode")) {
            reqHireCode = getParameter();
        } else {
            reqHireCode = null;
        }
        return reqHireCode;
    }

    public String getMaritalStatus() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "maritalStatus")) {
            maritalStatus = getParameter();
        } else {
            maritalStatus = null;
        }
        return maritalStatus;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setReqHireCode(String reqHireCode) {
        this.reqHireCode = reqHireCode;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
