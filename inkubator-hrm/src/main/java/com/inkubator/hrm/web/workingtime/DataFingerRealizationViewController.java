package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.DataFingerRealizationLazyDataModel;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;
import com.inkubator.hrm.web.search.DataFingerRealizationSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "dataFingerRealizationViewController")
@ViewScoped
public class DataFingerRealizationViewController extends BaseController {

    private DataFingerRealizationSearchParameter searchParameter;
    private LazyDataModel<DataFingerRealizationModel> lazyData;
    private DataFingerRealizationModel selectedModel;
    @ManagedProperty(value = "#{tempProcessReadFingerService}")
    private TempProcessReadFingerService tempProcessReadFingerService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        try {
	        searchParameter = new DataFingerRealizationSearchParameter();
        } catch (Exception ex) {
            LOGGER.error("Error", ex);
        }
    }

    @PreDestroy
    public void cleanAndExit() {
    	searchParameter = null;
    	lazyData = null;
    	selectedModel = null;
    	tempProcessReadFingerService = null;
    }
    
	public DataFingerRealizationSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(
			DataFingerRealizationSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public DataFingerRealizationModel getSelectedModel() {
		return selectedModel;
	}

	public void setSelectedModel(DataFingerRealizationModel selectedModel) {
		this.selectedModel = selectedModel;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(
			TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
	}

	public LazyDataModel<DataFingerRealizationModel> getLazyData() {
		if(lazyData == null){
			lazyData = new DataFingerRealizationLazyDataModel(searchParameter, tempProcessReadFingerService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<DataFingerRealizationModel> lazyData) {
		this.lazyData = lazyData;
	}	

	public void doSearch() {
        lazyData = null;
    }
	
	public void doSync(){
		try {
			tempProcessReadFingerService.synchDataFingerRealization();
		} catch (Exception e) {
			LOGGER.error("Error ", e);
		}
	}

    public String doDetail() {
        return "/protected/working_time/data_finger_realization_detail.htm?faces-redirect=true&execution=e" + selectedModel.getNik();
    }
    
}
