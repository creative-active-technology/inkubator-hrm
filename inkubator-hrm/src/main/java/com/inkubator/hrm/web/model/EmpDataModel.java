/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Deni Husni FR
 */
public class EmpDataModel implements Serializable {

    private Long id;
    private long workingGroupId;
    private Long employeeTypeId;
    private long bioDataId;
    private String bioDataName;
    private long paySalaryGradeId;
    private long jabatanByJabatanId;
    private long golonganJabatan;
    private long jabatanByJabatanGajiId;
    private String nik;
    private Date joinDate;
    private String ppmp;
    private String ppip;
    private Boolean isFinger;
    private Boolean heatlyPremi;
    private Boolean ptkpStatus;
    private Integer ptkpNumber;
    private Boolean insentifStatus;
    private BigDecimal basicSalary;
    private Date birthDate;
    private long departementId;
    private String noSk;
    private Date rotasiDate;
    private String nikAndName;
    private Integer ptkpStatusInt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getWorkingGroupId() {
        return workingGroupId;
    }

    public void setWorkingGroupId(long workingGroupId) {
        this.workingGroupId = workingGroupId;
    }

    public Long getEmployeeTypeId() {
        return employeeTypeId;
    }

    public void setEmployeeTypeId(Long employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    public long getBioDataId() {
        return bioDataId;
    }

    public void setBioDataId(long bioDataId) {
        this.bioDataId = bioDataId;
    }

    public String getBioDataName() {
        return bioDataName;
    }

    public void setBioDataName(String bioDataName) {
        this.bioDataName = bioDataName;
    }

    public long getPaySalaryGradeId() {
        return paySalaryGradeId;
    }

    public void setPaySalaryGradeId(long paySalaryGradeId) {
        this.paySalaryGradeId = paySalaryGradeId;
    }

    public long getJabatanByJabatanId() {
        return jabatanByJabatanId;
    }

    public void setJabatanByJabatanId(long jabatanByJabatanId) {
        this.jabatanByJabatanId = jabatanByJabatanId;
    }

    public long getGolonganJabatan() {
        return golonganJabatan;
    }

    public void setGolonganJabatan(long golonganJabatan) {
        this.golonganJabatan = golonganJabatan;
    }

    public long getJabatanByJabatanGajiId() {
        return jabatanByJabatanGajiId;
    }

    public void setJabatanByJabatanGajiId(long jabatanByJabatanGajiId) {
        this.jabatanByJabatanGajiId = jabatanByJabatanGajiId;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPpmp() {
        return ppmp;
    }

    public void setPpmp(String ppmp) {
        this.ppmp = ppmp;
    }

    public String getPpip() {
        return ppip;
    }

    public void setPpip(String ppip) {
        this.ppip = ppip;
    }

    public Boolean isIsFinger() {
        return isFinger;
    }

    public void setIsFinger(Boolean isFinger) {
        this.isFinger = isFinger;
    }

    public Boolean isHeatlyPremi() {
        return heatlyPremi;
    }

    public void setHeatlyPremi(Boolean heatlyPremi) {
        this.heatlyPremi = heatlyPremi;
    }

    public Boolean getPtkpStatus() {
        return ptkpStatus;
    }

    public void setPtkpStatus(Boolean ptkpStatus) {
        this.ptkpStatus = ptkpStatus;
    }

    public Integer getPtkpNumber() {
        return ptkpNumber;
    }

    public void setPtkpNumber(Integer ptkpNumber) {
        this.ptkpNumber = ptkpNumber;
    }

    public Boolean isInsentifStatus() {
        return insentifStatus;
    }

    public void setInsentifStatus(Boolean insentifStatus) {
        this.insentifStatus = insentifStatus;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(long departementId) {
        this.departementId = departementId;
    }

    public String getNoSk() {
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    public Date getRotasiDate() {
        return rotasiDate;
    }

    public void setRotasiDate(Date rotasiDate) {
        this.rotasiDate = rotasiDate;
    }

    public String getNikAndName() {
        return nikAndName;
    }

    public void setNikAndName(String nikAndName) {
        this.nikAndName = nikAndName;
    }

    public Integer getPtkpStatusInt() {
        return ptkpStatusInt;
    }

    public void setPtkpStatusInt(Integer ptkpStatusInt) {
        this.ptkpStatusInt = ptkpStatusInt;
    }

   
    
    
}
