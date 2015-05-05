/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.recruitment;

import com.inkubator.hrm.web.loan.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.LoanNewType;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.service.LoanNewTypeService;
import com.inkubator.hrm.service.RecruitMppApplyService;
import com.inkubator.hrm.web.lazymodel.LoanNewTypeLazyDataModel;
import com.inkubator.hrm.web.lazymodel.RecruitMppApplyViewLazyDataModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.LoanNewTypeSearchParameter;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@ManagedBean(name = "recruitMppApplyViewController")
@ViewScoped
public class RecruitMppApplyViewController extends BaseController{
    private RecruitMppApplySearchParameter searchParameter;
    private LazyDataModel<RecruitMppApplyViewModel> lazyData;
    private RecruitMppApply selectedRecruitMppApply;
    @ManagedProperty(value = "#{recruitMppApplyService}")
    private RecruitMppApplyService recruitMppApplyService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new RecruitMppApplySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        recruitMppApplyService = null;
        searchParameter = null;
        lazyData = null;
        selectedRecruitMppApply = null;
    }

    public void doSearch() {
        lazyData = null;
    }

    public String doDetail() {       
            selectedRecruitMppApply = this.recruitMppApplyService.getEntityWithDetailById(selectedRecruitMppApply.getId());
            return "/protected/recruitment/recruit_mpp_apply_detail.htm?faces-redirect=true&execution=e" + selectedRecruitMppApply.getId();
       
    }

    public void doDelete() {
        try {
            recruitMppApplyService.delete(selectedRecruitMppApply);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete LoanType ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete LoanType", ex);
        }
    }

    public String doAdd() {
        //showDialog(null);
        return "/protected/recruitment/recruit_mpp_apply_form.htm?faces-redirect=true";
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedRecruitMppApply.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 475);
        RequestContext.getCurrentInstance().openDialog("recruit_mpp_apply_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public RecruitMppApplySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(RecruitMppApplySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<RecruitMppApplyViewModel> getLazyData() {
        if (lazyData == null) {
            lazyData = new RecruitMppApplyViewLazyDataModel(searchParameter, recruitMppApplyService);
        }
        return lazyData;
    }

    public void setLazyData(LazyDataModel<RecruitMppApplyViewModel> lazyData) {
        this.lazyData = lazyData;
    }

    public RecruitMppApply getSelectedRecruitMppApply() {
        return selectedRecruitMppApply;
    }

    public void setSelectedRecruitMppApply(RecruitMppApply selectedRecruitMppApply) {
        this.selectedRecruitMppApply = selectedRecruitMppApply;
    }

    public void setRecruitMppApplyService(RecruitMppApplyService recruitMppApplyService) {
        this.recruitMppApplyService = recruitMppApplyService;
    }

    

    

    
    
}

