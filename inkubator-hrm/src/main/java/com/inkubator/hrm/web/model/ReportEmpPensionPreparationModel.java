/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public class ReportEmpPensionPreparationModel implements Serializable {
    private Long id;
    
    private String nik;
    private String firstName;
    private String lastName;
    private String jabatan;
    private String golJabatan;
    private Integer usiaKaryawan;
    private Date tglLahir;
    private Date tglMulaiBekerja;
    private BigInteger departmentId;
    private String departmentName;
    private BigInteger empTypeId;
    private String statusKaryawan;

    public BigInteger getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(BigInteger empTypeId) {
        this.empTypeId = empTypeId;
    }

    public String getStatusKaryawan() {
        return statusKaryawan;
    }

    public void setStatusKaryawan(String statusKaryawan) {
        this.statusKaryawan = statusKaryawan;
    }
    
    

    public BigInteger getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(BigInteger departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getGolJabatan() {
        return golJabatan;
    }

    public void setGolJabatan(String golJabatan) {
        this.golJabatan = golJabatan;
    }

    public Integer getUsiaKaryawan() {
        return usiaKaryawan;
    }

    public void setUsiaKaryawan(Integer usiaKaryawan) {
        this.usiaKaryawan = usiaKaryawan;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Date getTglMulaiBekerja() {
        return tglMulaiBekerja;
    }

    public void setTglMulaiBekerja(Date tglMulaiBekerja) {
        this.tglMulaiBekerja = tglMulaiBekerja;
    }
    
}
