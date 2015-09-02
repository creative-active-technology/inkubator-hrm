package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PublicHolidayException;
import com.inkubator.hrm.service.PublicHolidayExceptionService;
import com.inkubator.hrm.web.lazymodel.PublicHolidayExceptionLazyDataModel;
import com.inkubator.hrm.web.search.PublicHolidayExceptionSearchParameter;
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
 * @author rizkykojek
 */
@ManagedBean(name = "publicHolidayExceptionViewController")
@ViewScoped
public class PublicHolidayExceptionViewController extends BaseController {

    private PublicHolidayExceptionSearchParameter searchParameter;
    private LazyDataModel<PublicHolidayException> lazyDataPublicHolidayException;
    private PublicHolidayException selectedPublicHolidayException;
    @ManagedProperty(value = "#{publicHolidayExceptionService}")
    private PublicHolidayExceptionService publicHolidayExceptionService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PublicHolidayExceptionSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        publicHolidayExceptionService = null;
        searchParameter = null;
        lazyDataPublicHolidayException = null;
        selectedPublicHolidayException = null;
    }

    public void setPublicHolidayExceptionService(PublicHolidayExceptionService publicHolidayExceptionService) {
        this.publicHolidayExceptionService = publicHolidayExceptionService;
    }

    public PublicHolidayExceptionSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PublicHolidayExceptionSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

   

   

    public LazyDataModel<PublicHolidayException> getLazyDataPublicHolidayException() {
        if (lazyDataPublicHolidayException == null) {
            lazyDataPublicHolidayException = new PublicHolidayExceptionLazyDataModel(searchParameter, publicHolidayExceptionService);
        }
        return lazyDataPublicHolidayException;
    }

    public void setLazyDataPublicHolidayException(LazyDataModel<PublicHolidayException> lazyDataPublicHolidayException) {
        this.lazyDataPublicHolidayException = lazyDataPublicHolidayException;
    }

    public PublicHolidayException getSelectedPublicHolidayException() {
        return selectedPublicHolidayException;
    }

    public void setSelectedPublicHolidayException(PublicHolidayException selectedPublicHolidayException) {
        this.selectedPublicHolidayException = selectedPublicHolidayException;
    }

    public void doSearch() {
        lazyDataPublicHolidayException = null;
    }

    public void doDetail() {
        try {
            selectedPublicHolidayException = this.publicHolidayExceptionService.getEntityByPKWithDetail(selectedPublicHolidayException.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            publicHolidayExceptionService.delete(selectedPublicHolidayException);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete publicHolidayException ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete publicHolidayException", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedPublicHolidayException.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 510);
        options.put("contentHeight", 430);
        RequestContext.getCurrentInstance().openDialog("public_holiday_ex_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
