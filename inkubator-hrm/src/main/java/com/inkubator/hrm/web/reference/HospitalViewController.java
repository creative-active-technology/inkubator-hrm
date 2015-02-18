package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Hospital;
import com.inkubator.hrm.service.HospitalService;
import com.inkubator.hrm.web.lazymodel.HospitalLazyDataModel;
import com.inkubator.hrm.web.search.HospitalSearchParameter;
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
@ManagedBean(name = "hospitalViewController")
@ViewScoped
public class HospitalViewController extends BaseController {

    private HospitalSearchParameter searchParameter;
    private LazyDataModel<Hospital> lazyDataHospital;
    private Hospital selectedHospital;
    @ManagedProperty(value = "#{hospitalService}")
    private HospitalService hospitalService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new HospitalSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        hospitalService = null;
        searchParameter = null;
        lazyDataHospital = null;
        selectedHospital = null;
    }

    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    public HospitalSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(HospitalSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    

    public LazyDataModel<Hospital> getLazyDataHospital() {
        if (lazyDataHospital == null) {
            lazyDataHospital = new HospitalLazyDataModel(searchParameter, hospitalService);
        }
        return lazyDataHospital;
    }

    public void setLazyDataHospital(LazyDataModel<Hospital> lazyDataHospital) {
        this.lazyDataHospital = lazyDataHospital;
    }

    public Hospital getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(Hospital selectedHospital) {
        this.selectedHospital = selectedHospital;
    }

    public void doSearch() {
        lazyDataHospital = null;
    }

    public void doDetail() {
        try {
            selectedHospital = this.hospitalService.getEntityByPKWithDetail(selectedHospital.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            hospitalService.delete(selectedHospital);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete hospital ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete hospital", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedHospital.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 450);
        options.put("contentHeight", 500);
        RequestContext.getCurrentInstance().openDialog("hospital_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
