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
import javax.persistence.Version;

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "company", catalog="hrm_payroll")
public class Company implements Serializable {

	private Long id;
    private Integer version;
    
    private String code;
    private byte[] companyLogo;
    private String companyLogoName;
    private String name;    
    private String officialName;
    private String legalNo;
    private String level;
    private Country taxCountry;
    private String taxAccountNumber;
    private String address;
    private City city;
    private BusinessType businessType;
    private Integer postalCode;
    private String phone;
    private String fax;
    private String vision;
    private String mision;
    
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private Set<CompanyBankAccount> companyBankAccounts = new HashSet<CompanyBankAccount>(0);
    private Set<FinancialPartner> financialPartners = new HashSet<FinancialPartner>(0);
    
    public Company(){
    	
    }
    
    public Company(Long id){
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
    
    @Column(name = "code", unique = true, nullable = false, length = 12)
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
		
	@Column(name = "company_logo",columnDefinition="blob")
	public byte[] getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	@Column(name = "company_logo_name")
	public String getCompanyLogoName() {
		return companyLogoName;
	}

	public void setCompanyLogoName(String companyLogoName) {
		this.companyLogoName = companyLogoName;
	}

	@Column(name = "name", nullable = false, length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "official_name", nullable = false, length = 60)
	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	@Column(name = "legal_no", unique = true, nullable = false, length = 60)
	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

	@Column(name = "level", nullable = false, length = 1)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_country_id", nullable = false)
	public Country getTaxCountry() {
		return taxCountry;
	}

	public void setTaxCountry(Country taxCountry) {
		this.taxCountry = taxCountry;
	}

	@Column(name = "tax_account_number", unique = true, nullable = false, length = 60)
	public String getTaxAccountNumber() {
		return taxAccountNumber;
	}

	public void setTaxAccountNumber(String taxAccountNumber) {
		this.taxAccountNumber = taxAccountNumber;
	}

	@Column(name = "address", nullable = false, length = 65535, columnDefinition = "Text")
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
		
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_id", nullable = false)
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Column(name = "postal_code", nullable = false, length = 12)
	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "phone", unique = true, nullable = false, length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "fax", length = 20)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "vision", nullable = false, length = 65535, columnDefinition = "Text")
	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	@Column(name = "mision", nullable = false, length = 65535, columnDefinition = "Text")
	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyBankAccount> getCompanyBankAccounts() {
		return companyBankAccounts;
	}

	public void setCompanyBankAccounts(Set<CompanyBankAccount> companyBankAccounts) {
		this.companyBankAccounts = companyBankAccounts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<FinancialPartner> getFinancialPartners() {
		return financialPartners;
	}

	public void setFinancialPartners(Set<FinancialPartner> financialPartners) {
		this.financialPartners = financialPartners;
	}
    
}
