/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.Reimbursment;
import com.inkubator.hrm.entity.ReimbursmentSchema;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.ReimbursmentSchemaService;
import com.inkubator.hrm.service.ReimbursmentService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.ReimbursmentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
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
@ManagedBean(name = "reimbursmentFormController")
@ViewScoped
public class ReimbursmentFormController extends BaseController {

    private ReimbursmentModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{reimbursmentService}")
    private ReimbursmentService reimbursmentService;
    @ManagedProperty(value = "#{reimbursmentSchemaService}")
    private ReimbursmentSchemaService reimbursmentSchemaService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    //Dropdown
    private Map<String, Long> dropDownReimbursmentSchema = new TreeMap<String, Long>();
    ;
    private List<ReimbursmentSchema> listReimbursmentSchema = new ArrayList<>();

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
            model = new ReimbursmentModel();
            isUpdate = Boolean.FALSE;
            String reimbursmentId = FacesUtil.getRequestParameter("reimbursmentId");
            if (StringUtils.isNotEmpty(reimbursmentId)) {
                Reimbursment reimbursment = reimbursmentService.getEntityByPkWithDetail(Long.parseLong(reimbursmentId));
                if (reimbursmentId.substring(1) != null) {
                    model = getModelFromEntity(reimbursment);
                    isUpdate = Boolean.TRUE;
                }
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
        reimbursmentService = null;
        empDataService = null;
        dropDownReimbursmentSchema = null;
        reimbursmentSchemaService = null;
        listReimbursmentSchema = null;
    }

    public void listDrowDown() throws Exception {
        //cost center
        listReimbursmentSchema = reimbursmentSchemaService.getAllData();
        for (ReimbursmentSchema reimbursmentSchema : listReimbursmentSchema) {
            dropDownReimbursmentSchema.put(reimbursmentSchema.getName(), reimbursmentSchema.getId());
        }
        MapUtil.sortByValue(dropDownReimbursmentSchema);
    }

    private ReimbursmentModel getModelFromEntity(Reimbursment entity) {
        ReimbursmentModel reimbursmentModel = new ReimbursmentModel();
        reimbursmentModel.setId(entity.getId());
        reimbursmentModel.setCode(entity.getCode());
        if (entity.getEmpData() != null) {
            reimbursmentModel.setEmpData(entity.getEmpData());
        }
        reimbursmentModel.setClaimDate(entity.getClaimDate());
        reimbursmentModel.setNominal(entity.getNominal());
        reimbursmentModel.setQuantity(entity.getQuantity());
        reimbursmentModel.setReimbursmentSchemaId(entity.getReimbursmentSchema().getId());
        return reimbursmentModel;
    }

    public void doSave() throws Exception {
        Reimbursment reimbursment = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                reimbursmentService.update(reimbursment);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                reimbursmentService.save(reimbursment);
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

    private Reimbursment getEntityFromViewModel(ReimbursmentModel model) throws Exception {
        Reimbursment reimbursment = new Reimbursment();
        if (model.getId() != null) {
            reimbursment.setId(model.getId());
        }
        reimbursment.setCode(model.getCode());
        reimbursment.setEmpData(model.getEmpData());
        reimbursment.setClaimDate(model.getClaimDate());
        reimbursment.setNominal(model.getNominal());
        reimbursment.setQuantity(model.getQuantity());
        reimbursment.setReimbursmentSchema(new ReimbursmentSchema(model.getReimbursmentSchemaId()));
        return reimbursment;
    }

    public List<EmpData> doAutoCompletEmployee(String param) {
        List<EmpData> empDatas = new ArrayList<EmpData>();
        try {
            empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
        return empDatas;
    }

    public ReimbursmentModel getModel() {
        return model;
    }

    public void setModel(ReimbursmentModel model) {
        this.model = model;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public ReimbursmentService getReimbursmentService() {
        return reimbursmentService;
    }

    public void setReimbursmentService(ReimbursmentService reimbursmentService) {
        this.reimbursmentService = reimbursmentService;
    }

    public ReimbursmentSchemaService getReimbursmentSchemaService() {
        return reimbursmentSchemaService;
    }

    public void setReimbursmentSchemaService(ReimbursmentSchemaService reimbursmentSchemaService) {
        this.reimbursmentSchemaService = reimbursmentSchemaService;
    }

    public EmpDataService getEmpDataService() {
        return empDataService;
    }

    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    public Map<String, Long> getDropDownReimbursmentSchema() {
        return dropDownReimbursmentSchema;
    }

    public void setDropDownReimbursmentSchema(Map<String, Long> dropDownReimbursmentSchema) {
        this.dropDownReimbursmentSchema = dropDownReimbursmentSchema;
    }

    public List<ReimbursmentSchema> getListReimbursmentSchema() {
        return listReimbursmentSchema;
    }

    public void setListReimbursmentSchema(List<ReimbursmentSchema> listReimbursmentSchema) {
        this.listReimbursmentSchema = listReimbursmentSchema;
    }
    
    
}
