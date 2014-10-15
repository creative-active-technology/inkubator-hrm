/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanEdukasi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.service.JabatanEdukasiService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.JabatanSpesifikasiService;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "jabatanSpesifikasiDetailController")
@ViewScoped
public class JabatanSpesifikasiDetailController extends BaseController{
    private Jabatan selectedJabatan;
    private JabatanSpesifikasi selectedJobSpec;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{jabatanSpesifikasiService}")
    private JabatanSpesifikasiService jabatanSpecService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;
    @ManagedProperty(value = "#{majorService}")
    private MajorService majorService;
    @ManagedProperty(value = "#{facultyService}")
    private FacultyService facultyService;
    @ManagedProperty(value = "#{jabatanEdukasiService}")
    private JabatanEdukasiService jabatanEdukasiService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private String userId;
    private DualListModel<EducationLevel> dualListModel = new DualListModel<>();
    private List<EducationLevel> source = new ArrayList<>();
    private DualListModel<OccupationType> occupationDualListModel = new DualListModel<>();
    private List<OccupationType> occupationSource = new ArrayList<>();
    private DualListModel<Major> majorDualListModel = new DualListModel<>();
    private List<Major> majorSource = new ArrayList<>();
    private DualListModel<Faculty> facultyDualListModel = new DualListModel<>();
    private List<Faculty> facultySource = new ArrayList<>();
    private Boolean isDataEmpty;

    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            userId = FacesUtil.getRequestParameter("execution");
            
            selectedJabatan = jabatanService.getJabatanByIdWithDetail(Long.parseLong(userId.substring(1)));
            listJabatanSpesifikasi = jabatanSpecService.getAllDataByJabatanId(selectedJabatan.getId());
            
            //source
            source = educationLevelService.getAllData();
            occupationSource = occupationTypeService.getAllData();
            majorSource = majorService.getAllData();
            facultySource = facultyService.getAllData();
            
            /* 
             * update or view
            */
            
            //education level
            EducationLevel educationLevel = educationLevelService.getEntityByPkWithDetail(Long.parseLong(userId.substring(1)));
            List<EducationLevel> educationData = educationLevel.getListEducationLevels();
            isDataEmpty = Boolean.TRUE;
            if(educationData.isEmpty()){
                dualListModel.setSource(source);
            }else{
                isDataEmpty = Boolean.FALSE;
                source.removeAll(educationData);
                dualListModel = new DualListModel<>(source, educationData);
            }
            
            //occupation
            OccupationType occupationType = occupationTypeService.getEntityByPkWithDetail(Long.parseLong(userId.substring(1)));
            List<OccupationType> occupationTypeData = occupationType.getListOccupationTypes();
            if(occupationTypeData.isEmpty()){
                occupationDualListModel.setSource(occupationSource);
            }else{
                isDataEmpty = Boolean.FALSE;
                occupationSource.removeAll(occupationTypeData);
                occupationDualListModel = new DualListModel<>(occupationSource, occupationTypeData);
            }
            
            //major
            Major major = majorService.getEntityByPkWithDetail(Long.parseLong(userId.substring(1)));
            List<Major> majorData = major.getListMajors();
            if(majorData.isEmpty()){
                majorDualListModel.setSource(majorSource);
            }else{
                isDataEmpty = Boolean.FALSE;
                majorSource.removeAll(majorData);
                majorDualListModel = new DualListModel<>(majorSource, majorData);
            }
            //faculty
            Faculty faculty = facultyService.getEntityByPkWithDetail(Long.parseLong(userId.substring(1)));
            List<Faculty> facultyData = faculty.getListFaculties();
            if(educationData.isEmpty()){
                facultyDualListModel.setSource(facultySource);
            }else{
                isDataEmpty = Boolean.FALSE;
                facultySource.removeAll(facultyData);
                facultyDualListModel = new DualListModel<>(facultySource, facultyData);
            }
            
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    @PreDestroy
    public void cleanAndExit() {
        isDataEmpty = null;
        jabatanEdukasiService = null;
        facultyDualListModel = null;
        facultySource = null;
        facultyService = null;
        majorDualListModel = null;
        majorSource = null;
        majorService = null;
        occupationDualListModel = null;
        occupationSource = null;
        dualListModel = null;
        selectedJabatan = null;
        jabatanService = null;
        listJabatanSpesifikasi = null;
        source = null;
        educationLevelService = null;
        occupationTypeService = null;
        majorService = null;
    }
    
    public JabatanSpesifikasiService getJabatanSpecService() {
        return jabatanSpecService;
    }

    public void setJabatanSpecService(JabatanSpesifikasiService jabatanSpecService) {
        this.jabatanSpecService = jabatanSpecService;
    }

    
    public JabatanSpesifikasi getSelectedJobSpec() {
        return selectedJobSpec;
    }

    public void setSelectedJobSpec(JabatanSpesifikasi selectedJobSpec) {
        this.selectedJobSpec = selectedJobSpec;
    }

    
    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }
    
    

    public String doBack() {
        return "/protected/organisation/job_spesifikasi_view.htm?faces-redirect=true";
    }

    
    public void doAdd() throws Exception{
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        List<String> dataIsi = new ArrayList<>();
        Map<String, List<String>> dataToSend = new HashMap<>();
        dataIsi.add("i" + String.valueOf(selectedJabatan.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_spesifikasi_form", options, dataToSend);
    }

    public void doEdit() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", false);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 400);
        options.put("contentHeight", 250);
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> dataIsi = new ArrayList<>();
        dataIsi.add("e" + String.valueOf(selectedJobSpec.getId()));
        dataToSend.put("param", dataIsi);
        RequestContext.getCurrentInstance().openDialog("job_spesifikasi_form", options, dataToSend);
    }
    
    public void doDelete() {
        try {
            this.jabatanSpecService.delete(selectedJobSpec);
            listJabatanSpesifikasi = jabatanSpecService.getAllDataByJabatanId(Long.parseLong(userId.substring(1)));
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error", ex);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public void onDelete() {
        try {
            selectedJobSpec = this.jabatanSpecService.getEntiyByPK(selectedJobSpec.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    @Override
    public void onDialogReturn(SelectEvent event)  {
        try {
            listJabatanSpesifikasi = jabatanSpecService.getAllDataByJabatanId(Long.parseLong(userId.substring(1)));
            super.onDialogReturn(event);
        } catch (Exception ex) {
            Logger.getLogger(JabatanSpesifikasiDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }
    
    public void doSave() throws Exception{
        List<EducationLevel> getEducationLevel = dualListModel.getTarget();
        List<Major> getMajorLevel = majorDualListModel.getTarget();
        List<Faculty> getFaculty = facultyDualListModel.getTarget();
        List<OccupationType> getOccupation = occupationDualListModel.getTarget();
        if(isDataEmpty == Boolean.FALSE){
            this.jabatanSpecService.update(Long.parseLong(userId.substring(1)), getEducationLevel, getMajorLevel, getFaculty, getOccupation);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }else{
            this.jabatanSpecService.save(Long.parseLong(userId.substring(1)), getEducationLevel, getMajorLevel, getFaculty, getOccupation);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        }
    }
    
    public void doDetail() {
        try {
            selectedJobSpec = this.jabatanSpecService.getDataByPK(selectedJobSpec.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    public OccupationTypeService getOccupationTypeService() {
        return occupationTypeService;
    }

    public void setOccupationTypeService(OccupationTypeService occupationTypeService) {
        this.occupationTypeService = occupationTypeService;
    }

    public MajorService getMajorService() {
        return majorService;
    }

    public void setMajorService(MajorService majorService) {
        this.majorService = majorService;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public DualListModel<EducationLevel> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<EducationLevel> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public List<EducationLevel> getSource() {
        return source;
    }

    public void setSource(List<EducationLevel> source) {
        this.source = source;
    }

    public DualListModel<OccupationType> getOccupationDualListModel() {
        return occupationDualListModel;
    }

    public void setOccupationDualListModel(DualListModel<OccupationType> occupationDualListModel) {
        this.occupationDualListModel = occupationDualListModel;
    }

    public List<OccupationType> getOccupationSource() {
        return occupationSource;
    }

    public void setOccupationSource(List<OccupationType> occupationSource) {
        this.occupationSource = occupationSource;
    }

    public DualListModel<Major> getMajorDualListModel() {
        return majorDualListModel;
    }

    public void setMajorDualListModel(DualListModel<Major> majorDualListModel) {
        this.majorDualListModel = majorDualListModel;
    }

    public List<Major> getMajorSource() {
        return majorSource;
    }

    public void setMajorSource(List<Major> majorSource) {
        this.majorSource = majorSource;
    }

    public DualListModel<Faculty> getFacultyDualListModel() {
        return facultyDualListModel;
    }

    public void setFacultyDualListModel(DualListModel<Faculty> facultyDualListModel) {
        this.facultyDualListModel = facultyDualListModel;
    }

    public List<Faculty> getFacultySource() {
        return facultySource;
    }

    public void setFacultySource(List<Faculty> facultySource) {
        this.facultySource = facultySource;
    }

    public JabatanEdukasiService getJabatanEdukasiService() {
        return jabatanEdukasiService;
    }

    public void setJabatanEdukasiService(JabatanEdukasiService jabatanEdukasiService) {
        this.jabatanEdukasiService = jabatanEdukasiService;
    }
    
    
}
