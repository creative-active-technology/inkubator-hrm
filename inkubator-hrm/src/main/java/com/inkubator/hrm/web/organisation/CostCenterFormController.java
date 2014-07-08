/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.CostCenterModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
    private CostCenterModel costCenterModel;
    private Boolean isEdit;
    private Map<String, Long> costCenterParent;
    private List<CostCenter> costCenterList = new ArrayList<>();

    

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
        costCenterParent = new HashMap<String, Long>();
        MapUtil.sortByValue(costCenterParent);
        try { 
            if (param != null) {

                isEdit = Boolean.TRUE;
                CostCenter costCenter = costCentreService.getEntiyByPK(Long.parseLong(param));
                //list cost center tidak menampilkan dirinya sendiri
                costCenterList = costCentreService.getAllDataWhichIsNotItself(costCenter.getId());
                costCenterModel.setId(costCenter.getId());
                costCenterModel.setCode(costCenter.getCode());
                costCenterModel.setName(costCenter.getName());
                costCenterModel.setDescription(costCenter.getDescription());
                costCenterModel.setBalance(costCenter.getBalance());
                if(costCenter.getCostCenter() != null){
                    costCenterModel.setParentId(costCenter.getCostCenter().getId());
                }
                //jika cost center null / levelnya paling atas, maka dropdown disable
                if(costCenter.getCostCenter() == null){
                    isParentDisabled = Boolean.TRUE;
                }else{
                    isParentDisabled = Boolean.FALSE;
                }
                for (CostCenter costCenterListUpdate : costCenterList) {
                    costCenterParent.put(costCenterListUpdate.getName(), costCenterListUpdate.getId());               
                }
            } else {
                costCenterList = costCentreService.getAllData();
                isEdit = Boolean.FALSE;
                isParentDisabled = Boolean.FALSE;
                for (CostCenter costCenter : costCenterList) {
                        costCenterParent.put(costCenter.getName(), costCenter.getId());
                } 
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
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
        costCenter.setDescription(costCenterModel.getDescription());
        costCenter.setBalance(costCenterModel.getBalance());
        
        return costCenter;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        costCenterParent = null;
        costCenterModel = null;
        costCentreService = null;
        isEdit = null;
        isParentDisabled = null;

    }
}


