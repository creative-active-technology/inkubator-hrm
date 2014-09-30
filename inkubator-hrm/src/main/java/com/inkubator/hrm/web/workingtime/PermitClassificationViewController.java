package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.web.reference.*;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.PermitClassification;
import com.inkubator.hrm.service.PermitClassificationService;
import com.inkubator.hrm.web.lazymodel.PermitClassificationLazyDataModel;
import com.inkubator.hrm.web.search.PermitClassificationSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "permitClassificationViewController")
@ViewScoped
public class PermitClassificationViewController extends BaseController {

    private PermitClassificationSearchParameter searchParameter;
    private LazyDataModel<PermitClassification> lazyDataPermitClassification;
    private PermitClassification selectedPermitClassification;
    @ManagedProperty(value = "#{permitClassificationService}")
    private PermitClassificationService permitClassificationService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new PermitClassificationSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        permitClassificationService = null;
        searchParameter = null;
        lazyDataPermitClassification = null;
        selectedPermitClassification = null;
    }

    public void setPermitClassificationService(PermitClassificationService permitClassificationService) {
        this.permitClassificationService = permitClassificationService;
    }

    public PermitClassificationSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(PermitClassificationSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<PermitClassification> getLazyDataPermitClassification() {
        if (lazyDataPermitClassification == null) {
            lazyDataPermitClassification = new PermitClassificationLazyDataModel(searchParameter, permitClassificationService);
        }
        return lazyDataPermitClassification;
    }

    public void setLazyDataPermitClassification(LazyDataModel<PermitClassification> lazyDataPermitClassification) {
        this.lazyDataPermitClassification = lazyDataPermitClassification;
    }

    public PermitClassification getSelectedPermitClassification() {
        return selectedPermitClassification;
    }

    public void setSelectedPermitClassification(PermitClassification selectedPermitClassification) {
        this.selectedPermitClassification = selectedPermitClassification;
    }

    public void doSearch() {
        lazyDataPermitClassification = null;
    }

    public String doDetail() {
//        emptyModel.getMarkers().clear();
//        try {
//            selectedPermitClassification = this.permitClassificationService.getPermitClassificationByIdWithDetail(selectedPermitClassification.getId());
//            LatLng coord = new LatLng(Double.parseDouble(selectedPermitClassification.getLatitude()), Double.parseDouble(selectedPermitClassification.getLongitude()));
//
//            //Basic marker
//            emptyModel.addOverlay(new Marker(coord, selectedPermitClassification.getPermitClassificationName()));
        return "/protected/working_time/permit_classification_detail.htm?faces-redirect=true&execution=e" + selectedPermitClassification.getId();
    }

    public void doDelete() {
        try {
            permitClassificationService.delete(selectedPermitClassification);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete permitClassification ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete permitClassification", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/permit_classification_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/permit_classification_form.htm?faces-redirect=true&execution=e" + selectedPermitClassification.getId();
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
