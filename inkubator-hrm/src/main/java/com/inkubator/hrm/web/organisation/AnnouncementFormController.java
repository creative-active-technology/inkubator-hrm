/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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

import ch.lambdaj.Lambda;

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
import com.inkubator.hrm.util.UploadFilesUtil;
import com.inkubator.hrm.web.model.AnnouncementModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

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
    @ManagedProperty(value = "#{uploadFilesUtil}")
	private UploadFilesUtil uploadFilesUtil;
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
            dualListEmployeeType.setSource(listEmployeeType);
            dualListGolonganJabatan.setSource(listGolonganJabatan);
            dualListUnitKerja.setSource(listUnitKerja);

            //insert value to model to show in form
            model.setDropDownCompany(listCompany);
            model.setDualListEmployeeType(dualListEmployeeType);
            model.setDualListGolonganJabatan(dualListGolonganJabatan);
            model.setDualListUnitKerja(dualListUnitKerja);
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
        uploadFilesUtil = null;
    }

    public void handingAttachmentUpload(FileUploadEvent fileUploadEvent) {
    	Map<String, String> results = uploadFilesUtil.checkUploadFileSizeLimit(fileUploadEvent.getFile());
		if(StringUtils.equals(results.get("result"),"true")){
			UploadedFile attachmentFile = fileUploadEvent.getFile();
			model.setAttachmentFile(attachmentFile);
			model.setAttachmentFileName(attachmentFile.getFileName());                        
		} else {
			ResourceBundle messages = ResourceBundle.getBundle("Messages", new Locale(FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString()));
			String errorMsg = messages.getString("global.file_size_should_not_bigger_than") + " " + results.get("sizeMax");
			MessagesResourceUtil.setMessagesFromException(FacesMessage.SEVERITY_ERROR, "global.error", errorMsg, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		}
    }

    public String doSaved() throws Exception {
        Announcement announcement = getEntityFromViewModel(model);
        List<Long> listEmployeeTypeId = Lambda.extract(model.getDualListEmployeeType().getTarget(), Lambda.on(EmployeeType.class).getId());
    	List<Long> listGolonganJabatanId = Lambda.extract(model.getDualListGolonganJabatan().getTarget(), Lambda.on(GolonganJabatan.class).getId());
    	List<Long> listUnitKerjaId = Lambda.extract(model.getDualListUnitKerja().getTarget(), Lambda.on(UnitKerja.class).getId());
        try {
        	String message = announcementService.saveWithApproval(announcement, model.getAttachmentFile(), listEmployeeTypeId, listGolonganJabatanId, listUnitKerjaId);
            if (StringUtils.equals(message, "success_need_approval")) {
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully_and_requires_approval",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
            	MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
            		FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/organisation/announcement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
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
        announcement.setViewModel(model.getViewModel());
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

	public void setGolonganJabatanService(
			GolonganJabatanService golonganJabatanService) {
		this.golonganJabatanService = golonganJabatanService;
	}

	public UnitKerjaService getUnitKerjaService() {
		return unitKerjaService;
	}

	public void setUnitKerjaService(UnitKerjaService unitKerjaService) {
		this.unitKerjaService = unitKerjaService;
	}

	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public UploadFilesUtil getUploadFilesUtil() {
		return uploadFilesUtil;
	}

	public void setUploadFilesUtil(UploadFilesUtil uploadFilesUtil) {
		this.uploadFilesUtil = uploadFilesUtil;
	}

	public AnnouncementModel getModel() {
		return model;
	}

	public void setModel(AnnouncementModel model) {
		this.model = model;
	}

}
