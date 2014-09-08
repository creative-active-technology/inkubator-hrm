package com.inkubator.hrm.web.payroll;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.service.BenefitGroupService;
import com.inkubator.hrm.web.lazymodel.BenefitGroupLazyDataModel;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;
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
 * @author Taufik Hidayat
 */
@ManagedBean(name = "benefitGroupViewController")
@ViewScoped
public class BenefitGroupViewController extends BaseController {

    private BenefitGroupSearchParameter searchParameter;
    private LazyDataModel<BenefitGroup> lazyDataBenefitGroup;
    private BenefitGroup selectedBenefitGroup;
    @ManagedProperty(value = "#{benefitGroupService}")
    private BenefitGroupService benefitGroupService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new BenefitGroupSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        benefitGroupService = null;
        searchParameter = null;
        lazyDataBenefitGroup = null;
        selectedBenefitGroup = null;
    }

    public void setBenefitGroupService(BenefitGroupService benefitGroupService) {
        this.benefitGroupService = benefitGroupService;
    }

    public BenefitGroupSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(BenefitGroupSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<BenefitGroup> getLazyDataBenefitGroup() {
        if (lazyDataBenefitGroup == null) {
            lazyDataBenefitGroup = new BenefitGroupLazyDataModel(searchParameter, benefitGroupService);
        }
        return lazyDataBenefitGroup;
    }

    public void setLazyDataBenefitGroup(LazyDataModel<BenefitGroup> lazyDataBenefitGroup) {
        this.lazyDataBenefitGroup = lazyDataBenefitGroup;
    }

    public BenefitGroup getSelectedBenefitGroup() {
        return selectedBenefitGroup;
    }

    public void setSelectedBenefitGroup(BenefitGroup selectedBenefitGroup) {
        this.selectedBenefitGroup = selectedBenefitGroup;
    }

    public void doSearch() {
        lazyDataBenefitGroup = null;
    }

    public String doDetail() {

        return "/protected/payroll/benefit_group_detail.htm?faces-redirect=true&execution=e" + selectedBenefitGroup.getId();

    }
    
     public void doDetailDelete() {
        try {
            selectedBenefitGroup = this.benefitGroupService.getEntiyByPK(selectedBenefitGroup.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            benefitGroupService.delete(selectedBenefitGroup);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete benefitGroup ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete benefitGroup", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedBenefitGroup.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 530);
        options.put("contentHeight", 420);
        RequestContext.getCurrentInstance().openDialog("benefit_group_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
