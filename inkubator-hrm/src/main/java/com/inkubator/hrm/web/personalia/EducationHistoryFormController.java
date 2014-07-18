/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.EducationHistory;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.EducationHistoryService;
import com.inkubator.hrm.web.model.EducationHistoryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "educationHistoryFormController")
@ViewScoped
public class EducationHistoryFormController extends BaseController{
    @ManagedProperty(value = "#{educationHistoryService}")
    private EducationHistoryService service;
    private EducationHistory selected;
    private EducationHistoryModel model;
    private Boolean isEdit;
    
    @PreDestroy
    private void cleanAndExit() {
        model = null;
        service = null;
        selected = null;
        isEdit = null;

    }
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new EducationHistoryModel();
        try {
            if (param != null) {
                isEdit = Boolean.TRUE;
                EducationHistory educationHistory = service.getAllDataByPK(Long.parseLong(param));
                model.setId(educationHistory.getId());
                model.setBiodataId(educationHistory.getBiodata().getId());
                model.setEducationLevelId(educationHistory.getEducationLevel().getId());
                model.setInstitutionEducationId(educationHistory.getInstitutionEducation().getId());
                model.setFacultyId(educationHistory.getFaculty().getId());
                model.setMajorId(educationHistory.getMajor().getId());
                model.setCertificateNumber(educationHistory.getCertificateNumber());
                model.setScore(educationHistory.getScore());
            } else {
                isEdit = Boolean.FALSE;
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        EducationHistory educationHistory = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                service.update(educationHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                service.save(educationHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.SAVE_CONDITION);
            }
            cleanAndExit();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
            //cleanAndExit();

//        cleanAndExit();
    }
    
    private EducationHistory getEntityFromViewModel(EducationHistoryModel model) {
        EducationHistory educationHistory = new EducationHistory();
        if (model.getId() != null) {
            educationHistory.setId(model.getId());
        }
        educationHistory.setBiodata(new BioData(model.getBiodataId()));
        educationHistory.setEducationLevel(new EducationLevel(model.getBiodataId()));
        educationHistory.setInstitutionEducation(new InstitutionEducation(model.getInstitutionEducationId()));
        educationHistory.setFaculty(new Faculty(model.getFacultyId()));
        educationHistory.setMajor(new Major(model.getMajorId()));
        educationHistory.setCertificateNumber(model.getCertificateNumber());
        educationHistory.setScore(model.getScore());
        return educationHistory;
    }
    
    
}
