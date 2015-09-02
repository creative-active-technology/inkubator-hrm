package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PublicHoliday;
import com.inkubator.hrm.service.PublicHolidayService;
import com.inkubator.hrm.web.lazymodel.PublicHolidayLazyDataModel;
import com.inkubator.hrm.web.lazymodel.PublicHolidayReportLazyDataModel;
import com.inkubator.hrm.web.search.PublicHolidaySearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name = "publicHolidayViewController")
@ViewScoped
public class PublicHolidayViewController extends BaseController {

    private String parameter;
    private LazyDataModel<PublicHoliday> lazyDataPublicHoliday;
    private PublicHoliday selectedPublicHoliday;
    @ManagedProperty(value = "#{publicHolidayService}")
    private PublicHolidayService publicHolidayService;
    private PublicHolidaySearchParameter searchParameter;
    private List<PublicHoliday> listPublicHolidays;
    private LazyDataModel<PublicHoliday> lazyDataPublicHolidayReport;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PublicHolidaySearchParameter();
        listReportHistory();
    }

    @PreDestroy
    public void cleanAndExit() {
        publicHolidayService = null;
        parameter = null;
        lazyDataPublicHoliday = null;
        lazyDataPublicHolidayReport = null;
        searchParameter = null;
        selectedPublicHoliday = null;
    }

    public LazyDataModel<PublicHoliday> getLazyDataPublicHolidayReport() {
        if (lazyDataPublicHolidayReport == null) {
            lazyDataPublicHolidayReport = new PublicHolidayReportLazyDataModel(searchParameter, publicHolidayService);
        }
        return lazyDataPublicHolidayReport;
    }

    public void setLazyDataPublicHolidayReport(LazyDataModel<PublicHoliday> lazyDataPublicHolidayReport) {
        this.lazyDataPublicHolidayReport = lazyDataPublicHolidayReport;
    }

    public List<PublicHoliday> getListPublicHolidays() {
        return listPublicHolidays;
    }

    public void setListPublicHolidays(List<PublicHoliday> listPublicHolidays) {
        this.listPublicHolidays = listPublicHolidays;
    }

    public void setPublicHolidayService(PublicHolidayService publicHolidayService) {
        this.publicHolidayService = publicHolidayService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<PublicHoliday> getLazyDataPublicHoliday() {
        if (lazyDataPublicHoliday == null) {
            lazyDataPublicHoliday = new PublicHolidayLazyDataModel(parameter, publicHolidayService);
        }
        return lazyDataPublicHoliday;
    }

    public void setLazyDataPublicHoliday(LazyDataModel<PublicHoliday> lazyDataPublicHoliday) {
        this.lazyDataPublicHoliday = lazyDataPublicHoliday;
    }

    public PublicHoliday getSelectedPublicHoliday() {
        return selectedPublicHoliday;
    }

    public void setSelectedPublicHoliday(PublicHoliday selectedPublicHoliday) {
        this.selectedPublicHoliday = selectedPublicHoliday;
    }

    public void doSearch() {
        lazyDataPublicHoliday = null;
    }

    public void doSearchReport() {
        lazyDataPublicHolidayReport = null;
    }
    
    public void doDetail() {
        try {
            selectedPublicHoliday = this.publicHolidayService.getEntityByPKWithDetail(selectedPublicHoliday.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            publicHolidayService.delete(selectedPublicHoliday);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete publicHoliday ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete publicHoliday", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedPublicHoliday.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 465);
        RequestContext.getCurrentInstance().openDialog("public_holiday_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    public PublicHolidaySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PublicHolidaySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public void listReportHistory() {
        try {
            listPublicHolidays = publicHolidayService.getReportHistoryByParam(searchParameter);
        } catch (Exception ex) {
            Logger.getLogger(PublicHolidayViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
