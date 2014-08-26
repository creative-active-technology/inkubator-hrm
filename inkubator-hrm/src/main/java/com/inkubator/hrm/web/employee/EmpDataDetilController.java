/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.EmpPersonAchievement;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.EmpPersonAchievementService;
import com.inkubator.hrm.service.PersonalDisciplineService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empDataDetilController")
@ViewScoped
public class EmpDataDetilController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    private String id;

    //personal discipline
    @ManagedProperty(value = "#{personalDisciplineService}")
    private PersonalDisciplineService personalDisciplineService;
    private List<PersonalDiscipline> listPersonalDiscipline; 
    
    //Achievement
    @ManagedProperty(value = "#{empPersonAchievementService}")
    private EmpPersonAchievementService empPersonAchievementService;
    private List<EmpPersonAchievement> listPersonAchievement; 
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");
            selectedEmpData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanDeskripsis());
            listJabatanSpesifikasi = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanSpesifikasis());
            listPersonalDiscipline = personalDisciplineService.getAllDataByEmployeeId(selectedEmpData.getId());
            listPersonAchievement = empPersonAchievementService.getAllDataByEmployeeId(selectedEmpData.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public String doEdit() {
        return "/protected/personalia/biodata_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doBack() {
        return "/protected/employee/employee_palcement_view.htm?faces-redirect=true";
    }

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    public void doSelectEmpCardName() {
        try {
            selectedEmpData = empDataService.getByEmpIdWithDetail(selectedEmpData.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public PersonalDisciplineService getPersonalDisciplineService() {
        return personalDisciplineService;
    }

    public void setPersonalDisciplineService(PersonalDisciplineService personalDisciplineService) {
        this.personalDisciplineService = personalDisciplineService;
    }

    public List<PersonalDiscipline> getListPersonalDiscipline() {
        return listPersonalDiscipline;
    }

    public void setListPersonalDiscipline(List<PersonalDiscipline> listPersonalDiscipline) {
        this.listPersonalDiscipline = listPersonalDiscipline;
    }

    public EmpPersonAchievementService getEmpPersonAchievementService() {
        return empPersonAchievementService;
    }

    public void setEmpPersonAchievementService(EmpPersonAchievementService empPersonAchievementService) {
        this.empPersonAchievementService = empPersonAchievementService;
    }

    public List<EmpPersonAchievement> getListPersonAchievement() {
        return listPersonAchievement;
    }

    public void setListPersonAchievement(List<EmpPersonAchievement> listPersonAchievement) {
        this.listPersonAchievement = listPersonAchievement;
    }
    
    
}
