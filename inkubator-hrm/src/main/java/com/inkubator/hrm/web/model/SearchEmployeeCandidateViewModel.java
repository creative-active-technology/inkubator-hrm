package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateViewModel implements Serializable {

    private BigInteger empDataId;
    private String nik;
    private String firstName;
    private String lastName;
    private BigInteger idJabatan;
    private String jabatanName;
    private BigInteger idReligion;
    private String religionName;
    private String kriteria;
    private BigInteger lastEducationLevelId;
    private String lastEducationLevelName;

    public BigInteger getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(BigInteger empDataId) {
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

    public BigInteger getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(BigInteger idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getJabatanName() {
        return jabatanName;
    }

    public void setJabatanName(String jabatanName) {
        this.jabatanName = jabatanName;
    }

    public BigInteger getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(BigInteger idReligion) {
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

    public BigInteger getLastEducationLevelId() {
        return lastEducationLevelId;
    }

    public void setLastEducationLevelId(BigInteger lastEducationLevelId) {
        this.lastEducationLevelId = lastEducationLevelId;
    }

    public String getLastEducationLevelName() {
        return lastEducationLevelName;
    }

    public void setLastEducationLevelName(String lastEducationLevelName) {
        this.lastEducationLevelName = lastEducationLevelName;
    }

    
   
}
