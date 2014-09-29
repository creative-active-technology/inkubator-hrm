/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempJadwalKaryawan;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;



@ManagedBean(name = "empDataDetilScheduleController")
@ViewScoped
public class EmpDataDetilScheduleController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    @ManagedProperty(value = "#{tempJadwalKaryawanService}")
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    private List<TempJadwalKaryawan>dataToShow=new ArrayList<>();
//    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
//    private List<JabatanDeskripsi> jabatanDeskripsis;
//    private List<EmpCareerHistory> listCareerHistory;
//    private String id;
//    @ManagedProperty(value = "#{empCareerHistoryService}")
//    private EmpCareerHistoryService empCareerHistoryService;

    //personal discipline
//    @ManagedProperty(value = "#{personalDisciplineService}")
//    private PersonalDisciplineService personalDisciplineService;
//    private List<PersonalDiscipline> listPersonalDiscipline;

    //Achievement
//    @ManagedProperty(value = "#{empPersonAchievementService}")
//    private EmpPersonAchievementService empPersonAchievementService;
//    private List<EmpPersonAchievement> listPersonAchievement;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String empId = FacesUtil.getRequestParameter("execution");
            selectedEmpData = empDataService.getByEmpIdWithDetail(Long.parseLong(empId.substring(1)));
            dataToShow=tempJadwalKaryawanService.getAllByEmpIdWithDetail(selectedEmpData.getId());
//            jabatanDeskripsis = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanDeskripsis());
//            listJabatanSpesifikasi = new ArrayList<>(selectedEmpData.getJabatanByJabatanId().getJabatanSpesifikasis());
//            listCareerHistory = empCareerHistoryService.getEmployeeCareerByBioId(selectedEmpData.getBioData().getId());
//            listPersonalDiscipline = personalDisciplineService.getAllDataByEmployeeId(selectedEmpData.getId());
//            listPersonAchievement = empPersonAchievementService.getAllDataByEmployeeId(selectedEmpData.getId());

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

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
        return "/protected/employee/employee_palcement_form.htm?faces-redirect=true&execution=e" + selectedEmpData.getId();
    }

    public String doBack() {
        return "/protected/employee/employee_palcement_view.htm?faces-redirect=true";
    }

     public String doBackSchedule() {
        return "/protected/employee/employee_schedule_view.htm?faces-redirect=true";
    }
//    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
//        return listJabatanSpesifikasi;
//    }
//
//    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
//        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
//    }
//
//    public List<JabatanDeskripsi> getJabatanDeskripsis() {
//        return jabatanDeskripsis;
//    }
//
//    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
//        this.jabatanDeskripsis = jabatanDeskripsis;
//    }

    public void doSelectEmpCardName() {
        try {
            selectedEmpData = empDataService.getByEmpIdWithDetail(selectedEmpData.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    

//    public void setEmpCareerHistoryService(EmpCareerHistoryService empCareerHistoryService) {
//        this.empCareerHistoryService = empCareerHistoryService;
//    }
//
//    public List<EmpCareerHistory> getListCareerHistory() {
//        return listCareerHistory;
//    }
//
//    public void setListCareerHistory(List<EmpCareerHistory> listCareerHistory) {
//        this.listCareerHistory = listCareerHistory;
//    }
//
//    
//    public PersonalDisciplineService getPersonalDisciplineService() {
//        return personalDisciplineService;
//    }
//
//    public void setPersonalDisciplineService(PersonalDisciplineService personalDisciplineService) {
//        this.personalDisciplineService = personalDisciplineService;
//    }
//
//    public List<PersonalDiscipline> getListPersonalDiscipline() {
//        return listPersonalDiscipline;
//    }
//
//    public void setListPersonalDiscipline(List<PersonalDiscipline> listPersonalDiscipline) {
//        this.listPersonalDiscipline = listPersonalDiscipline;
//    }
//
//    public EmpPersonAchievementService getEmpPersonAchievementService() {
//        return empPersonAchievementService;
//    }
//
//    public void setEmpPersonAchievementService(EmpPersonAchievementService empPersonAchievementService) {
//        this.empPersonAchievementService = empPersonAchievementService;
//    }
//
//    public List<EmpPersonAchievement> getListPersonAchievement() {
//        return listPersonAchievement;
//    }
//
//    public void setListPersonAchievement(List<EmpPersonAchievement> listPersonAchievement) {
//        this.listPersonAchievement = listPersonAchievement;
//    }

     @PreDestroy
    public void cleanAndExit() {
        empDataService=null;
        selectedEmpData=null;
        tempJadwalKaryawanService=null;
        dataToShow=null;
        
//        listJabatanSpesifikasi=null;
//        jabatanDeskripsis=null;
//        listCareerHistory=null;
//        id=null;
//        empCareerHistoryService=null;
//        personalDisciplineService=null;
//        listPersonalDiscipline=null;
//        empPersonAchievementService=null;
//        listPersonAchievement=null;
        
    }

    public void setTempJadwalKaryawanService(TempJadwalKaryawanService tempJadwalKaryawanService) {
        this.tempJadwalKaryawanService = tempJadwalKaryawanService;
    }

    public List<TempJadwalKaryawan> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<TempJadwalKaryawan> dataToShow) {
        this.dataToShow = dataToShow;
    }
    

}
