package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class AnnouncementSearchParameter extends SearchParameter {
	
    private String subject;

    public String getSubject() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "subject")) {
            subject = getParameter();
        } else {
            subject = null;
        }
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
