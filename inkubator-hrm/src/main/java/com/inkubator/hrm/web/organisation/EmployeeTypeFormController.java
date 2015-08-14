package com.inkubator.hrm.web.organisation;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.web.model.EmployeeTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
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
 * @author rizkykojek
 */
@ManagedBean(name = "employeeTypeFormController")
@ViewScoped
public class EmployeeTypeFormController extends BaseController {

    private EmployeeTypeModel employeeTypeModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        employeeTypeModel = new EmployeeTypeModel();
        employeeTypeModel.setDirectTask(Boolean.FALSE);
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                EmployeeType employeeType = employeeTypeService.getEntiyByPK(Long.parseLong(param));
                if (employeeType != null) {
                    employeeTypeModel.setId(employeeType.getId());
                    employeeTypeModel.setName(employeeType.getName());
                    employeeTypeModel.setDirectTask((employeeType.getDirectTask()));
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        employeeTypeService = null;
        employeeTypeModel = null;
        isUpdate = null;
    }

    public EmployeeTypeModel getEmployeeTypeModel() {
        return employeeTypeModel;
    }

    public void setEmployeeTypeModel(EmployeeTypeModel employeeTypeModel) {
        this.employeeTypeModel = employeeTypeModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public void doSave() {
        EmployeeType employeeType = getEntityFromViewModel(employeeTypeModel);
        try {
            if (isUpdate) {
                employeeTypeService.update(employeeType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                employeeTypeService.save(employeeType);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private EmployeeType getEntityFromViewModel(EmployeeTypeModel employeeTypeModel) {
        EmployeeType employeeType = new EmployeeType();
        if (employeeTypeModel.getId() != null) {
            employeeType.setId(employeeTypeModel.getId());
        }
        employeeType.setName(employeeTypeModel.getName());
        employeeType.setDirectTask(employeeTypeModel.getDirectTask());
        return employeeType;
    }
}
