/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empTimeScheduleFormController")
@ViewScoped
public class EmpTimeScheduleFormController extends BaseController {

    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private EmpData selectedEmpData;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    private List<WtGroupWorking> dataToShow = new ArrayList<>();
    private Map<String, Long> workingTime = new HashMap<>();
    private long groupWorkingId;

    @PostConstruct
    @Override
    public void initialization() {
        try {

            super.initialization();
            String empId = FacesUtil.getRequestParameter("empId");
            selectedEmpData = empDataService.getEntiyByPK(Long.parseLong(empId));
            dataToShow = wtGroupWorkingService.getAllData();
            System.out.println(dataToShow.size());
            for (WtGroupWorking dataToShow1 : dataToShow) {
                workingTime.put(dataToShow1.getCode() + " - " + dataToShow1.getName(), dataToShow1.getId());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }

    }

    @PreDestroy
    public void cleanAndExit() {

    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public EmpData getSelectedEmpData() {
        return selectedEmpData;
    }

    public void setSelectedEmpData(EmpData selectedEmpData) {
        this.selectedEmpData = selectedEmpData;
    }

  
    public List<WtGroupWorking> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<WtGroupWorking> dataToShow) {
        this.dataToShow = dataToShow;
    }

    public long getGroupWorkingId() {
        return groupWorkingId;
    }

    public void setGroupWorkingId(long groupWorkingId) {
        this.groupWorkingId = groupWorkingId;
    }

    public Map<String, Long> getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Map<String, Long> workingTime) {
        this.workingTime = workingTime;
    }

    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    
}
