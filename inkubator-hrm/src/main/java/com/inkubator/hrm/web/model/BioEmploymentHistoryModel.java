package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.City;
import java.io.Serializable;

/**
 *
 * @author Taufik Hidayat
 */
public class BioEmploymentHistoryModel implements Serializable {

    private Long id;
    private Long bioDataId;
    private City city;
    private Integer yearIn;
    private Integer yearOut;
    private String companyName;
    private String lastOccupation;
    private Double salary;
    private String jobSector;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    

    public Integer getYearIn() {
        return yearIn;
    }

    public void setYearIn(Integer yearIn) {
        this.yearIn = yearIn;
    }

    public Integer getYearOut() {
        return yearOut;
    }

    public void setYearOut(Integer yearOut) {
        this.yearOut = yearOut;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLastOccupation() {
        return lastOccupation;
    }

    public void setLastOccupation(String lastOccupation) {
        this.lastOccupation = lastOccupation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobSector() {
        return jobSector;
    }

    public void setJobSector(String jobSector) {
        this.jobSector = jobSector;
    }

    
}
