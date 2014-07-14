package com.inkubator.hrm.entity;
// Generated Jun 25, 2014 4:51:03 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name="country"
    ,catalog="hrm"
    , uniqueConstraints = {@UniqueConstraint(columnNames="country_code"), @UniqueConstraint(columnNames="phone_code")} 
)
public class Country  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private String countryCode;
     private String countryName;
     private byte[] flagIcon;
     private Integer phoneCode;
     private String latitude;
     private String longitude;
     private Set<Province> provinces = new HashSet<Province>(0);

    public Country() {
    }

	
    public Country(long id) {
        this.id = id;
    }
    public Country(long id, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String countryCode, String countryName, byte[] flagIcon, Integer phoneCode, String latitude, String longitude, Set<Province> provinces) {
       this.id = id;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.countryCode = countryCode;
       this.countryName = countryName;
       this.flagIcon = flagIcon;
       this.phoneCode = phoneCode;
       this.latitude = latitude;
       this.longitude = longitude;
       this.provinces = provinces;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    @Version
    @Column(name="version")
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }

    
    @Column(name="created_by", length=45)
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on", length=19)
    public Date getCreatedOn() {
        return this.createdOn;
    }
    
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    
    @Column(name="updated_by", length=45)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on", length=19)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    
    @Column(name="country_code", unique=true, length=8)
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    
    @Column(name="country_name", length=60)
    public String getCountryName() {
        return this.countryName;
    }
    
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    
    @Column(name="flag_icon")
    public byte[] getFlagIcon() {
        return this.flagIcon;
    }
    
    public void setFlagIcon(byte[] flagIcon) {
        this.flagIcon = flagIcon;
    }

    
    @Column(name="phone_code")
    public Integer getPhoneCode() {
        return this.phoneCode;
    }
    
    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }
    
    @Column(name="latitude", length=45)
    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    
    @Column(name="longitude", length=45)
    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="country")
    public Set<Province> getProvinces() {
        return this.provinces;
    }
    
    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }




}


