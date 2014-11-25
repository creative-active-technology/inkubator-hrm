/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PaySalaryComponentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "paySalaryComponentFormController")
@ViewScoped
public class PaySalaryComponentFormController extends BaseController{
    private PaySalaryComponentModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{paySalaryComponentService}")
    private PaySalaryComponentService paySalaryComponentService;
    @ManagedProperty(value = "#{paySalaryJurnalService}")
    private PaySalaryJurnalService paySalaryJurnalService;
    @ManagedProperty(value = "#{modelComponentService}")
    private ModelComponentService modelComponentService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{taxComponentService}")
    private TaxComponentService taxComponentService;
    private Boolean disableTax;
    
    //Dropdown
    private Map<String, Long> dropDownModelComponent = new TreeMap<String, Long>();;
    private List<ModelComponent> listModelComponent = new ArrayList<>();
    private Map<String, Long> dropDownPaySalaryJurnal = new TreeMap<String, Long>();;
    private List<PaySalaryJurnal> listPaySalaryJurnal = new ArrayList<>();
    private Map<String, Long> dropDownTaxComponent = new TreeMap<String, Long>();;
    private List<TaxComponent> listTaxComponent = new ArrayList<>();
    private DualListModel<EmployeeType> dualListModel = new DualListModel<>();
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            disableTax = Boolean.FALSE;
            model = new PaySalaryComponentModel();
            isUpdate = Boolean.FALSE;
            String loanId = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(loanId)) {
                PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.parseLong(loanId.substring(1)));
                if (loanId.substring(1) != null) {
                    model = getModelFromEntity(paySalaryComponent);
//                    List<EmployeeType> sourceSpiRole = this.employeeTypeService.getAllData();
//                    List<EmployeeType> targetRole = paySalaryComponent.getEmployeeTypes();
//                    sourceSpiRole.removeAll(targetRole);
//
//                    dualListModel = new DualListModel<>(sourceSpiRole, targetRole);
                    isUpdate = Boolean.TRUE;
                }
            }else{
                List<EmployeeType> source = this.employeeTypeService.getAllData();
                dualListModel.setSource(source);
            }
            
            listDrowDown();
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isUpdate = null;
        model = null;
        paySalaryComponentService = null;
        paySalaryJurnalService = null;
        paySalaryJurnalService = null;
        modelComponentService = null;
        employeeTypeService = null;
        taxComponentService = null;
        employeeTypeService = null;
        dualListModel = null;
        dropDownModelComponent = null;
        listModelComponent = null;
        dropDownPaySalaryJurnal = null;
        listPaySalaryJurnal = null;
        dropDownTaxComponent = null;
        listTaxComponent = null;
        disableTax = null;
    }
    
    public void listDrowDown() throws Exception{
        //model component
        listModelComponent = modelComponentService.getAllData();
        for (ModelComponent modelComponent : listModelComponent) {
            dropDownModelComponent.put(modelComponent.getName(), modelComponent.getId());
        }
        
        //pay salary jurnal
        listPaySalaryJurnal = paySalaryJurnalService.getAllData();
        for (PaySalaryJurnal paySalaryJurnal: listPaySalaryJurnal) {
            dropDownPaySalaryJurnal.put(paySalaryJurnal.getName(), paySalaryJurnal.getId());
        }
        
        //tax component
        listTaxComponent = taxComponentService.getAllData();
        for (TaxComponent taxComponent : listTaxComponent) {
            dropDownTaxComponent.put(taxComponent.getName(), taxComponent.getId());
        }
        
        MapUtil.sortByValue(dropDownTaxComponent);
        MapUtil.sortByValue(dropDownPaySalaryJurnal);
//        MapUtil.sortByValue(dropDownModelComponent);
    }
    
    private PaySalaryComponentModel getModelFromEntity(PaySalaryComponent entity) {
        PaySalaryComponentModel paySalaryComponentModel = new PaySalaryComponentModel();
        paySalaryComponentModel.setId(entity.getId());
        paySalaryComponentModel.setCode(entity.getCode());
        paySalaryComponentModel.setName(entity.getName());
        if(entity.getModelComponent()!= null){
            paySalaryComponentModel.setModelComponentId(entity.getModelComponent().getId());
        }
        if(entity.getTaxComponent()!= null){
            paySalaryComponentModel.setTaxComponentId(entity.getTaxComponent().getId());
        }
        if(entity.getPaySalaryJurnal()!= null){
            paySalaryComponentModel.setPaySalaryJurnalId(entity.getPaySalaryJurnal().getId());
        }
        paySalaryComponentModel.setRenumeration(entity.getRenumeration());
        paySalaryComponentModel.setFormula(entity.getFormula());
        paySalaryComponentModel.setComponentCategory(entity.getComponentCategory());
        paySalaryComponentModel.setResetData(entity.getResetData());
        return paySalaryComponentModel;
    }
    
    
    private PaySalaryComponent getEntityFromViewModel(PaySalaryComponentModel model) throws Exception {
        PaySalaryComponent paySalaryComponent = new PaySalaryComponent();
        if (model.getId() != null) {
            paySalaryComponent.setId(model.getId());
        }
        paySalaryComponent.setCode(model.getCode());
        paySalaryComponent.setName(model.getName());
        paySalaryComponent.setPaySalaryJurnal(new PaySalaryJurnal(model.getPaySalaryJurnalId()));
        paySalaryComponent.setTaxComponent(new TaxComponent(model.getTaxComponentId()));
        paySalaryComponent.setModelComponent(new ModelComponent(model.getModelComponentId()));
        paySalaryComponent.setRenumeration(model.getRenumeration());
        paySalaryComponent.setFormula(model.getFormula());
        paySalaryComponent.setComponentCategory(model.getComponentCategory());
        paySalaryComponent.setResetData(model.getResetData());
        return paySalaryComponent;
    }
    
    public void doSave() throws Exception {
        PaySalaryComponent paySalaryComponent = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                paySalaryComponentService.update(paySalaryComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                paySalaryComponentService.save(paySalaryComponent);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doChangeTaxable() throws Exception{
        if(Objects.equals(model.getTaxableCheck(), Boolean.TRUE)){
            disableTax = Boolean.FALSE;
        }else{
            disableTax = Boolean.TRUE;
        }
    }
    
    public String doBack() {
        return "/protected/payroll/pay_salary_component_view.htm?faces-redirect=true";
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        super.onDialogReturn(event);

    }
    
    public void doSearch() {
      
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 700);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("pay_salary_component_formula", options, null);
    }
    
    public Boolean getDisableTax() {
        return disableTax;
    }

    public void setDisableTax(Boolean disableTax) {
        this.disableTax = disableTax;
    }
    
    public PaySalaryComponentModel getModel() {
        return model;
    }

    public void setModel(PaySalaryComponentModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public PaySalaryComponentService getPaySalaryComponentService() {
        return paySalaryComponentService;
    }

    public void setPaySalaryComponentService(PaySalaryComponentService paySalaryComponentService) {
        this.paySalaryComponentService = paySalaryComponentService;
    }

    public PaySalaryJurnalService getPaySalaryJurnalService() {
        return paySalaryJurnalService;
    }

    public void setPaySalaryJurnalService(PaySalaryJurnalService paySalaryJurnalService) {
        this.paySalaryJurnalService = paySalaryJurnalService;
    }

    public ModelComponentService getModelComponentService() {
        return modelComponentService;
    }

    public void setModelComponentService(ModelComponentService modelComponentService) {
        this.modelComponentService = modelComponentService;
    }

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public TaxComponentService getTaxComponentService() {
        return taxComponentService;
    }

    public void setTaxComponentService(TaxComponentService taxComponentService) {
        this.taxComponentService = taxComponentService;
    }

    public Map<String, Long> getDropDownModelComponent() {
        return dropDownModelComponent;
    }

    public void setDropDownModelComponent(Map<String, Long> dropDownModelComponent) {
        this.dropDownModelComponent = dropDownModelComponent;
    }

    public List<ModelComponent> getListModelComponent() {
        return listModelComponent;
    }

    public void setListModelComponent(List<ModelComponent> listModelComponent) {
        this.listModelComponent = listModelComponent;
    }

    public Map<String, Long> getDropDownPaySalaryJurnal() {
        return dropDownPaySalaryJurnal;
    }

    public void setDropDownPaySalaryJurnal(Map<String, Long> dropDownPaySalaryJurnal) {
        this.dropDownPaySalaryJurnal = dropDownPaySalaryJurnal;
    }

    public List<PaySalaryJurnal> getListPaySalaryJurnal() {
        return listPaySalaryJurnal;
    }

    public void setListPaySalaryJurnal(List<PaySalaryJurnal> listPaySalaryJurnal) {
        this.listPaySalaryJurnal = listPaySalaryJurnal;
    }

    public Map<String, Long> getDropDownTaxComponent() {
        return dropDownTaxComponent;
    }

    public void setDropDownTaxComponent(Map<String, Long> dropDownTaxComponent) {
        this.dropDownTaxComponent = dropDownTaxComponent;
    }

    public List<TaxComponent> getListTaxComponent() {
        return listTaxComponent;
    }

    public void setListTaxComponent(List<TaxComponent> listTaxComponent) {
        this.listTaxComponent = listTaxComponent;
    }

    public DualListModel<EmployeeType> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EmployeeType> dualListModel) {
        this.dualListModel = dualListModel;
    }
    
    
}
