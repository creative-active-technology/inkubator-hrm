/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.UnitKerja;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.service.EmployeeTypeService;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.service.UnitKerjaService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "announcementDetailController")
@ViewScoped
public class AnnouncementDetailController extends BaseController {
	
	private Announcement announcement;
    private Boolean isHaveAttachment;
    
    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            super.initialization();
            String id = FacesUtil.getRequestParameter("execution").substring(1);
            announcement = announcementService.getEntityByPkWithDetail(Long.parseLong(id));
            isHaveAttachment = StringUtils.isNotEmpty(announcement.getAttachmentPath());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	announcement = null;
        isHaveAttachment = null;
        announcementService = null;
    }   

    public String doBack() {
    	return "/protected/organisation/announcement_view.htm?faces-redirect=true";
    }

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public Boolean getIsHaveAttachment() {
		return isHaveAttachment;
	}

	public void setIsHaveAttachment(Boolean isHaveAttachment) {
		this.isHaveAttachment = isHaveAttachment;
	}

	public AnnouncementService getAnnouncementService() {
		return announcementService;
	}

	public void setAnnouncementService(AnnouncementService announcementService) {
		this.announcementService = announcementService;
	}
	
}
