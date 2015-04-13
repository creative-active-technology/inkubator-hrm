/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import com.inkubator.common.util.RandomNumberUtil;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.hrm.web.model.AnnouncementModel;
import com.inkubator.hrm.web.model.AnnouncementModelJson;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "announcementFormController")
@ViewScoped
public class AnnouncementFormController extends BaseController {

    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    private AnnouncementModel model;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        model = new AnnouncementModel();
        try {
            //get list data
            List<Company> listCompany = companyService.getAllData();
            List<EmployeeType> listEmployeeType = employeeTypeService.getAllData();
            List<GolonganJabatan> listGolonganJabatan = golonganJabatanService.getAllData();
            List<UnitKerja> listUnitKerja = unitKerjaService.getAllData();

            //create object dual list model
            DualListModel<EmployeeType> dualListEmployeeType = new DualListModel<>();
            DualListModel<GolonganJabatan> dualListGolonganJabatan = new DualListModel<>();
            DualListModel<UnitKerja> dualListUnitKerja = new DualListModel<>();

            //set value for form
            Map<String, Long> dropDownCompany = new TreeMap<String, Long>();
            for (Company company : listCompany) {
                dropDownCompany.put(company.getName(), company.getId());
            }
            dualListEmployeeType.setSource(listEmployeeType);
            dualListGolonganJabatan.setSource(listGolonganJabatan);
            dualListUnitKerja.setSource(listUnitKerja);

            //insert value to model to show in form
            model.setDropDownCompany(dropDownCompany);
            model.setDualListEmployeeType(dualListEmployeeType);
            model.setDualListGolonganJabatan(dualListGolonganJabatan);
            model.setDualListUnitKerja(dualListUnitKerja);
            model.setIsUpdate(Boolean.FALSE);
            model.setNomor("TEMP-" + RandomNumberUtil.getRandomNumber(9));
        } catch (Exception ex) {
            Logger.getLogger(AnnouncementFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PreDestroy
    private void cleanAndExit() {
        companyService = null;
        employeeTypeService = null;
        golonganJabatanService = null;
        unitKerjaService = null;
        announcementService = null;
    }

    public void handingAttachmentUpload(FileUploadEvent fileUploadEvent) {
        UploadedFile attachmentFile = fileUploadEvent.getFile();
        model.setFotoFileName(attachmentFile.getFileName());
        model.setAttachmentFile(attachmentFile);
        System.out.println(model.getFotoFileName() + "wkwkwkwkwkwk");
    }

    public String doSaved() throws Exception {
        Announcement announcement = getEntityFromViewModel(model);
        AnnouncementModelJson announcementModelJson = getEntityForModelJson(model);
        try {
            if (model.getIsUpdate()) {
                announcementService.update(announcement);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
//                String message = announcementService.save(reimbursment, fotoFile, false);
                String message = announcementService.save(announcementModelJson, false);
                if (StringUtils.equals(message, "success_need_approval")) {
                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                } else {

                    MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                            FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                }
            }

            cleanAndExit();
            return "/protected/personalia/reimbursment_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private AnnouncementModelJson getEntityForModelJson(AnnouncementModel model) throws Exception {
        AnnouncementModelJson announcementModelJson = new AnnouncementModelJson();
        announcementModelJson.setCompanyId(model.getCompanyId());
        List<EmployeeType> listEmpTypeTarget = model.getDualListEmployeeType().getTarget();
        List<GolonganJabatan> listGolJabTarget = model.getDualListGolonganJabatan().getTarget();
        List<UnitKerja> listUnitKerjaTarget = model.getDualListUnitKerja().getTarget();
        List<Long> listEmpTypeId = new ArrayList<Long>();
        List<Long> listGolJabId = new ArrayList<Long>();
        List<Long> listUnitKerjaId = new ArrayList<Long>();
        List<String> listEmpTypeName = new ArrayList<String>();
        List<String> listGolJabName = new ArrayList<String>();
        List<String> listUnitKerjaName = new ArrayList<String>();
        for (UnitKerja unitKerja : listUnitKerjaTarget) {
            listUnitKerjaId.add(unitKerja.getId());
            listUnitKerjaName.add(unitKerja.getName());
        }
        for (GolonganJabatan golJab : listGolJabTarget) {
            listGolJabId.add(golJab.getId());
            listGolJabName.add(golJab.getCode());
        }
        for (EmployeeType employeeType : listEmpTypeTarget) {
            listEmpTypeId.add(employeeType.getId());
            listEmpTypeName.add(employeeType.getName());
        }
        announcementModelJson.setListEmployeeType(listEmpTypeId);
        announcementModelJson.setListGolonganJabatan(listGolJabId);
        announcementModelJson.setListUnitKerja(listUnitKerjaId);
        announcementModelJson.setListEmpTypeName(listEmpTypeName);
        announcementModelJson.setListGolJabName(listGolJabName);
        announcementModelJson.setListUnitKerjaName(listUnitKerjaName);
        announcementModelJson.setAnnouncementSubject(model.getSubject());
        announcementModelJson.setAnnouncementContent(model.getAnnouncementContent());
        announcementModelJson.setPublishTimeModel(model.getTimeModel());
        announcementModelJson.setPeriodStartDate(model.getPeriodeStartDate());
        announcementModelJson.setPeriodEndDate(model.getPeriodeEndDate());
        announcementModelJson.setViewModel(model.getViewModel());
        announcementModelJson.setIsInternetPublish(model.getInternetPublish());
        announcementModelJson.setAttachmentFileName(model.getFotoFileName());
        announcementModelJson.setNomor(model.getNomor());
        return announcementModelJson;
    }

    private Announcement getEntityFromViewModel(AnnouncementModel model) throws Exception {
        Announcement announcement = new Announcement();
        if (model.getId() != null) {
            announcement.setId(model.getId());
        }
        announcement.setAnnouncementContent(model.getAnnouncementContent());
        announcement.setCompany(new Company(model.getCompanyId()));
        announcement.setSubject(model.getSubject());
        announcement.setInternetPublish(model.getInternetPublish());
        announcement.setPeriodeEndDate(model.getPeriodeEndDate());
        announcement.setPeriodeStartDate(model.getPeriodeStartDate());
        announcement.setTimeModel(model.getTimeModel());
        return announcement;
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    public EmployeeTypeService getEmployeeTypeService() {
        return employeeTypeService;
    }

    public void setEmployeeTypeService(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    public GolonganJabatanService getGolonganJabatanService() {
        return golonganJabatanService;
    }

    public void setGolonganJabatanService(GolonganJabatanService golonganJabatanService) {
        this.golonganJabatanService = golonganJabatanService;
    }

    public UnitKerjaService getUnitKerjaService() {
        return unitKerjaService;
    }

    public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
        this.unitKerjaService = unitKerjaService;
    }

    public AnnouncementModel getModel() {
        return model;
    }

    public void setModel(AnnouncementModel model) {
        this.model = model;
    }

    public AnnouncementService getAnnouncementService() {
        return announcementService;
    }

    public void setAnnouncementService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

}
