/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Deni
 */
public class EmpDataMatrixModel {
    private String male;
    private String female;
    private List<String> listGender = new ArrayList<String>();
    private int age;
    private Integer ages;
    private Double ages2;

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public List<String> getListGender() {
        return listGender;
    }

    public void setListGender(List<String> listGender) {
        this.listGender = listGender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getAges() {
        return ages;
    }

    public void setAges(Integer ages) {
        this.ages = ages;
    }

    public Double getAges2() {
        return ages2;
    }

    public void setAges2(Double ages2) {
        this.ages2 = ages2;
    }
    
    
}
