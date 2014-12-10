package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PaySalaryJurnalModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "paySalaryJurnalFormController")
@ViewScoped
public class PaySalaryJurnalFormController extends BaseController {

    private PaySalaryJurnalModel paySalaryJurnalModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{paySalaryJurnalService}")
    private PaySalaryJurnalService paySalaryJurnalService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    private Map<String, Long> coas = new TreeMap<>();

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String param = FacesUtil.getRequestParameter("param");
            paySalaryJurnalModel = new PaySalaryJurnalModel();
            isUpdate = Boolean.FALSE;
            
            List<CostCenter> listCost = costCenterService.getAllData();
            
            for (CostCenter costCenter : listCost) {
                coas.put(costCenter.getName(), costCenter.getId());
            }
            
            MapUtil.sortByValue(coas);
            
            if (StringUtils.isNumeric(param)) {
                try {
                    PaySalaryJurnal paySalaryJurnal = paySalaryJurnalService.getEntityByPKWithDetail(Long.parseLong(param));
                    if (paySalaryJurnal != null) {
                        paySalaryJurnalModel.setId(paySalaryJurnal.getId());
                        paySalaryJurnalModel.setCode(paySalaryJurnal.getCode());
                        paySalaryJurnalModel.setName(paySalaryJurnal.getName());
                        paySalaryJurnalModel.setCoa(paySalaryJurnal.getCostCenter().getId());
                        paySalaryJurnalModel.setTypeJurnal(paySalaryJurnal.getTypeJurnal());
                        paySalaryJurnalModel.setModelJurnal(paySalaryJurnal.getModelJurnal());
                        paySalaryJurnalModel.setDescription(paySalaryJurnal.getDescription());
                        isUpdate = Boolean.TRUE;
                    }
                } catch (Exception e) {
                    LOGGER.error("Error", e);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PaySalaryJurnalFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        paySalaryJurnalService = null;
//        paySalaryJurnalModel = null;
        costCenterService = null;
        isUpdate = null;
        coas = null;
    }

    public PaySalaryJurnalModel getPaySalaryJurnalModel() {
        return paySalaryJurnalModel;
    }

    public void setPaySalaryJurnalModel(PaySalaryJurnalModel paySalaryJurnalModel) {
        this.paySalaryJurnalModel = paySalaryJurnalModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setPaySalaryJurnalService(PaySalaryJurnalService paySalaryJurnalService) {
        this.paySalaryJurnalService = paySalaryJurnalService;
    }


    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

    public Map<String, Long> getCoas() {
        return coas;
    }

    public void setCoas(Map<String, Long> coas) {
        this.coas = coas;
    }
    
    

    public void doSave() {
        PaySalaryJurnal paySalaryJurnal = getEntityFromViewModel(paySalaryJurnalModel);
        try {
            if (isUpdate) {
                paySalaryJurnalService.update(paySalaryJurnal);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                paySalaryJurnalService.save(paySalaryJurnal);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private PaySalaryJurnal getEntityFromViewModel(PaySalaryJurnalModel paySalaryJurnalModel) {
        PaySalaryJurnal paySalaryJurnal = new PaySalaryJurnal();
        if (paySalaryJurnalModel.getId() != null) {
            paySalaryJurnal.setId(paySalaryJurnalModel.getId());
        }
        paySalaryJurnal.setCode(paySalaryJurnalModel.getCode());
        paySalaryJurnal.setName(paySalaryJurnalModel.getName());
        paySalaryJurnal.setCostCenter(new CostCenter(paySalaryJurnalModel.getCoa()));
        paySalaryJurnal.setTypeJurnal(paySalaryJurnalModel.getTypeJurnal());
        paySalaryJurnal.setModelJurnal(paySalaryJurnalModel.getModelJurnal());
        paySalaryJurnal.setDescription(paySalaryJurnalModel.getDescription());
        return paySalaryJurnal;
    }
}
