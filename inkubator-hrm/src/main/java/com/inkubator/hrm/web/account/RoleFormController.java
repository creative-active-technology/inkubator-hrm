/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.account;

import com.inkubator.common.util.RandomNumberUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.compare.ArgumentComparator;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.entity.HrmMenuRole;
import com.inkubator.hrm.entity.HrmRole;
import com.inkubator.hrm.service.HrmRoleService;
import com.inkubator.hrm.web.model.RoleModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author Deni Husni FR
 */
@ManagedBean(name = "roleFormController")
@ViewScoped
public class RoleFormController extends BaseController {

    private RoleModel roleModel;
    private Boolean isEdit;
    private List<HrmMenu> menus;
    private HrmMenu selectedMenu;
    @ManagedProperty(value = "#{hrmRoleService}")
    private HrmRoleService hrmRoleService;

    @PostConstruct
    @Override
    public void initialization() {
        try {
            roleModel = new RoleModel();
            menus = new ArrayList<HrmMenu>();

            String param = FacesUtil.getRequestParameter("execution");
            isEdit = param != null;
            if (isEdit) {
                HrmRole hrmRole = hrmRoleService.getEntityByPkWithMenus(Long.parseLong(param.substring(1)));
                getModelFromEntity(hrmRole);
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    private void cleanAndExit() {
        roleModel = null;
        isEdit = null;
        hrmRoleService = null;
        menus = null;
        selectedMenu = null;
    }

    public void setHrmRoleService(HrmRoleService hrmRoleService) {
        this.hrmRoleService = hrmRoleService;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }

    public HrmMenu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(HrmMenu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public List<HrmMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<HrmMenu> menus) {
        this.menus = menus;
    }

    public String doSave() {
        HrmRole hrmRole = getEntityFromView(roleModel);
        if (isEdit) {
            doUpdate(hrmRole);
        } else {
            doInsert(hrmRole);
        }
        return "/protected/account/role_detail.htm?faces-redirect=true&execution=e" + hrmRole.getId();
    }

    public String doBack() {
        return "/protected/account/role_view.htm?faces-redirect=true";
    }

    public void doInsert(HrmRole hrmRole) {
        try {
            boolean isDuplicate = hrmRoleService.getByRoleName(roleModel.getRoleName()) != null;
            if (isDuplicate) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "role_form.error_duplicate_role_name",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                hrmRoleService.save(hrmRole, menus);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.added_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doUpdate(HrmRole hrmRole) {
        try {
            HrmRole hrmRoleExisting = hrmRoleService.getEntiyByPK(hrmRole.getId());
            boolean isDuplicate = (hrmRoleService.getByRoleName(hrmRole.getRoleName()) != null && !StringUtils.equals(hrmRoleExisting.getRoleName(), hrmRole.getRoleName()));
            if (isDuplicate) {
                MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "role_form.error_duplicate_role_name",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            } else {
                hrmRoleService.update(hrmRole, menus);
                MessagesResourceUtil.setMessagesFlas(FacesMessage.SEVERITY_INFO, "global.save_info", "global.update_successfully",
                        FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            }
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    public void doDeleteMenu() {
        menus.remove(selectedMenu);
    }

    public void doReset() {
        if (isEdit) {
            try {
                HrmRole hrmRole = hrmRoleService.getEntityByPkWithMenus(roleModel.getId());
                getModelFromEntity(hrmRole);
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        } else {
            roleModel = new RoleModel();
        }
    }

    public void doShowMenuList() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        for (HrmMenu menu : menus) {
            values.add(String.valueOf(menu.getId()));
        }
        dataToSend.put("menuIds", values);

        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 900);
        options.put("contentHeight", 550);
        RequestContext.getCurrentInstance().openDialog("role_menus_form", options, dataToSend);
    }

    @Override
    public void onDialogReturn(SelectEvent event) {
    	
    	/** looping sampai parent paling atas dari node, 
    	 *  exclude jika terdapat duplicate parent node */    	
    	Set<HrmMenu> menuSets = new HashSet<HrmMenu>();
    	TreeNode[] selectedNodes = (TreeNode[]) event.getObject();
    	if(selectedNodes != null){
	    	for(TreeNode node : selectedNodes){
	    		HrmMenu menu = (HrmMenu) node.getData();
	    		if(menu.getId() != null){
	    			menuSets.add(menu);
	    		}
	    		
	    		while(node.getParent() != null){
	    			node = node.getParent();
	    			HrmMenu parentMenu = (HrmMenu) node.getData();
	    			if(parentMenu.getId() != null){
	    				menuSets.add(parentMenu);
	    			}
	    		}    		
	    	}
    	}
    	
    	/** order list by menu level asc, orderMenuLevel asc */
    	Comparator<HrmMenu> byMenuLevel = new ArgumentComparator(Lambda.on(HrmMenu.class).getMenuLevel());
    	Comparator<HrmMenu> byOrderLevelMenu = new ArgumentComparator(Lambda.on(HrmMenu.class).getOrderLevelMenu());
    	Comparator<HrmMenu> orderBy = ComparatorUtils.chainedComparator(byMenuLevel, byOrderLevelMenu);
    	
    	menus.clear();
    	menus = Lambda.sort(menuSets, Lambda.on(HrmMenu.class), orderBy);
    }

    public HrmRole getEntityFromView(RoleModel roleModel) {
        HrmRole hrmRole = new HrmRole();
        if (roleModel.getId() != null) {
            hrmRole.setId(roleModel.getId());
        } else {
            hrmRole.setId(Long.parseLong(RandomNumberUtil.getRandomNumber(12)));
        }
        hrmRole.setRoleName(roleModel.getRoleName());
        hrmRole.setDescription(roleModel.getDescription());
        return hrmRole;
    }

    private void getModelFromEntity(HrmRole role) {
        roleModel.setId(role.getId());
        roleModel.setRoleName(role.getRoleName());
        roleModel.setDescription(role.getDescription());
        menus.clear();
        for (HrmMenuRole menuRoles : role.getHrmMenuRoles()) {
            menus.add(menuRoles.getHrmMenu());
        }
    }
}
