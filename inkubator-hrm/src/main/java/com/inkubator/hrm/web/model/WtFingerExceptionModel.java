/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Deni
 */
public class WtFingerExceptionModel implements Serializable{
    private Long id;
    private Integer departmentLikeOrEqual;
    private String departmentName;
    private Integer employeeTypeLikeOrEqual;
    private String employeeTypeName;
    private Integer gender;
    private Long golonganJabatanId;
    private Integer sortBy;
    private Integer orderBy;
    private Date startDate;
    private Date endDate;
    private Boolean extendException;
    private String empData;

    public Integer getDepartmentLikeOrEqual() {
        return departmentLikeOrEqual;
    }

    public void setDepartmentLikeOrEqual(Integer departmentLikeOrEqual) {
        this.departmentLikeOrEqual = departmentLikeOrEqual;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getEmployeeTypeLikeOrEqual() {
        return employeeTypeLikeOrEqual;
    }

    public void setEmployeeTypeLikeOrEqual(Integer employeeTypeLikeOrEqual) {
        this.employeeTypeLikeOrEqual = employeeTypeLikeOrEqual;
    }

    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getGolonganJabatanId() {
        return golonganJabatanId;
    }

    public void setGolonganJabatanId(Long golonganJabatanId) {
        this.golonganJabatanId = golonganJabatanId;
    }

    public Integer getSortBy() {
        return sortBy;
    }

    public void setSortBy(Integer sortBy) {
        this.sortBy = sortBy;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
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

    public Boolean getExtendException() {
        return extendException;
    }

    public void setExtendException(Boolean extendException) {
        this.extendException = extendException;
    }

    public String getEmpData() {
        return empData;
    }

    public void setEmpData(String empData) {
        this.empData = empData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
