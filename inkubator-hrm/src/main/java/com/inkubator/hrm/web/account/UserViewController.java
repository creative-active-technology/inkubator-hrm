/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.service.HrmUserService;
import com.inkubator.hrm.web.lazymodel.HrmUserLazyDataModel;
import com.inkubator.hrm.web.search.HrmUserSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "userViewController")
@ViewScoped
public class UserViewController extends BaseController {

    private HrmUserSearchParameter hrmUserSearchParameter;
    private LazyDataModel<HrmUser> lazyDataHrmUser;
    private HrmUser selectedHrmUser;
    @ManagedProperty(value = "#{hrmUserService}")
    private HrmUserService hrmUserService;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }
    
    public void setHrmUserService(HrmUserService hrmUserService) {
        this.hrmUserService = hrmUserService;
    }

    @PostConstruct
    @Override

    public void initialization() {
        super.initialization();
        hrmUserSearchParameter = new HrmUserSearchParameter();

    }

    public HrmUserSearchParameter getHrmUserSearchParameter() {
        return hrmUserSearchParameter;
    }

    public void setHrmUserSearchParameter(HrmUserSearchParameter hrmUserSearchParameter) {
        this.hrmUserSearchParameter = hrmUserSearchParameter;
    }

    public LazyDataModel<HrmUser> getLazyDataHrmUser() {
        if (lazyDataHrmUser == null) {
            lazyDataHrmUser = new HrmUserLazyDataModel(hrmUserSearchParameter, hrmUserService);
        }
        return lazyDataHrmUser;
    }

    public void setLazyDataHrmUser(LazyDataModel<HrmUser> lazyDataHrmUser) {
        this.lazyDataHrmUser = lazyDataHrmUser;
    }

    public HrmUser getSelectedHrmUser() {
        return selectedHrmUser;
    }

    public void setSelectedHrmUser(HrmUser selectedHrmUser) {
        this.selectedHrmUser = selectedHrmUser;
    }

    public void doSearch() {
        lazyDataHrmUser = null;
    }

    public String doAdd() {
        return "/protected/account/user_form.htm?faces-redirect=true";
    }

    public String doDetail() {
        return "/protected/account/user_detail.htm?faces-redirect=true&execution=e" + selectedHrmUser.getId();
    }

    public void onDelete() {
        try {
            selectedHrmUser = this.hrmUserService.getEntiyByPK(selectedHrmUser.getId());
        } catch (Exception ex) {
            LOGGER.error("Errpr", ex);

        }
    }

    public void doDelete() {
        try {
            this.hrmUserService.delete(selectedHrmUser);
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
}
