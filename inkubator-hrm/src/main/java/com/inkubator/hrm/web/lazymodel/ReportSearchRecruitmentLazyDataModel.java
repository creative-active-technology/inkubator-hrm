/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author arsyad_
 */

public class ReportSearchRecruitmentLazyDataModel extends LazyDataModel<RecruitApplicant> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(ReportSearchRecruitmentLazyDataModel.class);
    private final ReportSearchRecruitmentSearchParameter searchParameter;
    private final RecruitApplicantService recruitApplicantService;
    private List<RecruitApplicant> list = new ArrayList<>();
    private Integer total;
    
    public ReportSearchRecruitmentLazyDataModel(ReportSearchRecruitmentSearchParameter searchParameter, RecruitApplicantService recruitApplicantService){
        this.searchParameter = searchParameter;
        this.recruitApplicantService = recruitApplicantService;
    }
    
    @Override
    public List<RecruitApplicant> load(int firstResult, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters){
    	LOGGER.info("Step load lazy data Model");
    	try{
            Order orderable = null;
    	    if(sortField != null){
    	    	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
    	    } else {
    	    	orderable = Order.asc("id");
            }
    			
    	    list = recruitApplicantService.getByParamForReportSearchRecruitment(searchParameter, firstResult, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitApplicantService.getTotalByParamforReportSearchRecruitment(searchParameter)));
            LOGGER.info("Success load lazy data model");
    	} catch (Exception ex){
    		LOGGER.error("Failed to load lazy data model");
    		LOGGER.error("Error : ", ex);
    	}
    	
    	setPageSize(pageSize);
    	setRowCount(total);
    	return list;
    }
    
    @Override
    public Object getRowKey(RecruitApplicant recruitApplicant){
    	return recruitApplicant.getId();
    }
    
    @Override
    public RecruitApplicant getRowData(String id){
    	for (RecruitApplicant recruitApplicant : list){
    		if(id.equals(String.valueOf(recruitApplicant.getId()))){
    			return recruitApplicant;
    		}
    	}
    	return null;
    }

	@Override
	public void setRowIndex(int rowIndex) {
		if(rowIndex == -1 || getPageSize() == 0){
    		super.setRowIndex(-1);
    	} else {
    		super.setRowIndex(rowIndex % getPageSize());
    	}
	}
    
}
