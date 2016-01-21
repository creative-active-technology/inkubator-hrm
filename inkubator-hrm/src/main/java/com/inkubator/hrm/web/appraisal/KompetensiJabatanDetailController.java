package com.inkubator.hrm.web.appraisal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang3.StringUtils;

import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.service.AppraisalCompetencyTypeService;
import com.inkubator.hrm.service.JabatanService;
import com.inkubator.hrm.web.model.AppraisalCompetencyTypeModel;
import com.inkubator.webcore.controller.BaseController;
import com.inkubator.webcore.util.FacesUtil;

@SuppressWarnings("serial")
public class KompetensiJabatanDetailController extends BaseController{
		
	@ManagedProperty(value = "#{jabatanService}")
    private JabatanService jabatanService;
	@ManagedProperty(value = "#{appraisalCompetencyTypeService}")
	private AppraisalCompetencyTypeService appraisalCompetencyService;
	private List<List<AppraisalCompetencyType>> dataForRenders = new ArrayList<>();
	private List<String> listKompetensiTypeName = new ArrayList<>();
	private String kompJabatanNumber;
	private AppraisalCompetencyTypeModel model;
	
	@PostConstruct
    @Override
    public void initialization() {
		try{
			model = new AppraisalCompetencyTypeModel();
			kompJabatanNumber = FacesUtil.getRequestParameter("execution");
				listKompetensiTypeName = appraisalCompetencyService.getAppraisalCompetencyTypeName();
				if (StringUtils.isNotBlank(kompJabatanNumber)){
//				dataForRenders = appraisalCompetencyService.getAllData();
				}
		} catch(Exception ex){
			LOGGER.error(ex, ex);
		}
	}
	
	public JabatanService getJabatanService() {
		return jabatanService;
	}

	public void setJabatanService(JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	public AppraisalCompetencyTypeService getAppraisalCompetencyService() {
		return appraisalCompetencyService;
	}

	public void setAppraisalCompetencyService(AppraisalCompetencyTypeService appraisalCompetencyService) {
		this.appraisalCompetencyService = appraisalCompetencyService;
	}

//	public List<List> getDataForRenders() {
//		return dataForRenders;
//	}
//
//	public void setDataForRenders(List<List> dataForRenders) {
//		this.dataForRenders = dataForRenders;
//	}

	public String getKompJabatanNumber() {
		return kompJabatanNumber;
	}

	public void setKompJabatanNumber(String kompJabatanNumber) {
		this.kompJabatanNumber = kompJabatanNumber;
	}

	public AppraisalCompetencyTypeModel getModel() {
		return model;
	}

	public void setModel(AppraisalCompetencyTypeModel model) {
		this.model = model;
	}
	
	
}
