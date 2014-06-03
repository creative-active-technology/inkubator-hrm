package com.inkubator.hrm.web.workingtime;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.WtWorkingHour;
import com.inkubator.hrm.service.WtWorkingHourService;
import com.inkubator.hrm.web.lazymodel.WorkingHourLazyDataModel;
import com.inkubator.hrm.web.search.WorkingHourSearchParameter;
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
 * @author rizkykojek
 */
@ManagedBean(name = "workingHourViewController")
@ViewScoped
public class WorkingHourViewController extends BaseController {

    private WorkingHourSearchParameter searchParameter;
    private LazyDataModel<WtWorkingHour> lazyDataWorkingHour;
    private WtWorkingHour selectedWorkingHour;
    @ManagedProperty(value = "#{wtWorkingHourService}")
    private WtWorkingHourService workingHourService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new WorkingHourSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        workingHourService = null;
        searchParameter = null;
        lazyDataWorkingHour = null;
        selectedWorkingHour = null;
    }

    public LazyDataModel<WtWorkingHour> getLazyDataWorkingHour() {
        if (lazyDataWorkingHour == null) {
            lazyDataWorkingHour = new WorkingHourLazyDataModel(searchParameter, workingHourService);
        }
        return lazyDataWorkingHour;
    }

    public void setLazyDataWorkingHour(LazyDataModel<WtWorkingHour> lazyDataWorkingHour) {
        this.lazyDataWorkingHour = lazyDataWorkingHour;
    }

    public WtWorkingHour getSelectedWorkingHour() {
        return selectedWorkingHour;
    }

    public void setSelectedWorkingHour(WtWorkingHour selectedWorkingHour) {
        this.selectedWorkingHour = selectedWorkingHour;
    }

    public void setWorkingHourService(WtWorkingHourService workingHourService) {
        this.workingHourService = workingHourService;
    }

    public WorkingHourSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(WorkingHourSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public void doSearch() {
        lazyDataWorkingHour = null;
    }

    public String doDetail() {
        return "/protected/working_time/working_hour_detail.htm?faces-redirect=true&execution=e" + selectedWorkingHour.getId();
    }

    public void doSelectEntity() {
        try {
            selectedWorkingHour = this.workingHourService.getEntiyByPK(selectedWorkingHour.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            workingHourService.delete(selectedWorkingHour);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete specificationAbility ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete specificationAbility", ex);
        }
    }

    public String doAdd() {
        return "/protected/working_time/working_hour_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/working_time/working_hour_form.htm?faces-redirect=true&execution=e" + selectedWorkingHour.getId();
    }

    public void onDialogClose(SelectEvent event) {
        //re-calculate searching
        doSearch();

        //show growl message
        String condition = (String) event.getObject();
        if (condition.equalsIgnoreCase(HRMConstant.SAVE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } else if (condition.equalsIgnoreCase(HRMConstant.UPDATE_CONDITION)) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
}
