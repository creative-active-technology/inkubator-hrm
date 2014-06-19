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
    private Boolean isDisabledBreakConf;
    private CostCenter selectedCostCenter;
    private CostCenterModel costCenterModel;
    private Boolean isEdit;
    private String city; 
    private Map<String, Long> cities = new HashMap<String, Long>();
    private List<CostCenter> costCenterList = new ArrayList<>();

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

    public Map<String, Long> getCities() {
        return cities;
    }

    public void setCities(Map<String, Long> cities) {
        this.cities = cities;
    }

    
    public Boolean getIsDisabledBreakConf() {
        return isDisabledBreakConf;
    }

    public void setIsDisabledBreakConf(Boolean isDisabledBreakConf) {
        this.isDisabledBreakConf = isDisabledBreakConf;
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
        
        try {
            costCenterList = costCentreService.getAllData();
            cities = new HashMap<String, Long>();
                for (CostCenter costCenter : costCenterList) {
                    cities.put(costCenter.getName(), costCenter.getId());
                }  
            if (param != null) {

                isEdit = Boolean.TRUE;
                CostCenter costCenter = costCentreService.getEntiyByPK(Long.parseLong(param));
                costCenterModel.setId(costCenter.getId());
                costCenterModel.setCode(costCenter.getCode());
                costCenterModel.setName(costCenter.getName());
                
                //cities


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
            //cleanAndExit();

//        cleanAndExit();
    }
    
    private CostCenter getEntityFromViewModel(CostCenterModel costCenterModel) {
        CostCenter costCenter = new CostCenter();
        if (costCenterModel.getId() != null) {
            costCenter.setId(costCenterModel.getId());
        }
        costCenter.setCode(costCenterModel.getCode());
        costCenter.setName(costCenterModel.getName());
        costCenter.setCostCenter(costCenterModel.getParent());
        costCenter.setLevel(costCenterModel.getLevel());
        return costCenter;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        costCenterModel = null;
        costCenterModel = null;
        selectedCostCenter = null;
        isEdit = null;

    }
    
    public void onChangeManageBreakTime() {
        isDisabledBreakConf = BooleanUtils.isNotTrue(costCenterModel.getIsManageParentId());
        if (isDisabledBreakConf) {
            costCenterModel.setParent(null);

        }
    }
}


