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

/**
 *
 * @author rizkykojek
 */
@Entity
@Table(name = "recruit_applicant", catalog = "hrm"
)
public class RecruitApplicant implements Serializable {

	private Long id;
	private Integer version;
	private BioData bioData;
	private Integer careerCandidate;
	private Boolean isActive;
	private Boolean isVerified;
	private EducationLevel educationLevel;
	private Integer educationStartYear;
	private Integer educationEndYear;
	private Double score;
	private Integer scale;
	private InstitutionEducation institutionEducation;
	private String certificateNumber;
	private String lastWorkCompany;
	private Integer lastWorkSince;
	private Integer lastWorkEnd;
	private String lastJabatan;
	private KlasifikasiKerja klasifikasiKerja;
	private Integer lastJabatanSince;
	private BusinessType businessType;
	private Date createdOn;
    private String createdBy;
    private String updatedBy;    
    private Date updatedOn;
    
    public RecruitApplicant(){
    	
    }
    
    public RecruitApplicant(Long id){
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bio_data_id", nullable = false)
    public BioData getBioData() {
		return bioData;
	}

	public void setBioData(BioData bioData) {
		this.bioData = bioData;
	}

	@Column(name = "career_candidate", nullable = false)
	public Integer getCareerCandidate() {
		return careerCandidate;
	}

	public void setCareerCandidate(Integer careerCandidate) {
		this.careerCandidate = careerCandidate;
	}
	
	@Column(name = "is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "is_verified")
	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_level_id")
	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	@Column(name = "education_start_year")
	public Integer getEducationStartYear() {
		return educationStartYear;
	}

	public void setEducationStartYear(Integer educationStartYear) {
		this.educationStartYear = educationStartYear;
	}

	@Column(name = "education_end_year")
	public Integer getEducationEndYear() {
		return educationEndYear;
	}

	public void setEducationEndYear(Integer educationEndYear) {
		this.educationEndYear = educationEndYear;
	}

	@Column(name = "score")
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "scale")
	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_education_id")
	public InstitutionEducation getInstitutionEducation() {
		return institutionEducation;
	}

	public void setInstitutionEducation(InstitutionEducation institutionEducation) {
		this.institutionEducation = institutionEducation;
	}

	@Column(name = "certificate_number")
	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	@Column(name = "last_work_company")
	public String getLastWorkCompany() {
		return lastWorkCompany;
	}

	public void setLastWorkCompany(String lastWorkCompany) {
		this.lastWorkCompany = lastWorkCompany;
	}

	@Column(name = "last_work_since")
	public Integer getLastWorkSince() {
		return lastWorkSince;
	}

	public void setLastWorkSince(Integer lastWorkSince) {
		this.lastWorkSince = lastWorkSince;
	}

	@Column(name = "last_work_end")
	public Integer getLastWorkEnd() {
		return lastWorkEnd;
	}

	public void setLastWorkEnd(Integer lastWorkEnd) {
		this.lastWorkEnd = lastWorkEnd;
	}

	@Column(name = "last_jabatan")
	public String getLastJabatan() {
		return lastJabatan;
	}

	public void setLastJabatan(String lastJabatan) {
		this.lastJabatan = lastJabatan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "klasifikasi_kerja_id")
	public KlasifikasiKerja getKlasifikasiKerja() {
		return klasifikasiKerja;
	}

	public void setKlasifikasiKerja(KlasifikasiKerja klasifikasiKerja) {
		this.klasifikasiKerja = klasifikasiKerja;
	}

	@Column(name = "last_jabatan_since")
	public Integer getLastJabatanSince() {
		return lastJabatanSince;
	}

	public void setLastJabatanSince(Integer lastJabatanSince) {
		this.lastJabatanSince = lastJabatanSince;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_type_id")
	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", length = 19)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
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
    @Column(name = "updated_on", length = 19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
