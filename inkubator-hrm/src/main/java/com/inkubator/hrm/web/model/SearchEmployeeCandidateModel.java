/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.Religion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateModel implements Serializable{
    private Long id;
//    private String name;
//    private String lastName;
//    private String codeJabatan;
//    private String nikFrom;
//    private String nikUntil;
    private EmpData selectedEmpData;
    private int ageFrom;
    private int ageUntil;
    private int workingPeriodFrom;
    private int workingPeriodEnd;
//    private int fromJoin;
//    private int untilJoin;
    private String gender;
//    private String firstName;
//    private String empTypeName;
    private String jabatans;
    private String religions;
//    private String golonganJabatans;
//    private String tipeKaryawan;
//    private String employeeTypeView;
//    private String[] employeeTypeName;
//    private String[] employeeTypeNameView;
//    private Double age;
//    private Date joinDate;
    private DualListModel<Jabatan> dualListModelJabatan = new DualListModel<>();
    private DualListModel<Religion> dualListModelReligion = new DualListModel<>();
    private Map<String, Long> mapEducation = new TreeMap<>();
    private Long educationLevelType;
    private Double gpa;
    private Double gpaScale = 4.0;
//    private List<String> listEmployeeType = new ArrayList<String>();
//    private List<EmpData> listEmpData = new ArrayList<EmpData>();
    private LazyDataModel<EmpData> lazyDataModel;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DualListModel<Jabatan> getDualListModelJabatan() {
        return dualListModelJabatan;
    }

    public void setDualListModelJabatan(DualListModel<Jabatan> dualListModelJabatan) {
        this.dualListModelJabatan = dualListModelJabatan;
    }

    public DualListModel<Religion> getDualListModelReligion() {
        return dualListModelReligion;
    }

    public void setDualListModelReligion(DualListModel<Religion> dualListModelReligion) {
        this.dualListModelReligion = dualListModelReligion;
    }  

    public LazyDataModel<EmpData> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public int getWorkingPeriodFrom() {
        return workingPeriodFrom;
    }

    public void setWorkingPeriodFrom(int workingPeriodFrom) {
        this.workingPeriodFrom = workingPeriodFrom;
    }

    public int getWorkingPeriodEnd() {
        return workingPeriodEnd;
    }

    public void setWorkingPeriodEnd(int workingPeriodEnd) {
        this.workingPeriodEnd = workingPeriodEnd;
    }

    public int getAgeFrom() {
        return ageFrom;
    }

    public void setAgeFrom(int ageFrom) {
        this.ageFrom = ageFrom;
    }

    public int getAgeUntil() {
        return ageUntil;
    }

    public void setAgeUntil(int ageUntil) {
        this.ageUntil = ageUntil;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<String, Long> getMapEducation() {
        return mapEducation;
    }

    public void setMapEducation(Map<String, Long> mapEducation) {
        this.mapEducation = mapEducation;
    }

    public Long getEducationLevelType() {
        return educationLevelType;
    }

    public void setEducationLevelType(Long educationLevelType) {
        this.educationLevelType = educationLevelType;
    }   

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Double getGpaScale() {
        return gpaScale;
    }

    public void setGpaScale(Double gpaScale) {
        this.gpaScale = gpaScale;
    }

    public String getJabatans() {
        return jabatans;
    }

    public void setJabatans(String jabatans) {
        this.jabatans = jabatans;
    }

    public String getReligions() {
        return religions;
    }

    public void setReligions(String religions) {
        this.religions = religions;
    }

    
    
}
