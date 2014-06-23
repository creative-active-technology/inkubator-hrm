/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.web.model.CostCenterModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "costCenterFormController")
@ViewScoped
public class CostCenterFormController extends BaseController{
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCentreService;
    private Boolean isParentDisabled;
    private CostCenter selectedCostCenter;
    private CostCenterModel costCenterModel;
    private Boolean isEdit;
    private String city; 
    private Map<String, Long> costCenterParent;
    private List<CostCenter> costCenterList = new ArrayList<>();
    private Integer parentLevel;

    public Integer getBranchLevel() {
        return parentLevel;
    }

    public void setBranchLevel(Integer parentLevel) {
        this.parentLevel = parentLevel;
    }

    public Map<String, Long> getCostCenterParent() {
        return costCenterParent;
    }

    public void setCostCenterParent(Map<String, Long> costCenterParent) {
        this.costCenterParent = costCenterParent;
    }

    public List<CostCenter> getCostCenterList() {
        return costCenterList;
    }

    public void setCostCenterList(List<CostCenter> costCenterList) {
        this.costCenterList = costCenterList;
    }
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, Long> getCostCenter() {
        return costCenterParent;
    }

    public void setCostCenter(Map<String, Long> costCenterParent) {
        this.costCenterParent = costCenterParent;
    }

    public Boolean getIsParentDisabled() {
        return isParentDisabled;
    }

    public void setIsParentDisabled(Boolean isParentDisabled) {
        this.isParentDisabled = isParentDisabled;
    }

    public CostCenterService getCostCentreService() {
        return costCentreService;
    }

    public void setCostCentreService(CostCenterService costCentreService) {
        this.costCentreService = costCentreService;
    }

    public CostCenter getSelectedCostCenter() {
        return selectedCostCenter;
    }

    public void setSelectedCostCenter(CostCenter selectedCostCenter) {
        this.selectedCostCenter = selectedCostCenter;
    }

    public CostCenterModel getCostCenterModel() {
        return costCenterModel;
    }

    public void setCostCenterModel(CostCenterModel costCenterModel) {
        this.costCenterModel = costCenterModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        costCenterModel = new CostCenterModel();
        isParentDisabled=Boolean.TRUE;
        try {
            costCenterList = costCentreService.getAllData();
        } catch (Exception ex) {
            Logger.getLogger(CostCenterFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            if (param != null) {

                isEdit = Boolean.TRUE;
                isParentDisabled = Boolean.FALSE;
                CostCenter costCenter = costCentreService.getEntiyByPK(Long.parseLong(param));
                costCenterModel.setId(costCenter.getId());
                costCenterModel.setCode(costCenter.getCode());
                costCenterModel.setName(costCenter.getName());
                costCenterModel.setLevel(costCenter.getLevel());
                costCenterModel.setParentId(Long.valueOf(String.valueOf(costCenter.getCostCenter().getId())));
                //get level
                Integer level = costCenterModel.getLevel();
                costCenterParent = new HashMap<String, Long>();

                //kalo level 3, tampilkan level 2, dst
                for (CostCenter costCenterListUpdate : costCenterList) {
                    if((level-1) == costCenterListUpdate.getLevel()){
                        costCenterParent.put(costCenterListUpdate.getName(), costCenterListUpdate.getId());
                    }
                }
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        CostCenter costCenter = getEntityFromViewModel(costCenterModel);
        try {
            if (isEdit) {
                costCentreService.update(costCenter);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                costCentreService.save(costCenter);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private CostCenter getEntityFromViewModel(CostCenterModel costCenterModel) {
        CostCenter costCenter = new CostCenter();
        if (costCenterModel.getId() != null) {
            costCenter.setId(costCenterModel.getId());
        }
        if(costCenterModel.getParentId() == null){
            costCenter.setCostCenter(new CostCenter());
        }else{
            costCenter.setCostCenter(new CostCenter(costCenterModel.getParentId()));
        }
        costCenter.setCode(costCenterModel.getCode());
        costCenter.setName(costCenterModel.getName());
        costCenter.setLevel(costCenterModel.getLevel());
        return costCenter;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        costCenterParent = null;
        costCenterModel = null;
        costCentreService = null;
        selectedCostCenter = null;
        isEdit = null;
        isParentDisabled = null;
        parentLevel = null;

    }
    
    public void onChangeManageBreakTime() throws Exception {
        //kalo get level = 1 disable parent idnya
        isParentDisabled = costCenterModel.getLevel() <= 1 ? true : false;
        System.out.println(isParentDisabled + " = " + costCenterModel.getLevel());
        //get level
        Integer level = costCenterModel.getLevel();
        costCenterParent = new HashMap<String, Long>();
        
        //kalo level 3, tampilkan level 2, dst
        for (CostCenter costCenter : costCenterList) {
            if((level-1) == costCenter.getLevel()){
                costCenterParent.put(costCenter.getName(), costCenter.getId());
            }
        } 
    }
}


