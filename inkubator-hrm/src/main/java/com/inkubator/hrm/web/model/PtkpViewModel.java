/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author deni
 */
public class PtkpViewModel implements Serializable{
    private Long id;
    private String nik;
    private String name;
    private Date age;
    private Integer gender;
    private String status;
    private Integer tanggungan;
    private BigDecimal nominalPtkp;
    private String firstName;
    private String lastName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTanggungan() {
        return tanggungan;
    }

    public void setTanggungan(Integer tanggungan) {
        this.tanggungan = tanggungan;
    }

    public BigDecimal getNominalPtkp() {
        return nominalPtkp;
    }

    public void setNominalPtkp(BigDecimal nominalPtkp) {
        this.nominalPtkp = nominalPtkp;
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
    
    
    
}
