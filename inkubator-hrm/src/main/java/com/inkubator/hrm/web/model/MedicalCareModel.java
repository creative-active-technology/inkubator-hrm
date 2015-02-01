package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.Disease;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Hospital;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Taufik Hidayat
 */
public class MedicalCareModel implements Serializable {

    private Long id;
    private EmpData empDataByTemporaryActingId;
    private EmpData empDataByEmpDataId;
    private Disease diseaseId;
    private Hospital hospitalId;
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

    public EmpData getEmpDataByTemporaryActingId() {
        return empDataByTemporaryActingId;
    }

    public void setEmpDataByTemporaryActingId(EmpData empDataByTemporaryActingId) {
        this.empDataByTemporaryActingId = empDataByTemporaryActingId;
    }

    public EmpData getEmpDataByEmpDataId() {
        return empDataByEmpDataId;
    }

    public void setEmpDataByEmpDataId(EmpData empDataByEmpDataId) {
        this.empDataByEmpDataId = empDataByEmpDataId;
    }

    public Disease getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Disease diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Hospital getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Hospital hospitalId) {
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
