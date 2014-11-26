package com.inkubator.hrm.entity;
// Generated Nov 24, 2014 11:16:03 AM by Hibernate Tools 4.3.1

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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * PaySalaryComponent generated by hbm2java
 */
@Entity
@Table(name = "pay_salary_component", catalog = "hrm", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name"),
    @UniqueConstraint(columnNames = "code")}
)
public class PaySalaryComponent implements java.io.Serializable {

    private long id;
    private Integer version;
    private ModelComponent modelComponent;
    private PaySalaryJurnal paySalaryJurnal;
    private TaxComponent taxComponent;
    private String code;
    private String name;
    private Boolean renumeration;
    private String formula;
    private Integer componentCategory;
    private Boolean resetData;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<PaySalaryEmpType> paySalaryEmpTypes = new HashSet<PaySalaryEmpType>(0);
    private Integer modelReffernsil;

    public PaySalaryComponent() {
    }

    public PaySalaryComponent(long id) {
        this.id = id;
    }

    public PaySalaryComponent(long id, ModelComponent modelComponent, PaySalaryJurnal paySalaryJurnal, TaxComponent taxComponent, String code, String name, Boolean renumeration, String formula, Integer componentCategory, Boolean resetData, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<PaySalaryEmpType> paySalaryEmpTypes) {
        this.id = id;
        this.modelComponent = modelComponent;
        this.paySalaryJurnal = paySalaryJurnal;
        this.taxComponent = taxComponent;
        this.code = code;
        this.name = name;
        this.renumeration = renumeration;
        this.formula = formula;
        this.componentCategory = componentCategory;
        this.resetData = resetData;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.paySalaryEmpTypes = paySalaryEmpTypes;
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
    @JoinColumn(name = "model_component_id")
    public ModelComponent getModelComponent() {
        return this.modelComponent;
    }

    public void setModelComponent(ModelComponent modelComponent) {
        this.modelComponent = modelComponent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paysalary_jurnal_id")
    public PaySalaryJurnal getPaySalaryJurnal() {
        return this.paySalaryJurnal;
    }

    public void setPaySalaryJurnal(PaySalaryJurnal paySalaryJurnal) {
        this.paySalaryJurnal = paySalaryJurnal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_component_id")
    public TaxComponent getTaxComponent() {
        return this.taxComponent;
    }

    public void setTaxComponent(TaxComponent taxComponent) {
        this.taxComponent = taxComponent;
    }

    @Column(name = "code", unique = true, length = 45)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", unique = true, length = 60)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "renumeration")
    public Boolean getRenumeration() {
        return this.renumeration;
    }

    public void setRenumeration(Boolean renumeration) {
        this.renumeration = renumeration;
    }

    @Column(name = "formula")
    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    @Column(name = "component_category")
    public Integer getComponentCategory() {
        return this.componentCategory;
    }

    public void setComponentCategory(Integer componentCategory) {
        this.componentCategory = componentCategory;
    }

    @Column(name = "reset_data")
    public Boolean getResetData() {
        return this.resetData;
    }

    public void setResetData(Boolean resetData) {
        this.resetData = resetData;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paySalaryComponent")
    public Set<PaySalaryEmpType> getPaySalaryEmpTypes() {
        return this.paySalaryEmpTypes;
    }

    public void setPaySalaryEmpTypes(Set<PaySalaryEmpType> paySalaryEmpTypes) {
        this.paySalaryEmpTypes = paySalaryEmpTypes;
    }

    @Column(name = "model_refferensi")
    public Integer getModelReffernsil() {
        return modelReffernsil;
    }

    public void setModelReffernsil(Integer modelReffernsil) {
        this.modelReffernsil = modelReffernsil;
    }

}
