package com.inkubator.hrm.web;

import com.inkubator.hrm.entity.FavoriteMenu;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.FavoriteMenuService;
import com.inkubator.securitycore.util.UserInfoUtil;
import com.inkubator.webcore.controller.BaseController;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "homeFavoriteMenuController")
@RequestScoped
public class HomeFavoriteMenuController extends BaseController {

    private List<HrmMenu> favoriteMenus;
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
        favoriteMenus = null;
        favoriteMenuService = null;
    }

    public List<HrmMenu> getFavoriteMenus() {
        return favoriteMenus;
    }

    public void setFavoriteMenus(List<HrmMenu> favoriteMenus) {
        this.favoriteMenus = favoriteMenus;
    }

    public void setFavoriteMenuService(FavoriteMenuService favoriteMenuService) {
        this.favoriteMenuService = favoriteMenuService;
    }

    private List<HrmMenu> getUserListFavoriteMenu() {
        List<FavoriteMenu> listUserFavoriteMenu = new ArrayList<FavoriteMenu>();
        try {
            listUserFavoriteMenu = favoriteMenuService.getAllDataByUserIdWithMenus(UserInfoUtil.getUserName());
        } catch (Exception e) {
            LOGGER.error("Error in HomeFavoriteMenuController ", e);
        }

        List<HrmMenu> menus = new ArrayList<HrmMenu>();
        for (FavoriteMenu favoriteMenu : listUserFavoriteMenu) {
            menus.add(favoriteMenu.getHrmMenu());
        }
        return menus;
    }
}
