package com.inkubator.hrm.entity;

import java.io.Serializable;
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
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "financial_non_banking", catalog = "hrm_personalia", uniqueConstraints =
        @UniqueConstraint(columnNames = "code"))
public class FinancialNonBanking implements Serializable {

    private Long id;
    private Integer version;
    private String financialService;
    private String code;
    private String name;
    private String address;
    private City city;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<FinancialPartner> financialPartners = new HashSet<FinancialPartner>(0);

    public FinancialNonBanking() {
    }

    public FinancialNonBanking(Long id) {
        this.id = id;
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

    @Column(name = "financial_service", nullable = false, length = 1)
    public String getFinancialService() {
        return financialService;
    }

    public void setFinancialService(String financialService) {
        this.financialService = financialService;
    }

    @Column(name = "name", unique = true, nullable = false, length = 65)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", unique = true, nullable = false, length = 20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name = "address", length = 65535, columnDefinition = "Text")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "financialNonBanking")
    public Set<FinancialPartner> getFinancialPartners() {
        return financialPartners;
    }

    public void setFinancialPartners(Set<FinancialPartner> financialPartners) {
        this.financialPartners = financialPartners;
    }
}
