package com.inkubator.hrm.entity;
// Generated Oct 20, 2014 2:41:54 PM by Hibernate Tools 4.3.1

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 * MecineFinger generated by hbm2java
 */
@Entity
@Table(name = "mecine_finger", catalog = "hrm", uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class MecineFinger implements java.io.Serializable {

    private long id;
    private Integer version;
    private String code;
    private String name;
    private String description;
    private Integer mecineMethode;
    private Integer fileType;
    private Integer fileExtension;
    private Integer baseOnField;
    private Boolean inOutStatus;
    private String matchBase;
    private String serviceHost;
    private Integer serviceType;
    private Integer serviceOpenProtocol;
    private String serviceOpenProtocolPassword;
    private String dbType;
    private String dbHost;
    private String dbUser;
    private String dbPass;
    private String dbSwapBase;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String query;
    private Set<MacineFingerUpload> macineFingerUploads = new HashSet<MacineFingerUpload>(0);
    private Set<DepartementUploadCapture> departementUploadCaptures = new HashSet<DepartementUploadCapture>(0);
    private List<Department> departments = new ArrayList<>(0);

    public MecineFinger() {
    }

    public MecineFinger(long id) {
        this.id = id;
    }

    public MecineFinger(long id, String code, String name, String description, Integer mecineMethode, Integer fileType, Integer fileExtension, Boolean inOutStatus, String matchBase, String serviceHost, Integer serviceType, Integer serviceOpenProtocol, String serviceOpenProtocolPassword, String dbType, String dbHost, String dbUser, String dbPass, String dbSwapBase, String createdBy, Date createdOn, String updatedBy, Date updatedOn, String query, Set<MacineFingerUpload> macineFingerUploads, Set<DepartementUploadCapture> departementUploadCaptures) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.mecineMethode = mecineMethode;
        this.fileType = fileType;
        this.fileExtension = fileExtension;
        this.inOutStatus = inOutStatus;
        this.matchBase = matchBase;
        this.serviceHost = serviceHost;
        this.serviceType = serviceType;
        this.serviceOpenProtocol = serviceOpenProtocol;
        this.serviceOpenProtocolPassword = serviceOpenProtocolPassword;
        this.dbType = dbType;
        this.dbHost = dbHost;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        this.dbSwapBase = dbSwapBase;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.query = query;
//        this.macineFingerUploads = macineFingerUploads;
        this.departementUploadCaptures = departementUploadCaptures;
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

    @Column(name = "code", unique = true, length = 4)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", length = 60)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "mecine_methode")
    public Integer getMecineMethode() {
        return this.mecineMethode;
    }

    public void setMecineMethode(Integer mecineMethode) {
        this.mecineMethode = mecineMethode;
    }

    @Column(name = "file_type")
    public Integer getFileType() {
        return this.fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    @Column(name = "file_extension")
    public Integer getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(Integer fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Column(name = "in_out_status")
    public Boolean getInOutStatus() {
        return this.inOutStatus;
    }

    public void setInOutStatus(Boolean inOutStatus) {
        this.inOutStatus = inOutStatus;
    }

    @Column(name = "match_base", length = 45)
    public String getMatchBase() {
        return this.matchBase;
    }

    public void setMatchBase(String matchBase) {
        this.matchBase = matchBase;
    }

    @Column(name = "service_host", length = 45)
    public String getServiceHost() {
        return this.serviceHost;
    }

    public void setServiceHost(String serviceHost) {
        this.serviceHost = serviceHost;
    }

    @Column(name = "service_type")
    public Integer getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    @Column(name = "service_open_protocol")
    public Integer getServiceOpenProtocol() {
        return this.serviceOpenProtocol;
    }

    public void setServiceOpenProtocol(Integer serviceOpenProtocol) {
        this.serviceOpenProtocol = serviceOpenProtocol;
    }

    @Column(name = "service_open_protocol_password", length = 45)
    public String getServiceOpenProtocolPassword() {
        return this.serviceOpenProtocolPassword;
    }

    public void setServiceOpenProtocolPassword(String serviceOpenProtocolPassword) {
        this.serviceOpenProtocolPassword = serviceOpenProtocolPassword;
    }

    @Column(name = "db_type", length = 45)
    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Column(name = "db_host", length = 45)
    public String getDbHost() {
        return this.dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    @Column(name = "db_user", length = 45)
    public String getDbUser() {
        return this.dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @Column(name = "db_pass", length = 45)
    public String getDbPass() {
        return this.dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    @Column(name = "db_swap_base", length = 45)
    public String getDbSwapBase() {
        return this.dbSwapBase;
    }

    public void setDbSwapBase(String dbSwapBase) {
        this.dbSwapBase = dbSwapBase;
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

    @Column(name = "query", length = 100)
    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mecineFinger")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mecineFinger", orphanRemoval = true)
    public Set<MacineFingerUpload> getMacineFingerUploads() {
        return this.macineFingerUploads;
    }

    public void setMacineFingerUploads(Set<MacineFingerUpload> macineFingerUploads) {
        this.macineFingerUploads = macineFingerUploads;
    }
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mecineFinger")

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mecineFinger", orphanRemoval = true)
    public Set<DepartementUploadCapture> getDepartementUploadCaptures() {
        return this.departementUploadCaptures;
    }

    public void setDepartementUploadCaptures(Set<DepartementUploadCapture> departementUploadCaptures) {
        this.departementUploadCaptures = departementUploadCaptures;
    }

    @Column(name = "base_on_field")
    public Integer getBaseOnField() {
        return baseOnField;
    }

    public void setBaseOnField(Integer baseOnField) {
        this.baseOnField = baseOnField;
    }

    @Transient
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

}