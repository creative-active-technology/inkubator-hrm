/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LoanNewSchema;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmpId;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LoanNewSchemaListOfEmpService;
import com.inkubator.hrm.service.LoanNewSchemaService;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@ManagedBean(name = "loanNewSchemaListOfEmpFormController")
@ViewScoped
public class LoanNewSchemaListOfEmpFormController extends BaseController {

    private LoanNewSchemaListOfEmpModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{loanNewSchemaListOfEmpService}")
    private LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService;
    @ManagedProperty(value = "#{loanNewSchemaService}")
    private LoanNewSchemaService loanNewSchemaService;
    private String empDataId;
    private Map<String, Long> dropDownLoanNewSchema = new TreeMap<String, Long>();
    private Long oldId;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new LoanNewSchemaListOfEmpModel();
            empDataId = FacesUtil.getRequestParameter("empDataId");
            isUpdate = Boolean.FALSE;
            if (StringUtils.isNotEmpty(empDataId)) {
                EmpData empData = empDataService.getByEmpIdWithDetail(Long.valueOf(empDataId));
                if (empData != null) {
                    isUpdate = Boolean.FALSE;
                    model.setFirstName(empData.getBioData().getFirstName());
                    model.setLastName(empData.getBioData().getLastName());
                    model.setCodeJabatan(empData.getGolonganJabatan().getCode());
                    model.setNik(empData.getNik());
                    model.setEmpDataId(empData.getId());
                    if(empData.getJabatanByJabatanId().getJabatan() != null){
                        model.setNamaJabatan(empData.getJabatanByJabatanId().getJabatan().getName());
                    }
                }
                
                LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = loanNewSchemaListOfEmpService.getEntityByEmpDataId(Long.valueOf(empDataId));
                if(loanNewSchemaListOfEmp != null){
                    isUpdate = Boolean.TRUE;
                    oldId = loanNewSchemaListOfEmp.getLoanNewSchema().getId();
                    model.setNoSk(loanNewSchemaListOfEmp.getNomorSk());
                    model.setLoanNewSchemaId(loanNewSchemaListOfEmp.getLoanNewSchema().getId());
                    model.setDescription(loanNewSchemaListOfEmp.getDescription());
                }
                
                
            }
            
            List<LoanNewSchema> listLoanNewSchema = loanNewSchemaService.getAllData();
            for (LoanNewSchema loanNewSchema : listLoanNewSchema) {
                dropDownLoanNewSchema.put(loanNewSchema.getLoanSchemaName(), loanNewSchema.getId());
            }

        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        empDataService = null;
        loanNewSchemaListOfEmpService = null;
        empDataId = null;
        model = null;
        oldId = null;
        isUpdate = null;
        dropDownLoanNewSchema = null;
        loanNewSchemaService = null;
    }

    public void doSave() {
        
        LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                loanNewSchemaListOfEmpService.update(loanNewSchemaListOfEmp, oldId);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                loanNewSchemaListOfEmpService.save(loanNewSchemaListOfEmp);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private LoanNewSchemaListOfEmp getEntityFromViewModel(LoanNewSchemaListOfEmpModel model) {
        LoanNewSchemaListOfEmp loanNewSchemaListOfEmp = new LoanNewSchemaListOfEmp();
        loanNewSchemaListOfEmp.setId(new LoanNewSchemaListOfEmpId(Long.valueOf(empDataId), model.getLoanNewSchemaId()));
        loanNewSchemaListOfEmp.setEmpData(new EmpData(Long.valueOf(empDataId)));
        loanNewSchemaListOfEmp.setLoanNewSchema(new LoanNewSchema(model.getLoanNewSchemaId()));
        loanNewSchemaListOfEmp.setNomorSk(model.getNoSk());
        loanNewSchemaListOfEmp.setDescription(model.getDescription());
        return loanNewSchemaListOfEmp;
    }
    
    public LoanNewSchemaListOfEmpModel getModel() {
        return model;
    }

    public void setModel(LoanNewSchemaListOfEmpModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public LoanNewSchemaListOfEmpService getLoanNewSchemaListOfEmpService() {
        return loanNewSchemaListOfEmpService;
    }

    public void setLoanNewSchemaListOfEmpService(LoanNewSchemaListOfEmpService loanNewSchemaListOfEmpService) {
        this.loanNewSchemaListOfEmpService = loanNewSchemaListOfEmpService;
    }

    public String getEmpDataId() {
        return empDataId;
    }

    public void setEmpDataId(String empDataId) {
        this.empDataId = empDataId;
    }

    public LoanNewSchemaService getLoanNewSchemaService() {
        return loanNewSchemaService;
    }

    public void setLoanNewSchemaService(LoanNewSchemaService loanNewSchemaService) {
        this.loanNewSchemaService = loanNewSchemaService;
    }

    public Map<String, Long> getDropDownLoanNewSchema() {
        return dropDownLoanNewSchema;
    }

    public void setDropDownLoanNewSchema(Map<String, Long> dropDownLoanNewSchema) {
        this.dropDownLoanNewSchema = dropDownLoanNewSchema;
    }

    
}
