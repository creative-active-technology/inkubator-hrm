/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtGroupWorking;
import com.inkubator.hrm.service.WtGroupWorkingService;
import com.inkubator.hrm.web.lazymodel.WtGroupWorkingLazyModel;
import com.inkubator.hrm.web.search.WtGroupWorkingSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "groupWorkingViewController")
@ViewScoped
public class GroupWorkingViewController extends BaseController {

    private WtGroupWorkingSearchParameter wtOverTimeSearchParameter;
    private LazyDataModel<WtGroupWorking> wtOverTimesLazyDataModel;
    @ManagedProperty(value = "#{wtGroupWorkingService}")
    private WtGroupWorkingService wtGroupWorkingService;
    private WtGroupWorking selectedWtGroupWorking;

    public void setWtGroupWorkingService(WtGroupWorkingService wtGroupWorkingService) {
        this.wtGroupWorkingService = wtGroupWorkingService;
    }

    public WtGroupWorkingSearchParameter getWtGroupWorkingSearchParameter() {
        return wtOverTimeSearchParameter;
    }

    public void setWtGroupWorkingSearchParameter(WtGroupWorkingSearchParameter wtOverTimeSearchParameter) {
        this.wtOverTimeSearchParameter = wtOverTimeSearchParameter;
    }

    public LazyDataModel<WtGroupWorking> getWtGroupWorkingsLazyDataModel() {
        if (wtOverTimesLazyDataModel == null) {
            wtOverTimesLazyDataModel = new WtGroupWorkingLazyModel(wtOverTimeSearchParameter, wtGroupWorkingService);
        }
        return wtOverTimesLazyDataModel;
    }

    public void setWtGroupWorkingsLazyDataModel(LazyDataModel<WtGroupWorking> wtOverTimesLazyDataModel) {
        this.wtOverTimesLazyDataModel = wtOverTimesLazyDataModel;
    }

    public WtGroupWorking getSelectedWtGroupWorking() {
        return selectedWtGroupWorking;
    }

    public void setSelectedWtGroupWorking(WtGroupWorking selectedWtGroupWorking) {
        this.selectedWtGroupWorking = selectedWtGroupWorking;
    }

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        wtOverTimeSearchParameter = new WtGroupWorkingSearchParameter();

    }

    public void doSearch() {
        wtOverTimesLazyDataModel = null;
    }

    public String doDetail() {
         return "/protected/working_time/working_group_detail.htm?faces-redirect=true&execution=e" + selectedWtGroupWorking.getCode();
    }

    public String doPlacementOfEmployee() {
         return "/protected/working_time/placement_of_employee_work_schedule.htm?faces-redirect=true";
    }
    
    public void doDelete() {
        try {
            this.wtGroupWorkingService.delete(selectedWtGroupWorking);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void onDelete() {
        try {
            selectedWtGroupWorking = wtGroupWorkingService.getEntiyByPK(selectedWtGroupWorking.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        wtOverTimeSearchParameter = null;
        wtOverTimesLazyDataModel = null;
        wtGroupWorkingService = null;
        selectedWtGroupWorking = null;
    }

    public void doAdd() {
        try {
            ExternalContext red = FacesUtil.getExternalContext();
            red.redirect(red.getRequestContextPath() + "/flow-protected/working_group");
        } catch (IOException ex) {
          LOGGER.error("Erorr", ex);
        }
    }
    
    public void doEdit(){
         ExternalContext red = FacesUtil.getExternalContext();
        try {
            red.redirect(red.getRequestContextPath() + "/flow-protected/working_group?otherParam=" + selectedWtGroupWorking.getId());
        } catch (IOException ex) {
          LOGGER.error("Error", ex);
        }
    }

}
