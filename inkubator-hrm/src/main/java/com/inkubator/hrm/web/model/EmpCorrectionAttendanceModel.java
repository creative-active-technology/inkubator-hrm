package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.entity.WtWorkingHour;

/**
*
* @author rizkykojek
*/
public class EmpCorrectionAttendanceModel implements Serializable {

	private Long id;
	private EmpData empData;
    private Date startDate;
    private Date endDate;
    private Date requestDate;
    private String requestCode;
    private String workingGroupName;
    private WtPeriode period;
    private List<EmpCorrectionAttendanceDetailModel> listDetail = new ArrayList<EmpCorrectionAttendanceDetailModel>();
    private List<WtWorkingHour> listWorkingHours = new ArrayList<WtWorkingHour>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public String getWorkingGroupName() {
		return workingGroupName;
	}
	public void setWorkingGroupName(String workingGroupName) {
		this.workingGroupName = workingGroupName;
	}
	public WtPeriode getPeriod() {
		return period;
	}
	public void setPeriod(WtPeriode period) {
		this.period = period;
	}
	public List<EmpCorrectionAttendanceDetailModel> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<EmpCorrectionAttendanceDetailModel> listDetail) {
		this.listDetail = listDetail;
	}
	public List<WtWorkingHour> getListWorkingHours() {
		return listWorkingHours;
	}
	public void setListWorkingHours(List<WtWorkingHour> listWorkingHours) {
		this.listWorkingHours = listWorkingHours;
	}
    
}
