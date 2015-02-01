/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.WtFingerException;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.WtFingerExceptionService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "wtFingerExceptionFormController")
@ViewScoped
public class WtFingerExceptionFormController extends BaseController{
    @ManagedProperty(value = "#{wtFingerExceptionService}")
    private WtFingerExceptionService wtFingerExceptionService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    private WtFingerExceptionModel model;
    private Map<String, Long> golonganJabatanDropDown = new TreeMap<String, Long>();
    private List<GolonganJabatan> golonganJabatanList = new ArrayList<>();
    private DualListModel<EmpData> dualListModel = new DualListModel<>();
    private List<EmpData> source = new ArrayList<EmpData>();
    
    @Override
    public void initialization() {

        super.initialization();
        String wtFingerExceptionId = FacesUtil.getRequestParameter("wtFingerExceptionId");
        model = new WtFingerExceptionModel();
        try {
            if(wtFingerExceptionId != null){
                WtFingerException wtFingerException = wtFingerExceptionService.getEntityByParamWithDetail(Long.parseLong(wtFingerExceptionId));
                model.setId(wtFingerException.getId());
                model.setEmpData(wtFingerException.getEmpData().getNik() + " - " + wtFingerException.getEmpData().getBioData().getFirstName() + " " + wtFingerException.getEmpData().getBioData().getLastName());
                model.setStartDate(wtFingerException.getStartDate());
                model.setEndDate(wtFingerException.getEndDate());
                model.setExtendException(wtFingerException.getExtendException());
            }else{
                int i = 0;
                golonganJabatanList = golonganJabatanService.getAllWithDetail();
                for (GolonganJabatan golonganJabatan : golonganJabatanList) {
                    golonganJabatanDropDown.put(golonganJabatan.getCode() + " - " + golonganJabatan.getPangkat().getPangkatName(), golonganJabatan.getId());
                    
                    i++;
                }
                MapUtil.sortByValue(golonganJabatanDropDown);
            }
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(WtFingerExceptionFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        dualListModel = null;
        source = null;
        golonganJabatanService = null;
        golonganJabatanDropDown = null;
        golonganJabatanList = null;
        wtFingerExceptionService = null;
        empDataService = null;
        model = null;
    }

    public void doSearchEmployee() throws Exception {
        source = empDataService.getEmployeeBySearchEmployeeFingerException(model);
        dualListModel.setSource(source);
    }

    public String doSave() {
        try {
                List<EmpData> dataToSave = dualListModel.getTarget();
                wtFingerExceptionService.saveMassFingerException(dataToSave, model.getStartDate(), model.getEndDate(), model.getExtendException());
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/working_time/wt_finger_ex_view.htm?faces-redirect=true";

        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    public void doSaveUpdate(){
        WtFingerException wtFingerException = getEntityFromViewModel(model);
        try {
                wtFingerExceptionService.update(wtFingerException);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private WtFingerException getEntityFromViewModel(WtFingerExceptionModel model) {
        WtFingerException wtFingerException = new WtFingerException();
        if (model.getId() != null) {
            wtFingerException.setId(model.getId());
        }
        wtFingerException.setStartDate(model.getStartDate());
        wtFingerException.setEndDate(model.getEndDate());
        wtFingerException.setExtendException(model.getExtendException());
        return wtFingerException;
    }
    
    public void doReset() {
        model.setDepartmentLikeOrEqual(null);
        model.setDepartmentLikeOrEqual(3);
        model.setEmployeeTypeLikeOrEqual(3);
        model.setGolonganJabatanId(Long.parseLong("0"));
        dualListModel = new DualListModel<>();
    }
    
    public void doResetEditForm(){
        model.setStartDate(null);
        model.setEndDate(null);
        model.setExtendException(Boolean.FALSE);
    }
    
    public String doBack(){
        return "/protected/working_time/wt_finger_ex_view.htm?faces-redirect=true";
    }
     
    public WtFingerExceptionService getWtFingerExceptionService() {
        return wtFingerExceptionService;
    }

    public void setWtFingerExceptionService(WtFingerExceptionService wtFingerExceptionService) {
        this.wtFingerExceptionService = wtFingerExceptionService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public WtFingerExceptionModel getModel() {
        return model;
    }

    public void setModel(WtFingerExceptionModel model) {
        this.model = model;
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
    
    
}
