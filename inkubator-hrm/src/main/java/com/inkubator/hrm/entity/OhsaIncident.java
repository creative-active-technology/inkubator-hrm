package com.inkubator.hrm.entity;
// Generated Mar 12, 2015 4:24:01 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Version;

/**
 * OhsaIncident generated by hbm2java
 */
@Entity
@Table(name="ohsa_incident"
    ,catalog="hrm_payroll_backup"
)
public class OhsaIncident  implements java.io.Serializable {


     private long id;
     private Integer version;
     private OhsaCategory ohsaCategory;
     private Integer severityLevel;
     private String subject;
     private Date incidentTime;
     private String location;
     private String description;
     private String ohsaIncidentCode;
     private Date createdOn;
     private String createdBy;
     private String updatedBy;
     private Date updatedOn;    
     private Set<OhsaIncidentDocument> ohsaIncidentDocuments = new HashSet<OhsaIncidentDocument>(0);
     private Set<OhsaEmpInvolve> ohsaEmpInvolves = new HashSet<OhsaEmpInvolve>(0);
     private Long totalEmpInvolves;
    public OhsaIncident() {
    }

	
    public OhsaIncident(long id) {
        this.id = id;
    }
    public OhsaIncident(long id, OhsaCategory ohsaCategory, Integer severityLevel, String subject, Date incidentTime, String location, String description, String ohsaIncidentCode, Date createdOn, String createdBy, String updatedBy, Date updatedOn,
            Set<OhsaIncidentDocument> ohsaIncidentDocuments, Set<OhsaEmpInvolve> ohsaEmpInvolves) {
       this.id = id;
       this.ohsaCategory = ohsaCategory;
       this.severityLevel = severityLevel;
       this.subject = subject;
       this.incidentTime = incidentTime;
       this.location = location;
       this.description = description;
       this.ohsaIncidentCode = ohsaIncidentCode;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.ohsaIncidentDocuments = ohsaIncidentDocuments;
       this.ohsaEmpInvolves = ohsaEmpInvolves;
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
    @JoinColumn(name="ohsa_category_id")
    public OhsaCategory getOhsaCategory() {
        return this.ohsaCategory;
    }
    
    public void setOhsaCategory(OhsaCategory ohsaCategory) {
        this.ohsaCategory = ohsaCategory;
    }

    
    @Column(name="severity_level")
    public Integer getSeverityLevel() {
        return this.severityLevel;
    }
    
    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    
    @Column(name="subject", length=100)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="incident_time", length=19)
    public Date getIncidentTime() {
        return this.incidentTime;
    }
    
    public void setIncidentTime(Date incidentTime) {
        this.incidentTime = incidentTime;
    }

    
    @Column(name="location", length=100)
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="ohsa_incident_code", length=45)
    public String getOhsaIncidentCode() {
        return this.ohsaIncidentCode;
    }
    
    public void setOhsaIncidentCode(String ohsaIncidentCode) {
        this.ohsaIncidentCode = ohsaIncidentCode;
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

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ohsaIncident")
    public Set<OhsaIncidentDocument> getOhsaIncidentDocuments() {
        return this.ohsaIncidentDocuments;
    }
    
    public void setOhsaIncidentDocuments(Set<OhsaIncidentDocument> ohsaIncidentDocuments) {
        this.ohsaIncidentDocuments = ohsaIncidentDocuments;
    }
       
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ohsaIncident")
    public Set<OhsaEmpInvolve> getOhsaEmpInvolves() {
        return ohsaEmpInvolves;
    }

    public void setOhsaEmpInvolves(Set<OhsaEmpInvolve> ohsaEmpInvolves) {
        this.ohsaEmpInvolves = ohsaEmpInvolves;
    }
    
    @Transient
    public Long getTotalEmpInvolves() {
        return totalEmpInvolves;
    }

    public void setTotalEmpInvolves(Long totalEmpInvolves) {
        this.totalEmpInvolves = totalEmpInvolves;
    }
    
    
}


