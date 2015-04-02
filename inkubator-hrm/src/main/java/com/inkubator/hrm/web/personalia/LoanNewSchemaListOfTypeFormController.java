/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewSchemaListOfType;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.service.LoanNewSchemaListOfTypeService;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @author Deni
 */
@ManagedBean(name = "loanNewSchemaListOfTypeFormController")
@ViewScoped
public class LoanNewSchemaListOfTypeFormController extends BaseController {
    
    private LoanNewSchemaListOfTypeModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{loanNewSchemaListOfTypeService}")
    private LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService;
    @ManagedProperty(value = "#{loanNewTypeService}")
    private LoanNewTypeService loanNewTypeService;
    private Map<String, Long> dropDownSchemeType;
    private List<LoanNewType> listLoanNewType;
    private String loanNewSchemaId;
    private String id;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new LoanNewSchemaListOfTypeModel();
            loanNewSchemaId = FacesUtil.getRequestParameter("loanNewSchemaId");
            id = FacesUtil.getRequestParameter("id");
            isUpdate = Boolean.FALSE;
            dropDownSchemeType = new HashMap<String, Long>();
            if (StringUtils.isNotEmpty(id)) {
                LoanNewSchemaListOfType loanNewSchemaListOfType = loanNewSchemaListOfTypeService.getEntityByLoanNewSchemaListOfTypeIdWithDetail(Long.valueOf(id));
                if(loanNewSchemaListOfType != null){
                    isUpdate = Boolean.TRUE;
                    model = getModelFromEntity(loanNewSchemaListOfType);
                }
            }else{
                    model.setIsActive(Boolean.TRUE);
                }
            
            listLoanNewType = loanNewTypeService.getAllData();
            for (LoanNewType loanNewType : listLoanNewType) {
                dropDownSchemeType.put(loanNewType.getLoanTypeName(), loanNewType.getId());
            }
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        loanNewTypeService = null;
        id = null;
        dropDownSchemeType = null;
        listLoanNewType = null;
        model = null;
        loanNewSchemaId = null;
        isUpdate = null;
        loanNewSchemaListOfTypeService = null;
    }

    private LoanNewSchemaListOfTypeModel getModelFromEntity(LoanNewSchemaListOfType entity) {
        LoanNewSchemaListOfTypeModel model = new LoanNewSchemaListOfTypeModel();
        model.setId(entity.getId());
        model.setLoanNewTypeId(entity.getLoanNewType().getId());
        model.setMaksimumHariTersedia(entity.getMaksimumHariTersedia());
        model.setMaxPeriode(entity.getMaxPeriode());
        model.setMaximumAllocation(entity.getMaximumAllocation());
        model.setMaximumApproval(entity.getMaximumApproval());
        model.setMinimumAllocation(entity.getMinimumAllocation());
        model.setMinimumApproval(entity.getMinimumApproval());
        model.setMinimumMonthlyInstallment(entity.getMinimumMonthlyInstallment());
        model.setIsActive(entity.getIsActive());
        return model;
    }
    
    public void doSave(){
        LoanNewSchemaListOfType loanNewSchemaListOfType = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                loanNewSchemaListOfTypeService.update(loanNewSchemaListOfType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanNewSchemaListOfTypeService.save(loanNewSchemaListOfType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private LoanNewSchemaListOfType getEntityFromViewModel(LoanNewSchemaListOfTypeModel model) {
        LoanNewSchemaListOfType loanNewSchemaListOfType = new LoanNewSchemaListOfType();
        if (model.getId() != null) {
            loanNewSchemaListOfType.setId(model.getId());
        }
        loanNewSchemaListOfType.setLoanNewSchema(new LoanNewSchema(Long.valueOf(loanNewSchemaId)));
        loanNewSchemaListOfType.setLoanNewType(new LoanNewType(model.getLoanNewTypeId()));
        loanNewSchemaListOfType.setMaksimumHariTersedia(model.getMaksimumHariTersedia());
        loanNewSchemaListOfType.setMaxPeriode(model.getMaxPeriode());
        loanNewSchemaListOfType.setMaximumAllocation(model.getMaximumAllocation());
        loanNewSchemaListOfType.setMaximumApproval(model.getMaximumApproval());
        loanNewSchemaListOfType.setMinimumAllocation(model.getMinimumAllocation());
        loanNewSchemaListOfType.setMinimumApproval(model.getMinimumApproval());
        loanNewSchemaListOfType.setMinimumMonthlyInstallment(model.getMinimumMonthlyInstallment());
        loanNewSchemaListOfType.setIsActive(model.getIsActive());
        return loanNewSchemaListOfType;
    }
    
    public LoanNewSchemaListOfTypeModel getModel() {
        return model;
    }

    public void setModel(LoanNewSchemaListOfTypeModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public LoanNewSchemaListOfTypeService getLoanNewSchemaListOfTypeService() {
        return loanNewSchemaListOfTypeService;
    }

    public void setLoanNewSchemaListOfTypeService(LoanNewSchemaListOfTypeService loanNewSchemaListOfTypeService) {
        this.loanNewSchemaListOfTypeService = loanNewSchemaListOfTypeService;
    }

    public LoanNewTypeService getLoanNewTypeService() {
        return loanNewTypeService;
    }

    public void setLoanNewTypeService(LoanNewTypeService loanNewTypeService) {
        this.loanNewTypeService = loanNewTypeService;
    }

    public Map<String, Long> getDropDownSchemeType() {
        return dropDownSchemeType;
    }

    public void setDropDownSchemeType(Map<String, Long> dropDownSchemeType) {
        this.dropDownSchemeType = dropDownSchemeType;
    }

    public List<LoanNewType> getListLoanNewType() {
        return listLoanNewType;
    }

    public void setListLoanNewType(List<LoanNewType> listLoanNewType) {
        this.listLoanNewType = listLoanNewType;
    }

    public String getLoanNewSchemaId() {
        return loanNewSchemaId;
    }

    public void setLoanNewSchemaId(String loanNewSchemaId) {
        this.loanNewSchemaId = loanNewSchemaId;
    }
    
    
}
