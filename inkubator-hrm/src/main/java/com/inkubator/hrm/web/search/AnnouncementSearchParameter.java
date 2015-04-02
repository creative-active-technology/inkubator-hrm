package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author WebGenX
 */
public class AnnouncementSearchParameter extends SearchParameter {

    private String announcementContent;
    private String subject;

    public String getAnnouncementContent() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "announcementContent")) {
            announcementContent = getParameter();
        } else {
            announcementContent = null;
        }
        return announcementContent;
    }

    public String getSubject() {
        if (StringUtils.equalsIgnoreCase(getKeyParam(), "subject")) {
            subject = getParameter();
        } else {
            subject = null;
        }
        return subject;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
