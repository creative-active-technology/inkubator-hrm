/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
public class SearchEmployeeModel implements Serializable{
    private Long id;
    private String name;
    private String lastName;
    private String codeJabatan;
    private String nikFrom;
    private String nikUntil;
    private int from;
    private int until;
    private int fromJoin;
    private int untilJoin;
    private String firstName;
    private String empTypeName;
    private String departments;
    private String golonganJabatans;
    private String tipeKaryawan;
    private String[] employeeTypeName;
    private Double age;
    private Date joinDate;
    private DualListModel<Department> dualListModelDepartment = new DualListModel<>();
    private DualListModel<GolonganJabatan> dualListModelGoljab = new DualListModel<>();
    private List<String> listEmployeeType = new ArrayList<String>();
    private List<EmpData> listEmpData = new ArrayList<EmpData>();
    private LazyDataModel<EmpData> lazyDataModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DualListModel<Department> getDualListModelDepartment() {
        return dualListModelDepartment;
    }

    public void setDualListModelDepartment(DualListModel<Department> dualListModelDepartment) {
        this.dualListModelDepartment = dualListModelDepartment;
    }

    public DualListModel<GolonganJabatan> getDualListModelGoljab() {
        return dualListModelGoljab;
    }

    public void setDualListModelGoljab(DualListModel<GolonganJabatan> dualListModelGoljab) {
        this.dualListModelGoljab = dualListModelGoljab;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getGolonganJabatans() {
        return golonganJabatans;
    }

    public void setGolonganJabatans(String golonganJabatans) {
        this.golonganJabatans = golonganJabatans;
    }

    public List<String> getListEmployeeType() {
        return listEmployeeType;
    }

    public void setListEmployeeType(List<String> listEmployeeType) {
        this.listEmployeeType = listEmployeeType;
    }

    public String[] getEmployeeTypeName() {
        return employeeTypeName;
    }

    public void setEmployeeTypeName(String[] employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    public String getTipeKaryawan() {
        return tipeKaryawan;
    }

    public void setTipeKaryawan(String tipeKaryawan) {
        this.tipeKaryawan = tipeKaryawan;
    }

    public List<EmpData> getListEmpData() {
        return listEmpData;
    }

    public void setListEmpData(List<EmpData> listEmpData) {
        this.listEmpData = listEmpData;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCodeJabatan() {
        return codeJabatan;
    }

    public void setCodeJabatan(String codeJabatan) {
        this.codeJabatan = codeJabatan;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int until) {
        this.until = until;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public int getFromJoin() {
        return fromJoin;
    }

    public void setFromJoin(int fromJoin) {
        this.fromJoin = fromJoin;
    }

    public int getUntilJoin() {
        return untilJoin;
    }

    public void setUntilJoin(int untilJoin) {
        this.untilJoin = untilJoin;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getNikFrom() {
        return nikFrom;
    }

    public void setNikFrom(String nikFrom) {
        this.nikFrom = nikFrom;
    }

    public String getNikUntil() {
        return nikUntil;
    }

    public void setNikUntil(String nikUntil) {
        this.nikUntil = nikUntil;
    }

    public LazyDataModel<EmpData> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    
    
}
