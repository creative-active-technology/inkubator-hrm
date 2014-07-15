package com.inkubator.hrm.web.account;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.FavoriteMenu;
import com.inkubator.hrm.service.FavoriteMenuService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "favoriteMenuViewController")
@ViewScoped
public class FavoriteMenuViewController extends BaseController {

	private List<FavoriteMenu> favoriteMenus;
	@ManagedProperty(value = "#{favoriteMenuService}")
	private FavoriteMenuService favoriteMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        favoriteMenus = favoriteMenuService.getAllDataByUserIdWithMenus(UserInfoUtil.getUserName());
	}
	
	@PreDestroy
    public void cleanAndExit() {
		favoriteMenuService = null;
	}

	public void setFavoriteMenuService(FavoriteMenuService favoriteMenuService) {
		this.favoriteMenuService = favoriteMenuService;
	}	
	
	public List<FavoriteMenu> getFavoriteMenus() {
		return favoriteMenus;
	}

	public void setFavoriteMenus(List<FavoriteMenu> favoriteMenus) {
		this.favoriteMenus = favoriteMenus;
	}

	public String doUpdate() {
        return "/protected/account/favorite_menu_form.htm?faces-redirect=true";
    }
}
