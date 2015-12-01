package com.inkubator.hrm.entity;
// Generated Nov 2, 2015 8:48:04 PM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * CarreerAwardType generated by hbm2java
 */
@Entity
@Table(name="carreer_award_type"
    ,catalog="hrm"
    , uniqueConstraints = {@UniqueConstraint(columnNames="award_code"), @UniqueConstraint(columnNames="award_name")} 
)
public class CareerAwardType  implements java.io.Serializable {


     private long id;
     private Integer version;
     private SystemLetterReference systemLetterReferenceByLetterTemplateId;
     private SystemLetterReference systemLetterReferenceByCertificateLetterTemplateId;
     private String code;
     private String name;
     private String description;
     private Integer validity;
     private Double point;
     private Boolean awardSource;
     private Date createdOn;
     private String createdBy;
     private Date updatedOn;
     private String updatedBy;

    public CareerAwardType() {
    }
    
    public CareerAwardType(long id){
    	this.id = id;
    }
	
    public CareerAwardType(long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    
    
    public CareerAwardType(long id, SystemLetterReference systemLetterReferenceByLetterTemplateId, SystemLetterReference systemLetterReferenceByCertificateLetterTemplateId, String code, String name, String description, Integer validity, Double point, Date createdOn, String createdBy, Date updatedOn, String updatedBy) {
       this.id = id;
       this.systemLetterReferenceByLetterTemplateId = systemLetterReferenceByLetterTemplateId;
       this.systemLetterReferenceByCertificateLetterTemplateId = systemLetterReferenceByCertificateLetterTemplateId;
       this.code = code;
       this.name = name;
       this.description = description;
       this.validity = validity;
       this.point = point;
       this.createdOn = createdOn;
       this.createdBy = createdBy;
       this.updatedOn = updatedOn;
       this.updatedBy = updatedBy;
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
    @JoinColumn(name="letter_template_id")
    public SystemLetterReference getSystemLetterReferenceByLetterTemplateId() {
        return this.systemLetterReferenceByLetterTemplateId;
    }
    
    public void setSystemLetterReferenceByLetterTemplateId(SystemLetterReference systemLetterReferenceByLetterTemplateId) {
        this.systemLetterReferenceByLetterTemplateId = systemLetterReferenceByLetterTemplateId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="certificate_letter_template_id")
    public SystemLetterReference getSystemLetterReferenceByCertificateLetterTemplateId() {
        return this.systemLetterReferenceByCertificateLetterTemplateId;
    }
    
    public void setSystemLetterReferenceByCertificateLetterTemplateId(SystemLetterReference systemLetterReferenceByCertificateLetterTemplateId) {
        this.systemLetterReferenceByCertificateLetterTemplateId = systemLetterReferenceByCertificateLetterTemplateId;
    }

    
    @Column(name="award_code", unique=true, nullable=false, length=12)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="award_name", unique=true, nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="validity")
    public Integer getValidity() {
        return this.validity;
    }
    
    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    
    @Column(name="point", precision=22, scale=0)
    public Double getPoint() {
        return this.point;
    }
    
    public void setPoint(Double point) {
        this.point = point;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name="award_source")
	public Boolean getAwardSource() {
		return awardSource;
	}


	public void setAwardSource(Boolean awardSource) {
		this.awardSource = awardSource;
	}
}


