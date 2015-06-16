/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.entity;

import java.math.BigDecimal;
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
import javax.persistence.Version;

/**
 *
 * @author Deni
 */
@Entity
@Table(name = "pay_component_data_exception", catalog="hrm_payroll"
)
public class PayComponentDataException implements java.io.Serializable {

    private Long id;
    private Integer version;
    private EmpData empData;
    private PaySalaryComponent paySalaryComponent;
    private BigDecimal nominal;
    private Boolean resetAfterMonth;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Integer jumlahKaryawan;
    private BigDecimal jumlahNominal;

    public PayComponentDataException() {
    }

    public PayComponentDataException(long id) {
        this.id = id;
    }

    public PayComponentDataException(long id, EmpData empData, PaySalaryComponent paySalaryComponent, BigDecimal nominal, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.empData = empData;
        this.paySalaryComponent = paySalaryComponent;
        this.nominal = nominal;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
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
    @JoinColumn(name = "emp_id")
    public EmpData getEmpData() {
        return this.empData;
    }

    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_component_id")
    public PaySalaryComponent getPaySalaryComponent() {
        return this.paySalaryComponent;
    }

    public void setPaySalaryComponent(PaySalaryComponent paySalaryComponent) {
        this.paySalaryComponent = paySalaryComponent;
    }

    @Column(name = "nominal", precision = 10, scale = 0)
    public BigDecimal getNominal() {
        return this.nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
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

    @Column(name = "reset_after_month")
    public Boolean getResetAfterMonth() {
        return resetAfterMonth;
    }

    public void setResetAfterMonth(Boolean resetAfterMonth) {
        this.resetAfterMonth = resetAfterMonth;
    }

    @Transient
    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    @Transient
    public BigDecimal getJumlahNominal() {
        return jumlahNominal;
    }

    public void setJumlahNominal(BigDecimal jumlahNominal) {
        this.jumlahNominal = jumlahNominal;
    }
    
    
}
