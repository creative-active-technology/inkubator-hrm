package com.inkubator.hrm.web.account;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.lazymodel.HrmMenuExceptRoleLazyDataModel;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "roleMenusFormController")
@ViewScoped
public class RoleMenusFormController extends BaseController {

	private HrmMenuSearchParameter searchParameter;
	private LazyDataModel<HrmMenu> lazyDataMenu;
	private Long selectedRoleId;
	@ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		searchParameter = new HrmMenuSearchParameter();
		String param = FacesUtil.getRequestParameter("roleId");
		if (StringUtils.isNumeric(param)){
			selectedRoleId = Long.parseLong(param);
		}
	}
	
	@PreDestroy
    public void cleanAndExit() {
		
	}

	public HrmMenuSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(HrmMenuSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public Long getSelectedRoleId() {
		return selectedRoleId;
	}

	public void setSelectedRoleId(Long selectedRoleId) {
		this.selectedRoleId = selectedRoleId;
	}

	public LazyDataModel<HrmMenu> getLazyDataMenu() {
		if(lazyDataMenu == null){
			lazyDataMenu = new HrmMenuExceptRoleLazyDataModel(searchParameter, hrmMenuService, selectedRoleId);
		}
		return lazyDataMenu;
	}

	public void setLazyDataMenu(LazyDataModel<HrmMenu> lazyDataMenu) {
		this.lazyDataMenu = lazyDataMenu;
	}

	public void setHrmMenuService(HrmMenuService hrmMenuService) {
		this.hrmMenuService = hrmMenuService;
	}
	
	public void doSearch(){
		lazyDataMenu = null;
	}
}
