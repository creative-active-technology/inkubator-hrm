package com.inkubator.hrm.entity;
// Generated Nov 3, 2014 2:13:41 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
import javax.persistence.Version;

/**
 * CompanyPolicy generated by hbm2java
 */
@Entity
@Table(name="company_policy"
    ,catalog="hrm"
)
public class CompanyPolicy  implements java.io.Serializable {


     private long id;
     private Integer version;
     private Department department;
     private String code;
     private String subjectTitle;
     private String contentPolicy;
     private Date effectiveDate;
     private String attachFilePath;
     private Boolean isBroadcast;
     private String repeatOn;
     private Integer weeklyDay;
     private Date monthlyDate;
     private Boolean useAttachment;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date upatedOn;
     private Set<CompanyPolicyJabatan> companyPolicyJabatans = new HashSet<CompanyPolicyJabatan>(0);

    public CompanyPolicy() {
    }

	
    public CompanyPolicy(long id) {
        this.id = id;
    }
    public CompanyPolicy(long id, Department department, String code, String subjectTitle, String contentPolicy, Date effectiveDate, String attachFilePath, Boolean isBroadcast, String repeatOn, Integer weeklyDay, Date monthlyDate, Boolean useAttachment, String createdBy, Date createdOn, String updatedBy, Date upatedOn, Set<CompanyPolicyJabatan> companyPolicyJabatans) {
       this.id = id;
       this.department = department;
       this.code = code;
       this.subjectTitle = subjectTitle;
       this.contentPolicy = contentPolicy;
       this.effectiveDate = effectiveDate;
       this.attachFilePath = attachFilePath;
       this.isBroadcast = isBroadcast;
       this.repeatOn = repeatOn;
       this.weeklyDay = weeklyDay;
       this.monthlyDate = monthlyDate;
       this.useAttachment = useAttachment;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.upatedOn = upatedOn;
       this.companyPolicyJabatans = companyPolicyJabatans;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
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
    @JoinColumn(name="departement_id")
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

    
    @Column(name="code", length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="subject_title", length=45)
    public String getSubjectTitle() {
        return this.subjectTitle;
    }
    
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    
    @Column(name="content_policy", length=65535)
    public String getContentPolicy() {
        return this.contentPolicy;
    }
    
    public void setContentPolicy(String contentPolicy) {
        this.contentPolicy = contentPolicy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="effective_date", length=10)
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    
    @Column(name="attach_file_path", length=45)
    public String getAttachFilePath() {
        return this.attachFilePath;
    }
    
    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }

    
    @Column(name="is_broadcast")
    public Boolean getIsBroadcast() {
        return this.isBroadcast;
    }
    
    public void setIsBroadcast(Boolean isBroadcast) {
        this.isBroadcast = isBroadcast;
    }

    
    @Column(name="repeat_on", length=45)
    public String getRepeatOn() {
        return this.repeatOn;
    }
    
    public void setRepeatOn(String repeatOn) {
        this.repeatOn = repeatOn;
    }

    
    @Column(name="weekly_day")
    public Integer getWeeklyDay() {
        return this.weeklyDay;
    }
    
    public void setWeeklyDay(Integer weeklyDay) {
        this.weeklyDay = weeklyDay;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="monthly_date", length=10)
    public Date getMonthlyDate() {
        return this.monthlyDate;
    }
    
    public void setMonthlyDate(Date monthlyDate) {
        this.monthlyDate = monthlyDate;
    }

    
    @Column(name="use_attachment")
    public Boolean getUseAttachment() {
        return this.useAttachment;
    }
    
    public void setUseAttachment(Boolean useAttachment) {
        this.useAttachment = useAttachment;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="upated_on", length=19)
    public Date getUpatedOn() {
        return this.upatedOn;
    }
    
    public void setUpatedOn(Date upatedOn) {
        this.upatedOn = upatedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="companyPolicy")
    public Set<CompanyPolicyJabatan> getCompanyPolicyJabatans() {
        return this.companyPolicyJabatans;
    }
    
    public void setCompanyPolicyJabatans(Set<CompanyPolicyJabatan> companyPolicyJabatans) {
        this.companyPolicyJabatans = companyPolicyJabatans;
    }




}

