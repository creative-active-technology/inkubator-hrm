/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.model;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.RecruitVacancySelection;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni
 */
public class RecruitVacancySelectionDetailModel implements Serializable {

    private String recruitSelectionSeriesName;
    private Date time;
    private String place;
    private BigDecimal basicCost;
    private BigDecimal individualCost;
    private Date recruitVacancySelectionDate;
    private Date startDate;
    private Date endDate;
    private LazyDataModel<EmpData> lazyDataModel;

    public String getRecruitSelectionSeriesName() {
        return recruitSelectionSeriesName;
    }

    public void setRecruitSelectionSeriesName(String recruitSelectionSeriesName) {
        this.recruitSelectionSeriesName = recruitSelectionSeriesName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public BigDecimal getBasicCost() {
        return basicCost;
    }

    public void setBasicCost(BigDecimal basicCost) {
        this.basicCost = basicCost;
    }

    public BigDecimal getIndividualCost() {
        return individualCost;
    }

    public void setIndividualCost(BigDecimal individualCost) {
        this.individualCost = individualCost;
    }

    public Date getRecruitVacancySelectionDate() {
        return recruitVacancySelectionDate;
    }

    public void setRecruitVacancySelectionDate(Date recruitVacancySelectionDate) {
        this.recruitVacancySelectionDate = recruitVacancySelectionDate;
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

    public LazyDataModel<EmpData> getLazyDataModel() {
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<EmpData> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    

    
}
