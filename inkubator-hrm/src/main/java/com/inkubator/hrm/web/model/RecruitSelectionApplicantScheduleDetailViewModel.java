package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.inkubator.hrm.entity.EmpData;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class RecruitSelectionApplicantScheduleDetailViewModel implements Serializable {
	
	private Long id;
	private Long scheduleId;
	private Long applicantId;
	private String applicantName;
	private EmpData empDataPic;
	private Date scheduleDate;
	private Date scheduleStartTime;
	private Date scheduleEndTime;
	private String room;
	private String notes;
	private Long selectionTypeId;
	private String selectionTypeName;
	private String candidateStatus;
	private Boolean isHaveBeenRealized;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	public Date getScheduleDate() {
		return scheduleDate;
	}
	public EmpData getEmpDataPic() {
		return empDataPic;
	}
	public void setEmpDataPic(EmpData empDataPic) {
		this.empDataPic = empDataPic;
	}
	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}
	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}
	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Long getSelectionTypeId() {
		return selectionTypeId;
	}
	public void setSelectionTypeId(Long selectionTypeId) {
		this.selectionTypeId = selectionTypeId;
	}
	public String getSelectionTypeName() {
		return selectionTypeName;
	}
	public void setSelectionTypeName(String selectionTypeName) {
		this.selectionTypeName = selectionTypeName;
	}
	public String getCandidateStatus() {
		return candidateStatus;
	}
	public void setCandidateStatus(String candidateStatus) {
		this.candidateStatus = candidateStatus;
	}
	public Boolean getIsHaveBeenRealized() {
		return isHaveBeenRealized;
	}
	public void setIsHaveBeenRealized(Boolean isHaveBeenRealized) {
		this.isHaveBeenRealized = isHaveBeenRealized;
	}
	
	
	
	
}
