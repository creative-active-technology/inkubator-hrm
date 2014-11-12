package com.inkubator.hrm.web.search;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import org.apache.commons.lang3.StringUtils;

import com.inkubator.webcore.util.SearchParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rizkykojek
 */
public class PermitImplementationReportSearchParameter extends SearchParameter {

    private Date startDate;
    private Date endDate;
    private String approvalStatus;

    public Date getStartDate() {
        
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    
}
