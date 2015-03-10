package com.inkubator.hrm.entity;
// Generated Oct 23, 2014 12:28:02 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * IpPermit generated by hbm2java
 */
@Entity
@Table(name = "ip_permit", catalog="hrm_payroll")
public class IpPermit implements java.io.Serializable {

    private long id;
    private Integer version;
    private Integer fromAddress1;
    private Integer fromAddress2;
    private Integer untilAddress1;
    private Integer untilAddress2;
    private String lokasi;
    private String ipAddressFromView;
    private String ipAddressUntilView;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;

    public IpPermit() {
    }

    public IpPermit(long id) {
        this.id = id;
    }

    public IpPermit(long id, Integer fromAddress1, Integer fromAddress2, Integer fromAddress3, Integer fromAddress4, Integer untilAddress1, Integer untilAddress2, Integer untilAddress3, Integer untilAddress4, String lokasi, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String ipAddressFromView) {
        this.id = id;
        this.fromAddress1 = fromAddress1;
        this.fromAddress2 = fromAddress2;

        this.untilAddress1 = untilAddress1;
        this.untilAddress2 = untilAddress2;
        this.ipAddressFromView = ipAddressFromView;
        this.lokasi = lokasi;
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

    @Column(name = "from_address_1")
    public Integer getFromAddress1() {
        return this.fromAddress1;
    }

    public void setFromAddress1(Integer fromAddress1) {
        this.fromAddress1 = fromAddress1;
    }

    @Column(name = "from_address_2")
    public Integer getFromAddress2() {
        return this.fromAddress2;
    }

    public void setFromAddress2(Integer fromAddress2) {
        this.fromAddress2 = fromAddress2;
    }

    @Column(name = "until_address_1")
    public Integer getUntilAddress1() {
        return this.untilAddress1;
    }

    public void setUntilAddress1(Integer untilAddress1) {
        this.untilAddress1 = untilAddress1;
    }

    @Column(name = "until_address_2")
    public Integer getUntilAddress2() {
        return this.untilAddress2;
    }

    public void setUntilAddress2(Integer untilAddress2) {
        this.untilAddress2 = untilAddress2;
    }

    @Column(name = "lokasi", length = 100)
    public String getLokasi() {
        return this.lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
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
    
    @Transient
    public Boolean isNineDigit() {
        char[] fromAddress = String.valueOf(fromAddress1).toCharArray();
        int fromAddressLength = fromAddress.length;
        if (fromAddressLength == 9) {
            return true;
        }
        return false;
    }

    @Column(name = "ip_address_from_view", length = 19)
    public String getIpAddressFromView() {
        return ipAddressFromView;
    }

    public void setIpAddressFromView(String ipAddressFromView) {
        this.ipAddressFromView = ipAddressFromView;
    }

    @Column(name = "ip_address_until_view", length = 19)
    public String getIpAddressUntilView() {
        return ipAddressUntilView;
    }

    public void setIpAddressUntilView(String ipAddressUntilView) {
        this.ipAddressUntilView = ipAddressUntilView;
    }

    @Transient
    public Boolean isSixDigit() {
        char[] fromAddress = String.valueOf(fromAddress1).toCharArray();
        int fromAddressLength = fromAddress.length;
        if (fromAddressLength == 6) {
            return true;
        }
        return false;
    }

    @Transient
    public Boolean isThreeDigit() {
        char[] fromAddress = String.valueOf(fromAddress1).toCharArray();
        int fromAddressLength = fromAddress.length;
        if (fromAddressLength == 3) {
            return true;
        }
        return false;
    }
}
