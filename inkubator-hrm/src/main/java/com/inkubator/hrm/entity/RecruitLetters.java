package com.inkubator.hrm.entity;
// Generated Oct 23, 2015 2:42:06 PM by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * RecruitLetters generated by hbm2java
 */
@Entity
@Table(name = "recruit_letters", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class RecruitLetters implements java.io.Serializable {

    private long id;
    private Integer version;
    private EmpData empData;
    private Integer leterTypeId;
    private Boolean isActive;
//     private String recruitLetterscol;
    private String code;
    private String formatNumber;
    private Integer expiryDays;
    private String contentHtml;
    private Boolean smsNotif;
    private Date createdOn;
    private String createdBy;
    private Date updatedOn;
    private String updatedBy;
    private Set<RecruitLetterSelection> recruitLetterSelections = new HashSet<>(0);
    private Set<RecruitLetterComChannel> recruitLetterComChannels = new HashSet<>(0);
    private List<RecruitSelectionType> recruitSelectionTypes = new ArrayList<>();
    private List<RecruitCommChannels> recruitCommChannelss = new ArrayList<>();
    private Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetailsForAcceptLetterId = new HashSet<RecruitmenSelectionSeriesDetail>(0);
    private Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetailsForRejectLetterId = new HashSet<RecruitmenSelectionSeriesDetail>(0);

    public RecruitLetters() {
    }

    public RecruitLetters(long id) {
        this.id = id;
    }

    public RecruitLetters(long id, EmpData empData, Integer leterTypeId, Boolean isActive, String recruitLetterscol, String code, String formatNumber, Integer expiryDays, String contentHtml, Boolean smsNotif, Date createdOn, String createdBy, Date updatedOn, String updatedBy, Set<RecruitLetterSelection> recruitLetterSelections, Set<RecruitLetterComChannel> recruitLetterComChannels) {
        this.id = id;
        this.empData = empData;
        this.leterTypeId = leterTypeId;
        this.isActive = isActive;
//       this.recruitLetterscol = recruitLetterscol;
        this.code = code;
        this.formatNumber = formatNumber;
        this.expiryDays = expiryDays;
        this.contentHtml = contentHtml;
        this.smsNotif = smsNotif;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
        this.recruitLetterSelections = recruitLetterSelections;
        this.recruitLetterComChannels = recruitLetterComChannels;
    }

    @Id

    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signment_emp_id")
    public EmpData getEmpData() {
        return this.empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @Column(name = "leter_type_id")
    public Integer getLeterTypeId() {
        return this.leterTypeId;
    }

    public void setLeterTypeId(Integer leterTypeId) {
        this.leterTypeId = leterTypeId;
    }

    @Column(name = "is_active")
    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

//    @Column(name="recruit_letterscol", length=45)
//    public String getRecruitLetterscol() {
//        return this.recruitLetterscol;
//    }
//    
//    public void setRecruitLetterscol(String recruitLetterscol) {
//        this.recruitLetterscol = recruitLetterscol;
//    }
    @Column(name = "code", unique = true, length = 45)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "format_number", length = 60)
    public String getFormatNumber() {
        return this.formatNumber;
    }

    public void setFormatNumber(String formatNumber) {
        this.formatNumber = formatNumber;
    }

    @Column(name = "expiry_days")
    public Integer getExpiryDays() {
        return this.expiryDays;
    }

    public void setExpiryDays(Integer expiryDays) {
        this.expiryDays = expiryDays;
    }

    @Column(name = "content_html", length = 65535)
    public String getContentHtml() {
        return this.contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    @Column(name = "sms_notif")
    public Boolean getSmsNotif() {
        return this.smsNotif;
    }

    public void setSmsNotif(Boolean smsNotif) {
        this.smsNotif = smsNotif;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recruitLetters", orphanRemoval = true)
    public Set<RecruitLetterSelection> getRecruitLetterSelections() {
        return this.recruitLetterSelections;
    }

    public void setRecruitLetterSelections(Set<RecruitLetterSelection> recruitLetterSelections) {
        this.recruitLetterSelections = recruitLetterSelections;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recruitLetters", orphanRemoval = true)
    public Set<RecruitLetterComChannel> getRecruitLetterComChannels() {
        return this.recruitLetterComChannels;
    }

    public void setRecruitLetterComChannels(Set<RecruitLetterComChannel> recruitLetterComChannels) {
        this.recruitLetterComChannels = recruitLetterComChannels;
    }

    @Transient
    public List<RecruitSelectionType> getRecruitSelectionTypes() {
        return recruitSelectionTypes;
    }

    public void setRecruitSelectionTypes(List<RecruitSelectionType> recruitSelectionTypes) {
        this.recruitSelectionTypes = recruitSelectionTypes;
    }

    @Transient
    public List<RecruitCommChannels> getRecruitCommChannelss() {
        return recruitCommChannelss;
    }

    public void setRecruitCommChannelss(List<RecruitCommChannels> recruitCommChannelss) {
        this.recruitCommChannelss = recruitCommChannelss;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recruitLettersByAcceptLetterId", orphanRemoval = true)
    public Set<RecruitmenSelectionSeriesDetail> getRecruitmenSelectionSeriesDetailsForAcceptLetterId() {
        return this.recruitmenSelectionSeriesDetailsForAcceptLetterId;
    }

    public void setRecruitmenSelectionSeriesDetailsForAcceptLetterId(Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetailsForAcceptLetterId) {
        this.recruitmenSelectionSeriesDetailsForAcceptLetterId = recruitmenSelectionSeriesDetailsForAcceptLetterId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recruitLettersByRejectLetterId", orphanRemoval = true)
    public Set<RecruitmenSelectionSeriesDetail> getRecruitmenSelectionSeriesDetailsForRejectLetterId() {
        return this.recruitmenSelectionSeriesDetailsForRejectLetterId;
    }

    public void setRecruitmenSelectionSeriesDetailsForRejectLetterId(Set<RecruitmenSelectionSeriesDetail> recruitmenSelectionSeriesDetailsForRejectLetterId) {
        this.recruitmenSelectionSeriesDetailsForRejectLetterId = recruitmenSelectionSeriesDetailsForRejectLetterId;
    }
}
