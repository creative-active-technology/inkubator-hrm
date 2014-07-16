package com.inkubator.hrm.web.account;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.FavoriteMenu;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.FavoriteMenuService;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.lazymodel.HrmMenuAvailableForFavoriteLazyDataModel;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

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
	@ManagedProperty(value = "#{hrmMenuService}")
	private HrmMenuService hrmMenuService;
	@ManagedProperty(value = "#{favoriteMenuService}")
	private FavoriteMenuService favoriteMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        List<FavoriteMenu> listFavoriteMenu = favoriteMenuService.getAllDataByUserIdWithMenus(UserInfoUtil.getUserName());
        for(FavoriteMenu favoriteMenu : listFavoriteMenu){
        	favoriteMenus.add(favoriteMenu.getHrmMenu());
        }
	}
	
	@PreDestroy
    public void cleanAndExit() {
		parameter = null;
		lazyAvailableMenus = null;
		favoriteMenus = null;
		favoriteMenuService = null;
		hrmMenuService = null;
	}
	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<HrmMenu> getLazyAvailableMenus() {
		if(lazyAvailableMenus == null){
			lazyAvailableMenus = new HrmMenuAvailableForFavoriteLazyDataModel(parameter, hrmMenuService);
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

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}

	public void setFavoriteMenuService(FavoriteMenuService favoriteMenuService) {
		this.favoriteMenuService = favoriteMenuService;
	}
	
	public void doSearchAvailableMenus(){
		lazyAvailableMenus = null;
	}
	
	public void doSave(){
		
	}
	
	public String doBack() {
        return "/protected/account/favorite_menu_view.htm?faces-redirect=true";
    }
}
