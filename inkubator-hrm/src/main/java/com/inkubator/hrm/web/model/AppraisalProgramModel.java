package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inkubator.hrm.entity.AppraisalPerformanceGroup;
import com.inkubator.hrm.entity.CareerAwardType;
import com.inkubator.hrm.entity.CareerDisciplineType;

/**
 *
 * @author rizkykojek
 */
public class AppraisalProgramModel implements Serializable {
	
	private Long id;
	private String code;
    private String name;
    private String description;
    private Date evalStartDate;
    private Date evalEndDate;
    private Boolean isGapCompetency;
    private Boolean isPerformanceScoring;
    private Boolean isAchievement;
    private Boolean isIndiscipline;
    private Long appraisalPerformanceGroupId;
    private Map<Long, BigDecimal> listAchievementScore = new HashMap<>();
    private Map<Long, BigDecimal> listIndisciplineScore = new HashMap<>();
    
    //not persisted
    private List<CareerAwardType> listCareerAward = new ArrayList<>();
    private List<CareerDisciplineType> listCareerDiscipline = new ArrayList<>();
    private List<AppraisalPerformanceGroup> listPerformanceGroup = new ArrayList<>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEvalStartDate() {
		return evalStartDate;
	}
	public void setEvalStartDate(Date evalStartDate) {
		this.evalStartDate = evalStartDate;
	}
	public Date getEvalEndDate() {
		return evalEndDate;
	}
	public void setEvalEndDate(Date evalEndDate) {
		this.evalEndDate = evalEndDate;
	}
	public Boolean getIsGapCompetency() {
		return isGapCompetency;
	}
	public void setIsGapCompetency(Boolean isGapCompetency) {
		this.isGapCompetency = isGapCompetency;
	}
	public Boolean getIsPerformanceScoring() {
		return isPerformanceScoring;
	}
	public void setIsPerformanceScoring(Boolean isPerformanceScoring) {
		this.isPerformanceScoring = isPerformanceScoring;
	}
	public Boolean getIsAchievement() {
		return isAchievement;
	}
	public void setIsAchievement(Boolean isAchievement) {
		this.isAchievement = isAchievement;
	}
	public Boolean getIsIndiscipline() {
		return isIndiscipline;
	}
	public void setIsIndiscipline(Boolean isIndiscipline) {
		this.isIndiscipline = isIndiscipline;
	}
	public Long getAppraisalPerformanceGroupId() {
		return appraisalPerformanceGroupId;
	}
	public void setAppraisalPerformanceGroupId(Long appraisalPerformanceGroupId) {
		this.appraisalPerformanceGroupId = appraisalPerformanceGroupId;
	}
	public Map<Long, BigDecimal> getListAchievementScore() {
		return listAchievementScore;
	}
	public void setListAchievementScore(Map<Long, BigDecimal> listAchievementScore) {
		this.listAchievementScore = listAchievementScore;
	}
	public Map<Long, BigDecimal> getListIndisciplineScore() {
		return listIndisciplineScore;
	}
	public void setListIndisciplineScore(Map<Long, BigDecimal> listIndisciplineScore) {
		this.listIndisciplineScore = listIndisciplineScore;
	}
	public List<CareerAwardType> getListCareerAward() {
		return listCareerAward;
	}
	public void setListCareerAward(List<CareerAwardType> listCareerAward) {
		this.listCareerAward = listCareerAward;
	}
	public List<CareerDisciplineType> getListCareerDiscipline() {
		return listCareerDiscipline;
	}
	public void setListCareerDiscipline(List<CareerDisciplineType> listCareerDiscipline) {
		this.listCareerDiscipline = listCareerDiscipline;
	}
	public List<AppraisalPerformanceGroup> getListPerformanceGroup() {
		return listPerformanceGroup;
	}
	public void setListPerformanceGroup(List<AppraisalPerformanceGroup> listPerformanceGroup) {
		this.listPerformanceGroup = listPerformanceGroup;
	}
    
}
