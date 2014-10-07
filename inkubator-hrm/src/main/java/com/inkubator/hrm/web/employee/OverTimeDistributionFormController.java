/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.WtOverTimeService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
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
@ManagedBean(name = "overTimeDistributionFormController")
@ViewScoped
public class OverTimeDistributionFormController extends BaseController {

    @ManagedProperty(value = "#{wtOverTimeService}")
    private WtOverTimeService wtOverTimeService;
    private List<WtOverTime> listWtOverTime = new ArrayList<>();
//    @ManagedProperty(value = "#{leaveSchemeService}")
//    private LeaveSchemeService leaveSchemeService;
//    @ManagedProperty(value = "#{leaveService}")
//    private LeaveService leaveService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private Map<String, Long> otListDown = new HashMap<String, Long>();
//    @ManagedProperty(value = "#{leaveDistributionService}")
//    private LeaveDistributionService leaveDistributionService;
//    private Leave selecedLeave;
//    private Map<String, Long> leaveSchemeDropDown = new HashMap<String, Long>();
//    private List<Leave> leaveList = new ArrayList<>();
    private Map<String, Long> golonganJabatanDropDown = new TreeMap<String, Long>();
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
//    private DistributionLeaveSchemeModel model;
    private DistributionOvetTimeModel ovetTimeModel;
    private List<EmpData> source = new ArrayList<EmpData>();

    @PostConstruct
    @Override
    public void initialization() {

        super.initialization();
//        String param = FacesUtil.getRequestParameter("param");
        ovetTimeModel = new DistributionOvetTimeModel();
        try {
            listWtOverTime = wtOverTimeService.getAllData();
            for (WtOverTime wtOverTime : listWtOverTime) {
                otListDown.put(wtOverTime.getName(), wtOverTime.getId());
            }
            golonganJabatanList = golonganJabatanService.getAllWithDetail();
//            for (Leave leave : leaveList) {
//                leaveSchemeDropDown.put(leave.getName(), leave.getId());
//            }
            int i = 0;
            for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
                System.out.println(golonganJabatanList.get(i).getCode());
                i++;
            }
            MapUtil.sortByValue(golonganJabatanDropDown);
//            source = this.empDataService.getAllDataWithRelation();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
//        leaveSchemeService = null;
        golonganJabatanService = null;
//        leaveSchemeDropDown = null;
//        leaveList = null;
        ovetTimeModel = null;
        dualListModel = null;
//        leaveService = null;

    }

//    public LeaveSchemeService getLeaveSchemeService() {
//        return leaveSchemeService;
//    }
//
//    public void setLeaveSchemeService(LeaveSchemeService leaveSchemeService) {
//        this.leaveSchemeService = leaveSchemeService;
//    }
    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

//    public Map<String, Long> getLeaveSchemeDropDown() {
//        return leaveSchemeDropDown;
//    }
//
//    public void setLeaveSchemeDropDown(Map<String, Long> leaveSchemeDropDown) {
//        this.leaveSchemeDropDown = leaveSchemeDropDown;
//    }
//
//    public LeaveService getLeaveService() {
//        return leaveService;
//    }
//
//    public void setLeaveService(LeaveService leaveService) {
//        this.leaveService = leaveService;
//    }
//    public List<Leave> getLeaveList() {
//        return leaveList;
//    }
//
//    public void setLeaveList(List<Leave> leaveList) {
//        this.leaveList = leaveList;
//    }
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

    public DistributionOvetTimeModel getOvetTimeModel() {
        return ovetTimeModel;
    }

    public void setOvetTimeModel(DistributionOvetTimeModel ovetTimeModel) {
        this.ovetTimeModel = ovetTimeModel;
    }

    public void doSearchEmployee() throws Exception {
//        selecedLeave=leaveService.getEntiyByPK(model.getLeaveSchemeId());
        source = empDataService.getEmployeeByOtSearchParameter(ovetTimeModel);
        dualListModel.setSource(source);
    }

    public String doSave() {

//        try {
        List<EmpData> dataToSave = dualListModel.getTarget();
//            leaveDistributionService.saveMassPenempatanCuti(dataToSave, model.getLeaveSchemeId(), model.getStartBalance());
//            empDataService.saveMassPenempatanJadwal(dataToSave, model.getWorkingGroupId());
        MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        return "/protected/employee/leave_distribution_view.htmfaces-redirect=true";
//        } catch (BussinessException ex) { //data already exist(duplicate)
////            LOGGER.error("Error", ex);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
//        return null;
    }

    public void doReset() {
//        selecedLeave=new Leave();
        ovetTimeModel.setDepartmentLikeOrEqual(null);
        ovetTimeModel.setOverTimeId(null);
        ovetTimeModel.setDepartmentLikeOrEqual(3);
        ovetTimeModel.setEmployeeTypeLikeOrEqual(3);
        ovetTimeModel.setGolonganJabatanId(Long.parseLong("0"));
        dualListModel = new DualListModel<>();
    }

//    public void setLeaveDistributionService(LeaveDistributionService leaveDistributionService) {
////        this.leaveDistributionService = leaveDistributionService;
//    }
//
//    public Leave getSelecedLeave() {
//        return selecedLeave;
//    }
//
//    public void setSelecedLeave(Leave selecedLeave) {
//        this.selecedLeave = selecedLeave;
//    }
    public String doBack() {
        return "/protected/employee/leave_distribution_view.htmfaces-redirect=true";
    }

    public List<WtOverTime> getListWtOverTime() {
        return listWtOverTime;
    }

    public void setListWtOverTime(List<WtOverTime> listWtOverTime) {
        this.listWtOverTime = listWtOverTime;
    }

    public void setWtOverTimeService(WtOverTimeService wtOverTimeService) {
        this.wtOverTimeService = wtOverTimeService;
    }

    public Map<String, Long> getOtListDown() {
        return otListDown;
    }

    public void setOtListDown(Map<String, Long> otListDown) {
        this.otListDown = otListDown;
    }

}
