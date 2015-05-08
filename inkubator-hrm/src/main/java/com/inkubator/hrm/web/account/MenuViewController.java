package com.inkubator.hrm.web.account;

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
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.lazymodel.HrmMenuLazyDataModel;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "menuViewController")
@ViewScoped
public class MenuViewController extends BaseController {

    private HrmMenuSearchParameter searchParameter;
    private LazyDataModel<HrmMenu> lazyDataMenu;
    private HrmMenu selectedMenu;
    @ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new HrmMenuSearchParameter();
    }

    @PreDestroy
    public void cleanAndExit() {
        hrmMenuService = null;
        searchParameter = null;
        lazyDataMenu = null;
        selectedMenu = null;
    }

    public HrmMenuSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(HrmMenuSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<HrmMenu> getLazyDataMenu() {
		if(lazyDataMenu == null){
			lazyDataMenu = new HrmMenuLazyDataModel(searchParameter, hrmMenuService);
		}
		return lazyDataMenu;
	}

	public void setLazyDataMenu(LazyDataModel<HrmMenu> lazyDataMenu) {
		this.lazyDataMenu = lazyDataMenu;
	}

	public HrmMenu getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(HrmMenu selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}

	public void doSearch() {
        lazyDataMenu = null;
    }

    public String doDetail() {
        return "/protected/account/menu_detail.htm?faces-redirect=true&execution=e" + selectedMenu.getId();
    }

    public void doSelectEntity() {
        try {
            selectedMenu = this.hrmMenuService.getEntiyByPK(selectedMenu.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDelete() {
        try {
            hrmMenuService.delete(selectedMenu);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete menu ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete menu ", ex);
        }
    }

    public String doAdd() {
        return "/protected/account/menu_form.htm?faces-redirect=true";
    }

    public String doUpdate() {
        return "/protected/account/menu_form.htm?faces-redirect=true&execution=e" + selectedMenu.getId();
    }
    
    public String doViewShema(){
           return "/protected/account/menu_diagram.htm?faces-redirect=true";
    }
}
