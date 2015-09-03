/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.webcore.util.FacesUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "reimbursment", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Reimbursment implements java.io.Serializable {

    private Long id;
    private Integer version;
    private ReimbursmentSchema reimbursmentSchema;
    private EmpData empData;
    private String code;
    private Date claimDate;
    private Integer quantity;
    private BigDecimal nominal;
    private String approvalActivityNumber;
    private byte[] reimbursmentDocument;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private BigDecimal basicValueOrNominal;

    public Reimbursment() {
    }

    public Reimbursment(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "reimbursment_seq_gen")
    @Column(name = "id", unique = true, nullable = false)
    @SequenceGenerator(name = "reimbursment_seq_gen", sequenceName = "REIMBURSMENT_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reimbursment_schema_id", nullable = false)
    public ReimbursmentSchema getReimbursmentSchema() {
        return reimbursmentSchema;
    }

    public void setReimbursmentSchema(ReimbursmentSchema reimbursmentSchema) {
        this.reimbursmentSchema = reimbursmentSchema;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    public EmpData getEmpData() {
        return empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @Column(name = "code", unique = true, nullable = false, length = 60)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "claim_date", length = 19)
    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "nominal", precision = 10, scale = 0)
    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    @Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Column(name = "reimbursment_document", columnDefinition = "blob")
    public byte[] getReimbursmentDocument() {
        return reimbursmentDocument;
    }

    public void setReimbursmentDocument(byte[] reimbursmentDocument) {
        this.reimbursmentDocument = reimbursmentDocument;
    }

    @Column(name = "approval_activity_number", length = 45, unique = true)
    public String getApprovalActivityNumber() {
        return approvalActivityNumber;
    }

    public void setApprovalActivityNumber(String approvalActivityNumber) {
        this.approvalActivityNumber = approvalActivityNumber;
    }

    public BigDecimal getBasicValueOrNominal() {
        if (reimbursmentSchema.getRatioSalary() != null) {
            basicValueOrNominal = new BigDecimal(reimbursmentSchema.getRatioSalary());
        } else {
            basicValueOrNominal = reimbursmentSchema.getNominalUnit();
        }
        return basicValueOrNominal;
    }

    @Transient
    public String getXSalary() {
        ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));

        String data = "";
        if (reimbursmentSchema.getRatioSalary() != null) {
            data = "x " + messages.getString("global.salary");
        }
        return data;
    }

    public void setBasicValueOrNominal(BigDecimal basicValueOrNominal) {
        this.basicValueOrNominal = basicValueOrNominal;
    }

}
