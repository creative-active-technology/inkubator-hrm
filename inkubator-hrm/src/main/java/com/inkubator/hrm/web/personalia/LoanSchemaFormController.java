/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.LoanSchema;
import com.inkubator.hrm.entity.LoanSchemaEmployeeType;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.LoanSchemaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.LoanSchemaModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "loanSchemaFormController")
@ViewScoped
public class LoanSchemaFormController extends BaseController{
    private LoanSchemaModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{loanSchemaService}")
    private LoanSchemaService loanSchemaService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    //Dropdown
    private Map<String, Long> dropDownCostCenter = new TreeMap<String, Long>();;
    private List<CostCenter> listCostCenter = new ArrayList<>();
    private DualListModel<EmployeeType> dualListModel = new DualListModel<>();
    private Boolean isMaxNominal;
    private Boolean isMaxPayment;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            isMaxNominal = Boolean.TRUE;
            isMaxPayment = Boolean.TRUE;
            model = new LoanSchemaModel();
            isUpdate = Boolean.FALSE;
            String loanId = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(loanId)) {
                LoanSchema loanSchema = loanSchemaService.getEntityByPkWithAllRelation(Long.parseLong(loanId.substring(1)));
                if (loanId.substring(1) != null) {
                    model = getModelFromEntity(loanSchema);
                    List<EmployeeType> sourceSpiRole = this.employeeTypeService.getAllData();
                    List<EmployeeType> targetRole = loanSchema.getEmployeeTypes();
                    sourceSpiRole.removeAll(targetRole);

                    dualListModel = new DualListModel<>(sourceSpiRole, targetRole);
                    isUpdate = Boolean.TRUE;
                }
                if(loanSchema.getBasicValue() == 0){
                    isMaxNominal = Boolean.FALSE;
                    isMaxPayment = Boolean.TRUE;
                }else{
                    isMaxNominal = Boolean.TRUE;
                    isMaxPayment = Boolean.FALSE;
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
        listCostCenter = null;
        dropDownCostCenter = null;
        costCenterService = null;
        loanSchemaService = null;
        employeeTypeService = null;
        dualListModel = null;
        isMaxNominal = null;
        isMaxPayment = null;
    }
    
    public void listDrowDown() throws Exception{
        //cost center
        listCostCenter = costCenterService.getAllData();
        for (CostCenter costCenter : listCostCenter) {
            dropDownCostCenter.put(costCenter.getName(), costCenter.getId());
        }
        MapUtil.sortByValue(dropDownCostCenter);
    }
    
    public void doChangeMaxNominalOr(){
        if(model.getBasicValue() == 0){
            
            isMaxNominal = Boolean.FALSE;
            isMaxPayment = Boolean.TRUE;
        }else{
            isMaxNominal = Boolean.TRUE;
            isMaxPayment = Boolean.FALSE;
        }
    }
    
    private LoanSchemaModel getModelFromEntity(LoanSchema entity) {
        LoanSchemaModel loanSchemaModel = new LoanSchemaModel();
        loanSchemaModel.setId(entity.getId());
        loanSchemaModel.setCode(entity.getCode());
        if(entity.getCostCenter() != null){
            loanSchemaModel.setCostCenter(entity.getCostCenter().getId());
        }
        loanSchemaModel.setBasicValue(entity.getBasicValue());
        loanSchemaModel.setMaxNominal(entity.getMaxNominal());
        loanSchemaModel.setName(entity.getName());
        loanSchemaModel.setMinPaymment(entity.getMinPayment());
        loanSchemaModel.setInterestRate(entity.getInterestRate());
        loanSchemaModel.setMaxPaymentOfSalary(entity.getMaxPaymentOfSalary());
        loanSchemaModel.setMaxPeriode(entity.getMaxPeriode());
        loanSchemaModel.setPenaltyOfNonComplance(entity.getPenaltyOfNonComplance());
        loanSchemaModel.setTypeOfInterest(entity.getTypeOfInterest());
        return loanSchemaModel;
    }

    
    public void doSave() throws Exception {
        LoanSchema loanSchema = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                loanSchemaService.update(loanSchema);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanSchemaService.save(loanSchema);
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
    
    public String doSaved() throws Exception {
        LoanSchema loanSchema = getEntityFromViewModel(model);
        if (isUpdate) {
            return doUpdate(loanSchema);
        } else {
            return doInsert(loanSchema);
        }
    }
    
    private String doUpdate(LoanSchema loanSchema) {
        try {
            
                Set<LoanSchemaEmployeeType> dataToSave = new HashSet<>();
                List<EmployeeType> employeeTypes = dualListModel.getTarget();
                for (EmployeeType employeeType : employeeTypes) {
                    LoanSchemaEmployeeType loanSchemaEmployeeType = new LoanSchemaEmployeeType();
                    loanSchemaEmployeeType.setEmployeeType(employeeType);
                    loanSchemaEmployeeType.setLoanSchema(loanSchema);
                    loanSchemaEmployeeType.setCreatedBy(UserInfoUtil.getUserName());
                    loanSchemaEmployeeType.setCreatedOn(new Date());
                    dataToSave.add(loanSchemaEmployeeType);
                }
                loanSchema.setLoanSchemaEmployeeTypes(dataToSave);
                loanSchemaService.update(loanSchema);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/personalia/loan_schema_detail.htm?faces-redirect=true&execution=e" + loanSchema.getId();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    private String doInsert(LoanSchema loanSchema) {
        try {
                Set<LoanSchemaEmployeeType> dataToSave = new HashSet<>();
                List<EmployeeType> employeeTypes = dualListModel.getTarget();
                for (EmployeeType employeeType : employeeTypes) {
                    LoanSchemaEmployeeType loanSchemaEmployeeType = new LoanSchemaEmployeeType();
                    loanSchemaEmployeeType.setEmployeeType(employeeType);
                    loanSchemaEmployeeType.setLoanSchema(loanSchema);
                    loanSchemaEmployeeType.setCreatedBy(UserInfoUtil.getUserName());
                    loanSchemaEmployeeType.setCreatedOn(new Date());
                    dataToSave.add(loanSchemaEmployeeType);
                }
                loanSchema.setLoanSchemaEmployeeTypes(dataToSave);
                loanSchemaService.saveAndNotification(loanSchema);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/personalia/loan_schema_detail.htm?faces-redirect=true&execution=e" + loanSchema.getId();

            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private LoanSchema getEntityFromViewModel(LoanSchemaModel model) throws Exception {
        LoanSchema loanSchema = new LoanSchema();
        if (model.getId() != null) {
            loanSchema.setId(model.getId());
        }
        loanSchema.setCode(model.getCode());
        loanSchema.setName(model.getName());
        loanSchema.setCostCenter(new CostCenter(model.getCostCenter()));
        if(model.getBasicValue() == HRMConstant.BASIC_VALUE_NOMINAL){
            loanSchema.setMaxNominal(model.getMaxNominal());
            loanSchema.setMaxPaymentOfSalary(null);
        }else{
            loanSchema.setMaxNominal(null);
            loanSchema.setMaxPaymentOfSalary(model.getMaxPaymentOfSalary());
        }
        loanSchema.setBasicValue(model.getBasicValue());
        loanSchema.setMaxPeriode(model.getMaxPeriode());
        loanSchema.setPenaltyOfNonComplance(model.getPenaltyOfNonComplance());
        loanSchema.setTypeOfInterest(model.getTypeOfInterest());
        loanSchema.setInterestRate(model.getInterestRate());
        loanSchema.setMinPayment(model.getMinPaymment());
        return loanSchema;
    }
    
    public String doBack() {
        return "/protected/personalia/loan_schema_view.htm?faces-redirect=true";
    }

    public LoanSchemaModel getModel() {
        return model;
    }

    public void setModel(LoanSchemaModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public LoanSchemaService getLoanSchemaService() {
        return loanSchemaService;
    }

    public void setLoanSchemaService(LoanSchemaService loanSchemaService) {
        this.loanSchemaService = loanSchemaService;
    }

    public CostCenterService getCostCenterService() {
        return costCenterService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
    }

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public Map<String, Long> getDropDownCostCenter() {
        return dropDownCostCenter;
    }

    public void setDropDownCostCenter(Map<String, Long> dropDownCostCenter) {
        this.dropDownCostCenter = dropDownCostCenter;
    }

    public List<CostCenter> getListCostCenter() {
        return listCostCenter;
    }

    public void setListCostCenter(List<CostCenter> listCostCenter) {
        this.listCostCenter = listCostCenter;
    }

    public DualListModel<EmployeeType> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EmployeeType> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public Boolean getIsMaxNominal() {
        return isMaxNominal;
    }

    public void setIsMaxNominal(Boolean isMaxNominal) {
        this.isMaxNominal = isMaxNominal;
    }

    public Boolean getIsMaxPayment() {
        return isMaxPayment;
    }

    public void setIsMaxPayment(Boolean isMaxPayment) {
        this.isMaxPayment = isMaxPayment;
    }

    public void doReset() {
        cleanAndExit();
    }
    
    
}
