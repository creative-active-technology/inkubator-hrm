package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class MedicalCareModel implements Serializable {

    private Long id;
    private Long empDataByTemporaryActingId;
    private Long empDataByEmpDataId;
    private Long diseaseId;
    private Long hospitalId;
    private Date requestDate;
    private Date endDate;
    private String materialJobsAbandoned;
    private String docterName;
    private Date startDate;
    private Integer totalDays;
    private String uploadPath;
    private String medicalNotes;
    private String jabatan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpDataByTemporaryActingId() {
        return empDataByTemporaryActingId;
    }

    public void setEmpDataByTemporaryActingId(Long empDataByTemporaryActingId) {
        this.empDataByTemporaryActingId = empDataByTemporaryActingId;
    }

    public Long getEmpDataByEmpDataId() {
        return empDataByEmpDataId;
    }

    public void setEmpDataByEmpDataId(Long empDataByEmpDataId) {
        this.empDataByEmpDataId = empDataByEmpDataId;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMaterialJobsAbandoned() {
        return materialJobsAbandoned;
    }

    public void setMaterialJobsAbandoned(String materialJobsAbandoned) {
        this.materialJobsAbandoned = materialJobsAbandoned;
    }

    public String getDocterName() {
        return docterName;
    }

    public void setDocterName(String docterName) {
        this.docterName = docterName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    
}
