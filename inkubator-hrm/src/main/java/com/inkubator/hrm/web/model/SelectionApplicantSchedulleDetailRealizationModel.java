package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author rizkykojek
 */
public class SelectionApplicantSchedulleDetailRealizationModel implements Serializable {

	private Long id;
    private Long selectionApplicantSchedulleDetailId;
    private Date realizationDate;
    private Date realizationTimeStart;
    private Date realizationTimeEnd;
    private String realizationRoom;
    private String notes;
    private Date scoringDate;
    private Double scoringPoint;
    private Long scoringByEmpDataId;
    private String status;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSelectionApplicantSchedulleDetailId() {
		return selectionApplicantSchedulleDetailId;
	}
	public void setSelectionApplicantSchedulleDetailId(Long selectionApplicantSchedulleDetailId) {
		this.selectionApplicantSchedulleDetailId = selectionApplicantSchedulleDetailId;
	}
	public Date getRealizationDate() {
		return realizationDate;
	}
	public void setRealizationDate(Date realizationDate) {
		this.realizationDate = realizationDate;
	}
	public Date getRealizationTimeStart() {
		return realizationTimeStart;
	}
	public void setRealizationTimeStart(Date realizationTimeStart) {
		this.realizationTimeStart = realizationTimeStart;
	}
	public Date getRealizationTimeEnd() {
		return realizationTimeEnd;
	}
	public void setRealizationTimeEnd(Date realizationTimeEnd) {
		this.realizationTimeEnd = realizationTimeEnd;
	}
	public String getRealizationRoom() {
		return realizationRoom;
	}
	public void setRealizationRoom(String realizationRoom) {
		this.realizationRoom = realizationRoom;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Date getScoringDate() {
		return scoringDate;
	}
	public void setScoringDate(Date scoringDate) {
		this.scoringDate = scoringDate;
	}
	public Double getScoringPoint() {
		return scoringPoint;
	}
	public void setScoringPoint(Double scoringPoint) {
		this.scoringPoint = scoringPoint;
	}
	public Long getScoringByEmpDataId() {
		return scoringByEmpDataId;
	}
	public void setScoringByEmpDataId(Long scoringByEmpDataId) {
		this.scoringByEmpDataId = scoringByEmpDataId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
