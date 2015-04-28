package com.inkubator.hrm.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.inkubator.hrm.entity.HrmMenu;

/**
 *
 * @author rizkykojek
 */
public class MenuModel implements Serializable {
	
	private Long id;
    private Long parentMenuId;
    private List<HrmMenu> listParentMenu;
    private String name;
    private String urlName;
    private String iconName;
    private Integer menuLevel;
    private String menuStyle;
    private String menuStyleClass;
    private Boolean isGroup;
    private Integer orderLevelMenu;
    
    public MenuModel(){
    	listParentMenu = new ArrayList<HrmMenu>();
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public List<HrmMenu> getListParentMenu() {
		return listParentMenu;
	}
	public void setListParentMenu(List<HrmMenu> listParentMenu) {
		this.listParentMenu = listParentMenu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getMenuStyle() {
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	public String getMenuStyleClass() {
		return menuStyleClass;
	}
	public void setMenuStyleClass(String menuStyleClass) {
		this.menuStyleClass = menuStyleClass;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getOrderLevelMenu() {
		return orderLevelMenu;
	}

	public void setOrderLevelMenu(Integer orderLevelMenu) {
		this.orderLevelMenu = orderLevelMenu;
	}
	
}
