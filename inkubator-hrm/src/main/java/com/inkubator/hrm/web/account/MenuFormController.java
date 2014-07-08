package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.exception.BussinessException;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.model.MenuModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "menuFormController")
@ViewScoped
public class MenuFormController extends BaseController {

    private MenuModel model;
    private Boolean isUpdate;
    @ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService menuService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String param = FacesUtil.getRequestParameter("execution");
        model = new MenuModel();
        isUpdate = Boolean.FALSE;
        if (StringUtils.isNotEmpty(param)) {
            try {
                HrmMenu menu = menuService.getEntiyByPK(Long.parseLong(param.substring(1)));
                if (menu != null) {
                    getModelFromEntity(menu);
                    isUpdate = Boolean.TRUE;
                }
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
        }
    }

    @PreDestroy
    public void cleanAndExit() {
        menuService = null;
        model = null;
        isUpdate = null;
    }
	
	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public void setMenuService(HrmMenuService menuService) {
		this.menuService = menuService;
	}

    public String doSave() {
        HrmMenu menu = getEntityFromViewModel(model);
        try {
            if (isUpdate) {
                menuService.update(menu);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());

            } else {
                menuService.save(menu);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
            return "/protected/account/menu_detail.htm?faces-redirect=true&execution=e" + menu.getId();
        } catch (BussinessException ex) { //data already exist(duplicate)
            LOGGER.error("Error", ex);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", ex.getErrorKeyMessage(), FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
    }

    private HrmMenu getEntityFromViewModel(MenuModel model) {
        HrmMenu menu = new HrmMenu();
        if(model.getId() != null){
        	menu.setId(model.getId());
        }
        menu.setName(model.getIconName());
        menu.setIconName(model.getIconName());
        menu.setUrlName(model.getUrlName());
        menu.setMenuLevel(model.getMenuLevel());
        menu.setMenuStyle(model.getMenuStyle());
        menu.setMenuStyleClass(model.getMenuStyleClass());

        return menu;
    }

    private void getModelFromEntity(HrmMenu menu) {
        model.setId(menu.getId());
        model.setName(menu.getName());
        model.setIconName(menu.getIconName());
        model.setUrlName(menu.getUrlName());
        model.setMenuLevel(menu.getMenuLevel());
        model.setMenuStyle(menu.getMenuStyle());
        model.setMenuStyleClass(menu.getMenuStyleClass());
    }

    public String doBack() {
        return "/protected/account/menu_view.htm?faces-redirect=true";
    }
}
