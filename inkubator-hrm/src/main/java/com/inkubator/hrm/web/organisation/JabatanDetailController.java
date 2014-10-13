/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.Faculty;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.entity.JabatanDeskripsi;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.Major;
import com.inkubator.hrm.entity.OccupationType;
import com.inkubator.hrm.service.EducationLevelService;
import com.inkubator.hrm.service.FacultyService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.service.MajorService;
import com.inkubator.hrm.service.OccupationTypeService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "jabatanDetailController")
@ViewScoped
public class JabatanDetailController extends BaseController {

    private Jabatan selectedJabatan;
    @ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    @ManagedProperty(value = "#{educationLevelService}")
    private EducationLevelService educationLevelService;
    @ManagedProperty(value = "#{occupationTypeService}")
    private OccupationTypeService occupationTypeService;
    @ManagedProperty(value = "#{majorService}")
    private MajorService majorService;
    @ManagedProperty(value = "#{facultyService}")
    private FacultyService facultyService;
    private List<JabatanSpesifikasi> listJabatanSpesifikasi;
    private List<JabatanDeskripsi> jabatanDeskripsis;
    private DualListModel<EducationLevel> dualListModel = new DualListModel<>();
    private List<EducationLevel> source = new ArrayList<>();
    private DualListModel<OccupationType> occupationDualListModel = new DualListModel<>();
    private List<OccupationType> occupationSource = new ArrayList<>();
    private DualListModel<Major> majorDualListModel = new DualListModel<>();
    private List<Major> majorSource = new ArrayList<>();
    private DualListModel<Faculty> facultyDualListModel = new DualListModel<>();
    private List<Faculty> facultySource = new ArrayList<>();

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String userId = FacesUtil.getRequestParameter("execution");
            selectedJabatan = jabatanService.getByIdWithJobDeskripsi(Long.parseLong(userId.substring(1)));
            jabatanDeskripsis = new ArrayList<>(selectedJabatan.getJabatanDeskripsis());
            listJabatanSpesifikasi = new ArrayList<>(selectedJabatan.getJabatanSpesifikasis());
            //source
            source = educationLevelService.getAllData();
            occupationSource = occupationTypeService.getAllData();
            majorSource = majorService.getAllData();
            facultySource = facultyService.getAllData();
            //dual list
            dualListModel.setSource(source);
            occupationDualListModel.setSource(occupationSource);
            majorDualListModel.setSource(majorSource);
            facultyDualListModel.setSource(facultySource);
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    public String doBack() {
        return "/protected/organisation/job_title_view.htm?faces-redirect=true";
    }

    public String doEdit() {
        return "/protected/organisation/job_title_form.htm?faces-redirect=true&execution=e" + selectedJabatan.getId();
    }

    @PreDestroy
    public void cleanAndExit() {
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

    public List<JabatanSpesifikasi> getListJabatanSpesifikasi() {
        return listJabatanSpesifikasi;
    }

    public void setListJabatanSpesifikasi(List<JabatanSpesifikasi> listJabatanSpesifikasi) {
        this.listJabatanSpesifikasi = listJabatanSpesifikasi;
    }

    public List<JabatanDeskripsi> getJabatanDeskripsis() {
        return jabatanDeskripsis;
    }

    public void setJabatanDeskripsis(List<JabatanDeskripsi> jabatanDeskripsis) {
        this.jabatanDeskripsis = jabatanDeskripsis;
    }

    public EducationLevelService getEducationLevelService() {
        return educationLevelService;
    }

    public void setEducationLevelService(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
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

    
}
