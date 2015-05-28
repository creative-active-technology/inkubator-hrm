
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

import com.inkubator.hrm.entity.WtWorkingHour;

/**
 *
 * @author rizkykojek
 */
public class EmpCorrectionAttendanceDetailModel implements Serializable {

	private Long id;
	private Long empCorrectionAttendanceId;
    private WtWorkingHour workingHour;
    private Date attendanceDate;
    private Date attendanceIn;
    private Date attendanceOut;
    private Date correctionIn;
    private Date correctionOut;    
    private String description;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpCorrectionAttendanceId() {
		return empCorrectionAttendanceId;
	}
	public void setEmpCorrectionAttendanceId(Long empCorrectionAttendanceId) {
		this.empCorrectionAttendanceId = empCorrectionAttendanceId;
	}
	public WtWorkingHour getWorkingHour() {
		return workingHour;
	}
	public void setWorkingHour(WtWorkingHour workingHour) {
		this.workingHour = workingHour;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}	
	public Date getAttendanceIn() {
		return attendanceIn;
	}
	public void setAttendanceIn(Date attendanceIn) {
		this.attendanceIn = attendanceIn;
	}
	public Date getAttendanceOut() {
		return attendanceOut;
	}
	public void setAttendanceOut(Date attendanceOut) {
		this.attendanceOut = attendanceOut;
	}
	public Date getCorrectionIn() {
		return correctionIn;
	}
	public void setCorrectionIn(Date correctionIn) {
		this.correctionIn = correctionIn;
	}
	public Date getCorrectionOut() {
		return correctionOut;
	}
	public void setCorrectionOut(Date correctionOut) {
		this.correctionOut = correctionOut;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
