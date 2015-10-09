package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.inkubator.hrm.entity.KlasifikasiKerja;
import com.inkubator.hrm.entity.OrgTypeOfSpecList;
import com.inkubator.hrm.entity.OrgTypeOfSpecListKlasifikasi;
import com.inkubator.hrm.service.OrgTypeOfSpecListService;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@ManagedBean(name = "orgTypeOfSpecListDetailController")
@ViewScoped
public class OrgTypeOfSpecListDetailController extends BaseController {
    @ManagedProperty(value = "#{orgTypeOfSpecListService}")
    private OrgTypeOfSpecListService orgTypeOfSpecListService;
    private OrgTypeOfSpecList selectedOrgTypeOfSpecList;
    private List<OrgTypeOfSpecListKlasifikasi> listOrgTypeOfSpecListKlasifikasi;
    
    @PostConstruct
    @Override
    public void initialization() {
        try {
            
            super.initialization();
            
            String orgTypeOfSpecListId = FacesUtil.getRequestParameter("execution");
            selectedOrgTypeOfSpecList = orgTypeOfSpecListService.getAllDataWithDetail(Long.parseLong(orgTypeOfSpecListId.substring(1)));
            
            
            listOrgTypeOfSpecListKlasifikasi = new ArrayList<>(selectedOrgTypeOfSpecList.getOrgTypeOfSpecListKlasifikasis());
            
        } catch (Exception ex) {
            LOGGER.error("Error", ex);

        }
    }
    
    public String doBack() {
        return "/protected/organisation/org_typespec_list_view.htm?faces-redirect=true";
    }
    
    public String doEdit() {
        return "/protected/organisation/org_typespec_list_form.htm?faces-redirect=true&execution=e" + selectedOrgTypeOfSpecList.getId();
    }
    
    @PreDestroy
    public void cleanAndExit() {
    	selectedOrgTypeOfSpecList = null;
    	orgTypeOfSpecListService = null;
    	listOrgTypeOfSpecListKlasifikasi = null;
    }

	public OrgTypeOfSpecListService getOrgTypeOfSpecListService() {
		return orgTypeOfSpecListService;
	}

	public void setOrgTypeOfSpecListService(
			OrgTypeOfSpecListService orgTypeOfSpecListService) {
		this.orgTypeOfSpecListService = orgTypeOfSpecListService;
	}

	public OrgTypeOfSpecList getSelectedOrgTypeOfSpecList() {
		return selectedOrgTypeOfSpecList;
	}

	public void setSelectedOrgTypeOfSpecList(
			OrgTypeOfSpecList selectedOrgTypeOfSpecList) {
		this.selectedOrgTypeOfSpecList = selectedOrgTypeOfSpecList;
	}

	public List<OrgTypeOfSpecListKlasifikasi> getListOrgTypeOfSpecListKlasifikasi() {
		return listOrgTypeOfSpecListKlasifikasi;
	}

	public void setListOrgTypeOfSpecListKlasifikasi(
			List<OrgTypeOfSpecListKlasifikasi> listOrgTypeOfSpecListKlasifikasi) {
		this.listOrgTypeOfSpecListKlasifikasi = listOrgTypeOfSpecListKlasifikasi;
	}

	

	
    
    
}
