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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateModel implements Serializable{
    private Long id;
    private SearchEmployeeCandidateViewModel selectedEmpCandidate;
    private int ageFrom;
    private int ageUntil;
    private int workingPeriodFrom;
    private int workingPeriodEnd;
    private String gender;
    private String jabatans;
    private String religions;
    private DualListModel<Jabatan> dualListModelJabatan = new DualListModel<>();
    private DualListModel<Religion> dualListModelReligion = new DualListModel<>();
    private Map<String, Long> mapEducation = new TreeMap<>();
    private Long educationLevelType;
    private Double gpa;
    private Double gpaScale = 4.0;
    private LazyDataModel<SearchEmployeeCandidateViewModel> lazyDataModel;
    private List<Long> listIdEmpDataCandidate = new ArrayList<Long>();
    private Map<Long, Boolean> selectedIds = new HashMap<Long, Boolean>();
    
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

    public LazyDataModel<SearchEmployeeCandidateViewModel> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<SearchEmployeeCandidateViewModel> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public SearchEmployeeCandidateViewModel getSelectedEmpCandidate() {
        return selectedEmpCandidate;
    }

    public void setSelectedEmpCandidate(SearchEmployeeCandidateViewModel selectedEmpCandidate) {
        this.selectedEmpCandidate = selectedEmpCandidate;
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

	public List<Long> getListIdEmpDataCandidate() {
		for (Map.Entry<Long, Boolean> selected : selectedIds.entrySet()) {
            if (StringUtils.equals(String.valueOf(selected.getValue()), "true")) {
            	listIdEmpDataCandidate.add(selected.getKey());
            }
        }
		return listIdEmpDataCandidate;
	}

	public void setListIdEmpDataCandidate(List<Long> listIdEmpDataCandidate) {
		this.listIdEmpDataCandidate = listIdEmpDataCandidate;
	}

	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}

    
    
}
