package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Nationality;
import com.inkubator.hrm.service.NationalityService;
import com.inkubator.hrm.web.lazymodel.NationalityLazyDataModel;
import com.inkubator.hrm.web.search.NationalitySearchParameter;
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
@ManagedBean(name = "nationalityViewController")
@ViewScoped
public class NationalityViewController extends BaseController {

    private NationalitySearchParameter searchParameter;
    private LazyDataModel<Nationality> lazyDataNationality;
    private Nationality selectedNationality;
    @ManagedProperty(value = "#{nationalityService}")
    private NationalityService nationalityService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new NationalitySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        nationalityService = null;
        searchParameter = null;
        lazyDataNationality = null;
        selectedNationality = null;
    }

    public void setNationalityService(NationalityService nationalityService) {
        this.nationalityService = nationalityService;
    }

    public NationalitySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(NationalitySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Nationality> getLazyDataNationality() {
        if (lazyDataNationality == null) {
            lazyDataNationality = new NationalityLazyDataModel(searchParameter, nationalityService);
        }
        return lazyDataNationality;
    }

    public void setLazyDataNationality(LazyDataModel<Nationality> lazyDataNationality) {
        this.lazyDataNationality = lazyDataNationality;
    }

    public Nationality getSelectedNationality() {
        return selectedNationality;
    }

    public void setSelectedNationality(Nationality selectedNationality) {
        this.selectedNationality = selectedNationality;
    }

    public void doSearch() {
        lazyDataNationality = null;
    }

    public void doDetail() {
        try {
            selectedNationality = this.nationalityService.getEntiyByPK(selectedNationality.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            nationalityService.delete(selectedNationality);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete nationality ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete nationality", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedNationality.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth",460);
        options.put("contentHeight", 400);
        RequestContext.getCurrentInstance().openDialog("nationality_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
