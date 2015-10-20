/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.KlasifikasiKerjaService;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.lazymodel.ReportSearchRecruitmentLazyDataModel;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;
import com.inkubator.webcore.controller.BaseController;

import ch.lambdaj.Lambda;

/**
 *
 * @author arsyad_
 */
@ManagedBean(name = "reportSearchRecruitmentViewController")
@ViewScoped
public class ReportSearchRecruitmentViewController extends BaseController{
    private ReportSearchRecruitmentSearchParameter searchParameter;
    private LazyDataModel<RecruitApplicant> lazyDataModel;
    private DualListModel<KlasifikasiKerja> klasifikasiKerjaDualModel;
    private DualListModel<EducationLevel> educationLevelDualModel;
    private RecruitApplicant selected;
    
    @ManagedProperty(value = "#{recruitApplicantService}")
    private RecruitApplicantService recruitApplicantService;
    @ManagedProperty(value = "#{klasifikasiKerjaService}")
    private KlasifikasiKerjaService klasifikasiKerjaService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    
    @PostConstruct
    @Override
    public void initialization(){
        super.initialization();
        try{
            searchParameter = new ReportSearchRecruitmentSearchParameter();
            List<KlasifikasiKerja> availableKlasifikasiKerja = klasifikasiKerjaService.getAllData();
            List<EducationLevel> availableEducationLevel = educationLevelService.getAllData();
            
            klasifikasiKerjaDualModel = new DualListModel<KlasifikasiKerja>(availableKlasifikasiKerja, new ArrayList<KlasifikasiKerja>());
            educationLevelDualModel = new DualListModel<EducationLevel>(availableEducationLevel, new ArrayList<EducationLevel>());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @PreDestroy
    public void cleanAndExit(){
        searchParameter = null;
        lazyDataModel = null;
        klasifikasiKerjaDualModel = null;
        educationLevelDualModel = null;
        recruitApplicantService = null;
        klasifikasiKerjaService = null;
        educationLevelService = null;
        selected = null;
    }
     
    public void doSearch(){
        searchParameter.setListEducationLevel(Lambda.extract(educationLevelDualModel.getTarget(), Lambda.on(EducationLevel.class).getId()));
        searchParameter.setListKlasifikasiKerja(Lambda.extract(klasifikasiKerjaDualModel.getTarget(), Lambda.on(KlasifikasiKerja.class).getId()));
        lazyDataModel = null;
    }
    
    public String doDetail() {
        return "/protected/report/report_search_applicant_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }

    public ReportSearchRecruitmentSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(ReportSearchRecruitmentSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitApplicant> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new ReportSearchRecruitmentLazyDataModel(searchParameter, recruitApplicantService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<RecruitApplicant> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public DualListModel<KlasifikasiKerja> getKlasifikasiKerjaDualModel() {
        return klasifikasiKerjaDualModel;
    }

    public void setKlasifikasiKerjaDualModel(DualListModel<KlasifikasiKerja> klasifikasiKerjaDualModel) {
        this.klasifikasiKerjaDualModel = klasifikasiKerjaDualModel;
    }

    public DualListModel<EducationLevel> getEducationLevelDualModel() {
        return educationLevelDualModel;
    }

    public void setEducationLevelDualModel(DualListModel<EducationLevel> educationLevelDualModel) {
        this.educationLevelDualModel = educationLevelDualModel;
    }

    public RecruitApplicantService getRecruitApplicantService() {
        return recruitApplicantService;
    }

    public void setRecruitApplicantService(RecruitApplicantService recruitApplicantService) {
        this.recruitApplicantService = recruitApplicantService;
    }

    public KlasifikasiKerjaService getKlasifikasiKerjaService() {
        return klasifikasiKerjaService;
    }

    public void setKlasifikasiKerjaService(KlasifikasiKerjaService klasifikasiKerjaService) {
        this.klasifikasiKerjaService = klasifikasiKerjaService;
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public RecruitApplicant getSelected() {
        return selected;
    }

    public void setSelected(RecruitApplicant selected) {
        this.selected = selected;
    }
    
    
    
}
