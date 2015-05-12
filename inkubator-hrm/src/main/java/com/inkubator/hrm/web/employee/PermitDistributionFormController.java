/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.PermitDistributionService;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Taufik
 */
@ManagedBean(name = "permitDistributionFormController")
@ViewScoped
public class PermitDistributionFormController extends BaseController {

    @ManagedProperty(value = "#{permitClassificationService}")
    private PermitClassificationService permitService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{permitDistributionService}")
    private PermitDistributionService permitDistributionService;
    private PermitClassification selecedPermit;
    private Map<String, Long> permitSchemeDropDown = new HashMap<String, Long>();
    private List<PermitClassification> permitList = new ArrayList<>();
    private Map<String, Long> golonganJabatanDropDown = new TreeMap<String, Long>();
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
    private PermitDistributionModel model;
    private List<EmpData> source = new ArrayList<EmpData>();

    @PostConstruct
    @Override
    public void initialization() {

        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new PermitDistributionModel();
        try {
            permitList = permitService.getAllData();
            golonganJabatanList = golonganJabatanService.getAllWithDetail();
            for (PermitClassification permit : permitList) {
                permitSchemeDropDown.put(permit.getName(), permit.getId());
            }
            int i = 0;
            for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
                
                i++;
            }
            MapUtil.sortByValue(golonganJabatanDropDown);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PermitDistributionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        golonganJabatanService = null;
        permitSchemeDropDown = null;
        permitList = null;
        model = null;
        dualListModel = null;
        permitService = null;

    }


    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public Map<String, Long> getPermitSchemeDropDown() {
        return permitSchemeDropDown;
    }

    public void setPermitSchemeDropDown(Map<String, Long> permitSchemeDropDown) {
        this.permitSchemeDropDown = permitSchemeDropDown;
    }

    public PermitClassificationService getPermitService() {
        return permitService;
    }

    public void setPermitService(PermitClassificationService permitService) {
        this.permitService = permitService;
    }

    
    
    public List<PermitClassification> getPermitList() {
        return permitList;
    }

    public void setPermitList(List<PermitClassification> permitList) {
        this.permitList = permitList;
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

    public PermitDistributionModel getModel() {
        return model;
    }

    public void setModel(PermitDistributionModel model) {
        this.model = model;
    }

    public void doSearchEmployee() throws Exception {
        selecedPermit=permitService.getEntiyByPK(model.getPermitId());
        source = empDataService.getEmployeelBySearchEmployeePermit(model);
        dualListModel.setSource(source);
    }

    public String doSave() {

        try {
            List<EmpData> dataToSave = dualListModel.getTarget();
            permitDistributionService.saveMassPenempatanPermit(dataToSave, model.getPermitId(), model.getStartBalance());
//            empDataService.saveMassPenempatanJadwal(dataToSave, model.getWorkingGroupId());
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/employee/permit_distribution_view.htm?faces-redirect=true";
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doReset() {
        selecedPermit=new PermitClassification();
        model.setDepartmentLikeOrEqual(null);
        model.setPermitId(null);
        model.setDepartmentLikeOrEqual(3);
        model.setEmployeeTypeLikeOrEqual(3);
        model.setGolonganJabatanId(Long.parseLong("0"));
        dualListModel = new DualListModel<>();
    }

    public void setPermitDistributionService(PermitDistributionService permitDistributionService) {
        this.permitDistributionService = permitDistributionService;
    }

    public PermitClassification getSelecedPermit() {
        return selecedPermit;
    }

    public void setSelecedPermit(PermitClassification selecedPermit) {
        this.selecedPermit = selecedPermit;
    }
    
    public String doBack(){
        return "/protected/employee/permit_distribution_view.htm?faces-redirect=true";
    }

}
