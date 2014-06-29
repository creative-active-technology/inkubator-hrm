package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Country;
import com.inkubator.hrm.service.CountryService;
import com.inkubator.hrm.web.lazymodel.CountryLazyDataModel;
import com.inkubator.hrm.web.search.CountrySearchParameter;
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
@ManagedBean(name = "countryViewController")
@ViewScoped
public class CountryViewController extends BaseController {

    private CountrySearchParameter searchParameter;
    private LazyDataModel<Country> lazyDataCountry;
    private Country selectedCountry;
    @ManagedProperty(value = "#{countryService}")
    private CountryService countryService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new CountrySearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        countryService = null;
        searchParameter = null;
        lazyDataCountry = null;
        selectedCountry = null;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    public CountrySearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(CountrySearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<Country> getLazyDataCountry() {
        if (lazyDataCountry == null) {
            lazyDataCountry = new CountryLazyDataModel(searchParameter, countryService);
        }
        return lazyDataCountry;
    }

    public void setLazyDataCountry(LazyDataModel<Country> lazyDataCountry) {
        this.lazyDataCountry = lazyDataCountry;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public void doSearch() {
        lazyDataCountry = null;
    }

    public void doDetail() {
        try {
            selectedCountry = this.countryService.getEntiyByPK(selectedCountry.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            countryService.delete(selectedCountry);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete country ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete country", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedCountry.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 475);
        RequestContext.getCurrentInstance().openDialog("country_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }

    
}
