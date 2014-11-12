/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;

/**
 *
 * @author Deni
 */
public class PermitDistributionModel implements Serializable{
    private Long permitId;
    private String permitName;
    private Integer departmentLikeOrEqual;
    private String departmentName;
    private Integer employeeTypeLikeOrEqual;
    private String employeeTypeName;
    private Integer gender;
    private Long golonganJabatanId;
    private Integer sortBy;
    private Integer orderBy;
    private Double startBalance;

    public Long getPermitId() {
        return permitId;
    }

    public void setPermitId(Long permitId) {
        this.permitId = permitId;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    

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

    public Double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(Double startBalance) {
        this.startBalance = startBalance;
    }

    
    
}
