/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenter;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.entity.ReimbursmentSchemaEmployeeType;
import com.inkubator.hrm.service.CostCenterService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.ReimbursmentSchemaModel;
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
@ManagedBean(name = "reimbursmentSchemaFormController")
@ViewScoped
public class ReimbursmentSchemaFormController extends BaseController{
    private ReimbursmentSchemaModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{reimbursmentSchemaService}")
    private ReimbursmentSchemaService reimbursmentSchemaService;
    @ManagedProperty(value = "#{costCenterService}")
    private CostCenterService costCenterService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    //Dropdown
    private Map<String, Long> dropDownCostCenter = new TreeMap<String, Long>();;
    private List<CostCenter> listCostCenter = new ArrayList<>();
    private DualListModel<EmployeeType> dualListModel = new DualListModel<>();
    private Boolean isNominalLimit;
    private Boolean isRatioSalary;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new ReimbursmentSchemaModel();
            isUpdate = Boolean.FALSE;
//            String reimbursmentId = FacesUtil.getRequestParameter("reimbursmentId");
            String reimbursmentId = FacesUtil.getRequestParameter("execution");
            if (StringUtils.isNotEmpty(reimbursmentId)) {
                ReimbursmentSchema reimbursmentSchema = reimbursmentSchemaService.getEntityByPkWithAllRelation(Long.parseLong(reimbursmentId.substring(1)));
                if (reimbursmentId.substring(1) != null) {
                    model = getModelFromEntity(reimbursmentSchema);
                    List<EmployeeType> sourceSpiRole = this.employeeTypeService.getAllData();
                    List<EmployeeType> targetRole = reimbursmentSchema.getEmployeeTypes();
                    sourceSpiRole.removeAll(targetRole);

                    dualListModel = new DualListModel<>(sourceSpiRole, targetRole);
                    isUpdate = Boolean.TRUE;
                }
                if(reimbursmentSchema.getBasicValue() == 0){
                    isNominalLimit = Boolean.FALSE;
                    isRatioSalary = Boolean.TRUE;
                }else{
                    isNominalLimit = Boolean.TRUE;
                    isRatioSalary = Boolean.FALSE;
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
        reimbursmentSchemaService = null;
        employeeTypeService = null;
        dualListModel = null;
        isNominalLimit = null;
        isRatioSalary = null;
    }
    
    public void listDrowDown() throws Exception{
        //cost center
        listCostCenter = costCenterService.getAllData();
        for (CostCenter costCenter : listCostCenter) {
            dropDownCostCenter.put(costCenter.getName(), costCenter.getId());
        }
        MapUtil.sortByValue(dropDownCostCenter);
    }
    
    public void doChangeRatioOrNominal(){
        if(model.getBasicValue() == 0){
            
            isNominalLimit = Boolean.FALSE;
            isRatioSalary = Boolean.TRUE;
        }else{
            isNominalLimit = Boolean.TRUE;
            isRatioSalary = Boolean.FALSE;
        }
    }
    
    private ReimbursmentSchemaModel getModelFromEntity(ReimbursmentSchema entity) {
        ReimbursmentSchemaModel reimbursmentSchemaModel = new ReimbursmentSchemaModel();
        reimbursmentSchemaModel.setId(entity.getId());
        reimbursmentSchemaModel.setCode(entity.getCode());
        if(entity.getCostCenter() != null){
            reimbursmentSchemaModel.setCostCenter(entity.getCostCenter().getId());
        }
        reimbursmentSchemaModel.setBasicValue(entity.getBasicValue());
        reimbursmentSchemaModel.setEffectiveDate(entity.getEffectiveDate());
        reimbursmentSchemaModel.setName(entity.getName());
        reimbursmentSchemaModel.setMeasurement(entity.getMeasurement());
        reimbursmentSchemaModel.setNominalUnit(entity.getNominalUnit());
        reimbursmentSchemaModel.setRatioSalary(entity.getRatioSalary());
        reimbursmentSchemaModel.setPayrolComponent(entity.getPayrollComponent());
        reimbursmentSchemaModel.setTimeRange(entity.getTimeRange());
        return reimbursmentSchemaModel;
    }

    
    public void doSave() throws Exception {
        ReimbursmentSchema  reimbursmentSchema = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                reimbursmentSchemaService.update(reimbursmentSchema);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                reimbursmentSchemaService.save(reimbursmentSchema);
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
        ReimbursmentSchema reimbursmentSchema = getEntityFromViewModel(model);
        if (isUpdate) {
            return doUpdate(reimbursmentSchema);
        } else {
            return doInsert(reimbursmentSchema);
        }
    }
    
    private String doUpdate(ReimbursmentSchema reimbursmentSchema) {
        try {
            
                Set<ReimbursmentSchemaEmployeeType> dataToSave = new HashSet<>();
                List<EmployeeType> employeeTypes = dualListModel.getTarget();
                for (EmployeeType employeeType : employeeTypes) {
                    ReimbursmentSchemaEmployeeType reimbursmentSchemaEmployeeType = new ReimbursmentSchemaEmployeeType();
                    reimbursmentSchemaEmployeeType.setEmployeeType(employeeType);
                    reimbursmentSchemaEmployeeType.setReimbursmentSchema(reimbursmentSchema);
                    reimbursmentSchemaEmployeeType.setCreatedBy(UserInfoUtil.getUserName());
                    reimbursmentSchemaEmployeeType.setCreatedOn(new Date());
                    dataToSave.add(reimbursmentSchemaEmployeeType);
                }
                reimbursmentSchema.setReimbursmentSchemaEmployeeTypes(dataToSave);
                reimbursmentSchemaService.update(reimbursmentSchema);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/personalia/reimbursment_view.htm";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }
    
    private String doInsert(ReimbursmentSchema reimbursmentSchema) {
        try {
                Set<ReimbursmentSchemaEmployeeType> dataToSave = new HashSet<>();
                List<EmployeeType> employeeTypes = dualListModel.getTarget();
                for (EmployeeType employeeType : employeeTypes) {
                    ReimbursmentSchemaEmployeeType reimbursmentSchemaEmployeeType = new ReimbursmentSchemaEmployeeType();
                    reimbursmentSchemaEmployeeType.setEmployeeType(employeeType);
                    reimbursmentSchemaEmployeeType.setReimbursmentSchema(reimbursmentSchema);
                    reimbursmentSchemaEmployeeType.setCreatedBy(UserInfoUtil.getUserName());
                    reimbursmentSchemaEmployeeType.setCreatedOn(new Date());
                    dataToSave.add(reimbursmentSchemaEmployeeType);
                }
                reimbursmentSchema.setReimbursmentSchemaEmployeeTypes(dataToSave);
                reimbursmentSchemaService.saveAndNotification(reimbursmentSchema);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                return "/protected/personalia/reimbursment_view.htm";

            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private ReimbursmentSchema getEntityFromViewModel(ReimbursmentSchemaModel model) throws Exception {
        ReimbursmentSchema reimbursmentSchema = new ReimbursmentSchema();
        if (model.getId() != null) {
            reimbursmentSchema.setId(model.getId());
        }
        reimbursmentSchema.setCode(model.getCode());
        reimbursmentSchema.setCostCenter(new CostCenter(model.getCostCenter()));
        reimbursmentSchema.setBasicValue(model.getBasicValue());
        reimbursmentSchema.setEffectiveDate(model.getEffectiveDate());
        reimbursmentSchema.setMeasurement(model.getMeasurement());
        reimbursmentSchema.setName(model.getName());
        reimbursmentSchema.setNominalUnit(model.getNominalUnit());
        reimbursmentSchema.setPayrollComponent(model.getPayrolComponent());
        reimbursmentSchema.setRatioSalary(model.getRatioSalary());
        reimbursmentSchema.setTimeRange(model.getTimeRange());
        return reimbursmentSchema;
    }

    public ReimbursmentSchemaModel getModel() {
        return model;
    }

    public void setModel(ReimbursmentSchemaModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public ReimbursmentSchemaService getReimbursmentSchemaService() {
        return reimbursmentSchemaService;
    }

    public void setReimbursmentSchemaService(ReimbursmentSchemaService reimbursmentSchemaService) {
        this.reimbursmentSchemaService = reimbursmentSchemaService;
    }

    public CostCenterService getCostCenterService() {
        return costCenterService;
    }

    public void setCostCenterService(CostCenterService costCenterService) {
        this.costCenterService = costCenterService;
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

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public DualListModel<EmployeeType> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EmployeeType> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public Boolean getIsNominalLimit() {
        return isNominalLimit;
    }

    public void setIsNominalLimit(Boolean isNominalLimit) {
        this.isNominalLimit = isNominalLimit;
    }

    public Boolean getIsRatioSalary() {
        return isRatioSalary;
    }

    public void setIsRatioSalary(Boolean isRatioSalary) {
        this.isRatioSalary = isRatioSalary;
    }
    
    
}
