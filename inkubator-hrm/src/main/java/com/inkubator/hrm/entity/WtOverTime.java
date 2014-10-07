package com.inkubator.hrm.entity;
// Generated May 29, 2014 11:36:01 AM by Hibernate Tools 3.6.0

import com.inkubator.hrm.HRMConstant;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * WtOverTime generated by hbm2java
 */
@Entity
@Table(name = "wt_over_time", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class WtOverTime implements java.io.Serializable {

    private long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private Date minimumTime;
    private Date maximumTime;
    private Integer overTimeCalculation;
    private Integer otRounding;
    private Date startTimeFactor;
    private Date finishTimeFactor;
    private Double valuePrice;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<ApprovalDefinitionOT> approvalDefinitionOTs = new HashSet<ApprovalDefinitionOT>(0);

    public WtOverTime() {
    }

    public WtOverTime(long id) {
        this.id = id;
    }

    public WtOverTime(long id, String code, String name, String description, Date minimumTime, Date maximumTime, Integer overTimeCalculation, Integer otRounding, Date startTimeFactor, Date finishTimeFactor, Double valuePrice, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<ApprovalDefinitionOT> approvalDefinitionOTs) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.minimumTime = minimumTime;
        this.maximumTime = maximumTime;
        this.overTimeCalculation = overTimeCalculation;
        this.otRounding = otRounding;
        this.startTimeFactor = startTimeFactor;
        this.finishTimeFactor = finishTimeFactor;
        this.valuePrice = valuePrice;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.approvalDefinitionOTs = approvalDefinitionOTs;
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

    @Column(name = "code", unique = true, length = 6)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", length = 45)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 65535, columnDefinition="Text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "minimum_time", length = 8)
    public Date getMinimumTime() {
        return this.minimumTime;
    }

    public void setMinimumTime(Date minimumTime) {
        this.minimumTime = minimumTime;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "maximum_time", length = 8)
    public Date getMaximumTime() {
        return this.maximumTime;
    }

    public void setMaximumTime(Date maximumTime) {
        this.maximumTime = maximumTime;
    }

    @Column(name = "over_time_calculation")
    public Integer getOverTimeCalculation() {
        return this.overTimeCalculation;
    }

    public void setOverTimeCalculation(Integer overTimeCalculation) {
        this.overTimeCalculation = overTimeCalculation;
    }

    @Column(name = "ot_rounding")
    public Integer getOtRounding() {
        return this.otRounding;
    }

    public void setOtRounding(Integer otRounding) {
        this.otRounding = otRounding;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "start_time_factor", length = 8)
    public Date getStartTimeFactor() {
        return this.startTimeFactor;
    }

    public void setStartTimeFactor(Date startTimeFactor) {
        this.startTimeFactor = startTimeFactor;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "finish_time_factor", length = 8)
    public Date getFinishTimeFactor() {
        return this.finishTimeFactor;
    }

    public void setFinishTimeFactor(Date finishTimeFactor) {
        this.finishTimeFactor = finishTimeFactor;
    }

    @Column(name = "value_price", precision = 22, scale = 0)
    public Double getValuePrice() {
        return this.valuePrice;
    }

    public void setValuePrice(Double valuePrice) {
        this.valuePrice = valuePrice;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wtOverTime")
    public Set<ApprovalDefinitionOT> getApprovalDefinitionOTs() {
        return approvalDefinitionOTs;
    }
    
    public void setApprovalDefinitionOTs(Set<ApprovalDefinitionOT> approvalDefinitionOTs) {
        this.approvalDefinitionOTs = approvalDefinitionOTs;
    }
    
    @Transient
    public String getOTCalculationAsString() {
        if (Objects.equals(getOverTimeCalculation(), HRMConstant.OT_SEPARATED)) {
            return "Separated";
        }

        if (Objects.equals(getOverTimeCalculation(), HRMConstant.OT_SUMMARY)) {
            return "Sumarry";
        }

        return null;
    }

}
