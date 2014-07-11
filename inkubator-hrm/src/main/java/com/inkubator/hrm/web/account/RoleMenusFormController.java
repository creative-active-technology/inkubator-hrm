package com.inkubator.hrm.web.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.service.HrmMenuService;
import com.inkubator.hrm.web.lazymodel.HrmMenuExceptMenuIdsLazyDataModel;
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
	private HrmMenu selectedMenu;
	private List<String> strMenuIds;
	@ManagedProperty(value = "#{hrmMenuService}")
    private HrmMenuService hrmMenuService;
	
	@PostConstruct
    @Override
    public void initialization() {
		super.initialization();
		searchParameter = new HrmMenuSearchParameter();
		String[] params = FacesUtil.getExternalContext().getRequestParameterValuesMap().get("menuIds");
		strMenuIds = (params == null) ? new ArrayList<String>() : new ArrayList<String>(Arrays.asList(params));
	}
	
	@PreDestroy
    public void cleanAndExit() {
		searchParameter = null;
		lazyDataMenu = null;
		selectedMenu = null;
		hrmMenuService = null;
		strMenuIds = null;
	}

	public HrmMenuSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(HrmMenuSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public List<String> getStrMenuIds() {
		return strMenuIds;
	}

	public void setStrMenuIds(List<String> strMenuIds) {
		this.strMenuIds = strMenuIds;
	}

	public HrmMenu getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(HrmMenu selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public LazyDataModel<HrmMenu> getLazyDataMenu() {
		if(lazyDataMenu == null){
			lazyDataMenu = new HrmMenuExceptMenuIdsLazyDataModel(searchParameter, hrmMenuService, strMenuIds);
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
	
	public void doPickMenuFromList(){
		RequestContext.getCurrentInstance().closeDialog(selectedMenu);
		cleanAndExit();
	}
}
