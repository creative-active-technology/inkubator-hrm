package com.inkubator.hrm.entity;

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
 * @author Deni
 */
@Entity
@Table(name = "insurance", 
catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class Insurance implements java.io.Serializable {
	private Long id;
	private String code;
	private String name;
	private String description;
	private Integer postalCode;
	private Integer polisNo;
	private String insuranceProduct;
	private String address;
	private City city;
	private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Integer version;
    
    
    
    public Insurance() {
	}

    
    
	public Insurance(Long id) {
		this.id = id;
	}



	public Insurance(Long id, String code, String name, String description,
			Integer postalCode, Integer polisNo, String insuranceProduct,
			String address, City city, String createdBy, Date createdOn,
			String updatedBy, Date updatedOn, Integer version) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.postalCode = postalCode;
		this.polisNo = polisNo;
		this.insuranceProduct = insuranceProduct;
		this.address = address;
		this.city = city;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.version = version;
	}
    
	@Id
    @Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "code", unique = true, nullable = false, length = 12)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "postal_code", length = 12)
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	@Column(name = "no_polis", length = 60)
	public Integer getPolisNo() {
		return polisNo;
	}
	public void setPolisNo(Integer polisNo) {
		this.polisNo = polisNo;
	}
	@Column(name = "insurance_product", length = 60)
	public String getInsuranceProduct() {
		return insuranceProduct;
	}
	public void setInsuranceProduct(String insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}
	@Column(name = "address", length = 255)
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
	@Version
    @Column(name = "version")
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
    
    
	
	
}