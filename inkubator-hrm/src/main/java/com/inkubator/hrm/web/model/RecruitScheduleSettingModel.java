package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class RecruitScheduleSettingModel implements Serializable {
	
	private Long id;
	private Long recruitHireApplyId;
	private String recruitHireApplyCode;
	private Long recruitMppApplyId;
	private String recruitMppApplyName;
	private Long recruitSelectionApplicantSchedulleId;
	/*private Long empCoordinatorId;
	private String empCoordinatorNik;
	private String empCoordinatorFullName;*/
	private EmpData empData;
	private Long selectionSeriesId;
	private String selectionSeriesName;
	private Long jabatanId;
	private String jabatanName;
	private Long recruitMppPeriodId;
	private Date startDateMppPeriod;
	private Date endDateMppPeriod;
	private Integer totalRecruitment;
	private List<RecruitmenSelectionSeriesDetail> listSelectionSeriesDetails = new ArrayList<RecruitmenSelectionSeriesDetail>();
	private Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> mapSelectionApplicantSchedule = new HashMap<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>>();
	private Boolean isAlreadyHaveSelectionScheduleSeries;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRecruitHireApplyId() {
		return recruitHireApplyId;
	}
	public void setRecruitHireApplyId(Long recruitHireApplyId) {
		this.recruitHireApplyId = recruitHireApplyId;
	}
	/*public Long getEmpCoordinatorId() {
		return empCoordinatorId;
	}
	public void setEmpCoordinatorId(Long empCoordinatorId) {
		this.empCoordinatorId = empCoordinatorId;
	}*/
	public Long getSelectionSeriesId() {
		return selectionSeriesId;
	}
	public void setSelectionSeriesId(Long selectionSeriesId) {
		this.selectionSeriesId = selectionSeriesId;
	}
	public Integer getTotalRecruitment() {
		return totalRecruitment;
	}
	public void setTotalRecruitment(Integer totalRecruitment) {
		this.totalRecruitment = totalRecruitment;
	}
	public Long getJabatanId() {
		return jabatanId;
	}
	public void setJabatanId(Long jabatanId) {
		this.jabatanId = jabatanId;
	}
	public String getJabatanName() {
		return jabatanName;
	}
	public void setJabatanName(String jabatanName) {
		this.jabatanName = jabatanName;
	}
	public Long getRecruitMppPeriodId() {
		return recruitMppPeriodId;
	}
	public void setRecruitMppPeriodId(Long recruitMppPeriodId) {
		this.recruitMppPeriodId = recruitMppPeriodId;
	}
	public Date getStartDateMppPeriod() {
		return startDateMppPeriod;
	}
	public void setStartDateMppPeriod(Date startDateMppPeriod) {
		this.startDateMppPeriod = startDateMppPeriod;
	}
	public Date getEndDateMppPeriod() {
		return endDateMppPeriod;
	}
	public void setEndDateMppPeriod(Date endDateMppPeriod) {
		this.endDateMppPeriod = endDateMppPeriod;
	}
	public String getRecruitHireApplyCode() {
		return recruitHireApplyCode;
	}
	public void setRecruitHireApplyCode(String recruitHireApplyCode) {
		this.recruitHireApplyCode = recruitHireApplyCode;
	}
	public Long getRecruitMppApplyId() {
		return recruitMppApplyId;
	}
	public void setRecruitMppApplyId(Long recruitMppApplyId) {
		this.recruitMppApplyId = recruitMppApplyId;
	}
	public String getRecruitMppApplyName() {
		return recruitMppApplyName;
	}
	public void setRecruitMppApplyName(String recruitMppApplyName) {
		this.recruitMppApplyName = recruitMppApplyName;
	}
	/*public String getEmpCoordinatorNik() {
		return empCoordinatorNik;
	}
	public void setEmpCoordinatorNik(String empCoordinatorNik) {
		this.empCoordinatorNik = empCoordinatorNik;
	}
	public String getEmpCoordinatorFullName() {
		return empCoordinatorFullName;
	}
	public void setEmpCoordinatorFullName(String empCoordinatorFullName) {
		this.empCoordinatorFullName = empCoordinatorFullName;
	}*/
	
	
	public String getSelectionSeriesName() {
		return selectionSeriesName;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public void setSelectionSeriesName(String selectionSeriesName) {
		this.selectionSeriesName = selectionSeriesName;
	}
	public List<RecruitmenSelectionSeriesDetail> getListSelectionSeriesDetails() {
		return listSelectionSeriesDetails;
	}
	public void setListSelectionSeriesDetails(
			List<RecruitmenSelectionSeriesDetail> listSelectionSeriesDetails) {
		this.listSelectionSeriesDetails = listSelectionSeriesDetails;
	}
	public Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> getMapSelectionApplicantSchedule() {
		return mapSelectionApplicantSchedule;
	}
	public void setMapSelectionApplicantSchedule(
			Map<Long, List<RecruitSelectionApplicantScheduleDetailViewModel>> mapSelectionApplicantSchedule) {
		this.mapSelectionApplicantSchedule = mapSelectionApplicantSchedule;
	}
	public Boolean getIsAlreadyHaveSelectionScheduleSeries() {
		return isAlreadyHaveSelectionScheduleSeries;
	}
	public void setIsAlreadyHaveSelectionScheduleSeries(
			Boolean isAlreadyHaveSelectionScheduleSeries) {
		this.isAlreadyHaveSelectionScheduleSeries = isAlreadyHaveSelectionScheduleSeries;
	}
	public Long getRecruitSelectionApplicantSchedulleId() {
		return recruitSelectionApplicantSchedulleId;
	}
	public void setRecruitSelectionApplicantSchedulleId(
			Long recruitSelectionApplicantSchedulleId) {
		this.recruitSelectionApplicantSchedulleId = recruitSelectionApplicantSchedulleId;
	}
	
	
}
