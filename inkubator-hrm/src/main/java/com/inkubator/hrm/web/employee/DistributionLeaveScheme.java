/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.LeaveSchemeService;
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
import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "distributionLeaveScheme")
@ViewScoped
public class DistributionLeaveScheme extends BaseController{
    @ManagedProperty(value = "#{leaveSchemeService}")
    private LeaveSchemeService leaveSchemeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    private Map<String, Long> leaveSchemeDropDown = new HashMap<String, Long>();
    private List<LeaveScheme> leaveSchemeList = new ArrayList<>();
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
            leaveSchemeList = leaveSchemeService.getAllData();
            golonganJabatanList = golonganJabatanService.getAllWithDetail();
            for (LeaveScheme leaveSchema : leaveSchemeList) {
                leaveSchemeDropDown.put(leaveSchema.getName(), leaveSchema.getId());
            }
            for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
            }
            source = this.empDataService.getAllDataWithRelation();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DistributionLeaveScheme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    private void cleanAndExit() {
        leaveSchemeService = null;
        golonganJabatanService = null;
        leaveSchemeDropDown = null;
        empDataService = null;
        leaveSchemeList = null;
        model = null;
        dualListModel = null;

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

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Map<String, Long> getLeaveSchemeDropDown() {
        return leaveSchemeDropDown;
    }

    public void setLeaveSchemeDropDown(Map<String, Long> leaveSchemeDropDown) {
        this.leaveSchemeDropDown = leaveSchemeDropDown;
    }

    public List<LeaveScheme> getLeaveSchemeList() {
        return leaveSchemeList;
    }

    public void setLeaveSchemeList(List<LeaveScheme> leaveSchemeList) {
        this.leaveSchemeList = leaveSchemeList;
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

    public DualListModel<EmpData> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EmpData> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public DistributionLeaveSchemeModel getModel() {
        return model;
    }

    public void setModel(DistributionLeaveSchemeModel model) {
        this.model = model;
    }

    public List<EmpData> getSource() {
        return source;
    }

    public void setSource(List<EmpData> source) {
        this.source = source;
    }
    
    public void doSearchEmployee() throws Exception {
        source = empDataService.getTotalBySearchEmployeeLeave(model);
        dualListModel.setSource(source);
    }
}
