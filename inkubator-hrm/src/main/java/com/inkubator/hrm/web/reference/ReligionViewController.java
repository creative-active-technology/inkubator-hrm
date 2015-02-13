package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Religion;
import com.inkubator.hrm.service.ReligionService;
import com.inkubator.hrm.web.lazymodel.ReligionLazyDataModel;
import com.inkubator.hrm.web.search.ReligionSearchParameter;
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
@ManagedBean(name = "religionViewController")
@ViewScoped
public class ReligionViewController extends BaseController {

    private ReligionSearchParameter parameter;
    private LazyDataModel<Religion> lazyDataReligion;
    private Religion selectedReligion;
    @ManagedProperty(value = "#{religionService}")
    private ReligionService religionService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new ReligionSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        religionService = null;
        parameter = null;
        lazyDataReligion = null;
        selectedReligion = null;
    }

    public void setReligionService(ReligionService religionService) {
        this.religionService = religionService;
    }

    public ReligionSearchParameter getParameter() {
        return parameter;
    }

    public void setParameter(ReligionSearchParameter parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<Religion> getLazyDataReligion() {
        if (lazyDataReligion == null) {
            lazyDataReligion = new ReligionLazyDataModel(parameter, religionService);
        }
        return lazyDataReligion;
    }

    public void setLazyDataReligion(LazyDataModel<Religion> lazyDataReligion) {
        this.lazyDataReligion = lazyDataReligion;
    }

    public Religion getSelectedReligion() {
        return selectedReligion;
    }

    public void setSelectedReligion(Religion selectedReligion) {
        this.selectedReligion = selectedReligion;
    }

    public void doSearch() {
        lazyDataReligion = null;
    }

    public void doDetail() {
        try {
            selectedReligion = this.religionService.getEntiyByPK(selectedReligion.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            religionService.delete(selectedReligion);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete religion ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete religion", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedReligion.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 430);
        options.put("contentHeight", 330);
        RequestContext.getCurrentInstance().openDialog("religion_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
