package com.inkubator.hrm.web.appraisal;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.lazymodel.KompetensiJabatanLazyDataModel;
import com.inkubator.hrm.web.model.KompetensiJabatanViewModel;
import com.inkubator.hrm.web.search.DivisiSearchParameter;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;

@ManagedBean(name = "kompetensiJabatanViewController")
@ViewScoped
public class KompetensiJabatanViewController extends BaseController{
	@ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
    private KompetensiJabatanSearchParameter searchParameter;
    private LazyDataModel<KompetensiJabatanViewModel> lazy;
    private Jabatan selected;
    
    @Override
    public void initialization(){
        super.initialization();
        searchParameter = new KompetensiJabatanSearchParameter();
    }
    
    @PreDestroy
    private void cleanAndExit(){
        searchParameter = null;
        lazy = null;
        jabatanService = null;
        selected = null;
    }
    
    public void doSearch(){
        lazy = null;
    }
    
    public void doSelectEntity(){
        try{
            selected = this.jabatanService.getEntiyByPK(selected.getId());
        } catch (Exception ex){
            LOGGER.error("Error", ex);
        }
    }
    
    public String doDetail(){
    	return "/protected/appraisal/komp_jabatan_detail.htm?faces-redirect=true&execution=e" + selected.getId();
    }
    
    @Override
    public void onDialogReturn(SelectEvent event){
        lazy = null;
        super.onDialogReturn(event);
    }

	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	public KompetensiJabatanSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(KompetensiJabatanSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<KompetensiJabatanViewModel> getLazy() {
		if (lazy == null){
			lazy = new KompetensiJabatanLazyDataModel(searchParameter, jabatanService);
		}
		return lazy;
	}

	public void setLazy(LazyDataModel<KompetensiJabatanViewModel> lazy) {
		this.lazy = lazy;
	}

	public Jabatan getSelected() {
		return selected;
	}

	public void setSelected(Jabatan selected) {
		this.selected = selected;
	}
    
    
}
