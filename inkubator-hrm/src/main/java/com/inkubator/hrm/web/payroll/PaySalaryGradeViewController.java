/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.payroll;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.service.PaySalaryGradeService;
import com.inkubator.hrm.web.lazymodel.PaySalaryGradeLazyDataModel;
import com.inkubator.hrm.web.search.PaySalaryGradeSearchParameter;
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
 * @author Deni
 */
@ManagedBean(name = "paySalaryGradeController")
@ViewScoped
public class PaySalaryGradeViewController extends BaseController {

    @ManagedProperty(value = "#{paySalaryGradeService}")
    private PaySalaryGradeService service;
    private PaySalaryGradeSearchParameter searchParameter;
    private LazyDataModel<PaySalaryGrade> lazy;
    private PaySalaryGrade selected;
    private Boolean isRendered;
    private Boolean isRenderedTextField;
    private Boolean isRenderedNumberField;
    private List<Integer> dataToShow;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            searchParameter = new PaySalaryGradeSearchParameter();
            isRendered = Boolean.FALSE;
            isRenderedTextField = Boolean.TRUE;
            isRenderedNumberField = Boolean.FALSE;

        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazy = null;
        service = null;
        selected = null;
        isRendered = null;
        isRenderedNumberField = null;
        isRenderedTextField = null;
    }

    public void doSearch() {
        lazy = null;
    }

    public void doChangeInputNumberOrText() {
        searchParameter.setParameter(null);
        if (searchParameter.getKeyParam().equals("minSalary") || searchParameter.getKeyParam().equals("mediumSalary") || searchParameter.getKeyParam().equals("maxSalary")) {
            isRenderedNumberField = Boolean.TRUE;
            isRenderedTextField = Boolean.FALSE;

        } else {
            isRenderedNumberField = Boolean.FALSE;
            isRenderedTextField = Boolean.TRUE;

        }
    }

    public void doDelete() {
        try {
            this.service.delete(selected);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doAdd() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 350);
        //options.put("closable", false);
        //options.put("height", "auto");
        //options.put("contentHeight", 340);
        RequestContext.getCurrentInstance().openDialog("pay_salary_grade_form", options, null);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 350);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add(String.valueOf(selected.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("pay_salary_grade_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        lazy = null;
        super.onDialogReturn(event);
    }

    public void doSelectEntity() {
        try {
            selected = this.service.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public PaySalaryGradeService getService() {
        return service;
    }

    public void setService(PaySalaryGradeService service) {
        this.service = service;
    }

    public PaySalaryGradeSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PaySalaryGradeSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PaySalaryGrade> getLazy() {
        try {
            dataToShow = new ArrayList<>();
            Long totalData = service.getTotalData();
            for (int i = 1; i < totalData + 1; i++) {
                dataToShow.add(i);
            }
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }
        if (lazy == null) {
            lazy = new PaySalaryGradeLazyDataModel(searchParameter, service);
        }
        return lazy;

    }

    public void setLazy(LazyDataModel<PaySalaryGrade> lazy) {
        this.lazy = lazy;
    }

    public PaySalaryGrade getSelected() {
        return selected;
    }

    public void setSelected(PaySalaryGrade selected) {
        this.selected = selected;
    }

    public Boolean getIsRendered() {
        return isRendered;
    }

    public void setIsRendered(Boolean isRendered) {
        this.isRendered = isRendered;
    }

    public Boolean getIsRenderedTextField() {
        return isRenderedTextField;
    }

    public void setIsRenderedTextField(Boolean isRenderedTextField) {
        this.isRenderedTextField = isRenderedTextField;
    }

    public Boolean getIsRenderedNumberField() {
        return isRenderedNumberField;
    }

    public void setIsRenderedNumberField(Boolean isRenderedNumberField) {
        this.isRenderedNumberField = isRenderedNumberField;
    }

    public void doChangeLevel(PaySalaryGrade grade) {

        try {
            int newGrade = grade.getGradeSalary();
            Long idOldData = grade.getId();
            service.doChangerGradeSalary(newGrade, idOldData);
        } catch (Exception ex) {
            LOGGER.error(ex, ex);
        }

    }

    public List<Integer> getDataToShow() {
        return dataToShow;
    }

    public void setDataToShow(List<Integer> dataToShow) {
        this.dataToShow = dataToShow;
    }

}
