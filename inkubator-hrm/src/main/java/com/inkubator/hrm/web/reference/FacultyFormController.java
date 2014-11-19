package com.inkubator.hrm.web.reference;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.web.model.FacultyModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Taufik Hidayat
 */
@ManagedBean(name = "facultyFormController")
@ViewScoped
public class FacultyFormController extends BaseController {

    private FacultyModel facultyModel;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{facultyService}")
    private FacultyService facultyService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        facultyModel = new FacultyModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNumeric(param)) {
            try {
                Faculty faculty = facultyService.getEntiyByPK(Long.parseLong(param));
                if (faculty != null) {
                    facultyModel.setId(faculty.getId());
                    facultyModel.setFacultyName(faculty.getFacultyName());
                    facultyModel.setDescription(faculty.getDescription());
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        facultyService = null;
        facultyModel = null;
        isUpdate = null;
    }

    public FacultyModel getFacultyModel() {
        return facultyModel;
    }

    public void setFacultyModel(FacultyModel facultyModel) {
        this.facultyModel = facultyModel;
    }

    public Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public void doSave() {
        Faculty faculty = getEntityFromViewModel(facultyModel);
        try {
            if (isUpdate) {
                facultyService.update(faculty);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                facultyService.save(faculty);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { 
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    private Faculty getEntityFromViewModel(FacultyModel facultyModel) {
        Faculty faculty = new Faculty();
        if (facultyModel.getId() != null) {
            faculty.setId(facultyModel.getId());
        }
        faculty.setFacultyName(facultyModel.getFacultyName());
        faculty.setDescription(facultyModel.getDescription());
        return faculty;
    }
}
