/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.search;

import com.inkubator.webcore.util.SearchParameter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arsyad_
 */
public class ReportSearchRecruitmentSearchParameter extends SearchParameter{
    
    private List<Long> listEducationLevel = new ArrayList<Long>();
    private List<Long> listKlasifikasiKerja = new ArrayList<Long>();
    private String gender;
    private String ageStart;
    private String ageEnd;
    private String careerCandidate;

    public List<Long> getListEducationLevel() {
        return listEducationLevel;
    }

    public void setListEducationLevel(List<Long> listEducationLevel) {
        this.listEducationLevel = listEducationLevel;
    }

    public List<Long> getListKlasifikasiKerja() {
        return listKlasifikasiKerja;
    }

    public void setListKlasifikasiKerja(List<Long> listKlasifikasiKerja) {
        this.listKlasifikasiKerja = listKlasifikasiKerja;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(String ageStart) {
        this.ageStart = ageStart;
    }

    public String getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(String ageEnd) {
        this.ageEnd = ageEnd;
    }

    public String getCareerCandidate() {
        return careerCandidate;
    }

    public void setCareerCandidate(String careerCandidate) {
        this.careerCandidate = careerCandidate;
    }


    
}
