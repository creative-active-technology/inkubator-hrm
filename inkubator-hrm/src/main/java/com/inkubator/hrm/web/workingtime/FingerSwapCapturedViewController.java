package com.inkubator.hrm.web.workingtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.FingerSwapCaptured;
import com.inkubator.hrm.entity.MecineFinger;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.FingerSwapCapturedService;
import com.inkubator.hrm.service.MecineFingerService;
import com.inkubator.hrm.web.lazymodel.FingerSwapCapturedLazyDataModel;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;
import com.inkubator.webcore.controller.BaseController;

/**
 *
 * @author rizkykojek
 */
@ManagedBean(name = "fingerSwapCapturedViewController")
@ViewScoped
public class FingerSwapCapturedViewController extends BaseController {
	
    private FingerSwapCapturedSearchParameter searchParameter;
    private LazyDataModel<FingerSwapCapturedViewModel> lazyData;
    private FingerSwapCaptured selectedFingerSwapCaptured;
    private List<MecineFinger> listMecineFinger;
    
    @ManagedProperty(value = "#{fingerSwapCapturedService}")
    private FingerSwapCapturedService fingerSwapCapturedService;
    @ManagedProperty(value = "#{empDataService}")
    private EmpDataService empDataService;
    @ManagedProperty(value = "#{mecineFingerService}")
    private MecineFingerService mecineFingerService;

    @PostConstruct
    @Override
    public void initialization() {
        super.initialization();
        searchParameter = new FingerSwapCapturedSearchParameter();
        try {
			listMecineFinger = mecineFingerService.getAllData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @PreDestroy
    public void cleanAndExit() {
        searchParameter = null;
        lazyData = null;
        selectedFingerSwapCaptured = null;
        fingerSwapCapturedService = null;
        empDataService = null;
        mecineFingerService = null;
        listMecineFinger = null;
    }	

	public void doSearch() {
		lazyData = null;
    }
	
	public List<EmpData> doAutoCompleteEmployee(String param){
		List<EmpData> empDatas = new ArrayList<EmpData>();
		try {
			empDatas = empDataService.getAllDataByNameOrNik(StringUtils.stripToEmpty(param));
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}
		return empDatas;
	}
	
	public void doUpload(){
		Map<String, List<String>> dataToSend = new HashMap<>();
		Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentWidth", 600);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("finger_swap_captured_file", options, dataToSend);
	}	

	public FingerSwapCapturedSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(FingerSwapCapturedSearchParameter searchParameter) {
		this.searchParameter = searchParameter;
	}

	public LazyDataModel<FingerSwapCapturedViewModel> getLazyData() {
		if(lazyData == null){
			lazyData =  new FingerSwapCapturedLazyDataModel(searchParameter, fingerSwapCapturedService);
		}
		return lazyData;
	}

	public void setLazyData(LazyDataModel<FingerSwapCapturedViewModel> lazyData) {
		this.lazyData = lazyData;
	}

	public FingerSwapCaptured getSelectedFingerSwapCaptured() {
		return selectedFingerSwapCaptured;
	}

	public void setSelectedFingerSwapCaptured(FingerSwapCaptured selectedFingerSwapCaptured) {
		this.selectedFingerSwapCaptured = selectedFingerSwapCaptured;
	}

	public FingerSwapCapturedService getFingerSwapCapturedService() {
		return fingerSwapCapturedService;
	}

	public void setFingerSwapCapturedService(FingerSwapCapturedService fingerSwapCapturedService) {
		this.fingerSwapCapturedService = fingerSwapCapturedService;
	}

	public EmpDataService getEmpDataService() {
		return empDataService;
	}

	public void setEmpDataService(EmpDataService empDataService) {
		this.empDataService = empDataService;
	}

	public List<MecineFinger> getListMecineFinger() {
		return listMecineFinger;
	}

	public void setListMecineFinger(List<MecineFinger> listMecineFinger) {
		this.listMecineFinger = listMecineFinger;
	}

	public MecineFingerService getMecineFingerService() {
		return mecineFingerService;
	}

	public void setMecineFingerService(MecineFingerService mecineFingerService) {
		this.mecineFingerService = mecineFingerService;
	}
    
}
