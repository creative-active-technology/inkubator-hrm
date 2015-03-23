/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.math.BigInteger;

/**
 *
 * @author Deni
 */
public class LoanNewSchemaListOfEmpViewModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String nik;
    private String schemaCode;
    private String code;
    private String noSk;
    private BigInteger idEmp;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoSk() {
        return noSk;
    }

    public void setNoSk(String noSk) {
        this.noSk = noSk;
    }

    public String getSchemaCode() {
        return schemaCode;
    }

    public void setSchemaCode(String schemaCode) {
        this.schemaCode = schemaCode;
    }

    public BigInteger getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(BigInteger idEmp) {
        this.idEmp = idEmp;
    }
    
    
}
