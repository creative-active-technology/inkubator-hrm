package com.inkubator.hrm.entity;
// Generated Oct 23, 2015 2:26:57 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RecruitLetterComChannelId generated by hbm2java
 */
@Embeddable
public class RecruitLetterComChannelId implements java.io.Serializable {

    private long commChannelId;
    private long letterId;

    public RecruitLetterComChannelId() {
    }

    public RecruitLetterComChannelId(long commChannelId, long letterId) {
        this.commChannelId = commChannelId;
        this.letterId = letterId;
    }

    @Column(name = "comm_channel_id", nullable = false)
    public long getCommChannelId() {
        return this.commChannelId;
    }

    public void setCommChannelId(long commChannelId) {
        this.commChannelId = commChannelId;
    }

    @Column(name = "letter_id", nullable = false)
    public long getLetterId() {
        return this.letterId;
    }

    public void setLetterId(long letterId) {
        this.letterId = letterId;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof RecruitLetterComChannelId)) {
            return false;
        }
        RecruitLetterComChannelId castOther = (RecruitLetterComChannelId) other;

        return (this.getCommChannelId() == castOther.getCommChannelId())
                && (this.getLetterId() == castOther.getLetterId());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (int) this.getCommChannelId();
        result = 37 * result + (int) this.getLetterId();
        return result;
    }

}
