package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateViewModel implements Serializable {

    private Long empDataId;
    private String nik;
    private String firstName;
    private String lastName;
    private Long idJabatan;
    private String jabatanName;
    private Long idReligion;
    private String religionName;
    private String kriteria;
    private Long lastEducationLevelId;
    private String lastEducationLevelName;

    public Long getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(Long empDataId) {
        this.empDataId = empDataId;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(Long idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    public Long getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Long idReligion) {
        this.idReligion = idReligion;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
    }

    public String getKriteria() {
        return kriteria;
    }

    public void setKriteria(String kriteria) {
        this.kriteria = kriteria;
    }

    public Long getLastEducationLevelId() {
        return lastEducationLevelId;
    }

    public void setLastEducationLevelId(Long lastEducationLevelId) {
        this.lastEducationLevelId = lastEducationLevelId;
    }

    public String getLastEducationLevelName() {
        return lastEducationLevelName;
    }

    public void setLastEducationLevelName(String lastEducationLevelName) {
        this.lastEducationLevelName = lastEducationLevelName;
    }

    
   
}
