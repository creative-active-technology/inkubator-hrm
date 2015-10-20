package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DualListModel;

import com.inkubator.hrm.entity.BusinessType;
import com.inkubator.hrm.entity.City;
import com.inkubator.hrm.entity.Dialect;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.MaritalStatus;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.entity.Race;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.entity.Religion;

/**
 *
 * @author rizkykojek
 */
public class ApplicantModel implements Serializable {
	
	//Applicant
	private Long id;
    private Long educationLevelId;
    private Long institutionEducationId;
    private Integer educationStartYear;
    private Integer educationEndYear;
    private Double score;
    private Double scale;
    private String certificateNumber;
    private String lastWorkCompany;
	private Integer lastWorkSince;
	private Integer lastWorkEnd;
	private String lastJabatan;
	private Long klasifikasiKerjaId;
	private Integer lastJabatanSince;
	private Long businessTypeId;
	private Long vacancyAdvertisementId;
	private Long vacancyAdvertisementDetailId;
	private Boolean isFreshGraduate;
	private Boolean isActive;
	private Boolean isVerified;
	private String uploadPath;
	
	//Bio Data
	private Long bioDataId;
	private String firstName;
    private String lastName;
    private String title;
    private String nickname;
    private Date dateOfBirth;
    private Long cityOfBirthId;
    private Integer gender;
    private Long maritalStatusId; 
    private String noKK;
    private Integer bloodType;
    private String npwp;
    private String jamsostek;
    private Long nationalityId;
    private Long dialectId;
    private Long religionId;
    private Long raceId;
    private String phoneNumber;
    private String emailAddress;
	
	//List
	private List<MaritalStatus> listMaritalStatus;
	private List<Religion> listReligion;
	private List<Race> listRace;
	private List<Dialect> listDialect;
	private List<Nationality> listNationality;
	private List<EducationLevel> listEducationLevel;
	private List<InstitutionEducation> listInstitutionEducation;
	private List<BusinessType> listBusinessType;
	private List<KlasifikasiKerja> listKlasifikasiKerja;
	private List<RecruitVacancyAdvertisement> listVacancyAdvertisement;
	private List<RecruitVacancyAdvertisementDetail> listVacancyAdvertisementDetail;
	private List<City> listCity;
	private List<DualListModel> specListDualModel; 
	private List<String> specListName; 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getCityOfBirthId() {
		return cityOfBirthId;
	}
	public void setCityOfBirthId(Long cityOfBirthId) {
		this.cityOfBirthId = cityOfBirthId;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Long getMaritalStatusId() {
		return maritalStatusId;
	}
	public void setMaritalStatusId(Long maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}
	public String getNoKK() {
		return noKK;
	}
	public void setNoKK(String noKK) {
		this.noKK = noKK;
	}
	public Integer getBloodType() {
		return bloodType;
	}
	public void setBloodType(Integer bloodType) {
		this.bloodType = bloodType;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}
	public String getJamsostek() {
		return jamsostek;
	}
	public void setJamsostek(String jamsostek) {
		this.jamsostek = jamsostek;
	}
	public Long getNationalityId() {
		return nationalityId;
	}
	public void setNationalityId(Long nationalityId) {
		this.nationalityId = nationalityId;
	}
	public Long getDialectId() {
		return dialectId;
	}
	public void setDialectId(Long dialectId) {
		this.dialectId = dialectId;
	}
	public Long getReligionId() {
		return religionId;
	}
	public void setReligionId(Long religionId) {
		this.religionId = religionId;
	}
	public Long getRaceId() {
		return raceId;
	}
	public void setRaceId(Long raceId) {
		this.raceId = raceId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getEducationLevelId() {
		return educationLevelId;
	}
	public void setEducationLevelId(Long educationLevelId) {
		this.educationLevelId = educationLevelId;
	}
	public Long getInstitutionEducationId() {
		return institutionEducationId;
	}
	public void setInstitutionEducationId(Long institutionEducationId) {
		this.institutionEducationId = institutionEducationId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getCertificateNumber() {
		return certificateNumber;
	}
	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}
	public String getLastWorkCompany() {
		return lastWorkCompany;
	}
	public void setLastWorkCompany(String lastWorkCompany) {
		this.lastWorkCompany = lastWorkCompany;
	}
	public Integer getLastWorkSince() {
		return lastWorkSince;
	}
	public void setLastWorkSince(Integer lastWorkSince) {
		this.lastWorkSince = lastWorkSince;
	}
	public Integer getLastWorkEnd() {
		return lastWorkEnd;
	}
	public void setLastWorkEnd(Integer lastWorkEnd) {
		this.lastWorkEnd = lastWorkEnd;
	}
	public String getLastJabatan() {
		return lastJabatan;
	}
	public void setLastJabatan(String lastJabatan) {
		this.lastJabatan = lastJabatan;
	}
	public Long getKlasifikasiKerjaId() {
		return klasifikasiKerjaId;
	}
	public void setKlasifikasiKerjaId(Long klasifikasiKerjaId) {
		this.klasifikasiKerjaId = klasifikasiKerjaId;
	}
	public Integer getLastJabatanSince() {
		return lastJabatanSince;
	}
	public void setLastJabatanSince(Integer lastJabatanSince) {
		this.lastJabatanSince = lastJabatanSince;
	}
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Long getVacancyAdvertisementId() {
		return vacancyAdvertisementId;
	}
	public void setVacancyAdvertisementId(Long vacancyAdvertisementId) {
		this.vacancyAdvertisementId = vacancyAdvertisementId;
	}
	public Boolean getIsFreshGraduate() {
		return isFreshGraduate;
	}
	public void setIsFreshGraduate(Boolean isFreshGraduate) {
		this.isFreshGraduate = isFreshGraduate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public List<MaritalStatus> getListMaritalStatus() {
		return listMaritalStatus;
	}
	public void setListMaritalStatus(List<MaritalStatus> listMaritalStatus) {
		this.listMaritalStatus = listMaritalStatus;
	}
	public List<Religion> getListReligion() {
		return listReligion;
	}
	public void setListReligion(List<Religion> listReligion) {
		this.listReligion = listReligion;
	}
	public List<Race> getListRace() {
		return listRace;
	}
	public void setListRace(List<Race> listRace) {
		this.listRace = listRace;
	}
	public List<Dialect> getListDialect() {
		return listDialect;
	}
	public void setListDialect(List<Dialect> listDialect) {
		this.listDialect = listDialect;
	}
	public List<Nationality> getListNationality() {
		return listNationality;
	}
	public void setListNationality(List<Nationality> listNationality) {
		this.listNationality = listNationality;
	}
	public List<EducationLevel> getListEducationLevel() {
		return listEducationLevel;
	}
	public void setListEducationLevel(List<EducationLevel> listEducationLevel) {
		this.listEducationLevel = listEducationLevel;
	}
	public List<InstitutionEducation> getListInstitutionEducation() {
		return listInstitutionEducation;
	}
	public void setListInstitutionEducation(List<InstitutionEducation> listInstitutionEducation) {
		this.listInstitutionEducation = listInstitutionEducation;
	}
	public List<BusinessType> getListBusinessType() {
		return listBusinessType;
	}
	public void setListBusinessType(List<BusinessType> listBusinessType) {
		this.listBusinessType = listBusinessType;
	}
	public List<KlasifikasiKerja> getListKlasifikasiKerja() {
		return listKlasifikasiKerja;
	}
	public void setListKlasifikasiKerja(List<KlasifikasiKerja> listKlasifikasiKerja) {
		this.listKlasifikasiKerja = listKlasifikasiKerja;
	}
	public List<City> getListCity() {
		return listCity;
	}
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
	public Integer getEducationStartYear() {
		return educationStartYear;
	}
	public void setEducationStartYear(Integer educationStartYear) {
		this.educationStartYear = educationStartYear;
	}
	public Integer getEducationEndYear() {
		return educationEndYear;
	}
	public void setEducationEndYear(Integer educationEndYear) {
		this.educationEndYear = educationEndYear;
	}
	public List<DualListModel> getSpecListDualModel() {
		return specListDualModel;
	}
	public void setSpecListDualModel(List<DualListModel> specListDualModel) {
		this.specListDualModel = specListDualModel;
	}
	public List<String> getSpecListName() {
		return specListName;
	}
	public void setSpecListName(List<String> specListName) {
		this.specListName = specListName;
	}
	public Long getBioDataId() {
		return bioDataId;
	}
	public void setBioDataId(Long bioDataId) {
		this.bioDataId = bioDataId;
	}
	public List<RecruitVacancyAdvertisement> getListVacancyAdvertisement() {
		return listVacancyAdvertisement;
	}
	public void setListVacancyAdvertisement(List<RecruitVacancyAdvertisement> listVacancyAdvertisement) {
		this.listVacancyAdvertisement = listVacancyAdvertisement;
	}
	public Long getVacancyAdvertisementDetailId() {
		return vacancyAdvertisementDetailId;
	}
	public void setVacancyAdvertisementDetailId(Long vacancyAdvertisementDetailId) {
		this.vacancyAdvertisementDetailId = vacancyAdvertisementDetailId;
	}
	public List<RecruitVacancyAdvertisementDetail> getListVacancyAdvertisementDetail() {
		return listVacancyAdvertisementDetail;
	}
	public void setListVacancyAdvertisementDetail(List<RecruitVacancyAdvertisementDetail> listVacancyAdvertisementDetail) {
		this.listVacancyAdvertisementDetail = listVacancyAdvertisementDetail;
	}
    
}
