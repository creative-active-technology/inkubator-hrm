package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author rizkykojek
 */
public class AppraisalCompetencyUnitIndicatorModel implements Serializable {

	private Long id;
	private String indicator;
	private Integer levelIndex;
	private Long competencyUnitId;
	
	//not persisted
	private String competencyGroupName;
	private String competencyTypeName;
	private String competencyUnitName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public Integer getLevelIndex() {
		return levelIndex;
	}
	public void setLevelIndex(Integer levelIndex) {
		this.levelIndex = levelIndex;
	}
	public String getCompetencyGroupName() {
		return competencyGroupName;
	}
	public void setCompetencyGroupName(String competencyGroupName) {
		this.competencyGroupName = competencyGroupName;
	}
	public String getCompetencyTypeName() {
		return competencyTypeName;
	}
	public void setCompetencyTypeName(String competencyTypeName) {
		this.competencyTypeName = competencyTypeName;
	}
	public String getCompetencyUnitName() {
		return competencyUnitName;
	}
	public void setCompetencyUnitName(String competencyUnitName) {
		this.competencyUnitName = competencyUnitName;
	}
	public Long getCompetencyUnitId() {
		return competencyUnitId;
	}
	public void setCompetencyUnitId(Long competencyUnitId) {
		this.competencyUnitId = competencyUnitId;
	}

}
