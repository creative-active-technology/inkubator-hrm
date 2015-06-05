package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class BioMedicalHistoryModel implements Serializable {

    private Long id;
    private Long bioDataId;
    private Integer year;
    private String disease;
    private String description;
    private Integer status;
    private Long diseasesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(Long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDiseasesId() {
		return diseasesId;
	}

	public void setDiseasesId(Long diseasesId) {
		this.diseasesId = diseasesId;
	}
}
