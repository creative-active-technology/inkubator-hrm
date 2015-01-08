/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.sms.gateway.entity;

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
 *
 * @author deni
 */
@Entity
@Table(name="user"
    ,catalog="sms_gateway"
    , uniqueConstraints = {@UniqueConstraint(columnNames="user_id"), @UniqueConstraint(columnNames="email_address")} 
)
public class User  implements java.io.Serializable {


     private long id;
     private Integer version;
     private String userId;
     private String realName;
     private String emailAddress;
     private String phoneNumber;
     private String password;
     private Integer isActive;
     private Integer isLock;
     private Integer isExpired;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;
     private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public User() {
    }

	
    public User(long id) {
        this.id = id;
    }
    public User(long id, String userId, String realName, String emailAddress, String phoneNumber, String password, Integer isActive, Integer isLock, Integer isExpired, String createdBy, Date createdOn, String updatedBy, Date updatedOn, Set<UserRole> userRoles) {
       this.id = id;
       this.userId = userId;
       this.realName = realName;
       this.emailAddress = emailAddress;
       this.phoneNumber = phoneNumber;
       this.password = password;
       this.isActive = isActive;
       this.isLock = isLock;
       this.isExpired = isExpired;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.userRoles = userRoles;
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

    
    @Column(name="user_id", unique=true, length=45)
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    @Column(name="real_name", length=100)
    public String getRealName() {
        return this.realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }

    
    @Column(name="email_address", unique=true, length=100)
    public String getEmailAddress() {
        return this.emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    
    @Column(name="phone_number", length=45)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Column(name="password", length=65535)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="is_active")
    public Integer getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    
    @Column(name="is_lock")
    public Integer getIsLock() {
        return this.isLock;
    }
    
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    
    @Column(name="is_expired")
    public Integer getIsExpired() {
        return this.isExpired;
    }
    
    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
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

    @Temporal(TemporalType.DATE)
    @Column(name="updated_on", length=10)
    public Date getUpdatedOn() {
        return this.updatedOn;
    }
    
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }




}

