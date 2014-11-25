package com.inkubator.hrm.entity;
// Generated Nov 21, 2014 4:26:05 PM by Hibernate Tools 3.6.0


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
 * MedicalCare generated by hbm2java
 */
@Entity
@Table(name="medical_care"
    ,catalog="hrm"
)
public class MedicalCare  implements java.io.Serializable {


     private long id;
     private Integer version;
     private EmpData empDataByTemporaryActingId;
     private EmpData empDataByEmpDataId;
     private Disease disease;
     private Hospital hospital;
     private String createdBy;
     private Date createdOn;
     private Date requestDate;
     private Date endDate;
     private String materialJobsAbandoned;
     private String docterName;
     private Date startDate;
     private String updatedBy;
     private Date updatedOn;
     private Integer totalDays;
     private String uploadPath;
     private String medicalNotes;

    public MedicalCare() {
    }

	
    public MedicalCare(long id, EmpData empDataByEmpDataId, Disease disease, Date requestDate, Date endDate, String materialJobsAbandoned, String docterName, Date startDate) {
        this.id = id;
        this.empDataByEmpDataId = empDataByEmpDataId;
        this.disease = disease;
        this.requestDate = requestDate;
        this.endDate = endDate;
        this.materialJobsAbandoned = materialJobsAbandoned;
        this.docterName = docterName;
        this.startDate = startDate;
    }
    public MedicalCare(long id, EmpData empDataByTemporaryActingId, EmpData empDataByEmpDataId, Disease disease, Hospital hospital, String createdBy, Date createdOn, Date requestDate, Date endDate, String materialJobsAbandoned, String docterName, Date startDate, String updatedBy, Date updatedOn, Integer totalDays, String uploadPath, String medicalNotes) {
       this.id = id;
       this.empDataByTemporaryActingId = empDataByTemporaryActingId;
       this.empDataByEmpDataId = empDataByEmpDataId;
       this.disease = disease;
       this.hospital = hospital;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.requestDate = requestDate;
       this.endDate = endDate;
       this.materialJobsAbandoned = materialJobsAbandoned;
       this.docterName = docterName;
       this.startDate = startDate;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
       this.totalDays = totalDays;
       this.uploadPath = uploadPath;
       this.medicalNotes = medicalNotes;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="temporary_acting_id")
    public EmpData getEmpDataByTemporaryActingId() {
        return this.empDataByTemporaryActingId;
    }
    
    public void setEmpDataByTemporaryActingId(EmpData empDataByTemporaryActingId) {
        this.empDataByTemporaryActingId = empDataByTemporaryActingId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_data_id", nullable=false)
    public EmpData getEmpDataByEmpDataId() {
        return this.empDataByEmpDataId;
    }
    
    public void setEmpDataByEmpDataId(EmpData empDataByEmpDataId) {
        this.empDataByEmpDataId = empDataByEmpDataId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="disease_id", nullable=false)
    public Disease getDisease() {
        return this.disease;
    }
    
    public void setDisease(Disease disease) {
        this.disease = disease;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="hospital_id")
    public Hospital getHospital() {
        return this.hospital;
    }
    
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="request_date", nullable=false, length=19)
    public Date getRequestDate() {
        return this.requestDate;
    }
    
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_date", nullable=false, length=19)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="material_jobs_abandoned", nullable=false, length=65535)
    public String getMaterialJobsAbandoned() {
        return this.materialJobsAbandoned;
    }
    
    public void setMaterialJobsAbandoned(String materialJobsAbandoned) {
        this.materialJobsAbandoned = materialJobsAbandoned;
    }

    
    @Column(name="docter_name", nullable=false, length=60)
    public String getDocterName() {
        return this.docterName;
    }
    
    public void setDocterName(String docterName) {
        this.docterName = docterName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date", nullable=false, length=19)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    
    @Column(name="total_days")
    public Integer getTotalDays() {
        return this.totalDays;
    }
    
    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    
    @Column(name="upload_path", length=100)
    public String getUploadPath() {
        return this.uploadPath;
    }
    
    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    
    @Column(name="medical_notes", length=65535)
    public String getMedicalNotes() {
        return this.medicalNotes;
    }
    
    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }




}


