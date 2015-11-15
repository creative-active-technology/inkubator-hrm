package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.inkubator.hrm.entity.EmpData;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class SelectionSeriesDetailInitialParameterModel implements Serializable {
	
	private Long selectionSeriesId;
	private Long recruitSelectionTypeId;
	private String selectionSeriesDetailName;
	private EmpData empDataPic;
	private Date scheduleDate;
	private Date scheduleStartTime;
	private Date scheduleEndTime;
	private String room;
	private String notes;
	
	public Long getSelectionSeriesId() {
		return selectionSeriesId;
	}
	public void setSelectionSeriesId(Long selectionSeriesId) {
		this.selectionSeriesId = selectionSeriesId;
	}
	
	public String getSelectionSeriesDetailName() {
		return selectionSeriesDetailName;
	}
	public void setSelectionSeriesDetailName(String selectionSeriesDetailName) {
		this.selectionSeriesDetailName = selectionSeriesDetailName;
	}
	public EmpData getEmpDataPic() {
		return empDataPic;
	}
	public void setEmpDataPic(EmpData empDataPic) {
		this.empDataPic = empDataPic;
	}
	public Date getScheduleDate() {
		return scheduleDate;
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
	public Long getRecruitSelectionTypeId() {
		return recruitSelectionTypeId;
	}
	public void setRecruitSelectionTypeId(Long recruitSelectionTypeId) {
		this.recruitSelectionTypeId = recruitSelectionTypeId;
	}
	
	
}
