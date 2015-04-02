package com.inkubator.hrm.web.organisation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.dao.DataIntegrityViolationException;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.service.GolonganJabatanService;
import com.inkubator.hrm.web.lazymodel.GolonganJabatanLazyDataModel;
import com.inkubator.hrm.web.search.GolonganJabatanSearchParameter;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;
import com.inkubator.webcore.util.MessagesResourceUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "golonganJabatanViewController")
@ViewScoped
public class GolonganJabatanViewController extends BaseController {
    
    private GolonganJabatanSearchParameter parameter;
    private LazyDataModel<GolonganJabatan> lazyDataGolJabatan;
    private GolonganJabatan selectedGolJabatan;
    @ManagedProperty(value = "#{golonganJabatanService}")
    private GolonganJabatanService golJabatanService;
    
    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        parameter = new GolonganJabatanSearchParameter();
    }
    
    @PreDestroy
    public void cleanAndExit() {
        golJabatanService = null;
        parameter = null;
        lazyDataGolJabatan = null;
        selectedGolJabatan = null;
    }

	public GolonganJabatanSearchParameter getParameter() {
		return parameter;
	}

	public void setParameter(GolonganJabatanSearchParameter parameter) {
		this.parameter = parameter;
	}

	public LazyDataModel<GolonganJabatan> getLazyDataGolJabatan() {
		if(lazyDataGolJabatan == null){
			lazyDataGolJabatan = new GolonganJabatanLazyDataModel(parameter, golJabatanService);
		}		
		return lazyDataGolJabatan;
	}

	public void setLazyDataGolJabatan(LazyDataModel<GolonganJabatan> lazyDataGolJabatan) {
		this.lazyDataGolJabatan = lazyDataGolJabatan;
	}

	public GolonganJabatan getSelectedGolJabatan() {
		return selectedGolJabatan;
	}

	public void setSelectedGolJabatan(GolonganJabatan selectedGolJabatan) {
		this.selectedGolJabatan = selectedGolJabatan;
	}

	public void setGolJabatanService(GolonganJabatanService golJabatanService) {
		this.golJabatanService = golJabatanService;
	}

	public void doSearch() {
        lazyDataGolJabatan = null;
    }
    
    public void doDetail() {
        try {
            selectedGolJabatan = this.golJabatanService.getEntityByPkFetchAttendPangkat(selectedGolJabatan.getId());
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }    
    
    public void doDelete() {
        try {
            golJabatanService.delete(selectedGolJabatan);
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_INFO, "global.delete", "global.delete_successfully", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            
        } catch (ConstraintViolationException | DataIntegrityViolationException ex) {
            MessagesResourceUtil.setMessages(FacesMessage.SEVERITY_ERROR, "global.error", "error.delete_constraint", FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
            LOGGER.error("Error when doDelete golonganJabatan ", ex);
        } catch (Exception ex) {
            LOGGER.error("Error when doDelete golonganJabatan", ex);
        }
    }
    
    public void doAdd() {
        showDialog(null);
    }
    
    public void doUpdate() {
        Map<String, List<String>> dataToSend = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(String.valueOf(selectedGolJabatan.getId()));
        dataToSend.put("param", values);
        showDialog(dataToSend);
    }
    
    private void showDialog(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 500);
        options.put("contentHeight", 335);
        RequestContext.getCurrentInstance().openDialog("function_group_form", options, params);
    }
    
    @Override
    public void onDialogReturn(SelectEvent event) {
        //re-calculate searching
        doSearch();
        super.onDialogReturn(event);
    }
}
