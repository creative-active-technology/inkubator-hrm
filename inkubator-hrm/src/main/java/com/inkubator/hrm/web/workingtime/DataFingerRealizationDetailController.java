package com.inkubator.hrm.web.workingtime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.entity.WtPeriode;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.service.WtPeriodeService;
import com.inkubator.hrm.web.lazymodel.TempProcessReadFingerLazyDataModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "dataFingerRealizationDetailController")
@ViewScoped
public class DataFingerRealizationDetailController extends BaseController {
    
    private EmpData empData;
    private WtPeriode periode;
    private LazyDataModel<TempProcessReadFinger> lazyData;
    private TempProcessReadFinger selectedEntity;
    @ManagedProperty(value = "#{tempProcessReadFingerService}")
    private TempProcessReadFingerService tempProcessReadFingerService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{wtPeriodeService}")
    private WtPeriodeService wtPeriodeService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        String empDataId = FacesUtil.getRequestParameter("execution");
        try {
        	empData = empDataService.getByEmpIdWithDetail(Long.parseLong(empDataId.substring(1)));
			periode = wtPeriodeService.getEntityByAbsentTypeActive();
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
    }

    @PreDestroy
    public void cleanAndExit() {
    	periode = null;
    	empData = null;
    	lazyData = null;
    	selectedEntity = null;
    	tempProcessReadFingerService = null;
    	empDataService = null;
    	wtPeriodeService = null;
    }
    
	public WtPeriode getPeriode() {
		return periode;
	}

	public void setPeriode(WtPeriode periode) {
		this.periode = periode;
	}

	public EmpData getEmpData() {
		return empData;
	}

	public void setEmpData(EmpData empData) {
		this.empData = empData;
	}

	public TempProcessReadFinger getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(TempProcessReadFinger selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public TempProcessReadFingerService getTempProcessReadFingerService() {
		return tempProcessReadFingerService;
	}

	public void setTempProcessReadFingerService(
			TempProcessReadFingerService tempProcessReadFingerService) {
		this.tempProcessReadFingerService = tempProcessReadFingerService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public WtPeriodeService getWtPeriodeService() {
		return wtPeriodeService;
	}

	public void setWtPeriodeService(WtPeriodeService wtPeriodeService) {
		this.wtPeriodeService = wtPeriodeService;
	}

	public LazyDataModel<TempProcessReadFinger> getLazyData() {
		if(lazyData == null){
			lazyData = new TempProcessReadFingerLazyDataModel(empData.getId(), tempProcessReadFingerService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<TempProcessReadFinger> lazyData) {
		this.lazyData = lazyData;
	}	

	public void doSearch() {
        lazyData = null;
    }
	
	public void doCorrectionIn(Long id){
		try {
			selectedEntity = tempProcessReadFingerService.getEntiyByPK(id);
			tempProcessReadFingerService.doCorrectionIn(selectedEntity.getId(), !selectedEntity.getIsCorrectionIn());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}
	
	public void doCorrectionOut(Long id){
		try {
			selectedEntity = tempProcessReadFingerService.getEntiyByPK(id);
			tempProcessReadFingerService.doCorrectionOut(selectedEntity.getId(), !selectedEntity.getIsCorrectionOut());
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
	}

    public String doBack() {
        return "/protected/working_time/data_finger_real_view.htm?faces-redirect=true";
    }
    
}
