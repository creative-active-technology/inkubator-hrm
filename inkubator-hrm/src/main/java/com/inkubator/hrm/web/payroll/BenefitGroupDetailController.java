/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.BenefitGroupRate;
import com.inkubator.hrm.service.BenefitGroupRateService;
import com.inkubator.hrm.service.BenefitGroupService;
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
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "benefitGroupDetailController")
@ViewScoped
public class BenefitGroupDetailController extends BaseController {

    private BenefitGroup selectedBenefitGroup;
    @ManagedProperty(value = "#{benefitGroupService}")
    private BenefitGroupService benefitGroupService;
    private BenefitGroupRate selectedBenefitGroupRate;
    private List<BenefitGroupRate> benefitGroupRates;
    @ManagedProperty(value = "#{benefitGroupRateService}")
    private BenefitGroupRateService benefitGroupRateService;

    public BenefitGroup getSelectedBenefitGroup() {
        return selectedBenefitGroup;
    }

    public void setSelectedBenefitGroup(BenefitGroup selectedBenefitGroup) {
        this.selectedBenefitGroup = selectedBenefitGroup;
    }

    public void setBenefitGroupService(BenefitGroupService benefitGroupService) {
        this.benefitGroupService = benefitGroupService;
    }

    public BenefitGroupRate getSelectedBenefitGroupRate() {
        return selectedBenefitGroupRate;
    }

    public void setSelectedBenefitGroupRate(BenefitGroupRate selectedBenefitGroupRate) {
        this.selectedBenefitGroupRate = selectedBenefitGroupRate;
    }

    public List<BenefitGroupRate> getBenefitGroupRates() {
        return benefitGroupRates;
    }

    public void setBenefitGroupRates(List<BenefitGroupRate> benefitGroupRates) {
        this.benefitGroupRates = benefitGroupRates;
    }

    public void setBenefitGroupRateService(BenefitGroupRateService benefitGroupRateService) {
        this.benefitGroupRateService = benefitGroupRateService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            
            selectedBenefitGroup = benefitGroupService.getEntiyByPK(Long.parseLong(userId.substring(1)));
            benefitGroupRates = benefitGroupRateService.getAllDataByBenefitGroupId(selectedBenefitGroup.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/payroll/benefit_group_view.htm?faces-redirect=true";
    }

   public void doSelectBenefitGroupRate() {
        try {
            selectedBenefitGroupRate = benefitGroupRateService.getEntityByPKWithDetail(selectedBenefitGroupRate.getId());
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }

    public void doUpdateBenefitGroupRate() {

        List<String> benefitGroupRateId = new ArrayList<>();
        benefitGroupRateId.add(String.valueOf(selectedBenefitGroupRate.getId()));

        List<String> benefitGroupId = new ArrayList<>();
        benefitGroupId.add(String.valueOf(selectedBenefitGroup.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("benefitGroupRateId", benefitGroupRateId);
        dataToSend.put("benefitGroupId", benefitGroupId);
        showDialogBenefitGroupRate(dataToSend);

    }

    public void doAddBenefitGroupRate() {
        List<String> benefitGroupId = new ArrayList<>();
        benefitGroupId.add(String.valueOf(selectedBenefitGroup.getId()));

        Map<String, List<String>> dataToSend = new HashMap<>();
        dataToSend.put("benefitGroupId", benefitGroupId);
        showDialogBenefitGroupRate(dataToSend);
    }

    public void doDeleteBenefitGroupRate() {
        try {
            benefitGroupRateService.delete(selectedBenefitGroupRate);
            benefitGroupRates = benefitGroupRateService.getAllDataByBenefitGroupId(selectedBenefitGroupRate.getBenefitGroup().getId());
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete benefitGroupRate", ex);
        }
    }

    private void showDialogBenefitGroupRate(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 550);
        options.put("contentHeight", 300);
        RequestContext.getCurrentInstance().openDialog("benefit_group_rate", options, params);
    }

    public void onDialogReturnBenefitGroupRate(SelectEvent event) {
        try {
            benefitGroupRates = benefitGroupRateService.getAllDataByBenefitGroupId(selectedBenefitGroup.getId());
            super.onDialogReturn(event);
        } catch (Exception e) {
            LOGGER.error("Error", e);
        }
    }


    @PreDestroy
    public void cleanAndExit() {
        selectedBenefitGroup = null;
        benefitGroupService = null;
        selectedBenefitGroupRate = null;
        benefitGroupRates = null;
        benefitGroupRateService = null;
    }

}
