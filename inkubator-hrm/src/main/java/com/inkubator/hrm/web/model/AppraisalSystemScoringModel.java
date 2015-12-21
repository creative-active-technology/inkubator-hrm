package com.inkubator.hrm.web.model;

import java.io.Serializable;

import com.inkubator.hrm.entity.AppraisalSystemScoring;

public class AppraisalSystemScoringModel implements Serializable {
	private Long id;
    private String name;
	private Long appraisalSystemScoringId;
	private Integer value;
	private String labelMask;
	private String labels[];
	private Integer scaleValue[];
	private String description[];
	private Integer totalOption;
	
	public AppraisalSystemScoringModel(){
		labels = new String[20];
		scaleValue = new Integer[20];
		description = new String[20];
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAppraisalSystemScoringId() {
		return appraisalSystemScoringId;
	}
	public void setAppraisalSystemScoringId(Long appraisalSystemScoringId) {
		this.appraisalSystemScoringId = appraisalSystemScoringId;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getLabelMask() {
		return labelMask;
	}
	public void setLabelMask(String labelMask) {
		this.labelMask = labelMask;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public Integer[] getScaleValue() {
		return scaleValue;
	}

	public void setScaleValue(Integer[] scaleValue) {
		this.scaleValue = scaleValue;
	}

	public String[] getDescription() {
		return description;
	}

	public void setDescription(String[] description) {
		this.description = description;
	}

	public Integer getTotalOption() {
		return totalOption;
	}

	public void setTotalOption(Integer totalOption) {
		this.totalOption = totalOption;
	}

	
	
}
