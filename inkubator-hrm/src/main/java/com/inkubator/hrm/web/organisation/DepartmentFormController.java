/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.List;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.CostCenterDept;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.service.CostCenterDeptService;
import com.inkubator.hrm.service.DepartmentService;
import com.inkubator.hrm.web.model.DepartmentModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author deniarianto
 */
@ManagedBean(name = "departmentFormController")
@ViewScoped
public class DepartmentFormController extends BaseController {
    @ManagedProperty(value = "#{departmentService}")
    private DepartmentService departmentService;
    @ManagedProperty(value = "#{costCenterDeptService}")
    private CostCenterDeptService costCenterDeptService;
    private Department selectedDepartment;
    private DepartmentModel departmentModel;
    private List<CostCenterDept> costCenterDepts;
    private Boolean isEdit;

    public DepartmentModel getDepartmentModel() {
        return departmentModel;
    }

    public void setDepartmentModel(DepartmentModel departmentModel) {
        this.departmentModel = departmentModel;
    }
    
    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Department getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(Department selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
    
    public CostCenterDeptService getCostCenterDeptService() {
		return costCenterDeptService;
	}

	public void setCostCenterDeptService(CostCenterDeptService costCenterDeptService) {
		this.costCenterDeptService = costCenterDeptService;
	}

	public List<CostCenterDept> getCostCenterDepts() {
		return costCenterDepts;
	}

	public void setCostCenterDepts(List<CostCenterDept> costCenterDepts) {
		this.costCenterDepts = costCenterDepts;
	}

	@PostConstruct
    @Override
    public void initialization() {
      
        super.initialization();        
        String param = FacesUtil.getRequestParameter("param");
        departmentModel = new DepartmentModel();
        try {
        	costCenterDepts = costCenterDeptService.getAllData();
            if (param != null) {
                isEdit = Boolean.TRUE;
                Department department = departmentService.getEntiyByPK(Long.parseLong(param));
                departmentModel.setId(department.getId());
                departmentModel.setCode(department.getDepartmentCode());
                departmentModel.setName(department.getDepartmentName());
                departmentModel.setDescription(department.getDescription());
                if(department.getCostCenterDept() != null){
                	departmentModel.setCostCenterDeptId(department.getCostCenterDept().getId());
                }
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
      
        Department department = getEntityFromViewModel(departmentModel);
        try {
            if (isEdit) {
                departmentService.update(department);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                departmentService.save(department);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    private Department getEntityFromViewModel(DepartmentModel departmentModel) {
        Department department = new Department();
        if (departmentModel.getId() != null) {
            department.setId(departmentModel.getId());
        }
        department.setDepartmentCode(departmentModel.getCode());
        department.setDepartmentName(departmentModel.getName());
        department.setCostCenterDept(new CostCenterDept(departmentModel.getCostCenterDeptId()));
        department.setDescription(departmentModel.getDescription());

        return department;
    }
    
    @PreDestroy
    private void cleanAndExit() {
        //departmentModel = null;
        departmentService = null;
        selectedDepartment = null;
        isEdit = null;
        costCenterDeptService = null;
        costCenterDepts = null;
    }
}
