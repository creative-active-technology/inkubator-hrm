package com.inkubator.hrm.entity;
// Generated Sep 5, 2014 3:37:42 PM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * AppraisalElement generated by hbm2java
 */
@Entity
@Table(name="appraisal_element"
    ,catalog="hrm_payroll"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class AppraisalElement  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String description;
     private String name;
     private String updatedBy;
     private Date updatedOn;
     private Integer operator;
     private Integer leader;

    public AppraisalElement() {
    }

	
    public AppraisalElement(long id) {
        this.id = id;
    }
    public AppraisalElement(long id, String createdBy, Date createdOn, String description, String name, String updatedBy, Date updatedOn, Integer operator, Integer leader) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.description = description;
       this.name = name;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.operator = operator;
       this.leader = leader;
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

    
    @Column(name="description")
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="name", unique=true, length=60)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    
    @Column(name="operator")
    public Integer getOperator() {
        return this.operator;
    }
    
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    
    @Column(name="leader")
    public Integer getLeader() {
        return this.leader;
    }
    
    public void setLeader(Integer leader) {
        this.leader = leader;
    }




}


