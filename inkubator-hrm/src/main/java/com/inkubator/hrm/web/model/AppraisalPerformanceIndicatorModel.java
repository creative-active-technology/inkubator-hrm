package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class AppraisalPerformanceIndicatorModel implements Serializable {
	
	private Long id;
    private Long performanceGroupId;
    private Long systemScoringId;
    private String performanceIndicatorCode;
    private String performanceIndicatorName;
    private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPerformanceGroupId() {
		return performanceGroupId;
	}
	public void setPerformanceGroupId(Long performanceGroupId) {
		this.performanceGroupId = performanceGroupId;
	}
	public Long getSystemScoringId() {
		return systemScoringId;
	}
	public void setSystemScoringId(Long systemScoringId) {
		this.systemScoringId = systemScoringId;
	}
	public String getPerformanceIndicatorCode() {
		return performanceIndicatorCode;
	}
	public void setPerformanceIndicatorCode(String performanceIndicatorCode) {
		this.performanceIndicatorCode = performanceIndicatorCode;
	}
	public String getPerformanceIndicatorName() {
		return performanceIndicatorName;
	}
	public void setPerformanceIndicatorName(String performanceIndicatorName) {
		this.performanceIndicatorName = performanceIndicatorName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
    
}
