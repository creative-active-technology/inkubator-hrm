/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author deni
 */
public class UnregSalaryViewModel {
    private Long id;
    private BigInteger unregSalaryId;
    private String code;
    private String name;
    private String year;
    private Integer bulan;
    private Integer month;
    private Integer totalComponent;
    private Date salaryDate;
    private Long totalComponents;
    private BigInteger total;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTotalComponent() {
        return totalComponent;
    }

    public void setTotalComponent(Integer totalComponent) {
        this.totalComponent = totalComponent;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Long getTotalComponents() {
        return totalComponents;
    }

    public void setTotalComponents(Long totalComponents) {
        this.totalComponents = totalComponents;
    }

    public BigInteger getUnregSalaryId() {
        return unregSalaryId;
    }

    public void setUnregSalaryId(BigInteger unregSalaryId) {
        this.unregSalaryId = unregSalaryId;
    }

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getBulan() {
		return bulan;
	}

	public void setBulan(Integer bulan) {
		this.bulan = bulan;
	}

	public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
    
    
}
