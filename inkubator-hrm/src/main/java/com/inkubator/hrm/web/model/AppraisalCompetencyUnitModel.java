package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inkubator.hrm.entity.AppraisalCompetencyGroup;
import com.inkubator.hrm.entity.AppraisalCompetencyType;

/**
 *
 * @author rizkykojek
 */
public class AppraisalCompetencyUnitModel implements Serializable {

	private Long id;
    private String name;
    private String description;
    private Long competencyGroupId;
    private Long competencyTypeId;
    
    private List<AppraisalCompetencyGroup> listCompetencyGroup = new ArrayList<>();
    private List<AppraisalCompetencyType> listCompetencyType = new ArrayList<>();
    
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCompetencyGroupId() {
		return competencyGroupId;
	}
	public void setCompetencyGroupId(Long competencyGroupId) {
		this.competencyGroupId = competencyGroupId;
	}
	public Long getCompetencyTypeId() {
		return competencyTypeId;
	}
	public void setCompetencyTypeId(Long competencyTypeId) {
		this.competencyTypeId = competencyTypeId;
	}
	public List<AppraisalCompetencyGroup> getListCompetencyGroup() {
		return listCompetencyGroup;
	}
	public void setListCompetencyGroup(List<AppraisalCompetencyGroup> listCompetencyGroup) {
		this.listCompetencyGroup = listCompetencyGroup;
	}
	public List<AppraisalCompetencyType> getListCompetencyType() {
		return listCompetencyType;
	}
	public void setListCompetencyType(List<AppraisalCompetencyType> listCompetencyType) {
		this.listCompetencyType = listCompetencyType;
	}
    
}
