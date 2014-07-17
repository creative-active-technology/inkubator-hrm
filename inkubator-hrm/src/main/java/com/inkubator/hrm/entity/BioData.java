package com.inkubator.hrm.entity;
// Generated Jul 14, 2014 1:26:56 PM by Hibernate Tools 3.6.0

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
 * BioData generated by hbm2java
 */
@Entity
@Table(name = "bio_data", catalog = "hrm"
)
public class BioData implements java.io.Serializable {

    private long id;
    private Integer version;
    private Nationality nationality;
    private Dialect dialect;
    private Religion religion;
    private City city;
    private Race race;
    private MaritalStatus maritalStatus;
    private String firstName;
    private String lastName;
    private String title;
    private String nickname;
    private Integer gender;
    private Integer bloodType;
    private Date dateOfBirth;
    private String personalEmail;
    private String mobilePhone;
    private Double bodyTall;
    private Double bodyWeight;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String pathFoto;
    private String pathFinger;
    private String pathSignature;
    private Set<BioAddress> bioAddresses = new HashSet<BioAddress>(0);

    public BioData() {
    }

    public BioData(long id) {
        this.id = id;
    }

    public BioData(long id, Nationality nationality, Dialect dialect, Religion religion, City city, Race race, MaritalStatus maritalStatus, String firstName, String lastName, String title, String nickname, Integer gender, Integer bloodType, Date dateOfBirth, String personalEmail, String mobilePhone, Double bodyTall, Double bodyWeight, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
        this.id = id;
        this.nationality = nationality;
        this.dialect = dialect;
        this.religion = religion;
        this.city = city;
        this.race = race;
        this.maritalStatus = maritalStatus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.nickname = nickname;
        this.gender = gender;
        this.bloodType = bloodType;
        this.dateOfBirth = dateOfBirth;
        this.personalEmail = personalEmail;
        this.mobilePhone = mobilePhone;
        this.bodyTall = bodyTall;
        this.bodyWeight = bodyWeight;
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
    @JoinColumn(name = "warga_negara")
    public Nationality getNationality() {
        return this.nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dialect")
    public Dialect getDialect() {
        return this.dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agama_id")
    public Religion getReligion() {
        return this.religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_of_birth")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ras")
    public Race getRace() {
        return this.race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "martial_id")
    public MaritalStatus getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Column(name = "first_name", length = 100)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "title", length = 50)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "nickname", length = 50)
    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "gender")
    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Column(name = "blood_type")
    public Integer getBloodType() {
        return this.bloodType;
    }

    public void setBloodType(Integer bloodType) {
        this.bloodType = bloodType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth", length = 10)
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "personal_email", length = 200)
    public String getPersonalEmail() {
        return this.personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    @Column(name = "mobile_phone", length = 25)
    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Column(name = "body_tall", precision = 22, scale = 0)
    public Double getBodyTall() {
        return this.bodyTall;
    }

    public void setBodyTall(Double bodyTall) {
        this.bodyTall = bodyTall;
    }

    @Column(name = "body_weight", precision = 22, scale = 0)
    public Double getBodyWeight() {
        return this.bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
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

    @Column(name = "path_foto", length = 100)
    public String getPathFoto() {
        return this.pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    @Column(name = "path_finger", length = 100)
    public String getPathFinger() {
        return this.pathFinger;
    }

    public void setPathFinger(String pathFinger) {
        this.pathFinger = pathFinger;
    }

    @Column(name = "path_signature", length = 100)
    public String getPathSignature() {
        return this.pathSignature;
    }

    public void setPathSignature(String pathSignature) {
        this.pathSignature = pathSignature;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bioData")
	public Set<BioAddress> getBioAddresses() {
		return bioAddresses;
	}

	public void setBioAddresses(Set<BioAddress> bioAddresses) {
		this.bioAddresses = bioAddresses;
	}
	
}
