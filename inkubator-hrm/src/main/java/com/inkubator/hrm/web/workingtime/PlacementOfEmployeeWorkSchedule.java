/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "placementOfEmployeeWorkScheduleFormController")
@ViewScoped
public class PlacementOfEmployeeWorkSchedule extends BaseController{
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Map<String, Long> workingGroupDropDown = new HashMap<String, Long>();;
    private List<WtGroupWorking> workingGroupList = new ArrayList<>();
    private Map<String, Long> golonganJabatanDropDown = new HashMap<String, Long>();;
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
    private PlacementOfEmployeeWorkScheduleModel model;
    private List<EmpData> source = new ArrayList<EmpData>();
    @PostConstruct
    @Override
    public void initialization() {
     
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new PlacementOfEmployeeWorkScheduleModel();
        try {
            workingGroupList = wtGroupWorkingService.getAllData();
            golonganJabatanList = golonganJabatanService.getAllWithDetail();
            for (WtGroupWorking wtGroupWorking : workingGroupList) {
                workingGroupDropDown.put(wtGroupWorking.getName(), wtGroupWorking.getId());
            }
            for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                golonganJabatanDropDown.put(golonganJabatan.getCode()+ " - " +golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
            }
            source = this.empDataService.getAllDataWithRelation();
        
        } catch (Exception ex) {
            Logger.getLogger(PlacementOfEmployeeWorkSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    private void cleanAndExit() {
        wtGroupWorkingService = null;
        golonganJabatanService = null;
        workingGroupDropDown = null;
        empDataService = null;
        workingGroupList = null;
        model = null;
        dualListModel = null;

    }
    
    public WtGroupWorkingService getWtGroupWorkingService() {
        return wtGroupWorkingService;
    }

    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public Map<String, Long> getWorkingGroupDropDown() {
        return workingGroupDropDown;
    }

    public void setWorkingGroupDropDown(Map<String, Long> workingGroupDropDown) {
        this.workingGroupDropDown = workingGroupDropDown;
    }

    public List<WtGroupWorking> getWorkingGroupList() {
        return workingGroupList;
    }

    public void setWorkingGroupList(List<WtGroupWorking> workingGroupList) {
        this.workingGroupList = workingGroupList;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public Map<String, Long> getGolonganJabatanDropDown() {
        return golonganJabatanDropDown;
    }

    public void setGolonganJabatanDropDown(Map<String, Long> golonganJabatanDropDown) {
        this.golonganJabatanDropDown = golonganJabatanDropDown;
    }

    public List<GolonganJabatan> getGolonganJabatanList() {
        return golonganJabatanList;
    }

    public void setGolonganJabatanList(List<GolonganJabatan> golonganJabatanList) {
        this.golonganJabatanList = golonganJabatanList;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public DualListModel<EmpData> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EmpData> dualListModel) {
        this.dualListModel = dualListModel;
    }
    
    public void doSearchEmployee() throws Exception{
        source = empDataService.getTotalBySearchEmployee(model.getWorkingGroupId(), model.getDepartmentLikeOrEqual(), model.getDepartmentName(), model.getEmployeeTypeLikeOrEqual(), model.getEmployeeTypeName(), model.getGender(), model.getGolonganJabatanId(), model.getSortBy(), model.getOrderBy());
        dualListModel.setSource(source);
    }

    public PlacementOfEmployeeWorkScheduleModel getModel() {
        return model;
    }

    public void setModel(PlacementOfEmployeeWorkScheduleModel model) {
        this.model = model;
    }

    public List<EmpData> getSource() {
        return source;
    }

    public void setSource(List<EmpData> source) {
        this.source = source;
    }
    
    
}
