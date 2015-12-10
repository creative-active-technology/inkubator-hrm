package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.AppraisalCompetencyType;
import com.inkubator.hrm.web.search.AppraisalCompetencyTypeSearchParameter;

/**
* @author Ahmad Mudzakkir Amal
*/
public interface AppraisalCompetencyTypeService extends IService<AppraisalCompetencyType> {
	
	public List<AppraisalCompetencyType> getListByParam(AppraisalCompetencyTypeSearchParameter searchParameter, int firstResult, int maxResult, Order order) throws Exception;
	
	public Long getTotalByParam(AppraisalCompetencyTypeSearchParameter searchParameter) throws Exception;
	
	public String saveDataCompetenceType(AppraisalCompetencyType competencyType, List<Long> listIdGolonganJabatan ) throws Exception;
	
	public String updateDataCompetenceType(AppraisalCompetencyType competencyType, List<Long> listIdGolonganJabatan ) throws Exception;
	
	public AppraisalCompetencyType getEntityByIdWithDetail(Long id) throws Exception;
}
