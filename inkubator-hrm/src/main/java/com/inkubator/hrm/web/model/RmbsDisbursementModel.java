package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.inkubator.hrm.entity.RmbsApplication;

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
    private List<RmbsApplication> listRmbsApplication;
    
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
	public List<RmbsApplication> getListRmbsApplication() {
		return listRmbsApplication;
	}
	public void setListRmbsApplication(List<RmbsApplication> listRmbsApplication) {
		this.listRmbsApplication = listRmbsApplication;
	}    
}
