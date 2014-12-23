/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author deni
 */
public class PayComponentDataExceptionModelView implements Serializable{
    private Long id;
    private BigInteger paySalaryComponentId;
    private String name;
    private String code;
    private BigInteger jumlahKaryawan;
    private BigDecimal jumlahNominal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getPaySalaryComponentId() {
        return paySalaryComponentId;
    }

    public void setPaySalaryComponentId(BigInteger paySalaryComponentId) {
        this.paySalaryComponentId = paySalaryComponentId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getJumlahNominal() {
        if(jumlahNominal == null){
            jumlahNominal = new BigDecimal(0);
        }
        return jumlahNominal;
    }

    public void setJumlahNominal(BigDecimal jumlahNominal) {
        this.jumlahNominal = jumlahNominal;
    }

    public BigInteger getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(BigInteger jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }
    
    
}
