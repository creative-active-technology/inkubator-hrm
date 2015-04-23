/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.ApprovalActivityService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "announcementApprovalFormController")
@ViewScoped
public class AnnouncementApprovalFormController extends BaseController {

    private Announcement announcement; 
    private String comment;
    private Boolean isWaitingApproval;
    private Boolean isWaitingRevised;
    private Boolean isApprover;
    private Boolean isRequester;
    private Boolean isHaveAttachment;
    private ApprovalActivity selectedApprovalActivity;
    private List<EmployeeType> listEmployeeType;
    private List<GolonganJabatan> listGolonganJabatan;
    private List<UnitKerja> listUnitKerja;
    
    @ManagedProperty(value = "#{approvalActivityService}")
    private ApprovalActivityService approvalActivityService;
    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    @ManagedProperty(value = "#{companyService}")
    private CompanyService companyService;
    @ManagedProperty(value = "#{employeeTypeService}")
    private EmployeeTypeService employeeTypeService;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golonganJabatanService;
    @ManagedProperty(value = "#{unitKerjaService}")
    private UnitKerjaService unitKerjaService;
    

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            
            /** initial data for approval activity tracking */
            String approvalActivityId = FacesUtil.getRequestParameter("execution");
            selectedApprovalActivity = approvalActivityService.getEntiyByPK(Long.parseLong(approvalActivityId.substring(1)));
            isWaitingApproval = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL;
            isWaitingRevised = selectedApprovalActivity.getApprovalStatus() == HRMConstant.APPROVAL_STATUS_WAITING_REVISED;
            isApprover = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getApprovedBy());
            isRequester = StringUtils.equals(UserInfoUtil.getUserName(), selectedApprovalActivity.getRequestBy());            
            
            /** start binding data that needed (from json) to object */
            Gson gson = JsonUtil.getHibernateEntityGsonBuilder().create();
            announcement = gson.fromJson(selectedApprovalActivity.getPendingData(), Announcement.class);      
            
            //relational object
            Company company = companyService.getEntiyByPK(announcement.getCompany().getId());
            announcement.setCompany(company);
            JsonObject jsonObject = (JsonObject) gson.fromJson(selectedApprovalActivity.getPendingData(), JsonObject.class);
            
            List<Long> listEmployeeTypeId = new GsonBuilder().create().fromJson(jsonObject.get("listEmployeeTypeId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            listEmployeeType = new ArrayList<EmployeeType>();
            for(Long id : listEmployeeTypeId){
            	EmployeeType employeeType = employeeTypeService.getEntiyByPK(id);
            	if(employeeType != null){
            		listEmployeeType.add(employeeType);
            	}
            }
            
            List<Long> listGolonganJabatanId = new GsonBuilder().create().fromJson(jsonObject.get("listGolonganJabatanId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            listGolonganJabatan = new ArrayList<GolonganJabatan>();
            for(Long id : listGolonganJabatanId){
            	GolonganJabatan golonganJabatan = golonganJabatanService.getEntiyByPK(id);
            	if(golonganJabatan != null){
            		listGolonganJabatan.add(golonganJabatan);
            	}
            }
            
            List<Long> listUnitKerjaId = new GsonBuilder().create().fromJson(jsonObject.get("listUnitKerjaId").getAsString(), new TypeToken<List<Long>>() {}.getType());
            listUnitKerja = new ArrayList<UnitKerja>();
            for(Long id : listUnitKerjaId){
            	UnitKerja unitKerja = unitKerjaService.getEntiyByPK(id);
            	if(unitKerja != null){
            		listUnitKerja.add(unitKerja);
            	}
            }
            
	        //attachment file
	    	isHaveAttachment = StringUtils.isNotEmpty(announcement.getAttachmentPath());            
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	announcement = null;
        isWaitingRevised = null;
        selectedApprovalActivity = null;
        approvalActivityService = null;
        comment = null;
        isWaitingApproval = null;
        isApprover = null;
        isRequester = null;
        isHaveAttachment = null;
        companyService = null;
        announcementService = null;
        listEmployeeType = null;
        listGolonganJabatan = null;
        listUnitKerja = null;
        employeeTypeService = null;
        golonganJabatanService = null;
        unitKerjaService = null;
    }

    public String doBack() {
        return "/protected/organisation/announcement_view.htm?faces-redirect=true";
    }

    public String doRevised() {
    	return "/protected/organisation/announcement_form.htm?faces-redirect=true&activity=e" + selectedApprovalActivity.getId();
    }
    
    public String doAskingRevised() {
        try {
        	announcementService.askingRevised(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.process_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/organisation/announcement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }
    
    public String doApproved() {
        try {
        	announcementService.approved(selectedApprovalActivity.getId(), null, comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.approved_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/organisation/announcement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

    public String doRejected() {
        try {
        	announcementService.rejected(selectedApprovalActivity.getId(), comment);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.approval_info", "global.rejected_successfully",
                    FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/organisation/announcement_view.htm?faces-redirect=true";
        } catch (BussinessException ex) {            
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception e) {
            LOGGER.error("Error ", e);
        }
        return null;
    }

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsWaitingApproval() {
		return isWaitingApproval;
	}

	public void setIsWaitingApproval(Boolean isWaitingApproval) {
		this.isWaitingApproval = isWaitingApproval;
	}

	public Boolean getIsWaitingRevised() {
		return isWaitingRevised;
	}

	public void setIsWaitingRevised(Boolean isWaitingRevised) {
		this.isWaitingRevised = isWaitingRevised;
	}

	public Boolean getIsApprover() {
		return isApprover;
	}

	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	public Boolean getIsRequester() {
		return isRequester;
	}

	public void setIsRequester(Boolean isRequester) {
		this.isRequester = isRequester;
	}

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}

	public ApprovalActivity getSelectedApprovalActivity() {
		return selectedApprovalActivity;
	}

	public void setSelectedApprovalActivity(
			ApprovalActivity selectedApprovalActivity) {
		this.selectedApprovalActivity = selectedApprovalActivity;
	}

	public ApprovalActivityService getApprovalActivityService() {
		return approvalActivityService;
	}

	public void setApprovalActivityService(
			ApprovalActivityService approvalActivityService) {
		this.approvalActivityService = approvalActivityService;
	}

	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public List<EmployeeType> getListEmployeeType() {
		return listEmployeeType;
	}

	public void setListEmployeeType(List<EmployeeType> listEmployeeType) {
		this.listEmployeeType = listEmployeeType;
	}

	public List<GolonganJabatan> getListGolonganJabatan() {
		return listGolonganJabatan;
	}

	public void setListGolonganJabatan(List<GolonganJabatan> listGolonganJabatan) {
		this.listGolonganJabatan = listGolonganJabatan;
	}

	public List<UnitKerja> getListUnitKerja() {
		return listUnitKerja;
	}

	public void setListUnitKerja(List<UnitKerja> listUnitKerja) {
		this.listUnitKerja = listUnitKerja;
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
    
}
