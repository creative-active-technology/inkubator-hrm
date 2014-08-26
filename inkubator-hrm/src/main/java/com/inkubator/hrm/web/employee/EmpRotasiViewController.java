/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.employee;

import com.inkubator.hrm.entity.EmpRotasi;
import com.inkubator.hrm.service.EmpRotasiService;
import com.inkubator.hrm.web.lazymodel.EmpRotasiLazyDataModel;
import com.inkubator.hrm.web.search.EmpRotasiSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "empRotasiViewController")
@ViewScoped
public class EmpRotasiViewController extends BaseController {

    private EmpRotasiSearchParameter empRotasiSearchParameter;
    private LazyDataModel<EmpRotasi> empRotasiLazyDataModel;
    @ManagedProperty(value = "#{empRotasiService}")
    private EmpRotasiService empRotasiService;
    private EmpRotasi selectedEmpRotasi;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        empRotasiSearchParameter = new EmpRotasiSearchParameter();

    }

    public EmpRotasiSearchParameter getEmpRotasiSearchParameter() {
        return empRotasiSearchParameter;
    }

    public void setEmpRotasiSearchParameter(EmpRotasiSearchParameter empRotasiSearchParameter) {
        this.empRotasiSearchParameter = empRotasiSearchParameter;
    }

    public void doSearch() {
        empRotasiLazyDataModel = null;
    }

    public void onDelete() {
//        try {
//            selectedEmpData = this.empRotasiService.getEntiyByPK(selectedEmpData.getId());
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public void doDelete() {

//        try {
//            this.empDataService.delete(selectedEmpData);
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
//
//        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
//            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
//                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
////            LOGGER.error("Error", ex);
//        } catch (Exception ex) {
//            LOGGER.error("Error", ex);
//        }
    }

    public String doDetail() {
        return "/protected/employee/employee_placement_detail.htm?faces-redirect=true&execution=e" + selectedEmpRotasi.getId();
    }

    public String doEdit() {
        return "/protected/employee/employee_palcement_form.htm?faces-redirect=true&execution=e" + selectedEmpRotasi.getId();
    }

    public LazyDataModel<EmpRotasi> getEmpRotasiLazyDataModel() {
        if (empRotasiLazyDataModel == null) {
            empRotasiLazyDataModel = new EmpRotasiLazyDataModel(empRotasiSearchParameter, empRotasiService);
        }
        return empRotasiLazyDataModel;
    }

    public void setEmpRotasiLazyDataModel(LazyDataModel<EmpRotasi> empRotasiLazyDataModel) {
        this.empRotasiLazyDataModel = empRotasiLazyDataModel;
    }

    public void setEmpRotasiService(EmpRotasiService empRotasiService) {
        this.empRotasiService = empRotasiService;
    }

    public EmpRotasi getSelectedEmpRotasi() {
        return selectedEmpRotasi;
    }

    public void setSelectedEmpRotasi(EmpRotasi selectedEmpRotasi) {
        this.selectedEmpRotasi = selectedEmpRotasi;
    }

}
