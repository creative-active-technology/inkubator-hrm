/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.TempJadwalKaryawanService;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
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
public class PlacementOfEmployeeWorkScheduleFormController extends BaseController {

    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{tempJadwalKaryawanService}")
    private TempJadwalKaryawanService tempJadwalKaryawanService;
    private Map<String, Long> workingGroupDropDown = new HashMap<String, Long>();
    
    private List<WtGroupWorking> workingGroupList = new ArrayList<>();
    private Map<String, Long> golonganJabatanDropDown = new TreeMap<String, Long>();
    
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
                golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
            }
            source = this.empDataService.getAllDataWithRelation();
            MapUtil.sortByValue(workingGroupDropDown);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
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
        tempJadwalKaryawanService = null;
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

    public void doSearchEmployee() throws Exception {
        source = empDataService.getTotalBySearchEmployee(model);
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

    public void setTempJadwalKaryawanService(TempJadwalKaryawanService tempJadwalKaryawanService) {
		this.tempJadwalKaryawanService = tempJadwalKaryawanService;
	}

	public String doSave() {

        try {
            List<EmpData> dataToSave = dualListModel.getTarget();
            tempJadwalKaryawanService.saveMassPenempatanJadwal(dataToSave, model.getWorkingGroupId());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/employee/employee_schedule_view.htm?faces-redirect=true";

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doReset() {
        model.setWorkingGroupId(null);
        model.setDepartmentLikeOrEqual(null);
        model.setEmployeeTypeLikeOrEqual(null);
        model.setGolonganJabatanId(Long.parseLong("0"));
        model.setGender(null);
        dualListModel = new DualListModel<>();
    }

    public String doBack() {
        return "/protected/employee/employee_schedule_view.htm?faces-redirect=true";
    }

}
