package com.inkubator.hrm.web.reference;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.web.lazymodel.FacultyLazyDataModel;
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
@ManagedBean(name = "facultyViewController")
@ViewScoped
public class FacultyViewController extends BaseController {

    private String parameter;
    private LazyDataModel<Faculty> lazyDataFaculty;
    private Faculty selectedFaculty;
    @ManagedProperty(value = "#{facultyService}")
    private FacultyService facultyService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
    }

    @PreDestroy
    public void cleanAndExit() {
        facultyService = null;
        parameter = null;
        lazyDataFaculty = null;
        selectedFaculty = null;
    }

    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LazyDataModel<Faculty> getLazyDataFaculty() {
        if (lazyDataFaculty == null) {
            lazyDataFaculty = new FacultyLazyDataModel(parameter, facultyService);
        }
        return lazyDataFaculty;
    }

    public void setLazyDataFaculty(LazyDataModel<Faculty> lazyDataFaculty) {
        this.lazyDataFaculty = lazyDataFaculty;
    }

    public Faculty getSelectedFaculty() {
        return selectedFaculty;
    }

    public void setSelectedFaculty(Faculty selectedFaculty) {
        this.selectedFaculty = selectedFaculty;
    }

    public void doSearch() {
        lazyDataFaculty = null;
    }

    public void doDetail() {
        try {
            selectedFaculty = this.facultyService.getEntiyByPK(selectedFaculty.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            facultyService.delete(selectedFaculty);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete faculty ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete faculty", ex);
        }
    }

    public void doAdd() {
        showDialog(null);
    }

    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedFaculty.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }

    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 365);
        RequestContext.getCurrentInstance().openDialog("faculty_form", options, params);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
