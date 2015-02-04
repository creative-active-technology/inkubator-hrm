package com.inkubator.hrm.entity;

import java.io.Serializable;
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
import javax.persistence.Version;

@Entity
@Table(name = "education_non_formal", catalog = "hrm_personalia")
public class EducationNonFormal implements Serializable {

    private Long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private String address;
    private City city;
    private String postalCode;
    private String officialPhoneNo;
    private String officialEmail;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;

    public EducationNonFormal() {

    }

    public EducationNonFormal(Long id) {
        this.id = id;
    }

    public EducationNonFormal(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Column(name = "name", unique = true, nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code", unique = true, nullable = false, length = 60)
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", length = 65535, columnDefinition = "Text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "address", length = 65535, columnDefinition = "Text", nullable = false)
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

	@Column(name = "postal_code", nullable = false, length = 8)
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}	
	
	@Column(name = "official_phone_no", length = 25)
	public String getOfficialPhoneNo() {
		return officialPhoneNo;
	}

	public void setOfficialPhoneNo(String officialPhoneNo) {
		this.officialPhoneNo = officialPhoneNo;
	}

	@Column(name = "official_email", length = 100)
	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	@Column(name = "created_by", length = 45)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "updated_by", length = 45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
