package com.inkubator.hrm.entity;
// Generated Oct 23, 2015 2:26:57 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * RecruitLetterComChannel generated by hbm2java
 */
@Entity
@Table(name="recruit_letter_com_channel"
    ,catalog="hrm"
)
public class RecruitLetterComChannel  implements java.io.Serializable {


     private RecruitLetterComChannelId id;
     private Integer version;
     private RecruitCommChannels recruitCommChannels;
     private RecruitLetters recruitLetters;
     private Date createdOn;
     private String createdBy;

    public RecruitLetterComChannel() {
    }

	
    public RecruitLetterComChannel(RecruitLetterComChannelId id, RecruitCommChannels recruitCommChannels, RecruitLetters recruitLetters) {
        this.id = id;
        this.recruitCommChannels = recruitCommChannels;
        this.recruitLetters = recruitLetters;
    }
    public RecruitLetterComChannel(RecruitLetterComChannelId id, RecruitCommChannels recruitCommChannels, RecruitLetters recruitLetters, Date createdOn, String createdBy) {
       this.id = id;
       this.recruitCommChannels = recruitCommChannels;
       this.recruitLetters = recruitLetters;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="commChannelId", column=@Column(name="comm_channel_id", nullable=false) ), 
        @AttributeOverride(name="letterId", column=@Column(name="letter_id", nullable=false) ) } )
    public RecruitLetterComChannelId getId() {
        return this.id;
    }
    
    public void setId(RecruitLetterComChannelId id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comm_channel_id", nullable=false, insertable=false, updatable=false)
    public RecruitCommChannels getRecruitCommChannels() {
        return this.recruitCommChannels;
    }
    
    public void setRecruitCommChannels(RecruitCommChannels recruitCommChannels) {
        this.recruitCommChannels = recruitCommChannels;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="letter_id", nullable=false, insertable=false, updatable=false)
    public RecruitLetters getRecruitLetters() {
        return this.recruitLetters;
    }
    
    public void setRecruitLetters(RecruitLetters recruitLetters) {
        this.recruitLetters = recruitLetters;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }




}


