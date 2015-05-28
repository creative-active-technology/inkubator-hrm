/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitVacancySelection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
public class RecruitVacancySelectionModel implements Serializable {

    private String code;
    private Date recruitVacancySelectionDate;
    private BigDecimal extraBudget;
    private Map<String, Long> dropDownRecruitHireApply;
    private Map<String, Long> dropDownRecruitSelectionType;
    private Long recruitHireApplyId;
    private Long recruitSelectionTypeId;
    private String jobTitleName;
    private String staffName;
    private Date effectiveDate;
    private String recruitHireApplyName;
    private List<RecruitVacancySelectionDetailModel> listVacancySelectionDetail;
    private LazyDataModel<EmpData> lazyDataModel;
    private String nikOrNameSearchParameter;
    private RecruitVacancySelectionDetailModel selectedVacSelectionDetailModel;
    private Map<Long, Boolean> selectedIds =  new HashMap<Long, Boolean>();
    private List<Long> listEmployeeId;
    private List<String> listEmployeeName;
    
    //test
    private List<EmpData> listEmpData;
    private Map<EmpData, Boolean> selectedEmpData = new HashMap<EmpData, Boolean>();
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getRecruitVacancySelectionDate() {
		return recruitVacancySelectionDate;
	}
	public void setRecruitVacancySelectionDate(Date recruitVacancySelectionDate) {
		this.recruitVacancySelectionDate = recruitVacancySelectionDate;
	}
	public BigDecimal getExtraBudget() {
		return extraBudget;
	}
	public void setExtraBudget(BigDecimal extraBudget) {
		this.extraBudget = extraBudget;
	}
	public Map<String, Long> getDropDownRecruitHireApply() {
		return dropDownRecruitHireApply;
	}
	public void setDropDownRecruitHireApply(
			Map<String, Long> dropDownRecruitHireApply) {
		this.dropDownRecruitHireApply = dropDownRecruitHireApply;
	}
	public Map<String, Long> getDropDownRecruitSelectionType() {
		return dropDownRecruitSelectionType;
	}
	public void setDropDownRecruitSelectionType(
			Map<String, Long> dropDownRecruitSelectionType) {
		this.dropDownRecruitSelectionType = dropDownRecruitSelectionType;
	}
	public Long getRecruitHireApplyId() {
		return recruitHireApplyId;
	}
	public void setRecruitHireApplyId(Long recruitHireApplyId) {
		this.recruitHireApplyId = recruitHireApplyId;
	}
	public Long getRecruitSelectionTypeId() {
		return recruitSelectionTypeId;
	}
	public void setRecruitSelectionTypeId(Long recruitSelectionTypeId) {
		this.recruitSelectionTypeId = recruitSelectionTypeId;
	}
	public String getJobTitleName() {
		return jobTitleName;
	}
	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getRecruitHireApplyName() {
		return recruitHireApplyName;
	}
	public void setRecruitHireApplyName(String recruitHireApplyName) {
		this.recruitHireApplyName = recruitHireApplyName;
	}
	public List<RecruitVacancySelectionDetailModel> getListVacancySelectionDetail() {
		return listVacancySelectionDetail;
	}
	public void setListVacancySelectionDetail(
			List<RecruitVacancySelectionDetailModel> listVacancySelectionDetail) {
		this.listVacancySelectionDetail = listVacancySelectionDetail;
	}
	public LazyDataModel<EmpData> getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	public String getNikOrNameSearchParameter() {
		return nikOrNameSearchParameter;
	}
	public void setNikOrNameSearchParameter(String nikOrNameSearchParameter) {
		this.nikOrNameSearchParameter = nikOrNameSearchParameter;
	}
	public RecruitVacancySelectionDetailModel getSelectedVacSelectionDetailModel() {
		return selectedVacSelectionDetailModel;
	}
	public void setSelectedVacSelectionDetailModel(
			RecruitVacancySelectionDetailModel selectedVacSelectionDetailModel) {
		this.selectedVacSelectionDetailModel = selectedVacSelectionDetailModel;
	}
	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}
	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	}
	public List<Long> getListEmployeeId() {
		listEmployeeId = new ArrayList<Long>();
		for(Map.Entry<Long, Boolean> selected : selectedIds.entrySet()){
			if(StringUtils.equals(String.valueOf(selected.getValue()), "true")){
				listEmployeeId.add(selected.getKey());
			}
		}
		return listEmployeeId;
	}
	public void setListEmployeeId(List<Long> listEmployeeId) {
		this.listEmployeeId = listEmployeeId;
	}
	public List<String> getListEmployeeName() {
		return listEmployeeName;
	}
	public void setListEmployeeName(List<String> listEmployeeName) {
		this.listEmployeeName = listEmployeeName;
	}
	public List<EmpData> getListEmpData() {
		listEmpData = new ArrayList<EmpData>();
		for(Map.Entry<EmpData, Boolean> selected : selectedEmpData.entrySet()){
			if(StringUtils.equals(String.valueOf(selected.getValue()), "true")){
				listEmpData.add(selected.getKey());
			}
		}
		return listEmpData;
	}
	public void setListEmpData(List<EmpData> listEmpData) {
		this.listEmpData = listEmpData;
	}
	public Map<EmpData, Boolean> getSelectedEmpData() {
		return selectedEmpData;
	}
	public void setSelectedEmpData(Map<EmpData, Boolean> selectedEmpData) {
		this.selectedEmpData = selectedEmpData;
	}

    

    
    
}
