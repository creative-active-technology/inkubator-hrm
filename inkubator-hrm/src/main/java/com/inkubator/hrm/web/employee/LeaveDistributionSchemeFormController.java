/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.service.LeaveService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
@ManagedBean(name = "leaveDistributionSchemeFormController")
@ViewScoped
public class LeaveDistributionSchemeFormController extends BaseController {

    @ManagedProperty(value = "#{leaveSchemeService}")
    private LeaveSchemeService leaveSchemeService;
    @ManagedProperty(value = "#{leaveService}")
    private LeaveService leaveService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;

    private Map<String, Long> leaveSchemeDropDown = new HashMap<String, Long>();
    private List<Leave> leaveList = new ArrayList<>();
    private Map<String, Long> golonganJabatanDropDown = new HashMap<String, Long>();
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
    private DistributionLeaveSchemeModel model;
    private List<EmpData> source = new ArrayList<EmpData>();

    @PostConstruct
    @Override
    public void initialization() {

        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new DistributionLeaveSchemeModel();
        try {
            leaveList = leaveService.getAllData();
            golonganJabatanList = golonganJabatanService.getAllWithDetail();
            for (Leave leave : leaveList) {
                leaveSchemeDropDown.put(leave.getName(), leave.getId());
            }
            int i = 0;
            for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
                System.out.println(golonganJabatanList.get(i).getCode());
                i++;
            }
            MapUtil.sortByValue(golonganJabatanDropDown);
            source = this.empDataService.getAllDataWithRelation();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LeaveDistributionSchemeFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        leaveSchemeService = null;
        golonganJabatanService = null;
        leaveSchemeDropDown = null;
        leaveList = null;
        model = null;
        dualListModel = null;
        leaveService = null;

    }

    public LeaveSchemeService getLeaveSchemeService() {
        return leaveSchemeService;
    }

    public void setLeaveSchemeService(LeaveSchemeService leaveSchemeService) {
        this.leaveSchemeService = leaveSchemeService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public Map<String, Long> getLeaveSchemeDropDown() {
        return leaveSchemeDropDown;
    }

    public void setLeaveSchemeDropDown(Map<String, Long> leaveSchemeDropDown) {
        this.leaveSchemeDropDown = leaveSchemeDropDown;
    }

    public LeaveService getLeaveService() {
        return leaveService;
    }

    public void setLeaveService(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public List<Leave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(List<Leave> leaveList) {
        this.leaveList = leaveList;
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

    public List<EmpData> getSource() {
        return source;
    }

    public void setSource(List<EmpData> source) {
        this.source = source;
    }

    public DistributionLeaveSchemeModel getModel() {
        return model;
    }

    public void setModel(DistributionLeaveSchemeModel model) {
        this.model = model;
    }

    public void doSearchEmployee() throws Exception {
        source = empDataService.getTotalBySearchEmployeeLeave(model);
        dualListModel.setSource(source);
    }

    public String doSave() {
        try {
            List<EmpData> dataToSave = dualListModel.getTarget();
//            empDataService.saveMassPenempatanJadwal(dataToSave, model.getWorkingGroupId());
//            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//            return "/protected/employee/employee_schedule_view.htm?faces-redirect=true";

        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doReset() {
        model.setDepartmentLikeOrEqual(null);
        model.setLeaveSchemeId(null);
        model.setDepartmentLikeOrEqual(3);
        model.setEmployeeTypeLikeOrEqual(3);
        model.setGolonganJabatanId(Long.parseLong("0"));
        dualListModel = new DualListModel<>();
    }
}
