package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author rizkykojek
 */
public class ApplicantStatisticViewModel implements Serializable {
	
	private PieChartModel workingExperienceApplicant;
	private BarChartModel educationLevelApplicant;
	private String jobClassificationApplicant;
	//private LineChartModel jobClassificationApplicantLineModel;
	private List<ApplicantAgeViewModel> listApplicantAge;
	
	public PieChartModel getWorkingExperienceApplicant() {
		return workingExperienceApplicant;
	}
	public void setWorkingExperienceApplicant(PieChartModel workingExperienceApplicant) {
		this.workingExperienceApplicant = workingExperienceApplicant;
	}
	public BarChartModel getEducationLevelApplicant() {
		return educationLevelApplicant;
	}
	public void setEducationLevelApplicant(BarChartModel educationLevelApplicant) {
		this.educationLevelApplicant = educationLevelApplicant;
	}
	public String getJobClassificationApplicant() {
		return jobClassificationApplicant;
	}
	public void setJobClassificationApplicant(String jobClassificationApplicant) {
		this.jobClassificationApplicant = jobClassificationApplicant;
	}
	public List<ApplicantAgeViewModel> getListApplicantAge() {
		return listApplicantAge;
	}
	public void setListApplicantAge(List<ApplicantAgeViewModel> listApplicantAge) {
		this.listApplicantAge = listApplicantAge;
	}
	/*public LineChartModel getJobClassificationApplicantLineModel() {
		return jobClassificationApplicantLineModel;
	}
	public void setJobClassificationApplicantLineModel(LineChartModel jobClassificationApplicantLineModel) {
		this.jobClassificationApplicantLineModel = jobClassificationApplicantLineModel;
	}*/
	
}
