/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.ModelComponent;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.entity.PaySalaryEmpType;
import com.inkubator.hrm.entity.PaySalaryEmpTypeId;
import com.inkubator.hrm.entity.PaySalaryJurnal;
import com.inkubator.hrm.entity.TaxComponent;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.ModelComponentService;
import com.inkubator.hrm.service.PaySalaryComponentService;
import com.inkubator.hrm.service.PaySalaryJurnalService;
import com.inkubator.hrm.service.TaxComponentService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.PaySalaryComponentModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
public class PaySalaryComponentFormController extends BaseController {

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
    private Boolean isDisableComponetModel;

    //Dropdown
    private Map<String, Long> dropDownModelComponent = new TreeMap<String, Long>();
    private List<ModelComponent> listModelComponent = new ArrayList<>();
    private Map<String, Long> dropDownPaySalaryJurnal = new TreeMap<String, Long>();
    private List<PaySalaryJurnal> listPaySalaryJurnal = new ArrayList<>();
    private HashMap<String, Long> dropDownTaxComponent = new HashMap<String, Long>();
    private List<TaxComponent> listTaxComponent = new ArrayList<>();
    private DualListModel<EmployeeType> dualListModel = new DualListModel<>();
    private Map<String, Long> dropDownModelRef = new HashMap<>();
    Map<String, Long> dropDownKomponenPajak = new HashMap<String, Long>();
    private String loanId;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            disableTax = Boolean.TRUE;
            model = new PaySalaryComponentModel();
            isUpdate = Boolean.FALSE;
            loanId = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(loanId)) {
                PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.parseLong(loanId.substring(1)));
                if (loanId.substring(1) != null) {
                    model = getModelFromEntity(paySalaryComponent);
                    dropDownModelRef = new HashMap<>();
//                    Long modRefId = Long.parseLong(String.valueOf(model.getModelReffernsiId()));
                    try {
                        dropDownModelRef = this.paySalaryComponentService.returnComponentChange(paySalaryComponent.getModelComponent().getId(), model.getModelReffernsiId());

                        if (dropDownModelRef.size() > 0) {
                            isDisableComponetModel = Boolean.FALSE;
                        } else {
                            isDisableComponetModel = Boolean.TRUE;
                        }
                    } catch (Exception ex) {
                        LOGGER.info(ex, ex);
                    }
                    List<EmployeeType> sourceSpiRole = this.employeeTypeService.getAllData();
                    List<EmployeeType> targetRole = paySalaryComponent.getEmployeeTypes();
                    sourceSpiRole.removeAll(targetRole);
//
                    dualListModel = new DualListModel<>(sourceSpiRole, targetRole);
                    isUpdate = Boolean.TRUE;
                }
            } else {
                List<EmployeeType> source = this.employeeTypeService.getAllData();
                dualListModel.setSource(source);
                isDisableComponetModel = Boolean.TRUE;
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
        loanId = null;
        employeeTypeService = null;
        dualListModel = null;
        dropDownKomponenPajak = null;
        dropDownModelComponent = null;
        listModelComponent = null;
        dropDownPaySalaryJurnal = null;
        listPaySalaryJurnal = null;
        dropDownTaxComponent = null;
        listTaxComponent = null;
        disableTax = null;
    }

    public void listDrowDown() throws Exception {
        //model component
        listModelComponent = modelComponentService.getAllData();
        for (ModelComponent modelComponent : listModelComponent) {
            dropDownModelComponent.put(modelComponent.getName(), modelComponent.getId());
        }

        //pay salary jurnal
        listPaySalaryJurnal = paySalaryJurnalService.getAllData();
        for (PaySalaryJurnal paySalaryJurnal : listPaySalaryJurnal) {
            dropDownPaySalaryJurnal.put(paySalaryJurnal.getName(), paySalaryJurnal.getId());
        }

        //tax component
        listTaxComponent = taxComponentService.getAllDataByUseComponent();
        for (TaxComponent taxComponent : listTaxComponent) {
            dropDownTaxComponent.put(taxComponent.getId() + " - " + taxComponent.getName(), taxComponent.getId());
        }
//        dropDownModelRef = new HashMap<>();
//        sortByValues(dropDownTaxComponent);
        dropDownKomponenPajak = sortByValues(dropDownTaxComponent);
        MapUtil.sortByValue(dropDownPaySalaryJurnal);
//        MapUtil.sortByValue(dropDownModelComponent);
    }

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    private PaySalaryComponentModel getModelFromEntity(PaySalaryComponent entity) {
        PaySalaryComponentModel paySalaryComponentModel = new PaySalaryComponentModel();
        paySalaryComponentModel.setId(entity.getId());
        paySalaryComponentModel.setCode(entity.getCode());
        paySalaryComponentModel.setName(entity.getName());
        if (entity.getModelComponent() != null) {
            paySalaryComponentModel.setModelComponentId(entity.getModelComponent().getId());
        }
        paySalaryComponentModel.setTaxableCheck(Boolean.FALSE);
        if (entity.getTaxComponent() != null) {
            paySalaryComponentModel.setTaxComponentId(entity.getTaxComponent().getId());
            paySalaryComponentModel.setTaxableCheck(Boolean.TRUE);
            disableTax = Boolean.FALSE;
        }
        if (entity.getPaySalaryJurnal() != null) {
            paySalaryComponentModel.setPaySalaryJurnalId(entity.getPaySalaryJurnal().getId());
        }
        paySalaryComponentModel.setModelReffernsiId(entity.getModelReffernsil());
        paySalaryComponentModel.setRenumeration(entity.getRenumeration());
        paySalaryComponentModel.setFormula(entity.getFormula());
        paySalaryComponentModel.setComponentCategory(entity.getComponentCategory());
        paySalaryComponentModel.setResetData(entity.getResetData());
        paySalaryComponentModel.setActiveFromTmb(entity.getActiveFromTmb());
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
        if (model.getTaxComponentId() != null) {
            paySalaryComponent.setTaxComponent(new TaxComponent(model.getTaxComponentId()));
        }
        paySalaryComponent.setModelComponent(new ModelComponent(model.getModelComponentId()));
        paySalaryComponent.setRenumeration(model.getRenumeration());
        paySalaryComponent.setFormula(model.getFormula());
        paySalaryComponent.setComponentCategory(model.getComponentCategory());
        paySalaryComponent.setModelReffernsil(model.getModelReffernsiId());
        paySalaryComponent.setResetData(model.getResetData());
        paySalaryComponent.setActiveFromTmb(model.getActiveFromTmb());
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

    public String doSaved() throws Exception {
        PaySalaryComponent paySalaryComponent = getEntityFromViewModel(model);
        if (isUpdate) {
            return doUpdate(paySalaryComponent);
        } else {
            return doInsert(paySalaryComponent);
        }
    }

    private String doUpdate(PaySalaryComponent paySalaryComponent) {
        try {
            Set<PaySalaryEmpType> dataToSave = new HashSet<>();
            List<EmployeeType> employeeTypes = dualListModel.getTarget();
            for (EmployeeType employeeType : employeeTypes) {
                PaySalaryEmpType paySalaryEmpType = new PaySalaryEmpType();
                paySalaryEmpType.setId(new PaySalaryEmpTypeId(paySalaryComponent.getId(), employeeType.getId()));
                paySalaryEmpType.setEmployeeType(employeeType);
                paySalaryEmpType.setPaySalaryComponent(paySalaryComponent);
                dataToSave.add(paySalaryEmpType);
            }
            paySalaryComponent.setPaySalaryEmpTypes(dataToSave);
            paySalaryComponentService.update(paySalaryComponent);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/payroll/pay_salary_comp_detail.htm?faces-redirect=true&execution=e" + paySalaryComponent.getId();

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doChangeTaxable() throws Exception {
        if (Objects.equals(model.getTaxableCheck(), Boolean.TRUE)) {
            disableTax = Boolean.FALSE;
        } else {
            disableTax = Boolean.TRUE;
        }
    }

    public String doBack() {
        return "/protected/payroll/pay_salary_comp_view.htm?faces-redirect=true";
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        super.onDialogReturn(event);
        String dataFormula = (String) event.getObject();
        model.setFormula(dataFormula);

    }

    private String doInsert(PaySalaryComponent paySalaryComponent) {
        try {
            paySalaryComponent.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(9)));
            Set<PaySalaryEmpType> dataToSave = new HashSet<>();
            List<EmployeeType> employeeTypes = dualListModel.getTarget();
            for (EmployeeType employeeType : employeeTypes) {
                PaySalaryEmpType paySalaryEmpType = new PaySalaryEmpType();
                paySalaryEmpType.setId(new PaySalaryEmpTypeId(paySalaryComponent.getId(), employeeType.getId()));
                paySalaryEmpType.setEmployeeType(employeeType);
                paySalaryEmpType.setPaySalaryComponent(paySalaryComponent);
                dataToSave.add(paySalaryEmpType);
            }
            paySalaryComponent.setPaySalaryEmpTypes(dataToSave);
            paySalaryComponentService.saveWithEmployeeType(paySalaryComponent);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/payroll/pay_salary_comp_detail.htm?faces-redirect=true&execution=e" + paySalaryComponent.getId();

        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    public void doSearch() {

        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 670);
        options.put("contentHeight", 510);
        RequestContext.getCurrentInstance().openDialog("pay_salary_comp_formula", options, null);
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

    public HashMap<String, Long> getDropDownTaxComponent() {
        return dropDownTaxComponent;
    }

    public void setDropDownTaxComponent(HashMap<String, Long> dropDownTaxComponent) {
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

    public void doCangeComponentModel() {
        dropDownModelRef = new HashMap<>();
        try {
//            Long modRefId = Long.parseLong(String.valueOf(model.getModelReffernsiId()));
            dropDownModelRef = this.paySalaryComponentService.returnComponentChange(model.getModelComponentId(), null);
            
            if (dropDownModelRef.size() > 0) {
                isDisableComponetModel = Boolean.FALSE;
            } else {
                isDisableComponetModel = Boolean.TRUE;
            }
        } catch (Exception ex) {
            LOGGER.info(ex, ex);
        }
    }

    public Map<String, Long> getDropDownModelRef() {
        return dropDownModelRef;
    }

    public void setDropDownModelRef(Map<String, Long> dropDownModelRef) {
        this.dropDownModelRef = dropDownModelRef;
    }

    public Boolean getIsDisableComponetModel() {
        return isDisableComponetModel;
    }

    public void setIsDisableComponetModel(Boolean isDisableComponetModel) {
        this.isDisableComponetModel = isDisableComponetModel;
    }

    public Map<String, Long> getDropDownKomponenPajak() {
        return dropDownKomponenPajak;
    }

    public void setDropDownKomponenPajak(Map<String, Long> dropDownKomponenPajak) {
        this.dropDownKomponenPajak = dropDownKomponenPajak;
    }

    public void doReset() throws Exception {
        if (isUpdate == Boolean.TRUE) {
            PaySalaryComponent paySalaryComponent = paySalaryComponentService.getEntityByPkWithDetail(Long.valueOf(loanId.substring(1)));
            model.setCode(paySalaryComponent.getCode());
            model.setName(paySalaryComponent.getName());
            model.setModelComponentId(paySalaryComponent.getModelComponent().getId());
            model.setModelReffernsiId(paySalaryComponent.getModelReffernsil());
            model.setFormula(paySalaryComponent.getFormula());
            model.setComponentCategory(paySalaryComponent.getComponentCategory());
            model.setActiveFromTmb(paySalaryComponent.getActiveFromTmb());
            model.setPaySalaryJurnalId(paySalaryComponent.getPaySalaryJurnal().getId());
            model.setResetData(paySalaryComponent.getResetData());
            model.setRenumeration(paySalaryComponent.getRenumeration());

            model.setTaxableCheck(Boolean.FALSE);

            if (paySalaryComponent.getModelComponent().getId() == HRMConstant.MODEL_COMP_LOAN || paySalaryComponent.getModelComponent().getId() == HRMConstant.MODEL_COMP_REIMBURSEMENT || paySalaryComponent.getModelComponent().getId() == HRMConstant.MODEL_COMP_BENEFIT_TABLE) {
                model.setModelComponentId(paySalaryComponent.getModelComponent().getId());
                isDisableComponetModel = Boolean.FALSE;
            } else {
                model.setModelComponentId(paySalaryComponent.getModelComponent().getId());
                isDisableComponetModel = Boolean.TRUE;
            }
            if (paySalaryComponent.getTaxComponent() != null) {
                model.setTaxComponentId(paySalaryComponent.getTaxComponent().getId());
                model.setTaxableCheck(Boolean.TRUE);
                disableTax = Boolean.FALSE;
            }
        } else {
            model.setCode(null);
            model.setName(null);
            model.setModelComponentId(null);
            model.setModelReffernsiId(null);
            model.setFormula(null);
            model.setComponentCategory(null);
            model.setActiveFromTmb(null);
            model.setPaySalaryJurnalId(null);
            model.setResetData(null);
            model.setRenumeration(null);
            model.setTaxableCheck(null);
        }
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

}
