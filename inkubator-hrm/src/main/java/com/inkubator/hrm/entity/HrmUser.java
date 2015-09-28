package com.inkubator.hrm.entity;
// Generated May 9, 2014 9:50:44 AM by Hibernate Tools 3.6.0

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * HrmUser generated by hbm2java
 */
@Entity
@Table(name = "hrm_user", catalog="hrm_payroll_backup", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email_address"),
    @UniqueConstraint(columnNames = "user_id")}
)
public class HrmUser implements java.io.Serializable {

    private long id;
    private Integer version;
    private String userId;
    private String realName;
    private String emailAddress;
    private String phoneNumber;
    private String phoneCode;
    private String password;
    private Integer isActive;
    private Integer isLock;
    private Integer isExpired;
    private EmpData empData;
    private String createdBy;
    private String updatedBy;
    private Date createdOn;
    private Date updatedOn;
    private Set<HrmUserRole> hrmUserRoles = new HashSet<>(0);
    private Set<LoginHistory> loginHistories = new HashSet<>(0);
    private List<HrmRole> roles = new ArrayList<>(0);
    private Set<FavoriteMenu> favoriteMenus = new HashSet<FavoriteMenu>(0);

    public HrmUser() {
    }

    public HrmUser(long id) {
        this.id = id;
    }

    public HrmUser(long id, String userId, String realName, String emailAddress, String password, Integer isActive, Integer isLock, Integer isExpired, EmpData empData, String createdBy, String updatedBy, Date createdOn, Date updatedOn, Set<HrmUserRole> hrmUserRoles, Set<LoginHistory> loginHistories) {
        this.id = id;
        this.userId = userId;
        this.realName = realName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isActive = isActive;
        this.isLock = isLock;
        this.isExpired = isExpired;
        this.empData = empData;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.hrmUserRoles = hrmUserRoles;
        this.loginHistories = loginHistories;
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

    @Column(name = "user_id", unique = true, length = 45)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "real_name", length = 100)
    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "email_address", unique = true, length = 100)
    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "password", length = 65535, columnDefinition="Text")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_active")
    public Integer getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Column(name = "is_lock")
    public Integer getIsLock() {
        return this.isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    @Column(name = "is_expired")
    public Integer getIsExpired() {
        return this.isExpired;
    }

    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_data_id", unique=true)
    public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hrmUser", orphanRemoval = true)
    public Set<HrmUserRole> getHrmUserRoles() {
        return this.hrmUserRoles;
    }

    public void setHrmUserRoles(Set<HrmUserRole> hrmUserRoles) {
        this.hrmUserRoles = hrmUserRoles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hrmUser")
    public Set<LoginHistory> getLoginHistories() {
        return this.loginHistories;
    }

    public void setLoginHistories(Set<LoginHistory> loginHistories) {
        this.loginHistories = loginHistories;
    }

    public HrmUser(String userId) {
        this.userId = userId;
    }

    @Column(name = "PHONE_NUMBER", length = 45)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "PHONE_CODE", length = 45)
    public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Transient
    public List<HrmRole> getRoles() {
        return roles;
    }

    public void setRoles(List<HrmRole> roles) {
        this.roles = roles;
    }

    @Transient
    public String getAcitveAsString() {
        String data = null;
        if (isActive == 1) {
            data = "Yes";
        }

        if (isActive == 0) {
            data = "No";
        }
        return data;
    }

    @Transient
    public String getLockAsString() {
        String data = null;
        if (isLock == 1) {
            data = "Yes";
        }

        if (isLock == 0) {
            data = "No";
        }
        return data;
    }

    @Transient
    public String getExpiredAsString() {
        String data = null;
        if (isExpired == 1) {
            data = "Yes";
        }

        if (isExpired == 0) {
            data = "No";
        }
        return data;
    }

    @Transient
    public List<String> getListRuleAsString() {
        List<String> roleNames = new ArrayList<>();
        for (HrmRole role : getRoles()) {
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hrmUser")
    public Set<FavoriteMenu> getFavoriteMenus() {
        return this.favoriteMenus;
    }

    public void setFavoriteMenus(Set<FavoriteMenu> favoriteMenus) {
        this.favoriteMenus = favoriteMenus;
    }

}
