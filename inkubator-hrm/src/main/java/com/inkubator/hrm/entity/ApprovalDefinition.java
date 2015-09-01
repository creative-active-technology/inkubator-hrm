package com.inkubator.hrm.entity;
// Generated Aug 18, 2014 1:42:18 PM by Hibernate Tools 3.6.0

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

import org.apache.commons.lang3.StringUtils;

/**
 * ApprovalDefinition generated by hbm2java
 */
@Entity
@Table(name = "approval_definition", catalog = "hrm")
public class ApprovalDefinition implements java.io.Serializable {

    private Long id;
    private Integer version;
    private HrmUser hrmUserByOnBehalfIndividual;
    private Jabatan jabatanByApproverPosition;
    private HrmUser hrmUserByApproverIndividual;
    private Jabatan jabatanByOnBehalfPosition;
    private String name;
    private Integer sequence;
    private Integer minApprover;
    private Integer minRejector;
    private String processType;
    private String approverType;
    private Boolean allowOnBehalf;
    private String onBehalfType;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Boolean autoApproveOnDelay;
    private Boolean escalateOnDelay;
    private Boolean isActive;
    private Integer delayTime;
    private String specificName;
    private Boolean isNoLongerInUse;
    private Boolean smsNotification;
    private Set<ApprovalActivity> approvalActivities = new HashSet<ApprovalActivity>(0);
    private Set<ApprovalDefinitionLeave> approvalDefinitionLeaves = new HashSet<ApprovalDefinitionLeave>(0);
    private Set<ApprovalDefinitionOT> approvalDefinitionOverTimes = new HashSet<ApprovalDefinitionOT>(0);
    private Set<ApprovalDefinitionRmbsSchema> approvalDefinitionRmbsSchemas = new HashSet<ApprovalDefinitionRmbsSchema>(0);

    public ApprovalDefinition() {
    }

    public ApprovalDefinition(long id) {
        this.id = id;
    }

    public ApprovalDefinition(long id, HrmUser hrmUserByOnBehalfIndividual, Jabatan jabatanByApproverPosition, HrmUser hrmUserByApproverIndividual, Jabatan jabatanByOnBehalfPosition, String name, Integer sequence, Integer minApprover, Integer minRejector, String processType, String approverType, Boolean allowOnBehalf, String onBehalfType, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<ApprovalActivity> approvalActivities, Set<ApprovalDefinitionLeave> approvalDefinitionLeaves, Set<ApprovalDefinitionOT> approvalDefinitionOTs) {
        this.id = id;
        this.hrmUserByOnBehalfIndividual = hrmUserByOnBehalfIndividual;
        this.jabatanByApproverPosition = jabatanByApproverPosition;
        this.hrmUserByApproverIndividual = hrmUserByApproverIndividual;
        this.jabatanByOnBehalfPosition = jabatanByOnBehalfPosition;
        this.name = name;
        this.sequence = sequence;
        this.minApprover = minApprover;
        this.minRejector = minRejector;
        this.processType = processType;
        this.approverType = approverType;
        this.allowOnBehalf = allowOnBehalf;
        this.onBehalfType = onBehalfType;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.approvalActivities = approvalActivities;
        this.approvalDefinitionLeaves = approvalDefinitionLeaves;
        this.approvalDefinitionOverTimes = approvalDefinitionOTs;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
    @JoinColumn(name = "on_behalf_individual")
    public HrmUser getHrmUserByOnBehalfIndividual() {
        return this.hrmUserByOnBehalfIndividual;
    }

    public void setHrmUserByOnBehalfIndividual(HrmUser hrmUserByOnBehalfIndividual) {
        this.hrmUserByOnBehalfIndividual = hrmUserByOnBehalfIndividual;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_position")
    public Jabatan getJabatanByApproverPosition() {
        return this.jabatanByApproverPosition;
    }

    public void setJabatanByApproverPosition(Jabatan jabatanByApproverPosition) {
        this.jabatanByApproverPosition = jabatanByApproverPosition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver_individual")
    public HrmUser getHrmUserByApproverIndividual() {
        return this.hrmUserByApproverIndividual;
    }

    public void setHrmUserByApproverIndividual(HrmUser hrmUserByApproverIndividual) {
        this.hrmUserByApproverIndividual = hrmUserByApproverIndividual;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "on_behalf_position")
    public Jabatan getJabatanByOnBehalfPosition() {
        return this.jabatanByOnBehalfPosition;
    }

    public void setJabatanByOnBehalfPosition(Jabatan jabatanByOnBehalfPosition) {
        this.jabatanByOnBehalfPosition = jabatanByOnBehalfPosition;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sequence")
    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Column(name = "min_approver")
    public Integer getMinApprover() {
        return this.minApprover;
    }

    public void setMinApprover(Integer minApprover) {
        this.minApprover = minApprover;
    }

    @Column(name = "min_rejector")
    public Integer getMinRejector() {
        return this.minRejector;
    }

    public void setMinRejector(Integer minRejector) {
        this.minRejector = minRejector;
    }

    @Column(name = "process_type", length = 45)
    public String getProcessType() {
        return this.processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    @Column(name = "approver_type", length = 45)
    public String getApproverType() {
        return this.approverType;
    }

    public void setApproverType(String approverType) {
        this.approverType = approverType;
    }

    @Column(name = "allow_on_behalf")
    public Boolean getAllowOnBehalf() {
        return this.allowOnBehalf;
    }

    public void setAllowOnBehalf(Boolean allowOnBehalf) {
        this.allowOnBehalf = allowOnBehalf;
    }

    @Column(name = "on_behalf_type", length = 45)
    public String getOnBehalfType() {
        return this.onBehalfType;
    }

    public void setOnBehalfType(String onBehalfType) {
        this.onBehalfType = onBehalfType;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "auto_approve_on_delay")
    public Boolean getAutoApproveOnDelay() {
        return this.autoApproveOnDelay;
    }

    public void setAutoApproveOnDelay(Boolean autoApproveOnDelay) {
        this.autoApproveOnDelay = autoApproveOnDelay;
    }

    @Column(name = "escalate_on_delay")
    public Boolean getEscalateOnDelay() {
        return this.escalateOnDelay;
    }

    public void setEscalateOnDelay(Boolean escalateOnDelay) {
        this.escalateOnDelay = escalateOnDelay;
    }

    @Column(name = "delay_time")
    public Integer getDelayTime() {
        return this.delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    @Column(name = "specific_name", length = 100)
    public String getSpecificName() {
        return specificName;
    }

    public void setSpecificName(String specificName) {
        this.specificName = specificName;
    }

    @Column(name = "is_no_longer_in_use")
    public Boolean getIsNoLongerInUse() {
        return isNoLongerInUse;
    }

    public void setIsNoLongerInUse(Boolean isNoLongerInUse) {
        this.isNoLongerInUse = isNoLongerInUse;
    }

    @Column(name = "sms_notification")
    public Boolean getSmsNotification() {
        return smsNotification;
    }

    public void setSmsNotification(Boolean smsNotification) {
        this.smsNotification = smsNotification;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "approvalDefinition")
    public Set<ApprovalActivity> getApprovalActivities() {
        return this.approvalActivities;
    }

    public void setApprovalActivities(Set<ApprovalActivity> approvalActivities) {
        this.approvalActivities = approvalActivities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "approvalDefinition")
    public Set<ApprovalDefinitionLeave> getApprovalDefinitionLeaves() {
        return approvalDefinitionLeaves;
    }

    public void setApprovalDefinitionLeaves(Set<ApprovalDefinitionLeave> approvalDefinitionLeaves) {
        this.approvalDefinitionLeaves = approvalDefinitionLeaves;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "approvalDefinition")
    public Set<ApprovalDefinitionOT> getApprovalDefinitionOverTimes() {
        return this.approvalDefinitionOverTimes;
    }

    public void setApprovalDefinitionOverTimes(Set<ApprovalDefinitionOT> approvalDefinitionOverTimes) {
        this.approvalDefinitionOverTimes = approvalDefinitionOverTimes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "approvalDefinition")
    public Set<ApprovalDefinitionRmbsSchema> getApprovalDefinitionRmbsSchemas() {
        return approvalDefinitionRmbsSchemas;
    }

    public void setApprovalDefinitionRmbsSchemas(Set<ApprovalDefinitionRmbsSchema> approvalDefinitionRmbsSchemas) {
        this.approvalDefinitionRmbsSchemas = approvalDefinitionRmbsSchemas;
    }

    @Transient
    public Boolean getIsHaveManyToManyRelations() {
        return StringUtils.isNotEmpty(this.specificName);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ApprovalDefinition other = (ApprovalDefinition) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Column(name = "is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}
