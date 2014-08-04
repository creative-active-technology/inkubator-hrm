/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.personalia;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.entity.BioEducationHistory;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.InstitutionEducation;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.service.EducationHistoryService;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.service.InstitutionEducationService;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.util.MapUtil;
import com.inkubator.hrm.web.model.EducationHistoryModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    private EducationHistoryService educationHistoryService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    @ManagedProperty(value = "#{institutionEducationService}")
    private InstitutionEducationService institutionEducationService;
    @ManagedProperty(value = "#{facultyService}")
    private FacultyService facultyService;
    @ManagedProperty(value = "#{majorService}")
    private MajorService majorService;
    
    private Long bioDataId;
    private BioEducationHistory selected;
    private EducationHistoryModel model;
    private Boolean isEdit;
    
    //List Dropdown
    
    private Map<String, Long> listEducationLevels = new TreeMap<String, Long>();;
    private List<EducationLevel> listEducationLevel = new ArrayList<>();
    
    private Map<String, Long> listInstitutionEducations = new TreeMap<String, Long>();;
    private List<InstitutionEducation> listInstitutionsEducation = new ArrayList<>();
    
    private Map<String, Long> listFaculties = new TreeMap<String, Long>();;
    private List<Faculty> listFaculty = new ArrayList<>();
    
    private Map<String, Long> listMajors = new TreeMap<String, Long>();;
    private List<Major> listMajor = new ArrayList<>();
    
    
    @PreDestroy
    private void cleanAndExit() {
        model = null;
        educationHistoryService = null;
        selected = null;
        isEdit = null;
        educationLevelService = null;
        institutionEducationService = null;
        facultyService = null;
        majorService = null;
        listEducationLevel = null;
        listEducationLevels = null;
        listInstitutionEducations = null;
        listInstitutionsEducation = null;
        listFaculties = null;
        listFaculty = null;
        listMajor = null;
        listMajors = null;
        bioDataId = null;
    }
    
    @PostConstruct
    @Override
    public void initialization() {
        System.out.println("init");
        super.initialization();
        String param = FacesUtil.getRequestParameter("param");
        model = new EducationHistoryModel();
        
        try {
            if(param.contains("i")){
                bioDataId = Long.parseLong(param.substring(1));
                isEdit = Boolean.FALSE;
            }
            if (param.contains("e")) {
                isEdit = Boolean.TRUE;
                long educationId = Long.parseLong(param.substring(1));
                BioEducationHistory educationHistory = educationHistoryService.getAllDataByPK(educationId);
                model.setId(educationHistory.getId());
                model.setBiodataId(educationHistory.getBiodata().getId());
                model.setEducationLevelId(educationHistory.getEducationLevel().getId());
                model.setInstitutionEducationId(educationHistory.getInstitutionEducation().getId());
                model.setFacultyId(educationHistory.getFaculty().getId());
                model.setMajorId(educationHistory.getMajor().getId());
                model.setCertificateNumber(educationHistory.getCertificateNumber());
                model.setScore(educationHistory.getScore());
                bioDataId = educationHistory.getBiodata().getId();
            } else {
                isEdit = Boolean.FALSE;
            }
            listDrowDown();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void listDrowDown() throws Exception{
        //Education Level
        listEducationLevel = educationLevelService.getAllData();
        for (EducationLevel educationLevel : listEducationLevel) {
            listEducationLevels.put(educationLevel.getName(), educationLevel.getId());
        }
        //Institution Education
        listInstitutionsEducation = institutionEducationService.getAllData();
        for (InstitutionEducation institutionEducation : listInstitutionsEducation) {
            listInstitutionEducations.put(institutionEducation.getInstitutionEducationName(), institutionEducation.getId());
        }
        //Faculty
        listFaculty = facultyService.getAllData();
        for (Faculty faculty : listFaculty) {
            listFaculties.put(faculty.getFacultyName(), faculty.getId());
        }
        //Major
        listMajor = majorService.getAllData();
        for (Major major : listMajor) {
            listMajors.put(major.getMajorName(), major.getId());
        }
        MapUtil.sortByValue(listEducationLevels);
        MapUtil.sortByValue(listInstitutionEducations);
        MapUtil.sortByValue(listFaculties);
        MapUtil.sortByValue(listMajors);
    }
    
    public void doSave() {
        System.out.println("masuk dosave");
        BioEducationHistory educationHistory = getEntityFromViewModel(model);
        try {
            if (isEdit) {
                educationHistoryService.update(educationHistory);
                RequestContext.getCurrentInstance().closeDialog(HRMConstant.UPDATE_CONDITION);
            } else {
                educationHistoryService.save(educationHistory);
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
    
    private BioEducationHistory getEntityFromViewModel(EducationHistoryModel model) {
        BioEducationHistory educationHistory = new BioEducationHistory();
        if (model.getId() != null) {
            educationHistory.setId(model.getId());
        }
        educationHistory.setBiodata(new BioData(bioDataId));
        educationHistory.setEducationLevel(new EducationLevel(model.getEducationLevelId()));
        educationHistory.setInstitutionEducation(new InstitutionEducation(model.getInstitutionEducationId()));
        educationHistory.setFaculty(new Faculty(model.getFacultyId()));
        educationHistory.setMajor(new Major(model.getMajorId()));
        educationHistory.setCertificateNumber(model.getCertificateNumber());
        educationHistory.setScore(model.getScore());
        return educationHistory;
    }

    public EducationHistoryService getEducationHistoryService() {
        return educationHistoryService;
    }

    public void setEducationHistoryService(EducationHistoryService educationHistoryService) {
        this.educationHistoryService = educationHistoryService;
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public InstitutionEducationService getInstitutionEducationService() {
        return institutionEducationService;
    }

    public void setInstitutionEducationService(InstitutionEducationService institutionEducationService) {
        this.institutionEducationService = institutionEducationService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public MajorService getMajorService() {
        return majorService;
    }

    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    public BioEducationHistory getSelected() {
        return selected;
    }

    public void setSelected(BioEducationHistory selected) {
        this.selected = selected;
    }

    public EducationHistoryModel getModel() {
        return model;
    }

    public void setModel(EducationHistoryModel model) {
        this.model = model;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public Map<String, Long> getListEducationLevels() {
        return listEducationLevels;
    }

    public void setListEducationLevels(Map<String, Long> listEducationLevels) {
        this.listEducationLevels = listEducationLevels;
    }

    public List<EducationLevel> getListEducationLevel() {
        return listEducationLevel;
    }

    public void setListEducationLevel(List<EducationLevel> listEducationLevel) {
        this.listEducationLevel = listEducationLevel;
    }

    public Map<String, Long> getListInstitutionEducations() {
        return listInstitutionEducations;
    }

    public void setListInstitutionEducations(Map<String, Long> listInstitutionEducations) {
        this.listInstitutionEducations = listInstitutionEducations;
    }

    public List<InstitutionEducation> getListInstitutionsEducation() {
        return listInstitutionsEducation;
    }

    public void setListInstitutionsEducation(List<InstitutionEducation> listInstitutionsEducation) {
        this.listInstitutionsEducation = listInstitutionsEducation;
    }

    public Map<String, Long> getListFaculties() {
        return listFaculties;
    }

    public void setListFaculties(Map<String, Long> listFaculties) {
        this.listFaculties = listFaculties;
    }

    public List<Faculty> getListFaculty() {
        return listFaculty;
    }

    public void setListFaculty(List<Faculty> listFaculty) {
        this.listFaculty = listFaculty;
    }

    public Map<String, Long> getListMajors() {
        return listMajors;
    }

    public void setListMajors(Map<String, Long> listMajors) {
        this.listMajors = listMajors;
    }

    public List<Major> getListMajor() {
        return listMajor;
    }

    public void setListMajor(List<Major> listMajor) {
        this.listMajor = listMajor;
    }
    
    
}
