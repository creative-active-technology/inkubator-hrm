/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtOverTime;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.OverTimeDistributionService;
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
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{overTimeDistributionService}")
    private OverTimeDistributionService overTimeDistributionService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private Map<String, Long> otListDown = new HashMap<>();
    private Map<String, Long> golonganJabatanDropDown = new TreeMap<>();
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
    private DistributionOvetTimeModel ovetTimeModel;
    private List<EmpData> source = new ArrayList<>();

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
        wtOverTimeService = null;
        listWtOverTime = null;
        empDataService = null;
        golonganJabatanService = null;
        otListDown = null;
        golonganJabatanDropDown = null;
        golonganJabatanList = null;
        dualListModel = null;
        ovetTimeModel = null;
        source = null;

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

    public void doSearchEmployee() {
        try {
            //        selecedLeave=leaveService.getEntiyByPK(model.getLeaveSchemeId());
            source = empDataService.getEmployeeByOtSearchParameter(ovetTimeModel);
            
            dualListModel.setSource(source);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public String doSave() {

        try {
            List<EmpData> dataToSave = dualListModel.getTarget();
            overTimeDistributionService.savePenempatanOt(dataToSave, ovetTimeModel.getOverTimeId());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/employee/ot_distribution_view.htm?faces-redirect=true";
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
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
        return "/protected/employee/ot_distribution_view.htm?faces-redirect=true";
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

    public void setOverTimeDistributionService(OverTimeDistributionService overTimeDistributionService) {
        this.overTimeDistributionService = overTimeDistributionService;
    }

}
