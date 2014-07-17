package com.inkubator.hrm.web.account;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.FavoriteMenu;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.FavoriteMenuService;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.lazymodel.HrmMenuAvailableForFavoriteLazyDataModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "favoriteMenuFormController")
@ViewScoped
public class FavoriteMenuFormController extends BaseController {

	private String parameter;
	private LazyDataModel<HrmMenu> lazyAvailableMenus;
	private List<HrmMenu> favoriteMenus = new ArrayList<HrmMenu>();
	private HrmMenu selectedFavoriteMenu;
	@ManagedProperty(value = "#{hrmMenuService}")
	private HrmMenuService hrmMenuService;
	@ManagedProperty(value = "#{favoriteMenuService}")
	private FavoriteMenuService favoriteMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        favoriteMenus = getUserListFavoriteMenu();        
	}

	@PreDestroy
    public void cleanAndExit() {
		parameter = null;
		lazyAvailableMenus = null;
		favoriteMenus = null;
		favoriteMenuService = null;
		hrmMenuService = null;
		selectedFavoriteMenu = null;
	}
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<HrmMenu> getLazyAvailableMenus() {
		if(lazyAvailableMenus == null){
			lazyAvailableMenus = new HrmMenuAvailableForFavoriteLazyDataModel(parameter, hrmMenuService, favoriteMenus);
		}
		return lazyAvailableMenus;
	}

	public void setLazyAvailableMenus(LazyDataModel<HrmMenu> lazyAvailableMenus) {
		this.lazyAvailableMenus = lazyAvailableMenus;
	}

	public List<HrmMenu> getFavoriteMenus() {
		return favoriteMenus;
	}

	public void setFavoriteMenus(List<HrmMenu> favoriteMenus) {
		this.favoriteMenus = favoriteMenus;
	}

	public HrmMenu getSelectedFavoriteMenu() {
		return selectedFavoriteMenu;
	}

	public void setSelectedFavoriteMenu(HrmMenu selectedFavoriteMenu) {
		this.selectedFavoriteMenu = selectedFavoriteMenu;
	}

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}

	public void setFavoriteMenuService(FavoriteMenuService favoriteMenuService) {
		this.favoriteMenuService = favoriteMenuService;
	}
	
	public void doSearchAvailableMenus(){
		lazyAvailableMenus = null;
	}
	
	public String doSave(){
		try {
			favoriteMenuService.saveOrUpdate(UserInfoUtil.getUserName(), favoriteMenus);
            MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            return "/protected/account/favorite_menu_view.htm?faces-redirect=true";
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
        return null;
	}
	
	public void doReset(){
		favoriteMenus = getUserListFavoriteMenu();
	}
	
	public String doBack() {
        return "/protected/account/favorite_menu_view.htm?faces-redirect=true";
    }
	
	public void doRemoveFavoriteMenu(){
		favoriteMenus.remove(selectedFavoriteMenu);
		doSearchAvailableMenus(); //recalculate searching available menus
	}
	
	public void onMenuDrop(DragDropEvent event){
		HrmMenu droppedMenu = (HrmMenu) event.getData();
		if(favoriteMenus.contains(droppedMenu)){
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "favorite_menu.menu_already_added", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		} else if(favoriteMenus.size()>= 6){
			MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "favorite_menu.max_six_menu_in_favorite_menu", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
		} else {
			favoriteMenus.add(droppedMenu);
			doSearchAvailableMenus(); //recalculate searching available menus
		}
	}
	
	private List<HrmMenu> getUserListFavoriteMenu() {
		List<FavoriteMenu> listUserFavoriteMenu = new ArrayList<FavoriteMenu>();
		try {
			listUserFavoriteMenu = favoriteMenuService.getAllDataByUserIdWithMenus(UserInfoUtil.getUserName());
		} catch (Exception e) {
			LOGGER.error("Error in FavoriteMenuFormController ", e);
		}
		
		List<HrmMenu> favoriteMenus = new ArrayList<HrmMenu>();
        for(FavoriteMenu favoriteMenu : listUserFavoriteMenu){
        	favoriteMenus.add(favoriteMenu.getHrmMenu());
        }
		return favoriteMenus;
	}
}
