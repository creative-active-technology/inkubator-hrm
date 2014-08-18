package com.inkubator.hrm.entity;
// Generated Aug 18, 2014 1:42:18 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * ApprovalDefinition generated by hbm2java
 */
@Entity
@Table(name="approval_definition"
    ,catalog="hrm"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class ApprovalDefinition  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String name;
     private Integer sequence;
     private Integer minApprover;
     private Integer minRejector;
     private String approverType;
     private String approverIndividual;
     private String approverPosition;
     private Boolean allowOnBehalf;
     private String onBehalfType;
     private String onBehalfIndividual;
     private String onBehalfPosition;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private Set<ApprovalActivity> approvalActivities = new HashSet<ApprovalActivity>(0);

    public ApprovalDefinition() {
    }

	
    public ApprovalDefinition(long id) {
        this.id = id;
    }
    public ApprovalDefinition(long id, String name, Integer sequence, Integer minApprover, Integer minRejector, String approverType, String approverIndividual, String approverPosition, Boolean allowOnBehalf, String onBehalfType, String onBehalfIndividual, String onBehalfPosition, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<ApprovalActivity> approvalActivities) {
       this.id = id;
       this.name = name;
       this.sequence = sequence;
       this.minApprover = minApprover;
       this.minRejector = minRejector;
       this.approverType = approverType;
       this.approverIndividual = approverIndividual;
       this.approverPosition = approverPosition;
       this.allowOnBehalf = allowOnBehalf;
       this.onBehalfType = onBehalfType;
       this.onBehalfIndividual = onBehalfIndividual;
       this.onBehalfPosition = onBehalfPosition;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.approvalActivities = approvalActivities;
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

    
    @Column(name="name", unique=true, length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="sequence")
    public Integer getSequence() {
        return this.sequence;
    }
    
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    
    @Column(name="min_approver")
    public Integer getMinApprover() {
        return this.minApprover;
    }
    
    public void setMinApprover(Integer minApprover) {
        this.minApprover = minApprover;
    }

    
    @Column(name="min_rejector")
    public Integer getMinRejector() {
        return this.minRejector;
    }
    
    public void setMinRejector(Integer minRejector) {
        this.minRejector = minRejector;
    }

    
    @Column(name="approver_type", length=45)
    public String getApproverType() {
        return this.approverType;
    }
    
    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    
    @Column(name="approver_individual", length=100)
    public String getApproverIndividual() {
        return this.approverIndividual;
    }
    
    public void setApproverIndividual(String approverIndividual) {
        this.approverIndividual = approverIndividual;
    }

    
    @Column(name="approver_position", length=100)
    public String getApproverPosition() {
        return this.approverPosition;
    }
    
    public void setApproverPosition(String approverPosition) {
        this.approverPosition = approverPosition;
    }

    
    @Column(name="allow_on_behalf")
    public Boolean getAllowOnBehalf() {
        return this.allowOnBehalf;
    }
    
    public void setAllowOnBehalf(Boolean allowOnBehalf) {
        this.allowOnBehalf = allowOnBehalf;
    }

    
    @Column(name="on_behalf_type", length=45)
    public String getOnBehalfType() {
        return this.onBehalfType;
    }
    
    public void setOnBehalfType(String onBehalfType) {
        this.onBehalfType = onBehalfType;
    }

    
    @Column(name="on_behalf_individual", length=100)
    public String getOnBehalfIndividual() {
        return this.onBehalfIndividual;
    }
    
    public void setOnBehalfIndividual(String onBehalfIndividual) {
        this.onBehalfIndividual = onBehalfIndividual;
    }

    
    @Column(name="on_behalf_position", length=100)
    public String getOnBehalfPosition() {
        return this.onBehalfPosition;
    }
    
    public void setOnBehalfPosition(String onBehalfPosition) {
        this.onBehalfPosition = onBehalfPosition;
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
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="approvalDefinition")
    public Set<ApprovalActivity> getApprovalActivities() {
        return this.approvalActivities;
    }
    
    public void setApprovalActivities(Set<ApprovalActivity> approvalActivities) {
        this.approvalActivities = approvalActivities;
    }




}


