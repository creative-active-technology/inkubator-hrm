/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.organisation;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.service.AnnouncementService;
import com.inkubator.hrm.web.lazymodel.AnnouncementLazyDataModel;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni
 */
@ManagedBean(name = "announcementViewController")
@ViewScoped
public class AnnouncementViewController extends BaseController {

    @ManagedProperty(value = "#{announcementService}")
    private AnnouncementService announcementService;
    private AnnouncementSearchParameter searchParameter;
    private LazyDataModel<Announcement> lazyDataModel;
    private Announcement selected;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new AnnouncementSearchParameter();
    }

    @PreDestroy
    private void cleanAndExit() {
        searchParameter = null;
        lazyDataModel = null;
        announcementService = null;
        selected = null;

    }

    public void doSearch() {
        lazyDataModel = null;
    }

    public void doSelectEntity() {
        try {
            selected = this.announcementService.getEntiyByPK(selected.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
    	return "/protected/organisation/announcement_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    public void doDelete() {
        try {
            this.announcementService.delete(selected);
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

    public AnnouncementService getAnnouncementService() {
        return announcementService;
    }

    public void setAnnouncementService(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    public AnnouncementSearchParameter getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(AnnouncementSearchParameter searchParameter) {
        this.searchParameter = searchParameter;
    }

    public LazyDataModel<Announcement> getLazyDataModel() {
        if(lazyDataModel == null){
            lazyDataModel = new AnnouncementLazyDataModel(searchParameter, announcementService);
        }
        return lazyDataModel;
    }

    public void setLazyDataModel(LazyDataModel<Announcement> lazyDataModel) {
        this.lazyDataModel = lazyDataModel;
    }

    public Announcement getSelected() {
        return selected;
    }

    public void setSelected(Announcement selected) {
        this.selected = selected;
    }
    
    
}
