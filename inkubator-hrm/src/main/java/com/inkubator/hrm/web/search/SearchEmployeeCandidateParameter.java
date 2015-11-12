package com.inkubator.hrm.web.search;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class SearchEmployeeCandidateParameter {
	
	private List<Long> listJabatanId = new ArrayList<Long>();	
	private List<Long> listReligionId = new ArrayList<Long>();	
	private List<Integer> listAge = new ArrayList<Integer>();
    private List<Integer> listJoinDate = new ArrayList<Integer>(); 
    private List<Long> listEducationlevelId = new ArrayList<Long>();
    private Double gpa;
    private String gender;
    
	public List<Long> getListJabatanId() {
		return listJabatanId;
	}
	public void setListJabatanId(List<Long> listJabatanId) {
		this.listJabatanId = listJabatanId;
	}
	public List<Integer> getListAge() {
		return listAge;
	}
	public void setListAge(List<Integer> listAge) {
		this.listAge = listAge;
	}
	public List<Integer> getListJoinDate() {
		return listJoinDate;
	}
	public void setListJoinDate(List<Integer> listJoinDate) {
		this.listJoinDate = listJoinDate;
	}
	public List<Long> getListEducationlevelId() {
		return listEducationlevelId;
	}
	public void setListEducationlevelId(List<Long> listEducationlevelId) {
		this.listEducationlevelId = listEducationlevelId;
	}
	public Double getGpa() {
		return gpa;
	}
	public void setGpa(Double gpa) {
		this.gpa = gpa;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<Long> getListReligionId() {
		return listReligionId;
	}
	public void setListReligionId(List<Long> listReligionId) {
		this.listReligionId = listReligionId;
	}
    
}
