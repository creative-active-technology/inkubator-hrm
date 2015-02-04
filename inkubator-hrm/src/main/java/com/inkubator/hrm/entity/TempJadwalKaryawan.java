package com.inkubator.hrm.entity;
// Generated Sep 16, 2014 10:12:24 AM by Hibernate Tools 4.3.1


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
 * TempJadwalKaryawan generated by hbm2java
 */
@Entity
@Table(name="temp_jadwal_karyawan"
    ,catalog="hrm_personalia"
)
public class TempJadwalKaryawan  implements java.io.Serializable {


     private long id;
     private Integer version;
//     private AttendanceStatus attendanceStatus;
     private EmpData empData;
     private WtWorkingHour wtWorkingHour;
     private Date tanggalWaktuKerja;
     private Boolean isCollectiveLeave;
     private String createdBy;
     private Date createdOn;
     private String updatedBy;
     private Date updatedOn;

    public TempJadwalKaryawan() {
    }

	
    public TempJadwalKaryawan(long id) {
        this.id = id;
    }
    public TempJadwalKaryawan(long id, AttendanceStatus attendanceStatus, EmpData empData, WtWorkingHour wtWorkingHour, Date tanggalWaktuKerja, Boolean isCollectiveLeave, String createdBy, Date createdOn, String updatedBy, Date updatedOn) {
       this.id = id;
//       this.attendanceStatus = attendanceStatus;
       this.empData = empData;
       this.wtWorkingHour = wtWorkingHour;
       this.tanggalWaktuKerja = tanggalWaktuKerja;
       this.isCollectiveLeave = isCollectiveLeave;
       this.createdBy = createdBy;
       this.createdOn = createdOn;
       this.updatedBy = updatedBy;
       this.updatedOn = updatedOn;
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

//@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="attendace_status_id")
//    public AttendanceStatus getAttendanceStatus() {
//        return this.attendanceStatus;
//    }
//    
//    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
//        this.attendanceStatus = attendanceStatus;
//    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_id")
    public EmpData getEmpData() {
        return this.empData;
    }
    
    public void setEmpData(EmpData empData) {
        this.empData = empData;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="working_our_id")
    public WtWorkingHour getWtWorkingHour() {
        return this.wtWorkingHour;
    }
    
    public void setWtWorkingHour(WtWorkingHour wtWorkingHour) {
        this.wtWorkingHour = wtWorkingHour;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_waktu_kerja", length=19)
    public Date getTanggalWaktuKerja() {
        return this.tanggalWaktuKerja;
    }
    
    public void setTanggalWaktuKerja(Date tanggalWaktuKerja) {
        this.tanggalWaktuKerja = tanggalWaktuKerja;
    }

    
    @Column(name="is_collective_leave")
    public Boolean getIsCollectiveLeave() {
        return this.isCollectiveLeave;
    }
    
    public void setIsCollectiveLeave(Boolean isCollectiveLeave) {
        this.isCollectiveLeave = isCollectiveLeave;
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




}


