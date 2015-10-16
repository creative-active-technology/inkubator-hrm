package com.inkubator.hrm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name = "recruit_applicant", catalog = "hrm"
)
public class RecruitApplicant implements Serializable {

	private Long id;
	private Integer version;
	private BioData bioData;
	private Integer careerCandidate;
	private Boolean isFreshGraduate;
	private Boolean isActive;
	private Boolean isVerified;
	private EducationLevel educationLevel;
	private Integer educationStartYear;
	private Integer educationEndYear;
	private Double score;
	private Double scale;
	private InstitutionEducation institutionEducation;
	private String certificateNumber;
	private String lastWorkCompany;
	private Integer lastWorkSince;
	private Integer lastWorkEnd;
	private String lastJabatan;
	private KlasifikasiKerja klasifikasiKerja;
	private Integer lastJabatanSince;
	private BusinessType businessType;
	private RecruitVacancyAdvertisement recruitVacancyAdvertisement;
	private String uploadPath;
	private Date createdOn;
    private String createdBy;
    private String updatedBy;    
    private Date updatedOn;
    private Set<RecruitApplicantSpecList> recruitApplicantSpecLists = new HashSet<RecruitApplicantSpecList>();
    
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
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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
	
	@Column(name = "is_fresh_graduate")
	public Boolean getIsFreshGraduate() {
		return isFreshGraduate;
	}

	public void setIsFreshGraduate(Boolean isFreshGraduate) {
		this.isFreshGraduate = isFreshGraduate;
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
	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_vacancy_advertisement_id", nullable = false)
	public RecruitVacancyAdvertisement getRecruitVacancyAdvertisement() {
		return recruitVacancyAdvertisement;
	}

	public void setRecruitVacancyAdvertisement(RecruitVacancyAdvertisement recruitVacancyAdvertisement) {
		this.recruitVacancyAdvertisement = recruitVacancyAdvertisement;
	}

	@Column(name = "upload_path")
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitApplicant", cascade = CascadeType.REMOVE)
	public Set<RecruitApplicantSpecList> getRecruitApplicantSpecLists() {
		return recruitApplicantSpecLists;
	}

	public void setRecruitApplicantSpecLists(Set<RecruitApplicantSpecList> recruitApplicantSpecLists) {
		this.recruitApplicantSpecLists = recruitApplicantSpecLists;
	}    
    
}
