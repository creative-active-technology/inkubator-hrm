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
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private Date time;
    private String place;
    private BigDecimal basicCost;
    private BigDecimal individualCost;
    private LazyDataModel<EmpData> lazyDataModel;
    private String nikOrName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Long> getDropDownRecruitHireApply() {
        return dropDownRecruitHireApply;
    }

    public void setDropDownRecruitHireApply(Map<String, Long> dropDownRecruitHireApply) {
        this.dropDownRecruitHireApply = dropDownRecruitHireApply;
    }

    public Map<String, Long> getDropDownRecruitSelectionType() {
        return dropDownRecruitSelectionType;
    }

    public void setDropDownRecruitSelectionType(Map<String, Long> dropDownRecruitSelectionType) {
        this.dropDownRecruitSelectionType = dropDownRecruitSelectionType;
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

    public Date getRecruitVacancySelectionDate() {
        return recruitVacancySelectionDate;
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

    public void setListVacancySelectionDetail(List<RecruitVacancySelectionDetailModel> listVacancySelectionDetail) {
        this.listVacancySelectionDetail = listVacancySelectionDetail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(BigDecimal basicCost) {
        this.basicCost = basicCost;
    }

    public BigDecimal getIndividualCost() {
        return individualCost;
    }

    public void setIndividualCost(BigDecimal individualCost) {
        this.individualCost = individualCost;
    }

    public LazyDataModel<EmpData> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public String getNikOrName() {
        return nikOrName;
    }

    public void setNikOrName(String nikOrName) {
        this.nikOrName = nikOrName;
    }

    
    
}
