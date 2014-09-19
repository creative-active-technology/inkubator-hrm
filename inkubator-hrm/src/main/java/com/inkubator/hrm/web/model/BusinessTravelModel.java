package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.BusinessTravelComponent;
import com.inkubator.hrm.entity.EmpData;

/**
 *
 * @author rizkykojek
 */
public class BusinessTravelModel implements Serializable {
	
	private Long id;
	private String businessTravelNo;
	private EmpData empData;
	private String destination;
	private Date proposeDate;
	private Long travelZoneId;
	private Long travelTypeId;
	private Date start;
	private Date end;
	private String description;
	private String golonganJabatanName;
	private Double totalAmount;
	private List<BusinessTravelComponent> businessTravelComponents;
	
	public BusinessTravelModel(){
		businessTravelComponents = new ArrayList<BusinessTravelComponent>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBusinessTravelNo() {
		return businessTravelNo;
	}
	public void setBusinessTravelNo(String businessTravelNo) {
		this.businessTravelNo = businessTravelNo;
	}
	public EmpData getEmpData() {
		return empData;
	}
	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getProposeDate() {
		return proposeDate;
	}
	public void setProposeDate(Date proposeDate) {
		this.proposeDate = proposeDate;
	}
	public Long getTravelZoneId() {
		return travelZoneId;
	}
	public void setTravelZoneId(Long travelZoneId) {
		this.travelZoneId = travelZoneId;
	}
	public Long getTravelTypeId() {
		return travelTypeId;
	}
	public void setTravelTypeId(Long travelTypeId) {
		this.travelTypeId = travelTypeId;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGolonganJabatanName() {
		return golonganJabatanName;
	}
	public void setGolonganJabatanName(String golonganJabatanName) {
		this.golonganJabatanName = golonganJabatanName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<BusinessTravelComponent> getBusinessTravelComponents() {
		return businessTravelComponents;
	}
	public void setBusinessTravelComponents(List<BusinessTravelComponent> businessTravelComponents) {
		this.businessTravelComponents = businessTravelComponents;
	}
	
	public Boolean getIsPaginator(){
		return businessTravelComponents.size() > 11;
	}
	
}
