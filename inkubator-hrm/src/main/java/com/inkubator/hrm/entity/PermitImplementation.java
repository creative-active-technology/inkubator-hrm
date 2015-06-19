package com.inkubator.hrm.entity;
// Generated Nov 13, 2014 9:33:14 AM by Hibernate Tools 3.6.0

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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

/**
 * PermitImplementation generated by hbm2java
 */
@Entity
@Table(name = "permit_implementation", catalog="hrm_payroll_backup", uniqueConstraints = @UniqueConstraint(columnNames = "number_filling")
)
public class PermitImplementation implements java.io.Serializable {

    private long id;
    private Integer version;
    private EmpData empData;
    private PermitClassification permitClassification;
    private String approvalActivityNumber;
    private String createdBy;
    private Date createdOn;
    private String description;
    private Date endDate;
    private Date fillingDate;
    private String numberFilling;
    private Date startDate;
    private String updatedBy;
    private Date updatedOn;
    private String uploadPath;

    public PermitImplementation() {
    }

    public PermitImplementation(long id, EmpData empData, PermitClassification permitClassification, Date endDate, Date fillingDate, String numberFilling, Date startDate) {
        this.id = id;
        this.empData = empData;
        this.permitClassification = permitClassification;
        this.endDate = endDate;
        this.fillingDate = fillingDate;
        this.numberFilling = numberFilling;
        this.startDate = startDate;
    }

    public PermitImplementation(long id, EmpData empData, PermitClassification permitClassification, String createdBy, Date createdOn, String description, Date endDate, Date fillingDate, String numberFilling, Date startDate, String updatedBy, Date updatedOn, String uploadPath) {
        this.id = id;
        this.empData = empData;
        this.permitClassification = permitClassification;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.description = description;
        this.endDate = endDate;
        this.fillingDate = fillingDate;
        this.numberFilling = numberFilling;
        this.startDate = startDate;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.uploadPath = uploadPath;
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
    @JoinColumn(name = "emp_data_id", nullable = false)
    public EmpData getEmpData() {
        return this.empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permit_classification_id", nullable = false)
    public PermitClassification getPermitClassification() {
        return this.permitClassification;
    }

    public void setPermitClassification(PermitClassification permitClassification) {
        this.permitClassification = permitClassification;
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

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = false, length = 19)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "filling_date", nullable = false, length = 19)
    public Date getFillingDate() {
        return this.fillingDate;
    }

    public void setFillingDate(Date fillingDate) {
        this.fillingDate = fillingDate;
    }

    @Column(name = "number_filling", unique = true, nullable = false, length = 60)
    public String getNumberFilling() {
        return this.numberFilling;
    }

    public void setNumberFilling(String numberFilling) {
        this.numberFilling = numberFilling;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false, length = 19)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    @Column(name = "upload_path", length = 100)
    public String getUploadPath() {
        return this.uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Transient
    public String getUploadFileName() {
        String fileName = StringUtils.EMPTY;
        if (uploadPath != null) {
            fileName = StringUtils.substringAfterLast(uploadPath, "/");
        }
        return fileName;
    }

    @Transient
    public String getUploadFileExtension() {
        String fileName = StringUtils.EMPTY;
        if (uploadPath != null) {
            fileName = StringUtils.substringAfterLast(uploadPath, ".");
        }
        return fileName;
    }
    
    @Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

}
