package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rizkykojek
 */
public class RmbsDisbursementModel implements Serializable {

	private Long id;
    private String code;
    private Date disbursementDate;
    private Date payrollPeriodDate;
    private String description;
    private List<Long> listRmbsApplicationId;
    private Map<Long, Boolean> selectedIds =  new HashMap<Long, Boolean>();
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getDisbursementDate() {
		return disbursementDate;
	}
	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}
	public Date getPayrollPeriodDate() {
		return payrollPeriodDate;
	}
	public void setPayrollPeriodDate(Date payrollPeriodDate) {
		this.payrollPeriodDate = payrollPeriodDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<Long, Boolean> getSelectedIds() {
		return selectedIds;
	}
	public void setSelectedIds(Map<Long, Boolean> selectedIds) {
		this.selectedIds = selectedIds;
	} 
	public List<Long> getListRmbsApplicationId() {
		listRmbsApplicationId = new ArrayList<Long>();
		for(Map.Entry<Long, Boolean> selected : selectedIds.entrySet()){
			if(StringUtils.equals(String.valueOf(selected.getValue()), "true")){
				listRmbsApplicationId.add(selected.getKey());
			}
		}
		return listRmbsApplicationId;
	}
	public void setListRmbsApplicationId(List<Long> listRmbsApplicationId) {
		//do nothing
	}
	
}
